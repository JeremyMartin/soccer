package fr.ewaux.backend.service.player;

import fr.ewaux.backend.entity.player.PlayerEntity;

public interface PlayerService {

	PlayerEntity add(PlayerEntity entity);

	void delete(PlayerEntity entity);
}
