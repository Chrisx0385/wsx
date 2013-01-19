package de.cgz.data.utils;

import java.lang.reflect.Method;

public final class ReflectionUtils {

	private static ReflectionUtils instance;

	public static ReflectionUtils getInstance() {
		return instance == null ? (instance = new ReflectionUtils()) : instance;
	}

	private ReflectionUtils() {}

	public Method getGetter(Class<?> type, String property) {
		if (isEmpty(property)) {
			return null;
		}
		String[] getterNames = getGetterNames(type, property);
		Method getter = null;
		for (String getterName : getterNames) {
			try {
				getter = type.getMethod(getterName);
			} catch (Exception e) {
				//fall through
			}
		}	
		if(getter == null) {
			throw new RuntimeException(String.format("Could not find getter for property '%s' of type '%s'", property, type.getName()));
		}
		return getter;
	}

	public String[] getGetterNames(Class<?> type, String property) {
		if (isEmpty(property)) {
			return null;
		}
		String capitializedProperty = StringUtils.getInstance().capitialize(property);
		return new String[] { "get" + capitializedProperty, "is" + capitializedProperty };
	}

	public String getSetterName(String property) {
		if (isEmpty(property)) {
			return null;
		}
		String capitializedProperty = StringUtils.getInstance().capitialize(property);
		return "set" + capitializedProperty;
	}

	public Method getSetter(Class<?> type, String property) {
		if (isEmpty(property)) {
			return null;
		}

		String setterName = getSetterName(property);
		Class<?> propertyType = getPropertyType(type, property);

		Method setter = null;
		try {
			setter = type.getMethod(setterName, propertyType);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Could not find setter for property '%s' of type '%s'", property, type.getName()));
		}
		return setter;
	}

	public Class<?> getPropertyType(Class<?> type, String property) {
		Method getter = getGetter(type, property);
		return getter.getReturnType();
	}

	public Object getPropertyValue(Object object, String property) {
		if (DataUtils.getInstance().isSomethingEmpty(object, property)) {
			return null;
		}
		Method getter = getGetter(object.getClass(), property);
		if (getter != null) {
			try {
				return getter.invoke(object);
			} catch (Exception e) {
				throw new RuntimeException(String.format("Could not determine get value from property '%s' of object '%s'", property, object), e);
			}
		}
		
		return null;
	}

	public void setPropertyValue(Object object, String property, Object value) {
		Method setter = getSetter(object.getClass(), property);
		if (setter != null) {
			try {
				setter.invoke(object, value);
			} catch (Exception e) {
				throw new RuntimeException(String.format("Could not set value '%s' for property '%s' of object '%s'", value, property, object), e);
			}
		}
	}

	private boolean isEmpty(Object o) {
		return DataUtils.getInstance().isEmpty(o);
	}
}
