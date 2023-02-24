package fr.ewaux.backend.service.nation.impl;

import fr.ewaux.backend.mapper.nation.NationMapper;
import fr.ewaux.backend.model.soccer.nation.Nation;
import fr.ewaux.backend.repository.nation.NationRepository;
import fr.ewaux.backend.service.nation.NationService;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "nationService")
public class NationServiceImpl implements NationService {

	NationRepository nationRepository;

	public NationServiceImpl(final NationRepository nationRepository) {
		super();
		this.nationRepository = nationRepository;
	}

	@Override
	@Transactional
	public Set<Nation> list(final boolean withClubs) {
		return NationMapper.mapEntitiesToSetModels(this.nationRepository.findAll(), withClubs);
	}
}
