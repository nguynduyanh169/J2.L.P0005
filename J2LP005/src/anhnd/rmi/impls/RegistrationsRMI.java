/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.rmi.impls;

import anhnd.entity.Registrations;
import anhnd.impl.repos.RegistrationsRepositoryImpl;
import anhnd.intefaces.repos.IRegistrationsRepository;
import anhnd.interfaces.rmi.IRegistrationsRMI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author anhnd
 */
public class RegistrationsRMI extends UnicastRemoteObject implements IRegistrationsRMI{
    
    private IRegistrationsRepository registrationsRepository;
    
    public RegistrationsRMI() throws RemoteException{
        
    }

    @Override
    public ArrayList<Registrations> getAllRegistrations() throws RemoteException{
        registrationsRepository = new RegistrationsRepositoryImpl();
        return registrationsRepository.getAllRegistrations();
    }

    @Override
    public Registrations findByRegistrationID(String id) throws RemoteException {
        registrationsRepository = new RegistrationsRepositoryImpl();
        return registrationsRepository.findByRegistrationID(id);
    }
    
}
