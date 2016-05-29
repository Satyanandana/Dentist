package com.dentist.webapp;

import java.util.Map;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;

public class ServerSideValidations {

	private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	// Atleast one upper case,one lowercase,on number and the length between 6
	// to 10
	private static final String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,10}";
	// Should contain only alphabets.
	private static final String NAME_PATTERN = "^[a-zA-Z ]+$";
	private static final String ADDRESS_PATTERN = "^[A-Za-z0-9 _\\.]*[A-Za-z0-9 _\\.][A-Za-z0-9 _\\.]+$";
	private static final String CITY_PATTERN = "^[a-z A-Z]{3,30}$";
	private static final String ZIPCODE_PATTERN = "^[0-9]{5}$";
	private static final String PHONENUMBER_PATTERN = "^[0-9]{10}$";
	private static final String EMPLOYEE_PATTERN = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]+$";
	// need to check
	private static final String INCOME_PATTERN = "^([1-9]{1,3}([0-9]{3})*[0-9]{3}|[0-9]+)(.[0-9]{0,2})?$";
	private static final String SSN_PATTERN = "^[0-9]{9}$";

	public static String unmask(String masked) {
		String unmasked = masked.replaceAll("[_(),\\s-$%]+", "");
		return unmasked;
	}

	public static boolean validatePassword(String password, Model model, Map<String, String> map, String errorName, String error) {
		Boolean valid = false;
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateConfirmPassword(String password, String confirmpattern, Model model, Map<String, String> map, String errorName,
			String error) {
		Boolean valid = false;
		if (password.equals(confirmpattern)) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);

		}
		return valid;
	}

	public static boolean validateEmail(String email, Model model, Map<String, String> map, String errorName, String error) {
		Boolean valid = false;
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateName(String name, Model model, Map<String, String> map, String errorName, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(NAME_PATTERN);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateAddress(String address, Model model, Map<String, String> map, String errorName, String error) {
		boolean valid = false;

		Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(address);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateCity(String city, Model model, Map<String, String> map, String errorName, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(CITY_PATTERN);
		Matcher matcher = pattern.matcher(city);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateZipCode(String zipcode, Model model, Map<String, String> map, String errorName, String error) {

		boolean valid = false;
		Pattern pattern = Pattern.compile(ZIPCODE_PATTERN);
		Matcher matcher = pattern.matcher(zipcode);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validatePhoneNumber(String phoneNumber, Model model, Map<String, String> map, String errorName, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(PHONENUMBER_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateSSN(String ssn, Model model, Map<String, String> map, String errorName, String error) {

		boolean valid = false;
		Pattern pattern = Pattern.compile(SSN_PATTERN);
		Matcher matcher = pattern.matcher(ssn);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateEmployee(String employee, Model model, Map<String, String> map, String errorName, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(EMPLOYEE_PATTERN);
		Matcher matcher = pattern.matcher(employee);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	public static boolean validateIncome(String income, Model model, Map<String, String> map, String errorName, String error) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(INCOME_PATTERN);
		Matcher matcher = pattern.matcher(income);
		if (matcher.matches() == true) {
			valid = true;
		}
		if (valid == false) {
			addToModelOrMap(model, map, errorName, error);
		}
		return valid;
	}

	private static void addToModelOrMap(Model model, Map<String, String> map, String errorName, String error) {
		if (model != null) {
			model.addAttribute(errorName, error);
		}
		if (map != null) {
			map.put(errorName, error);
		}
	}

}
