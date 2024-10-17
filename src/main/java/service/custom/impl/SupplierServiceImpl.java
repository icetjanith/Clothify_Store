package service.custom.impl;

import dto.Customer;
import dto.Supplier;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierRepository;
import service.custom.SupplierService;
import util.DaoType;

public class SupplierServiceImpl implements SupplierService {
    SupplierRepository supplierRepository= DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
    ModelMapper modelMapper=new ModelMapper();

    @Override
    public boolean addSupplier(Supplier supplier) {
        return supplierRepository.save(modelMapper.map(supplier, SupplierEntity.class));
    }

    @Override
    public boolean deleteSupplier(String id) {
        return supplierRepository.delete(id);
    }

    @Override
    public ObservableList<Supplier> getAll() {
        return null;
    }


    @Override
    public Customer searchCustomer(String id) {
        return null;
    }

    @Override
    public ObservableList<String> getSupplierIds() {
        return null;
    }

    @Override
    public boolean deleteSupplierById(String text) {
        return false;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return false;
    }
}
