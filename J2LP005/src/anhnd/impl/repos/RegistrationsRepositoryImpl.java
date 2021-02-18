/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.impl.repos;

import anhnd.entity.Registrations;
import anhnd.impl.daos.RegistrationsDAO;
import anhnd.intefaces.repos.IRegistrationsRepository;
import anhnd.interfaces.daos.IRegistrationsDAO;
import java.util.ArrayList;

/**
 *
 * @author anhnd
 */
public class RegistrationsRepositoryImpl implements IRegistrationsRepository{

    private IRegistrationsDAO registrationsDAO;
    
    @Override
    public ArrayList<Registrations> getAllRegistrations() {
        registrationsDAO = new RegistrationsDAO();
        return registrationsDAO.findAllRegistrations();
    }

    @Override
    public Registrations findByRegistrationID(String id) {
        registrationsDAO = new RegistrationsDAO();
        return registrationsDAO.findByRegistrationID(id);
    }

    @Override
    public boolean createRegistration(Registrations registration) {
        registrationsDAO = new RegistrationsDAO();
        return registrationsDAO.createRegistration(registration);
    }

    @Override
    public boolean updateRegistration(Registrations registration) {
        registrationsDAO = new RegistrationsDAO();
        return registrationsDAO.updateRegistration(registration);
    }

    @Override
    public boolean removeRegistration(String id) {
        registrationsDAO = new RegistrationsDAO();
        return registrationsDAO.removeRegistration(id);
    }
    
}
