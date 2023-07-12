package fr.ewaux.backend.service.club;

import fr.ewaux.backend.model.response.club.ClubResponse;
import java.util.Set;

public interface ClubService {

	Set<ClubResponse> list(boolean withNation);

}
