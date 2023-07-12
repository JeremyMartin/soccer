package fr.ewaux.commons.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractLongNamingEntity extends AbstractLongEntity {

	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
	String name;

	@Override
	public String toString() {
		return super.toString() + ", name:" + this.name;
	}
}
