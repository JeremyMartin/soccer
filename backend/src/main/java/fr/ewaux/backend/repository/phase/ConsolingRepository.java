package fr.ewaux.backend.repository.phase;

import fr.ewaux.backend.entity.phase.ConsolingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsolingRepository extends JpaRepository<ConsolingEntity, Long> {

}
