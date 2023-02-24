package fr.ewaux.commons.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractLongNamingEntity extends AbstractLongEntity {

	@Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
	String name;
}
