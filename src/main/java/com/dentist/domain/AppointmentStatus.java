package com.dentist.domain;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
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
