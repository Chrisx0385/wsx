package de.cgz.ui.widgets;

import de.cgz.ctrl.InnerFormController;
import de.cgz.data.types.DataObject;
import de.cgz.data.types.collection.container.ListDataContainer;
import de.cgz.data.ui.DisplayMode;


public class InnerForm<T extends DataObject> extends DataObjectForm<T> {

	private static final long serialVersionUID = -259964841386557341L;

	private final InnerFormController ctrl;	
	
	private final Titlebar titlebar;
		

	public InnerForm(InnerFormController ctrl, ListDataContainer<T> dataContainer, DataObjectForm<? extends DataObject> parent) {
		super(dataContainer, parent, ctrl.getFormFooterController());
		ctrl.setInnerForm(this);
		this.ctrl = ctrl;
		
		titlebar = new Titlebar(ctrl.getTitlebarController());
		init();
	}

	protected void init() {	
		super.init();
		setStyleName("inner-form");
		getInnerForm().setStyleName("inner-form-form");
		getInnerForm().setWidth("250px");
		
		addComponentAsFirst(titlebar);	
		titlebar.setWidth("250px");
	}

	@Override
	public void setDisplayMode(DisplayMode mode) {
		super.setDisplayMode(mode);
	}

	public void hide(boolean hide) {
		getInnerForm().setVisible(hide);		
	}
	public boolean isHidden() {
		return getInnerForm().isVisible();
	}

}
