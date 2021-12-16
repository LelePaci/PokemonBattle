/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import pokemonbattle.framework.GameObject;
import pokemonbattle.framework.*;
import pokemonbattle.window.*;

/**
 *
 * @author pacie
 */
public class Trainer extends GameObject {

    private float width = 100, height = width * 2;
    private Texture texture = Game.getTexture();

    public Trainer(float x, float y) {
        super(x, y);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.orange);
        //g.drawRect((int) (x - width / 2), (int) y, (int) width, (int) height);
        //g.drawImage(texture.trainers[1], (int) x, (int) y, null);
    }
}
