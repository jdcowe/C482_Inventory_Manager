package Model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Product {

    private  ObservableList<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    public Product() {}

    public Product (int productID, ObservableList<Part> associatedParts, String name, double price, int inStock, int min, int max) {
        this.productID = productID;
        this.associatedParts = associatedParts;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    public int getProductID() {
        return productID;
    }

    public ObservableList<Part> lookupAssociatedParts() {
        return associatedParts;
    }

    public void addAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean removeAssociatedPArt(int partNum) {
        return false;
    }


}
