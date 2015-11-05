public class Mobil implements Kendaraan
{
    private String plat;

    public Mobil(String plat)
    {
        this.plat = plat;
    }

    public String plat()
    {
       return plat; 
    }
    
    public String tipe()
    {
        String tipe = new String("Mobil");
        return tipe;
    }
    
    
}
