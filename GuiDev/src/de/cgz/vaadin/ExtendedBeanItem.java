package de.cgz.vaadin;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.data.Property;
import com.vaadin.data.util.PropertysetItem;

public class ExtendedBeanItem<BT> extends PropertysetItem {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The bean which this Item is based on.
	 */
	private final BT bean;

	/**
	 * <p>
	 * Creates a new instance of <code>BeanItem</code> and adds all properties
	 * of a Java Bean to it. The properties are identified by their respective
	 * bean names.
	 * </p>
	 * 
	 * <p>
	 * Note : This version only supports introspectable bean properties and
	 * their getter and setter methods. Stand-alone <code>is</code> and
	 * <code>are</code> methods are not supported.
	 * </p>
	 * 
	 * @param bean
	 *            the Java Bean to copy properties from.
	 * 
	 */
	public ExtendedBeanItem(BT bean) {
		this(bean, getProperties(bean));
	}

	/**
	 * <p>
	 * Creates a new instance of <code>BeanItem</code> using a pre-computed set
	 * of properties. The properties are identified by their respective bean
	 * names.
	 * </p>
	 * 
	 * @param bean
	 *            the Java Bean to copy properties from.
	 * @param properties
	 *            pre-computed property descriptors
	 */
	ExtendedBeanItem(BT bean, Map<String, Property> properties) {

		this.bean = bean;

		for (String pId : properties.keySet()) {
			addItemProperty(pId, properties.get(pId));
		}
	}

	/**
	 * <p>
	 * Creates a new instance of <code>BeanItem</code> and adds all listed
	 * properties of a Java Bean to it - in specified order. The properties are
	 * identified by their respective bean names.
	 * </p>
	 * 
	 * <p>
	 * Note : This version only supports introspectable bean properties and
	 * their getter and setter methods. Stand-alone <code>is</code> and
	 * <code>are</code> methods are not supported.
	 * </p>
	 * 
	 * @param bean
	 *            the Java Bean to copy properties from.
	 * @param propertyIds
	 *            id of the property.
	 */
	public ExtendedBeanItem(BT bean, Collection<?> propertyIds) {

		this.bean = bean;

		// Create bean information
		LinkedHashMap<String, Property> pds = getProperties(bean);

		// Add all the bean properties as MethodProperties to this Item
		for (Object id : propertyIds) {
			Property prop = pds.get(id);
			if (prop != null) {
				addItemProperty(id, prop);
			}
		}

	}

	/**
	 * <p>
	 * Creates a new instance of <code>BeanItem</code> and adds all listed
	 * properties of a Java Bean to it - in specified order. The properties are
	 * identified by their respective bean names.
	 * </p>
	 * 
	 * <p>
	 * Note : This version only supports introspectable bean properties and
	 * their getter and setter methods. Stand-alone <code>is</code> and
	 * <code>are</code> methods are not supported.
	 * </p>
	 * 
	 * @param bean
	 *            the Java Bean to copy properties from.
	 * @param propertyIds
	 *            ids of the properties.
	 */
	public ExtendedBeanItem(BT bean, String[] propertyIds) {
		this(bean, Arrays.asList(propertyIds));
	}

	/**
	 * <p>
	 * Perform introspection on a Java Bean class to find its properties.
	 * </p>
	 * 
	 * <p>
	 * Note : This version only supports introspectable bean properties and
	 * their getter and setter methods. Stand-alone <code>is</code> and
	 * <code>are</code> methods are not supported.
	 * </p>
	 * 
	 * @param beanClass
	 *            the Java Bean class to get properties for.
	 * @return an ordered map from property names to property descriptors
	 */
	private static <BT> LinkedHashMap<String, Property> getProperties(BT bean) {
		final LinkedHashMap<String, Property> pdMap = new LinkedHashMap<String, Property>();

		// Try to introspect, if it fails, we just have an empty Item
		try {
			List<PropertyDescriptor> propertyDescriptors = getBeanPropertyDescriptor(bean.getClass());

			// Add all the bean properties as MethodProperties to this Item
			// later entries on the list overwrite earlier ones
			for (PropertyDescriptor pd : propertyDescriptors) {
				final Method getMethod = pd.getReadMethod();
				if ((getMethod != null) && getMethod.getDeclaringClass() != Object.class) {
					@SuppressWarnings("rawtypes")
					ExtendedMethodProperty prop = new ExtendedMethodProperty(pd.getReadMethod().getReturnType(), bean, pd.getReadMethod(), pd.getWriteMethod());
					pdMap.put(pd.getName(), prop);
				}
			}
		} catch (final java.beans.IntrospectionException ignored) {}

		return pdMap;
	}

	/**
	 * Returns the property descriptors of a class or an interface.
	 * 
	 * For an interface, superinterfaces are also iterated as Introspector does
	 * not take them into account (Oracle Java bug 4275879), but in that case,
	 * both the setter and the getter for a property must be in the same
	 * interface and should not be overridden in subinterfaces for the discovery
	 * to work correctly.
	 * 
	 * For interfaces, the iteration is depth first and the properties of
	 * superinterfaces are returned before those of their subinterfaces.
	 * 
	 * @param beanClass
	 * @return
	 * @throws IntrospectionException
	 */
	private static List<PropertyDescriptor> getBeanPropertyDescriptor(final Class<?> beanClass) throws IntrospectionException {
		// Oracle bug 4275879: Introspector does not consider superinterfaces of
		// an interface
		if (beanClass.isInterface()) {
			List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>();

			for (Class<?> cls : beanClass.getInterfaces()) {
				propertyDescriptors.addAll(getBeanPropertyDescriptor(cls));
			}

			BeanInfo info = Introspector.getBeanInfo(beanClass);
			propertyDescriptors.addAll(Arrays.asList(info.getPropertyDescriptors()));

			return propertyDescriptors;
		} else {
			BeanInfo info = Introspector.getBeanInfo(beanClass);
			return Arrays.asList(info.getPropertyDescriptors());
		}
	}

	/**
	 * Gets the underlying JavaBean object.
	 * 
	 * @return the bean object.
	 */
	public BT getBean() {
		return bean;
	}

}
