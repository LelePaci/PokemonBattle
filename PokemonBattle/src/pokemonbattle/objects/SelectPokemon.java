/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.objects;

import java.io.File;
import pokemonbattle.framework.*;

/**
 *
 * @author paci_emanuele
 */
public class SelectPokemon {

    private final Handler handler;
    private Texture texture;
    private int x, y;
    private int relX, relY;

    public SelectPokemon(Handler handler, Texture texture, int x, int y) {
        this.handler = handler;
        this.texture = texture;
        this.x = x;
        this.y = y;
        init();
    }

    private void init() {
        texture = new Texture("res/pokemon-menu/pokemon-gray.png", 0, 0, 83, 55);
        handler.add(new GenericObject(x, y,
                texture.getSize(2).width,
                texture.getSize(2).height, texture));
        texture = new Texture("res/pokedex/images/charizard-front.png", 0, 0, 64, 64);
        relX = x + 10;
        relY = y + 10;
        handler.add(new GenericObject(relX, relY,
                texture.getSize(1).width,
                texture.getSize(1).height, texture));

    }
}
