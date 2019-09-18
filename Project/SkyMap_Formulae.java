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
        int julianDay;
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
        int A, B;
        // may be error prone depending on how it compares index
        for (int k = 0; k < date.length; k++){
            if(date[k] > test_data[k]){
                A = (int)(y/100); // fractions are dropped
                B = 2 - A + (int)(A/4);

            }
            else{
                B = 0;
            }
            julianDay = (int)(365.25 * y) + (int)(30.6001 * (m + 1)) + d + 1720994.5 + B;
         //  return JulianDay;
        }
    }


    public static void main(String[] args){
        SkyMap_Formulae sky = new SkyMap_Formulae();
        double test_angle = sky.Mod2Pi(365.00);
        System.out.println(test_angle);


    }
}