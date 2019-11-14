/*
New Moon
*/

import java.util.Calendar;

public class MoonPhase{

    // Constructor
    public static class Moon{
        private String phase;
    
    // getters and setters
    public void setMoonPhase(String Phase){
        this.phase = Phase;
    }
    public String getMoonPhase(){
        return this.phase;
    }

    }


    public static void ConvertCal(){
        // Create calendar object
        Calendar c = Calendar.getInstance(); 
        System.out.println("The Current Date is:" + c.getTime());   


    }




    public static void main(String[] args){
        ConvertCal();
    }
}