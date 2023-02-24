package fr.ewaux.backend.entity.club;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.nation.NationEntity;
import fr.ewaux.commons.entity.AbstractLongNamingEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "club")
@Table(name = "club")
public class ClubEntity extends AbstractLongNamingEntity {

	@Column(name = "short_name", nullable = false, columnDefinition = "varchar(255)")
	String shortName;

	@ManyToOne
	@JoinColumn(name = "nation_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CLUB_NATION_NATION_ID"))
	NationEntity nation;


}
