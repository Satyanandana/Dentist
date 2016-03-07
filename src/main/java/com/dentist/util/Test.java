package com.dentist.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.joda.time.DateTimeZone;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

public class Test {

	public static void main(String[] args) {

		String serverAccountEmail = WebPath.GOOGLE_SERVER_TO_SERVER_SERVICEACCOUNT_EMAIL.getPath();
		ArrayList<String> OuthScopes = new ArrayList<String>();
		OuthScopes.add(CalendarScopes.CALENDAR);
		File privateKeyFileP12 = new File(WebPath.GOOGLE_SERVER_TO_SERVER_P12.getPath());
		GoogleCredential credential = GoogleServerToServer.getGoogleCredential(serverAccountEmail, privateKeyFileP12,
				OuthScopes);
		Calendar calendar = GoogleServerToServer.getCalendar(credential, "dentalappointmentcalander");
		
		String s1 = "Sachin";
		String s2 = "Sachin";

		String s3 = new String("Sachin");
		System.out.println(s1 == s2);// true (because both refer to same
										// instance)
		System.out.println(s1 == s3);// false(because s3 refers to instance
										// created in nonpool)

		Event event = new Event().setSummary("Dentist Appointment")
				.setLocation("93 Union Street, Suite 404 ,Newton, Massachusetts 02459")
				.setDescription("A chance to hear more about Google's developer products.");

		org.joda.time.DateTime startDate = new org.joda.time.DateTime(2016, 02, 24, 21, 00,
				DateTimeZone.forID("America/New_York"));

		DateTime startDateTime = new DateTime(startDate.toDate());
		//
		EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/New_York");
		event.setStart(start);

		DateTime endDateTime = new DateTime("2016-02-24T23:00:00-05:00");
		EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("America/New_York");
		event.setEnd(end);

		EventAttendee[] attendees = new EventAttendee[] { new EventAttendee().setEmail("gtsatyansv@gmail.com"),
				new EventAttendee().setEmail("info@catiatraining.in"),
				new EventAttendee().setEmail("srikanthvarma.vadapalli@gmail.com"), };
		event.setAttendees(Arrays.asList(attendees));

		EventReminder[] reminderOverrides = new EventReminder[] { new EventReminder().setMethod("email").setMinutes(0),
				new EventReminder().setMethod("popup").setMinutes(0),
				new EventReminder().setMethod("email").setMinutes(24 * 60),
				new EventReminder().setMethod("popup").setMinutes(10), };
		Event.Reminders reminders = new Event.Reminders().setUseDefault(false)
				.setOverrides(Arrays.asList(reminderOverrides));
		event.setReminders(reminders);

		String calendarId = "mr09u9ogr5ti6qhdoeki64fgk8@group.calendar.google.com";
		try {
			event = calendar.events().insert(calendarId, event)
					.setSendNotifications(true)
					.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Event created: %s\n", event.getHtmlLink());

	}

}
