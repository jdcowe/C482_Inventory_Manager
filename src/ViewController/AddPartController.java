package ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import Model.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
            //baseInventory.addPart(newPart);
            //MainScreenController.setInventory(baseInventory);
        }


        /*try {
            Parent addProductPage = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

            Scene addProductScene = new Scene(addProductPage);
            Stage thisStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            thisStage.setScene(addProductScene);
            thisStage.show();
        } catch (IOException exception) {

        }*/

        returnToMain(event);

    }
    @FXML
    public void cancelButtonPressed(ActionEvent event) {
        returnToMain(event);
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
