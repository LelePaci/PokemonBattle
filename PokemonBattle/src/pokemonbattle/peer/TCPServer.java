package pokemonbattle.peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            System.out.println("Server started");
            serverSocket = new ServerSocket(4269);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("exit")) {
                    break;
                }
                out.println(inputLine.toUpperCase());
            }
            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException ex) {}
    }
}
