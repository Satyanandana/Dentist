package com.dentist.googlecalendar;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Mar 23, 20163:30:37 AM
 * @git
 * 
 */
public class CustomJodaDateTimeSerializer extends JsonSerializer<DateTime> {

	DateTimeFormatter format = DateTimeFormat.forPattern("MM-dd-yyyy : HH:mm");

	@Override
	public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {

		if (value == null) {
			gen.writeNull();
		} else {
			gen.writeString(format.print(value));
		}

	}

}
