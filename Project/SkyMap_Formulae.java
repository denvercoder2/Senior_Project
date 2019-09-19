/*
SkyMap Formulae in java
*/
import java.math.*;


public class SkyMap_Formulae{
    
    public double Mod2Pi(double angle){
        /* 
        Function will convert an angle above
        360 degrees to one less than 360
        */ 
        double b = angle / (2 * Math.PI);
        double b_floor;
        double a;

        if (b >= 0){
            b_floor = Math.floor(b); 
        }
        else{
            b_floor = Math.ceil(b);
        }
        a = (2 * Math.PI * (b - b_floor));

        if (a < 0){
            a = (2 * Math.PI + a);
        }
        double new_angle = a;
        return new_angle;
    }

    public double JulianDay(int year, int month, int day, int hour, int minute){
        /*
        Function will return the JulianDay
        given the date and time
        */
        int[] date = {year, month, day};
        int[] time = {hour, minute};
        double d = day + ((hour + (minute/60)) / 24);
        double julianDay;
        int y, m;
        if (month > 2){
            y = year;
            m = month;
        }
        else{
            y = year - 1;
            m = month + 12;
        }
        int[] test_data = {1582, 10, 15};
        int A = 0;
        int B = 0;
        // may be error prone depending on how it compares index
        for (int k = 0; k < date.length; k++){
            if(date[k] > test_data[k]){
                System.out.println(date[k]);
                A = (int)(y/100); // fractions are dropped
                B = 2 - A + (int)(A/4);

            }
            else{
                B = 0;
            }
        }
            julianDay = (int)(365.25 * y) + (int)(30.6001 * (m + 1)) + d + 1720994.5 + B;
            return julianDay;
    }

        public double get_TrueAnomoly(double meanAnom,double eccentricity){
          
            /*
            Function will calculate true anomaly
            given the mean and eccentricity (radians)
            */  
            double E = meanAnom + eccentricity * Math.sin(meanAnom) *
            (1 + eccentricity * Math.cos(meanAnom));
            double E1 = E;
            E = E1 - (E1 - eccentricity * Math.sin(E1) - meanAnom) /
            (1 - eccentricity * Math.cos(E1));
            double increment = 1.0E-12; // store for use
            while(Math.abs(E-E1) > increment){
                E1 = E;
                E = E1 - (E1 - eccentricity * Math.sin(E1) - meanAnom) /
                (1 - eccentricity) * Math.tan((.5 * E));
            }
            double anomaly = 2 * Math.atan(Math.sqrt((1 + eccentricity)
            / (1 - eccentricity)) * Math.tan(.5 * E));
            if (anomaly < 0.0){
                anomaly = anomaly + (2 * Math.PI);
            }
            return anomaly;
                
        }

        // getPosition mathod needs to be written post class
        // generartion since it takes the planet as an argument

    public static void main(String[] args){
        SkyMap_Formulae sky = new SkyMap_Formulae();
        double test_angle = sky.Mod2Pi(365.00);
        System.out.println(test_angle);
        // (int year, int month, int day, int hour, int minute) as arguments
        double jul = sky.JulianDay(1000, 12, 31, 7, 35);
        System.out.println(jul);

    }
}