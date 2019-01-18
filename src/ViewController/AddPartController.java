package ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Model.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddPartController implements Initializable {

    @FXML private RadioButton inHouseToggle;
    @FXML private RadioButton outsourcedToggle;
    @FXML private Label compNameLabel;
    @FXML private TextField partIdField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvField;
    @FXML private TextField partCostField;
    @FXML private TextField partMaxField;
    @FXML private TextField compNameField;
    @FXML private TextField partMinField;
    @FXML private Label machineIdLabel;
    @FXML private TextField machineID;

    private boolean isOutsourced = false;
    private int newPartID;
    private static Inventory baseInventory = MainScreenController.getInventory();



    @FXML
    public void radioButtonToggled(ActionEvent event) {
        if (inHouseToggle.isArmed()) {
            isOutsourced = false;
            compNameLabel.setVisible(false);
            compNameField.setVisible(false);
            machineID.setVisible(true);
            machineIdLabel.setVisible(true);
        } else if (outsourcedToggle.isArmed()) {
            isOutsourced = true;
            compNameField.setVisible(true);
            compNameLabel.setVisible(true);
            machineIdLabel.setVisible(false);
            machineID.setVisible(false);
        }
    }

    @FXML
    public void saveButtonPressed(ActionEvent event) {
        int productMax = Integer.parseInt(partMaxField.getText());
        int productMin = Integer.parseInt(partMinField.getText());

        if (productMax >= productMin) {

            if(isOutsourced) {
                Outsourced newPart = new Outsourced();
                newPart.setCompanyName(compNameField.getText());
                newPart.setName(partNameField.getText());
                newPart.setInStock(Integer.parseInt(partInvField.getText()));
                newPart.setPartID(newPartID);
                newPart.setMax(Integer.parseInt(partMaxField.getText()));
                newPart.setMin(Integer.parseInt(partMinField.getText()));
                newPart.setPrice(Double.parseDouble(partCostField.getText()));

                baseInventory.addPart(newPart);
                MainScreenController.setInventory(baseInventory);
            } else {
                Inhouse newPart = new Inhouse();
                newPart.setMachineID(Integer.parseInt(machineID.getText()));
                newPart.setName(partNameField.getText());
                System.out.println(newPart.getName());
                newPart.setInStock(Integer.parseInt(partInvField.getText()));
                System.out.println(newPart.getInStock());
                newPart.setPartID(newPartID);
                newPart.setMax(Integer.parseInt(partMaxField.getText()));
                newPart.setMin(Integer.parseInt(partMinField.getText()));
                newPart.setPrice(Double.parseDouble(partCostField.getText()));

                MainScreenController.Inventory.addPart(newPart);
            }


            returnToMain(event);

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Info Error");
            alert.setHeaderText("Min/Max Error");
            alert.setContentText("Error: Minimum Inventory must be less than Max Inventory");
            alert.showAndWait();

        }

    }
    @FXML
    public void cancelButtonPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Cancel Part Addition");
        alert.setContentText("Are you sure you want to proceed?");

        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.get() == ButtonType.OK) {
            returnToMain(event);
        } else {

        }
    }

    public void returnToMain(ActionEvent event) {
        try {
            Parent addProductPage = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

            Scene addProductScene = new Scene(addProductPage);
            Stage thisStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            thisStage.setScene(addProductScene);
            thisStage.show();
        } catch (IOException exception) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newPartID = MainScreenController.Inventory.getAllParts().size();
        partIdField.setText("Auto-Generated: " + newPartID);
    }

}
