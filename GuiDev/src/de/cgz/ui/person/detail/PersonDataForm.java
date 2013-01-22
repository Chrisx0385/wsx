package de.cgz.ui.person.detail;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

import de.cgz.ctrl.AddressFormController;
import de.cgz.ctrl.PersonDetailController;
import de.cgz.data.contact.GermanAddress;
import de.cgz.data.contact.PersonData;
import de.cgz.data.types.Statement;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.container.ListDataContainer;
import de.cgz.ui.widgets.DataObjectForm;
import de.cgz.ui.widgets.InnerForm;

@SuppressWarnings( {"serial", "unchecked"} )

public class PersonDataForm extends DataObjectForm<PersonData> {
	
	private ListDataCollection<InnerForm<GermanAddress>> addresses = (ListDataCollection<InnerForm<GermanAddress>>) factory().createListDataCollection();
	
//	private DataCollection<Notice> notices;
//	private DataCollection<Phone> phones;
//	private DataCollection<Email> emails;
//	private DataCollection<Picture> pictures;
	
	private final PersonDetailController ctrl;

    private final ContactPicture contactPicture;

	private GridLayout gridLayout;
	
	

	public PersonDataForm(PersonDetailController ctrl, ListDataContainer<PersonData> dataContainer) {
		super(dataContainer, ctrl);
		this.ctrl = ctrl;
		contactPicture = new ContactPicture();
		gridLayout = new GridLayout();
		init();
		
		dataObjectChanged(getDataObject());
	}
	
	@Override
	protected void init() {
		getInnerForm().getLayout().setMargin(true);
		HorizontalLayout layout = new HorizontalLayout();

		layout.addComponent(contactPicture);		
		layout.addComponent(getInnerForm());
		super.addComponent(layout);
		
		gridLayout.setColumns(2);
		super.addComponent(gridLayout);
	}

	
	private InnerForm<GermanAddress> createAddressForm() {
		InnerForm<GermanAddress> innerForm = new InnerForm<GermanAddress>(new AddressFormController(), getDataObject().getAddresses(), this);
		addresses.add(innerForm);
		addChildForm(innerForm);
		return innerForm;
	}
	
	@Override
	public void addComponent(Component c) {
		gridLayout.addComponent(c);
	}
	
	private void clearAddresses() {
		addresses.forEach(new Statement<InnerForm<GermanAddress>>() {
			public Object execute(InnerForm<GermanAddress> address, int index) {				
				removeChild(address);
				return null;
			}});
		addresses.clear();
	}
	
	@Override
	protected void dataObjectChanged(PersonData dataObject) {
		super.dataObjectChanged(dataObject);
		clearAddresses();
		for(GermanAddress address : getDataObject().getAddresses()) {
			InnerForm<GermanAddress> addressForm = createAddressForm();
			addressForm.setDataObject(address);
		}		
	}
	



	
	
	
	
	
	
	
	
	
	
	


}
