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
public class Background extends GameObject {

    private final float width, height;

    public Background(String path, float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.texture = new Texture(path, 0, 0, 240, 160);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.image, (int) (x), (int) y, (int) width, (int) height, null);
    }
}
