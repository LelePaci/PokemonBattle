/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemonbattle.objects;

/**
 *
 * @author pacie
 */
public class OggettoInv {

    private String nome;
    public int utilizzi;
    private int vitaCurata;

    public OggettoInv(String nome, int utilizzi, int vitaCurata) {
        this.nome = nome;
        this.utilizzi = utilizzi;
        this.vitaCurata = vitaCurata;
    }

    public String getNome() {
        return nome;
    }

    public int getVitaCurata() {
        return vitaCurata;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVitaCurata(int vitaCurata) {
        this.vitaCurata = vitaCurata;
    }
    
}
