import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Write a description of class KopiBerkas here.
 * 
 * @author (Yeni H Saragih) 
 * @version (06 November 2015)
 */
public class KopiBerkas
{
    
    
    
     public void kopi(String sumber, String sasaran) throws IOException {
        FileInputStream masukan = null;
        FileOutputStream keluaran = null;
        // Deklarasi variabel
        try {
            // Object stream untuk masukkan
            masukan = new FileInputStream(sumber);
            keluaran = new FileOutputStream(sasaran);
            
            // Coba baca  dari stream
            int karakter = masukan.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan
                //System.out.print((char) karakter);
                // Coba baca lagi dari stream
                keluaran.write(karakter);
                karakter = masukan.read();
                
            }
            keluaran.flush();
        } 
        catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }
        finally {
            // Tutup stream masukan
            if (masukan != null)
                masukan.close();
                if (keluaran != null)
               keluaran.close();
            }
        
    }
    
   

}
