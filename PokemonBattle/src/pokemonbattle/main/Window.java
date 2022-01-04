/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.main;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author paci_emanuele
 */
public class Window {

    public Window(int width, int height, String title, Game game) {
        game.setPreferredSize(new Dimension(width, height));
        game.setMaximumSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                game.stop();
                System.exit(0);
                if (Condivisa.server != null) {
                    if (Condivisa.server.getClient() != null) {
                        Condivisa.server.getClient().stopClient();
                    }
                    Condivisa.server.stopServer();
                    
                }
                if (Condivisa.client != null) {
                    Condivisa.client.stopClient();
                }
            }
        });

        game.start();
    }

}
