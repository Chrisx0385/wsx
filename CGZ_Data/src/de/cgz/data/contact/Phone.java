package de.cgz.data.contact;

import java.util.Arrays;
import java.util.List;


public class Phone extends AbstractContactData {
	
	private static final String[] defaultTypes = { "PHONE", "FAX", "HANDY" };
	
	enum PhoneType {
		PHONE_PRIVATE, PHONE_COMERCIAL, FAX
	}

	public static List<String> getDefaulttypes() {
		return Arrays.asList(defaultTypes);
	}
	
	/*END static **************************************************************************************/
	
	private String type;
	private String number;
	
	
}
