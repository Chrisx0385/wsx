package de.cgz.ui.widgets;

import java.util.Arrays;

import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import de.cgz.ctrl.FormFooterController;
import de.cgz.data.types.DataObject;
import de.cgz.data.types.Descriptor;
import de.cgz.data.types.Statement;
import de.cgz.data.types.TypeFactory;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.container.DataContainer;
import de.cgz.data.ui.DisplayMode;
import de.cgz.ui.data.descriptors.UIDataDescriptors;
import de.cgz.vaadin.ExtendedBeanItem;


@SuppressWarnings("serial")
public class DataObjectForm<T extends DataObject> extends Form {

	
	private final Descriptor<T> descriptor;
	private final DataContainer<T> dataContainer;
	private T dataObject;
	
	@SuppressWarnings({ "unchecked" })
	private final ListDataCollection<DataObjectForm<? extends DataObject>> childForms = (ListDataCollection<DataObjectForm<? extends DataObject>>) TypeFactory.getInstance().createListDataCollection();
	private DataObjectForm<? extends DataObject> parentForm;
	
	private FormFooter formFooter;
		
	public DataObjectForm(DataContainer<T> dataContainer, Descriptor<T> descriptor, DataObjectForm<? extends DataObject> parentForm, FormFooterController ctrl) {
		this.dataContainer = dataContainer;
		this.descriptor = descriptor;
		this.parentForm = parentForm;
		if(ctrl != null) {
			formFooter = new FormFooter(ctrl);
			setFooter(formFooter);
		}
		init();
	}
	
	public DataObjectForm(DataContainer<T> dataContainer, DataObjectForm<? extends DataObject> parent, FormFooterController ctrl) {
		this(dataContainer, UIDataDescriptors.getInstance().getDescriptor(dataContainer.getType()), parent, ctrl);
	}
	
	public DataObjectForm(DataContainer<T> dataContainer, FormFooterController ctrl) {
		this(dataContainer, null, ctrl);
	}
	
	public DataObjectForm(DataContainer<T> dataContainer) {
		this(dataContainer, null, null);
	}
	
	
	
	private void init() {
		this.setLayout(new VerticalLayout());
		
		setWriteThrough(false);
		setFormFieldFactory(createFieldFactory());
	}
	
	public void setFormFooter(FormFooter formFooter) {
		this.formFooter = formFooter;
		super.setFooter(formFooter);
	}
	
	public Layout getFormFooter() {
		return formFooter;
	}

	protected FormFieldFactory createFieldFactory() {
		return new DefaultFieldFactory() {
			@Override
			public Field createField(Item item, Object propertyId, Component uiContext) {
				return super.createField(item, propertyId, uiContext);
			}
		};
	}

	public void addComponent(Component c) {
		getLayout().addComponent(c);
	}

	
	protected Descriptor<T> getDescriptor() {
		return descriptor;
	}

	
	protected DataContainer<T> getDataContainer() {
		return dataContainer;
	}

	
	protected T getDataObject() {
		return dataObject;
	}

	public void setDisplayMode(DisplayMode mode) {
		dataObject = dataContainer.getSelectedDataObject();
		switch (mode) {
			case EDIT:
				setReadOnly(false);		
			break;
			case CREATE:
				setReadOnly(false);	
				dataObject = dataContainer.createDataObject();
			break;
			case DISPLAY:
				setReadOnly(true);	
			break;
		}
		setItemDataSource(new ExtendedBeanItem<T>(dataObject), Arrays.asList(descriptor.getProperties(mode)));
	}

	@Override
	public void commit() throws SourceException, InvalidValueException {
		getChildForms().forEach(new Statement<DataObjectForm<? extends DataObject>>() {			
			public Object execute(DataObjectForm<? extends DataObject> element, int index) {				
				element.commit();
				return null;
			}
		});
		super.commit();
	}

	public void addChildForm(DataObjectForm<? extends DataObject> childForm) {
		childForm.setParent(this);
		getChildForms().add(childForm);
	}

	public ListDataCollection<DataObjectForm<? extends DataObject>> getChildForms() {
		return childForms;
	}

	public DataObjectForm<? extends DataObject> getParentForm() {
		return parentForm;
	}
	protected void setParentForm(DataObjectForm<? extends DataObject> parentForm) {
		this.parentForm = parentForm;
	}

	public DataObjectForm<? extends DataObject> getRootForm() {
		return isRootForm() ? this : getParentForm().getRootForm();
	}

	public boolean isRootForm() {
		return getParentForm() == null;
	}
	
	
	
}
