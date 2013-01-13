package com.vaadin.demo.tutorial.addressbook.ui;

import com.example.addressmanagement.AddressmanagementApplication;
import com.vaadin.data.Property;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

import de.cgz.data.contact.Person;
import de.cgz.data.contact.PersonContainer;

public class PersonList extends Table {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	public PersonList(AddressmanagementApplication app) {
		// create some dummy data
		// addContainerProperty("First Name", String.class, "Mark");
		// addContainerProperty("Last Name", String.class, "Smith");
		// addItem();
		// addItem();

		setSizeFull();

		// customize email column to have mailto: links using column generator
		addGeneratedColumn("email", new ColumnGenerator() {
			public Component generateCell(Table source, Object itemId, Object columnId) {
				Person p = (Person) itemId;
				Link l = new Link();
				l.setResource(new ExternalResource("mailto:" + p.getEmail()));
				l.setCaption(p.getEmail());
				return l;
			}
		});

		setContainerDataSource(app.getDataSource());
		setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
		setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);

		/*
		 * Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		setSelectable(true);
		setImmediate(true);
		addListener((Property.ValueChangeListener) app);
		/* We don't want to allow users to de-select a row */
		setNullSelectionAllowed(false);
		
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);
	}
}