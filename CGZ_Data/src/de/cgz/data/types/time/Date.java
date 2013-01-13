package de.cgz.data.types.time;

import java.util.Calendar;


public interface Date extends Time {
	

	Calendar toCalendar();

	Day getDay();
	Month getMonth();
	Year getYear();
}
