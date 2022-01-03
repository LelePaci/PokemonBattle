package pokemonbattle.peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.ServerSocket;
import java.net.Socket;
import pokemonbattle.main.GameLogic;

public class TCPServer extends Thread {

    private ServerSocket server = null;
    private boolean running = true;
    PrintWriter out;
    private GameLogic logic;
    private TCPClient client;

    public TCPServer(GameLogic logic) {
        try {
            server = new ServerSocket(42069);
            this.logic = logic;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Socket connessione = null;
        System.out.println("Server start");
        while (running) {
            try {
                connessione = server.accept();
                out = new PrintWriter(connessione.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));

                System.out.println("Connessione con: " + connessione.getInetAddress() + ": " + connessione.getPort());
                client = new TCPClient(connessione, logic);
                client.start();
                
                /*while (true) {
                    String inputLine = in.readLine();
                    if (inputLine != null) {
                        System.out.println("Messaggio ricevuto: " + inputLine);
                        logic.add(inputLine);
                    }

                }*/
                 
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    public void invia(String toSend) {
        out.println(toSend);
    }

    public TCPClient getClient() {
        return this.client;
    }
}
