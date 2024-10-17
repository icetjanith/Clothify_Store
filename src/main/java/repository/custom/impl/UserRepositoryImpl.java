package repository.custom.impl;

import entity.UserEntity;
import jakarta.persistence.Query;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.UserRepository;
import util.HibernateUtil;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean save(UserEntity userEntity) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.persist(userEntity);
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
    public UserEntity authenticateUser(String email, String password) {

        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            String sql = "Select * from Users where email = ? and password = ?";
            Query query = session.createNativeQuery(sql, UserEntity.class);
            query.setParameter(1, email);
            query.setParameter(2, password);
            UserEntity userEntity = (UserEntity) query.getSingleResult();
            System.out.println(userEntity);
            session.getTransaction().commit();
            return userEntity;

        }catch(Exception e){

        }finally{

        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ObservableList<UserEntity> getAll() {
        return null;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        return false;
    }

    @Override
    public UserEntity search(String id) {
        return null;
    }


}
