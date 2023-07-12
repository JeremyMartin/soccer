package fr.ewaux.backend.controller.step;

import fr.ewaux.backend.controller.AbstractSoccerController;
import fr.ewaux.backend.mapper.step.StepMapper;
import fr.ewaux.backend.service.step.StepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "step")
public class StepController extends AbstractSoccerController {

	StepService stepService;

	public StepController(final StepService stepService) {
		super();
		this.stepService = stepService;
	}

	@GetMapping
	public ResponseEntity<?> list() {
		return ResponseEntity.ok(StepMapper.mapEntitiesToModels(this.stepService.listAll()));
	}

}
