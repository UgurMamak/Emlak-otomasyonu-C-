package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.JOptionPane;
import org.w3c.dom.Text;
import com.sun.prism.impl.ps.BaseShaderContext.State;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TarayiciController  implements Initializable {
	// TANIMLAMALAR****
	@FXML private TabPane AnaSekme;
	@FXML AnchorPane AnaPanel;
	@FXML WebView ViewSayfa0;
	//@FXML Menu FavoriMenu;
	@FXML TableView tableview;
	 public ObservableList<SekmeBilgi> data =FXCollections.observableArrayList();
	//nesneler class'ýndan nesne oluþturabilmek için tanýmladým.
	Button btngit,btnileri,btngeri,btnyenile;
	
	
	WebView viewsayfa;
	TextField txtadres;
	Tab yenisekme;
	@FXML MenuButton menubuton;
	@FXML Label lblZaman;
	
	Tab sekmeolustur;	
	Nesneler nesne=new Nesneler();
	Database veri=new Database();
	
	int index(){return AnaSekme.getSelectionModel().getSelectedIndex();} 	
	String ViewIsim(){return "ViewSayfa"+Integer.toString(index());}
	String TxtIsim(){return "txtAdres"+Integer.toString(index());}
	String TbIsim(){return AnaSekme.getSelectionModel().getSelectedItem().getText();} //seçili satýrýn baþlýðýný alýr.

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		   Date simdikiZaman = new Date();	            
	       DateFormat df = new SimpleDateFormat("yyyy/MM/dd:E=HH:mm");
	       lblZaman.setText((String) df.format(simdikiZaman));

		veri.Baglan();	
		veri.SekmeOlustur();
		veri.GecmisOlustur();
		sekmeolustur=new Tab("+");//  yeni sekme oluþturmak için oluþturulan sekme
		EventHandler<Event> event =   new EventHandler<Event>() {  public void handle(Event e)  { NesnelerOlustur();}};
		sekmeolustur.setOnSelectionChanged(event); 
		AnaSekme.getTabs().add(sekmeolustur);//yenisekme oluþturan sekmeyi ekledik.
		try {
		FavoriListele();
		} catch (SQLException e1) {
			//JOptionPane.showMessageDialog(null,"ekle fonk hatasý","",JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
				
	}
		
	public void NesnelerOlustur()
	{
	
		if(sekmeolustur.isSelected())
		{
	    	      	   
	    	   btngit=nesne.btnGit();
	    	//   btnekle=nesne.btnEkle();
	    	   btngeri=nesne.btnGeri();
	    	   btnileri=nesne.btnileri();
	    	   btnyenile=nesne.btnYenile();
	    	   viewsayfa=nesne.ViewSayfa();
	    	   txtadres=nesne.txtAdres();
	    	   yenisekme=nesne.Sekme();
	    	 
	    	   
	    	   //butonatýkladýðýnda gerçekleþecek olaylarý vermek için
	    	   btngit.setOnAction(this::BtnGit);
	    	   
	    	 /*  btnekle.setOnAction(event -> {
				try {
					BtnEkle(event);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});*/
	    	   btngeri.setOnAction(this::BtnGeri);
	    	   btnileri.setOnAction(this::Btnileri);
	    	   btnyenile.setOnAction(this::BtnYenile);
	    	   txtadres.setOnAction(this::TxtEnter);
	    	   
	    	   yenisekme.setContent(new AnchorPane(btngeri,txtadres,btnileri,btnyenile,btngit,viewsayfa));	    	   
	    	   nesne.counter++;
		 
		     AnaSekme.getTabs().add( AnaSekme.getTabs().size() - 1, yenisekme); 
		     AnaSekme.getSelectionModel().select(AnaSekme.getTabs().size() - 2); 
	       }		
	}

	@FXML public void BtnGit(ActionEvent event)
	   { 	
		Git();
	   }
	@FXML public void Btnileri(ActionEvent event)
	{	
		String yol;
		 WebEngine engine=new WebEngine();				
		Set<Node> nodes = AnaPanel.lookupAll(".web-view");
		for( Node node: nodes) {
			   if(node.getId().equals(ViewIsim()))
			   {
				   WebView sayfa=(WebView)node;
				   engine=sayfa.getEngine();
				   engine.executeScript("history.forward()");	   
			   }  
		}		
	
		 Set<Node> nodes2 = AnaPanel.lookupAll(".text-field");
		   for( Node node: nodes2) {
			   if(node.getId().equals(TxtIsim()))
			   {
				   TextField adres=(TextField) node;	
				   adres.setText(engine.getLocation());		    					   
				   engine.locationProperty().addListener(
							(observableValue, oldValue, newValue) -> {
								adres.setText(newValue);
							}); 
			   }			   
		   }
		

		
	}	
	@FXML public void BtnGeri(ActionEvent event) 
	{
		WebEngine engine=new WebEngine();	
		Set<Node> nodes = AnaPanel.lookupAll(".web-view");
		for( Node node: nodes) {
			   if(node.getId().equals(ViewIsim()))
			   {
				   WebView sayfa=(WebView)node;
				   engine=sayfa.getEngine();
				   engine.executeScript("history.back()");						  						   
			   }  
		}
		
		Set<Node> nodes2 = AnaPanel.lookupAll(".text-field");
		   for( Node node: nodes2) {
			   if(node.getId().equals(TxtIsim()))
			   {
				   TextField adres=(TextField) node;	
				   adres.setText(engine.getLocation());		    					   
				   engine.locationProperty().addListener(
							(observableValue, oldValue, newValue) -> {
								adres.setText(newValue);
							}); 
			   }			   
		   }		
	}
	@FXML public void BtnYenile(ActionEvent event)
	{
		
		WebEngine engine=new WebEngine();
		int index = AnaSekme.getSelectionModel().getSelectedIndex();//bulunduðumuz sekmenin index numarasýný alýr.	
		String isim="ViewSayfa"+Integer.toString(index);		
		Set<Node> nodes = AnaPanel.lookupAll(".web-view");
		for( Node node: nodes) {
			   if(node.getId().equals(isim))
			   {
				   WebView sayfa=(WebView)node;
				   engine=sayfa.getEngine();
				   engine.reload();  				  
			   }  
		}
	}
	
	
	/*
	@FXML public void BtnEkle(ActionEvent event) throws SQLException
	{
       // EKLEME ÝÞLEMÝ
		
		String baslik=AnaSekme.getSelectionModel().getSelectedItem().getText();
	     JOptionPane.showMessageDialog(null,baslik,"",JOptionPane.INFORMATION_MESSAGE); 
		
		String url;
		String baslik1;
		WebEngine engine=new WebEngine();
		int index = AnaSekme.getSelectionModel().getSelectedIndex();
		String	isim1="txtAdres"+Integer.toString(index);
		 Set<Node> nodes = AnaPanel.lookupAll(".text-field");
		   for( Node node: nodes) {
			   if(node.getId().equals(isim1))
			   {
				   TextField adres=(TextField) node;	
				   url=adres.getText();
				   baslik1=AnaSekme.getSelectionModel().getSelectedItem().getText();		
				  veri.SekmeEkleme(baslik1, url);	
				   
				  
				   MenuItem item=new MenuItem(baslik);		
					item.setOnAction(new EventHandler<ActionEvent>() {
						 public void handle(ActionEvent event) { }
				    });
					 JOptionPane.showMessageDialog(null,"yeni sekme eklendi. dýþarýsý" ,"",JOptionPane.INFORMATION_MESSAGE);   
			  }
		   }
		   	
	}
	*/
	
	public void AyarlarAc(ActionEvent event) {
		try {
			 

          Stage stage = new Stage();
          AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("GecmisTasarim.fxml"));
          stage.setScene(new Scene(root));
         
          stage.setTitle("Geçmiþ");
          stage.initModality(Modality.WINDOW_MODAL);
          stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
          Image icon = new Image(getClass().getResourceAsStream("/foto/Logo.png"));
          stage.getIcons().add(icon);
		stage.setTitle("Bilir");
          stage.show();
          

      } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
      }
		
	}
	
	void  gecmisgonder(String adres)
	 {
		Date simdikiZaman = new Date();
       System.out.println(simdikiZaman.toString());       
       DateFormat df = new SimpleDateFormat("yyyy/MM/dd:E=HH:mm");
       String t=(String) df.format(simdikiZaman);    
	     veri.GecmisEkleme(adres,t);		 
	 }
	
	@FXML public void btnFavoriEkle(ActionEvent event) throws SQLException
	{		
		WebEngine engine=new WebEngine();			
		Set<Node> nodes = AnaPanel.lookupAll(".text-field");
		   for( Node node: nodes) {
			   if(node.getId().equals(TxtIsim()))
			   {
				   TextField adres=(TextField) node;	
				 String  url=adres.getText();						
				  veri.SekmeEkleme(TbIsim(), url);	 
					 JOptionPane.showMessageDialog(null,"yeni sekme eklendi" ,"",JOptionPane.INFORMATION_MESSAGE); 
					 FavoriListele();			  
			   }
		   }	
	}		
	
	//SEKME TABLOSUNDAKÝ FAVORÝ SÝTELERÝ MENUBUTONA EKLEMEK ÝÇÝN KULLANDIM.
	public void FavoriListele() throws SQLException
	{	
		menubuton.getItems().clear();
		for (int i = 0; i < veri.SekmeBaslik().size(); i++)
		{
			MenuItem item=new MenuItem();	
			item.setText(veri.SekmeBaslik().get(i).toString());			
			menubuton.getItems().add(item);
			item.setOnAction(new EventHandler<ActionEvent>()
			{
				 public void handle(ActionEvent event)
				 {
					 String url=item.getText().toString();	                     
					 try 
					 {
						  String a=veri.SekmeAdresBul(url);	
						   WebEngine engine=new WebEngine();
						 Set<Node> nodes2 = AnaPanel.lookupAll(".web-view");
							for( Node node: nodes2) {
								   if(node.getId().equals(ViewIsim()))
								   {
									   WebView sayfa=(WebView)node;
									   engine=sayfa.getEngine();
									   engine.load(a);
								   }  
							}							
							 Set<Node> nodes = AnaPanel.lookupAll(".text-field");
							   for( Node node: nodes) {
								   if(node.getId().equals(TxtIsim()))
								   {
									   TextField adres=(TextField) node;		  
									   adres.setText(a);
									   gecmisgonder(a);
								   }
							   }							   
							   engine.titleProperty().addListener(new ChangeListener<String>()			
						       {		
						           public void changed(ObservableValue<? extends String> ov,				
						           final String oldvalue, final String newvalue)			
						           {
						           	AnaSekme.getSelectionModel().getSelectedItem().setText (newvalue);				               
						           }
						       });
					 } 
					 catch (SQLException e) {e.printStackTrace();}				   
			     }				 
		    });			
		}	
	}


	public void Git()
	{
		WebEngine engine=new WebEngine();		
		String url="";	  
		Set<Node> nodes2 = AnaPanel.lookupAll(".web-view");
		for( Node node: nodes2) {
			   if(node.getId().equals(ViewIsim()))
			   {
				   WebView sayfa=(WebView)node;
				   engine=sayfa.getEngine();				  
			   }  
		}		
	   Set<Node> nodes = AnaPanel.lookupAll(".text-field");
	   for( Node node: nodes) {
		   if(node.getId().equals(TxtIsim()))
		   {
			   TextField adres=(TextField) node;		  
			   engine.load(adres.getText());
			   url=adres.getText(); 
			   engine.titleProperty().addListener(new ChangeListener<String>()			
		       {		
		           public void changed(ObservableValue<? extends String> ov,				
		           final String oldvalue, final String newvalue)			
		           {AnaSekme.getSelectionModel().getSelectedItem().setText (newvalue);	}
		       });
		   }
	   }	  
	   gecmisgonder(url);//  txtadrese girilen verinin fonksiyona göndermek için kulllandým.	
	}


	@FXML//Enter olayý
	  public void TxtEnter(ActionEvent ae){
	     Git();

	  }
}



