package pokemonbattle.peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokemonbattle.main.*;

public class TCPClient extends Thread {

    private Socket client = null;
    private InetSocketAddress address = null;
    private PrintWriter out = null;
    private boolean running = false;
    private GameLogic logic;
    private boolean firstPeer;

    public TCPClient(String ipAddress) {
        address = new InetSocketAddress(ipAddress, 42069);
        try {
            client = new Socket();
            client.connect(address, 1000);
            try {
                out = new PrintWriter(client.getOutputStream(), true);
            } catch (IOException ex) {
                Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            running = true;
            this.logic = Condivisa.gameLogic;
            this.firstPeer = true;
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TCPClient(Socket client) {
        this.client = client;
        this.logic = Condivisa.gameLogic;
        try {
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.firstPeer = false;
        running = true;

    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (running) {
                String inputLine = in.readLine();
                if (inputLine != null) {
                    System.out.println("Messaggio ricevuto: " + inputLine);
                    logic.addPeerEvent(inputLine);
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

    public void stopClient() {
        running = false;
    }

    public void invia(String xml) {
        System.out.println("Inviato messaggio: " + xml);
        out.println(xml);
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public boolean isBound() {
        return client.isBound();
    }

    public boolean isFirstPeer() {
        return firstPeer;
    }
}
