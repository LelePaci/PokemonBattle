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
public class GenericObject extends GameObject {

    public GenericObject(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(texture.image, (int) (x), (int) y, (int) width, (int) height, null);
    }
    
    public void changeTexture(Texture texture){
        this.texture = texture;
    }
}
