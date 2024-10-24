package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import dto.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.BoFactory;
import service.custom.ItemService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public TableColumn colCategory;
    public JFXTextField txtSearchItem;
    public JFXComboBox<String> category;
    public JFXTextField supplierId;
    public ImageView searchImg;
    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    private final ItemService itemService = BoFactory.getInstance().getServiceType(ServiceType.ITEM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("Male");
        categoryList.add("Female");
        categoryList.add("Kids");
        category.setItems(categoryList);

        tblItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null){
                setTextToValues(newValue);
            }

        }));

        loadTable();
    }



    private void loadTable() {

        ObservableList<Item> all = itemService.getAll();
        System.out.println(all);
        tblItems.setItems(all);
    }

    private void setTextToValues(Item newValue) {
        txtItemCode.setText(newValue.getItemCode());
        txtDescription.setText(newValue.getDescription());
        txtPackSize.setText(newValue.getPackSize());
        txtUnitPrice.setText(newValue.getUnitPrice().toString());
        txtQty.setText(newValue.getQty().toString());
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if(isItemIdValid(txtItemCode.getText())) {
            new Alert(Alert.AlertType.ERROR,"Item already exists!").show();
        }else {
           Item item = new Item(
                   txtItemCode.getText(),
                   "Kids",
                   supplierId.getText(),
                   txtDescription.getText(),
                   txtPackSize.getText(),
                   Double.parseDouble(txtUnitPrice.getText()),
                   Integer.parseInt(txtQty.getText())
           );
            if(itemService.addItem(item)) {
                new Alert(Alert.AlertType.INFORMATION,"Item added successfully!").show();
                loadTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Item not add").show();
            }
        }
    }

    private boolean isItemIdValid(String id) {
        for(Item item:itemService.getAll()){
            if (item.getItemCode().equals(id)) {
                return true;
            }
        };
        return false;
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Item item = new Item(
                txtItemCode.getText(),
                category.getValue(),
                supplierId.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );
        if(itemService.updateItem(item)) {
            new Alert(Alert.AlertType.INFORMATION,"Item updated successfully!").show();
//                loadTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item not updated").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if(itemService.deleteItemById(txtItemCode.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Item deleted successfully!").show();

        }else {
            new Alert(Alert.AlertType.ERROR,"Item not deleted").show();

        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

    }


    public void imgOnMouseClicked(MouseEvent mouseEvent) {
        System.out.println("mouse clicked");
        Item item = itemService.searchItem(txtSearchItem.getText());
        if(item!=null) {
            setTextToValues(item);
        }else{
            new Alert(Alert.AlertType.ERROR,"Item not found").show();
        }
    }
}
