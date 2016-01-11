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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class ProcessServerThread implements Runnable {
    String baris=null;
    OutputStream keluaran=null;
    BufferedWriter keluaranBuf=null;

    static Map<String,String> map = new HashMap<String,String>();
    static Map<String,String> map1 = new HashMap<String,String>();
    static Map<String,String> map2 = new HashMap<String,String>();
    Calendar cal = Calendar.getInstance();
    static int tiket = 1;

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

    private void prosesPermintaanClient()throws IOException {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);

        // Ambil dan tampilkan masukan
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
        baris = masukanReader.readLine();

        keluaran = koneksi.getOutputStream();
        keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));

        SimpleDateFormat time1 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat time2 = new SimpleDateFormat("HH:mm");
        String waktu1, waktu2, wkt;
        String[] potong = baris.split(" ");

        String hasil = null;

        if(potong.length==2){
            if (potong[0].equalsIgnoreCase("M")){
                if (!map.containsKey(potong[1])) {
                    if(map.size()<=3){
                        hasil = "Tdk Penuh PM1   ";
                        waktu1 = time1.format(cal.getTime());
                        map.put(potong[1], waktu1);
                        //kirim ke client
                        keluaranBuf.write(hasil);
                        keluaranBuf.newLine();
                        keluaranBuf.flush();

                    }
                    else{
                        hasil = "Penuh --> Keluar";
                        //kirim ke client
                        keluaranBuf.write(hasil);
                        keluaranBuf.newLine();
                        keluaranBuf.flush();

                    }
                }else{
                    hasil = "Plat anda sudah terdaftar";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }
            }else if (potong[0].equalsIgnoreCase("SM")){
                if (!map1.containsKey(potong[1])) {
                    if(map1.size()<=3){
                        hasil = "Tdk Penuh PSM1   ";
                        waktu1 = time1.format(cal.getTime());

                        map1.put(potong[1], waktu1);
                        //kirim ke client
                        keluaranBuf.write(hasil);
                        keluaranBuf.newLine();
                        keluaranBuf.flush();

                    }
                    else{
                        hasil = "Penuh --> Keluar ";
                        //kirim ke client
                        keluaranBuf.write(hasil);
                        keluaranBuf.newLine();
                        keluaranBuf.flush();

                    }
                }else{
                    hasil = "Plat anda sudah terdaftar";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }
            }
        }else if (potong.length==1){
            if (potong[0].equalsIgnoreCase("S")){
                if(map2.size()<=3){
                    String tiket1 = Integer.toString(tiket);
                    waktu1 = time1.format(cal.getTime());
                    map2.put(tiket1,waktu1);

                    hasil = "Tdk Penuh PS1 : "+tiket;
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    tiket++;
                }else{
                    hasil = "Penuh --> Keluar ";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();

                }
            }else{
                hasil = "format salah";
                //kirim ke client
                keluaranBuf.write(hasil);
                keluaranBuf.newLine();
                keluaranBuf.flush();
            }
        }else if (potong.length==3){
            if (potong[0].equalsIgnoreCase("KELUAR") && potong[1].equalsIgnoreCase("M")){
                if (map.containsKey(potong[2])){
                    wkt = map.get(potong[2]);
                    waktu2 = time2.format(cal.getTime());
                    String[] ptg1 = wkt.split(":");
                    String[] ptg2 = waktu2.split(":");
                    int j3, sisa, m3;
                    int jam1=  Integer.valueOf(ptg1[0]);
                    int menit1 = Integer.valueOf(ptg1[1]);
                    int jam2=  Integer.valueOf(ptg2[0]);
                    int menit2 = Integer.valueOf(ptg2[1]);

                    int awal = (3600*jam1)+(60*menit1);
                    int akhir = (3600*jam2)+(60*menit2);
                    int selisih = akhir-awal;
                    j3 = selisih/3600;
                    sisa = selisih%3600;
                    m3 = sisa/60;
                    map.remove(potong[2]);

                    hasil = "Valid "+j3+" jam "+m3+" menit M1";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }else{
                    hasil = "Plat anda tidak terdaftar";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }

            }else if (potong[0].equalsIgnoreCase("KELUAR") && potong[1].equalsIgnoreCase("SM")){
                if (map1.containsKey(potong[2])){
                    wkt = map1.get(potong[2]);
                    waktu2 = time2.format(cal.getTime());
                    String[] ptg1 = wkt.split(":");
                    String[] ptg2 = waktu2.split(":");
                    int j3, sisa, m3;
                    int jam1=  Integer.valueOf(ptg1[0]);
                    int menit1 = Integer.valueOf(ptg1[1]);
                    int jam2=  Integer.valueOf(ptg2[0]);
                    int menit2 = Integer.valueOf(ptg2[1]);

                    int awal = (3600*jam1)+(60*menit1);
                    int akhir = (3600*jam2)+(60*menit2);
                    int selisih = akhir-awal;
                    j3 = selisih/3600;
                    sisa = selisih%3600;
                    m3 = sisa/60;

                    map1.remove(potong[2]);

                    hasil = "Valid "+j3+" jam "+m3+" menit SM1";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }else{
                    hasil = "Plat anda tidak terdaftar";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }

            } else if (potong[0].equalsIgnoreCase("KELUAR") && potong[1].equalsIgnoreCase("S")){
                if (map2.containsKey(potong[2])){
                    wkt = map2.get(potong[2]);
                    waktu2 = time2.format(cal.getTime());
                    String[] ptg1 = wkt.split(":");
                    String[] ptg2 = waktu2.split(":");
                    int j3, sisa, m3;
                    int jam1=  Integer.valueOf(ptg1[0]);
                    int menit1 = Integer.valueOf(ptg1[1]);
                    int jam2=  Integer.valueOf(ptg2[0]);
                    int menit2 = Integer.valueOf(ptg2[1]);

                    int awal = (3600*jam1)+(60*menit1);
                    int akhir = (3600*jam2)+(60*menit2);
                    int selisih = akhir-awal;
                    j3 = selisih/3600;
                    sisa = selisih%3600;
                    m3 = sisa/60;
                    map2.remove(potong[2]);

                    hasil = "Valid "+j3+" jam "+m3+" menit S1";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }else{
                    hasil = "Plat anda tidak terdaftar";
                    //kirim ke client
                    keluaranBuf.write(hasil);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                }
            } else{
                hasil = "Format yang anda masukkan salah";
                //kirim ke client
                keluaranBuf.write(hasil);
                keluaranBuf.newLine();
                keluaranBuf.flush();
            }
        }else{
            hasil = "Format yang anda masukkan salah";
            //kirim ke client
            keluaranBuf.write(hasil);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }

    }
    private Socket koneksi; 

}