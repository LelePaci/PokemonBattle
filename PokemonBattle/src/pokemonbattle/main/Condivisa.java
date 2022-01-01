/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.main;

import java.util.ArrayList;
import java.util.List;
import pokemonbattle.framework.*;
import pokemonbattle.objects.*;
import pokemonbattle.util.*;

/**
 *
 * @author pacie
 */
public class Condivisa {

    public static int level = 0;
    // 0 -> inserimento nome
    // 1 -> scelta pokemon
    // 2 -> connessione
    // 3 -> combattimento

    public static boolean canChangeName = true;
    public static KeyInput input;
    public static String name;
    public static final String pokedexPath = "res/pokedex/xml";
    public static final int pokedexCount = MyFile.CountElement(pokedexPath);
    public static int chosenPokemon = 0;
    public static int hoveredPokemonID = 0; // per key input e poi aggiornamento grafica della selezione del pokemon 
    public static List<SelectPokemon> pokedex = new ArrayList();
    public static List<Pokemon> selectedPokemon = new ArrayList();
    public static final PokeFont pokeFont = new PokeFont();
    
    
}
