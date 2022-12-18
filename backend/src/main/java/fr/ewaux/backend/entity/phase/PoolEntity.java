package fr.ewaux.backend.entity.phase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.match.MatchEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.commons.entity.AbstractLongNamingEntity;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "pool")
@Table(name = "pool")
public class PoolEntity extends AbstractLongNamingEntity {

	@OneToMany
	Set<TeamEntity> teams;
	@OneToMany
	Set<MatchEntity> matchs;
}
