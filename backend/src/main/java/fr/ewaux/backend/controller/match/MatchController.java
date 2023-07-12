package fr.ewaux.backend.controller.match;

import fr.ewaux.backend.controller.AbstractSoccerController;
import fr.ewaux.backend.model.request.match.MatchRequest;
import fr.ewaux.backend.model.response.match.MatchResponse;
import fr.ewaux.backend.service.match.MatchService;
import fr.ewaux.commons.utilities.utils.MessageHelper;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "match")
public class MatchController extends AbstractSoccerController {

	MatchService matchService;

	public MatchController(final MatchService matchService) {
		super();
		this.matchService = matchService;
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody final MatchRequest request) {
		MatchResponse matchResponse = this.matchService.update(request);
		if (Objects.isNull(matchResponse)) {
			return ResponseEntity.badRequest()
				.body(this.buildExceptionResponse(MessageHelper.ERROR_INVALID, MessageHelper.ERROR_ARGUMENT_NOT_VALID));
		}
		return new ResponseEntity<>(matchResponse, HttpStatus.ACCEPTED);
	}
}
