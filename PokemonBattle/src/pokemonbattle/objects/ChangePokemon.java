/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemonbattle.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import pokemonbattle.framework.*;

/**
 *
 * @author pacie
 */
public class ChangePokemon extends GameObject {

    private final Handler handler;
    private int relX, relY;
    private GenericObject bg;
    private GenericObject pkmn;
    private final Pokemon pokemon;
    private int type;

    public ChangePokemon(Handler handler, int type, int x, int y, Pokemon pokemon, int id) {
        super(x, y);
        this.handler = handler;
        this.pokemon = pokemon;
        this.type = type;

        init();
    }

    private void init() {
        int size = 0;
        int offset = 0;
        if (type == 0) {
            texture = new Texture("res/pokemon-menu/pokemon-base.png", 0, 0, 83, 55);
            size = 3;
            offset = 5;
        } else if (type == 2) {
            texture = new Texture("res/pokemon-menu/pokemon-selected.png", 0, 0, 83, 55);
            size = 3;
            offset = 5;
        }else {
            texture = new Texture("res/pokemon-menu/pokemon-info.png", 0, 0, 150, 22);
            size = 1;
            offset = 5;
        }
        height = texture.getSize(4).height;

        bg = new GenericObject(x, y,
                texture.getSize(4).width,
                texture.getSize(4).height, texture);

        String name = pokemon.getName();
        texture = new Texture(pokemon.getImageFront(), 0, 0, 64, 64);
        relX = (int) x + offset;
        relY = (int) y + offset;
        pkmn = new GenericObject(relX, relY,
                texture.getSize(size).width,
                texture.getSize(size).height, texture);
        handler.add(bg);
        handler.add(pkmn);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }

    @Override
    public void render(Graphics g) {
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public int getRelX() {
        return relX;
    }

    public int getRelY() {
        return relY;
    }

}
