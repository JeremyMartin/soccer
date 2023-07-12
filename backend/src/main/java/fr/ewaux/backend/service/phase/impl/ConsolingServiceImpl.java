package fr.ewaux.backend.service.phase.impl;

import fr.ewaux.backend.entity.phase.ConsolingEntity;
import fr.ewaux.backend.entity.phase.GroupEntity;
import fr.ewaux.backend.repository.phase.ConsolingRepository;
import fr.ewaux.backend.repository.phase.GroupRepository;
import fr.ewaux.backend.service.phase.ConsolingService;
import fr.ewaux.backend.service.phase.GroupService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "consolingService")
public class ConsolingServiceImpl implements ConsolingService {

	ConsolingRepository consolingRepository;

	public ConsolingServiceImpl(final ConsolingRepository consolingRepository) {
		super();
		this.consolingRepository = consolingRepository;
	}

	@Transactional
	@Override
	public ConsolingEntity add(final ConsolingEntity entity) {
		return this.consolingRepository.save(entity);
	}

}
