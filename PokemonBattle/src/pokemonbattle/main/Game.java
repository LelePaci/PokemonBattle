/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokemonbattle.framework.*;
import pokemonbattle.objects.*;

/**
 *
 * @author paci_emanuele
 */
public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    public static int HEIGHT;
    public static int WIDTH;

    //Object
    private Handler handler;
    private KeyInput input;

    //Other
    public static String stats = "";
    private Text text = new Text();
    private PokeFont pokeFont;

    public Game() {
        Window window = new Window(960, 640, "Pokemon Battle", this);
        HEIGHT = getHeight();
        WIDTH = getWidth();
    }

    public static void main(String[] args) {
        Game game = new Game();
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
    public void run() {
        init();
        this.requestFocus();
        System.out.println("Game started");
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            try {
                Thread.sleep(33);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                stats = "FPS: " + frames + " TICKS: " + updates;
                //System.out.println(stats);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void init() {
        handler = new Handler();
        input = new KeyInput(handler);
        this.addKeyListener(input);
        pokeFont = new PokeFont();
        createLevel_InsertName(Condivisa.level);
    }

    public void tick() {
        text.setData(new Text(stats, 5, 12, pokeFont.getFont(10f), Color.black),
                new Text(input.name, 425, 602, pokeFont.getFont(30f), Color.red)
        );
        handler.tick();
    }

    public void render() {
        BufferStrategy strategy = this.getBufferStrategy();
        if (strategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = strategy.getDrawGraphics();

        handler.render(g);
        g.dispose();

        strategy.show();
    }

    public void createLevel_InsertName(int level) {
        Texture texture;

        switch (Condivisa.level) {
            case 0:
                handler.add(new Background("res/intro-screens/background.png", 0, 0, WIDTH, HEIGHT));
                handler.add(new TrainerFront(WIDTH / 2, 185, 2, 3));
                //handler.add(new TrainerBack(WIDTH / 2, 185, 3));

                texture = new Texture("res/intro-screens/insert-name.png", 0, 0, 174, 46);
                handler.add(new GenericObject((WIDTH - texture.getSize(3).width) / 2,
                        (HEIGHT - texture.getSize(3).height - 5),
                        texture.getSize(3).width,
                        texture.getSize(3).height, texture));

                //statsText = new Text(5, 12, 15f, pokeFont, Color.black);
                handler.add(text);
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
