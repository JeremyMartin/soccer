package fr.ewaux.backend.repository.phase;

import fr.ewaux.backend.entity.phase.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

}
