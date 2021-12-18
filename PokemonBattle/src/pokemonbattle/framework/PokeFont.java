/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pacie
 */
public class PokeFont {

    private Font pokeFont;
    public PokeFont() {
        try {
            pokeFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("res/fonts/Pokemon-EM.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pokeFont);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokeFont.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(PokeFont.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Font getFont(){
        return pokeFont;
    }
}
