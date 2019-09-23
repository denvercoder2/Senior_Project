
public class InputUtils {

	private int month;
	private int day;
	private int year;
	private int hours;
	private int minutes;
	private double latitude;
	private double longitude;
	private double mins;
	
	public void sendInputs(int mo, int da, int ye, int ho, int mi, double lat, double lon, double min) {
		setMonth(mo);
		setDay(da);
		setYear(ye);
		setHours(ho);
	    setMinutes(mi);
	    
	    setLatitude(lat);
	    setLongitude(lon);
	    setMins(min);
	}
	
	public Boolean canApply()
	{
		Boolean applyFlag = false;
		
		return applyFlag;
	}
	
	
	public void setMonth(int input) {
		month = input;
	}
	
	public void setDay(int input) {
		day = input;
	}
	
	public void setYear(int input) {
		year = input;
	}
	
	public void setHours(int input) {
		hours = input;
	}
	
	public void setMinutes(int input) {
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
}
