package fr.ewaux.backend.mapper.phase;

import fr.ewaux.backend.entity.phase.GroupEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.backend.mapper.match.MatchMapper;
import fr.ewaux.backend.model.response.match.MatchResponse;
import fr.ewaux.backend.model.response.phase.GroupResponse;
import fr.ewaux.backend.model.response.ranking.RankingResponse;
import fr.ewaux.backend.model.response.team.TeamResponse;
import fr.ewaux.commons.utilities.model.AbstractLongNamingModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class GroupMapper {

	public static GroupResponse mapEntityToModel(final GroupEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		GroupResponse model = new GroupResponse();
		model.setId(entity.getId());
		model.setName(entity.getName());
		Set<MatchResponse> matchs = MatchMapper.mapEntitiesToModels(entity.getMatchs());
		model.setMatchs(matchs);
		if (CollectionUtils.isNotEmpty(matchs)) {
			Set<TeamResponse> teams = new HashSet<>();
			matchs.forEach(m -> {
				teams.add(m.getHome());
				teams.add(m.getAway());
			});
			model.setTeams(teams);
		}
		calculateRanking(model);
		return model;
	}

	public static Set<GroupResponse> mapEntitiesToModels(final List<GroupEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream().map(GroupMapper::mapEntityToModel)
			.collect(Collectors.toSet());
	}

	private static void calculateRanking(final GroupResponse model) {
		Set<TeamResponse> teams = model.getTeams();
		Set<MatchResponse> matchs = model.getMatchs();
		List<RankingResponse> ranks = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(teams)) {
			teams.forEach(team -> {
				RankingResponse rank = RankingResponse.builder().team(team).build();
				if (CollectionUtils.isNotEmpty(matchs)) {
					Set<MatchResponse> playedMatchs = matchs.stream()
						.filter(m -> (m.getHome().equals(team) || m.getAway().equals(team)) && m.isPlayed())
						.collect(Collectors.toSet());
					rank.setPlay(playedMatchs.size());
					if (CollectionUtils.isNotEmpty(playedMatchs)) {
						AtomicInteger scored = new AtomicInteger(0);
						AtomicInteger conceded = new AtomicInteger(0);
						AtomicInteger win = new AtomicInteger(0);
						AtomicInteger draw = new AtomicInteger(0);
						AtomicInteger lose = new AtomicInteger(0);
						AtomicInteger yellow = new AtomicInteger(0);
						AtomicInteger red = new AtomicInteger(0);
						playedMatchs.forEach(m -> {
							if (m.getHome().equals(team)) {
								scored.addAndGet(m.getHomeGoals());
								conceded.addAndGet(m.getAwayGoals());
								if (Objects.nonNull(m.getHomeYellowCard())) {
									yellow.addAndGet(m.getHomeYellowCard());
								}
								if (Objects.nonNull(m.getHomeRedCard())) {
									red.addAndGet(m.getHomeRedCard());
								}
								if (m.getHomeGoals() > m.getAwayGoals()) {
									win.getAndIncrement();
								} else if (m.getHomeGoals() < m.getAwayGoals()) {
									lose.getAndIncrement();
								} else {
									if (m.isPenalty()) {
										if (m.getHomePenalty() > m.getAwayPenalty()) {
											win.getAndIncrement();
										} else {
											lose.getAndIncrement();
										}
									} else {
										draw.getAndIncrement();
									}
								}
							} else {
								scored.addAndGet(m.getAwayGoals());
								conceded.addAndGet(m.getHomeGoals());
								if (Objects.nonNull(m.getAwayYellowCard())) {
									yellow.addAndGet(m.getAwayYellowCard());
								}
								if (Objects.nonNull(m.getAwayRedCard())) {
									red.addAndGet(m.getAwayRedCard());
								}
								if (m.getAwayGoals() > m.getHomeGoals()) {
									win.getAndIncrement();
								} else if (m.getAwayGoals() < m.getHomeGoals()) {
									lose.getAndIncrement();
								} else {
									if (m.isPenalty()) {
										if (m.getAwayPenalty() > m.getHomePenalty()) {
											win.getAndIncrement();
										} else {
											lose.getAndIncrement();
										}
									} else {
										draw.getAndIncrement();
									}
								}
							}
						});
						rank.setWin(win.get());
						rank.setLose(lose.get());
						rank.setDraw(draw.get());
						rank.setDiff(scored.get() - conceded.get());
						rank.setPoints((win.get() * 3) + (draw.get()));
						rank.setYellowCard(yellow.get());
						rank.setRedCard(red.get());
					}
				}
				ranks.add(rank);
			});
		}

		ranks.sort((a, b) -> {
			if (a.getPoints() > b.getPoints()) {
				return -1;
			} else if (a.getPoints() < b.getPoints()) {
				return 1;
			} else {
				if (a.getDiff() > b.getDiff()) {
					return -1;
				} else if (a.getDiff() < b.getDiff()) {
					return 1;
				} else if (a.getRedCard() > b.getRedCard()) {
					return 1;
				} else if (a.getRedCard() < b.getRedCard()) {
					return -1;
				} else if (a.getYellowCard() > b.getYellowCard()) {
					return 1;
				} else if (a.getYellowCard() < b.getYellowCard()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		AtomicInteger rank = new AtomicInteger(1);
		ranks.forEach(r -> {
			r.setRank(rank.getAndIncrement());
		});

		model.setRankings(new HashSet<>(ranks));
	}

}
