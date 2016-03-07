package com.dentist.domain;
/* Never change the order of elements after going into production*/
public enum AppointmentStatus {
	
	CONFIRMED("Confirmed"),
	CANCELLED("Cancelled"),
	PARTIALLY_COMPLETED("Partially Completed"),
	COMPLETED("Completed");
	
	private String status;

	AppointmentStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
