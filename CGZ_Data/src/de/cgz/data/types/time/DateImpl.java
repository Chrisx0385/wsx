package de.cgz.data.types.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateImpl extends AbstractDate {
	

	
	
	private final Day day;
	private final Month month;
	private final Year year;
	
	public DateImpl(Day day, Month month, Year year) {
		this.day = day;
		this.month = month;
		this.year = year;
		validate();
	}
	
	public DateImpl(Calendar c) {
		this(new DayImpl(c), new MonthImpl(c), new YearImpl(c));
	}
	
	@Override
	public boolean isValid() {
		return super.isValid() && day != null && month != null && year != null;
	}


	public Day getDay() {		
		return day;
	}

	public Month getMonth() {
		return month;
	}

	public Year getYear() {
		return year;
	}

	@Override
	public Calendar toCalendar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
