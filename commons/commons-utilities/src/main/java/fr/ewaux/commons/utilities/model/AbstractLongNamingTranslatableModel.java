package fr.ewaux.commons.utilities.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractLongNamingTranslatableModel extends AbstractLongModel {

	String nameEn;
	String nameFr;
}
