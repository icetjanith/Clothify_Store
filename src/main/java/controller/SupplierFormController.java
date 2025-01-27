package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Item;
import dto.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.BoFactory;
import service.custom.SupplierService;
import util.ServiceType;

public class SupplierFormController {

    @FXML
    private JFXTextField companyName;

    @FXML
    private JFXTextField contactNumber;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField nicNumber;

    @FXML
    private JFXTextField searchBox;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TableView<?> tblAllSuppliers;

    @FXML
    private TableView<?> tblSupplierDetails;

    private final SupplierService supplierService= BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Supplier supplier=new Supplier(
                name.getText(),
                email.getText(),
                contactNumber.getText(),
                nicNumber.getText(),
                companyName.getText()
        );
        if(supplierService.addSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier added successfully !").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier not added !").show();
        }
    }

    private void setTextToValues(Supplier newValue) {
        name.setText(newValue.getName());
        email.setText(newValue.getEmail());
        contactNumber.setText(newValue.getPhoneNumber());
        nicNumber.setText(newValue.getNicNumber());
        companyName.setText(newValue.getCompanyName());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(supplierService.deleteSupplier(nicNumber.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Supplier deleted !");
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier not deleted !");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier supplier=new Supplier(
                name.getText(),
                email.getText(),
                contactNumber.getText(),
                nicNumber.getText(),
                companyName.getText()
        );
        if(supplierService.updateSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier updated successfully !");
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier not updated !");
        }
    }

    public void imgOnMouseClicked(MouseEvent mouseEvent) {
        System.out.println("mouse clicked");
        Supplier supplier = supplierService.searchItem(searchBox.getText());
        if(supplier!=null) {
            setTextToValues(supplier);
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier not found").show();
        }
    }
}
