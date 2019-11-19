
import java.awt.*;
import java.lang.*;
import JDate.*;

/* This is the Moon object, where we perform all the calculations needed
   to determine the Moon's phase, distance, rise and set time, and so on.
   In the interest of speed, we have implemented all of these routines
   here, although we could really split off some of the more general
   routines and create a superclass of CelestialObject, deriving the 
   Moon class from it. However, deadlines loom, and we already have a lot
   of files to download to get the applet up and running, so we glom
   everything together here.
   
   All angular measures are expressed in radians, unless otherwise noted.
   
   Note that these routines are order dependant; you MUST call position()
   before any of the other routines will return meaningful values. This is
   another deadline related compromise; we want to give careful thought to
   how the proposed superclass can help with some of these routines.
   For now, just be VERY CAREFUL!
*/

public class MoonPosition
{
   private double RAmoon; // Moon Right Ascension.
   private double Decmoon; // Moon Declination.
   private double RAsun; // Sun Right Ascension.
   private double Decsun; // Sun Declination.
   private double Clat; // Moon's Celestial Latitude.
   private double Clong; // Moon's Celestial Longitude.
   
   private JDate sunrise;
   private JDate sunset;
   private JDate moonrise;
   private JDate moonset;
   
   public boolean MoonCircumpolar = false;
   public boolean SunCircumpolar = false;
   
   private JDate currentDate;
   
   private double Latitude; // Observer's latitude.
   private double Longitude; // Observer's longitude.
   
   private static double epoch = 2444238.5; // Base date for calculations.
											// January 0, 1980
   
   /****** Values used in determining the various data ******/
   
   double lambdaSun = 0;
   double meanAnomalySun = 0;
   double lPrimePrime = 0;
   double MPrimeM = 0;
   double EcSun = 0;
   double EcMoon = 0;
   double parallax = 0;
						 
   

   public static Moon()
   {
	  currentDate = new JDate(); // Set to system date.
	  moonrise = new JDate();
	  moonset = new JDate();
	  sunrise = new JDate();
	  sunset = new JDate();
   }   
   // Adjusts ang to lie in range 0 - 2*PI
   private double adjustRange(double ang)
   {
	  double temp = ang;
	  
	  while (temp > 2*Math.PI)
		 temp = temp - 2*Math.PI;
		 
	  while (temp < 0)
		 temp = temp + 2*Math.PI;
		 
	  return temp;
   }   
   private double degreesToRadians(double degrees)
   {
	  return Math.PI * degrees / 180;
   }   
   public double getDecmoon()
   {
	  return Decmoon;
   }   
   public double getDecsun()
   {
	  return Decsun;
   }   
   public double getRAmoon()
   {
	  return RAmoon;
   }   
   // Obvious set and get functions...
   public double getRAsun()
   {
	  return RAsun;
   }   
   // Returns the mean obliquity of the ecliptic for a given
   // date, in radians.
   private double meanObliquity(double date)
   {
	  double T = (date - 2415020.0) / 36525;
	  
	  double de = T * (46.845 + T * (0.0059 - T * (0.00181)));
	  
	  de = de / 3600;
	  
	  double e = 23.452294 - de;
	  
	  return degreesToRadians(e);
   }   
   public double moonDistance()
   {
	  return (384401 * (1 - 0.054900*0.054900))/
			 (1 + 0.054900*Math.cos(MPrimeM + EcMoon));
   }   
   public JDate moonRise()
   {
	  return moonrise;
   }   
   public JDate moonSet()
   {
	  return moonset;
   }   
   // Returns the position angle of the Moon's bright limb, in radians.
   // This angle is measured eastward from the north point of the Moon's
   // disk.
   public double pAng()
   {
	  double numerator = Math.cos(Decsun)*Math.sin(RAsun - RAmoon);
	  double denominator = Math.cos(Decmoon)*Math.sin(Decsun) - 
						   Math.sin(Decmoon)*Math.cos(Decsun)*Math.cos(RAsun - RAmoon);
	  
	  return Math.atan2(numerator, denominator);
   }   
   public double phase()
   {
	  return adjustRange(lPrimePrime - lambdaSun);
   }   
   // Sets the values of Clat and Clong.
   // Presumes that currentDate has already been set.
   //
   // This is a woefully inefficient algorithm as it now stands.
   // We have followed the "recipe" set forth in Practical Astronomy
   // With Your Calculator, by Peter Duffett-Smith. This procedure
   // is presented using degrees as arguments, and we use the
   // degreesToRadians function often to keep things in the radian
   // measure that we need. We should convert the various constants
   // avoid this conversion, but they are left in their original form
   // for now for debugging purposes.
   public void position()
   {
	  // Days since epoch. We use Universal time for these calculations.
	  double D = currentDate.getJD() - epoch;
			
	  // Find the Sun's position.
	  
	  double N = (D * 2 * Math.PI) / 365.2422;
	  N = adjustRange(N);
	  
	  meanAnomalySun = N + 4.86656333799 - 4.93223768664;
	  
	  if (meanAnomalySun < 0)
		 meanAnomalySun = meanAnomalySun + 2*Math.PI;
		 
	  EcSun = 2 * 0.016718 * Math.sin(meanAnomalySun);
	  
	  lambdaSun = N + EcSun + 4.86656333799;
	  
	  if (lambdaSun > 2*Math.PI)
		 lambdaSun = lambdaSun + 2*Math.PI;
		 
	  // Find the Moon's position.
	  
	  double l = degreesToRadians(13.1763966 * D) + 1.13403577981;
	  l = adjustRange(l);
	  
	  double Mm = l - degreesToRadians(0.1114041 * D) - 6.09788480005;
	  Mm = adjustRange(Mm);
	  
	  N = 2.65203528587 - degreesToRadians(0.052953 * D);
	  N = adjustRange(N);
	  
	  double C = (l - lambdaSun);
	  double Ev = degreesToRadians(1.2739 * Math.sin(2*C - Mm));
	  
	  double Ae = degreesToRadians(0.1858 * Math.sin(meanAnomalySun));
	  double A3 = degreesToRadians(0.37 * Math.sin(meanAnomalySun));
	  
	  MPrimeM = Mm + Ev - Ae - A3;
	  
	  EcMoon = degreesToRadians(6.2886 * Math.sin(MPrimeM));
	  
	  double A4 = degreesToRadians(0.214 * Math.sin(2*MPrimeM));
	  
	  double lPrime = l + Ev + EcMoon - Ae + A4;
	  
	  double V = degreesToRadians(0.6583 * Math.sin(2 * (lPrime - lambdaSun)));
	  
	  lPrimePrime = lPrime + V;
	  
	  double NPrime = N - degreesToRadians(0.16 * Math.sin(meanAnomalySun));
	  
	  double y = Math.sin(lPrimePrime - NPrime) * Math.cos(0.0898041015189);
	  double x = Math.cos(lPrimePrime - NPrime);
	  
	  double temp = Math.atan2(y, x);
	  
	  Clong = temp + NPrime;
	  
	  temp = Math.sin(lPrimePrime - NPrime) * Math.sin(0.0898041015189);
	  
	  Clat = Math.asin(temp);
	  
	  // Calculate right ascension and declination.
	  double e = meanObliquity(currentDate.getJD());
	  
	  // Moon.
	  Decmoon = Math.asin(Math.sin(Clat)*Math.cos(e) + 
					  Math.cos(Clat)*Math.sin(e)*Math.sin(Clong));
					  
	  y = Math.sin(Clong)*Math.cos(e) - Math.tan(Clat)*Math.sin(e);
	  x = Math.cos(Clong);
	  
	  RAmoon = Math.atan2(y, x);
	  
	  // Sun.
	  Decsun = Math.asin(Math.sin(e)*Math.sin(lambdaSun));
	  
	  y = Math.sin(lambdaSun)*Math.cos(e);
	  x = Math.cos(lambdaSun);
	  
      RAsun = Math.atan2(y, x);
      


   }   
   /* Utility functions */
   
   private double radiansToDegrees(double radians)
   {
	  return 180 * radians / Math.PI;
   }   
   // Calculates dividend modulus divisor for real numbers.
   private double ratMod(double dividend, double divisor)
   {
	  return dividend - (divisor * Math.floor(dividend/divisor));
   }   
   public void RiseSet(double latitude, double longitude)
   {  
	  // Set D to current date, midnight.
	  JDate D = new JDate(currentDate.getYear(),
								  currentDate.getMonth(),
								  (int)currentDate.getDay());
								  
	  JDate Dminus1 = new JDate(currentDate.getYear(),
								  currentDate.getMonth(),
								  (int)(currentDate.getDay() - 1));
								  
	  JDate Dplus1 = new JDate(currentDate.getYear(),
								  currentDate.getMonth(),
								  (int)(currentDate.getDay() + 1));                            
	  
	  double T = (D.getJD() - 2451545.0)/36525;
	  
	  // Sidereal time at midnight, current day.
	  double theta0 = 100.46061837 + T * (36000.770053608 +
									 T * (0.000387933 -
									 T * (1/38710000)));
	  
	  theta0 = degreesToRadians(theta0);
	  theta0 = adjustRange(theta0);
	  
	  Moon moon = new Moon();
	  
	  // Find the position of the sun and moon on the current day.
	  moon.setDate(D);
	  moon.position(); 
	  double moonAlpha2 = moon.getRAmoon(); 
	  double moonDelta2 = moon.getDecmoon();
	  double sunAlpha2 = moon.getRAsun();
	  double sunDelta2 = moon.getDecsun();
	  
	  double rho = moonDistance() / 384401;
	  parallax = degreesToRadians(0.9507) / rho;
	  double h0 = (0.7275 * parallax) - degreesToRadians(0.5666666667);
	  
	  // Extra variables for better Moon approximation.
	  moon.setDate(Dminus1);
	  moon.position(); 
	  double moonAlpha1 = moon.getRAmoon(); 
	  double moonDelta1 = moon.getDecmoon();
	  
	  moon.setDate(Dplus1);
	  moon.position(); 
	  double moonAlpha3 = moon.getRAmoon(); 
	  double moonDelta3 = moon.getDecmoon();
	  
	  moonAlpha1 = adjustRange(moonAlpha1);
	   moonAlpha2 = adjustRange(moonAlpha2);
	   moonAlpha3 = adjustRange(moonAlpha3);
	  
	  // Do calculations for the Moon.
	
	  double temp = (Math.sin(h0) - (Math.sin(latitude) * Math.sin(moonDelta2))) /
					(Math.cos(latitude) * Math.cos(moonDelta2));
	  
	  if (Math.abs(temp) > 1.0)
	  {
		 MoonCircumpolar = true;
	  }
	  else
	  {  
		 moonrise.setJD(D.getJD());
		 moonset.setJD(D.getJD());
					
	      double H0 = Math.acos(temp);
	      
	      double m0 = (moonAlpha2 + longitude - theta0) / (2*Math.PI);
	      if (m0 < 0)
	         m0 += 1.0;
	      if (m0 >= 1.0)
	         m0 -= 1.0;
	         
	      double m1 = m0 - (H0 / (2*Math.PI));
	      if (m1 < 0)
	         m1 += 1.0;
	      if (m1 >= 1.0)
	         m1 -= 1.0;
	        
	      double m2 = m0 + (H0 / (2*Math.PI));
	      if (m2 < 0)
	         m2 += 1.0;
	      if (m2 >= 1.0)
	         m2 -= 1.0;
	         
	      moonrise.addFrac(m1);
	      moonrise.addFrac(-moonrise.getZone()/24);
	      
	      moonset.addFrac(m2);
	      moonset.addFrac(-moonset.getZone()/24);
	      
	      MoonCircumpolar = false;
	   }
	      
	      // Do calculations for Sun.
	      
	  h0 = degreesToRadians(-0.8333);
   
	  temp = (Math.sin(h0) - (Math.sin(latitude) * Math.sin(sunDelta2))) /
					(Math.cos(latitude) * Math.cos(sunDelta2));
	  
	  if (Math.abs(temp) > 1.0)
	  {
		 SunCircumpolar = true;
	  }
	  else
	  {  
		 sunrise.setJD(D.getJD());
		 sunset.setJD(D.getJD());
					
	      double H0 = Math.acos(temp);
	      
	      double m0 = (sunAlpha2 + longitude - theta0) / (2*Math.PI);
	      if (m0 < 0)
	         m0 += 1.0;
	      if (m0 >= 1.0)
	         m0 -= 1.0;
	         
	      double m1 = m0 - (H0 / (2*Math.PI));
	      if (m1 < 0)
	         m1 += 1.0;
	      if (m1 >= 1.0)
	         m1 -= 1.0;
	         
	      double m2 = m0 + (H0 / (2*Math.PI));
	      if (m2 < 0)
	         m2 += 1.0;
	      if (m2 >= 1.0)
	         m2 -= 1.0;
	      
	      sunrise.addFrac(m1);
	      sunrise.addFrac(-sunrise.getZone()/24);
	      sunset.addFrac(m2);
	      sunset.addFrac(-sunrise.getZone()/24);
	      
	      SunCircumpolar = false;
	  }
   }   
   public void setDate(JDate date)
   {
	  currentDate.setJD(date.getJD());
   }   
   public void setLatitude(double latitude)
   {
	  Latitude = latitude;
   }   
   public void setLongitude(double longitude)
   {
	  Longitude = longitude;
   }   
   public double sunDistance()
   {
	  return (149598500 * (1 - 0.016718*0.016718))/
			 (1 + 0.016718*Math.cos(meanAnomalySun + EcSun));
   }   
   public JDate sunRise()
   {
	 return sunrise;
   }   
   public JDate sunSet()
   {
	  return sunset;
   }   
}