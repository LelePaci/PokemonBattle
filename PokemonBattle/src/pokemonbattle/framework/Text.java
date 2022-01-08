/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pokemonbattle.main.Condivisa;
import pokemonbattle.main.Game;
import static pokemonbattle.main.Game.stats;
import pokemonbattle.objects.*;

/**
 *
 * @author pacie
 */
public class Text {

    private float x, y;
    public String text;
    public Font font = null;
    public Color color;
    public List<Text> allTexts = new ArrayList<>();
    public PokeFont pokeFont = new PokeFont();

    public Text() {
    }

    public Text(String text, float x, float y, Font font, Color color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.font = font;
        this.color = color;
    }

    public void setData(Text... data) {
        allTexts.clear();
        allTexts.addAll(Arrays.asList(data));
    }

    public void setData(List<Text> data) {
        allTexts.clear();
        allTexts.addAll(data);
    }

    public void clearData() {
        allTexts.clear();
    }

    public void tick() {
        KeyInput input = Condivisa.input;
//        System.out.println(allTexts.size());
        allTexts.clear();
        List<Text> toAdd = new ArrayList();
        switch (Condivisa.level) {
            case 0:
                this.setData(new Text(stats, 5, 12, pokeFont.getFont(10f), Color.black),
                        new Text(input.name, 425, 602, pokeFont.getFont(30f), Color.red));
                break;
            case 1:
                int textHeight = 585;
                toAdd.add(new Text(stats, 5, 12, pokeFont.getFont(10f), Color.black));
                toAdd.add(new Text("Scegli i tuoi Pokémon.", 50, textHeight, pokeFont.getFont(30f), Color.black));
                toAdd.add(new Text("Ancora " + (6 - Condivisa.chosenPokemon), 315, textHeight, pokeFont.getFont(30f), Color.red));

                if (Condivisa.pokedexCount == Condivisa.pokedex.size() && !Condivisa.pokedex.isEmpty()) {
                    for (int i = 0; i < Condivisa.pokedexCount; i++) {
                        if (!Condivisa.pokedex.isEmpty()) {
                            Pokemon temp = Condivisa.pokedex.get(i).getPokemon();
                            String nome = temp.getName();
                            int life = temp.getLife();
                            int tempX = Condivisa.pokedex.get(i).getRelX();
                            int tempY = Condivisa.pokedex.get(i).getRelY();
                            toAdd.add(new Text(nome, tempX + 63, tempY + 55, pokeFont.getFont(20f), Color.black));
                            toAdd.add(new Text(life + "", tempX + 75, tempY + 93, pokeFont.getFont(20f), Color.black));
                            toAdd.add(new Text(life + "", tempX + 120, tempY + 93, pokeFont.getFont(20f), Color.black));
                        }
                    }
                    this.setData(toAdd);
                }

                toAdd.clear();
                break;

            case 2:
                toAdd.add(new Text(stats, 5, 12, pokeFont.getFont(10f), Color.black));
                toAdd.add(new Text(input.ipAddress, 420, 335, pokeFont.getFont(30f), Color.red));
                if (Condivisa.errorAddress) {
                    toAdd.add(new Text("Indirizzo IP NON VALIDO", 330, 285, pokeFont.getFont(40f), Color.red));
                } else {
                    toAdd.add(new Text("Inserisci indirizzo IP", 375, 265, pokeFont.getFont(30f), Color.black));
                    toAdd.add(new Text("oppure attendi una richiesta", 330, 295, pokeFont.getFont(30f), Color.black));
                }
                this.setData(toAdd);
                toAdd.clear();
                break;
            case 3:
                int firstLine = Game.HEIGHT - 113;
                int secondLine = Game.HEIGHT - 65;
                int leftSpacing = 50;
                toAdd.add(new Text(stats, 5, 12, pokeFont.getFont(10f), Color.black));
                if (Condivisa.battleStarting.running) {
                    toAdd.add(new Text("Inizia la battaglia con ", leftSpacing, firstLine, pokeFont.getFont(40f), Color.white));
                    toAdd.add(new Text(Condivisa.enemyName, leftSpacing, secondLine, pokeFont.getFont(40f), Color.red));
                }

                if (Condivisa.sendEnemyPokemon.running) {
                    toAdd.add(new Text("E' il turno di " + Condivisa.enemyPokemon.getName() + ", mandato in", leftSpacing, firstLine, pokeFont.getFont(40f), Color.white));
                    toAdd.add(new Text("campo da " + Condivisa.enemyName, leftSpacing, secondLine, pokeFont.getFont(40f), Color.white));
                }

                if (Condivisa.sendMyPokemon.running) {
                    toAdd.add(new Text("Vai, " + Condivisa.myCurrentPokemon.getName(), leftSpacing, firstLine, pokeFont.getFont(40f), Color.white));

                }

                if (Condivisa.enemyPokemonInCampo) {
                    toAdd.add(new Text(Condivisa.enemyPokemon.getName(), 62, 67, pokeFont.getFont(30f), Color.black));
                    toAdd.add(new Text(Condivisa.enemyPokemon.getLife() + "/" + Condivisa.enemyPokemon.maxLife, 272, 87, pokeFont.getFont(30f), Color.black));
                }
                if (Condivisa.myPokemonInCampo) {
                    toAdd.add(new Text(Condivisa.myCurrentPokemon.getName() , 600, 330, pokeFont.getFont(30f), Color.black));
                    toAdd.add(new Text(Condivisa.myCurrentPokemon.getLife() + "/" + Condivisa.myCurrentPokemon.maxLife, 830, 350, pokeFont.getFont(30f), Color.black));
                }
                
                if (Condivisa.gameInput) {
                    toAdd.add(new Text("Cosa deve fare", leftSpacing, firstLine, pokeFont.getFont(40f), Color.white));
                    toAdd.add(new Text(Condivisa.myCurrentPokemon.getName() + "?", leftSpacing, secondLine, pokeFont.getFont(40f), Color.white));
                }

                this.setData(toAdd);
                toAdd.clear();
                break;
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < allTexts.size(); i++) {
            Text temp = allTexts.get(i);
            g.setColor(temp.color);
            g.setFont(temp.font);
            g.drawString(temp.text, (int) temp.x, (int) temp.y);
        }
    }
}
