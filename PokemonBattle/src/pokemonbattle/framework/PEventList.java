/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemonbattle.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokemonbattle.main.Condivisa;

/**
 *
 * @author pacie
 */
public class PEventList extends Thread {

    private List<PEvent> list;
    private int cooldown = Condivisa.defaultCooldown;

    public PEventList() {
        this.list = new ArrayList();
        //PEventList pList = new PEventList();
        //pList.start();
    }

    public void addEvent(PEvent event) {
        list.add(event);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(33);
            } catch (InterruptedException ex) {
                Logger.getLogger(PEventList.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!list.isEmpty()) {
//                System.out.println("lenght" + list.size());
                PEvent event = list.get(0);
                event.running = true;
                if (event.running) {
//                    System.out.println(event.name + " " + event.cooldown);
                    if (event.cooldown > 0) {
                        event.cooldown--;

                    }
                    if (event.cooldown == 0) {
//                        System.out.println("fine evento");
                        event.cooldown = cooldown;
                        event.running = false;
                        if (event.name.equals("sendEnemyPokemon") || event.name.equals("sendMyPokemon")) {
                            Condivisa.gameInput = true;
                        }
                        list.remove(0);
                    }
                }
            }
        }
    }
}
