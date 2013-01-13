package de.cgz.ctrl;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import de.cgz.ui.PersonDataForm;
import de.cgz.ui.PersonDetailView;


public class PersonDetailController {
	
	private final PersonDetailView view;
	
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
	
	public PersonDetailController(PersonDetailView view) {
		this.view = view;
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
	
	

}
