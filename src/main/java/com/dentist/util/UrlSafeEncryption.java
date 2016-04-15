package com.dentist.util;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Apr 14, 20166:48:39 PM
 * @git
 * 
 */
public class UrlSafeEncryption {

	public static String encrypt(String string) {
		return string.replaceAll("/", "_").replaceAll("\\+", "-");
	}

	public static String decrypt(String string) {
		return string.replaceAll("_", "/").replaceAll("-", "\\+");
	}
}
