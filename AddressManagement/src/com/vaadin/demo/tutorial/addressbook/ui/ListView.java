package com.vaadin.demo.tutorial.addressbook.ui;

import com.vaadin.ui.VerticalSplitPanel;

public class ListView extends VerticalSplitPanel {


	private final PersonList personList;
	private final PersonForm personForm;

	public ListView(PersonList personList, PersonForm personForm) {
		this.personList = personList;
		this.personForm = personForm;
		
		addStyleName("view");
		
        setFirstComponent(personList);
        setSecondComponent(personForm);
        setSplitPosition(40);		
	}
}