/*
 Converting RAHour and Mintute to Singular RA
*/

public class Convert2RA{

    /*
*    Function: Convert
*    Parameters: double, double
*    Return Types: double
*           Purpose: 
*    To return the RA from the Hour and Minute           
    */
    public static double Convert(double RAHour, double RAMinute){
        double RA = (RAHour * 15 + RAMinute * .25);

        return RA;
    }

    public static void tests(){
        double RAHour = 20.00;
        double RAMinute = 12.00;
        double RA = Convert(RAHour, RAMinute);
        
        // java test case 1
        if(RA != 303.00){
            System.out.println("Test case Failed");
        }
        else{
            System.out.println("Test case Passed");
        }

        double RAHour2 = 10.00;
        double RAMinute2 = 10.00;
        double RA2 = Convert(RAHour2, RAMinute2);

        if(RA2 != 152.50){
            System.out.println("Test case Failed");
            System.out.printf("Answer should be: %.2f", RA2);
        }
        else{
            System.out.println("Test case Passed");
        }

    }

    public static void main(String[] args) {
        double RAHour = 20.00;
        double RAMinute = 12.00;
        tests();

        double RA = Convert(RAHour, RAMinute);

        System.out.printf("\nRA Hour and Minute converted to singular RA: %.2f", RA);
    }
}