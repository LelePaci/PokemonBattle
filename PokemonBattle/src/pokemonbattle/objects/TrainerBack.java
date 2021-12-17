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
public class TrainerBack extends GameObject {

    private float width, height;
    private Texture texture;
    private int sizeMultiplier = 2;

    public TrainerBack(float x, float y, int type) {
        super(x, y);
        init(x, y, 2);
    }

    public TrainerBack(float x, float y, int type, int sizeMultiplier) {
        super(x, y);
        init(x, y, sizeMultiplier);
    }

    public final void init(float x, float y, int sizeMultiplier) {
        //this.texture = new Texture("res/trainers-transparent.png", 405, 130, 86, 100);        
        this.texture = new Texture("res/trainers-transparent.png", 400, 128, 96, 104);
        width = texture.image.getWidth() * sizeMultiplier;
        height = texture.image.getHeight() * sizeMultiplier;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(texture.image, (int) (x - width / 2), (int) y, (int) width, (int) height, null);
    }
}
