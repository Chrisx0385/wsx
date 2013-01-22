package de.cgz.ui.widgets;

import com.vaadin.data.Item;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

import de.cgz.ctrl.FormFooterController;
import de.cgz.data.types.DataObject;
import de.cgz.data.types.Descriptor;
import de.cgz.data.types.Statement;
import de.cgz.data.types.TypeFactory;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.container.DataContainer;
import de.cgz.data.types.collection.container.ListDataContainer;
import de.cgz.data.ui.DisplayMode;
import de.cgz.data.utils.DataUtils;
import de.cgz.ui.data.descriptors.UIDataDescriptors;


@SuppressWarnings("serial")
public class DataObjectForm<T extends DataObject> extends VerticalLayout {

	protected final static DataUtils utils() {
		return DataUtils.getInstance();
	}
	protected static TypeFactory factory() {
		return TypeFactory.getInstance();
	}

	private final Form innerForm = new Form();

	private final Descriptor<T> descriptor;
	private final ListDataContainer<T> dataContainer;

	@SuppressWarnings({ "unchecked" })
	private final ListDataCollection<DataObjectForm<? extends DataObject>> childForms = (ListDataCollection<DataObjectForm<? extends DataObject>>) TypeFactory.getInstance().createListDataCollection();
	private DataObjectForm<? extends DataObject> parentForm;

	private FormFooter formFooter;
	private DisplayMode displayMode = DisplayMode.DISPLAY;

	public DataObjectForm(ListDataContainer<T> dataContainer, Descriptor<T> descriptor, DataObjectForm<? extends DataObject> parentForm, FormFooterController ctrl) {
		this.dataContainer = dataContainer;
		this.descriptor = descriptor;
		this.parentForm = parentForm;
		dataContainer.setSelectedDataObject(dataContainer.createDataObject());

		getInnerForm().setWriteThrough(false);

		if(ctrl != null) {
			setFooter(new FormFooter(ctrl));
		}
		getInnerForm().setFormFieldFactory(new DefaultFieldFactory() {
			@Override
			public Field createField(Item item, Object propertyId, Component uiContext) {
				Field field = super.createField(item, propertyId, uiContext);
				field.setReadOnly(uiContext.isReadOnly());
				return field;
			}
		});
	}


	public DataObjectForm(ListDataContainer<T> dataContainer, DataObjectForm<? extends DataObject> parent, FormFooterController ctrl) {
		this(dataContainer, UIDataDescriptors.getInstance().getDescriptor(dataContainer.getType()), parent, ctrl);
	}

	public DataObjectForm(ListDataContainer<T> dataContainer, FormFooterController ctrl) {
		this(dataContainer, null, ctrl);
	}

	public DataObjectForm(ListDataContainer<T> dataContainer) {
		this(dataContainer, null, null);
	}

	protected void init() {
		getInnerForm().getLayout().setMargin(true);
		addComponent(getInnerForm());

	}


	public FormFooter getFooter() {
		return formFooter;
	}
	public void setFooter(FormFooter formFooter) {
		this.formFooter = formFooter;
		getInnerForm().setFooter(formFooter);
	}

	public Descriptor<T> getDescriptor() {
		return descriptor;
	}


	public DataContainer<T> getDataContainer() {
		return dataContainer;
	}


	public T getDataObject() {
		return dataContainer.getSelectedDataObject();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		//super.setReadOnly(readOnly);
		getInnerForm().setReadOnly(readOnly);
	}

	public void setDisplayMode(final DisplayMode mode) {
		this.displayMode = mode;
		switch (mode) {
			case EDIT:
				setReadOnly(false);
			break;
			case CREATE:
				setReadOnly(false);
				setDataObject(dataContainer.createDataObject());
			break;
			case DISPLAY:
				setReadOnly(true);
			break;
		}
		getInnerForm().setItemDataSource(new BeanItem<T>(getDataObject()), descriptor.getProperties(mode).toList());
		childForms.forEach(new Statement<DataObjectForm<? extends DataObject>>() {
			public Object execute(DataObjectForm<? extends DataObject> element, int index) {
				element.setDisplayMode(mode);
				return null;
			}});
		if(getFooter() != null) {
			getFooter().setDisplayMode(getDisplayMode());
		}
	}


	public void commit() throws SourceException, InvalidValueException {
		getChildForms().forEach(new Statement<DataObjectForm<? extends DataObject>>() {
			public Object execute(DataObjectForm<? extends DataObject> element, int index) {
				element.commit();
				return null;
			}
		});
		getInnerForm().commit();
	}

	public void addChildForm(DataObjectForm<? extends DataObject> childForm) {
		childForm.setParentForm(this);
		addComponent(childForm);
		getChildForms().add(childForm);
	}

	public void removeChilds() {
		for(DataObjectForm<? extends DataObject> childForm : childForms) {
			childForm.removeParentForm();
			removeComponent(childForm);
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
		removeComponent(childForm);
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
		dataContainer.setSelectedDataObject(dataObject);
		dataObjectChanged(dataObject);
	}

	protected void dataObjectChanged(T dataObject) {
		//Hook
	}
	public Form getInnerForm() {
		return innerForm;
	}



}
