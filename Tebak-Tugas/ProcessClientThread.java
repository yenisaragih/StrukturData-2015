import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.String;

import java.io.IOException;
public class ProcessClientThread implements Runnable {
    private Socket koneksi;
    private String bilangan;
    
    public ProcessClientThread(Socket koneksiKiriman,int bilangan) {
        koneksi = koneksiKiriman;
        this.bilangan = ""+bilangan;
    }

    public void run() {
        try{
            if (koneksi != null)
                prosesPermintaanClient();
        }catch (IOException err){
            System.out.println(err);
        }
    }

    private void prosesPermintaanClient() throws IOException 
    {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        int i =0;
        String pesanServer=null;
        OutputStream keluaran=null;
        BufferedWriter keluaranBuf=null;

        for(; i<3;i++)
        {
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            String baris = masukanReader.readLine();
            System.out.println("Tebakan Client : "+baris);

            if(bilangan.equalsIgnoreCase(baris))
                pesanServer="Benar";
            else
                pesanServer="Salah";

            // Kirim ke client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
            keluaranBuf.write(pesanServer);
            keluaranBuf.newLine();
            keluaranBuf.flush();

            if(pesanServer.equalsIgnoreCase("Benar"))
                break;
        }
        if(i==3){
            pesanServer="Kalah, angka = "+bilangan;
            keluaranBuf.write(pesanServer);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }
    }
}
