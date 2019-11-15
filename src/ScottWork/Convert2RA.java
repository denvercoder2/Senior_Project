/*
 Converting RAHour and Mintute to Singular RA
*/

public class Convert2RA{

    /*
*    Function: Convert
*    Parameters: double, double, double
*    Return Types: double
*           Purpose: 
*    To return the RA from the Hour, Minute, and Second        
    */
    public static double Convert(double RAHour, double RAMinute, double RASeconds){
        // Ratio for conversion
        double RA = (RAHour * 15 + RAMinute * .25 + RASeconds * .00416);

        return RA;
    }

    public static void main(String[] args) {
        double RAHour = 20.00;
        double RAMinute = 12.00;
        double RASecond = 31.00;

        double RA = Convert(RAHour, RAMinute, RASecond);

        System.out.printf("\nRA Hour, Minute, and Second converted to singular RA: %.2f", RA);
    }
}