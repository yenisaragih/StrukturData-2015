import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class Client {    
    public void whois(String namaDomain) 
                throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("192.168.43.75", 33333);

        // Kirim perintah untuk informasi namaDomain
        kirimPerintah(namaDomain);
        
        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }
    
    public void kirimPerintah(String namaDomain) throws IOException {
        // 1 & 2. Minta socket untuk ditulis dan Langsung dibuka
        OutputStream keluaran = koneksi.getOutputStream();
        // Note: Karena protokol-nya berbasis teks pakai writer aja.
        Writer keluaranWriter = new OutputStreamWriter(keluaran); 
        // 3. Selagi ada data kirim
        keluaranWriter.write(namaDomain);
        // Syarat protokol-nya, semua perintah diakhiri dengan: CR & LF
        keluaranWriter.write("Yeni H Saragih (1408107010068)\r\n"); 
        keluaranWriter.flush(); // Paksa kirim data yang belum terkirim
    }
    
       
    private Socket koneksi = null;
}
