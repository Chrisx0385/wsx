package de.cgz.ui;

import java.util.Arrays;

import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;

import de.cgz.ctrl.DisplayMode;
import de.cgz.ctrl.PersonDetailController;
import de.cgz.data.contact.PersonData;
import de.cgz.vaadin.ExtendedBeanItem;


public class PersonDataForm extends Form {

	private static final long serialVersionUID = 1L;
	
	private final PersonDetailController ctrl;
	private final Footer footer;
	
	private PersonData personData;

	public PersonDataForm(PersonDetailController ctrl) {		
		this.ctrl = ctrl;
		this.footer = new Footer(ctrl);
		setFooter(footer);
		
		// Enable buffering so that commit() must be called for the form
		// before input is written to the data. (Form input is not written
		// immediately through to the underlying object.)
		setWriteThrough(false);
		setFormFieldFactory(createFieldFactory());
	}
	
	private FormFieldFactory createFieldFactory() {
		return new DefaultFieldFactory() {
			@Override
			public Field createField(Item item, Object propertyId, Component uiContext) {
				Field f = super.createField(item, propertyId, uiContext);
				//f.setCaption("Honigkuchen");
				return f;
			}
		};
	}

	public void setDisplayMode(DisplayMode mode) {
		setDisplayMode(mode, null);
	}
	
	public void setDisplayMode(DisplayMode mode, PersonData data) {
		personData = data;		
		switch (mode) {
			case EDIT:
				setReadOnly(false);		
			break;
			case CREATE:
				setReadOnly(false);	
				personData = new PersonData();
			break;
			case DISPLAY:
				setReadOnly(false);	
			break;
		}		
		setItemDataSource(new ExtendedBeanItem<PersonData>(personData), Arrays.asList(PersonData.properties));
		
		footer.setDisplayMode(mode);
	}
	

	
	
	
	
	
	
	
	
	
	
	

	private static class Footer extends HorizontalLayout {

		private static final long serialVersionUID = 1L;
		
		Button save;
		Button cancel;
		Button edit;

		Footer(PersonDetailController ctrl) {
			save = new Button("Save", ctrl.getSaveButtonListener());
			cancel = new Button("Cancel", ctrl.getCancelButtonListener());
			edit = new Button("Edit", ctrl.getEditButtonListener());	
			
			setSpacing(true);
			this.addComponent(save);
			this.addComponent(cancel);
			this.addComponent(edit);
			
			setVisible(false);
		}
		
		void setDisplayMode(DisplayMode mode) {
			switch (mode) {
				case EDIT:					
				case CREATE:
					save.setVisible(true);
					cancel.setVisible(true);
					edit.setVisible(false);
					setVisible(true);
				break;
				case DISPLAY:
					save.setVisible(false);
					cancel.setVisible(false);
					edit.setVisible(true);
					setVisible(true);
				break;
				case HIDE:
					setVisible(false);
				break;
			}
		}		
	}
}
