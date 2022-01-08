/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import pokemonbattle.main.*;
import pokemonbattle.objects.*;
import pokemonbattle.peer.*;
import pokemonbattle.util.*;

/**
 *
 * @author pacie
 */
public class KeyInput extends KeyAdapter {
    
    private final Handler handler;
    public String name = "";
    public String ipAddress = "";
    private final Game game;
    private XMLParser parser;
    
    public KeyInput(Game game, Handler handler) {
        this.handler = handler;
        this.game = game;
        this.parser = new XMLParser();
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
                        Condivisa.gameLogic.connectionStarted();
                    }
                }
                break;
            case 3:
                if (key == 39) {
                    if (Condivisa.selectedArrow + 1 < 4) {
                        Condivisa.selectedArrow += 1;
                        refreshArrow();
                    }
                }
                if (key == 37) {
                    if (Condivisa.selectedArrow - 1 >= 0) {
                        Condivisa.selectedArrow -= 1;
                        refreshArrow();
                    }
                }
                if (key == 40) {
                    if (Condivisa.selectedArrow + 2 < 4) {
                        Condivisa.selectedArrow += 2;
                        refreshArrow();
                    }
                }
                if (key == 38) {
                    if (Condivisa.selectedArrow - 2 >= 0) {
                        Condivisa.selectedArrow -= 2;
                        refreshArrow();
                    }
                }
                if (key == 10) {
                    Condivisa.mossaMenu.changeTexture(new Texture("res/ingame-menu/mossa-menu.png", 0, 0, 240, 48));
                }

                /*
                if (key == 68) {
                    Condivisa.eventList.addEvent(Condivisa.battleStarting);
                }
                if (key == 69) {
                    Condivisa.eventList.addEvent(Condivisa.sendEnemyPokemon);
                }
                if (key == 65) {
                    //invio attacco
                    Mossa mossa = Condivisa.myCurrentPokemon.getMossa(1);
                    String xml = parser.getXMLAttacco(mossa.getNome(), mossa.getTipo(), mossa.getDanni(), mossa.getTipoStatus(), mossa.getProbStatus());
                    Condivisa.client.invia(xml);
                }
                if (key == 67) {
                    //invio i

                    OggettoInv oggetto = Condivisa.inventario.getOggetto(0);
                    oggetto.utilizzi--;
                    Condivisa.myCurrentPokemon.setLife(Condivisa.myCurrentPokemon.getLife() + oggetto.getVitaCurata());
                    String xml = parser.getXMLInventario(oggetto.getNome(), Condivisa.myCurrentPokemon.getName(), Condivisa.myCurrentPokemon.getLife());
                    Condivisa.client.invia(xml);

                    xml = parser.getXMLPassoTurno();
                    Condivisa.client.invia(xml);
                }
                 */
                break;
            case 4:
                if (key == 66) {
                    //invio nuovo pokemon
                    Pokemon temp = Condivisa.selectedPokemon.get(1);
                    Condivisa.myCurrentPokemon = temp;
                    String xml = parser.getXMLInvioPokemon("c", temp.getName(), temp.getLife(), temp.getType());
                    Condivisa.client.invia(xml);
                    
                    Condivisa.level--;
                    Condivisa.handler.clearLevel();
                    Condivisa.game.createLevel(Condivisa.level);
                }
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
    
    private void refreshArrow() {        
        for (int i = 0; i < Condivisa.arrows.size(); i++) {
            if (true) {
                Condivisa.arrows.get(i).changeTexture(new Texture("res/ingame-menu/empty-6.10.png", 0, 0, 6, 10));
            }
        }
        Condivisa.arrows.get(Condivisa.selectedArrow).changeTexture(new Texture("res/ingame-menu/arrow.png", 0, 0, 6, 10));
    }
}
