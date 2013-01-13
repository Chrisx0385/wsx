package de.cgz.vaadin;

import java.lang.reflect.Method;

import com.vaadin.data.util.AbstractProperty;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.data.util.MethodProperty.MethodException;

import de.cgz.util.Converter;
import de.cgz.util.Converters;

public class ExtendedMethodProperty<T> extends AbstractProperty {

	private static final long serialVersionUID = 1L;

	private final Class<? extends T> type;
	private final Object instance;
	private final Method getMethod;
	private final Method setMethod;
	private final MethodProperty<T> methodProperty;

	public ExtendedMethodProperty(Class<? extends T> type, Object instance, Method getMethod, Method setMethod) {
		this.type = type;
		this.instance = instance;
		this.getMethod = getMethod;
		this.setMethod = setMethod;
		methodProperty = new MethodProperty<T>(type, instance, getMethod, setMethod);

	}

	/**
	 * Gets the value stored in the Property. The value is resolved by calling
	 * the specified getter method with the argument specified at instantiation.
	 * 
	 * @return the value of the Property
	 */
	public Object getValue() {
		try {			
			Object value = getMethod.invoke(instance);
			if(value == null) {
				return null;
			}
			if(! (value instanceof CharSequence)) {
				@SuppressWarnings("unchecked")
				Converter<Object, ? extends String> converter = (Converter<Object, ? extends String>) Converters.getInstance().getConverter(value.getClass(), String.class);
				if(converter != null) {
					value = converter.convert(value);
				}
			}
			return value;
		} catch (final Throwable e) {
			throw new MethodException(this, e);
		}
	}

	public void setValue(Object newValue) throws ReadOnlyException, ConversionException {

		try {
			methodProperty.setValue(newValue);
			return;
		} catch (ConversionException e1) {
			// fall through
		}

		try {
			setMethod.invoke(instance, Converters.getInstance().convert(newValue, type));
		} catch (Exception e) {
			throw new ConversionException(e);
		}

	}

	public Class<?> getType() {
		return type;
	}

}
