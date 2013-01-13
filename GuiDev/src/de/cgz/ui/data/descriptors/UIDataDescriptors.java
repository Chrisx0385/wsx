package de.cgz.ui.data.descriptors;

import java.util.HashMap;
import java.util.Map;

import de.cgz.data.types.DataObject;
import de.cgz.data.types.Descriptor;


public class UIDataDescriptors {
	private static UIDataDescriptors instance;

	public static UIDataDescriptors getInstance() {
		return instance == null ? (instance = new UIDataDescriptors()) : instance;
	}
	
	private final Map<String, Descriptor<? extends DataObject>> descriptors = new HashMap<String, Descriptor<? extends DataObject>>();
	
	private UIDataDescriptors() {
		registerDescriptors();
	}
	
	private <T extends DataObject> void register(Class<T> type, Descriptor<T> descriptor) {
		register(type.getName(), descriptor);
	}
	
	private void register(String type, Descriptor<? extends DataObject> descriptor) {
		descriptors.put(type, descriptor);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends DataObject> Descriptor<T> getDescriptor(String descriptedType) {
		return (Descriptor<T>) descriptors.get(descriptedType);		
	}
	public <T extends DataObject> Descriptor<T> getDescriptor(Class<T> descriptedType) {
		return getDescriptor(descriptedType.getName());		
	}
	
	
	private void registerDescriptors() {
		register("de.cgz.data.contact.GermanAddress", new GermanAddressDiscriptor());
	}
}
