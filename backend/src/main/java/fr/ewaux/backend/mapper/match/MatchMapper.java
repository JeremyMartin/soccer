package fr.ewaux.backend.mapper.match;

import fr.ewaux.backend.entity.match.MatchEntity;
import fr.ewaux.backend.mapper.step.StepMapper;
import fr.ewaux.backend.mapper.team.TeamMapper;
import fr.ewaux.backend.model.request.match.MatchRequest;
import fr.ewaux.backend.model.response.match.MatchResponse;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class MatchMapper {

	public static MatchResponse mapEntityToModel(final MatchEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		MatchResponse model = new MatchResponse();
		model.setId(entity.getId());
		model.setHome(TeamMapper.mapEntityToModel(entity.getHome()));
		model.setAway(TeamMapper.mapEntityToModel(entity.getAway()));
		model.setStep(StepMapper.mapEntityToModel(entity.getStep()));
		model.setHomeGoals(entity.getHomeGoals());
		model.setAwayGoals(entity.getAwayGoals());
		model.setPenalty(entity.isPenalty());
		model.setHomePenalty(entity.getHomePenalty());
		model.setAwayPenalty(entity.getAwayPenalty());
		model.setHomeYellowCard(entity.getHomeYellowCard());
		model.setHomeRedCard(entity.getHomeRedCard());
		model.setAwayYellowCard(entity.getAwayYellowCard());
		model.setAwayRedCard(entity.getAwayRedCard());
		model.setPlayed(Objects.nonNull(entity.getHomeGoals()) && Objects.nonNull(entity.getAwayGoals()));
		return model;
	}

	public static Set<MatchResponse> mapEntitiesToModels(final List<MatchEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream().map(MatchMapper::mapEntityToModel).collect(Collectors.toSet());
	}

	public static MatchEntity mapModelToEntity(final MatchRequest request) {
		if (Objects.isNull(request)) {
			return null;
		}
		MatchEntity entity = new MatchEntity();
		entity.setId(request.getId());
		entity.setHome(TeamMapper.mapModelToEntity(request.getHome()));
		entity.setAway(TeamMapper.mapModelToEntity(request.getAway()));
		entity.setStep(StepMapper.mapModelToEntity(request.getStep()));
		entity.setHomeGoals(request.getHomeGoals());
		entity.setAwayGoals(request.getAwayGoals());
		entity.setPenalty(request.isPenalty());
		entity.setHomePenalty(request.getHomePenalty());
		entity.setAwayPenalty(request.getAwayPenalty());
		return entity;
	}

	public static List<MatchEntity> mapModelsToEntities(final Set<MatchRequest> requests) {
		if (CollectionUtils.isEmpty(requests)) {
			return null;
		}
		return requests.stream().map(MatchMapper::mapModelToEntity).collect(Collectors.toList());
	}
}
