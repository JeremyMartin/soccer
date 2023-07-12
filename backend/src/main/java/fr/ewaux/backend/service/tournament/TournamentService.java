package fr.ewaux.backend.service.tournament;

import fr.ewaux.backend.model.request.tournament.TournamentRequest;
import fr.ewaux.backend.model.response.tournament.TournamentResponse;
import java.util.Set;

public interface TournamentService {

	Set<TournamentResponse> findAll();

	TournamentResponse add(TournamentRequest request);

	TournamentResponse findById(Long id);

	void deleteById(Long id);
}
