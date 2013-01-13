package de.cgz.data.types;


/**
 * All DataObject-types must provide a static method <code>public static DataObject createFromString(String stringrepresentation)</code>
 * to create an DataObject from its toString() - value.
 * @author Chris
 *
 */
public interface DataObject {	
	
	boolean isEmpty();
	boolean isValid();

}
