
package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date convertToDate(String dateToBeConverted) throws ParseException {

		return new SimpleDateFormat("dd/MM/yyyy").parse(dateToBeConverted);

	}

}
