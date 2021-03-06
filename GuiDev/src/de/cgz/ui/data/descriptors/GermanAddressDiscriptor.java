package de.cgz.ui.data.descriptors;

import de.cgz.data.contact.GermanAddress;
import de.cgz.data.types.AbstractDescriptor;
import de.cgz.data.ui.DisplayMode;


public class GermanAddressDiscriptor extends AbstractDescriptor<GermanAddress> {
	/*
	 * 	private String receiver;	
	private String street;
	private String streetNumber;
	private String postalCode;
	private String city;
	 */
	private final String[] properties = {"receiver", "street", "streetNumber", "postalCode", "city"};
	
	
	public GermanAddressDiscriptor() {
		super(GermanAddress.class);
	}

	@Override
	protected String[] getPropertiesArray(DisplayMode mode) {		
		return properties;
	}

	@Override
	protected String[] getCollectionPropertiesArray(DisplayMode mode) {		
		return new String[0];
	}
	
	

}
