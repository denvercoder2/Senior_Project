/*
    3D to 2D U,V coordinated
    u = x / z;
    v = y / z;
*/
import java.io.*;
import java.util.*;

public class Conversion {

    public static double[] UV_Coordinates(double x, double y, double z){
        double[] coordinateArr = new double[2];
        double u = x / z;
        double v = y / z;


        coordinateArr[0] = u;
        coordinateArr[1] = y;

        return coordinateArr;

    }

    public static void main(String[] args) throws IOException{
        double x = 5.00;
        double y = 3.00;
        double z = 12.00;

        double[] temp = UV_Coordinates(x,y,z);

        System.out.printf("X, Y, Z coordinate converted to U,V: %.5f %.5f ", temp[0], temp[1]);

    }
}
