package de.cgz.data.types;


public enum Zodiac implements DataObject {

	Wassermann (21, 1 , 19, 2),
	Fische (20, 2 , 20, 3),
	Widder (21, 3 , 20,  4),
	Stier (21, 4 , 20 , 5),
	Zwillinge (21, 5 , 21 , 6),
	Krebs (22, 6 ,  22, 7),
	Loewe (23, 7 , 23, 8),
	Jungfrau (24, 8 , 23 , 9),
	Waage (24, 9 , 23, 10),
	Skorpion (24, 10 , 22, 11),
	Schuetze (23, 11 , 21, 12),
	Steinbock (22, 12 , 20, 1),
	UNDEFINED(0,0,0,0);

	private final int fday;
	private final int fmonth;
	private final int umonth;
	private final int uday;
	

	Zodiac(int fday, int fmonth, int uday, int umonth) {
		this.fday = fday;
		this.fmonth = fmonth;
		this.uday = uday;
		this.umonth = umonth;
	}
	
	public static Zodiac getZodiac(int day, int month) {
		for(Zodiac z : values()) {
			if(z.fday <= day && z.fmonth <= month && z.uday >= day && z.umonth >= month) {
				return z;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%02d.%02d. - %02d.%02d.)", super.toString(), fday, fmonth, uday, umonth);
	}
		
	
	
	public static void main(String[] args) {
		System.out.println(Steinbock);
	}

	public boolean isEmpty() {
		return this != UNDEFINED;
	}

	public boolean isValid() {
		return true;
	}
}
