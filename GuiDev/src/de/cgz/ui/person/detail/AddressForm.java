package de.cgz.ui.person.detail;

import de.cgz.ctrl.InnerFormController;
import de.cgz.data.contact.GermanAddress;
import de.cgz.data.types.collection.container.DataContainer;
import de.cgz.ui.widgets.InnerForm;


public class AddressForm extends InnerForm<GermanAddress> {

	public AddressForm(InnerFormController ctrl, DataContainer<GermanAddress> dataContainer) {
		super(ctrl, dataContainer);
	}

	

}
