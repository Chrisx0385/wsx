package de.cgz.ctrl;

import de.cgz.data.types.DataObject;
import de.cgz.ui.widgets.InnerForm;



public interface InnerFormController {
	
	TitlebarController getTitlebarController();
	
	FormFooterController getFormFooterController();
	
	void setInnerForm(InnerForm<? extends DataObject> form);
}
