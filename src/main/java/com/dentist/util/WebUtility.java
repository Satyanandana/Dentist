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
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.mobile.device.Device;

@Service
public class WebUtility {

	public static LocalDate getLocalDateFromHtmlDate(String monthDateYear) {
		int[] array1 = new int[3];
		LocalDate localdate = null;
		boolean valid = false;
		int i = 0;
		if (monthDateYear != null
				&& monthDateYear.matches("([0-9]{4})[-/\\\\](0?[1-9]|[1][012])[-/\\\\](0?[1-9]|[12][0-9]|3[01])")) {
			String[] array = monthDateYear.split("[-/\\\\]");
			for (String part : array) {
				if (part != null) {
					array1[i] = Integer.parseInt(part);
				}
				i++;
			}
			valid = true;
		}
		if (valid) {
			localdate = new LocalDate(array1[0], array1[1], array1[2]);
		}
		return localdate;
	}

	public static DateTime getDateTimeFromHtmlDate(String monthDateYear, String hours) {
		int[] array1 = new int[3];
		DateTime dateTime = null;
		boolean validDate = false;
		boolean validTime = false;
		int hour = 0;
		int i = 0;
		if (monthDateYear != null
				&& monthDateYear.matches("([0-9]{4})[-/\\\\](0?[1-9]|[1][012])[-/\\\\](0?[1-9]|[12][0-9]|3[01])")) {
			String[] array = monthDateYear.split("[-/\\\\]");
			for (String part : array) {
				if (part != null) {
					array1[i] = Integer.parseInt(part);
				}
				i++;
			}
			validDate = true;
		}
		if (hours != null && hours.matches("^((0?[1-9])|([1][1-9])|([2][0-4]))$")) {
			hour = Integer.parseInt(hours.trim());
			validTime = true;
		}
		if (validDate && validTime) {
			dateTime = new DateTime(array1[0], array1[1], array1[2], hour, 0);
		}
		return dateTime;
	}

	public static String getDevice(Device device) {
		String deviceType;
		if (device.isNormal()) {
			deviceType = "Computer";
		} else if (device.isMobile()) {
			deviceType = "mobile";
		} else if (device.isTablet()) {
			deviceType = "tablet";
		} else {
			deviceType = "device";
		}

		return deviceType;

	}

	public static String getIpAddress(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_VIA");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
