/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.peer;

//import org.apache.commons.validator.routines.InetAddressValidator;

/**
 *
 * @author pacie
 */
public class Connection {

    public static void validateFromString(String address) {
        //InetAddressValidator validator = new InetAddressValidator();
        if (validator.isValidInet4Address(address)) {
            System.out.print("The IP address " + address + " is valid");
        }else{
            System.out.print("The IP address " + address + " isn't valid");
        }
    }
}
