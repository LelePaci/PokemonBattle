/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.peer;

import org.apache.commons.validator.routines.InetAddressValidator;

/**
 *
 * @author pacie
 */
public class Connection {

    public static void validateFromString(String address) {
        InetAddressValidator validator = new InetAddressValidator();
        if (validator.isValidInet4Address(address)) {
            System.out.println("L'indirizzo IP: " + address + " è valido");
        }else{
            System.out.println("L'indirizzo IP: " + address + " non è valido");
        }
    }
}
