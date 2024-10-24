package repository.custom.impl;

import entity.CustomerEntity;
import entity.ItemEntity;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.ItemRepository;
import util.HibernateUtil;

import java.util.List;


public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public boolean save(ItemEntity itemEntity) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.persist(itemEntity);
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
        return false;
    }

    @Override
    public ObservableList<ItemEntity> getAll() {
        ObservableList<ItemEntity> itemEntities = FXCollections.observableArrayList();
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            String sql = "Select * from items";
            Query query = session.createNativeQuery(sql, ItemEntity.class);
            List resultList = query.getResultList();
            resultList.forEach(object -> {
                itemEntities.add((ItemEntity) object);
            });
            System.out.println(itemEntities);
            session.getTransaction().commit();
            return itemEntities;

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{

        }
        return null;
    }

    @Override
    public boolean update(ItemEntity itemEntity) {
        return false;
    }

    @Override
    public ItemEntity search(String id) {
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            String sql = "SELECT * FROM items WHERE itemCode = ?";
            Query query = session.createNativeQuery(sql, ItemEntity.class);
            query.setParameter(1, id);
            ItemEntity singleResult = (ItemEntity) query.getSingleResult();
            session.getTransaction().commit();
            return singleResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }finally {
            if (session!= null) {
                session.close();
            }
        }
        return null;
    }
}
