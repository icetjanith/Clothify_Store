package controller;

import dto.CartTM;
import dto.Customer;
import dto.Item;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.BoFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import util.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public ComboBox<String> cmbItemCode;
    public Label lblNetTotal;
    public TextField txtOrderId;
    public TextField txtTelephoneNumber;
    public TextField txtCustomerId;
    public TextField txtItemDescription1;
    public Label employeeName;
    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<?> comItemCode;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtItemDescription;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPrice;

    private static String empName;

    private ItemService itemService;
    private CustomerService customerService;
     ObservableList<Item> items = FXCollections.observableArrayList();

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Initialize services inside the initialize method
            this.customerService = BoFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
            itemService= BoFactory.getInstance().getServiceType(ServiceType.ITEM);
            items.addAll(itemService.getAll());
            l(); // Assuming this method displays some employee name
            loadDateAndTime(); // Assuming this method sets up date and time
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize services: " + e.getMessage()).show();
        }
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);

        lblDate.setText(dateNow);

//-------------------------------------------------------------------------------

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    private void calcNetTotal(){
        Double total=0.0;

        for (CartTM cartTM: cartTMS){
            total+=cartTM.getTotal();
        }

        lblNetTotal.setText(total.toString()+"/=");

    }

    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String itemCode = cmbItemCode.getValue();
        String description = txtItemDescription.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total = unitPrice*qty;

        if (Integer.parseInt(txtStock.getText())<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
        }else{
            cartTMS.add(new CartTM(itemCode,description,qty,unitPrice,total));
            calcNetTotal();
        }




        tblCart.setItems(cartTMS);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCommitOnAction(ActionEvent actionEvent) {
    }

    public void btnAddCustomerFromOnAction(ActionEvent actionEvent) {

    }

    public static void setEmployeeName(String employeeName1) {
        empName = employeeName1;
    }

    private void l(){
        employeeName.setText(empName);
    }

    public void btnSearchCustmomerOnAction(ActionEvent actionEvent) {
        boolean found=false;
        for (Customer customer :customerService.getAll()){
            if(customer.getCity().equals(txtTelephoneNumber.getText())){
                txtCustomerId.setText(customer.getId());
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                found=true;
                return;

            }
        }
        if(!found){
            new Alert(Alert.AlertType.ERROR,"Customer not found!").show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        boolean found=false;
        for (Item item :items){
            if(item.getItemCode().equals(txtItemDescription1.getText())){
                txtItemDescription.setText(item.getDescription());
                txtStock.setText(item.getPackSize());
                txtUnitPrice.setText(item.getUnitPrice().toString());
                found=true;
                return;
            }
        }
        if(!found){
            new Alert(Alert.AlertType.ERROR,"Item not found!").show();
        }
    }


}
