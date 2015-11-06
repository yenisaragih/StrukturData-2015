 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Write a description of class Upper here.
 * 
 * @author (Yeni H Saragih) 
 * @version (06 November 2015)
 */
public class KopiBerkasBuffer
{  
    public void kopi(String sumber, String sasaran) throws IOException {
        // Deklarasi variabel
        FileInputStream masukan = null;
        FileOutputStream keluaran = null;
        BufferedInputStream masukanBuffer = null;
        BufferedOutputStream keluaranBuffer = null;
        
        try {
            // Object stream untuk masukkan
            masukan = new FileInputStream(sumber);
            masukanBuffer = new BufferedInputStream(masukan);
            keluaran = new FileOutputStream(sasaran);
            keluaranBuffer = new BufferedOutputStream(keluaran);
            
            // Coba baca  dari stream
            int karakter = masukanBuffer.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan
                //System.out.print((char) karakter);
                // Coba baca lagi dari stream
                keluaranBuffer.write(karakter);
                karakter = masukanBuffer.read();
            }
        keluaranBuffer.flush();
        } 
        catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }
        finally {
            // Tutup stream masukan
            
            if (masukanBuffer != null)
                masukanBuffer.close();
           
            if (keluaranBuffer != null)
                keluaranBuffer.close();
            }
    }
    
    
}

