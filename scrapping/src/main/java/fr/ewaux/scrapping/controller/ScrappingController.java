package fr.ewaux.scrapping.controller;

import fr.ewaux.commons.utilities.model.response.ExpectedResponse;
import fr.ewaux.commons.utilities.model.response.SuccessResponse;
import fr.ewaux.scrapping.model.external.club.Club;
import fr.ewaux.scrapping.model.external.club.ClubsResponse;
import fr.ewaux.scrapping.model.external.league.LeaguesResponse;
import fr.ewaux.scrapping.model.external.nation.Nation;
import fr.ewaux.scrapping.model.external.nation.NationsResponse;
import fr.ewaux.scrapping.service.web.ExternalApiService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "scrapping")
public class ScrappingController {

	String endpoint;
	ExternalApiService externalApiService;
	String nations = "nations";
	String clubs = "clubs";
	String leagues = "leagues";

	public ScrappingController(@Value("${endpoint.api-url}") final String endpoint,
		final ExternalApiService externalApiService) {
		super();
		this.endpoint = endpoint;
		this.externalApiService = externalApiService;

	}

	@GetMapping
	public ResponseEntity<?> scrapping() {
		Set<ExpectedResponse> errors = new HashSet<>();
		Set<ExpectedResponse> errorsNation = this.externalApiService.callEndpoint(
			this.endpoint + this.nations, NationsResponse.class);
		if (errorsNation.size() > 0) {
			errors.addAll(errorsNation);
		}
    Set<ExpectedResponse> errorsClubs = this.externalApiService.callEndpoint(
        this.endpoint + this.clubs, ClubsResponse.class);
    if (errorsClubs.size() > 0) {
      errors.addAll(errorsClubs);
    }
    Set<ExpectedResponse> errorsLeagues = this.externalApiService.callEndpoint(
        this.endpoint + this.leagues, LeaguesResponse.class);
    if (errorsLeagues.size() > 0) {
      errors.addAll(errorsLeagues);
    }
		return ResponseEntity.ok(SuccessResponse.builder().message("ok").errors(errors).timestamp(LocalDateTime.now()).build());
	}

	@GetMapping(value = "report")
	public ResponseEntity<?> report() {
		this.externalApiService.generateReport(Nation.class, Club.class);
		return ResponseEntity.ok(SuccessResponse.builder().message("ok").timestamp(LocalDateTime.now()).build());
	}

}
