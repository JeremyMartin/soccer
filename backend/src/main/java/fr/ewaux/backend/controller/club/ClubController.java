package fr.ewaux.backend.controller.club;

import fr.ewaux.backend.service.club.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "club")
public class ClubController {

	ClubService clubService;

	public ClubController(final ClubService clubService) {
		super();
		this.clubService = clubService;
	}

	@GetMapping
	@RequestMapping(path = "list")
	public ResponseEntity<?> list() {
		return ResponseEntity.ok(this.clubService.list());
	}
}
