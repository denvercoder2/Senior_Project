package JohnWork.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanetCalls {
	static SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public String name, currentDate;
	int mins, secs;
	public double meanLongitude, semiMajorAxis, eccentricity, inclination, perihilion, perihilionDistanc; 
	public double meanAnomaly, trueAnomaly, position,  rightAscension, declination, ecl;
	public double cy, 	xh, yh, zh, 	xg, yg, zg, 	xeq, yeq, zeq;
	public double ascendingNodeLongtitude;
	public HrMinSecs ra;
	
	private PlanetCalls EarthInfo;
	
	
	public PlanetCalls(String currentDate, int mins, int secs) throws ParseException {
		this.currentDate = currentDate;
		this.mins = mins;
		this.secs = secs;
		getEarth();
	}
	
	public void getEarth() throws ParseException {
		createEarth();
	}
	
	private void createEarth() throws ParseException {
		EarthInfo.cy = PlanetCalls.getCy(currentDate);
		EarthInfo.meanLongitude = Mod2Pi.Mod2Pi(Math.toRadians(100.46435 + 129597740.63 * cy / 3600));
		EarthInfo.semiMajorAxis = 1.00000011 - 0.00000005 * cy;
		EarthInfo.eccentricity = 0.01671022 - 0.00003804 * cy;
        
		EarthInfo.inclination = Math.toRadians( 0.00005 - 46.94 * cy / 3600);
		EarthInfo.perihilion = Math.toRadians(102.94719 + 1198.28 * cy / 3600);
		EarthInfo.ascendingNodeLongtitude = Math.toRadians(-11.26064 - 18228.25 * cy / 3600);
        
		EarthInfo.meanAnomaly = Mod2Pi.Mod2Pi(this.meanLongitude - this.eccentricity);
		EarthInfo.trueAnomaly = TrueAnomaly.TrueAnomaly(this.meanAnomaly, this.eccentricity);
        
        // step 3: calculate position of Earth in orbit
		EarthInfo.position = getPosition(this);

        // step 4: calculate heliocentric coordinates of Earth
		EarthInfo.xh = this.position * Math.cos(this.trueAnomaly + this.perihilion);
		EarthInfo.yh = this.position * Math.sin(this.trueAnomaly + this.perihilion);
		EarthInfo.zh = 0.0;
		

	}
	
	private double getPosition(PlanetCalls planet) {
		    // get orbital position of a planet
		    this.position = (planet.semiMajorAxis * (1 - Math.pow(planet.eccentricity, 2)))
		    		/ (1 + planet.eccentricity * Math.cos(planet.trueAnomaly));
		    return this.position;
	}
	
	private static double getCy(String date) throws ParseException {
		Date checkAgainstDate = newDateFormat.parse(date);
		return GetJulianDay.GetJulianDay(checkAgainstDate, 20, 14);
	}
	

	
	public void getHelioCoordinates() {
		this.xh = this.position * (Math.cos(this.ascendingNodeLongtitude) * Math.cos(this.trueAnomaly + this.perihilion - this.ascendingNodeLongtitude) - Math.sin(this.ascendingNodeLongtitude) * Math.sin(this.trueAnomaly + this.perihilion - this.ascendingNodeLongtitude) * Math.cos(this.inclination));
		this.yh = this.position * (Math.sin(this.ascendingNodeLongtitude) * Math.cos(this.trueAnomaly + this.perihilion - this.ascendingNodeLongtitude) + Math.cos(this.ascendingNodeLongtitude) * Math.sin(this.trueAnomaly + this.perihilion - this.ascendingNodeLongtitude) * Math.cos(this.inclination));
		this.zh = 0.0;
	}
	
	public void getGeoCoordinates() {
		this.xg = this.xh - EarthInfo.xh;
		this.yg = this.yh - EarthInfo.yh;
		this.zg = this.zh - EarthInfo.zh;
	}
	
	public void getEquCoordinates() {
		this.declination = Math.toRadians(23.439281);
		this.xeq = this.xg;
		this.yeq = this.yg * Math.cos(ecl) - this.zg * Math.sin(ecl);
		this.zeq = this.zg * Math.sin(ecl) + this.zg * Math.cos(ecl);
	}
	
	public void getRightAscension() throws ParseException {
		double hours, mins, secs;
		this.rightAscension = Math.toDegrees(Mod2Pi.Mod2Pi((Math.atan2(this.yeq, this.xeq))));
		
		hours = (int)(this.rightAscension / 15.0);
		mins = (int)((this.rightAscension / 15.0) * 60);
		secs = (int)(((this.rightAscension / 15.0) * 60) * 60);
		
		this.ra = new HrMinSecs(hours, mins, secs);
	}
	
}
