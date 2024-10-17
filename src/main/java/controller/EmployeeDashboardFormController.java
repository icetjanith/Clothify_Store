package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class EmployeeDashboardFormController {


    private void loadFxmlFile(ActionEvent actionEvent, String filePath) {
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

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadFxmlFile(actionEvent, "/view/customerform.fxml");
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        loadFxmlFile(actionEvent, "/view/itemform.fxml");
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {
        loadFxmlFile(actionEvent, "/view/placeorder.fxml");
    }

    public void btnReturnOnAction(ActionEvent actionEvent) {
        loadFxmlFile(actionEvent, "/view/returnitems.fxml");
    }


    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        loadFxmlFile(actionEvent, "/view/supplierform.fxml");
    }
}
