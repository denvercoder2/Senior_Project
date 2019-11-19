// Scott moon position
import java.math.*;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class MoonPoint{

    public static double RAmoon; // Moon Right Ascension.
    public static double Decmoon; // Moon Declination.
    public static double RAsun; // Sun Right Ascension.
    public static double Decsun; // Sun Declination.
    public static double Clat; // Moon's Celestial Latitude.
    public static double Clong; // Moon's Celestial Longitude.


    public static double JDCalc(int day, int month, int year){
        double JD = 0.0;
        double T;
        if (month < 2){
            year = year - 1;
            month = month + 12;
        }
        double epoch = 2451545.0 ; // 2000
        int A = year/100;
        int B = A/4;
        int C = (2 - A + B);
        int E = (int)(365.25 * (year + 4716));
        int F = (int)(30.6001 * (month + 1));
        JD =  C + day + E + F - 1524.5;
        T = JD - epoch;

            // Julian Centuries since Epoch
            return JD;
    }

    // Mod2Pi
    public static double fixRange(double ang){
       double temp = ang;
       
       while (temp > 2*Math.PI)
          temp = temp - 2*Math.PI;
          
       while (temp < 0)
          temp = temp + 2*Math.PI;
          
       return temp;
    }   

    public static double degreesToRadians(double degrees){
	  return Math.PI * degrees / 180;
   }   

   public static double meanObliquity(double date)
   {
	  double T = (date - 2415020.0) / 36525;
	  
	  double de = T * (46.845 + T * (0.0059 - T * (0.00181)));
	  
	  de = de / 3600;
	  
	  double e = 23.452294 - de;
	  
	  return degreesToRadians(e);
   }   

   public static double[] position(int d, int m, int y){
        double SunLambda = 0;
        double meanAnomalySun = 0;
        double lPrimePrime = 0;
        double MPrimeM = 0;
        double EcSun = 0;
        double EcMoon = 0;
        double parallax = 0;
        double epoch = 2444238.5 ; // 1980

        // Days since epoch. We use Universal time for these calculations.
        double jd = JDCalc(d, m, y);
        double D = jd - epoch;
            
        // Find the Sun's position.
        
        double N = (D * 2 * Math.PI) / 365.2422;
        N = fixRange(N);
        
        meanAnomalySun = N + 4.86656333799 - 4.93223768664;
        
        if (meanAnomalySun < 0)
            meanAnomalySun = meanAnomalySun + 2*Math.PI;
            
        EcSun = 2 * 0.016718 * Math.sin(meanAnomalySun);
        
        SunLambda = N + EcSun + 4.86656333799;
        
        if (SunLambda > 2*Math.PI)
            SunLambda = SunLambda + 2*Math.PI;
            
        // Find the Moon's position.
        
        double l = degreesToRadians(13.1763966 * D) + 1.13403577981;
        l = fixRange(l);
        
        double Mm = l - degreesToRadians(0.1114041 * D) - 6.09788480005;
        Mm = fixRange(Mm);
        
        N = 2.65203528587 - degreesToRadians(0.052953 * D);
        N = fixRange(N);
        
        double C = (l - SunLambda);
        double Ev = degreesToRadians(1.2739 * Math.sin(2*C - Mm));
        
        double Ae = degreesToRadians(0.1858 * Math.sin(meanAnomalySun));
        double A3 = degreesToRadians(0.37 * Math.sin(meanAnomalySun));
        
        MPrimeM = Mm + Ev - Ae - A3;
        
        EcMoon = degreesToRadians(6.2886 * Math.sin(MPrimeM));
        
        double A4 = degreesToRadians(0.214 * Math.sin(2*MPrimeM));
        
        double lPrime = l + Ev + EcMoon - Ae + A4;
        
        double V = degreesToRadians(0.6583 * Math.sin(2 * (lPrime - SunLambda)));
        
        lPrimePrime = lPrime + V;
        
        double NPrime = N - degreesToRadians(0.16 * Math.sin(meanAnomalySun));
        
        double y_c = Math.sin(lPrimePrime - NPrime) * Math.cos(0.0898041015189);
        double x_c = Math.cos(lPrimePrime - NPrime);
        
        double temp = Math.atan2(y_c, x_c);
        
        Clong = temp + NPrime;
        
        temp = Math.sin(lPrimePrime - NPrime) * Math.sin(0.0898041015189);
        
        Clat = Math.asin(temp);
        
        // Calculate right ascension and declination.
        double e = meanObliquity(jd);
        
        // Moon.
        Decmoon = Math.asin(Math.sin(Clat)*Math.cos(e) + 
                        Math.cos(Clat)*Math.sin(e)*Math.sin(Clong));
                        
        double y_m = Math.sin(Clong)*Math.cos(e) - Math.tan(Clat)*Math.sin(e);
        double x_m = Math.cos(Clong);
        
        RAmoon = Math.atan2(y_m, x_m);

        double[] rd = new double[3];
        rd[0] = RAmoon;
        rd[1] = Decmoon;

        return rd;
   }   


   public static void main(String[] args){
        int d = 19;
        int m = 5;
        int y = 1991;

        double[] temp;
        temp = position(d, m, y);
        System.out.printf("RA : %.5f",temp[0]);
        System.out.printf("\nDec : %.5f",temp[1]);
   }
}