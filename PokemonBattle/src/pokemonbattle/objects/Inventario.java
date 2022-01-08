/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemonbattle.objects;

/**
 *
 * @author pacie
 */
public class Inventario {

    private OggettoInv[] listaOggetti = new OggettoInv[2];

    public Inventario() {
        listaOggetti[0] = new OggettoInv("Pozione", 10, 20);
        listaOggetti[1] = new OggettoInv("Super Pozione", 4, 60);
    }

    public OggettoInv getOggetto(int pos) {
        return getOggetto(pos);
    }
}
