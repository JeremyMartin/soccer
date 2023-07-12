package fr.ewaux.backend.mapper.team;

import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.backend.mapper.club.ClubMapper;
import fr.ewaux.backend.mapper.nation.NationMapper;
import fr.ewaux.backend.mapper.player.PlayerMapper;
import fr.ewaux.backend.model.request.team.TeamRequest;
import fr.ewaux.backend.model.response.team.TeamResponse;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class TeamMapper {

	public static TeamResponse mapEntityToModel(final TeamEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		TeamResponse model = new TeamResponse();
		model.setId(entity.getId());
		model.setClub(ClubMapper.mapEntityToModelWithNation(entity.getClub()));
		model.setNation(NationMapper.mapEntityToModel(entity.getNation()));
		model.setPlayer(PlayerMapper.mapEntityToModel(entity.getPlayer()));
		return model;
	}

	public static TeamEntity mapModelToEntity(final TeamRequest request) {
		if (Objects.isNull(request)) {
			return null;
		}
		TeamEntity entity = new TeamEntity();
		entity.setPlayer(PlayerMapper.mapModelToEntity(request.getPlayer()));
		entity.setClub(ClubMapper.mapModelToEntity(request.getClub()));
		entity.setNation(NationMapper.mapModelToEntity(request.getNation()));
		return entity;
	}

	public static Set<TeamResponse> mapEntitiesToModels(final List<TeamEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream().map(TeamMapper::mapEntityToModel).collect(Collectors.toSet());
	}
}
