package de.cgz.ui.widgets;

import de.cgz.ctrl.InnerFormController;
import de.cgz.data.types.DataObject;
import de.cgz.data.types.collection.container.DataContainer;


public class InnerForm<T extends DataObject> extends DataObjectForm<T> {

	private static final long serialVersionUID = -259964841386557341L;

	private final InnerFormController ctrl;	
	
	private final Titlebar titlebar;
	

	public InnerForm(InnerFormController ctrl, DataContainer<T> dataContainer) {
		super(dataContainer, ctrl.getFormFooterController());
		this.ctrl = ctrl;

		titlebar = new Titlebar(ctrl.getTitlebarController());
		init();
	}

	private void init() {
		getLayout().setStyleName("inner-form");
		
		addComponent(titlebar);		
	}



}
