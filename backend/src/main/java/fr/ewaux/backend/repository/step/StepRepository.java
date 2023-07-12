package fr.ewaux.backend.repository.step;

import fr.ewaux.backend.entity.step.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends JpaRepository<StepEntity, Long> {

	@Query("SELECT s from step s where s.nameEn like %:name% or s.nameFr like %:name%")
	StepEntity findByName(@Param(value = "name") String name);
}
