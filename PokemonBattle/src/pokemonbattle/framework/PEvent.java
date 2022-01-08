/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemonbattle.framework;

/**
 *
 * @author pacie
 */
public class PEvent {
    public String name;
    public boolean running;
    public int cooldown;

    public PEvent(String name, boolean running, int cooldown) {
        this.name = name;
        this.running = running;
        this.cooldown = cooldown;
    }
}
