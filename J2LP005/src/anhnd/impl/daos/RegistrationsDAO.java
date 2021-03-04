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
import org.hibernate.Criteria;
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
            result = (ArrayList<Registrations>) session.createCriteria(Registrations.class).setMaxResults(5).list();
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

    @Override
    public boolean createRegistration(Registrations registration) {
        boolean check = false;
        try {
            session.getTransaction().begin();
            check = session.save(registration) == null ? false : true;
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean updateRegistration(Registrations registration) {
        boolean check = false;
        try {
            session.getTransaction().begin();
            String sql = "Update Registrations set Fullname = ?, Phone = ?, Email = ?, Address = ?, Age = ?, Gender = ?, NumberOfMember = ?, NumberOfChildren = ?, NumberOfAdults = ? "
                    + "where RegistrationID = ?";
            Query query = session.createQuery(sql);
            query.setString(0, registration.getFullname());
            query.setString(1, registration.getPhone());
            query.setString(2, registration.getEmail());
            query.setString(3, registration.getAddress());
            query.setInteger(4, registration.getAge());
            query.setBoolean(5, registration.getGender());
            query.setInteger(6, registration.getNumberOfMember());
            query.setInteger(7, registration.getNumberOfChildren());
            query.setInteger(8, registration.getNumberOfAdults());
            query.setString(9, registration.getRegistrationId());
            check = query.executeUpdate() > 0;
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean removeRegistration(String id) {
        boolean check = false;
        try {
            session.getTransaction().begin();
            String sql = "Delete from Registrations where RegistrationID = ?";
            Query query = session.createQuery(sql);
            query.setString(0, id);
            check = query.executeUpdate() > 0;
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

}