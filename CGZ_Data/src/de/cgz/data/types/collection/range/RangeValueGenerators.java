package de.cgz.data.types.collection.range;

import java.util.HashMap;
import java.util.Map;



public final class RangeValueGenerators {
	
	private static RangeValueGenerators instance;

	public static RangeValueGenerators getInstance() {
		return instance == null ? (instance = new RangeValueGenerators()) : instance;
	}
	
	@SuppressWarnings("rawtypes")
	private final Map<Class<?>, RangeValueGenerator> register = new HashMap<Class<?>, RangeValueGenerator>();

	private RangeValueGenerators() {
		registerGenerators();
	}
	
	@SuppressWarnings("rawtypes")
	private void register(Class<?> type, RangeValueGenerator generator) {
		register.put(type, generator);
	}

	private void registerGenerators() {
		register(Long.class, new AbstractRangeValueGenerator<Long>() {
			public Long nextValue(Long current, int n) {
				return current + n;
			}		
		});
		register(Integer.class, new AbstractRangeValueGenerator<Integer>() {
			public Integer nextValue(Integer current, int n) {
				return current + n;
			}			
		});			
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> RangeValueGenerator<T> generator(Class<T> type) {
		return register.get(type);
	}
	
	
	
}
