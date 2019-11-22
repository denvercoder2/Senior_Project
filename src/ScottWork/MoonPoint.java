// Scott moon position
import java.math.*;
import java.util.ArrayList;

public class MoonPoint{

    public static class Coordinates{
        private String Lat;
        private String Long;
    }

    public static double getJD(int year, int month, int day){
        double JD = 0.0;
        if (month > 2){
            year = year - 1;
            month = month + 12;
        }
        // source: http://home.hiwaay.net/~krcool/Astro/moon/moonphase/

        // This is essentially a barebones JulianDay method
        int A = year/100;
        int B = A/4;
        int C = (2 - A + B);
        int E = (int)(365.25 * (year + 4716));
        int F = (int)(30.6001 * (month + 1));
        JD =  C + day + E + F - 1527.5;
        
        return JD;
    }
    /*
    Function: SunMoonPosition
    Parameters: int year, int month, int day
    Return Type: ArrayList<String>
    Purpose:
    Compute location of Sun and Moon on Given day
    */
    public static ArrayList<String> SunMoonPosition(int year, int month, int day){
        Coordinates coords = new Coordinates();
        ArrayList<String> Coordinates_hold = new ArrayList<>();


        double JD_Epoch = 2455196.5;    // 2010 JD
        double ELong =  279.557208;     // 2010
        double ELongPer =  283.112438; // longitude of the Sun at perigee

        // finding the sun
        double e =  0.016705;   // eccentricity of orbit at epoch 2010
        double JD = getJD(year, month, day);
        double D = JD - JD_Epoch;
        double T = D/36525.0;
        double N = (360/365.242191) * D;
        
        while(N < 0){
            N = N + 360.00;
        }
        double M = N + ELong - ELongPer;
        if(M < 0){
            M = M + 360.00;
        }
        double Ec = (360/Math.PI) * e * Math.sin(M);
        double lam = N + Ec + ELong;    // Sun's geocentric eliptic longitude
        double SunLat = 0;              // suns latitude
        while(lam > 360.0){
            lam = lam - 360.0;
        }


        // System.out.printf("JD: %f", JD);
        // System.out.printf("\nMJD: %f", D);
        // System.out.printf("\nT:  %f", T);
        // System.out.printf("\nN:  %f", N);
        // System.out.printf("\nM:  %f", M);
        // System.out.printf("\nEc:  %f", Ec);

        System.out.printf("\nSun's geocentric eliptic longitude:  %f degrees", lam);
        System.out.printf("\nSun's geocentric eliptic Latitude:  %f degrees", SunLat);


        // finding the moon
        double Moon_epoch = 91.929336;
        double L = (13.1763966 * D) + Moon_epoch; // moons mean longitude 
        double P0 = 130.143076;                   // mean longitude of the perigee at the epoch
        double Moon_meanAnom = L - (0.1114041 * D) - P0; //  Moon’s mean anomaly
        double Node = 291.682547 - (0.0529539 *  D); //  ascending node’s mean longitude
        double C = L - lam;
        double Ev = 1.2739 * Math.sin((2*C) - Moon_meanAnom); 
        double Ae = 0.1858 * Math.sin(M);
        double A3 = 0.37 * Math.sin(M);

        double MoonCorrectAnom = Moon_meanAnom + Ev + Ae - A3;

        double EcCorrected = 6.2886 * Math.sin(MoonCorrectAnom);

        double A4 = 0.214 * Math.sin((2*Moon_meanAnom));

        // moons corrected longitude
        double L_prime = L + Ev + Ec + Ae + A4;

        // The final correction to apply to the Moon’s longitude is the variation, V
        double V = 0.6583 * Math.sin(2 * (L_prime - lam));

        // Moon’s true orbital longitude
        double L_prime2 = L_prime + V;

        double N_prime = N - (.16 * Math.sin(M));
        // inclination of Moon’s orbit
        double i = 5.145396;
        // Finally, ecliptic longitude
        double MoonEclipLong = Math.atan((Math.sin(L_prime2 - N_prime) * Math.cos(i) / Math.cos(L_prime2 - N_prime)) + N_prime) * 115.33896;

        // and ecliptical latitude
        double MoonEclipLat = Math.sin(Math.sin(L_prime2 - N_prime) * Math.sin(i)) * 7.46778;
        coords.Lat = String.valueOf(MoonEclipLat);
        coords.Long = String.valueOf(MoonEclipLong);

        Coordinates_hold.add(String.valueOf(MoonEclipLong));
        Coordinates_hold.add(String.valueOf(MoonEclipLat));

        return Coordinates_hold;
    }


   public static void main(String[] args){
        int month = 9;
        int day = 17;
        int year = 2019;

        Coordinates coords = new Coordinates();
        ArrayList<String> Coordinates_AL = new ArrayList<>();
        Coordinates_AL = SunMoonPosition(year, month, day);

        System.out.printf("\n\nFor date: %d, %d, %d", month, day, year);
        System.out.printf("\nMoon's Ecliptic Longitude: %s",Coordinates_AL.get(0));
        System.out.printf("\nMoon's Ecliptic Latitude: %s",Coordinates_AL.get(1));    
   }
}