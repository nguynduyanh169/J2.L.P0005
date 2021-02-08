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

    private IRegistrationsDAO registrationsDAO = new RegistrationsDAO();
    
    @Override
    public ArrayList<Registrations> getAllRegistrations() {
        return registrationsDAO.findAllRegistrations();
    }
    
}
