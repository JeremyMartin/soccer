package fr.ewaux.backend.service.club;

import fr.ewaux.backend.entity.club.ClubEntity;
import java.util.Set;

public interface ClubService {

	Set<ClubEntity> list();
}
