public class Becak implements Kendaraan
{
    private String plat;

    /**
     * Constructor for objects of class Becak
     */
    public Becak(String plat)
    {
        this.plat = plat;
    }
   
    public String plat()
    {
       return plat;
    }
    
    public String tipe(){
        String tipe = new String("Becak");
        return tipe;
    }
    
}
