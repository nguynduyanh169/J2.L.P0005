/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.server;

import anhnd.interfaces.rmi.IRegistrationsRMI;
import anhnd.rmi.impls.RegistrationsRMI;
import anhnd.utils.Constants;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author anhnd
 */
public class RMIserver {
    
    public static void main(String[] args) {
        try {
            IRegistrationsRMI registrationsRMI = new RegistrationsRMI();
            LocateRegistry.createRegistry(6789);
            Naming.bind(Constants.URL, registrationsRMI);
            System.out.println("Server running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
