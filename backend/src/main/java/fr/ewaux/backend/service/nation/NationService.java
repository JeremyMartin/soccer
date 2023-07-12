package fr.ewaux.backend.service.nation;

import fr.ewaux.backend.model.response.nation.NationResponse;
import java.util.Set;

public interface NationService {

	Set<NationResponse> list(boolean withClubs);

}
