package de.cgz.ctrl;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import de.cgz.data.contact.PersonData;
import de.cgz.ui.person.detail.PersonDataForm;
import de.cgz.ui.person.detail.PersonDetailView;


@SuppressWarnings("serial")
public class PersonDetailController extends AbstractController implements FormFooterController {
	
	private final PersonDetailView view;
	private final PersonDataForm personDataForm;
	
	private final ClickListener saveButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
			view.commit();
		}
	};
	private final ClickListener editButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
		}
	};
	private final ClickListener cancelButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
		}
	};
	
	private final ClickListener addAddressButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
			PersonData data = personDataForm.getDataObject();
			data.getAddresses().add(data.getAddresses().createDataObject());
			personDataForm.setDataObject(data);
		}
	};	
	private final ClickListener addNoticeButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
		}
	};
	private final ClickListener addPhoneButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
		}
	};
	
	public PersonDetailController(PersonDetailView view) {
		this.view = view;
		this.personDataForm = view.getPersonDataForm();
	}

	public ClickListener getSaveButtonListener() {
		return saveButtonListener;
	}
	
	public ClickListener getEditButtonListener() {
		return editButtonListener;
	}
	
	public ClickListener getCancelButtonListener() {
		return cancelButtonListener;
	}

	
	public ClickListener getAddAddressButtonListener() {
		return addAddressButtonListener;
	}

	
	public ClickListener getAddNoticeButtonListener() {
		return addNoticeButtonListener;
	}

	
	public ClickListener getAddPhoneButtonListener() {
		return addPhoneButtonListener;
	}
	
	
	
	

}
