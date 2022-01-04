/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokemonbattle.framework.*;
import pokemonbattle.objects.*;
import pokemonbattle.peer.*;
import pokemonbattle.util.*;

/**
 *
 * @author paci_emanuele
 */
public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    public static int HEIGHT;
    public static int WIDTH;

    private Graphics g;

    //Object
    private Handler handler;
    private KeyInput input;

    //Other
    public static String stats = "";
    private final Text text = new Text();
    private final PokeFont pokeFont = Condivisa.pokeFont;
    private final XMLParser parser = new XMLParser();
    private static Game game;

    public Game() {
        Window window = new Window(960, 640, "Pokemon Battle", this);
        Condivisa.gameLogic = new GameLogic();
        HEIGHT = getHeight();
        WIDTH = getWidth();
    }

    public static void main(String[] args) {
        game = new Game();
        // Condivisa.client = new TCPClient();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        init();
        this.requestFocus();
        System.out.println("Game started");
        while (running) {
            try {
                Thread.sleep(33);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            tick();
            render();
        }
    }

    public void init() {
        handler = new Handler();
        input = new KeyInput(this, handler, text);
        Condivisa.input = input;
        this.addKeyListener(input);
        createLevel(Condivisa.level);
    }

    public void tick() {
        //Controllo errore di input dell'indirizzo IP
        if (Condivisa.errorAddress) {
            if (Condivisa.errorAddressCooldown > 0) {
                Condivisa.errorAddressCooldown--;
            }
            if (Condivisa.errorAddressCooldown == 0) {
                Condivisa.errorAddressCooldown = Condivisa.errorAddressCooldownDefault;
                Condivisa.errorAddress = false;
            }
        }

        handler.tick();
        text.tick();
        Condivisa.gameLogic.tick();
    }

    public void render() {
        BufferStrategy strategy = this.getBufferStrategy();
        if (strategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        g = strategy.getDrawGraphics();

        handler.render(g);
        text.render(g);
        g.dispose();

        strategy.show();
    }

    public void createLevel(int level) {
        Texture texture = null;

        switch (Condivisa.level) {
            case 0:
                handler.add(new Background("res/intro-screens/background.png", 0, 0, WIDTH, HEIGHT));
                handler.add(new TrainerFront(WIDTH / 2, 185, 2, 3));

                texture = new Texture("res/intro-screens/insert-name.png", 0, 0, 174, 46);
                handler.add(new GenericObject((WIDTH - texture.getSize(3).width) / 2,
                        (HEIGHT - texture.getSize(3).height - 5),
                        texture.getSize(3).width,
                        texture.getSize(3).height, texture));

                break;
            case 1:
                handler.add(new Background("res/pokemon-menu/background-custom.png", 0, 0, WIDTH, HEIGHT));
                int number = Condivisa.pokedexCount;
                int row = 0;
                int col = 0;

                System.out.println("Pokedex count: " + number);
                for (int i = 0; i < number; i++) {
                    String fileName = MyFile.getListOfFiles(Condivisa.pokedexPath)[i];
                    try {
                        String XMLPokemon = MyFile.ReadFileXML(Condivisa.pokedexPath + "/" + fileName);
                        Pokemon temp = parser.getPokemon(XMLPokemon);
                        SelectPokemon select = new SelectPokemon(handler, texture, col * 175 + 45, row * 120 + 25, i, temp);
                        Condivisa.pokedex.add(select);
                        handler.add(select);
                        col++;
                        if (col == 5) {
                            col = 0;
                            row++;
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                texture = new Texture("res/pokemon-menu/text-field.png", 0, 0, 180, 28);
                handler.add(new GenericObject(8, HEIGHT - texture.getSize(4).height - 8,
                        texture.getSize(4).width + 222,
                        texture.getSize(4).height, texture));
                break;

            case 2:
                Condivisa.server = new TCPServer(game, handler);
                Condivisa.server.start();

                int max = MyFile.CountElement("res/background-images");
                int rand = (int) (Math.random() * max) + 1;
                handler.add(new Background("res/background-images/" + rand + ".png", 0, 0, WIDTH, HEIGHT));

                texture = new Texture("res/intro-screens/insert-empty.png", 0, 0, 174, 46);
                handler.add(new GenericObject((WIDTH - texture.getSize(3).width) / 2, 230,
                        texture.getSize(3).width,
                        texture.getSize(3).height, texture));
                break;
            case 3:
                break;
        }
    }
}
