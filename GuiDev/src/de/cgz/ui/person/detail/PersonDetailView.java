package de.cgz.ui.person.detail;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import de.cgz.ctrl.AddressFormController;
import de.cgz.ctrl.PersonDetailController;
import de.cgz.data.contact.GermanAddress;
import de.cgz.data.contact.PersonData;
import de.cgz.data.types.TypeFactory;
import de.cgz.data.types.collection.container.ListDataContainer;
import de.cgz.data.ui.DisplayMode;
import de.cgz.ui.widgets.InnerForm;
import de.cgz.ui.widgets.Titlebar;


@SuppressWarnings("serial")
public class PersonDetailView extends HorizontalLayout {
	
	private final PersonDetailController ctrl;
	
	private final PersonDataForm personDataForm;
	private final PersonDataToolbar toolbar;
	
	private final ListDataContainer<PersonData> dataContainer = TypeFactory.getInstance().createDataContainer(PersonData.class); //TODO

	public PersonDetailView() {
		ctrl = new PersonDetailController(this);
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
		
		InnerForm<GermanAddress> addF = new InnerForm<GermanAddress>(new AddressFormController(), dataContainer.createDataObject().getAddresses(), null);
		personDataForm.setDisplayMode(DisplayMode.EDIT);
		contentPane.addComponent(personDataForm);
		addF.setDisplayMode(DisplayMode.EDIT);
		addF.setHeight("100px");
		addF.setWidth("100px");
		contentPane.addComponent(addF);
		Titlebar tb = new Titlebar(new AddressFormController().getTitlebarController());
		contentPane.addComponent(tb);
//		
		this.addComponent(contentPane);
		this.setExpandRatio(contentPane, 1);
	}
	


	public void commit() {
		personDataForm.commit();
	}

	public void setDisplayMode(DisplayMode mode) {
		personDataForm.setDisplayMode(mode);
	}

	
	public PersonDataForm getPersonDataForm() {
		return personDataForm;
	}
	
	public PersonDataToolbar getToolbar() {
		return toolbar;
	}
	
	

}
