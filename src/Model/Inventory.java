package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();

    public Inventory() {

    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public void addProduct(Product product) {
        allProducts.add(product);
    }

    public boolean removeProduct(Product product) {
        return allProducts.remove(product);
    }

    public Product lookupProduct(int productID) {
        for (int i = 0; i < allProducts.size()-1; i++) {
            if (allProducts.get(i).getProductID() == productID) {
                return allProducts.get(i);
            }
        }

        return new Product(0, null, null, 0, 0, 0, 0);
    }

    public void updateProduct(int productID, Product product) {
        Product targetProduct = lookupProduct(productID);
        targetProduct.addAssociatedParts(product.lookupAssociatedParts());
        targetProduct.setInStock(product.getInStock());
        targetProduct.setMax(product.getMax());
        targetProduct.setMin(product.getMin());
        targetProduct.setName(product.getName());
        targetProduct.setPrice(product.getPrice());
    }

    public void updatePart(int partID, Part part) {
        allParts.set(partID, part);
    }

    public void addPart(Part part) {
        allParts.add(part);
    }

    public boolean deletePart(Part part) {
        return allParts.remove(part);
    }

    public Part lookupPart(int partID) {

        return allParts.get(partID);

    }


}
