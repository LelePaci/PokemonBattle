/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pacie
 */
public class Texture {

    private Sprite trainerSprite;

    public BufferedImage[] trainers = new BufferedImage[2];

    public Texture() {
        
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            trainerSprite = new Sprite(loader.laodImage("assets/trainers.png"));
        } catch (IOException ex) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
        }

        getTextures();
    }

    private void getTextures() {
        trainers[0] = trainerSprite.takeImage(0, 0, 0, 0); //trainer di fronte
        trainers[1] = trainerSprite.takeImage(10, 10, 100, 100); //trainer di spalle
    }
}
