import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Utama {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Yeni:");
            String pesan = input.next();
            
            Client kirim = new Client();
            kirim.chat(pesan);
        }
        catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
