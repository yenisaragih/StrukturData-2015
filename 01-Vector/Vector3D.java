
/**
 * Write a description of class Vector3D here.
 * 
 * @author (Yeni H Saragih) 
 */
public class Vector3D
{
    // instance variables - replace the example below with your own
     private double x;
     private double y;
     private double z;
    /**
     * Constructor for objects of class Vector3D
     */
    public Vector3D(double x,double y,double z)
    {
        // initialise instance variables
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
}

