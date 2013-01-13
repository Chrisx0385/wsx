package de.cgz.util;


public interface Converter<TS, TD> {
	TD convert(TS src);
}
