package de.cgz.data.types.time;

import java.util.Calendar;

import de.cgz.data.types.AbstractDataObject;

public class MonthImpl extends AbstractDataObject implements Month{
	
	public static enum MonthOfYear {
		JANUARY(1),
		FEBRUARY(2),
		MARC(3),
		APRIL(4),
		MAY(5),
		JUNE(6),
		JULY(7),
		AUGUST(8),
		SEPTEMBER(9),
		OCTOBER(10),
		NOVEMBER(11),
		DECEMBER(12);
		
		private final int number;

		private MonthOfYear(int number) {
			this.number = number;			
		}
		
		public static MonthOfYear getFromCalendar(Calendar c) {			
			return values()[c.get(Calendar.MONTH)];
		}
		
		public static MonthOfYear getByNumber(int month) {
			return values()[month-1];
		}		
		
		public int getNumber() {
			return number;
		}
	}
	
	private final MonthOfYear monthOfYear;

	public MonthImpl(MonthOfYear monthOfYear) {
		this.monthOfYear = monthOfYear;
		validate();
	}
	
	public MonthImpl(Calendar c) {
		this(MonthOfYear.getFromCalendar(c));
	}
	
	@Override
	public boolean isValid() {
		return super.isValid() && monthOfYear != null;
	}

}
