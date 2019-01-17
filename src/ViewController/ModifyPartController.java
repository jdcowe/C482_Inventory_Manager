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
import ViewController.MainScreenController.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ModifyPartController implements Initializable {

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

    private boolean isOutsourced;
    private Part selectedPart;
    private static Inventory baseInventory = ViewController.MainScreenController.getInventory();
    private int partIndex = ViewController.MainScreenController.getSelectedPart();
    private int targetPartID;


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

        boolean isError = false;
        isOutsourced = !inHouseToggle.isArmed();
        try {
            if (isOutsourced) {
                Outsourced newPart = new Outsourced();
                newPart.setCompanyName(compNameField.getText());
                newPart.setName(partNameField.getText());
                newPart.setInStock(Integer.parseInt(partInvField.getText()));
                newPart.setPartID(Integer.parseInt(partIdField.getText()));
                newPart.setMax(Integer.parseInt(partMaxField.getText()));
                newPart.setMin(Integer.parseInt(partMinField.getText()));
                newPart.setPrice(Double.parseDouble(partCostField.getText()));

                baseInventory.updatePart(partIndex, newPart);
            } else {
                Inhouse newPart = new Inhouse();
                newPart.setMachineID(Integer.parseInt(machineID.getText()));
                newPart.setName(partNameField.getText());
                System.out.println(newPart.getName());
                newPart.setInStock(Integer.parseInt(partInvField.getText()));
                System.out.println(newPart.getInStock());
                newPart.setPartID(Integer.parseInt(partIdField.getText()));
                newPart.setMax(Integer.parseInt(partMaxField.getText()));
                newPart.setMin(Integer.parseInt(partMinField.getText()));
                newPart.setPrice(Double.parseDouble(partCostField.getText()));

                baseInventory.updatePart(partIndex, newPart);
            }
        } catch (NumberFormatException error) {
            isError = true;
        }
        if (!isError) {
            returnToMain(event);
        }

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
        selectedPart = baseInventory.lookupPart(partIndex);
        System.out.println(partIndex);

        partIdField.setText(Integer.toString(selectedPart.getPartID()));
        partNameField.setText(selectedPart.getName());
        partInvField.setText(Integer.toString(selectedPart.getInStock()));
        partCostField.setText(Double.toString(selectedPart.getPrice()));
        partMaxField.setText(Integer.toString(selectedPart.getMax()));
        partMinField.setText(Integer.toString(selectedPart.getMin()));

        if (selectedPart instanceof Outsourced) {
            isOutsourced = true;
            inHouseToggle.setSelected(false);
            outsourcedToggle.setSelected(true);
            compNameField.setVisible(true);
            compNameLabel.setVisible(true);
            machineID.setVisible(false);
            machineIdLabel.setVisible(false);
            compNameField.setText(((Outsourced) selectedPart).getCompanyName());
        } else if (selectedPart instanceof Inhouse) {
            isOutsourced = false;
            inHouseToggle.setSelected(true);
            outsourcedToggle.setSelected(false);
            compNameField.setVisible(false);
            compNameLabel.setVisible(false);
            machineID.setVisible(true);
            machineIdLabel.setVisible(true);
            machineID.setText(Integer.toString(((Inhouse) selectedPart).getMachineID()));
        }
    }

}
