/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pacie
 */
public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final List<Character> lista = new ArrayList();

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println(key);
        if (key >= 65 && key < 90) {
            if (lista.size() < 10) {
                lista.add((char) key);
            }
            System.out.println((char) key);
        }
        if (key == 8) {
            if (lista.size() > 0) {
                lista.remove(lista.size() - 1);
            }
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
    public void paint(Graphics g){
        char[] data = new char[lista.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = lista.get(i);
        }
        PokeFont font = new PokeFont();
        //g.setFont(font.getFont());
        g.setColor(Color.red);
        g.setFont(font.getFont());
        g.drawChars(data, 0, data.length, 425, 602);
        
    }
}
