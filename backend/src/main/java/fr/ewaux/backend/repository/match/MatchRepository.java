package fr.ewaux.backend.repository.match;

import fr.ewaux.backend.entity.match.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, MatchEntity> {

	MatchEntity findById(Long id);
}
