package com.dentist.util;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;

public class GoogleServerToServer {

	/*
	 * serverAccountEmail = Google Server To Server account email
	 * privateKeyFileP12 = java.io.File object of Google private key file with
	 * .p12 extention OuthScope = list of Google OuthScopes of applications for
	 * Google authentication.
	 */
	public static GoogleCredential getGoogleCredential(String serverAccountEmail, File privateKeyFileP12,
			ArrayList<String> OuthScopes) {
		GoogleCredential credential = null;
		try {
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			credential = new GoogleCredential.Builder().setTransport(httpTransport).setJsonFactory(jsonFactory)
					.setServiceAccountId(serverAccountEmail).setServiceAccountPrivateKeyFromP12File(privateKeyFileP12)
					.setServiceAccountScopes(OuthScopes).build();

		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return credential;
	}

	/*
	 * credential = GoogleCredential object with OuthScopes of Google Calendar
	 * application appName = application name
	 */
	public static Calendar getCalendar(GoogleCredential credential, String appName) {

		Calendar service = null;

		try {
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			service = new Calendar.Builder(httpTransport, jsonFactory, null).setApplicationName(appName)
					.setHttpRequestInitializer(credential).build();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return service;
	}

}
