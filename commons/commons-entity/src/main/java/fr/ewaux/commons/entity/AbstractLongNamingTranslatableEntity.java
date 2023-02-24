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
public abstract class AbstractLongNamingTranslatableEntity extends AbstractLongEntity {

	@Column(name = "name_en", nullable = false, columnDefinition = "varchar(500)")
	String nameEn;
	@Column(name = "name_fr", nullable = false, columnDefinition = "varchar(500)")
	String nameFr;
}
