package fr.ewaux.backend.controller.club;

import fr.ewaux.backend.service.club.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "club")
public class ClubController {

	ClubService clubService;

	public ClubController(final ClubService clubService) {
		super();
		this.clubService = clubService;
	}

	@GetMapping(path = "list")
	public ResponseEntity<?> list(@RequestParam(name = "full",required = false) final boolean withNation) {
		return ResponseEntity.ok(this.clubService.list(withNation));
	}
}
