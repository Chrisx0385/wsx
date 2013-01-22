package de.cgz.ctrl;

import de.cgz.data.types.TypeFactory;
import de.cgz.data.utils.DataUtils;


public abstract class AbstractController {

	protected final static DataUtils utils() {
		return DataUtils.getInstance();
	}
	protected static TypeFactory factory() {
		return TypeFactory.getInstance();
	}
}
