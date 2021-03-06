package pokemonbattle.peer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import pokemonbattle.framework.*;
import pokemonbattle.main.*;

public class TCPServer extends Thread {

    private ServerSocket server = null;
    private boolean running = true;
    PrintWriter out;
    private TCPClient client;
    public boolean canConnect = true;

    public TCPServer(Game game, Handler handler) {
        try {
            server = new ServerSocket(42069);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("Server started, port: " + server.getLocalPort());
        while (running) {
            try {
                if (canConnect) {
                    Socket connessione = server.accept();
                    out = new PrintWriter(connessione.getOutputStream(), true);

                    System.out.println("Connessione con: " + connessione.getInetAddress() + ": " + connessione.getPort());

                    client = new TCPClient(connessione);
                    client.start();
                    Condivisa.gameLogic.connectionReceived();
                }
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

    public void stopServer() {
        running = false;
        System.out.println("Server stopped");
    }

    public void stopReceive() {
        canConnect = false;
    }
}
