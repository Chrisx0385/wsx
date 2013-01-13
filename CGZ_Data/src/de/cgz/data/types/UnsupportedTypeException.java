package de.cgz.data.types;


public class UnsupportedTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedTypeException() {
		super();
	}

	public UnsupportedTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedTypeException(String message) {
		super(message);	}

	public UnsupportedTypeException(Throwable cause) {
		super(cause);
	}

	
}
