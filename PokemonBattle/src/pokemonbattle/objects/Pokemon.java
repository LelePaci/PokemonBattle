/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import pokemonbattle.framework.*;

/**
 *
 * @author pacie
 */
public class Pokemon extends GameObject{
    
    private String name;
    private int life;
    private String[] type;
    private Mosse[] mosse = new Mosse[4];  

    public Pokemon() {
        super(0, 0);
    }
    
    @Override
    public void tick(LinkedList<GameObject> object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
