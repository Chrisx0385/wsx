package com.example.addressmanagement;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.demo.tutorial.addressbook.data.SearchFilter;
import com.vaadin.demo.tutorial.addressbook.ui.HelpWindow;
import com.vaadin.demo.tutorial.addressbook.ui.ListView;
import com.vaadin.demo.tutorial.addressbook.ui.NavigationTree;
import com.vaadin.demo.tutorial.addressbook.ui.PersonForm;
import com.vaadin.demo.tutorial.addressbook.ui.PersonList;
import com.vaadin.demo.tutorial.addressbook.ui.SearchView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import de.cgz.data.contact.PersonContainer;

public class AddressmanagementApplication extends Application implements Button.ClickListener,
		Property.ValueChangeListener, ItemClickListener {

	private static final long serialVersionUID = 1L;

	private Button newContact = new Button("Add contact");
	private Button search = new Button("Search");
	private Button share = new Button("Share");
	private Button help = new Button("Help");

	private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();

	private NavigationTree tree;

	private ListView listView;

	private SearchView searchView = null;

	private PersonList personList;

	private PersonForm personForm;

	private Window helpWindow;

	private PersonContainer dataSource = PersonContainer.createWithTestData();

	public void init() {
		setTheme("contacts");
		buildMainLayout();

		showListView();
		// getMainWindow().addWindow(getHelpWindow());
	}

	private Window getHelpWindow() {
		if (helpWindow == null) {
			helpWindow = new HelpWindow();
		}

		return helpWindow;
	}

	private void buildMainLayout() {
		setMainWindow(new Window("Address Book Demo application"));		

		VerticalLayout layout = new VerticalLayout();
		
		layout.setSizeFull();

		layout.addComponent(createToolbar());
		layout.addComponent(horizontalSplit);

		/* Allocate all available extra space to the horizontal split panel */
		layout.setExpandRatio(horizontalSplit, 1);

		/*
		 * Set the initial split position so we can have a 200 pixel menu to the
		 * left
		 */
		horizontalSplit.setSplitPosition(200, Sizeable.UNITS_PIXELS);

		horizontalSplit.setFirstComponent(getTree());

		getMainWindow().setContent(layout);
	}

	private NavigationTree getTree() {
		if (tree == null) {
			tree = new NavigationTree(this);

		}

		return tree;
	}

	private Component createToolbar() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setStyleName("toolbar");
		
		search.setIcon(new ThemeResource("icons/32/folder-add.png"));
		share.setIcon(new ThemeResource("icons/32/users.png"));
		help.setIcon(new ThemeResource("icons/32/help.png"));
		newContact.setIcon(new ThemeResource("icons/32/document-add.png"));
		
		layout.addComponent(newContact);
		layout.addComponent(search);
		layout.addComponent(share);
		layout.addComponent(help);
		
		layout.setWidth("100%");

		Embedded em = new Embedded("", new ThemeResource("images/logo.png"));
		layout.addComponent(em);
		layout.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
		layout.setExpandRatio(em, 1);	
		

		search.addListener((Button.ClickListener) this);
		newContact.addListener((Button.ClickListener) this);		
		
		layout.setMargin(true);
		layout.setSpacing(true);

		return layout;
	}

	private void setMainComponent(Component c) {
		horizontalSplit.setSecondComponent(c);
	}

	private ListView getListView() {
		if (listView == null) {
			listView = new ListView(getPersonList(), getPersonForm());
		}
		return listView;
	}

	private PersonForm getPersonForm() {
		if (personForm == null) {
			personForm = new PersonForm(this);
		}

		return personForm;
	}

	private PersonList getPersonList() {
		if (personList == null) {
			personList = new PersonList(this);
		}
		return personList;
	}

	public PersonContainer getDataSource() {
		return dataSource;
	}

	private SearchView getSearchView() {
		if (searchView == null) {
			searchView = new SearchView(this);
		}
		return searchView;
	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();
		if (source == search) {
			showSearchView();
		} else if (source == newContact) {
			addNewContact();
		}

	}

	private void showSearchView() {
		setMainComponent(getSearchView());
	}

	private void showListView() {
		setMainComponent(getListView());
	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == personList) {
			Item item = personList.getItem(personList.getValue());
			if (item != personForm.getItemDataSource()) {
				personForm.setItemDataSource(item);
			}
		}

	}

	public void itemClick(ItemClickEvent event) {
		if (event.getSource() == tree) {
			Object itemId = event.getItemId();
			if (itemId != null) {
				if (NavigationTree.SHOW_ALL.equals(itemId)) {
					// clear previous filters
					getDataSource().removeAllContainerFilters();
					showListView();
				} else if (NavigationTree.SEARCH.equals(itemId)) {
					showSearchView();
				} else if (itemId instanceof SearchFilter) {
					search((SearchFilter) itemId);
				}
			}
		}
	}

	private void addNewContact() {
		showListView();
		personForm.addContact();
	}

	public void search(SearchFilter searchFilter) {
		// clear previous filters
		getDataSource().removeAllContainerFilters();
		// filter contacts with given filter
		getDataSource().addContainerFilter(searchFilter.getPropertyId(), searchFilter.getTerm(), true, false);
		getMainWindow().showNotification("Searched for " + searchFilter.getPropertyId() + "=*" + searchFilter.getTerm()
				+ "*, found " + getDataSource().size() + " item(s).", Notification.TYPE_TRAY_NOTIFICATION);
		showListView();
	}

	public void saveSearch(SearchFilter searchFilter) {
		tree.addItem(searchFilter);
		tree.setParent(searchFilter, NavigationTree.SEARCH);
		// mark the saved search as a leaf (cannot have children)
		tree.setChildrenAllowed(searchFilter, false);
		// make sure "Search" is expanded
		tree.expandItem(NavigationTree.SEARCH);
		// select the saved search
		tree.setValue(searchFilter);
	}

}
