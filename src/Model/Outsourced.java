package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Outsourced extends Part {

    private final StringProperty companyName;

    public Outsourced () {
        // default
        this.companyName = new SimpleStringProperty();

    }
    public StringProperty getCompanyNameProperty() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getCompanyName() {
        return this.companyName.get();
    }
}
