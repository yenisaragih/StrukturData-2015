import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;
/**
 * @author (Yeni H Saragih) 
 * @version (9 November 2015)
 */
public class Upper
{  
    public void upper(String sumber, String sasaran) throws IOException {
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
                karakter = Character.toUpperCase(karakter);
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

