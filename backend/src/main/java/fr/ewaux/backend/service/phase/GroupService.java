package fr.ewaux.backend.service.phase;

import fr.ewaux.backend.entity.phase.GroupEntity;
import java.util.List;

public interface GroupService {

	GroupEntity add(GroupEntity entity);

	List<GroupEntity> addAll(List<GroupEntity> entities);

	void deleteAll(List<GroupEntity> entities);

}
