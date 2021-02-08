/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.server;

import anhnd.interfaces.rmi.IRegistrationsRMI;
import anhnd.rmi.impls.RegistrationsRMI;
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
            Naming.bind("rmi://192.168.1.8:6789/RegistrationsRMI", registrationsRMI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
