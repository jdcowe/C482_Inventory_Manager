package ViewController;

import Model.Inventory;
import Model.Part;
import Model.Product;
import Model.Inhouse;
import Model.Outsourced;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {

    public static Inventory Inventory = new Inventory();
    public static ObservableList<Part> searchParts = FXCollections.observableArrayList();
    public static ObservableList<Product> searchProducts = FXCollections.observableArrayList();
    private static int selectedPartNum;
    private static int selectedProductNum;


    @FXML
    private TextField partSearchField;

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> colPartID;
    @FXML
    private TableColumn<Part, String > colPartName;
    @FXML
    private TableColumn<Part, Integer> colPartInventory;
    @FXML
    private TableColumn<Part, Double> colPartCost;


    @FXML
    private TextField productSearchField;

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> colProductID;
    @FXML
    private TableColumn<Product, String> colProductName;
    @FXML
    private TableColumn<Product, Integer> colProductInventory;
    @FXML
    private TableColumn<Product, Double> colProductPrice;

    public static Part selectedPart;
    public static Product selectedProduct;
    public static int partModifyIndex;

    @FXML
    void addPart(ActionEvent event) {
        sceneChange("AddPart.fxml", event);
    }

    public static Inventory getInventory() {
        return Inventory;
    }

    public static void setInventory(Inventory inv) {
        Inventory = inv;
    }

    public static int getPartModifyIndex() {
        return partModifyIndex;
    }

    @FXML
    void addProduct(ActionEvent event) {
        sceneChange("AddProduct.fxml", event);
    }

    @FXML
    void deletePart(ActionEvent event) {
        selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Part Deletion");
            alert.setHeaderText("Delete Part");
            alert.setContentText("Are you sure you want to proceed?");

            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
                partsTable.refresh();
            } else {

            }
        }
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Product Deletion");
            alert.setHeaderText("Delete Product");
            alert.setContentText("Are you sure you want to proceed?");

            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == ButtonType.OK) {
                Inventory.removeProduct(selectedProduct);
                productsTable.refresh();
            } else {

            }
        }
    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
    Platform.exit();
    System.exit(0);
    }

    @FXML
    void modifyPart(ActionEvent event) throws IOException {
        selectedPartNum = partsTable.getSelectionModel().getSelectedIndex();
        sceneChange("ModifyPart.fxml", event);

    }

    public static int getSelectedPart() {
        return selectedPartNum;
    }

    @FXML
    void modifyProduct(ActionEvent event) {
        selectedProductNum = productsTable.getSelectionModel().getSelectedIndex();
        sceneChange("ModifyProduct.fxml", event);
    }

    public static int getSelectedProduct() {
        return selectedProductNum;
    }

    @FXML
    void searchPartButtonPressed(ActionEvent event) {
        String searchValue = productSearchField.getText();
        ObservableList<Part> displayParts = FXCollections.observableArrayList();
        boolean partFound = false;
        searchParts.clear();
        searchParts = Inventory.getAllParts();
        int foundId = 0;

        try {
            int numericSearch = Integer.parseInt(searchValue);

            for (Part part : searchParts) {
                if (part.getPartID() == numericSearch) {
                    partFound = true;
                    foundId = numericSearch - 1;
                }
            }
        } catch(NumberFormatException exception) {
            for(Part part : searchParts) {
                if (part.getName().equals((searchValue))) {
                    partFound = true;
                    foundId = part.getPartID()-1;
                }
            }
        }

        if (partFound) {
            displayParts.add(Inventory.lookupPart(foundId));
            partsTable.setItems(displayParts);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error");
            alert.setHeaderText("Part not found");
            alert.setContentText("Error: Part Nor Found");
            alert.showAndWait();
        }



    }

    @FXML
    void searchProductButtonPressed(ActionEvent event) {
        String searchValue = productSearchField.getText();
        ObservableList<Product> displayProducts = FXCollections.observableArrayList();
        boolean productFound = false;
        searchProducts.clear();
        searchProducts = Inventory.getAllProducts();
        int foundId = 0;

        try {
            int numericSearch = Integer.parseInt(searchValue);

            for (Product product : searchProducts) {
                if (product.getProductID() == numericSearch) {
                    productFound = true;
                    foundId = numericSearch - 1;
                }
            }
        } catch(NumberFormatException exception) {
            for(Product product : searchProducts) {
                if (product.getName().equals((searchValue))) {
                    productFound = true;
                    foundId = product.getProductID()-1;
                }
            }
        }

        if (productFound) {
            displayProducts.add(Inventory.lookupProduct(foundId));
            productsTable.setItems(displayProducts);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error");
            alert.setHeaderText("Product not found");
            alert.setContentText("Error: Product Nor Found");
            alert.showAndWait();
        }

    }

    public void sceneChange(String fxml, ActionEvent event) {
        try {
            Parent addProductPage = FXMLLoader.load(getClass().getResource(fxml));

            Scene addProductScene = new Scene(addProductPage);
            Stage thisStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            thisStage.setScene(addProductScene);
            thisStage.show();
        } catch (IOException exception) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colPartID.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        colPartName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        colPartInventory.setCellValueFactory(cellData -> cellData.getValue().getInStockProperty().asObject());
        colPartCost.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());

        /*colPartID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        colPartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        colPartInventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        colPartCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));*/

        colProductID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        colProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colProductInventory.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inStock"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());
    }

}
