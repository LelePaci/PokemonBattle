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
    private String status = "";
    private boolean danniOverTime_aggiunta;
    private int danniOverTime_dps;
    private String danniOverTime_tipo;

    public Pokemon(String Nome, int Vita, String imageFront, String imageBack, String[] type, String[] debolezze, Mossa[] mosse) {
        super(0, 0);
        this.name = Nome;
        this.life = Vita;
        this.type = type;
        this.debolezze = debolezze;
        this.mosse = mosse;
    }

    public Pokemon(String Nome, int Vita, String[] tipi) {
        super(0, 0);
        name = Nome;
        life = Vita;
        type = tipi;
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

    public Mossa getMossa(int pos) {
        return mosse[pos];
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getDanniOverTime_aggiunta() {
        return danniOverTime_aggiunta;
    }

    public void setDanniOverTime_aggiunta(boolean danniOverTime_aggiunta) {
        this.danniOverTime_aggiunta = danniOverTime_aggiunta;
    }

    public int getDanniOverTime_dps() {
        return danniOverTime_dps;
    }

    public void setDanniOverTime_dps(int danniOverTime_dps) {
        this.danniOverTime_dps = danniOverTime_dps;
    }

    public String getDanniOverTime_tipo() {
        return danniOverTime_tipo;
    }

    public void setDanniOverTime_tipo(String danniOverTime_tipo) {
        this.danniOverTime_tipo = danniOverTime_tipo;
    }
    
    
    
    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {

    }
}