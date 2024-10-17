package repository.custom.impl;

import db.DBConnection;
import entity.CustomerEntity;
import entity.UserEntity;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import repository.custom.CustomerRepository;
import util.HibernateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public boolean save(CustomerEntity customerEntity) {

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.persist(customerEntity);
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
    public boolean delete(String id) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(session.load(CustomerEntity.class, id));
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
    public ObservableList<CustomerEntity> getAll() {
        ObservableList<CustomerEntity> customerEntities = FXCollections.observableArrayList();
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            String sql = "Select * from customerentity";
            Query query = session.createNativeQuery(sql, CustomerEntity.class);
            List resultList = query.getResultList();
            resultList.forEach(object -> {
                customerEntities.add((CustomerEntity) object);
            });
            System.out.println(customerEntities);
            session.getTransaction().commit();
            return customerEntities;

        }catch(Exception e){

        }finally{

        }
        return customerEntities;

    }


    @Override
    public boolean update(CustomerEntity customerEntity) {
        Session session = null;
        String sql="UPDATE customerentity SET name=?, dob=?, salary=?, address=?, city=?, province=?, postalcode=? WHERE id=?";
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query = session.createNativeQuery(sql);
            query.setParameter(1, customerEntity.getName());
            query.setParameter(2, customerEntity.getPhone());
            query.setParameter(3, customerEntity.getSalary());
            query.setParameter(4, customerEntity.getAddress());
            query.setParameter(5, customerEntity.getCity());
            query.setParameter(6, customerEntity.getProvince());
            query.setParameter(7, customerEntity.getPostalCode());
            query.setParameter(8, customerEntity.getId());
            // Execute the update query
            int result = query.executeUpdate();

            // Commit the transaction only if the query executed successfully
            if (result > 0) {
                session.getTransaction().commit();
                return true;
            } else {
                session.getTransaction().rollback();
                return false;
            }
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
    public CustomerEntity search(String id) {
        return null;
    }
}
