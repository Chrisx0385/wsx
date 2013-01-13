package de.cgz.data.types.time;

import java.util.Calendar;

import de.cgz.data.types.AbstractDataObject;


public class YearImpl extends AbstractDataObject implements Year {
	
	private final int year;

	public YearImpl(int year) {
		this.year = year;
	}
	
	public YearImpl(Calendar c) {
		this(c.get(Calendar.YEAR));
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isValid() {
		return super.isValid() && year > 0;
	}
	
	@Override
	public String toString() {
		return String.format("%04d", year);
	}
	
}
