package com.dentist.util;

public enum WebPath {

	WEB_ROOT(""), 
	GOOGLE_SERVER_TO_SERVER_P12("C:/Users/Nandu/Desktop/DentalAppointmentCalander-9292a1aa991e.p12"),
	GOOGLE_SERVER_TO_SERVER_SERVICEACCOUNT_EMAIL("serviceaccount1@dentalappointmentcalander.iam.gserviceaccount.com"),
	GOOGLE_CALENDAR_FAKE("b9r265osqo1icfq1putdm9crkk@group.calendar.google.com");

	private String path;

	WebPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
