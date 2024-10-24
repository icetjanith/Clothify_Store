package service.custom;

import dto.Order;
import service.SuperBo;

public interface PlaceOrderService extends SuperBo{
    boolean PlaceOrder (Order order);
}
