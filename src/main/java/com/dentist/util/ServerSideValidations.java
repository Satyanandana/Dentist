package com.dentist.util;


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerSideValidations {

	private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	private static final String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,10}";
	private static final String NAME_PATTERN = "^[a-zA-Z]+$";
	private static final String ADDRESS_PATTERN = "^[A-Za-z0-9 _\\.]*[A-Za-z0-9 _\\.][A-Za-z0-9 _\\.]+$";
	private static final String CITY_PATTERN = "^[a-zA-Z]{3,30}$";
	private static final String ZIPCODE_PATTERN = "^[0-9]{5}$";
	private static final String PHONENUMBER_PATTERN = "^[0-9]{10}$";
	private static final String EMPLOYEE_PATTERN = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]+$";
	private static final String INCOME_PATTERN = "^([1-9]{1,3}([0-9]{3})*[0-9]{3}|[0-9]+)(.[0-9]{0,2})?$";//need check
	private static final String SSN_PATTERN ="^[0-9]{9}$";
	
	
	public static String unmask(String masked){
		String unmasked = masked.replaceAll("[_(),\\s-$%]+", "");
		return unmasked;
		}
	
	
	public static boolean validatePassword(String password, Map<String, Object> map, String error) {
		Boolean valid = false;
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
	public static boolean validateConfirmPassword (String password,String confirmpattern, Map<String, Object> map,String error) {
		Boolean valid = false;
		if (password.equals(confirmpattern)) {
			valid = true;
		}
		if (valid == false) {
			map.put("error", error);
			map.notify();
		}
		return valid;
	}

	public static boolean validateEmail(String email, Map<String, Object> map, String error) {
		Boolean valid = false;
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
	public static boolean validateName(String name, Map<String, Object> map, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(NAME_PATTERN);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
	public static boolean validAddress(String address, Map<String, Object> map, String error) {
		boolean valid = false;
		
		Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(address);
		if(matcher.matches() == true) {
			valid= true;
		}
		if(valid == false) {
			map.put("error",error);
		}
		return valid;
	}
	
	public static boolean validCity(String city, Map<String, Object> map, String error) {
		boolean valid = false;
		Pattern pattern  = Pattern.compile(CITY_PATTERN);
		Matcher matcher = pattern.matcher(city);
		if(matcher.matches() == true) {
			valid = true;
		}
		if(valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
	public static boolean validZipCode (String zipcode, Map<String, Object> map, String error) {
		
		boolean valid = false;
		Pattern pattern = Pattern.compile(ZIPCODE_PATTERN);
		Matcher matcher = pattern.matcher(zipcode);
		if(matcher.matches() == true) {
			valid = true;
		}
		if(valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
	public static boolean validPhoneNumber (String phoneNumber, Map<String, Object> map, String error) {
		boolean valid= false;
		Pattern pattern = Pattern.compile(PHONENUMBER_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);
		if(matcher.matches() == true) {
			valid = true;
		}
		if(valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
 public static boolean validSSN (String ssn, Map<String, Object> map, String error) {
		
		boolean valid = false;
		Pattern pattern = Pattern.compile(SSN_PATTERN);
		Matcher matcher = pattern.matcher(ssn);
		if(matcher.matches() == true) {
			valid = true;
		}
		if(valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
	public static boolean validEmployee (String employee, Map<String, Object> map, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(EMPLOYEE_PATTERN);
		Matcher matcher = pattern.matcher(employee);
		if(matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			map.put("error", error);
		}
		return valid;
	}
	
	
	public static boolean validateIncome (String income, Map<String, Object> map, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(INCOME_PATTERN);
		Matcher matcher = pattern.matcher(income);
		if(matcher.matches() == true) {
			 valid = true;
		}
		if(valid == false) {
			map.put("error", error);
		}
		return valid;
	}
		
}

