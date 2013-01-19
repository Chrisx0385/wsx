package de.cgz.ui.data.descriptors;

import de.cgz.data.contact.PersonData;
import de.cgz.data.types.AbstractDescriptor;
import de.cgz.data.ui.DisplayMode;


public class PersonDataDescriptor extends AbstractDescriptor<PersonData> {

	public PersonDataDescriptor() {
		super(PersonData.class);
	}

	public static final String[] editProperties = { "businessName", "firstName", "lastName", "birthDay", "gender"};
	public static final String[] displayProperties = { "businessName", "firstName", "lastName", "birthDay", "gender", "addresses", "notices", "phones", "emails", "pictures" };
	
	public static final String[] collectionProperties = {"addresses", "notices", "phones", "emails", "pictures"};
	
	
	
	public String[] getPropertiesArray(DisplayMode mode) {		
		switch (mode) {
			case CREATE:
			case EDIT:
				return editProperties;
			case DISPLAY:
				return displayProperties;
			default:
				return new String[0];
		}
	}



	@Override
	protected String[] getCollectionPropertiesArray(DisplayMode mode) {
		return collectionProperties;
	}


}
