package fr.ewaux.backend.service.tournament.impl;

import fr.ewaux.backend.entity.match.MatchEntity;
import fr.ewaux.backend.entity.phase.GroupEntity;
import fr.ewaux.backend.entity.step.StepEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.backend.entity.tournament.TournamentEntity;
import fr.ewaux.backend.mapper.club.ClubMapper;
import fr.ewaux.backend.mapper.step.StepMapper;
import fr.ewaux.backend.mapper.team.TeamMapper;
import fr.ewaux.backend.mapper.tournament.TournamentMapper;
import fr.ewaux.backend.model.request.tournament.TournamentRequest;
import fr.ewaux.backend.model.response.phase.GroupResponse;
import fr.ewaux.backend.model.response.ranking.RankingResponse;
import fr.ewaux.backend.model.response.step.StepResponse;
import fr.ewaux.backend.model.response.team.TeamResponse;
import fr.ewaux.backend.model.response.tournament.TournamentResponse;
import fr.ewaux.backend.repository.tournament.TournamentRepository;
import fr.ewaux.backend.service.phase.GroupService;
import fr.ewaux.backend.service.step.StepService;
import fr.ewaux.backend.service.team.TeamService;
import fr.ewaux.backend.service.tournament.TournamentService;
import fr.ewaux.commons.utilities.utils.StringHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.math3.util.Pair;
import org.paukov.combinatorics3.Generator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tournamentService")
@Slf4j
public class TournamentServiceImpl implements TournamentService {

	TournamentRepository tournamentRepository;
	TeamService teamService;
	GroupService groupService;
	StepService stepService;

	public TournamentServiceImpl(final TournamentRepository tournamentRepository, final TeamService teamService,
		final GroupService groupService, final StepService stepService) {
		super();
		this.tournamentRepository = tournamentRepository;
		this.teamService = teamService;
		this.groupService = groupService;
		this.stepService = stepService;
	}

	@Override
	public Set<TournamentResponse> findAll() {
		Set<StepResponse> stepResponses = StepMapper.mapEntitiesToModels(this.stepService.listAll());
		Set<TournamentResponse> tournamentResponses = TournamentMapper.mapEntitiesToSimpleSetModels(
			this.tournamentRepository.findAll(Sort.by("date")));
		tournamentResponses.forEach(t -> this.calculateCurrentStep(t, stepResponses));
		return tournamentResponses;
	}

	@Transactional
	@Override
	public TournamentResponse add(final TournamentRequest request) {
		TournamentEntity entity = TournamentMapper.mapModelToEntity(request);
		if (Objects.nonNull(entity)) {
			StepEntity stepEntity = null;
			if (Objects.nonNull(entity.getTeams())) {
				List<TeamEntity> teamEntities = this.teamService.addAll(entity.getTeams());
				entity.setTeams(teamEntities);
				if (entity.getTeams().size() == 2 && request.getMatchType() == 1) {
					stepEntity = this.stepService.findByName("Final stage");
				} else {
					stepEntity = this.stepService.findByName("Qualifying stage");
					entity.setGroups(this.groupService.addAll(
						this.generateGroups(request.getNbGroup(), request.getMatchType(), teamEntities, stepEntity)));
				}
			}
			TournamentResponse model = TournamentMapper.mapEntityToModel(this.tournamentRepository.save(entity));
			if (Objects.nonNull(model)) {
				model.setStep(StepMapper.mapEntityToModel(stepEntity));
			}
			return model;
		}
		return null;
	}

	@Transactional
	@Override
	public TournamentResponse findById(final Long id) {
		Set<StepResponse> stepResponses = StepMapper.mapEntitiesToModels(this.stepService.listAll());
		TournamentEntity tournamentEntity = this.tournamentRepository.findById(id).orElse(null);
		TournamentResponse model = TournamentMapper.mapEntityToModel(tournamentEntity);
		this.calculateCurrentStep(model, stepResponses);
		if (Objects.nonNull(model) && Objects.nonNull(model.getStep()) && model.getStep().getNameEn().equals("Final stage") && Objects.nonNull(
			tournamentEntity)) {
			List<TeamResponse> firstWinners = new ArrayList<>();
			List<TeamResponse> secondWinners = new ArrayList<>();
			model.getGroups().forEach(g -> {
				if (CollectionUtils.isNotEmpty(g.getRankings())) {
					RankingResponse rankingFirstWinner = g.getRankings().stream().filter(r -> r.getRank() == 1).findFirst().orElse(null);
					RankingResponse rankingSecondWinner = g.getRankings().stream().filter(r -> r.getRank() == 2).findFirst().orElse(null);
					if (Objects.nonNull(rankingFirstWinner)) {
						firstWinners.add(rankingFirstWinner.getTeam());
					}
					if (Objects.nonNull(rankingSecondWinner)) {
						secondWinners.add(rankingSecondWinner.getTeam());
					}
				}
			});
			if (CollectionUtils.isNotEmpty(firstWinners) && CollectionUtils.isNotEmpty(secondWinners)) {
				List<TeamEntity> teamEntities = new ArrayList<>();
				Iterator<TeamResponse> i1 = firstWinners.iterator();
				List<MatchEntity> matchEntities = new ArrayList<>();
				while (i1.hasNext()) {
					TeamResponse teamResponse1 = i1.next();
					TeamEntity t1 = tournamentEntity.getTeams().stream().filter(t -> Objects.equals(t.getId(), teamResponse1.getId())).findFirst()
						.orElseThrow(() -> new RuntimeException("technicalError"));
					i1.remove();
					Iterator<TeamResponse> i2 = secondWinners.iterator();
					teamEntities.add(t1);
					while (i2.hasNext()) {
						TeamResponse teamResponse2 = i2.next();
						TeamEntity t2 = tournamentEntity.getTeams().stream().filter(t -> Objects.equals(t.getId(), teamResponse2.getId())).findFirst()
							.orElseThrow(() -> new RuntimeException("technicalError"));
						i2.remove();
						teamEntities.add(t2);
						matchEntities.add(MatchEntity.builder().home(t1).away(t2).build());
					}
				}
				log.info(" " + teamEntities);
				log.info(" " + matchEntities);
			}
		}
		return model;
	}

	@Transactional
	@Override
	public void deleteById(final Long id) {
		Optional<TournamentEntity> entityOptional = this.tournamentRepository.findById(id);
		if (entityOptional.isPresent()) {
			this.groupService.deleteAll(entityOptional.get().getGroups());
			this.teamService.deleteAll(entityOptional.get().getTeams());
			this.tournamentRepository.deleteById(id);
		}
	}

	private List<GroupEntity> generateGroups(final int nbGroup, final int typeMatch, final List<TeamEntity> teamEntities,
		final StepEntity stepEntity) {
		List<GroupEntity> groupEntities = new ArrayList<>();
		List<TeamEntity> copyTeams = new ArrayList<>(teamEntities);
		Set<TeamEntity> already = new HashSet<>();
		int nbTeamsByGroup = copyTeams.size() / nbGroup;
		for (int i = 0; i < nbGroup; i++) {
			GroupEntity entity = new GroupEntity();
			entity.setName(StringHelper.generate(i));
			List<TeamEntity> rest = copyTeams.stream().filter(t -> !already.contains(t)).collect(Collectors.toList());
			List<TeamEntity> randomTeams = this.randomTeams(rest, nbTeamsByGroup);
			if (!already.containsAll(randomTeams)) {
				already.addAll(randomTeams);
				List<TeamEntity> notAdded = teamEntities.stream().filter(t -> !already.contains(t)).collect(Collectors.toList());
				if (notAdded.size() != 0 && nbGroup > notAdded.size()) {
					randomTeams.add(notAdded.get(0));
				}
			}
			entity.setMatchs(this.generateMatchs(typeMatch, randomTeams, stepEntity));
			groupEntities.add(entity);
		}
		return groupEntities;
	}


	private List<TeamEntity> randomTeams(final List<TeamEntity> teamEntities, final int nbTeamsByGroup) {
		List<TeamEntity> entities = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < nbTeamsByGroup; i++) {
			int randomIndex = random.nextInt(teamEntities.size());
			TeamEntity entity = teamEntities.get(randomIndex);
			entities.add(entity);
			teamEntities.remove(randomIndex);
		}
		return entities;
	}

	private List<MatchEntity> generateMatchs(final int typeMatch, final List<TeamEntity> teamEntities, final StepEntity stepEntity) {
		List<Pair<TeamEntity, TeamEntity>> combinaisons = Generator.combination(teamEntities).simple(2).stream()
			.map(list -> new Pair<>(list.get(0), list.get(1))).collect(Collectors.toList());
		List<Pair<TeamEntity, TeamEntity>> matchs = new ArrayList<>();
		int nbMatch = combinaisons.size() * typeMatch;
		List<MatchEntity> matchEntities = new ArrayList<>(nbMatch);
		Set<TeamEntity> already = new HashSet<>();
		for (Pair<TeamEntity, TeamEntity> pair : combinaisons) {
			if (!already.contains(pair.getFirst())) {
				already.add(pair.getFirst());
				already.add(pair.getSecond());
				matchs.add(pair);
			}
		}
		List<Pair<TeamEntity, TeamEntity>> rest = combinaisons.stream().filter(p -> !matchs.contains(p)).collect(Collectors.toList());
		rest.stream().filter(p -> !already.contains(p.getSecond())).forEach(p -> {
			already.add(p.getSecond());
			matchs.add(p);
		});
		if (combinaisons.size() != matchs.size()) {
			List<Pair<TeamEntity, TeamEntity>> notAdded = combinaisons.stream().filter(p -> !matchs.contains(p)).collect(Collectors.toList());
			Collections.shuffle(notAdded);
			matchs.addAll(notAdded);
		}
		for (Pair<TeamEntity, TeamEntity> pair : matchs) {
			matchEntities.add(MatchEntity.builder().home(pair.getFirst()).away(pair.getSecond()).step(stepEntity).build());
		}
		// two legs
		if (typeMatch == 2) {
			matchs.stream().map(p -> new Pair<>(p.getSecond(), p.getFirst()))
				.forEach(p -> matchEntities.add(MatchEntity.builder().home(p.getFirst()).away(p.getSecond()).step(stepEntity).build()));
		}
		return matchEntities;
	}

	private void calculateCurrentStep(final TournamentResponse model, final Set<StepResponse> stepResponses) {
		if (Objects.nonNull(model)) {
//			boolean hasConsoling = Objects.nonNull(model.getConsoling());
//			boolean hasFinishMain = Objects.nonNull(model.getMain());
//			boolean isFinishGroups = CollectionUtils.isNotEmpty(model.getGroups());
			// todo implement currentStep tournament
			if (CollectionUtils.isNotEmpty(model.getGroups())) {
				// check allMatch is completed
				boolean isFinishGroups = model.getGroups().stream()
					.filter(Objects::nonNull)
					.map(GroupResponse::getMatchs)
					.filter(CollectionUtils::isNotEmpty)
					.allMatch(matchs -> matchs.stream().allMatch(m -> Objects.nonNull(m.getHomeGoals()) && Objects.nonNull(m.getAwayGoals())));
				if (isFinishGroups) {
					model.setStep(stepResponses.stream().filter(s -> s.getNameEn().equals("Final stage")).findFirst()
						.orElse(StepMapper.mapEntityToModel(this.stepService.findByName("Final stage"))));
//					model.setStep(StepMapper.mapEntityToModel(this.stepService.findByName("Final stage")));
				} else {
					if (Objects.isNull(model.getMain()) || Objects.isNull(model.getConsoling())) {
						model.setStep(stepResponses.stream().filter(s -> s.getNameEn().equals("Qualifying stage")).findFirst()
							.orElse(StepMapper.mapEntityToModel(this.stepService.findByName("Qualifying stage"))));
//						model.setStep(StepMapper.mapEntityToModel(this.stepService.findByName("Qualifying stage")));
					}
				}
			} else {
				model.setStep(stepResponses.stream().filter(s -> s.getNameEn().equals("Final stage")).findFirst()
					.orElse(StepMapper.mapEntityToModel(this.stepService.findByName("Final stage"))));
			}
		}
	}
}
