package de.cgz.ui.widgets;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;
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
import de.cgz.data.utils.DataUtils;
import de.cgz.ui.data.descriptors.UIDataDescriptors;
import de.cgz.vaadin.ExtendedBeanItem;


@SuppressWarnings("serial")
public class DataObjectForm<T extends DataObject> extends Form {

	protected final static DataUtils utils() {
		return DataUtils.getInstance();
	}
	protected static TypeFactory factory() {
		return TypeFactory.getInstance();
	}
	
	private final Descriptor<T> descriptor;
	private final DataContainer<T> dataContainer;
	private T dataObject;
	
	@SuppressWarnings({ "unchecked" })
	private final ListDataCollection<DataObjectForm<? extends DataObject>> childForms = (ListDataCollection<DataObjectForm<? extends DataObject>>) TypeFactory.getInstance().createListDataCollection();
	private DataObjectForm<? extends DataObject> parentForm;
	
	private FormFooter formFooter;
	private DisplayMode displayMode = DisplayMode.DISPLAY;
		
	public DataObjectForm(DataContainer<T> dataContainer, Descriptor<T> descriptor, DataObjectForm<? extends DataObject> parentForm, FormFooterController ctrl) {
		this.dataContainer = dataContainer;
		this.descriptor = descriptor;
		this.parentForm = parentForm;
		dataObject = dataContainer.createDataObject();
		
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
	}
	
	public void setFormFooter(FormFooter formFooter) {
		this.formFooter = formFooter;
		super.setFooter(formFooter);
	}
	
	public Layout getFormFooter() {
		return formFooter;
	}



	public void addComponent(Component c) {
		getLayout().addComponent(c);
	}

	
	public Descriptor<T> getDescriptor() {
		return descriptor;
	}

	
	public DataContainer<T> getDataContainer() {
		return dataContainer;
	}

	
	public T getDataObject() {
		return dataObject;
	}

	public void setDisplayMode(DisplayMode mode) {
		this.displayMode = mode;
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
		setItemDataSource(new ExtendedBeanItem<T>(dataObject), descriptor.getProperties(mode).toList());
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
	
	public void removeChilds() {
		for(DataObjectForm<? extends DataObject> childForm : childForms) {
			childForm.removeParentForm();
		}
		childForms.clear();
	}
	
	public void removeChilds(Iterable<DataObjectForm<? extends DataObject>> childForms) {		
		for(DataObjectForm<? extends DataObject> childForm : childForms) {
			removeChild(childForm);
		}
	}
	
	public void removeChild(DataObjectForm<? extends DataObject> childForm) {
		childForms.remove(childForm);
		childForm.removeParentForm();
	}
	
	

	public ListDataCollection<DataObjectForm<? extends DataObject>> getChildForms() {
		return childForms;
	}

	public DataObjectForm<? extends DataObject> getParentForm() {
		return parentForm;
	}
	
	public void setParentForm(DataObjectForm<? extends DataObject> parentForm) {
		this.parentForm = parentForm;
	}
	public void removeParentForm() {
		setParentForm(null);
	}

	public DataObjectForm<? extends DataObject> getRootForm() {
		return isRootForm() ? this : getParentForm().getRootForm();
	}

	public boolean isRootForm() {
		return getParentForm() == null;
	}

	
	public DisplayMode getDisplayMode() {
		return displayMode;
	}
	
	public void setDataObject(T dataObject) {
		this.dataObject = dataObject;
		dataObjectChanged(dataObject);
	}
	
	protected void dataObjectChanged(T dataObject) {
		//Hook
	}
	
	
	
}
