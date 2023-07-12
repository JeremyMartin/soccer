package fr.ewaux.backend.service.team.impl;

import fr.ewaux.backend.entity.player.PlayerEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.backend.repository.team.TeamRepository;
import fr.ewaux.backend.service.player.PlayerService;
import fr.ewaux.backend.service.team.TeamService;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "teamService")
public class TeamServiceImpl implements TeamService {

	TeamRepository teamRepository;
	PlayerService playerService;

	public TeamServiceImpl(final TeamRepository teamRepository, final PlayerService playerService) {
		super();
		this.teamRepository = teamRepository;
		this.playerService = playerService;
	}

	@Transactional
	@Override
	public TeamEntity add(final TeamEntity entity) {
		return this.teamRepository.save(entity);
	}

	@Transactional
	@Override
	public List<TeamEntity> addAll(final List<TeamEntity> entities) {
		if (Objects.isNull(entities)) {
			return null;
		}
		entities.forEach(entity -> {
			if (Objects.nonNull(entity.getPlayer())) {
				entity.setPlayer(this.playerService.add(entity.getPlayer()));
			}
		});
		return this.teamRepository.saveAll(entities);
	}

	@Transactional
	@Override
	public void deleteAll(final List<TeamEntity> entities) {
		if (CollectionUtils.isNotEmpty(entities)) {
			this.teamRepository.deleteAll(entities);
			entities.forEach(e->{
				this.playerService.delete(e.getPlayer());
			});
		}
	}
}
