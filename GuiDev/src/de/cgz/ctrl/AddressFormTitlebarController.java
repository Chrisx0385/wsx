package de.cgz.ctrl;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;


public class AddressFormTitlebarController implements TitlebarController {
	
	private final ClickListener closeButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
			
		}
	};
	
	private final ClickListener hideButtonListener = new ClickListener() {		
		public void buttonClick(ClickEvent event) {
			
		}
	};

	public ClickListener getCloseButtonListener() {
		return closeButtonListener;
	}

	public ClickListener getHideButtonListener() {
		return hideButtonListener;
	}
	
	
	

}
