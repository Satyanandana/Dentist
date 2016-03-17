package com.dentist.util;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.io.IOException;
import java.util.Arrays;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

@Service
@PropertySource(value = { "classpath:application.properties" })
public class CalendarEventHandler {

	@Autowired
	@Qualifier("googleCalendar")
	private Calendar calendar;
	@Autowired
	private Environment environment;
	
	
	public CalendarEventHandler() {
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Event insertFakeEvent(org.joda.time.DateTime startDate) {
		org.joda.time.DateTime createdDate = new org.joda.time.DateTime(DateTimeZone.forID("America/New_York"));
		EventDateTime start = getStartDate(startDate);
		EventDateTime end = getEndDate(startDate);
		Event event = new Event().setSummary(getFakeEventSummary())
				.setLocation(getEventLocation())
				.setDescription(getFakeEventDescription())
				.setCreated(new DateTime(createdDate.toDate()))
				.setStart(start)
				.setEnd(end);
		
			try {
				event = calendar.events().insert(getFakeCalendarId(), event).setSendNotifications(true)
						                                               .execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return event;
	}
	
	public Event insertActualEvent(org.joda.time.DateTime startDate,String attendeeEmail ) {
		org.joda.time.DateTime createdDate = new org.joda.time.DateTime(DateTimeZone.forID("America/New_York"));
		EventDateTime start = getStartDate(startDate);
		EventDateTime end = getEndDate(startDate);
		EventReminder[] reminderOverrides = new EventReminder[] { 
				new EventReminder().setMethod("email").setMinutes(60),
				new EventReminder().setMethod("popup").setMinutes(60),
				new EventReminder().setMethod("email").setMinutes(24 * 60),
				new EventReminder().setMethod("popup").setMinutes(24 * 60),
				new EventReminder().setMethod("popup").setMinutes(10) };
		Event.Reminders reminders = new Event.Reminders().setUseDefault(false)
				.setOverrides(Arrays.asList(reminderOverrides));
		
		EventAttendee[] attendees = new EventAttendee[] { new EventAttendee().setEmail(attendeeEmail)};
		
		
		
		Event event = new Event().setSummary(getActualEventSummary())
				                 .setAttendees(Arrays.asList(attendees))
				                 .setLocation(getEventLocation())
				                 .setDescription(getActualEventDescription())
				                 .setCreated(new DateTime(createdDate.toDate()))
				                 .setReminders(reminders)
				                 .setStart(start)
				                 .setEnd(end);
		    System.out.println(getActualCalendarId());
			try {
				event = calendar.events().insert(getActualCalendarId(), event).setSendNotifications(true).execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return event;
	}
	

	

	public EventDateTime getStartDate(org.joda.time.DateTime startDate) {

		DateTime startDateTime = new DateTime(startDate.toDate());
		EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/New_York");
		return start;
	}

	public EventDateTime getEndDate(org.joda.time.DateTime startDate) {
		startDate = startDate.plusHours(2);
		DateTime startDateTime = new DateTime(startDate.toDate());
		EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/New_York");
		return start;
	}

	public String getFakeCalendarId() {
		return  environment.getRequiredProperty("google.fake.calendarid").trim();
	}

	public String getActualCalendarId() {
		return  environment.getRequiredProperty("google.actual.calendarid").trim();
	}

	public String getFakeEventSummary() {
		return  environment.getRequiredProperty("google.calendar.fakeevent.summary").trim();
	}

	public String getActualEventSummary() {
		return  environment.getRequiredProperty("google.calendar.actualevent.summary").trim();
	}

	public String getEventLocation() {
		return  environment.getRequiredProperty("google.calendar.event.location").trim();
	}

	public String getFakeEventDescription() {
		return  environment.getRequiredProperty("google.calendar.fakeevent.description").trim();
	}

	public String getActualEventDescription() {
		return  environment.getRequiredProperty("google.calendar.actualevent.description").trim();
	}

	public String getWebsiteDomain() {
		return  environment.getRequiredProperty("website.domain").trim();
	}
	
	

}
