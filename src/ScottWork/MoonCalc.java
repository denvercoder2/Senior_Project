/*
Scott Holley
*/
public class MoonCalc{

    // constants
    // Radians to degrees. 
	public static final double RAD_TO_DEG = 180.0 / Math.PI;

	// Degrees to radians. 
	public static final double DEG_TO_RAD = 1.0 / RAD_TO_DEG;

	// Astronomical Unit in km. As defined by JPL. 
	public static final double AU = 149597870.691;

	// Earth equatorial radius in km. IERS 2003 Conventions. 
	public static final double EARTH_RADIUS = 6378.1366;

	// Two times Pi. 
	public static final double TWO_PI = 2.0 * Math.PI;

	// Pi divided by two.
	public static final double PI_OVER_TWO = Math.PI / 2.0;

	// Julian century conversion constant = 100 * days per year. 
	public static final double JULIAN_DAYS_PER_CENTURY = 36525.0;

	// Seconds in one day. 
	public static final double SECONDS_PER_DAY = 86400;

	// Our default epoch. The Julian Day which represents noon on 2000-01-01.
    public static final double J2000 = 2451545.0;
    

    // inputs
    private double jd_UT = 0, t = 0, obsLon = 0, obsLat = 0, TTminusUT = 0;
    
}