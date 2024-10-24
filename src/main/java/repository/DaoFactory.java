package repository;

import repository.custom.impl.*;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.DaoType;

public class DaoFactory {

    private static DaoFactory instance;
    private DaoFactory() {}
    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }
    public <T> T getDaoType(DaoType daoType) {
        switch (daoType) {
            case USER:
                return (T)new UserRepositoryImpl();
            case CUSTOMER:
                return (T)new CustomerRepositoryImpl();
            case ITEM:
                return (T)new ItemRepositoryImpl();
            case SUPPLIER:
                return (T)new SupplierRepositoryImpl();
            case PLACEORDER:
                return (T) new PlaceOrderRepositoryImpl();
            default:
                return null;
        }

    }
}
