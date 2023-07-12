package fr.ewaux.backend.service.player.impl;

import fr.ewaux.backend.entity.player.PlayerEntity;
import fr.ewaux.backend.repository.player.PlayerRepository;
import fr.ewaux.backend.service.player.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "playerService")
public class PlayerServiceImpl implements PlayerService {

	PlayerRepository playerRepository;

	public PlayerServiceImpl(final PlayerRepository playerRepository) {
		super();
		this.playerRepository = playerRepository;
	}

	@Transactional
	@Override
	public PlayerEntity add(final PlayerEntity entity) {
		return this.playerRepository.save(entity);
	}

	@Transactional
	@Override
	public void delete(final PlayerEntity entity) {
		this.playerRepository.delete(entity);
	}
}
