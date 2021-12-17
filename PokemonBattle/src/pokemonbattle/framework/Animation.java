/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author pacie
 */
public class Animation {

    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;
    private BufferedImage[] images;
    private BufferedImage currentImage;

    public Animation(int speed, BufferedImage... list) {
        this.speed = speed;
        this.frames = list.length;
        this.images = new BufferedImage[frames];
        for (int i = 0; i < frames; i++) {
            images[i] = list[i];
        }
    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    public void nextFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i) {
                currentImage = images[i];
            }
        }
        count++;
        if (count > frames) {
            count = 0;
        }
    }

    public void drawAnimation(Graphics g, int x, int y) {
        g.drawImage(currentImage, x, y, null);
    }

    public void drawAnimation(Graphics g, int x, int y, int width, int height) {
        g.drawImage(currentImage, x, y, width, height, null);
    }

}
