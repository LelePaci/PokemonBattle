/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokemonbattle.util.XMLParser;

/**
 *
 * @author matti
 */
public class GameLogic {

    private static List<String> listPeerEvent;
    private XMLParser parser = new XMLParser();
    
    public GameLogic() {
        listPeerEvent = new ArrayList();
    }

    public void tick() {
            if (!listPeerEvent.isEmpty()) {
                parser.parseString(listPeerEvent.get(0));
                listPeerEvent.remove(0);
                String comando = parser.getComando();
                switch (comando) {
                    case "m":
                        System.out.println(parser.getNomeAllenatore());
                        break;
                    case "s":
                        System.out.println("Nome: " + parser.getNomePokemon());
                        System.out.println("Vita: " + parser.getVitaPokemon());
                        System.out.println("Tipo: " + parser.getTipoPokemon());
                        break;

                    case "a":
                        System.out.println("Nome mossa: " + parser.getNomeMossa());
                        System.out.println("Tipo mossa: " + parser.getTipoMossa());
                        System.out.println("Danni mossa: " + parser.getDanniMossa());
                        System.out.println("Tipo Status: " + parser.getTipoStatus());
                        System.out.println("Probabilità Status: " + parser.getProbStatus());
                        break;
                    case "r":
                        System.out.println("Vita rimanente: " + parser.getVitaRimanente());
                        System.out.println("Moltiplicatore: " + parser.getMoltiplicatore());
                        System.out.println("Status vita: " + parser.getStatus());
                        System.out.println("Note: " + parser.getNote());
                        break;
                    case "i":
                        System.out.println("Oggetto: " + parser.getOggetto());
                        System.out.println("Nome Pokemon: " + parser.getNomePokemon());
                        System.out.println("Vita attuale: " + parser.getVitaAttuale());
                        break;
                    case "f":
                        System.out.println("L'avversario si è arreso");
                        break;
                    case "c":
                        System.out.println("Nome: " + parser.getNomePokemon());
                        System.out.println("Vita: " + parser.getVitaPokemon());
                        System.out.println("Tipo: " + parser.getTipoPokemon());
                        break;

                    case "l":
                        System.out.println("Nome Pokemon: " + parser.getNomePokemon());
                        break;
                    case "e":
                        System.out.println("Nome Pokemon: " + parser.getNomePokemon());
                        System.out.println("Status: " + parser.getStatus());

                        System.out.println("Aggiunta: " + parser.getDOTAggiunta());
                        System.out.println("Dps: " + parser.getDOTDps());
                        System.out.println("Tipo: " + parser.getDOTTipo());
                        break;
                }
            }
    }

    public void addPeerEvent(String toAdd) {
        listPeerEvent.add(toAdd);

    }

    public void printListPeerEvent() {
        if (!listPeerEvent.isEmpty()) {
            for (int i = 0; i < listPeerEvent.size(); i++) {
                System.out.println("list " + i + ": " + listPeerEvent.get(i));
            }
        }
    }
}
