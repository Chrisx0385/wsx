package de.cgz.ui.widgets;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;

import de.cgz.ctrl.TitlebarController;


public class Titlebar extends HorizontalLayout {
	
	private final HorizontalLayout spacer = new HorizontalLayout();
	private final Button hideButton = new Button();
	private final Button closeButton = new Button();
	
	private Component[] components = new Component[]{spacer, hideButton, closeButton};
	
	private final TitlebarController ctrl;
	
	public Titlebar(TitlebarController ctrl) {
		this.ctrl = ctrl;
		init();
	}

	protected void init() {
		setStyleName("titlebar");
		
		setHeight("18px");
		setWidth("200px");
		
		Embedded borderL = new Embedded();
		Embedded borderR = new Embedded();
		
		borderL.setSource(new ThemeResource("images/titlebar-l.png"));
		borderR.setSource(new ThemeResource("images/titlebar-r-slim.png"));
		
		borderL.setStyleName("titlebar-border titlebar-border-l");
		borderR.setStyleName("titlebar-border titlebar-border-r");
		
		addComponent(borderL);
		setComponentAlignment(borderL, Alignment.MIDDLE_LEFT);
		
		for (Component c : components) {
			addComponent(c);
			if(c instanceof Button) {
				c.setStyleName("titlebar-button icon-button");
			}
		}
		
		addComponent(borderR);
		setComponentAlignment(borderR, Alignment.MIDDLE_RIGHT);
		
		setExpandRatio(spacer, 1);
		
		hideButton.addListener(ctrl.getHideButtonListener());
		closeButton.addListener(ctrl.getCloseButtonListener());
		
		hideButton.setIcon(new ThemeResource("icons/14/titlebar-hide-button.png"));
		closeButton.setIcon(new ThemeResource("icons/14/titlebar-close-button.png"));		
	}
	
	
}
