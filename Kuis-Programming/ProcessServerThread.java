import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.Scanner;
import java.util.Calendar;

public class ProcessServerThread implements Runnable {
  
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
      
        // Ambil dan tampilkan masukan
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
        baris = masukanReader.readLine();
        
        if(baris.equalsIgnoreCase("SIAPA"))
        {
            //kirim ke client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write(""+ip);
            keluaranBuf.newLine();
            keluaranBuf.flush();
            
        }
        else if(baris.equalsIgnoreCase("WAKTU"))
        {
            Calendar kalender = Calendar.getInstance();
            String waktuStr = kalender.getTime().toString();
            
            //kirim ke client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write(""+waktuStr);
            keluaranBuf.newLine();
            keluaranBuf.flush();
            
        }
        else 
        {
            // Kirim ke Client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write("Perintah tidak sesuai");
            keluaranBuf.newLine();
            keluaranBuf.flush();
            
        }
   
    }
    private Socket koneksi; 
}