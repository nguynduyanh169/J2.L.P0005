/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.impl.daos;

import anhnd.entity.Registrations;
import anhnd.interfaces.daos.IRegistrationsDAO;
import anhnd.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author anhnd
 */
public class RegistrationsDAO implements IRegistrationsDAO {

    private Session session;

    public RegistrationsDAO() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.getCurrentSession();
    }

    @Override
    public ArrayList<Registrations> findAllRegistrations() {
        ArrayList<Registrations> result = null;
        try {
            session.getTransaction().begin();
            result = (ArrayList<Registrations>) session.createCriteria(Registrations.class).list();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Registrations findByRegistrationID(String id) {
        Registrations registration = null;
        try {
            session.getTransaction().begin();
            registration = (Registrations) session.get(Registrations.class, id);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return registration;
    }
}
