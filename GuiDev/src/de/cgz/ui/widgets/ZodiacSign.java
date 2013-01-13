package de.cgz.ui.widgets;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

import de.cgz.data.types.Zodiac;


public class ZodiacSign extends Embedded {	
	
	private static final Map<Zodiac, ThemeResource> picMap = new HashMap<Zodiac, ThemeResource>();
	
	static {
		picMap.put(Zodiac.Fische, new ThemeResource("images/zodiac/Fische.png"));
		picMap.put(Zodiac.Jungfrau, new ThemeResource("images/zodiac/Jungfrau.png"));
		picMap.put(Zodiac.Krebs, new ThemeResource("images/zodiac/Krebs.png"));
		picMap.put(Zodiac.Loewe, new ThemeResource("images/zodiac/Loewe.png"));
		picMap.put(Zodiac.Schuetze, new ThemeResource("images/zodiac/Schuetze.png"));
		picMap.put(Zodiac.Skorpion, new ThemeResource("images/zodiac/Skorpion.png"));
		picMap.put(Zodiac.Steinbock, new ThemeResource("images/zodiac/Steinbock.png"));
		picMap.put(Zodiac.Stier, new ThemeResource("images/zodiac/Stier.png"));
		picMap.put(Zodiac.Waage, new ThemeResource("images/zodiac/Waage.png"));
		picMap.put(Zodiac.Wassermann, new ThemeResource("images/zodiac/Wassermann.png"));
		picMap.put(Zodiac.Widder, new ThemeResource("images/zodiac/Widder.png"));
		picMap.put(Zodiac.Zwillinge, new ThemeResource("images/zodiac/Zwillinge.png"));		
		picMap.put(Zodiac.UNDEFINED, new ThemeResource("images/nothing.png"));		
	}
	
	private int current = 0;
	
	public ZodiacSign() {		
		this.setImmediate(false);
		this.setWidth("30px");
		this.setHeight("30px");
		this.setType(1);
		this.setMimeType("image/png");
		setZodiac(Zodiac.UNDEFINED);
		addListener(new ClickListener() {			
			public void click(ClickEvent event) {
				current++;
				setZodiac(Zodiac.values()[current % 12]);
			}
		});
	}
	
	public void setZodiac(Zodiac z) {
		this.setSource(picMap.get(z));
	}
	
	

}
