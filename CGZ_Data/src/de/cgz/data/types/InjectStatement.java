package de.cgz.data.types;


public interface InjectStatement<T, M> {
	public M execute(T element, int index, M mem);
}
