package ScottWork.main;
/*
 Converting RAHour and Mintute to Singular RA
*/
// package ScottWork;
public class Convert2RA{

    /*
*    Function: Convert
*    Parameters: double, double, double
*    Return Types: double
*           Purpose: 
*    To return the RA from the Hour, Minute, and Second        
    */
    public static double ConvertRA(double RAHour, double RAMinute){
        // Ratio for conversion
        double RA = (RAHour * 15 + RAMinute * .25);

        return RA;
    }
    /*
*    Function: ConvertDec
*    Parameters: double, double
*    Return Types: double
*           Purpose: 
*    To return the Dec from Degree and Minute
    */
    public static double ConvertDec(double dec, double minute){
        // Ratio for conversion
        // 60 archminutes per degree
        double Dec = (dec + minute/60);

        return Dec;
    }


    public static void main(String[] args) {
        double RAHour = 20.00;
        double RAMinute = 12.00;

        double Decdeg = 9.00;
        double decMin = 24.00;

        double Dec = ConvertDec(Decdeg, decMin);


        double RA = ConvertRA(RAHour, RAMinute);

        System.out.printf("\nRA Hour, Minute, and Second converted to singular RA: %.2f", RA);
        System.out.printf("\nDec Degree and Minute converted to singular Declination: %.2f", Dec);
    }
}