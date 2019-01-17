package Model;

import javafx.beans.property.*;

public abstract class Part {
    private final IntegerProperty partID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    public Part() {
        this.partID = new SimpleIntegerProperty();
        this.name  = new SimpleStringProperty();
        this.price  = new SimpleDoubleProperty();
        this.inStock  = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }

    public IntegerProperty getPartIDProperty() {
        return this.partID;
    }
    public IntegerProperty getInStockProperty() {
        return this.inStock;
    }
    public IntegerProperty getMinProperty() {
        return this.min;
    }

    public IntegerProperty getMaxProperty() {
        return this.max;
    }

    public DoubleProperty getPriceProperty() {
        return this.price;
    }

    public StringProperty getNameProperty() {
        return this.name;
    }

    public  void setName(String name) {
        this.name.set(name);
    }
    public  String getName() {
        return name.get();
    }
    public  void setPrice(double price) {
        this.price.set(price);
    }
    public  double getPrice() {
        return price.get();
    }
    public  void setInStock(int inStock) {
        this.inStock.set(inStock);

    }
    public  int getInStock() {
        return inStock.get();
    }
    public  void setMin(int min) {
        this.min.set(min);
    }
    public  int getMin() {
        return min.get();
    }
    public  void setMax(int max) {
        this.max.set(max);
    }
    public  int getMax() {
        return max.get();
    }
    public  void setPartID(int partID) {
        this.partID.set(partID);
    }
    public  int getPartID() {
        return partID.get();
    }
}
