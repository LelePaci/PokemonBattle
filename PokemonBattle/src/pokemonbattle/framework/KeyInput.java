/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokemonbattle.main.*;
import pokemonbattle.peer.*;

/**
 *
 * @author pacie
 */
public class KeyInput extends KeyAdapter {

    private final Handler handler;
    public String name = "";
    public String ipAddress = "";
    private final Game game;
    private final Text text;

    public KeyInput(Game game, Handler handler, Text text) {
        this.handler = handler;
        this.game = game;
        this.text = text;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
//        System.out.println(key);
//        System.out.println((char) key);
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        switch (Condivisa.level) {
            case 0:
                if (key >= 65 && key < 90) {
                    if (name.length() < 10) {
                        name += (char) key;
                    }
                }
                if (key == 8) {
                    if (name.length() > 0) {
                        name = name.substring(0, name.length() - 1);
                    }
                }
                if (key == 10) {
                    if (Condivisa.canChangeName) {
                        Condivisa.myName = name;
                        Condivisa.canChangeName = false;
                    }
                    Condivisa.level++;
                    handler.clearLevel();
                    game.createLevel(Condivisa.level);
                }
                break;

            case 1:
                if (key == 39) {
                    if (Condivisa.hoveredPokemonID + 1 < Condivisa.pokedexCount) {
                        Condivisa.hoveredPokemonID += 1;
                        refreshPokedex();
                    }
                }
                if (key == 37) {
                    if (Condivisa.hoveredPokemonID - 1 >= 0) {
                        Condivisa.hoveredPokemonID -= 1;
                        refreshPokedex();
                    }
                }
                if (key == 40) {
                    if (Condivisa.hoveredPokemonID + 5 < Condivisa.pokedexCount) {
                        Condivisa.hoveredPokemonID += 5;
                        refreshPokedex();
                    }
                }
                if (key == 38) {
                    if (Condivisa.hoveredPokemonID - 5 >= 0) {
                        Condivisa.hoveredPokemonID -= 5;
                        refreshPokedex();
                    }
                }
                if (key == 10) {
                    if (!Condivisa.pokedex.get(Condivisa.hoveredPokemonID).isSelected() && Condivisa.chosenPokemon < 6) {
                        Condivisa.pokedex.get(Condivisa.hoveredPokemonID).setSelected(true);
                        Condivisa.pokedex.get(Condivisa.hoveredPokemonID).changeTexture(2);
                        Condivisa.selectedPokemon.add(Condivisa.pokedex.get(Condivisa.hoveredPokemonID).getPokemon());
                        Condivisa.chosenPokemon++;
                    } else if (Condivisa.pokedex.get(Condivisa.hoveredPokemonID).isSelected() && Condivisa.chosenPokemon > 0) {
                        Condivisa.pokedex.get(Condivisa.hoveredPokemonID).setSelected(false);
                        Condivisa.pokedex.get(Condivisa.hoveredPokemonID).changeTexture(1);
                        Condivisa.selectedPokemon.remove(Condivisa.pokedex.get(Condivisa.hoveredPokemonID).getPokemon());
                        Condivisa.chosenPokemon--;
                    }
                    if (Condivisa.chosenPokemon == 6) {
                        Condivisa.level++;
                        handler.clearLevel();
                        game.createLevel(Condivisa.level);
                    }
                }
                break;

            case 2:
                if (key >= 48 && key <= 57 || key == 46) {
                    if (ipAddress.length() < 15) {
                        ipAddress += (char) key;
                    }
                }
                if (key >= 96 && key <= 105 || key == 110) {
                    if (key != 110) {
                        ipAddress += key - 96;
                    } else {
                        ipAddress += ".";
                    }
                }

                if (key == 8) {
                    if (ipAddress.length() > 0) {
                        ipAddress = ipAddress.substring(0, ipAddress.length() - 1);
                    }
                }
                if (key == 10) {
                    if (!Connection.validateFromString(ipAddress)) {
                        Condivisa.errorAddress = true;
                    } else {
                        Condivisa.level++;
                        handler.clearLevel();
                        game.createLevel(Condivisa.level);
                        Condivisa.server.stopReceive();
                        Condivisa.client = new TCPClient(ipAddress);
                        Condivisa.client.start();
                    }
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void refreshPokedex() {
        for (int i = 0; i < Condivisa.pokedex.size(); i++) {
            if (!Condivisa.pokedex.get(i).isSelected()) {
                Condivisa.pokedex.get(i).changeTexture(0);
            }
        }
        if (!Condivisa.pokedex.get(Condivisa.hoveredPokemonID).isSelected()) {
            Condivisa.pokedex.get(Condivisa.hoveredPokemonID).changeTexture(1);
        }
    }
}
