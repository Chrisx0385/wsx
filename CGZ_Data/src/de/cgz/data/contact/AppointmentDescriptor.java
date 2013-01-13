package de.cgz.data.contact;

import java.util.Arrays;
import java.util.List;

import de.cgz.data.types.AbstractDescriptor;


public class AppointmentDescriptor extends AbstractDescriptor<Appointment> {

	public AppointmentDescriptor(Class<Appointment> type) {
		super(type);
	}

	private List<String> types = Arrays.asList(new String[]{ "PRIVATE", "COMMERCIAL" });

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public boolean addType(String e) {
		return types.add(e);
	}

	public boolean isValid() {
		return true;
	}

	public boolean hasData() {
		return !isEmpty(types);
	}

	
	
	
	

}
