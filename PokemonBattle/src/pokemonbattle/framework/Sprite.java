/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.image.BufferedImage;

/**
 *
 * @author pacie
 */
public class Sprite {
    private BufferedImage image;

    public Sprite(BufferedImage image) {
        this.image = image;
    }
    
    public BufferedImage takeImage(int x, int y, int width, int height){
        BufferedImage img = image.getSubimage(x, y, width, height);
        return img;
    }
}
