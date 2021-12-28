package pokemonbattle.peer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient extends Thread {

    @Override
    public void run() {
        Socket socket = null;
        try {
            InetAddress addr = InetAddress.getByName("82.48.148.233");
            socket = new Socket(addr, 4269);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            System.out.println("test");
            out.println("CIAO");
            String inputLine = in.readLine();
            System.out.println(inputLine);
            out.println("exit");
            inputLine = in.readLine();
            System.out.println(inputLine);
            socket.close();
        } catch (IOException ex) {
        } finally {
        }
        try {
            socket.close();
        } catch (IOException ex) {
        }
    }
}
