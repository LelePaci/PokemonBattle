package pokemonbattle.peer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokemonbattle.main.GameLogic;

public class TCPClient extends Thread {

private Socket client = null;
    private InetSocketAddress address = null;
    private PrintWriter out = null;
    private InputStreamReader input;
    private boolean running = false;
    private GameLogic logic;

    public TCPClient(GameLogic logic) {
        address = new InetSocketAddress("87.8.112.50", 42069);
        try {
            client = new Socket();
            client.connect(address, 1000);
            out = new PrintWriter(client.getOutputStream(), true);
            running = true;
            this.logic = logic;
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TCPClient(Socket client, GameLogic logic) {
        this.client=client;
        this.logic=logic;
    }

    @Override

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String inputLine = in.readLine();
                if (inputLine != null) {
                    System.out.println("Messaggio ricevuto: " + inputLine);
                    logic.add(inputLine);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void invia(String n) {
        out.println(n);
    }
}
