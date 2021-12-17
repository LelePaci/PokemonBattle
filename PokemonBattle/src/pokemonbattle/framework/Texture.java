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
public class Texture {

    private BufferedImageLoader loader;
    public BufferedImage image;

    public Texture(String path, int x, int y, int width, int height) {
        loader = new BufferedImageLoader();
        Sprite sprite = new Sprite(loader.loadImage(path));
        image = sprite.takeImage(x, y, width, height);
    }
}
