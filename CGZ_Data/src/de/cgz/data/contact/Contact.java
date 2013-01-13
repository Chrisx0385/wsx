package de.cgz.data.contact;



public class Contact extends AbstractContactData {

	private String type; 	
	
	private NamingData naming;
	
	private Address address;
	
	
	
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	
}
