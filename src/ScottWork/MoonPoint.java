// Scott moon position
import java.math.*;
import java.util.ArrayList;

public class MoonPoint{

    public static class Coordinates{
        private String Lat;
        private String Long;
    }

    public double radian2degree(double rad){
        return rad * (180.00/Math.PI);
    }

	public static double toJulianDay(int year, int month, int day) throws Exception {
		// The conversion formulas are from Meeus, chapter 7.
		boolean julian = false; // Use Gregorian calendar
		
		if (year < 1582 || (year == 1582 && month <= 10) || (year == 1582 && month == 10 && day < 15)){
			julian = true;
		}

		int D = day;
		int M = month;
		int Y = year;
		if (M < 3) {
			Y--;
			M += 12;
		}
		int A = Y / 100;
		int B = julian ? 0 : 2 - A + A / 4;

		// double dayFraction = (hours + minutes) / 24.0;
		double jd = (int) (365.25D * (Y + 4716)) + (int) (30.6001 * (M + 1)) + D + B - 1524.5;

		if (jd < 2299160.0 && jd >= 2299150.0)
			throw new Exception("invalid julian day " + jd + ". This date does not exist.");

		return jd;
    }
    

    public static void test() throws Exception{
        int year = 2003;
        int month = 9;
        int day = 1;
        int hours = 0;
        int minutes = 0;
        double JD_Epoch = 2455196.5;    // 2010 JD
        double ELong =  279.557208;     // 2010
        double ELongPer =  283.112438; // longitude of the Sun at perigee

        double FindDate = toJulianDay(year, month, day, hours, minutes);
        double daysSinceEpoch = FindDate - JD_Epoch; //D
        System.out.printf("\nDays since epoch: %f", daysSinceEpoch);

        double e =  0.016705;   // eccentricity of orbit at epoch 2010
        // double JD = toJulianDay(year, month, day, hours, minutes);
        // double D = JD - JD_Epoch;
        double T = daysSinceEpoch/36525.0;
        double N = (360/365.242191) * daysSinceEpoch;
        
        
        // double N0 =  291.682547;
        // double N = N0 - .0529539 * daysSinceEpoch;
        while(N > 360){
            N = N - 360.00;
        }
        double M = N + ELong - ELongPer; // mean anomaly of sun
        while(M < 0){
            M = M + 360.00;
        }
        
        double Ec = (360/Math.PI) * e * Math.sin(M);
        System.out.printf("\nEc: %f", Ec);
        double lam = N + Ec + ELong - 2.322242;   // Sun's geocentric eliptic longitude
        double SunLat = 0;              // suns latitude
        while(lam > 360.0){
            lam = lam - 360.00;
        }
        double Moon_epoch = 91.929336;
        double L = (13.1763966 * daysSinceEpoch) + Moon_epoch; // moons mean longitude 
        while(L < 0){
            L = L + 360.00;
        }
        double i = 5.145396;
        System.out.printf("\nLambda: %f", lam);
        System.out.printf("\nM: %f", M);
        System.out.printf("\nl: %f", L);
        double P0 = 130.143076;          
        double MoonAnom = L - 0.1114041 * daysSinceEpoch -  P0;
        System.out.printf("\nMoonAnom: %f", MoonAnom);
        System.out.printf("\nN: %f", N);

        double C = L - lam;
        // !!!
        double Ev = 1.2739 * Math.sin(2*C - MoonAnom) * -1;
        System.out.printf("\nEv: %f", Ev);
        double Ae = .1858 * Math.sin(M);
        System.out.printf("\nAe: %f", Ae);
        double A3 = .37 * Math.sin(M);
        System.out.printf("\nA3: %f", A3);

        double correctedMoonAnom = MoonAnom + Ev - Ae - A3;
        System.out.printf("\nCorrected Moon Anom: %f", correctedMoonAnom);
        
        double A4 = .214 * Math.sin(correctedMoonAnom * 2);
        System.out.printf("\nA4: %f", A4);

        double LPrime = L + Ev + Ec - Ae  + A4;
        System.out.printf("\nLPrime: %f", LPrime);

        double V = .6583 * Math.sin(2 *(LPrime - lam));
        System.out.printf("\nV: %f", V);

        double LPrime2 =  LPrime + V;
        System.out.printf("\nLPrime2: %f", LPrime2);

        double NPrime = N - .16 * Math.sin(M);
        System.out.printf("\nNPrime: %f", NPrime);

        double y = Math.sin(LPrime2 - NPrime) * Math.cos(i);
        System.out.printf("\ny: %f", y);

        double x = Math.cos(LPrime2 - NPrime) * -1;
        System.out.printf("\nx: %f", x);

        double temp = Math.atan(y/x);
        while(temp < 0){
            temp = temp + 180;
        }
        System.out.printf("\ntemp: %f", temp);

        double lamMoon = temp + NPrime;
        System.out.printf("\nLambdaMoon: %f", lamMoon);

        double delta = Math.asin(Math.sin(LPrime2 - NPrime) * Math.sin(i));
        System.out.printf("\nDelta: %f", delta);
    }


    /*
    Function: SunMoonPosition
    Parameters: int year, int month, int day
    Return Type: ArrayList<String>
    Purpose:
    Compute location of Sun and Moon on Given day
    */
    public static ArrayList<String> SunMoonPosition(int year, int month, int day, int hours, int minutes) throws Exception{
        Coordinates coords = new Coordinates();
        ArrayList<String> Coordinates_hold = new ArrayList<>();


        double JD_Epoch = 2455196.5;    // 2010 JD
        double ELong =  279.557208;     // 2010
        double ELongPer =  283.112438; // longitude of the Sun at perigee

        // finding the sun
        double e =  0.016705;   // eccentricity of orbit at epoch 2010
        double JD = toJulianDay(year, month, day, hours, minutes);
        double D = JD - JD_Epoch;
        double T = D/36525.0;
        double N = (360/365.242191) * D;
        
        while(N < 0){
            N = N + 360.00;
        }
        double M = N + ELong - ELongPer; // mean anomaly of sun
        if(M < 0){
            M = M + 360.00;
        }
        double Ec = (360/Math.PI) * e * Math.sin(M);
        double lam = N + Ec + ELong - 2.322242;   // Sun's geocentric eliptic longitude
        double SunLat = 0;              // suns latitude
        while(lam > 360.0){
            lam = lam - 360.00;
        }

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

        double MoonCorrectAnom = Moon_meanAnom + Ev - Ae - A3;

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
        double MoonEclipLong = Math.atan(((Math.sin(L_prime2 - N_prime) * Math.cos(i)) / Math.cos(L_prime2 - N_prime)) + N_prime);

        // and ecliptical latitude
        double MoonEclipLat = Math.asin(Math.sin(L_prime2 - N_prime) * Math.sin(i));
        coords.Lat = String.valueOf(MoonEclipLat);
        coords.Long = String.valueOf(MoonEclipLong);

        Coordinates_hold.add(String.valueOf(MoonEclipLong));
        Coordinates_hold.add(String.valueOf(MoonEclipLat));

        return Coordinates_hold;
    }


   public static void main(String[] args)throws Exception{
       // check source http://time.unitarium.com/moon/where.html
        // int month = 12;
        // int day = 11;
        // int year = 2019;
        // int hours = 14;
        // int minutes = 6;

        // Coordinates coords = new Coordinates();
        // ArrayList<String> Coordinates_AL = new ArrayList<>();
        // Coordinates_AL = SunMoonPosition(year, month, day, hours, minutes);

        // System.out.printf("\n\nFor date: %d, %d, %d", month, day, year);
        // System.out.printf("\nMoon's Ecliptic Longitude: %s",Coordinates_AL.get(0));
        // System.out.printf("\nMoon's Ecliptic Latitude: %s",Coordinates_AL.get(1));    

        test();
   }
}