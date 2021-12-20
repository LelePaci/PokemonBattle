/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Graphics;
import java.util.LinkedList;
import static pokemonbattle.main.Game.HEIGHT;
import static pokemonbattle.main.Game.WIDTH;

/**
 *
 * @author pacie
 */
public class Handler {

    private LinkedList<GameObject> object = new LinkedList<GameObject>();
    private GameObject temp;

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            temp = object.get(i);
            if (temp != null) {
                temp.tick(object);
            }
        }
    }

    public void render(Graphics g) {
        if (object.isEmpty()) {
            g.clearRect(0, 0, WIDTH, HEIGHT);
        }

        for (int i = 0; i < object.size(); i++) {
            temp = object.get(i);
            if (temp != null) {
                temp.render(g);
            }
            
        }
    }

    public void add(GameObject object) {
        this.object.add(object);
    }

    public void remove(GameObject object) {
        this.object.remove(object);
    }
    
    public void clearLevel(){
        for (int i = 0; i < object.size(); i++) {
            object.set(i, null);
        }
        this.object.clear();
    }
}
