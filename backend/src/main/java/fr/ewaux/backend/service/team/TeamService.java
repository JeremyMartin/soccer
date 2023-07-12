package fr.ewaux.backend.service.team;

import fr.ewaux.backend.entity.team.TeamEntity;
import java.util.List;

public interface TeamService {

	TeamEntity add(TeamEntity entity);

	List<TeamEntity> addAll(List<TeamEntity> entities);

	void deleteAll(List<TeamEntity> entities);
}
