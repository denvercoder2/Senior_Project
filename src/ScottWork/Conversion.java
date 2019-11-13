/*
 3D to 2D space
*/
import java.io.*;
import java.util.*;


public class Conversion{


    public static double[] UV_Conversion(double x, double y, double z){
        double[] ConversionArr = new double[2];
        double U = x/z;
        double Y = y/z;

        ConversionArr[0] = U;
        ConversionArr[1] = Y;

        return ConversionArr;
        
    }

    public static void main(String[] args){
        double x = 3.00;
        double y = 5.00;
        double z = 12.00;

        double[] temp = UV_Conversion(x,y,z);
        System.out.printf("X, Y, Z coordinates converted to U,V: %.5f %.5f", temp[0], temp[1]);

    }
}
