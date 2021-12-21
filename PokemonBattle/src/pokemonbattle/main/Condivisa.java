/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import pokemonbattle.objects.*;

/**
 *
 * @author pacie
 */
public class Condivisa {

    public static int level = 0;
    // 0 -> inserimento nome
    // 1 -> scelta pokemon
    // 3 -> combattimento

    public static boolean canChangeName = true;
    public static String name;
    public static final int pokedexCount = new File("res/pokedex/xml").list().length + 20;
    public static int chosenPokemon = 0;
    public static int hoveredPokemonID = 0; // per key input e poi aggiornamento grafica della selezione del pokemon 
    //public static int[] selectedPokemonID = new int[6];
    public static List<SelectPokemon> pokedex = new ArrayList();

}
