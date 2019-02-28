package application;

import javafx.beans.property.SimpleStringProperty;

public class SekmeBilgi {
	 private final SimpleStringProperty baslik;
     private final SimpleStringProperty adres;

      SekmeBilgi(String fName, String lName) {
         this.baslik = new SimpleStringProperty(fName);
         this.adres = new SimpleStringProperty(lName);
     }

     public String getFirstName() {
         return baslik.get();
     }

     public void setFirstName(String fName) {
         baslik.set(fName);
     }

     public String getLastName() {
        return adres.get();
     }

     public void setLastName(String fName) {
         adres.set(fName);
     }
 
 
}