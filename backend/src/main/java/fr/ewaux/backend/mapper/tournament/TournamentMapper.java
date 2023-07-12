package fr.ewaux.backend.mapper.tournament;

import fr.ewaux.backend.entity.step.StepEntity;
import fr.ewaux.backend.entity.tournament.TournamentEntity;
import fr.ewaux.backend.mapper.phase.GroupMapper;
import fr.ewaux.backend.mapper.team.TeamMapper;
import fr.ewaux.backend.model.request.tournament.TournamentRequest;
import fr.ewaux.backend.model.response.match.MatchResponse;
import fr.ewaux.backend.model.response.phase.GroupResponse;
import fr.ewaux.backend.model.response.step.StepResponse;
import fr.ewaux.backend.model.response.team.TeamResponse;
import fr.ewaux.backend.model.response.tournament.TournamentResponse;
import fr.ewaux.commons.utilities.model.AbstractLongNamingModel;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class TournamentMapper {

	public static TournamentEntity mapModelToEntity(final TournamentRequest request) {
		if (Objects.isNull(request)) {
			return null;
		}
		TournamentEntity entity = new TournamentEntity();
		entity.setDate(OffsetDateTime.now());
		entity.setName(request.getName());
		entity.setTeams(request.getTeams().stream().map(TeamMapper::mapModelToEntity).collect(Collectors.toList()));
		entity.setMatchType(request.getMatchType());
		return entity;
	}

	public static TournamentResponse mapEntityToModel(final TournamentEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		TournamentResponse model = new TournamentResponse();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setDate(entity.getDate());
		model.setTeams(TeamMapper.mapEntitiesToModels(entity.getTeams()));
		Set<GroupResponse> groups = GroupMapper.mapEntitiesToModels(entity.getGroups());
		model.setGroups(groups);
		model.setMatchType(entity.getMatchType());
		return model;
	}

	public static Set<TournamentResponse> mapEntitiesToSimpleSetModels(final List<TournamentEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return new HashSet<>();
		}
		return entities.stream().filter(Objects::nonNull).map(TournamentMapper::mapEntityToModel).collect(Collectors.toCollection(
			LinkedHashSet::new));
	}

}
