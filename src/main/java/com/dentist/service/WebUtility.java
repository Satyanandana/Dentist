package com.dentist.service;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.mobile.device.Device;
@Service
public class WebUtility {

	public static LocalDate getLocalDatefromHtmlDate(String monthDateYear) {
		int[] array1 = new int[3];
		LocalDate localdate = null;
		int i = 0;
		if (monthDateYear != null) {
			String[] array = monthDateYear.split("[-/]");
			for (String part : array) {
				if (part != null) {
					array1[i] = Integer.parseInt(part);
				}
				i++;
			}
		}
		if (array1 != null && array1[1]<=12 && array1[2]<=31 ) {
			localdate = new LocalDate(array1[0], array1[1], array1[2]);
		}
		return localdate;
	}
	
	public static String getDevice(Device device){
		String deviceType = "unknown";
        if (device.isNormal()) {
            deviceType = "normal";
        } else if (device.isMobile()) {
            deviceType = "mobile";
        } else if (device.isTablet()) {
            deviceType = "tablet";
        }
        return "Hello " + deviceType + " browser!";
		
	}
	
	public static String getIpAddress(HttpServletRequest request){
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
		   }
		   return ipAddress;
	}

}
