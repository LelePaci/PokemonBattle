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
    private GameLogic logic;
    private TCPClient client;
    private boolean canConnect = true;
    private Game game;
    private Handler handler;

    public TCPServer(Game game, Handler handler) {
        try {
            server = new ServerSocket(42069);
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.logic = Condivisa.gameLogic;
        this.game = game;
        this.handler = handler;
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

                    Condivisa.level++;
                    handler.clearLevel();
                    game.createLevel(Condivisa.level);

                    client = new TCPClient(connessione);
                    client.start();
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
