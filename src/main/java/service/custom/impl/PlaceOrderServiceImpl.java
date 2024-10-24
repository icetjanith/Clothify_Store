package service.custom.impl;

import dto.Order;
import entity.OrderEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.PlaceOrderRepository;
import repository.custom.SupplierRepository;
import service.custom.PlaceOrderService;
import util.DaoType;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    ModelMapper modelMapper=new ModelMapper();
    PlaceOrderRepository placeOrderRepository=DaoFactory.getInstance().getDaoType(DaoType.PLACEORDER);
    @Override
    public boolean PlaceOrder(Order order) {
        return placeOrderRepository.placeOrder(
                modelMapper.map(order, OrderEntity.class)
        );
    }
}
