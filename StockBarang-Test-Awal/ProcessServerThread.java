import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

public class ProcessServerThread implements Runnable {
    private int jumlah=0;
    
    public ProcessServerThread(Socket koneksiKiriman) {
        koneksi = koneksiKiriman;
    }

    public void run() {
            try {
                if(koneksi != null)
                   prosesPermintaanClient();
            }
            catch(IOException err) {
                System.out.println(err);
            }
            
    }

    private void prosesPermintaanClient() 
                 throws IOException {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        
        String baris=null;
        OutputStream keluaran=null;
        BufferedWriter keluaranBuf=null;
        System.out.println("Jumlah awal : "+jumlah);
        
        do{
        // Ambil dan tampilkan masukan
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
        baris = masukanReader.readLine();
        
        if(baris.equalsIgnoreCase("TAMBAH"))
        {
            jumlah++;
            System.out.println("Jumlah sekarang : "+jumlah);
        }
        else if(baris.equalsIgnoreCase("KURANG"))
        {
            jumlah--;
            System.out.println("Jumlah sekarang : "+jumlah);
        }
        else if(baris.equalsIgnoreCase("JUMLAH"))
        {
            // Kirim ke Client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write(""+jumlah);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }
    }while(!baris.equalsIgnoreCase("SELESAI"));
}
    private Socket koneksi; 
}