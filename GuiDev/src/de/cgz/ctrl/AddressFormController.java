package de.cgz.ctrl;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import de.cgz.data.types.DataObject;
import de.cgz.ui.widgets.InnerForm;

@SuppressWarnings("serial")
public class AddressFormController implements InnerFormController {
	
	private final TitlebarController titlebarController = new TitlebarController() {		
		
		private final ClickListener closeButtonListener = new ClickListener() {		
			public void buttonClick(ClickEvent event) {
				
			}
		};
		
		private final ClickListener hideButtonListener = new ClickListener() {		
			public void buttonClick(ClickEvent event) {		
				getInnerForm().hide(!getInnerForm().isHidden());
			}
		};
		
		public ClickListener getCloseButtonListener() {
			return closeButtonListener;
		}

		public ClickListener getHideButtonListener() {
			return hideButtonListener;
		}

	};

	private InnerForm<? extends DataObject> form;


	public TitlebarController getTitlebarController() {
		return titlebarController;
	}

	public FormFooterController getFormFooterController() {
		return null;
	}

	public void setInnerForm(InnerForm<? extends DataObject> form) {
		this.form = form;				
	}

	protected InnerForm<? extends DataObject> getInnerForm() {
		return form;
	}

	
	
	

}
