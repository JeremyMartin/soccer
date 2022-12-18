package fr.ewaux.backend.entity.nation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.commons.entity.AbstractLongNamingTranslatableEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "nation")
@Table(name = "nation")
public class NationEntity extends AbstractLongNamingTranslatableEntity {

}
