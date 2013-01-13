package de.cgz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Converters {
	
	private static Converters instance;

	public static Converters getInstance() {
		return instance == null ? (instance = new Converters()) : instance;
	}

	private Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converters = new HashMap<Class<?>, Map<Class<?>,Converter<?,?>>>();
	
	private Converters() {
		registerConverters();
	}
	

	private <TS, TD> void register(Class<TS> src, Class<TD> dest, Converter<TS, TD> converter) {
		if(!converters.containsKey(src)) {
			converters.put(src, new HashMap<Class<?>, Converter<?,?>>());
		}
		converters.get(src).put(dest, converter);
	}

	private void registerConverters() {
		register(String.class, Calendar.class, new Converter<String, Calendar>() {
			public Calendar convert(String src) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				try {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(sdf.parse(src));					
					return calendar;
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}			
		});
		register(Calendar.class, String.class, new Converter<Calendar, String>() {
			public String convert(Calendar src) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
					return sdf.format(src.getTime());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public <TS, TD> Converter<? super TS, ? extends TD> getConverter(Class<TS> src, Class<TD> dest) {
		Map<Class<?>, Converter<?, ?>> map = converters.get(src);
		if(map == null) {
			for(Class<?> srcType : converters.keySet()) {
				if(srcType.isAssignableFrom(src)) {
					map = converters.get(srcType);
					break;
				}
			}
		}
		if(map == null) {
			return null;
		}
		Converter<?, ?> converter = map.get(dest);
		if(converter == null) {
			for(Class<?> destType : map.keySet()) {
				if(dest.isAssignableFrom(destType)) {
					converter = map.get(destType);
					break;
				}
			}			
		}
		return (Converter<? super TS, ? extends TD>) converter;
	}
	
	public <TD, TS> TD convert(TS src, Class<TD> destType) {
		@SuppressWarnings("unchecked")
		Converter<Object, ? extends TD> converter = (Converter<Object, ? extends TD>) getConverter(src.getClass(), destType);
		if(converter == null) {
			throw new UnsupportedOperationException(String.format("Can't convert from '%s' to '%s'.", src.getClass(), destType.getClass()));
		}
		return converter.convert(src);
	}
}
