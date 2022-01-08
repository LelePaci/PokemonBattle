/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import pokemonbattle.framework.*;
import pokemonbattle.main.Condivisa;

/**
 *
 * @author pacie
 */
public class TrainerBack extends GameObject {

//    private final Texture[] animationFrames = new Texture[5];
//    private Animation animation;
    private float startX;
    private float finalX;

    public TrainerBack(float x, float y, int sizeMultiplier) {
        super(x, y, sizeMultiplier);
        init();
    }

    public final void init() {
        this.texture = new Texture("res/trainers.png", 15, 168, 55, 64);
//        animationFrames[0] = new Texture("res/trainers.png", 72, 168, 60, 64);
//        animationFrames[1] = new Texture("res/trainers.png", 136, 168, 60, 64);
//        animationFrames[2] = new Texture("res/trainers.png", 200, 168, 60, 64);
//        animationFrames[3] = new Texture("res/trainers.png", 264, 168, 60, 64);
        width = texture.image.getWidth() * sizeMultiplier;
        height = texture.image.getHeight() * sizeMultiplier;

//        animation = new Animation(10, texture.image, animationFrames[0].image, animationFrames[1].image, animationFrames[2].image, animationFrames[3].image);

        startX = 0 - width;
        finalX = x;
        x = startX;

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (Condivisa.battleStarting.running) {
            if (x < finalX) {
                x += 10;
            }
        }
        if (Condivisa.sendMyPokemon.running) {
            if (x > startX) {
                x -= 10;
            }
        }
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(texture.image, (int) (x - width / 2), (int) y, (int) width, (int) height, null);
    }
}
