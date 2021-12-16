/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Graphics;
import java.util.LinkedList;

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
            temp.tick(object);
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            temp = object.get(i);
            temp.render(g);
        }
    }

    public void add(GameObject object) {
        this.object.add(object);
    }

    public void remove(GameObject object) {
        this.object.remove(object);
    }
}
