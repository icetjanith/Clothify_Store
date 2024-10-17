package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.BoFactory;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;
import java.util.Map;

public class LoginFormController {
    public Label forgotPassword;
    public JFXTextField email;
    public JFXTextField password;
    private String userType;

    private void loadFxmlFile(ActionEvent actionEvent,String filePath) {
        try{
            Stage newStage = new Stage();
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(filePath))));
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.hide();
            newStage.setResizable(false);
            newStage.show();
            newStage.setOnCloseRequest(event -> {
                currentStage.show();
            });
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load employee form : "+e.getMessage());
        }
    }

    // samrilikoital5@gmail.com

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (authenticateUser(email.getText(), password.getText())) {
//            new Alert(Alert.AlertType.INFORMATION, "User Login Successful").show();

            String fxmlPath = userType.equals("Admin")
                    ? "/view/admindashboardform.fxml"
                    : "/view/employeedashboardform.fxml";
            PlaceOrderFormController.setEmployeeName("Amal");
            loadFxmlFile(actionEvent,fxmlPath);


        } else {
            new Alert(Alert.AlertType.INFORMATION, "Invalid Email or Password").show();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        loadFxmlFile(actionEvent,"/view/registerform.fxml");
    }


    private boolean authenticateUser(String email, String password) {
        UserService userService = BoFactory.getInstance().getServiceType(ServiceType.USER);
        Map<String, Object> map = userService.authenticateUser(email, password);
        userType = (String) map.get("userType");
        return (boolean) map.get("userExists");
    }
}
