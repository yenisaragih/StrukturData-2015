
/**
 * Write a description of class Profil here.
 * 
 * @author (Yeni H Saragih) 
 * @version (06 November 2015)
 */
public class Profil
{
    // instance variables - replace the example below with your own
    private String nama = new String("Yeni H Saragih");
    private String nim = new String("1408107010068");

    /**
     * Constructor for objects of class Profil
     */
    public Profil()
    {
       this.nama = nama;
       this.nim = nim;
    }
    
    public Profil(String nama, String nim)
    {
       this.nama = nama;
       this.nim = nim;
    }
    
    public String getNama(){
         return nama;
    }
    
    public String getNim(){
        return nim;
    }
}
