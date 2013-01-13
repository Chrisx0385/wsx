package de.cgz.data.types.time;

import java.util.Calendar;

import de.cgz.data.types.AbstractDataObject;
import de.cgz.data.types.collection.range.Range;

public class DayImpl extends AbstractDataObject implements Day{

	private final static Range<Integer> VALID_DAY_OF_MONTHS = factory().createDiscreteRange(1, 31);

	private final int dayOfMonth;
	private final DayOfWeek dayOfWeek;

	public static enum DayOfWeek {
		MONDAY(Calendar.MONDAY), 
		TUESDAY(Calendar.TUESDAY), 
		WEDNESDAY(Calendar.WEDNESDAY), 
		THURSDAY(Calendar.THURSDAY), 
		FRIDAY(Calendar.FRIDAY), 
		SATURDAY(Calendar.SATURDAY), 
		SUNDAY(Calendar.SUNDAY),
		UNDEFINED(-1);

		private final int CALENDAR_DAY_OF_WEEK_CONSTANT;

		private DayOfWeek(final int CALENDAR_DAY_OF_WEEK_CONSTANT) {
			this.CALENDAR_DAY_OF_WEEK_CONSTANT = CALENDAR_DAY_OF_WEEK_CONSTANT;
		}

		public static DayOfWeek getFromCalendar(Calendar c) {			
			for(DayOfWeek value : values()) {
				if(value.CALENDAR_DAY_OF_WEEK_CONSTANT == c.get(Calendar.DAY_OF_WEEK)) return value;
			}
			return null; //never happens
		}
	}

	public DayImpl(int dayOfMonth, DayOfWeek dayOfWeek) {
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
		validate();
	}
	
	public DayImpl(int dayOfMonth) {
		this(dayOfMonth, DayOfWeek.UNDEFINED);
	}

	public DayImpl(Calendar c) {		
		this(c.get(Calendar.DAY_OF_MONTH), DayOfWeek.getFromCalendar(c));
	}

	public boolean isValid() {
		return super.isValid() && VALID_DAY_OF_MONTHS.contains(dayOfMonth) && dayOfWeek != null;
	}

	public boolean isEmpty() {
		return false;
	}
	
	@Override
	protected void doValidate() {
		super.doValidate();
	}

}
