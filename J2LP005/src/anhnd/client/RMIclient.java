/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.client;

import anhnd.entity.Registrations;
import anhnd.interfaces.rmi.IRegistrationsRMI;
import anhnd.rmi.impls.RegistrationsRMI;
import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author anhnd
 */
public class RMIclient {
    public static void main(String[] args) {
        try {
            IRegistrationsRMI registrationsRMI = (IRegistrationsRMI) Naming.lookup("rmi://192.168.1.8:6789/RegistrationsRMI");
            ArrayList<Registrations> result = registrationsRMI.getAllRegistrations();
            for (Registrations registrations : result) {
                System.out.println(registrations.getFullname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
