package service.custom;

import dto.Customer;
import javafx.collections.ObservableList;
import service.SuperBo;

public interface CustomerService extends SuperBo {
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
    ObservableList<Customer> getAll();
    boolean updateCustomer(Customer customer);
    Customer searchCustomer(String id);
    ObservableList<String> getCustomerIds();

    boolean deleteCustomerById(String text);
}
