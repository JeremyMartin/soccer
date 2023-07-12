package fr.ewaux.backend.service.step.impl;

import fr.ewaux.backend.entity.step.StepEntity;
import fr.ewaux.backend.mapper.step.StepMapper;
import fr.ewaux.backend.model.response.step.StepResponse;
import fr.ewaux.backend.repository.step.StepRepository;
import fr.ewaux.backend.service.step.StepService;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "stepService")
public class StepServiceImpl implements StepService {

	StepRepository stepRepository;

	public StepServiceImpl(final StepRepository stepRepository) {
		super();
		this.stepRepository = stepRepository;
	}

	@Transactional
	@Override
	public List<StepEntity> listAll() {
		return this.stepRepository.findAll(Sort.by("id"));
	}

	@Override
	public StepEntity findByName(final String name) {
		return this.stepRepository.findByName(name);
	}

}
