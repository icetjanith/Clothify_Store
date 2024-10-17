package controller;

import com.jfoenix.controls.JFXTextField;
import dto.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import service.BoFactory;
import service.SuperBo;
import service.custom.UserService;
import util.ServiceType;

public class RegisterFormController {

    public JFXTextField name;
    public JFXTextField userId;
    public JFXTextField phoneNumber;
    public JFXTextField nicNumber;
    public JFXTextField email;
    public JFXTextField password;
    private final String userType="Admin";

    public void btnRegisterOnAction(ActionEvent actionEvent) {

        if(userId.getText().equals("12345")){
            User user = new User(
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


        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid UserId").show();
        }

    }
}
