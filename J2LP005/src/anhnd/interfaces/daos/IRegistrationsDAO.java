/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.interfaces.daos;

import anhnd.entity.Registrations;
import java.util.ArrayList;

/**
 *
 * @author anhnd
 */
public interface IRegistrationsDAO {
    public ArrayList<Registrations> findAllRegistrations();
}
