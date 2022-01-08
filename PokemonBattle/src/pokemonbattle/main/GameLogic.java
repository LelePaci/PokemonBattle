/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.main;

import java.util.ArrayList;
import java.util.List;
import pokemonbattle.objects.*;
import pokemonbattle.peer.*;
import pokemonbattle.util.*;

/**
 *
 * @author matti
 */
public class GameLogic {
    
    private static List<String> listPeerEvent;
    private final XMLParser parser = new XMLParser();
    private boolean inviaNick = true;
    private boolean turno;
    private boolean inviaPrimoPokemon = false;
    
    public GameLogic() {
        listPeerEvent = new ArrayList();
    }
    
    public void tick() {
        if (Condivisa.client != null) {
            if (Condivisa.client.isConnected()) {
                if (inviaNick) {
                    if (Condivisa.client.isFirstPeer()) {
                        String xml = parser.getXMLAllenatore(Condivisa.myName);
                        Condivisa.client.invia(xml);
                        inviaNick = false;
                    } else {
                        String cmd = "";
                        if (leggiEvento(cmd)) { //caso di leggi evento è "m"
                            String xml = parser.getXMLAllenatore(Condivisa.myName);
                            Condivisa.client.invia(xml);
                            if (!inviaPrimoPokemon) {
                                Pokemon send = Condivisa.selectedPokemon.get(0);
                                Condivisa.myCurrentPokemon = send;
                                xml = parser.getXMLInvioPokemon("s", send.getName(), send.getLife(), send.getType());
                                Condivisa.client.invia(xml);
                                inviaPrimoPokemon = true;
                                Condivisa.myPokemonInCampo = true;
                            }
                            inviaNick = false;
                            //
                            //Condivisa.sendMyPokemon.running = true;
                            Condivisa.eventList.addEvent(Condivisa.sendMyPokemon);
                            Condivisa.myCurrentPokemon = Condivisa.selectedPokemon.get(0);
                        }
                    }
                }
                String cmd = "";
                if (leggiEvento(cmd)) {
                    if (cmd.equals("s")) {
                        System.out.println("test");
                        //invia pokemon
                        //invia attacco
                    }
                    if (cmd.equals("a")) {
                        //faccio i calcoli per grafica
                        //invia risposta
                    }
                    if (cmd.equals("r")) {
                        //faccio i calcoli per grafica
                        //invia fine turno
                    }
                    if (cmd.equals("r")) {
                        //faccio i calcoli per grafica
                        //invia fine turno
                    }
                    if (cmd.equals("p")) {
                        //invia fine turno
                        //invio danni over time nel caso ci sono
                        //invio attacco
                    }
                    if (cmd.equals("e")) {
                        //applico danni pokemon avversario
                    }
                }
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
    
    private boolean leggiEvento(String cmd) {
        if (!listPeerEvent.isEmpty()) {
            parser.parseString(listPeerEvent.get(0));
            listPeerEvent.remove(0);
            String comando = parser.getComando();
            cmd = comando;
            switch (comando) {
                case "m":
                    Condivisa.enemyName = parser.getNomeAllenatore();
                    System.out.println("Ricevuto nickname: " + Condivisa.enemyName);
                    Condivisa.eventList.addEvent(Condivisa.battleStarting);
                    break;
                case "s":
                    System.out.println("Ricevuto primo pokemon avversario");
                    Condivisa.enemyPokemonInCampo = true;
                    Condivisa.eventList.addEvent(Condivisa.sendEnemyPokemon);
                    String nome = parser.getNomePokemon();
                    int vita = parser.getVitaPokemon();
                    String[] tipi = {parser.getTipoPokemon()};
                    Condivisa.enemyPokemon = new Pokemon(nome, vita, tipi);
                    if (!inviaPrimoPokemon) {
                        Pokemon send = Condivisa.selectedPokemon.get(0);
                        Condivisa.myCurrentPokemon = send;
                        String xml = parser.getXMLInvioPokemon("s", send.getName(), send.getLife(), send.getType());
                        Condivisa.client.invia(xml);
                        inviaPrimoPokemon = true;
                        Condivisa.eventList.addEvent(Condivisa.sendMyPokemon);
                        Condivisa.myPokemonInCampo = true;
                    }
                    //in seguito inviare l'attacco dopo input
                    break;
                
                case "a":
                    System.out.println("Ricevuto attacco dall'avversario");
//                    System.out.println("Nome mossa: " + parser.getNomeMossa());
//                    System.out.println("Tipo mossa: " + parser.getTipoMossa());
//                    System.out.println("Danni mossa: " + parser.getDanniMossa());
//                    System.out.println("Tipo Status: " + parser.getTipoStatus());
//                    System.out.println("Probabilità Status: " + parser.getProbStatus());

                    Pokemon p = Condivisa.myCurrentPokemon;
                    
                    int vitaRimanente = p.getLife() - parser.getDanniMossa();
                    Condivisa.myCurrentPokemon.setLife(vitaRimanente);
                    int molt = 0;
                    String note = "";
                    String xml = "";
                    if (vitaRimanente <= 0) {
                        if (Condivisa.pokemonRimanenti < 0) {
                            //resa obbligatoria
                        } else {
                            Condivisa.level++;
                            Condivisa.handler.clearLevel();
                            Condivisa.game.createLevel(Condivisa.level);
                        }
                        
                        xml = parser.getXMLPokemonSconfitto(Condivisa.myCurrentPokemon.getName());
                    } else {
                        xml = parser.getXMLRispostaAttacco(vitaRimanente, molt, p.getStatus(), note);
                    }
                    Condivisa.client.invia(xml);
                    break;
                case "r":
                    System.out.println("Ricevuta risposta attacco");
//                    System.out.println("Vita rimanente: " + parser.getVitaRimanente());
//                    System.out.println("Moltiplicatore: " + parser.getMoltiplicatore());
//                    System.out.println("Status vita: " + parser.getStatus());
//                    System.out.println("Note: " + parser.getNote());
                    Condivisa.enemyPokemon.setLife(parser.getVitaRimanente());
                    xml = parser.getXMLPassoTurno();
                    Condivisa.client.invia(xml);
                    break;
                case "i":
                    System.out.println("Oggetto: " + parser.getOggetto());
                    System.out.println("Nome Pokemon: " + parser.getNomePokemon());
                    System.out.println("Vita attuale: " + parser.getVitaAttuale());
                    //visualizzare a schermo
                    
                    break;
                case "f":
                    System.out.println("L'avversario si è arreso");
                    //visualizzare a schermo
                    break;
                case "c":
                    System.out.println("Nome: " + parser.getNomePokemon());
                    System.out.println("Vita: " + parser.getVitaPokemon());
                    System.out.println("Tipo: " + parser.getTipoPokemon());
                    //inviare attacco
                    break;
                
                case "l":
                    System.out.println("Nome Pokemon: " + parser.getNomePokemon());
                    //visualizzare pokemon sconfitto
                    //attendere l'avversario
                    Condivisa.enemyPokemonInCampo = false;
                    break;
                case "e":
                    System.out.println("Nome Pokemon: " + parser.getNomePokemon());
                    System.out.println("Status: " + parser.getStatus());
                    
                    System.out.println("Aggiunta: " + parser.getDOTAggiunta());
                    System.out.println("Dps: " + parser.getDOTDps());
                    System.out.println("Tipo: " + parser.getDOTTipo());
                    //visualizzare a schermo
                    break;
                case "p":
                    System.out.println("Ricevuto fine turno avversario");
                    Pokemon temp = Condivisa.myCurrentPokemon;
                    xml = parser.getXMLStatus(temp.getName(), temp.getStatus(), temp.getDanniOverTime_aggiunta(), temp.getDanniOverTime_dps(), temp.getDanniOverTime_tipo(), temp.getLife());
                    Condivisa.client.invia(xml);
                    break;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public void connectionReceived() {
        Condivisa.level++;
        Condivisa.handler.clearLevel();
        Condivisa.game.createLevel(Condivisa.level);
        Condivisa.client = Condivisa.server.getClient();
        turno = Condivisa.client.isFirstPeer();
    }
    
    public void connectionStarted() {
        Condivisa.level++;
        Condivisa.handler.clearLevel();
        Condivisa.game.createLevel(Condivisa.level);
        Condivisa.server.stopReceive();
        Condivisa.client = new TCPClient(Condivisa.input.ipAddress);
        Condivisa.client.start();
        turno = Condivisa.client.isFirstPeer();
    }
}
