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

    // public double getJulianDay(double date, double time){
        /*
        Function will return the JulianDay
        given the date and time
        */
        // double JulianDay;
        // whatever java's version of a tuple is


        // return JulianDay;
    // }
    public static void main(String[] args){
        SkyMap_Formulae sky = new SkyMap_Formulae();
        double test_angle = sky.Mod2Pi(360.00);
        System.out.println(test_angle);
    }
}