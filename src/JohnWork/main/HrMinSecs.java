package JohnWork.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HrMinSecs {
	SimpleDateFormat newDateFormat = new SimpleDateFormat("HH,mm,ss");
	Date parsedDate;
	
	public HrMinSecs(int hour, int mins, int secs) throws ParseException {
		parsedDate = newDateFormat.parse(hour + "," + mins + "," + secs);
	}
	
	public HrMinSecs(double hour, double mins, double secs) throws ParseException {
		parsedDate = newDateFormat.parse(hour + "," + mins + "," + secs);
	}
	
	public HrMinSecs(String dateToParse) throws ParseException {
		parsedDate = newDateFormat.parse(dateToParse);
	}
	
	public boolean compareTo(HrMinSecs comparedAgainst) {
		boolean result;

		result = this.compareTo(comparedAgainst);
		
		return result;
	}
}
