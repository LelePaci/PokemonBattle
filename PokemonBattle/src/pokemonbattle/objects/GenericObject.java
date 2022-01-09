/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import pokemonbattle.framework.*;
import pokemonbattle.main.Condivisa;

/**
 *
 * @author pacie
 */
public class GenericObject extends GameObject {

    private String id = "";
    private float finalX;
    private float startX;

    public GenericObject(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    public GenericObject(float x, float y, float width, float height, Texture texture, String id) {
        super(x, y, width, height, texture);
        this.id = id;
        if (id.equals("enemy-bar")) {
            startX = 0 - width;
            finalX = x;
            x = startX;
        }
        if (id.equals("my-bar")) {
            startX = Condivisa.gameWIDTH + width;
            finalX = x;
            x = startX;
        }

    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        if (!id.equals("battleMenu") && !id.equals("arrow1")) {
            g.drawImage(texture.image, (int) (x), (int) y, (int) width, (int) height, null);
        } else {
            if (Condivisa.gameInput && !Condivisa.waitingEnemy) {
                g.drawImage(texture.image, (int) (x), (int) y, (int) width, (int) height, null);
            }
        }
    }

    public void changeTexture(Texture texture) {
        this.texture = texture;
    }
}
