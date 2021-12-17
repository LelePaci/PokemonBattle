/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import pokemonbattle.framework.*;

/**
 *
 * @author pacie
 */
public class TrainerBack extends GameObject {

    private float width, height;
    
    private Texture[] animationFrames = new Texture[4];
    private Animation animation;

    public TrainerBack(float x, float y) {
        super(x, y);
        init();
    }

    public TrainerBack(float x, float y, int sizeMultiplier) {
        super(x, y, sizeMultiplier);
        init();
    }

    public final void init() {
        this.texture = new Texture("res/trainers.png", 15, 168, 55, 64);
        animationFrames[0] = new Texture("res/trainers.png", 72, 168, 60, 64);
        animationFrames[1] = new Texture("res/trainers.png", 136, 168, 60, 64);
        animationFrames[2] = new Texture("res/trainers.png", 200, 168, 60, 64);
        animationFrames[3] = new Texture("res/trainers.png", 264, 168, 60, 64);
        width = texture.image.getWidth() * sizeMultiplier;
        height = texture.image.getHeight() * sizeMultiplier;
        
        animation = new Animation(10, texture.image, animationFrames[0].image, animationFrames[1].image, animationFrames[2].image, animationFrames[3].image);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        animation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(texture.image, (int) (x - width / 2), (int) y, (int) width, (int) height, null);
        animation.drawAnimation(g, (int) (x - width / 2), (int) y, (int) width, (int) height);
    }
}
