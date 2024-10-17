package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BoFactory;
import service.SuperBo;
import service.custom.CustomerService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public TableColumn colSalary;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    public ComboBox cmbTitle;
    public JFXTextField txtSalary;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXTextField txtTelephoneNum;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tblCustomers;

    private final CustomerService customerService = BoFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr");
        titles.add("Miss");
        titles.add("Ms");
        cmbTitle.setItems(titles);
        tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }

    private void setTextToValues(Customer newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtTelephoneNum.setText(newValue.getPhone());
        cmbTitle.setValue(newValue.getCity());
        txtProvince.setText(newValue.getProvince());
        txtPostalCode.setText(newValue.getPostalCode());
        txtSalary.setText(newValue.getSalary().toString());
        txtCity.setText(newValue.getCity());
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        tblCustomers.refresh();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if(isCustomerIdValid(txtId.getText())) {
            new Alert(Alert.AlertType.ERROR,"Customer already exists!").show();
        }else {
            Customer customer = new Customer(
                    txtId.getText(),
                    cmbTitle.getValue().toString(),
                    txtName.getText(),
                    txtTelephoneNum.getText(),
                    Double.parseDouble(txtSalary.getText()),
                    txtAddress.getText(),
                    txtCity.getText(), txtProvince.getText(),
                    txtPostalCode.getText()

            );
            if(customerService.addCustomer(customer)) {
                new Alert(Alert.AlertType.INFORMATION,"Customer added successfully!").show();
//                loadTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Customer not add").show();
            }
        }
    }

    private boolean isCustomerIdValid(String id) {
        for(Customer customer:customerService.getAll()){
            if (customer.getId().equals(id)) {
                return true;
            }
        };
        return false;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        for(Customer customer:customerService.getAll()){
            if(customer.getId().equals(txtId.getText())){
                if(customerService.deleteCustomerById(txtId.getText())){
                    new Alert(Alert.AlertType.INFORMATION,"Customer deleted successfully!").show();
                    return;
                }else {
                    new Alert(Alert.AlertType.ERROR,"Customer not deleted").show();
                    return;
                }
            }
        }
        new Alert(Alert.AlertType.ERROR,"Customer not found").show();
    }

    private void loadTable() {

        ObservableList<Customer> all = customerService.getAll();
        tblCustomers.setItems(all);
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {

        Customer customer = new Customer(
                txtId.getText(),
                cmbTitle.getValue().toString(),
                txtName.getText(),
                txtTelephoneNum.getText(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(), txtProvince.getText(),
                txtPostalCode.getText()

        );
        if(customerService.updateCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION,"Customer updated successfully!").show();
//                loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer not add").show();
        }

    }
}
