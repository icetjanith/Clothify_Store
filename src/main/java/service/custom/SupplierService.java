package service.custom;

import dto.Customer;
import dto.Supplier;
import javafx.collections.ObservableList;
import service.SuperBo;

public interface SupplierService extends SuperBo {
    boolean addSupplier(Supplier supplier);
    boolean deleteSupplier(String id);
    ObservableList<Supplier> getAll();
    Customer searchCustomer(String id);
    ObservableList<String> getSupplierIds();

    boolean deleteSupplierById(String text);

    boolean updateSupplier(Supplier supplier);
}
