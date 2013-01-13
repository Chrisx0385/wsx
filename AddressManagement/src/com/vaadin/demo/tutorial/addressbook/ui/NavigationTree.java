package com.vaadin.demo.tutorial.addressbook.ui;

import com.example.addressmanagement.AddressmanagementApplication;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

public class NavigationTree extends Tree {

	private static final long serialVersionUID = 1L;

	public static final Object SHOW_ALL = "Show all";
	public static final Object SEARCH = "Search";

	public NavigationTree(AddressmanagementApplication app) {
		addItem(SHOW_ALL);
		addItem(SEARCH);

		/*
		 * We want items to be selectable but do not want the user to be able to
		 * de-select an item.
		 */
		setSelectable(true);
		setNullSelectionAllowed(false);

		// Make application handle item click events
		addListener((ItemClickListener) app);
	}

}
