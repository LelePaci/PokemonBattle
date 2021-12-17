/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import pokemonbattle.framework.GameObject;
import pokemonbattle.framework.Texture;

/**
 *
 * @author pacie
 */
public class Background extends GameObject {

    private float width;
    private float height;

    public Background(String path, float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;
        System.out.println(this.width);
        System.out.println(this.height);
        this.texture = new Texture(path, 5, 5, 240, 160);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.image, (int) (x), (int) y, (int) width, (int) height, null);
    }

}
