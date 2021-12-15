package com.aburgos.tiendamusicalweb.converters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.aburgos.tiendamusicalweb.utils.DateUtils;

@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return LocalDateTime.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		LocalDateTime fecha = (LocalDateTime) value;
		
		Date fechaPersonalizada = Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
		
		return DateUtils.convertDateToString("dd/MM/yyyy hh:mm:ss", fechaPersonalizada);
	}

}
