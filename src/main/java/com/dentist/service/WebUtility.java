package com.dentist.service;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
@Service
public class WebUtility {

	public LocalDate getLocalDatefromHtmlDate(String monthDateYear) {
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

}
