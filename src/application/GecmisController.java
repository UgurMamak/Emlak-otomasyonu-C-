package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GecmisController  implements Initializable {
	
	@FXML TableView gecmisListe;
	@FXML TableView tableviewFavori;
	
	Database veri=new Database();
	 public ObservableList<GecmisBilgi> data =FXCollections.observableArrayList();
	public ObservableList<SekmeBilgi> data2 =FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Gecmislistele();
		SekmeListele();
		
		
	}
	
	void Gecmislistele()
	{
		gecmisListe.getColumns().clear();
		List list = new ArrayList();
		TableColumn firstNameCol = new TableColumn("Adresler");
        firstNameCol.setMinWidth(500);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Tarih");
        lastNameCol.setMinWidth(300);
        lastNameCol.setCellValueFactory( new PropertyValueFactory<>("lastName"));
        try {
			list=veri.GecmisListele();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        data =FXCollections.observableArrayList(list);
        gecmisListe.setItems(data);
        gecmisListe.getColumns().addAll(firstNameCol);
        gecmisListe.getColumns().addAll(lastNameCol);
	}
	
	
	void SekmeListele()
	{
		tableviewFavori.getColumns().clear();
		List list = new ArrayList();
		TableColumn firstNameCol = new TableColumn("Baþlýk");
        firstNameCol.setMinWidth(300);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Adres");
        lastNameCol.setMinWidth(500);
        lastNameCol.setCellValueFactory( new PropertyValueFactory<>("lastName"));
        try {
			list=veri.SekmeListele();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        data2 =FXCollections.observableArrayList(list);
        tableviewFavori.setItems(data2);
        tableviewFavori.getColumns().addAll(firstNameCol);
        tableviewFavori.getColumns().addAll(lastNameCol);
	}
	
	
	@FXML public void BtnFavoriSil(ActionEvent event) throws SQLException
	{
		
		int reply = JOptionPane.showConfirmDialog(null, "Kayýtlý Siteyi Silmek istediðinizden emin misiniz?", "Uyarý", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
	     String baslikBilgi;
	     int satirno=tableviewFavori.getSelectionModel().getSelectedIndex();
	     baslikBilgi=veri.FavoriSatirSecme(satirno+1);
	     veri.FavoriSil(baslikBilgi);
	     SekmeListele();
	     JOptionPane.showMessageDialog(null, "Silindi");	    
		}
		 else {JOptionPane.showMessageDialog(null, "iptal edildi"); }	
		
		
				
      
	}
	
	
	@FXML public void GecmisTemizle(ActionEvent event)
	{
	    int reply = JOptionPane.showConfirmDialog(null, "Geçmiþi temizlemek istediðinizden emin misiniz?", "Uyarý", JOptionPane.YES_NO_OPTION);
	    if (reply == JOptionPane.YES_OPTION) {
	    	veri.GecmisSilme();
			Gecmislistele();
			  JOptionPane.showMessageDialog(null, "Temizlendi");
	        }
	        else {JOptionPane.showMessageDialog(null, "iptal edildi"); }
	}
	
}