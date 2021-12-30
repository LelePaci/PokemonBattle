/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private Game game;

    public KeyInput(Game game, Handler handler) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
//        System.out.println(key);
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
                        Condivisa.name = name;
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
                        Condivisa.chosenPokemon++;
                    } else if (Condivisa.pokedex.get(Condivisa.hoveredPokemonID).isSelected() && Condivisa.chosenPokemon > 0) {
                        Condivisa.pokedex.get(Condivisa.hoveredPokemonID).setSelected(false);
                        Condivisa.pokedex.get(Condivisa.hoveredPokemonID).changeTexture(1);
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
                if (key == 8) {
                    if (ipAddress.length() > 0) {
                        ipAddress = ipAddress.substring(0, ipAddress.length() - 1);
                    }
                }
                if (key == 10) {
                    Connection.validateFromString(ipAddress);
                }
                System.out.println(ipAddress);
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
