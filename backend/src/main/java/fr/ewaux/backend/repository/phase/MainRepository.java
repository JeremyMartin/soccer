package fr.ewaux.backend.repository.phase;

import fr.ewaux.backend.entity.phase.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<MainEntity, Long> {

}
