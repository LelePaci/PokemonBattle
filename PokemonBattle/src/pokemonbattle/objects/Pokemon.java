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
public class Pokemon extends GameObject {

    private String name;
    private int life;
    private String[] type;
    private Mossa[] mosse = new Mossa[4];
    private String[] debolezze;

    public Pokemon(String Nome, int Vita, String imageFront, String imageBack, String[] type, String[] debolezze, Mossa[] mosse) {
        super(0, 0);
        this.name = Nome;
        this.life = Vita;
        System.arraycopy(type, 0, this.type, 0, type.length);
        System.arraycopy(debolezze, 0, this.debolezze, 0, debolezze.length);
        System.arraycopy(mosse, 0, this.mosse, 0, mosse.length);
    }

    public Pokemon(String Nome, int Vita, String imagePath, Mossa Mossa1, Mossa Mossa2, Mossa Mossa3, Mossa Mossa4, String... types) {
        super(0, 0);
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public Mossa[] getMosse() {
        return mosse;
    }

    public void setMosse(Mossa[] mosse) {
        this.mosse = mosse;
    }

    public String[] getDebolezze() {
        return debolezze;
    }

    public void setDebolezze(String[] debolezze) {
        this.debolezze = debolezze;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {

    }
}
