import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (Yeni H Saragih) 
 * @version (06 November 2015)
 */
public class CaesarCipher
{
    private int shift;
    public CaesarCipher(int shift){
        this.shift = shift;
    }
     public void enkripsi(String sumber, String sasaran) throws IOException {
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
                karakter +=shift;
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
     public void dekripsi(String sumber, String sasaran) throws IOException {
        FileInputStream masukan = null;
        FileOutputStream keluaran = null;
        // Deklarasi variabel
        try {
            // Object stream untuk masukkan
            masukan = new FileInputStream(sumber);
            keluaran = new FileOutputStream(sasaran);
            
            // Coba baca  dari stream
            int karakter = masukan.read();
            
            while (karakter != -1) {
                karakter -= shift;
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
    public static void main(String[] args){
        try{
            CaesarCipher baru = new CaesarCipher(3);
            baru.enkripsi("yeni.txt","enkripsi.txt");
            baru.dekripsi("yeni.txt","dekripsi.txt");
        }
        catch(IOException kesalahan){
            System.out.printf("eror %s",kesalahan);
        }

    }
   

}
