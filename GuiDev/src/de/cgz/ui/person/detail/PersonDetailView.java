package de.cgz.ui.person.detail;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import de.cgz.ctrl.PersonDetailController;
import de.cgz.data.contact.PersonData;
import de.cgz.data.types.TypeFactory;
import de.cgz.data.types.collection.container.DataContainer;
import de.cgz.data.ui.DisplayMode;


@SuppressWarnings("serial")
public class PersonDetailView extends HorizontalLayout {
	
	private final PersonDetailController ctrl;
	
	private final PersonDataForm personDataForm;
	private final ContactPicture contactPicture;
	private final PersonDataToolbar toolbar;
	
	private final DataContainer<PersonData> dataContainer = TypeFactory.getInstance().createDataContainer(PersonData.class); //TODO

	public PersonDetailView() {
		ctrl = new PersonDetailController(this);
		contactPicture = new ContactPicture();
		personDataForm = new PersonDataForm(ctrl, dataContainer);
		toolbar = new PersonDataToolbar(ctrl);
		
		init();
	}

	private void init() {
		this.setSizeFull();
		this.addComponent(toolbar);
		toolbar.setWidth(43, UNITS_PIXELS);
		
		Panel contentPane = new Panel();
		contentPane.setStyleName(Reindeer.PANEL_LIGHT);
		
		contentPane.setHeight("100%");
		
		VerticalLayout contentLayout = new VerticalLayout();	
		contentLayout.setMargin(true);
		contentLayout.setSpacing(true);
		
		contentPane.setContent(contentLayout);
		
		contentPane.addComponent(createMainDataComponents());
		
//		Titlebar tb = new Titlebar(new AddressFormController().getTitlebarController());
//		contentPane.addComponent(tb);
//		
		this.addComponent(contentPane);
		this.setExpandRatio(contentPane, 1);
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

	
	public PersonDataForm getPersonDataForm() {
		return personDataForm;
	}

	
	public ContactPicture getContactPicture() {
		return contactPicture;
	}

	
	public PersonDataToolbar getToolbar() {
		return toolbar;
	}
	
	

}
