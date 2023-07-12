package fr.ewaux.backend.service.match.impl;

import fr.ewaux.backend.entity.match.MatchEntity;
import fr.ewaux.backend.mapper.match.MatchMapper;
import fr.ewaux.backend.model.request.match.MatchRequest;
import fr.ewaux.backend.model.response.match.MatchResponse;
import fr.ewaux.backend.repository.match.MatchRepository;
import fr.ewaux.backend.service.match.MatchService;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "matchService")
public class MatchServiceImpl implements MatchService {

	MatchRepository matchRepository;

	public MatchServiceImpl(final MatchRepository matchRepository) {
		super();
		this.matchRepository = matchRepository;
	}

	@Transactional
	@Override
	public MatchEntity add(final MatchEntity entity) {
		return this.matchRepository.save(entity);
	}

	@Transactional
	@Override
	public List<MatchEntity> addAll(final List<MatchEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return this.matchRepository.saveAll(entities);
	}

	@Transactional
	@Override
	public MatchResponse update(final MatchRequest request) {
		MatchEntity entity = this.matchRepository.findById(request.getId());
		if (Objects.isNull(entity)) {
			return null;
		}
		entity.setHomeGoals(request.getHomeGoals());
		entity.setAwayGoals(request.getAwayGoals());
		entity.setPenalty(request.isPenalty());
		entity.setHomePenalty(request.getHomePenalty());
		entity.setAwayPenalty(request.getAwayPenalty());
		entity.setHomeYellowCard(request.getHomeYellowCard());
		entity.setHomeRedCard(request.getHomeRedCard());
		entity.setAwayYellowCard(request.getAwayYellowCard());
		entity.setAwayRedCard(request.getAwayRedCard());
		return MatchMapper.mapEntityToModel(this.matchRepository.save(entity));
	}

	@Transactional
	@Override
	public void deleteAll(final List<MatchEntity> entities) {
		if (CollectionUtils.isNotEmpty(entities)) {
			this.matchRepository.deleteAll(entities);
		}
	}
}
