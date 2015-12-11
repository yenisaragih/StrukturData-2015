import java.net.Socket;
import java.net.ServerSocket;
import java.net.BindException;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Server {
    public Server() throws BindException, IOException {
        serverSocket = new ServerSocket(33333);
    }
    
    public void dengar() {
        while (true) {
            try  {
                // Tunggu sampai ada koneksi dari client
                Socket koneksi = serverSocket.accept();
                
                // Buat thread untuk proses
                ProcessClientThread satuProcess = new ProcessClientThread(koneksi);
                Thread satuProcessThread = new Thread(satuProcess);
                satuProcessThread.start();                
            }
            catch(IOException err) {
                System.out.println(err);
            }
        }
    }
    
    private ServerSocket serverSocket = null;
}
