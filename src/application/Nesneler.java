package application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class Nesneler {
	
	public int counter=1;	
	public String num;
	public TextField txtAdres()
	{
		 int sayac=counter;	
		 num=Integer.toString(sayac);
		 TextField txt=new TextField();
		  txt.setId("txtAdres"+num);//name 		  
			AnchorPane.setRightAnchor(txt, 65.0);
			AnchorPane.setLeftAnchor(txt, 151.0);
			AnchorPane.setTopAnchor(txt, 15.0);
			
			txt.setStyle("-fx-border-color:black;"
					+ "-fx-border-style: solid;"
					+ "-fx-border-width:2px;"
					+ " -fx-background-radius:10px;"
					+ " -fx-border-radius:10px;"
					+ "-fx-font-weight:bold;");
			
			
	     return txt;
	}
	
	public Button btnGeri()
	{
		// txt.setMinSize(353, 25);//Boyutu vermek için
        Image resim = new Image(getClass().getResourceAsStream("/foto/geri24.png"));
        Button btn = new Button("", new ImageView(resim));	
		int sayac=counter; num=Integer.toString(sayac);				
	     btn.setId("btnGeri"+num);      	     	  
	     AnchorPane.setLeftAnchor(btn, 14.0); 
	     AnchorPane.setTopAnchor(btn, 14.0);
	     btn.setStyle("-fx-background-radius:10px;"
	     		+ "-fx-border-color:black;"
	     		+ "-fx-border-style: solid;"
	     		+ " -fx-border-width:2px;"
	     		+ "-fx-border-radius:10px;");
	     
	     
	     return btn;
	}
	
	public Button btnileri()
	{
		
		Image resim = new Image(getClass().getResourceAsStream("/foto/ileri24.png"));
        Button btn = new Button("", new ImageView(resim));	
		int sayac=counter;num=Integer.toString(sayac);
		btn.setId("btnileri"+num); 	 
		 AnchorPane.setLeftAnchor(btn, 60.0); 
	     AnchorPane.setTopAnchor(btn, 14.0);
	     btn.setStyle("-fx-background-radius:10px;"
		     		+ "-fx-border-color:black;"
		     		+ "-fx-border-style: solid;"
		     		+ " -fx-border-width:2px;"
		     		+ "-fx-border-radius:10px;");
	    return btn;
	}
	
	public Button btnYenile()
	{
		Image resim = new Image(getClass().getResourceAsStream("/foto/yenile24.png"));
        Button btn = new Button("", new ImageView(resim));
		int sayac=counter;num=Integer.toString(sayac);
		btn.setId("btnYenile"+num);
		 AnchorPane.setLeftAnchor(btn, 106.0); 
	     AnchorPane.setTopAnchor(btn, 14.0);
	     btn.setStyle("-fx-background-radius:10px;"
		     		+ "-fx-border-color:black;"
		     		+ "-fx-border-style: solid;"
		     		+ " -fx-border-width:2px;"
		     		+ "-fx-border-radius:10px;");
	    return btn;
	}
	
	/*public AnchorPane AncPane()
	{
		AnchorPane pane=new AnchorPane();
		 pane.setMinSize(600,541);
		return pane;
	}*/
	
	
    
	/*
	public Button btnEkle()
	{
		int sayac=counter;num=Integer.toString(sayac);
		Button btn=new Button("Ekle");btn.setId("btnEkle"+num); 
	    btn.setTranslateX(555);//konum verir
	    btn.setTranslateY(14);//konum verir
	    return btn;
	}
    */
    
    public Button btnGit()
    {
    	Image resim = new Image(getClass().getResourceAsStream("/foto/git24.png"));
        Button btn = new Button("", new ImageView(resim));
    	int sayac=counter;num=Integer.toString(sayac);
    	
    	btn.setId("btnGit"+num);
    	AnchorPane.setRightAnchor(btn, 20.0);
    	AnchorPane.setTopAnchor(btn, 15.0); 
    	btn.setStyle("-fx-background-radius:10px;"
	     		+ "-fx-border-color:black;"
	     		+ "-fx-border-style: solid;"
	     		+ " -fx-border-width:2px;"
	     		+ "-fx-border-radius:10px;");
    	 
        return btn;
    }
    
    public WebView ViewSayfa()
    {
    	int sayac=counter;num=Integer.toString(sayac);
    	  WebView  View=new WebView();View.setId("ViewSayfa"+num);
    
    	  AnchorPane.setRightAnchor(View, 0.0);
      	AnchorPane.setLeftAnchor(View, 0.0); 
      	AnchorPane.setBottomAnchor(View, 20.0);
    	AnchorPane.setTopAnchor(View, 55.0);
    	
    	     return View;	
    }    
    
  

	public Tab Sekme() {
		int sayac=counter;num=Integer.toString(sayac);
		Tab yenisekme = new Tab("Yeni Sekme");
		yenisekme.setId("tbsekme"+num);
		return yenisekme;
	}
  


    
    
}


