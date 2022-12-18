package fr.ewaux.backend.repository.nation;

import fr.ewaux.backend.entity.nation.NationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationRepository extends JpaRepository<NationEntity, Long> {

}
