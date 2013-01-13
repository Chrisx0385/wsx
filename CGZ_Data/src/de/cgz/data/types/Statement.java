package de.cgz.data.types;

public interface Statement<T> {
	
	public Object execute(T element, int index);
}