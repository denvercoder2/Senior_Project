// Placement for the java parsing program


public class Moon{

    // add attributes as needed
    String phase;
    boolean visability;
    double distance;
    double x_location;
    double y_location;


    //constructor
    public Moon(String phase, boolean visability,
                double distance, double x_location, double y_location){

                     this.phase      = phase;
                     this.visability = visability;
                     this.distance   = distance;
                     this.x_location = x_location;
                     this.y_location = y_location;
                }
    public String getPhase(){
        return phase;
    }
    // may need to edit this as
    // it will change
    public boolean getVisability(){
        return true;
    }
    public double getDistance(){
        return distance;
    }
    public double getX_Location(){
        return x_location;
    }
    public double getY_Location(){
        return y_location;
    }


    public static void main(String[] args){
        System.out.println("Working");
    }
}