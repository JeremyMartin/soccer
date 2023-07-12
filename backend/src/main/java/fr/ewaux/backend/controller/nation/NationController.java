package fr.ewaux.backend.controller.nation;

import fr.ewaux.backend.service.nation.NationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "nation")
public class NationController {

	NationService nationService;

	public NationController(final NationService nationService) {
		super();
		this.nationService = nationService;
	}

	@GetMapping(path = "list")
	public ResponseEntity<?> list(@RequestParam(name = "full", required = false) final boolean withClub) {
		return ResponseEntity.ok(this.nationService.list(withClub));
	}
}
