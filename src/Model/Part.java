package Model;

public abstract class Part {
    protected int partID;
    protected String name;
    protected double price;
    protected int inStock;
    protected int min;
    protected int max;

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return name;
    }

    public void setPartName(String name) {
        this.name = name;
    }

    public double getPartPrice() {
        return price;
    }

    public void setPartPrice(double price) {
        this.price = price;
    }

    public int getPartInStock() {
        return inStock;
    }

    public void setPartInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getPartMin() {
        return min;
    }

    public void setPartMin(int min) {
        this.min = min;
    }

    public int getPartMax() {
        return max;
    }

    public void setPartMax(int max) {
        this.max = max;
    }
}
