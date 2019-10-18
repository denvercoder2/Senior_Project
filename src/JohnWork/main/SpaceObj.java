package JohnWork.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/*
 * A lot of this, almost entirely, is derived from Scott's ParseCreateStars
 * It has a really good baseline, but work and functions were included in an
 * object, and to neatly separate it I split his .java into SpaceObj and SpaceObjListBuilder
 */


public class SpaceObj {
    private String starId;
    private String Hip;
    private String HD;
    private String HR;
    private String Gliese;
    private String BayerFlamsteed;
    private String ProperName;
    private String RA;
    private String Dec;
    private String Distance;
    private String Magnitude;
    private String AbsMag;
    private String Spectrum;
    private String ColorIndex;
    private double altitude;
    private double azimuth;
	
	public SpaceObj() {
		
	}
	
    // getters and setters if we want to use them later
    public void setAzimuth(double Azimuth){
        this.azimuth = Azimuth;
    }
    public double getAzimuth(){
        return this.azimuth;
    }
    public void setAltitude(double Altitude){
        this.altitude = Altitude;
    }
    public double getAltitude(){
        return this.altitude;
    }
    public void setStarID(String starID){
        this.starId = starID;
    }
    public String getStarID(){
        return this.starId;
    }
    public void setHip(String Hip){
        this.Hip = Hip;
    }
    public String getHip(){
        return this.Hip;
    }
    public void setHD(String HD){
        this.HD = HD;
    }
    public String getHD(){
        return this.HD;
    }
    public void setHR(String HR){
        this.HR = HR;
    }
    public String getHR(){
        return this.HR;
    }
    public void setGliese(String Gliese){
        this.Gliese = Gliese;
    }
    public String getGliese(){
        return this.Gliese;
    }
    public void setBayerFlamsteed(String BayerFlamsteed){
        this.BayerFlamsteed = BayerFlamsteed;
    }
    public String getBayerFlamsteed(){
        return this.BayerFlamsteed;
    }
    public void setProperName(String ProperName){
        this.ProperName = ProperName;
    }
    public String getProperName(){
        return this.ProperName;
    }
    public void setRA(String RA){
        this.RA = RA;
    }
    public String getRA(){
        return this.RA;
    }
    public void setDec(String Dec){
        this.Dec = Dec;
    }
    public String getDec(){
        return this.Dec;
    }
    public void setDistance(String Distance){
        this.Distance = Distance;
    }
    public String getDistance(){
        return this.Distance;
    }
    public void setMagnitude(String Magnitude){
        this.Magnitude = Magnitude;
    }
    public String getMagnitude(){
        return this.Magnitude;
    }
    public void setAbsMag(String AbsMag){
        this.AbsMag = AbsMag;
    }
    public String getAbsMag(){
        return this.AbsMag;
    }
    public void setSpectrum(String Spectrum){
        this.Spectrum = Spectrum;
    }
    public String getSpectrum(){
        return this.Spectrum;
    }
    public void setColorIndex(String ColorIndex){
        this.ColorIndex = ColorIndex;
    }
    public String getColorIndex(){
        return this.ColorIndex;
    }
    
    public void solveOwnLocation() {
    	this.solvingOwnLocation();
    }
    
    public void solveOwnLocation(double lat, double lon) {
    	this.solvingOwnLocation(lat, lon);
    }
    
    public void solveOwnLocation(double lat, double lon, Date chosenDate) {
    	this.solvingOwnLocation(lat, lon, chosenDate);
    }
    
    private double getDH(Date chosenDate) {
    	double chosenDate_hours = chosenDate.getHours(),chosenDate_mins = chosenDate.getMinutes(),chosenDate_secs = chosenDate.getSeconds(); 
    	
    	chosenDate_secs /= 60;
    	chosenDate_mins = (chosenDate_mins + chosenDate_secs)/60;
    	chosenDate_hours += chosenDate_mins;
    	
    	return chosenDate_hours;
    }
    
    private double getUT(double lat, double lon, Date chosenDate) {
    	double offset, ut;
    	if (TimeZone.getDefault().inDaylightTime(chosenDate))
    		chosenDate.setHours(chosenDate.getHours()-1);
    	
    	offset = (int)(lon/15);
    	ut = getDH(chosenDate) - offset;
    	
    	return ut;
    }
    
    private Date getGCD(Date chosenDate, double UT) {
    	Date gcDate = chosenDate;
    	double chosenDate_year = chosenDate.getYear(),chosenDate_mon = chosenDate.getMonth(),chosenDate_day = chosenDate.getDate() + (UT/24);
    	gcDate.setDate((int) chosenDate_day);
    	
    	return gcDate;
    }
    
    private double getJulianDate(Date gcDate) {
    	boolean testOutput = false;
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
    	double JD = 0, DDdd, y, m, A = 0, B;
		
		int dayAsInt = gcDate.getDate() - 1;
		DDdd = (double)(gcDate.getDate() + gcDate.getHours() / 24.0);
		
		if (gcDate.getMonth() > 2) {
			y = gcDate.getYear()+1900;
			m = gcDate.getMonth();
		} else {
			int yearAsInt = gcDate.getYear()+1900;
			int monthAsInt = gcDate.getMonth();
			y = yearAsInt - 1;
			m = monthAsInt + 13;
		}
		
		Date checkAgainstDate = null;
		try {
			checkAgainstDate = dateFormat.parse("1582,10,15,0,0,0");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (gcDate.compareTo(checkAgainstDate) > 0) {
			A = (int)(y/100);
			B = 2-A+(int)(A/4);
		} else
			B = 0;
		
		if (testOutput == true) {
		System.out.println("::JD TEST::"
				+ "\nchosenDate.year = " + gcDate.getYear()
				+ "\nchosenDate.month = " + gcDate.getMonth()
				+ "\nchosenDate.day = " + gcDate.getDate()
				+ "\nhours = " + gcDate.getHours()
				+ "\nmins = " + gcDate.getMinutes()
				+ "\ny = " + y
				+ "\nm = " + m
				+ "\nA = " + A
				+ "\nB = " + B
				+ "\nDDdd = " + DDdd
				+ "\nDate = " + gcDate);
		}
		
		JD = (int)(365.25 * y) + (int)(30.6001 * (m + 1)) + DDdd + 1720994.5 + B;
		
		return JD;
    }
    
    private Date getCD(double jd) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd");
    	double A, B, C, D, E, G;
    	int I;
    	double F, day, month, year;
    	jd += 0.5;
    	I = (int)jd;
    	F = jd - (int)jd;
    	
    	if (I > 2299160) {
    		A = (int)(I-1867216.25/36524.25);
    		B = I + A - (int)(A/4) + 1;
    	}
    	else
    		B = I;
    	
    	C = B + 1524;
    	D = (int)((C-122.1)/365.25);
    	E = (int)(365.25*D);
    	G = (int)((C-E)/30.6001);
    	
    	day = C - E + F - (int)(30.6001*G);
    	if (G < 13.5)
    		month = (G-1);
    	else
    		month = (G-13);
    	if (month > 2.5)
    		year = D-4716;
    	else
    		year = D-4715;
    	
    	Date CDDate = null;
		try {
			CDDate = dateFormat.parse((int)year + "," + (int)month + "," + (int)day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return CDDate;
    }
    
    private double UTtoGST(Date gcDate, double UT, double jd) {
    	double s = jd - 2451545;
    	double t = s/36525;
    	
    	double t0 = 6.697374558 + (2400.051336 * t) + (0.000025862*t*t);
    	double A = UT * 1.002727909;
    	
    	t0 += A;
    	
    	while (t0 > 24 || t0 < 0) {
    		if (t0 > 24)
    			t0 -= 24;
    		if (t0 < 0)
    			t0 += 24;
    	}
    	double gst = t0;
    	
    	return gst;
    }
    
    private double RAtoH(double RA, double lst) {
    	double hr = lst - RA;
    	if (hr < 0)
    		hr += 24;
    	return hr;
    }
    
    private double getAltitude(double lat, double dh, double dec) {
    	double a = (Math.sin(dec)*Math.sin(lat))+(Math.cos(dec)*Math.cos(lat)*Math.cos(dh));
    	a = Math.asin(a);
    	return a;
    }
    private double getAzimuth(double lat, double dh, double alt, double dec) {
    	double A, H;
    	A = (Math.sin(dec)-(Math.sin(lat)*Math.sin(alt)))/(Math.cos(lat)*Math.cos(alt));
    	A = Math.acos(A);
    	H = Math.sin(dh);
    	if (H > 0)
    		A = 2*(Math.PI) - A;
    	return A;
    }
    
    private void solvingOwnLocation() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
    	Date chosenDate = new Date();
    	//String choiceDate = dateFormat.format(chosenDate);
    	
    	double d_RA, d_Dec, lat, lon;
    	d_RA = Math.toRadians(Double.parseDouble(this.getRA()));
		d_Dec = Math.toRadians(Double.parseDouble(this.getDec())); 
    	lat = 34.7251; lon = 86.6398; // Lat and Lon of UAH default if none given
    	
    	
    	Date gcDate; double ut, jd, gst, lst, dh, altitude, azimuth;
    	ut = getUT(lat,lon,chosenDate);
    	gcDate = getGCD(chosenDate, ut);
    	jd = getJulianDate(gcDate);
    	gcDate = getCD(jd);
    	ut = 24*(gcDate.getDate() - (int)gcDate.getDate());
    	gst = UTtoGST(gcDate, ut, jd);
    	//
    	lst = gst + lon/15; // First big step
    	//
    	dh = RAtoH(d_RA,lst); // Second big step
    	//
    	
    	altitude = getAltitude(lat,dh,d_Dec);
    	this.setAltitude(altitude);
    	azimuth = getAzimuth(lat,dh,altitude,d_Dec);
    	this.setAzimuth(azimuth);
    	
    	//System.out.println(this.ProperName + " \nstar has an altitude of " + altitude + "\nand an azimuth of " + azimuth);
    }
    
    private void solvingOwnLocation(double lat, double lon) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
    	Date chosenDate = new Date();
    	//String choiceDate = dateFormat.format(chosenDate);
    	
    	double d_RA, d_Dec;
    	d_RA = Math.toRadians(Double.parseDouble(this.getRA()));
		d_Dec = Math.toRadians(Double.parseDouble(this.getDec())); 
    	
		Date gcDate; double ut, jd, gst, lst, dh, altitude, azimuth;
    	ut = getUT(lat,lon,chosenDate);
    	gcDate = getGCD(chosenDate, ut);
    	jd = getJulianDate(gcDate);
    	gcDate = getCD(jd);
    	ut = 24*(gcDate.getDate() - (int)gcDate.getDate());
    	gst = UTtoGST(gcDate, ut, jd);
    	//
    	lst = gst + lon/15; // First big step
    	//
    	dh = RAtoH(d_RA,lst); // Second big step
    	//
    	
    	altitude = getAltitude(lat,dh,d_Dec);
    	this.setAltitude(altitude);
    	azimuth = getAzimuth(lat,dh,altitude,d_Dec);
    	this.setAzimuth(azimuth);
    	
    	//System.out.println(this.ProperName + " \nstar has an altitude of " + altitude + "\nand an azimuth of " + azimuth);
    }
    
    private void solvingOwnLocation(double lat, double lon, Date chosenDate) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
    	//String choiceDate = dateFormat.format(chosenDate);
    	
    	double d_RA, d_Dec;
		d_RA = Math.toRadians(Double.parseDouble(this.getRA()));
		d_Dec = Math.toRadians(Double.parseDouble(this.getDec())); 
    	
		Date gcDate; double ut, jd, gst, lst, dh, altitude, azimuth;
    	ut = getUT(lat,lon,chosenDate);
    	gcDate = getGCD(chosenDate, ut);
    	jd = getJulianDate(gcDate);
    	gcDate = getCD(jd);
    	ut = 24*(gcDate.getDate() - (int)gcDate.getDate());
    	gst = UTtoGST(gcDate, ut, jd);
    	//
    	lst = gst + lon/15; // First big step
    	//
    	dh = RAtoH(d_RA,lst); // Second big step
    	//
    	
    	altitude = getAltitude(lat,dh,d_Dec);
    	this.setAltitude(altitude);
    	azimuth = getAzimuth(lat,dh,altitude,d_Dec);
    	this.setAzimuth(azimuth);
    	
    	//System.out.println(this.ProperName + " \nstar has an altitude of " + altitude + "\nand an azimuth of " + azimuth);
    	
    	
    }
}
