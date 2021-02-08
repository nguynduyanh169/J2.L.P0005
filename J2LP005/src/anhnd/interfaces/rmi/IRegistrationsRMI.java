/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.interfaces.rmi;

import anhnd.entity.Registrations;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author anhnd
 */
public interface IRegistrationsRMI extends Remote{

    public ArrayList<Registrations> getAllRegistrations() throws RemoteException;
}
