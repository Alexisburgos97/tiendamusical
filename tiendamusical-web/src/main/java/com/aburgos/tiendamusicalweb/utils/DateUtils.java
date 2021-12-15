package com.aburgos.tiendamusicalweb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String convertDateToString(String formato, Date fecha) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		
		String fechaConvert = simpleDateFormat.format(fecha);
		
		return fechaConvert;
	}

}
