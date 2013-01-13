package de.cgz.ui;

import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import de.cgz.ctrl.DisplayMode;
import de.cgz.ctrl.PersonDetailController;


public class PersonDataToolbar extends VerticalLayout {

	private static final long serialVersionUID = 4867631660606579479L;
	
	private final Button addAddressButton = new Button();
	private final Button addNoticeButton = new Button();
	private final Button addPhoneButton = new Button();
	
	private final Button[] buttons = new Button[]{addAddressButton, addNoticeButton, addPhoneButton};
	
	private final PersonDetailController ctrl;
	
	public PersonDataToolbar(PersonDetailController ctrl) {
		this.ctrl = ctrl;
		init();
	}

	private void init() {	
		
		setStyleName("v-toolbar");
		setHeight(100, Sizeable.UNITS_PERCENTAGE);
		
		for(Button b : buttons) {
			b.setStyleName("toolbar-button");
			b.setHeight(41, Sizeable.UNITS_PIXELS);
			b.setWidth(41, Sizeable.UNITS_PIXELS);			
			addComponent(b);
		}

		
		addAddressButton.addListener(ctrl.getAddAddressButtonListener());
		addNoticeButton.addListener(ctrl.getAddNoticeButtonListener());
		addPhoneButton.addListener(ctrl.getAddPhoneButtonListener());
		
		addAddressButton.setIcon(new ThemeResource("icons/32/add_address.png"));
		addNoticeButton.setIcon(new ThemeResource("icons/32/add_note.png"));
		addPhoneButton.setIcon(new ThemeResource("icons/32/add_phone.png"));
		
		addAddressButton.setDescription("Eine Adresse hinzufügen");
		addNoticeButton.setDescription("Eine Notiz hinzufügen");
		addPhoneButton.setDescription("Eine Telefonnr. hinzufügen");
	}
	
	public void setDisplayMode(DisplayMode mode) {		
		switch (mode) {
			case EDIT:
			case CREATE:
				setVisible(true);
			break;
			case DISPLAY:
				setVisible(false);	
			break;
		}
	}
}
