package repository.custom.impl;

import entity.ItemEntity;
import entity.OrderEntity;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.PlaceOrderRepository;
import util.HibernateUtil;

public class PlaceOrderRepositoryImpl implements PlaceOrderRepository {
    @Override
    public boolean save(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ObservableList<OrderEntity> getAll() {
        return null;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public OrderEntity search(String id) {
        return null;
    }

    @Override
    public boolean placeOrder(OrderEntity orderEntity) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.persist(orderEntity);
            Session finalSession = session;
            orderEntity.getOrderDetails().forEach(orderDetail -> {
                finalSession.persist(orderDetail);

                ItemEntity itemEntity = finalSession.get(ItemEntity.class, orderDetail.getItem().getItemCode());
                if (itemEntity != null) {
                    int updatedQty = itemEntity.getQty() - orderDetail.getQty();
                    if (updatedQty < 0) {
                        throw new IllegalStateException("Quantity cannot be negative.");
                    }
                    itemEntity.setQty(updatedQty);
                    finalSession.update(itemEntity);
                }

            });
            session.getTransaction().commit();
            System.out.println("Transaction committed.");
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback(); // Rollback in case of an error
            }
            System.out.println(e.getMessage()); // Log the exception for debugging
            return false; // Return false in case of failure
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed in the end
                System.out.println("Session closed.");
            }
        }
    }
}
