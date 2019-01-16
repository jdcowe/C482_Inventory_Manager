package Model;

import java.util.ArrayList;

public class Product {

    private ArrayList<Part> associatedParts = new ArrayList<Part>();
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    public int getProductID() {
        return productID;
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

    public void addAssociatedPart(Part part) {

    }

    public boolean removeAssociatedPArt(int partNum) {
        return false;
    }

    public Part lookupAssociatedPart(int partNum) {
        return part;
    }


}
