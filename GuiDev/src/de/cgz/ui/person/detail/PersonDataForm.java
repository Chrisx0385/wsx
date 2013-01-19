package de.cgz.ui.person.detail;

import de.cgz.ctrl.AddressFormController;
import de.cgz.ctrl.PersonDetailController;
import de.cgz.data.contact.GermanAddress;
import de.cgz.data.contact.PersonData;
import de.cgz.data.types.Statement;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.container.DataContainer;
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
	
	

	public PersonDataForm(PersonDetailController ctrl, DataContainer<PersonData> dataContainer) {
		super(dataContainer, ctrl);
		this.ctrl = ctrl;
		init();
	}

	private void init() {
		

	}
	
	private InnerForm<GermanAddress> createAddressForm() {
		InnerForm<GermanAddress> innerForm = new InnerForm<GermanAddress>(new AddressFormController(), getDataObject().getAddresses(), this);
		addresses.add(innerForm);
		addChildForm(innerForm);
		return innerForm;
	}
	
	private void clearAddresses() {
		addresses.forEach(new Statement<InnerForm<GermanAddress>>() {
			public Object execute(InnerForm<GermanAddress> address, int index) {
				
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
