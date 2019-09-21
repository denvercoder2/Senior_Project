package JohnWork.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetJulianDay {
	static boolean testOutput = false;
	
	public static double GetJulianDay(Date chosenDate, int hours, int mins) throws ParseException {
		double JD = 0, DDdd, y, m, A = 0, B;
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		int dayAsInt = chosenDate.getDay() - 1;
		DDdd = (double)(dayAsInt + hours / 24.0);
		
		if (chosenDate.getMonth() > 2) {
			y = chosenDate.getYear()+1900;
			m = chosenDate.getMonth();
		} else {
			int yearAsInt = chosenDate.getYear()+1900;
			int monthAsInt = chosenDate.getMonth();
			y = yearAsInt - 1;
			m = monthAsInt + 13;
		}
		
		Date checkAgainstDate = newDateFormat.parse("1582-10-15");
		if (chosenDate.compareTo(checkAgainstDate) > 0) {
			A = (int)(y/100);
			B = 2-A+(int)(A/4);
		} else
			B = 0;
		
		if (testOutput == true) {
		System.out.println("::JD TEST::"
				+ "\nchosenDate.year = " + chosenDate.getYear()
				+ "\nchosenDate.month = " + chosenDate.getMonth()
				+ "\nchosenDate.day = " + chosenDate.getDay()
				+ "\nhours = " + hours
				+ "\nmins = " + mins
				+ "\ny = " + y
				+ "\nm = " + m
				+ "\nA = " + A
				+ "\nB = " + B
				+ "\nDDdd = " + DDdd
				+ "\nDate = " + chosenDate);
		}
		
		JD = (int)(365.25 * y) + (int)(30.6001 * (m + 1)) + DDdd + 1720994.5 + B;
		
		return JD;
	}
}
