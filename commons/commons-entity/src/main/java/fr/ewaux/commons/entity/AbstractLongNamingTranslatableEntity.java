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
public abstract class AbstractLongNamingTranslatableEntity extends AbstractLongEntity {

	@Column(name = "name_en", nullable = false, columnDefinition = "VARCHAR(500)")
	String nameEn;
	@Column(name = "name_fr", nullable = false, columnDefinition = "VARCHAR(500)")
	String nameFr;

	@Override
	public String toString() {
		return super.toString() + ", nameEn:" + this.nameEn + ", nameFr:" + this.nameFr;
	}
}
