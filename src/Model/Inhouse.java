package Model;

public class Inhouse extends Part {
    private int machineID;

    public Inhouse() {

    }

    public Inhouse(int partID, String name, double price, int inStock, int min, int max) {
        this.partID = partID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
