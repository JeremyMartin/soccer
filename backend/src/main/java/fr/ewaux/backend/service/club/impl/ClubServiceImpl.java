package fr.ewaux.backend.service.club.impl;

import fr.ewaux.backend.entity.club.ClubEntity;
import fr.ewaux.backend.repository.club.ClubRepository;
import fr.ewaux.backend.service.club.ClubService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service(value = "clubService")
public class ClubServiceImpl implements ClubService {

	ClubRepository clubRepository;

	public ClubServiceImpl(final ClubRepository clubRepository) {
		super();
		this.clubRepository = clubRepository;
	}

	@Override
	public Set<ClubEntity> list() {
		return new HashSet<>(this.clubRepository.findAll());
	}
}
