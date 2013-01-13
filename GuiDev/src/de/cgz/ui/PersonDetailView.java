package de.cgz.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import de.cgz.ctrl.DisplayMode;
import de.cgz.ctrl.PersonDetailController;


public class PersonDetailView extends HorizontalLayout {
	
	private final PersonDetailController ctrl;
	
	private final PersonDataForm personDataForm;
	private final ContactPicture contactPicture;
	private final PersonDataToolbar toolbar;

	public PersonDetailView() {
		ctrl = new PersonDetailController(this);
		contactPicture = new ContactPicture();
		personDataForm = new PersonDataForm(ctrl);
		toolbar = new PersonDataToolbar(ctrl);
		init();
	}

	private void init() {
		this.setSizeFull();
		this.addComponent(toolbar);
		toolbar.setWidth(43, UNITS_PIXELS);
		VerticalLayout content = new VerticalLayout();		
		content.addComponent(createMainDataComponents());
		this.addComponent(content);
		this.setExpandRatio(content, 1);
		

		
	}
	
	private Component createMainDataComponents() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(contactPicture);
		layout.addComponent(personDataForm);
		layout.setComponentAlignment(personDataForm, Alignment.TOP_LEFT);
		layout.setSpacing(true);	
		
		return layout;
	}

	public void commit() {
		personDataForm.commit();
	}

	public void setDisplayMode(DisplayMode mode) {
		personDataForm.setDisplayMode(mode);
		contactPicture.setDisplayMode(mode);
	}

}
