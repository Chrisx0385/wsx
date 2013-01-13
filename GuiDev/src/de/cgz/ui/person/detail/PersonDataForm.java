package de.cgz.ui.person.detail;

import java.util.Arrays;

import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;

import de.cgz.ctrl.PersonDetailController;
import de.cgz.data.contact.PersonData;
import de.cgz.data.types.collection.container.DataContainer;
import de.cgz.data.ui.DisplayMode;
import de.cgz.ui.widgets.DataObjectForm;
import de.cgz.vaadin.ExtendedBeanItem;


public class PersonDataForm extends DataObjectForm<PersonData> {

	private static final long serialVersionUID = 1L;
	
	private final PersonDetailController ctrl;

	public PersonDataForm(PersonDetailController ctrl, DataContainer<PersonData> dataContainer) {
		super(dataContainer, ctrl);
		this.ctrl = ctrl;
	}


	

	
	
	
	
	
	
	
	
	
	
	


}
