package ViewController;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    @FXML    private TextField productIdField;
    @FXML    private TextField productNameField;
    @FXML    private TextField productInventoryField;
    @FXML    private TextField productPriceField;
    @FXML    private TextField productMaxField;
    @FXML    private TextField productMinField;

    @FXML    private TextField searchField;

    @FXML    private TableView<Part> tableVieldPartAdd;
    @FXML    private TableColumn<Part, Integer> partIdAdd;
    @FXML    private TableColumn<Part, String> partNameAdd;
    @FXML    private TableColumn<Part, Integer> invLevelAdd;
    @FXML    private TableColumn<Part, Double> priceUnitAdd;

    @FXML    private TableView<Part> tableViewPartDel;
    @FXML    private TableColumn<Part, Integer> partIdDel;
    @FXML    private TableColumn<Part, String> partNameDel;
    @FXML    private TableColumn<Part, Integer> invLevelDel;
    @FXML    private TableColumn<Part, Double> priceUnitDel;

    private static Inventory baseInventory = MainScreenController.getInventory();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private Product selectedProduct;
    private int productIndex = ViewController.MainScreenController.getSelectedProduct();

    @FXML
    void addProductPressed(ActionEvent event) {
        Part addedPart = tableVieldPartAdd.getSelectionModel().getSelectedItem();
        associatedParts.add(addedPart);
        tableViewPartDel.setItems(associatedParts);
        tableViewPartDel.refresh();
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) {
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

    @FXML
    void deletePartPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part");
        alert.setHeaderText("Confirm Delete Part");
        alert.setContentText("Are you sure you want to proceed?");

        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.get() == ButtonType.OK) {
            Part deletedPart = tableViewPartDel.getSelectionModel().getSelectedItem();
            associatedParts.remove(deletedPart);
            tableViewPartDel.refresh();
        } else {

        }
    }

    @FXML
    void saveProductPressed(ActionEvent event) {

        int productMax = Integer.parseInt(productMaxField.getText());
        int productMin = Integer.parseInt(productMinField.getText());

        if (productMax >= productMin) {
            Product newProduct = new Product();

            newProduct.setProductID(Integer.parseInt(productIdField.getText()));
            newProduct.setName(productNameField.getText());
            newProduct.setInStock(Integer.parseInt(productInventoryField.getText()));
            newProduct.setPrice(Double.parseDouble(productPriceField.getText()));
            newProduct.setMax(productMax);
            newProduct.setMin(productMin);

            newProduct.addAssociatedParts(associatedParts);
            baseInventory.updateProduct(productIndex, newProduct);

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
    void searchButtonPressed(ActionEvent event) {
        ObservableList<Part> searchParts= FXCollections.observableArrayList();

        String searchValue = searchField.getText();
        ObservableList<Part> displayProducts = FXCollections.observableArrayList();
        boolean productFound = false;
        searchParts.clear();
        searchParts = baseInventory.getAllParts();
        int foundId = 0;

        try {
            int numericSearch = Integer.parseInt(searchValue);

            for (Part part : searchParts) {
                if (part.getPartID() == numericSearch) {
                    productFound = true;
                    foundId = numericSearch;
                }
            }
        } catch(NumberFormatException exception) {
            for(Part part : searchParts) {
                if (part.getName().equals((searchValue))) {
                    productFound = true;
                    foundId = part.getPartID();
                }
            }
        }

        if (productFound) {
            displayProducts.add(baseInventory.lookupPart(foundId));
            tableVieldPartAdd.setItems(displayProducts);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error");
            alert.setHeaderText("Product not found");
            alert.setContentText("Error: Product Nor Found");
            alert.showAndWait();
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
        selectedProduct = baseInventory.lookupProduct(productIndex);
        associatedParts = selectedProduct.lookupAssociatedParts();

        productIdField.setText(Integer.toString(selectedProduct.getProductID()));
        productNameField.setText(selectedProduct.getName());
        productInventoryField.setText(Integer.toString(selectedProduct.getInStock()));
        productPriceField.setText(Double.toString(selectedProduct.getPrice()));
        productMaxField.setText(Integer.toString(selectedProduct.getMax()));
        productMinField.setText(Integer.toString(selectedProduct.getMin()));

        partIdAdd.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        partNameAdd.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        invLevelAdd.setCellValueFactory(cellData -> cellData.getValue().getInStockProperty().asObject());
        priceUnitAdd.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        tableVieldPartAdd.setItems(baseInventory.getAllParts());

        partIdDel.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        partNameDel.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        invLevelDel.setCellValueFactory(cellData -> cellData.getValue().getInStockProperty().asObject());
        priceUnitDel.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        tableViewPartDel.setItems(associatedParts);
    }

}