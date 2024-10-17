package service.custom.impl;

import dto.Customer;
import entity.CustomerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerRepository;
import service.custom.CustomerService;
import util.DaoType;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean addCustomer(Customer customer) {
        return customerRepository.save(
                modelMapper.map(customer, CustomerEntity.class)
        );
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public ObservableList<Customer> getAll() {
        ObservableList<Customer>customers= FXCollections.observableArrayList();
        ObservableList<CustomerEntity> all = customerRepository.getAll();
        all.forEach(customerEntity -> {
            customers.add(modelMapper.map(customerEntity, Customer.class));
        });
        return customers;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerRepository.update(modelMapper.map(customer, CustomerEntity.class));
    }

    @Override
    public Customer searchCustomer(String id) {
        return null;
    }

    @Override
    public ObservableList<String> getCustomerIds() {
        return null;
    }

    @Override
    public boolean deleteCustomerById(String text) {
        return customerRepository.delete(text);
    }
}
