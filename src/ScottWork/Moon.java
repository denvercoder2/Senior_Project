// Placement for the java parsing program

import java.util.*;

public class Moon {

    // add attributes as needed
    String phase;
    double size;
    boolean visability;
    double distance;
    double x_location;
    double y_location;

    // constructor
    public Moon(String phase, double size, boolean visability, double distance, double x_location, double y_location) {

        this.phase = phase;
        this.size = size;
        this.visability = visability;
        this.distance = distance;
        this.x_location = x_location;
        this.y_location = y_location;
    }

    // getters
    public String getPhase() {
        return phase;
    }

    public double getSize() {
        return size;
    }

    // may need to edit this as
    // it will change
    public boolean getVisability() {
        return true;
    }

    public double getDistance() {
        return distance;
    }

    public double getX_Location() {
        return x_location;
    }

    public double getY_Location() {
        return y_location;
    }

    // function for returning the phase of the moon
    // given an arbitrary day
    // source https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf

    public static double JD(int year, int month, int day){
        double A = (int)year/100;
        double B = (int)A/4;
        double C = 2 - A + B;
        double E = (int)(365.25 * (year + 4713));
        double F = (int)(30.6001 * (month + 1));
        double julianDay = C + day + E + F - 1524.5; 

        return julianDay;
    }
    /*
    Rules include:
        - Finding the pattern for the phases
        - Mapping this pattern to a given date
        - it takes the moon 29.53 days to complete
          a full cycle through its phases
    */
    // public static String getPhase(int day, int month, int year) {
    //     // January 1st, 1900 was a new moon
    //     String new_moon = "New Moon";
    //     // String 

        /*
         * Define the pattern that the moon follows Take the parameters and map to rules
         * Return what phase the moon is in
         */

    // }

    public static void main(String[] args) {
        // Moon moon = new Moon();
        // String phase_moon = getPhase(1900, 1, 1);
        double jd = JD(2017, 3, 1);
        double days_since_newMoon = jd - 24515949.5;
        double new_moons = days_since_newMoon/29.53;

        System.out.println(new_moons);
    }
}