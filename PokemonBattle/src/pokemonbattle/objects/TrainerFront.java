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
public class TrainerFront extends GameObject {

    public TrainerFront(float x, float y, int type, int sizeMultiplier) {
        super(x, y, sizeMultiplier);
        init(type);
    }
    
    public final void init(int type) {
        if (type == 1) {
            this.texture = new Texture("res/trainers.png", 400, 128, 96, 104);
        }
        if (type == 2) {
            this.texture = new Texture("res/trainers.png", 504, 128, 48, 88);
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
