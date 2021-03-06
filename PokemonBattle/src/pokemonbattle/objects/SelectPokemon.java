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
 * @author paci_emanuele
 */
public class SelectPokemon extends GameObject {

    private final Handler handler;
    private Texture texture;
    private int relX, relY;
    private final int id;
    private boolean selected = false;
    private GenericObject bg;
    private GenericObject pkmn;
    private final Pokemon pokemon;

    public SelectPokemon(Handler handler, Texture texture, int x, int y, int id, Pokemon pokemon) {
        super(x, y);
        this.handler = handler;
        this.texture = texture;
        this.id = id;
        this.pokemon = pokemon;
        init();
    }

    private void init() {
        if (this.id == 0) {
            texture = new Texture("res/pokemon-menu/pokemon-base.png", 0, 0, 83, 55);
        } else {
            texture = new Texture("res/pokemon-menu/pokemon-gray.png", 0, 0, 83, 55);
        }

        bg = new GenericObject(x, y,
                texture.getSize(2).width,
                texture.getSize(2).height, texture);

        String name = pokemon.getName();
        System.out.println("Added Pokemon: " + name);
        texture = new Texture(pokemon.getImageFront(), 0, 0, 64, 64);
        relX = (int) x + 10;
        relY = (int) y + 10;
        pkmn = new GenericObject(relX, relY,
                texture.getSize(1).width,
                texture.getSize(1).height, texture);
        handler.add(bg);
        handler.add(pkmn);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {

    }

    public void changeTexture(int status) {
        if (status == 0) {
            texture = new Texture("res/pokemon-menu/pokemon-gray.png", 0, 0, 83, 55);
            bg.changeTexture(texture);
        }
        if (status == 1) {
            texture = new Texture("res/pokemon-menu/pokemon-base.png", 0, 0, 83, 55);
            bg.changeTexture(texture);
        }
        if (status == 2) {
            texture = new Texture("res/pokemon-menu/pokemon-selected.png", 0, 0, 83, 55);
            bg.changeTexture(texture);
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getID() {
        return id;
    }
    
    public Pokemon getPokemon(){
        return pokemon;
    }

    public int getRelX() {
        return relX;
    }

    public int getRelY() {
        return relY;
    }
}
