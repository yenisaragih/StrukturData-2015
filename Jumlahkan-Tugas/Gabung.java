import java.lang.Thread;
public class Gabung
{
    private double[] data;
    private Kelompok[] kelompok= new Kelompok[5];
    public Gabung(double[] data)
    {
       this.data = data;
       
       for(int i=0; i<5; i++){ 
            int awal = (data.length/5) *i;
            int akhir = awal+(data.length/5) -1;
            kelompok[i] = new Kelompok(awal,akhir,data);
       }
       Thread thread01 = new Thread (kelompok[0]);
       Thread thread02 = new Thread (kelompok[1]);
       Thread thread03 = new Thread (kelompok[2]);
       Thread thread04 = new Thread (kelompok[3]);
       Thread thread05 = new Thread (kelompok[4]);
       
       thread01.start();
       thread02.start();
       thread03.start();
       thread04.start();
       thread05.start();
    }
    
    public double hasil(){
        double hasil = 0;
       try {
           Thread.sleep(1000);
        }
       catch (InterruptedException e){}
       
       for(int i=0; i<5; i++)
            hasil = hasil + kelompok[i].hasil();
       return hasil;
    }
        
}
