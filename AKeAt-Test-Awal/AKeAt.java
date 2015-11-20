import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;

public class AKeAt {
    public void aKeAt(String sumber, String sasaran) throws IOException {
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
                if (karakter=='a' || karakter=='A'){
                   keluaran.write('@');
                }else{
                   keluaran.write(karakter);
                }
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