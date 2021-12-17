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
public class TrainerFront extends GameObject {

    private float width, height;
    private int sizeMultiplier;

    public TrainerFront(float x, float y, int type) {
        super(x, y);
        //this.texture = new Texture("res/trainers-transparent.png", 405, 130, 86, 100);        
        sizeMultiplier = 2;
        init(x, y, type, sizeMultiplier);
    }

    public TrainerFront(float x, float y, int type, int sizeMultiplier) {
        super(x, y);
        //this.texture = new Texture("res/trainers-transparent.png", 405, 130, 86, 100);   
        this.sizeMultiplier = sizeMultiplier;
        init(x, y, type, sizeMultiplier);
    }

    public final void init(float x, float y, int type, int sizeMultiplier) {
        if (type == 1) {
            this.texture = new Texture("res/trainers-transparent.png", 400, 128, 96, 104);
        }
        if (type == 2) {
            this.texture = new Texture("res/trainers-transparent.png", 504, 128, 48, 88);
        }
        
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
