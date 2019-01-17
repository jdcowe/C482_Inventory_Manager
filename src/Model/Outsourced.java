package Model;

public class Outsourced extends Part {
    private String companyName;

    public Outsourced() {

    }

    public Outsourced(int partID, String name, double price, int inStock, int min, int max) {
        this.partID = partID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
