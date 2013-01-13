package de.cgz.ctrl;

import com.vaadin.ui.Button.ClickListener;

public interface FormFooterController {

	ClickListener getSaveButtonListener();

	ClickListener getEditButtonListener();

	ClickListener getCancelButtonListener();

}
