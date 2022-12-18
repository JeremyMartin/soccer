package fr.ewaux.commons.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractLongNamingTranslatableEntity extends AbstractLongEntity {

	@Column(name = "name_en", nullable = false, columnDefinition = "varchar(500)")
	String nameEn;
	@Column(name = "name_fr", nullable = false, columnDefinition = "varchar(500)")
	String nameFr;
}
