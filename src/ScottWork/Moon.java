// Placement for the java parsing program

import java.util.*;

public class Moon {
    // add attributes as needed
    String phase;
    boolean visability;
    // constructor
    public Moon(String phase,boolean visability) {
        this.phase = phase;
        this.visability = visability;
    }
    // getters
    public String getPhase() {
        return phase;
    }
    // if the moon is visable
    public boolean getVisability(){
        return true;
    }
    // function for returning the phase of the moon
    // given an arbitrary day
    // source https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf

    /*
    Rules include:
        - Finding the pattern for the phases
        - Mapping this pattern to a given date
        - it takes the moon 29.53 days to complete
          a full cycle through its phases
    */
    public static String getPhase(int day, int month, int year) {
        /*
        An array of the phase names to be returned under the 
        certain rules at which they apply to
        */
        String[] phases = {"New Moon", "Waxing Crescent", "First Quater",
                            "Waxing Gibbous", "Full", "Waning Gibbous", 
                            "Third Quarter", "Waning Crescent"};
        
        // iterate the year every 365 iterations
        // iterate the day every iteration
        // the cycle takes exactly 29.53 days to go through
        // completely
        int[] start_day = {1900, 1, 1};
        int day_in_year = 366;
        for (int i = 0; i < day_in_year; i++){
            

        }

        
        int[] given_day = {year, month, day};



    }





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