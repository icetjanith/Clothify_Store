package repository.custom.impl;

import entity.CustomerEntity;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.SupplierRepository;
import util.HibernateUtil;

public class SupplierRepositoryImpl implements SupplierRepository {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.persist(supplierEntity);
            session.getTransaction().commit();
            return true; // Return true if successful
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback(); // Rollback in case of an error
            }
            e.printStackTrace(); // Log the exception for debugging
            return false; // Return false in case of failure
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed in the end
            }
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(session.load(SupplierEntity.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        return null;
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        return false;
    }

    @Override
    public SupplierEntity search(String id) {
        return null;
    }
}
