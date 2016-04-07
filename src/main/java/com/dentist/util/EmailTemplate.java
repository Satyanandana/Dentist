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
public enum EmailTemplate {
	WELCOME_EMAIL("welcomeemail.vm"),
	VERIFY_ACCOUNT_EMAIL("verifyaccountemail.vm"),
	LAST_LOGIN_EMAIL("lastloginemail.vm"),
	FORGOT_PASSWORD("forgotpassword.vm");

	private String name;

	EmailTemplate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
