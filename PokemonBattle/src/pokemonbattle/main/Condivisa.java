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

    public static int level = 0;
    // 0 -> inserimento nome
    // 1 -> scelta pokemon
    // 2 -> connessione
    // 3 -> combattimento

    //Game Objects e framework
    public static Game game;
    public static KeyInput input;
    public static Handler handler;
    public static final PokeFont pokeFont = new PokeFont();
    public static GameLogic gameLogic;
    public static TCPServer server;
    public static TCPClient client;
    
    //myGame
    public static String myName = "guest";
    public static List<Pokemon> selectedPokemon = new ArrayList(); //lista dei pokemon scelti dal giocatore
    public static Pokemon myCurrentPokemon;
    public static int pokemonRimanenti = selectedPokemon.size();
    //enemyGame
    public static String enemyName = "guest";
    public static Pokemon enemyPokemon;

    //Level: Inserimento nickname
    public static boolean canChangeName = true;
    public static final String pokedexPath = "res/pokedex/xml";
    public static final int pokedexCount = MyFile.CountElement(pokedexPath);
    public static int chosenPokemon = 0; //variabile per controllo dei pokemon scelti dal giocatore
    public static int hoveredPokemonID = 0; //per key input e poi aggiornamento grafica della selezione del pokemon 
    public static List<SelectPokemon> pokedex = new ArrayList(); //lista di tutti i pokemon selezionabili all'interno del gioco

    //Level: Inserimento IP e connessione
    public static boolean errorAddress = false; //In caso di errore di inserimento dell'indirizzo ip, viene settato a true e viene visualizzato un messaggio di errore
    public static int errorAddressCooldownDefault = 40;
    public static int errorAddressCooldown = errorAddressCooldownDefault; //Non utilizzo una Thread.sleep() perchè blocca il metodo tick() e impedisce l'inserimento di altri dati immediatamente

    //Level: Battaglia
    public static String battleBackgroundPath = ""; //Percorso del background della lotta, in modo che quando viene ricaricata la schermata non cambia random
    public static int defaultCooldown = 60;
    public static boolean battle_starting = true; //Visualizzazione messaggio inizio battaglia
    public static int battle_startingCooldown = defaultCooldown;
    public static boolean battle_sendEnemyPokemon = false;
    public static int battle_sendEnemyPokemonCooldown = defaultCooldown;
    public static boolean battle_sendMyPokemon = false;
    public static int battle_sendMyPokemonCooldown = defaultCooldown;
}
