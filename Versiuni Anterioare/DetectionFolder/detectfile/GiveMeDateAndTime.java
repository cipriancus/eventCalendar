/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectfile;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GiveMeDateAndTime {

	public String giveMeDate(String text) {
		int regexsNumber = 4;
		String[] results = new String[regexsNumber + 1];
		String[] dataExplicitRegex = new String[regexsNumber + 1];

		String sep, zi, luna, an;
		String bestDate;
		sep = "([/]| |:|'|[.]|,|of|)*";
		zi = "([1-3]?)([0-9])(nd|th)*";
		luna = "(jan|Jan|JAN|january|JANUARY|January|01|1|";
		luna = luna + "feb|Feb|FEB|february|FEBRUARY|February|02|2|";
		luna = luna + "mar|Mar|MAR|march|MARCH|March|03|3|";
		luna = luna + "apr|Apr|APR|april|APRIL|April|04|4|";
		luna = luna + "may|May|MAY|05|5|";
		luna = luna + "jun|Jun|JUN|june|JUNE|June|06|6|";
		luna = luna + "jul|Jul|JUL|july|JULY|July|07|7|";
		luna = luna + "aug|Aug|AUG|august|AUGUST|August|08|8|";
		luna = luna + "sep|Sep|SEP|september|SEPTEMBER|September|09|9|";
		luna = luna + "oct|Oct|OCT|october|OCTOBER|October|10|";
		luna = luna + "nov|Nov|NOV|november|NOVEMBER|November|11|";
		luna = luna + "dec|Dec|DEC|december|DECEMBER|December|12)";

		an = "(((2(0|1)([0-9])([0-9])))?)";
		dataExplicitRegex[1] = sep + zi + sep + luna + sep + an;
		dataExplicitRegex[2] = sep + luna + sep + zi;
		dataExplicitRegex[3] = sep + luna + sep + zi + sep + an;
		dataExplicitRegex[4] = sep + zi + sep + luna;

		Pattern r = Pattern.compile(dataExplicitRegex[1]);
		Matcher m = r.matcher(text);

		for (int i = 1; i <= regexsNumber; i++) {

			r = Pattern.compile(dataExplicitRegex[i]);
			m = r.matcher(text);
			if (m.find()) {
				results[i] = m.group(0);

			}

		}
		
		results = RemoveNullValue(results);
		if (results.length > 0)
			bestDate = maxString(results);
		else	
			bestDate="nu am gasit data";
		return bestDate;

	}

	public String giveMeTime(String text) {
		String time = null;
		int regexsNumber = 3;
		String[] results = new String[regexsNumber + 1];
		String[] timeRegex = new String[regexsNumber + 1];

		timeRegex[1] = "(((0|1)?[0-9])|2[0-3])(:|.|/|,)[0-5][0-9]";
		timeRegex[2] = "(([1-9]|1[0-2]) (am|pm) )";
		timeRegex[3] = "((one|two|three|four|five|six|seven|eight|nine|ten|eleven|twelve|1|2|3|4|5|6|7|8|9|10|11|12)( )*(O'Clock|O'CLOCK|O'clock|o'clock))";
		Pattern r = Pattern.compile(timeRegex[1]);
		Matcher m = r.matcher(text);

		for (int i = 1; i <= regexsNumber; i++) {

			r = Pattern.compile(timeRegex[i]);
			m = r.matcher(text);
			if (m.find()) {
				results[i] = m.group(0);

			}

		}
		results = RemoveNullValue(results);
		if (results.length > 0)
			time = maxString(results);
		else
			time ="nu am gasit timp";
		return time;
	}

	static String[] RemoveNullValue(String[] firstArray) {
		return firstArray = Arrays.stream(firstArray).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);

	}

	static String maxString(String array[]) {
		int index = 0;

		int elementLength = array[0].length();
		for (int i = 1; i < array.length; i++) {
			if (array[i].length() > elementLength) {
				index = i;
				elementLength = array[i].length();
			}
		}
		return array[index];

	}
}