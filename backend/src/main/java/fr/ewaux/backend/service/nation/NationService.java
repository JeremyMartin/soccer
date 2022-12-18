package fr.ewaux.backend.service.nation;

import fr.ewaux.backend.entity.nation.NationEntity;
import java.util.Set;

public interface NationService {

	Set<NationEntity> list();
}
