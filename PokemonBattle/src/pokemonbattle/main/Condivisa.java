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
import pokemonbattle.peer.*;
import pokemonbattle.util.*;

/**
 *
 * @author pacie
 */
public class Condivisa {

    public static int level = 2;
    // 0 -> inserimento nome
    // 1 -> scelta pokemon
    // 2 -> connessione
    // 3 -> combattimento

    public static boolean canChangeName = true;
    public static KeyInput input;
    public static String name;
    public static final String pokedexPath = "res/pokedex/xml";
    public static final int pokedexCount = MyFile.CountElement(pokedexPath);
    public static int chosenPokemon = 0; //variabile per controllo dei pokemon scelti dal giocatore
    public static int hoveredPokemonID = 0; //per key input e poi aggiornamento grafica della selezione del pokemon 
    public static List<SelectPokemon> pokedex = new ArrayList(); //lista di tutti i pokemon selezionabili all'interno del gioco
    public static List<Pokemon> selectedPokemon = new ArrayList(); //lista dei pokemon scelti dal giocatore
    public static final PokeFont pokeFont = new PokeFont();
    public static GameLogic gameLogic;
    public static TCPServer server;
    public static TCPClient client;
    public static boolean errorAddress = false; //In caso di errore di inserimento dell'indirizzo ip, viene settato a true e viene visualizzato un messaggio di errore
    public static int errorAddressCooldownDefault = 40;
    public static int errorAddressCooldown = errorAddressCooldownDefault; //Non utilizzo una Thread.sleep() perchè blocca il metodo tick() e impedisce l'inserimento di altri dati immediatamente
    
}
