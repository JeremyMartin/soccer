package fr.ewaux.backend.service.phase.impl;

import fr.ewaux.backend.entity.phase.MainEntity;
import fr.ewaux.backend.repository.phase.MainRepository;
import fr.ewaux.backend.service.phase.MainService;
import org.springframework.stereotype.Service;

@Service(value = "mainService")
public class MainServiceImpl implements MainService {

	MainRepository mainRepository;

	public MainServiceImpl(final MainRepository mainRepository) {
		super();
		this.mainRepository = mainRepository;
	}

	@Override
	public MainEntity add(final MainEntity entity) {
		return this.mainRepository.save(entity);
	}

}
