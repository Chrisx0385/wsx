package de.cgz.ui;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

import de.cgz.data.types.Gender;
import de.cgz.data.types.Zodiac;


public class GenderSign extends Embedded {	
	
	private static final Map<Gender, ThemeResource> picMap = new HashMap<Gender, ThemeResource>();
	
	static {
		picMap.put(Gender.MALE, new ThemeResource("images/gender/male.png"));
		picMap.put(Gender.FEMALE, new ThemeResource("images/gender/female.png"));
		picMap.put(Gender.UNDEFINED, new ThemeResource("images/gender/undefined.png"));
	}
	
	private int current = 0;
	
	public GenderSign() {		
		this.setImmediate(false);
		this.setWidth("30px");
		this.setHeight("30px");
		this.setSource(picMap.get(Gender.UNDEFINED));
		this.setType(1);
		this.setMimeType("image/png");
		addListener(new ClickListener() {			
			public void click(ClickEvent event) {
				current++;
				setGender(Gender.values()[current % 3]);
			}
		});
	}
	
	public void setGender(Gender g) {
		setSource(picMap.get(g));
	}
	
	

}
