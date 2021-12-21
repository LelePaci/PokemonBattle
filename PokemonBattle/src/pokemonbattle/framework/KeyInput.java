/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import pokemonbattle.main.*;

/**
 *
 * @author pacie
 */
public class KeyInput extends KeyAdapter {

    private final Handler handler;
    public String name = "";
    private Game game;

    public KeyInput(Game game, Handler handler) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println(key);
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        switch (Condivisa.level) {
            case 0:
                if (key >= 65 && key < 90) {
                    if (name.length() < 10) {
                        name += (char) key;
                    }
                }
                if (key == 8) {
                    if (name.length() > 0) {
                        name = name.substring(0, name.length() - 1);
                    }
                }
                if (key == 10) {
                    if (Condivisa.canChangeName) {
                        Condivisa.name = name;
                        Condivisa.canChangeName = false;
                    }
                    Condivisa.level++;
                    handler.clearLevel();
                    game.createLevel_InsertName(Condivisa.level);
                }
                break;
                
            case 1:
                break;
            case 2:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
