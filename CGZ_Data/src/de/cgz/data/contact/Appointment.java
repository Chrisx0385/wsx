package de.cgz.data.contact;

import de.cgz.data.types.time.PointOfTime;


public class Appointment extends AbstractContactData {
	
	private PointOfTime appointedTimeBegin;
	
	private PointOfTime appointedTimeEnd;
	
	private String type;
	
	private String title;
	
	private Notice description;


}
