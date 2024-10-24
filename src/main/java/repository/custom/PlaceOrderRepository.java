package repository.custom;

import entity.OrderEntity;
import repository.CrudRepository;

public interface PlaceOrderRepository extends CrudRepository<OrderEntity> {
    boolean placeOrder(OrderEntity orderEntity);
}
