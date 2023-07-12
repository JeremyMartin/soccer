package fr.ewaux.backend.mapper.player;

import fr.ewaux.backend.entity.player.PlayerEntity;
import fr.ewaux.backend.model.request.player.PlayerRequest;
import fr.ewaux.backend.model.response.player.PlayerResponse;
import java.util.Objects;

public final class PlayerMapper {

	public static PlayerResponse mapEntityToModel(final PlayerEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		PlayerResponse model = new PlayerResponse();
		model.setId(entity.getId());
		model.setName(entity.getName());
		return model;
	}

	public static PlayerEntity mapModelToEntity(final PlayerRequest request) {
		if (Objects.isNull(request)) {
			return null;
		}
		PlayerEntity entity = new PlayerEntity();
		entity.setId(request.getId());
		entity.setName(request.getName());
		return entity;
	}
}
