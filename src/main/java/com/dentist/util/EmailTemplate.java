package com.dentist.util;

public enum EmailTemplate {
	WELCOME_EMAIL("welcomeemail.vm");

	private String name;

	EmailTemplate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
