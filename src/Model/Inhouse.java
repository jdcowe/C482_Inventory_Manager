package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inhouse extends Part {
    private final IntegerProperty machineID;

    public Inhouse() {
        this.machineID = new SimpleIntegerProperty();
    }

    public IntegerProperty getMachineIDProperty() {
        return this.machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }

    public int getMachineID() {
        return this.machineID.intValue();
    }
}
