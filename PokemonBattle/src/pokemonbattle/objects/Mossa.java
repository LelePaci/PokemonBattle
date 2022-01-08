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
public class Mossa extends GameObject {

    private String nome;
    private String tipo;
    private int utilizzi;
    private int danni;
    private String tipoStatus;
    private int probStatus;

    public Mossa(String nome, String tipo, int utilizzi, int danni, String tipoStatus, int probStatus) {
        super(0, 0);
        this.nome = nome;
        this.tipo = tipo;
        this.utilizzi = utilizzi;
        this.danni = danni;
        this.tipoStatus = tipoStatus;
        this.probStatus = probStatus;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getUtilizzi() {
        return utilizzi;
    }

    public int getDanni() {
        return danni;
    }

    public String getTipoStatus() {
        return tipoStatus;
    }

    public int getProbStatus() {
        return probStatus;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
