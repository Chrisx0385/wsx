package de.cgz.data.contact;

import java.util.Arrays;
import java.util.List;

import de.cgz.data.types.AbstractDataObject;


public class AbstractContactData extends AbstractDataObject {

	protected static final String[] defaultTypes = { "PRIVATE", "COMMERCIAL" };

	public static List<String> getDefaulttypes() {
		return Arrays.asList(defaultTypes);
	}
	
	private String contactDataType;
	
	

}
