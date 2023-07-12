package fr.ewaux.backend.entity.phase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.match.MatchEntity;
import fr.ewaux.commons.entity.AbstractLongNamingEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@ToString(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "groups")
@Table(name = "groups")
public class GroupEntity extends AbstractLongNamingEntity {

	@OneToMany
	@JoinTable(name = "groups_matchs", joinColumns = {
		@JoinColumn(name = "groups_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "fk_groups_matchs_groups_id"), inverseJoinColumns = {
		@JoinColumn(name = "match_id", referencedColumnName = "id")}, inverseForeignKey = @ForeignKey(name = "fk_groups_matchs_match_id"), uniqueConstraints = @UniqueConstraint(name = "uk_groups_matchs_match_id", columnNames = {
		"match_id"}))
	List<MatchEntity> matchs;
}
