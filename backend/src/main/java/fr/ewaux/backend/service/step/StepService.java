package fr.ewaux.backend.service.step;

import fr.ewaux.backend.entity.step.StepEntity;
import java.util.List;

public interface StepService {

	List<StepEntity> listAll();

	StepEntity findByName(String name);

}
