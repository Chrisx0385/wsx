package de.cgz.data.contact;

import java.io.Serializable;

import com.vaadin.data.util.BeanItemContainer;


public class PersonContainer extends BeanItemContainer<Person> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Natural property order for Person bean. Used in tables and forms.
	 */
	public static final Object[] NATURAL_COL_ORDER = new Object[] { "firstName", "lastName", "email", "phoneNumber", "streetAddress", "postalCode", "city" };

	/**
	 * "Human readable" captions for properties in same order as in
	 * NATURAL_COL_ORDER.
	 */
	public static final String[] COL_HEADERS_ENGLISH = new String[] { "First name", "Last name", "Email", "Phone number", "Street Address", "Postal Code", "City" };

	public PersonContainer() throws InstantiationException, IllegalAccessException {
		super(Person.class);
	}



}