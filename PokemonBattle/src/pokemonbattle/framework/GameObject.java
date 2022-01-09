/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author paci_emanuele
 */
public abstract class GameObject {

    protected float x, y;
    protected float velX = 0, velY = 0;
    protected float width, height;
    protected int sizeMultiplier;
    protected Texture texture;

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(float x, float y, int sizeMultiplier) {
        this.x = x;
        this.y = y;
        this.sizeMultiplier = sizeMultiplier;
    }

    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public GameObject(float x, float y, float width, float height, int sizeMultiplier) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sizeMultiplier = sizeMultiplier;
    }

    public GameObject(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public GameObject(float x, float y, Texture texture, int sizeMultiplier) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.sizeMultiplier = sizeMultiplier;
    }

    public GameObject(float x, float y, float width, float height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    public GameObject(float x, float y, float width, float height, Texture texture, int sizeMultiplier) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.sizeMultiplier = sizeMultiplier;
    }

    public abstract void tick(LinkedList<GameObject> object);

    public abstract void render(Graphics g);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getHeight() {
        return height;
    }    
}
