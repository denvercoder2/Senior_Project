
public class InputUtils {

	/*private double month;
	private double day;
	private double year;
	private double hours;
	private double minutes;
	private double latitude;
	private double longitude;
	private double mins;
	*/
	/*
	public void sendInputs(double mo, double da, double ye, double ho, double mi, double lat, double lon, double min) {
		setMonth(mo);
		setDay(da);
		setYear(ye);
		setHours(ho);
	    setMinutes(mi);
	    
	    setLatitude(lat);
	    setLongitude(lon);
	    setMins(min);
	}
	*/
	public Boolean canApply()
	{
		Boolean flag = false;
		
		return flag;
	}
	
	public Boolean checkMonth(double month){
		
        Boolean flag = false;
		if(month >= 1 || month <= 12) {
			flag = true;
		}
		return flag;
	}
	
	public Boolean checkDay(double day) {
		
        Boolean flag = false;
		if(day >= 1 || day <= 31) {
			flag = true;
		}
		return flag;
	}
	
	public Boolean checkYear(double year) {
        Boolean flag = false;
		if(year >=1900 || year <= 2100) {
			flag = true;
		}
		return flag;
	}
	
	public Boolean checkHours(double hours) {
        Boolean flag = false;
		if(hours >= 0 || hours <= 24) {
			flag = true;
		}
		return flag;
	}
	
	public Boolean checkMinutes(double minutes) {
        Boolean flag = false;
		if(minutes >= 0 || minutes <= 60) {
			flag = true;
		}
		return flag;
	}
	
	public Boolean checkLatitude(double latitude) {
        Boolean flag = false;
		if(latitude >= 0 || latitude <= 90) {
			flag = true;
		}
		return flag;
	}
	
	public Boolean checkLongitude(double longitude) {
        Boolean flag = false;
		if(longitude >= 0 || longitude <= 180) {
			flag = true;
		}
		return flag;
	}
	
	public Boolean checkMins(double mins) {
        Boolean flag = false;
		
		return flag;
	}
	/*
	public void setMonth(double input) {
		month = input;
	}
	
	public void setDay(double input) {
		day = input;
	}
	
	public void setYear(double input) {
		year = input;
	}
	
	public void setHours(double input) {
		hours = input;
	}
	
	public void setMinutes(double input) {
		minutes = input;
	}
	
	public void setLatitude(double input) {
		latitude = input;
	}
	
	public void setLongitude(double input) {
		longitude = input;
	}
	
	public void setMins(double input) {
		mins = input;
	}
	*/
}
