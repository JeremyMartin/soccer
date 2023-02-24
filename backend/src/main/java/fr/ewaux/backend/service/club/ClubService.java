package fr.ewaux.backend.service.club;

import fr.ewaux.backend.model.soccer.club.Club;
import java.util.Set;

public interface ClubService {

	Set<Club> list(boolean withNation);
}
