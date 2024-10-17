package service;

import service.custom.SupplierService;
import service.custom.impl.*;
import util.ServiceType;

public class BoFactory {

    private static BoFactory instance;

    private BoFactory() {}

    public static BoFactory getInstance() {
        return instance==null?instance=new BoFactory():instance;
    }

    public <T extends SuperBo>T getServiceType(ServiceType type){
        switch(type){
            case USER :
                return (T)new UserServiceImpl();
            case CUSTOMER :
                return (T)new CustomerServiceImpl();
            case ITEM:
                return (T)new ItemServiceImpl();
            case ORDER:
                return (T)new OrderServiceImpl();
            case SUPPLIER:
                return (T)new SupplierServiceImpl();
            default :
                return null;
        }
    }
}
