package controller;

import com.jfoenix.controls.JFXTextField;
import dto.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import service.BoFactory;
import service.SuperBo;
import service.custom.UserService;
import util.ServiceType;

public class EmployeeFormController {

    public JFXTextField name;
    public JFXTextField phoneNumber;
    public JFXTextField email;
    public JFXTextField password;
    public JFXTextField nicNumber;
    public JFXTextField city;
    public ComboBox title;
    private final String userType = "Employee";

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
        User user=new User(
                name.getText(),
                email.getText(),
                password.getText(),
                phoneNumber.getText(),
                nicNumber.getText(),
                userType
        );
        UserService userService = BoFactory.getInstance().getServiceType(ServiceType.USER);
        if(userService.addUser(user)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"User registered successfully");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Something went wrong");
            alert.showAndWait();
        }
    }
}
