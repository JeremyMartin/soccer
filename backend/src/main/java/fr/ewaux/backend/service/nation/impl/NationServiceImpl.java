package fr.ewaux.backend.service.nation.impl;

import fr.ewaux.backend.entity.nation.NationEntity;
import fr.ewaux.backend.repository.nation.NationRepository;
import fr.ewaux.backend.service.nation.NationService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service(value = "nationService")
public class NationServiceImpl implements NationService {

	NationRepository nationRepository;

	public NationServiceImpl(final NationRepository nationRepository) {
		super();
		this.nationRepository = nationRepository;
	}

	@Override
	public Set<NationEntity> list() {
		return new HashSet<>(this.nationRepository.findAll());
	}
}
