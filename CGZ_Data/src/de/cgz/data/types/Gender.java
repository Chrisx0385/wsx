package de.cgz.data.types;


public enum Gender implements DataObject {

	MALE, FEMALE, UNDEFINED;

	public boolean isEmpty() {
		return this != UNDEFINED;
	}

	@Override
	public boolean isValid() {
		return true;
	}
	
	public static DataObject createFromString(String stringrepresentation) {
		return valueOf(stringrepresentation);
	}

}
