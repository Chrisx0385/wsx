package de.cgz.test;

import de.cgz.data.types.Statement;
import de.cgz.data.types.TypeFactory;


public class Main {
	
	private static String[] array;

	public static void main(String[] args) {
		
		TypeFactory.getInstance().createDiscreteRange(10, 30).forEach(new Statement<Integer>() {			
			public Object execute(Integer element, int index) {		
				System.out.println(element);
				return null;
			}
		});		
	}

}
