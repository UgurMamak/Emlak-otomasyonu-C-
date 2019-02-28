package application;

import javafx.beans.property.SimpleStringProperty;

public class GecmisBilgi {
	private final SimpleStringProperty gecmisadres;
    private final SimpleStringProperty tarih;

     GecmisBilgi(String fName, String lName) {
        this.gecmisadres = new SimpleStringProperty(fName);
        this.tarih = new SimpleStringProperty(lName);
    }

    public String getFirstName() {
        return gecmisadres.get();
    }

    public void setFirstName(String fName) {
        gecmisadres.set(fName);
    }

    public String getLastName() {
       return tarih.get();
    }

    public void setLastName(String fName) {
        tarih.set(fName);
    }

}
