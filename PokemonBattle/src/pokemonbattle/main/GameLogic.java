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
public class GameLogic extends Thread {

    private static List<String> list;
    private XMLParser parser = new XMLParser();

    public GameLogic() {
        list = new ArrayList();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!list.isEmpty()) {
                    parser.parseString(list.get(0));
                    list.remove(0);
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
                            String Tipo_Danni = parser.getStatus();
                            String Tipo = Tipo_Danni.substring(0, Tipo_Danni.indexOf(";"));
                            String Dn = Tipo_Danni.substring(Tipo_Danni.indexOf(";") + 1);
                            int Danni = Integer.parseInt(Dn);
                            System.out.println("Tipo Status: " + Tipo);
                            System.out.println("Danni Status: " + Danni);
                            break;
                        case "r":
                            System.out.println("Vita rimanente: " + parser.getVitaRimanente());
                            System.out.println("Moltiplicatore: " + parser.getMoltiplicatore());
                            System.out.println("Status vita: " + parser.getStatusVita());
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
                            System.out.println("Status vita: " + parser.getStatusVita());
                            String DanniOverTime = parser.getdanniOverTime();
                            String SplitDanni[]=DanniOverTime.split(";");
                            String Aggiunta = SplitDanni[0];
                            String dps = SplitDanni[1];
                            String TipoDanni = SplitDanni[2];
                            int Dps= Integer.parseInt(dps);
                            boolean Agg=Boolean.parseBoolean(Aggiunta);
                            System.out.println("Aggiunta: " + Agg);
                            System.out.println("Dps: " + Dps);
                            System.out.println("Tipo: " + TipoDanni);
                            break;

                    }
                }

                Thread.sleep(33);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void add(String toAdd) {
        list.add(toAdd);

    }

    public void printList() {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {

                System.out.println("list " + i + ": " + list.get(i));
            }
        }
    }
}
