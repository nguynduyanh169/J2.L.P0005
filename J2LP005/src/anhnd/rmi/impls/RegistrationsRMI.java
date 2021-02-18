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
import java.util.List;

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

    @Override
    public boolean createRegistration(Registrations registration) throws RemoteException {
        registrationsRepository = new RegistrationsRepositoryImpl();
        return registrationsRepository.createRegistration(registration);
    }

    @Override
    public boolean updateRegistration(Registrations registration) throws RemoteException {
        registrationsRepository = new RegistrationsRepositoryImpl();
        return registrationsRepository.updateRegistration(registration);
    }

    @Override
    public boolean removeRegistration(String id) throws RemoteException {
        registrationsRepository = new RegistrationsRepositoryImpl();
        return registrationsRepository.removeRegistration(id);
    }

    @Override
    public ArrayList<Registrations> findRegistrationByLikeName(String keywords) throws RemoteException {
        ArrayList<Registrations> result = new ArrayList<>();
        registrationsRepository = new RegistrationsRepositoryImpl();
        ArrayList<Registrations> registrations = registrationsRepository.getAllRegistrations();
        for (Registrations registration : registrations) {
            if(registration.getFullname().toLowerCase().contains(keywords.toLowerCase())){
                result.add(registration);
            }
        }
        return result;
    }
    
}
