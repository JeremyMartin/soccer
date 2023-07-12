package fr.ewaux.backend.controller.tournament;

import fr.ewaux.backend.controller.AbstractSoccerController;
import fr.ewaux.backend.model.request.tournament.TournamentRequest;
import fr.ewaux.backend.model.response.tournament.TournamentResponse;
import fr.ewaux.backend.service.tournament.TournamentService;
import fr.ewaux.commons.utilities.utils.MessageHelper;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "tournament")
public class TournamentController extends AbstractSoccerController {

	TournamentService tournamentService;

	public TournamentController(final TournamentService tournamentService) {
		super();
		this.tournamentService = tournamentService;
	}

	@GetMapping(path = "list")
	public ResponseEntity<?> list() {
		return ResponseEntity.ok(this.tournamentService.findAll());
	}

	@PostMapping(path = "add")
	public ResponseEntity<?> add(@RequestBody final TournamentRequest request) {
		TournamentResponse tournamentResponse = this.tournamentService.add(request);
		if (Objects.isNull(tournamentResponse)) {
			return ResponseEntity.badRequest()
				.body(this.buildExceptionResponse(MessageHelper.ERROR_INVALID, MessageHelper.ERROR_ARGUMENT_NOT_VALID));
		}
		return new ResponseEntity<>(tournamentResponse, HttpStatus.CREATED);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") final Long id) {
		TournamentResponse tournamentResponse = this.tournamentService.findById(id);
		if (Objects.isNull(tournamentResponse)) {
			return new ResponseEntity<>(
				this.buildExceptionResponse(MessageHelper.ERROR_NOT_FOUND + ".tournament", MessageHelper.ERROR_ENTITY_NOT_FOUND),
				HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tournamentResponse, HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name = "id") final Long id) {
		this.tournamentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
