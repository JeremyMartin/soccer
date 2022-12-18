package fr.ewaux.backend.repository.club;

import fr.ewaux.backend.entity.club.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity,Long> {

}
