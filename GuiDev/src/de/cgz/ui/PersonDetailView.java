package de.cgz.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import de.cgz.ctrl.DisplayMode;
import de.cgz.ctrl.PersonDetailController;


public class PersonDetailView extends VerticalLayout {
	
	private final PersonDetailController ctrl;
	private final PersonDataForm personDataForm;
	private final ContactPicture contactPicture;

	public PersonDetailView() {
		ctrl = new PersonDetailController(this);
		contactPicture = new ContactPicture();
		personDataForm = new PersonDataForm(ctrl);
		init();
	}

	private void init() {
		this.setSizeFull();
		HorizontalLayout layout = new HorizontalLayout();

		layout.addComponent(contactPicture);
		layout.addComponent(personDataForm);
		layout.setComponentAlignment(personDataForm, Alignment.TOP_LEFT);
		layout.setSpacing(true);
		
		this.addComponent(layout);
	}

	public void commit() {
		personDataForm.commit();
		
	}

	public void setDisplayMode(DisplayMode mode) {
		personDataForm.setDisplayMode(mode);
		contactPicture.setDisplayMode(mode);
	}

}
