package com.vaadin.demo.tutorial.addressbook.data;

import java.io.Serializable;

public class SearchFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String term;
	private final Object propertyId;
	private String searchName;

	public SearchFilter(Object propertyId, String searchTerm, String name) {
		this.propertyId = propertyId;
		this.term = searchTerm;
		this.searchName = name;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getTerm() {
		return term;
	}

	public Object getPropertyId() {
		return propertyId;
	}

	@Override
	public String toString() {
		return getSearchName();
	}

}