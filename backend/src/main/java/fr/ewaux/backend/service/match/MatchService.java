package fr.ewaux.backend.service.match;

import fr.ewaux.backend.entity.match.MatchEntity;
import fr.ewaux.backend.model.request.match.MatchRequest;
import fr.ewaux.backend.model.response.match.MatchResponse;
import java.util.List;

public interface MatchService {

	MatchEntity add(MatchEntity entity);

	List<MatchEntity> addAll(List<MatchEntity> entities);

	MatchResponse update(MatchRequest request);

	void deleteAll(List<MatchEntity> entities);

}
