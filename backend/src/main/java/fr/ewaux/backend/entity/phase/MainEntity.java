package fr.ewaux.backend.entity.phase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.match.MatchEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.commons.entity.AbstractLongEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "main")
@Table(name = "main")
public class MainEntity extends AbstractLongEntity {

	@OneToMany
	@JoinTable(name = "mains_teams", joinColumns = {
		@JoinColumn(name = "main_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "fk_mains_teams_main_id"), inverseJoinColumns = {
		@JoinColumn(name = "team_id", referencedColumnName = "id")}, inverseForeignKey = @ForeignKey(name = "fk_mains_teams_team_id"), uniqueConstraints = @UniqueConstraint(name = "uk_mains_teams_teams_id", columnNames = {
		"team_id"}))
	List<TeamEntity> teams;

	@OneToMany
	@JoinTable(name = "mains_matchs", joinColumns = {
		@JoinColumn(name = "main_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "fk_mains_matchs_main_id"), inverseJoinColumns = {
		@JoinColumn(name = "match_id", referencedColumnName = "id")}, inverseForeignKey = @ForeignKey(name = "fk_mains_matchs_match_id"), uniqueConstraints = @UniqueConstraint(name = "uk_mains_matchs_match_id", columnNames = {
		"match_id"}))
	List<MatchEntity> matchs;
}
