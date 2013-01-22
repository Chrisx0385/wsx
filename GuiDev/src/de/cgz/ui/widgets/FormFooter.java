package de.cgz.ui.widgets;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

import de.cgz.ctrl.FormFooterController;
import de.cgz.data.ui.DisplayMode;

public class FormFooter extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	Button save;
	Button cancel;
	Button edit;

	public FormFooter(FormFooterController ctrl) {
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
		}
	}		
}