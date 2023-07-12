package fr.ewaux.backend.repository.tournament;

import fr.ewaux.backend.entity.tournament.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {

}
