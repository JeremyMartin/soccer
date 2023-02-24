package fr.ewaux.backend.service.nation;

import fr.ewaux.backend.model.soccer.nation.Nation;
import java.util.Set;

public interface NationService {

	Set<Nation> list(boolean withClubs);
}
