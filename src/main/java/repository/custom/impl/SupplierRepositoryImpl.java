package repository.custom.impl;

import entity.CustomerEntity;
import entity.SupplierEntity;
import jakarta.persistence.Query;
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
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String sql="SELECT*FROM suppliers WHERE supplier_id=?";
            Query query =session.createNativeQuery(sql,SupplierEntity.class);
            query.setParameter(1, id);
            SupplierEntity supplierEntity = (SupplierEntity) query.getSingleResult();
            session.getTransaction().commit();
            return supplierEntity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if (session!= null) {
                session.close();
            }
        }
        return null;
    }
}
