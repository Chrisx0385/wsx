package com.example.guidev;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.cgz.ctrl.PersonDetailController;
import de.cgz.data.contact.PersonData;
import de.cgz.data.ui.DisplayMode;
import de.cgz.ui.person.detail.ContactPicture;
import de.cgz.ui.person.detail.PersonDataForm;
import de.cgz.ui.person.detail.PersonDetailView;
import de.cgz.ui.widgets.ZodiacSign;

public class GuidevApplication extends Application {
	@Override
	public void init() {
		setTheme("contacts");
		Window mainWindow = new Window("Guidev Application");
		Label label = new Label("Hello Vaadin user");

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        PersonDetailView personDetailView = new PersonDetailView();
        layout.addComponent(personDetailView);

		mainWindow.setContent(layout);
		setMainWindow(mainWindow);

		personDetailView.setDisplayMode(DisplayMode.CREATE);
	}

}
