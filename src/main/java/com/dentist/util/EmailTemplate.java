package com.dentist.util;

public enum EmailTemplate {
	WELCOME_EMAIL("welcomeemail.vm"),
	VERIFY_ACCOUNT_EMAIL("verifyaccountemail.vm"),
	LAST_LOGIN_EMAIL("lastloginemail.vm");

	private String name;

	EmailTemplate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
