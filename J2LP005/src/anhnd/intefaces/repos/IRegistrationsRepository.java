/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.intefaces.repos;

import anhnd.entity.Registrations;
import java.util.ArrayList;

/**
 *
 * @author anhnd
 */
public interface IRegistrationsRepository {
    public ArrayList<Registrations> getAllRegistrations();
    
    public Registrations findByRegistrationID(String id);
    
    public boolean createRegistration(Registrations registration);
    
    public boolean updateRegistration(Registrations registration);
}
