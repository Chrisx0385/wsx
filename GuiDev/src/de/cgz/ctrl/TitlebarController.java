package de.cgz.ctrl;

import com.vaadin.ui.Button.ClickListener;

public interface TitlebarController {

	ClickListener getCloseButtonListener();

	ClickListener getHideButtonListener();

}