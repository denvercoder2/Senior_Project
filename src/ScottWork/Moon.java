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


    /*
    Rules include:
        - Finding the pattern for the phases
        - Mapping this pattern to a given date
        - it takes the moon 29.53 days to complete
        - A full cycle through its phases takes 29.53 days
        - Source: https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf
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

        for (int i = 0; i < phases.length; i++){

            System.out.println(phases[i]);
        }


        String sample = "TEST";
        return sample;
    
    }


    public static void main(String[] args) {
        String phase = getPhase(10, 16, 1996);


    }
}