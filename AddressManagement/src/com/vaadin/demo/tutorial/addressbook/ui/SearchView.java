package com.vaadin.demo.tutorial.addressbook.ui;

import com.example.addressmanagement.AddressmanagementApplication;
import com.vaadin.demo.tutorial.addressbook.data.SearchFilter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

import de.cgz.data.contact.PersonContainer;

public class SearchView extends Panel {

	private static final long serialVersionUID = 1L;

	private TextField tf;
	private NativeSelect fieldToSearch;
	private CheckBox saveSearch;
	private TextField searchName;
	private AddressmanagementApplication app;

	public SearchView(final AddressmanagementApplication app) {
		this.app = app;
		
		addStyleName("view");
		
		setCaption("Search contacts");
		setSizeFull();

		/* Use a FormLayout as main layout for this Panel */
		FormLayout formLayout = new FormLayout();
		setContent(formLayout);

		/* Create UI components */
		tf = new TextField("Search term");
		fieldToSearch = new NativeSelect("Field to search");
		saveSearch = new CheckBox("Save search");
		searchName = new TextField("Search name");
		Button search = new Button("Search");

		/* Initialize fieldToSearch */
		for (int i = 0; i < PersonContainer.NATURAL_COL_ORDER.length; i++) {
			fieldToSearch.addItem(PersonContainer.NATURAL_COL_ORDER[i]);
			fieldToSearch.setItemCaption(PersonContainer.NATURAL_COL_ORDER[i], PersonContainer.COL_HEADERS_ENGLISH[i]);
		}

		fieldToSearch.setValue("lastName");
		fieldToSearch.setNullSelectionAllowed(false);

		/* Initialize save checkbox */
		saveSearch.setValue(true);

		/* Add all the created components to the form */
		addComponent(tf);
		addComponent(fieldToSearch);
		addComponent(saveSearch);
		addComponent(searchName);
		addComponent(search);

		saveSearch.setImmediate(true);
		saveSearch.addListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				searchName.setVisible(event.getButton().booleanValue());
			}
		});

		search.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				performSearch();
			}
		});
	}

	private void performSearch() {
		String searchTerm = (String) tf.getValue();
		SearchFilter searchFilter = new SearchFilter(fieldToSearch.getValue(), searchTerm, (String) searchName.getValue());
		if (saveSearch.booleanValue()) {
			app.saveSearch(searchFilter);
		}
		app.search(searchFilter);

	}
}