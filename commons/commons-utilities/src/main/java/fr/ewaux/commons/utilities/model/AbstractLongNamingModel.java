package fr.ewaux.commons.utilities.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractLongNamingModel extends AbstractLongModel {

	String name;
}
