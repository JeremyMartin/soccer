package fr.ewaux.backend.entity.tournament;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.phase.GroupEntity;
import fr.ewaux.backend.entity.phase.ConsolingEntity;
import fr.ewaux.backend.entity.phase.MainEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.commons.entity.AbstractLongNamingEntity;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Entity(name = "tournament")
@Table(name = "tournament")
public class TournamentEntity extends AbstractLongNamingEntity {

	@Column(name = "date", columnDefinition = "timestamp with time zone")
	OffsetDateTime date;

	@Column(name = "match_type", columnDefinition = "integer default 1")
	Integer matchType;

	@OneToMany
	@JoinTable(name = "tournaments_teams", joinColumns = {
		@JoinColumn(name = "tournament_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "fk_tournaments_teams_tournament_id"), inverseJoinColumns = {
		@JoinColumn(name = "team_id", referencedColumnName = "id")}, inverseForeignKey = @ForeignKey(name = "fk_tournaments_teams_team_id"), uniqueConstraints = @UniqueConstraint(name = "uk_tournaments_teams_teams_id", columnNames = {
		"team_id"}))
	List<TeamEntity> teams;

	@OneToMany
	@JoinTable(name = "tournaments_groups", joinColumns = {
		@JoinColumn(name = "tournaments_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "fk_tournaments_groups_tournament_id"), inverseJoinColumns = {
		@JoinColumn(name = "groups_id", referencedColumnName = "id")}, inverseForeignKey = @ForeignKey(name = "fk_tournaments_groups_groups_id"), uniqueConstraints = @UniqueConstraint(name = "uk_tournaments_groups_groups_id", columnNames = {
		"groups_id"}))
	List<GroupEntity> groups;

	@OneToOne()
	@JoinColumn(name = "main_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tournament_main_id"))
	MainEntity main;

	@OneToOne
	@JoinColumn(name = "consoling_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tournament_consoling_id"))
	ConsolingEntity consoling;
}
