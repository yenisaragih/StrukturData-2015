import java.util.Date;
/**
 * Write a description of class Vector4D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector4D extends Vector3D
{
    // instance variables - replace the example below with your own
    private Date t;

    /**
     * Constructor for objects of class Vector4D
     */
    public Vector4D(double x,double y,double z, Date t)
    {
        // initialise instance variables
        super(x,y,z);
        this.t = t;
        
    }
    public Date getT(){
        return t;
    }
    
}
