package de.cgz.data.utils;


public final class StringUtils {
	
	private static StringUtils instance;

	public static StringUtils getInstance() {
		return instance == null ? (instance = new StringUtils()) : instance;
	}

	private StringUtils() {}
	
	public String capitialize(String original) {
		if(isEmpty(original)) {
			return original;
		}
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	

	
	
	private boolean isEmpty(Object o) {
		return DataUtils.getInstance().isEmpty(o);
	}
}
