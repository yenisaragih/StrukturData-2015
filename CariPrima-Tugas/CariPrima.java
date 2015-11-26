import java.io.FileWriter;
import java.io.IOException;

public class CariPrima {
    public static void main() throws IOException {
        // Buat berkas untuk menulis hasil. Pakai WRITER karena yang ditulis 
        // berkas text.
        FileWriter berkas = new FileWriter(NAMA_BERKAS);
        
        // Buat array dari thread
        BenarPrima[] benarPrima = new BenarPrima[JUMLAH_THREAD];
        Thread [] thread = new Thread[JUMLAH_THREAD];
        // Mulai hitung dari angka 2, karena 1 otomatis bukan prima
        int angka = 2;
        // Loop sampai batas atas yang diminta
        while (angka<=ANGKA_TERBESAR) {
          
          for(int j=0; j<JUMLAH_THREAD;j++){
         
             benarPrima[j] = new BenarPrima(angka);
             thread[j] = new Thread(benarPrima[j]);
             angka++;
            
          }
          
          for (int counter = 0; counter < JUMLAH_THREAD; ++counter)
                thread[counter].start();
          
          for (int counter = 0; counter < JUMLAH_THREAD; ++counter)
            while (benarPrima[counter].selesai() == false) { }

          for(int x=0; x<JUMLAH_THREAD;x++){
            if(benarPrima[x].selesai()){
                if(benarPrima[x].prima()){
                    
                  synchronized(berkas) {
                     try {
                          berkas.write(benarPrima[x].angka()+"\n");
                       }
                       catch (IOException kesalahan) {
                          System.out.printf("Terjadi kesalahan: %s", kesalahan);
                     }
                  }
                  
                }
            }   
          }
        }
        
            berkas.close();
      }
  
    
     private final static String NAMA_BERKAS = "prima.log";
     private final static int JUMLAH_THREAD = 10;
     private final static int ANGKA_TERBESAR = 100000;
    }
    