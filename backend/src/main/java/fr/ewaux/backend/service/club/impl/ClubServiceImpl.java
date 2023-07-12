package fr.ewaux.backend.service.club.impl;

import fr.ewaux.backend.mapper.club.ClubMapper;
import fr.ewaux.backend.model.response.club.ClubResponse;
import fr.ewaux.backend.repository.club.ClubRepository;
import fr.ewaux.backend.service.club.ClubService;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "clubService")
public class ClubServiceImpl implements ClubService {

	ClubRepository clubRepository;

	public ClubServiceImpl(final ClubRepository clubRepository) {
		super();
		this.clubRepository = clubRepository;
	}

	@Override
	@Transactional
	public Set<ClubResponse> list(final boolean withNation) {
		return ClubMapper.mapEntitiesToModels(this.clubRepository.findAll(), withNation);
	}

}
