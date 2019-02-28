package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Database {
	
	 public Connection Baglan(){
	        try {
	            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");          
	            Connection conn=DriverManager.getConnection("jdbc:derby:ConnectingCreatingJavaDB;create=true");		    
	            return conn;}
	            catch(Exception e4)
	            {  
	            }
	        return null;
	    }
	
	        public void SekmeOlustur(){
	            
	                try {
	                	Statement stmt=Baglan().createStatement();
	                    stmt.executeUpdate("CREATE TABLE tblsekme (sekmebaslik VARCHAR(100),sekmeadres VARCHAR(150))");
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	                 
	            }
	       
	        public void GecmisOlustur(){
	            
                try {
                	Statement stmt=Baglan().createStatement();
                    stmt.executeUpdate("CREATE TABLE tblgecmis (gecmisadres VARCHAR(150),tarih VARCHAR(20))");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                 
            }
  
	        public void SekmeEkleme(String baslik, String adres)
	        {	        	
	            try {	
	                    PreparedStatement psInsert=Baglan().prepareStatement("insert into tblsekme values (?,?)");
	                    psInsert.setString(1,baslik);
	                    psInsert.setString(2,adres);	
	                    psInsert.executeUpdate();
	                    JOptionPane.showMessageDialog(null, "Favorilere eklendi","",JOptionPane.INFORMATION_MESSAGE);  
	                } 
	            catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        public void GecmisEkleme(String adres, String tarih)
	        {	        	
	            try {	
	                    PreparedStatement psInsert=Baglan().prepareStatement("insert into tblgecmis values (?,?)");
	                    psInsert.setString(1,adres);
	                    psInsert.setString(2,tarih);	
	                    psInsert.executeUpdate();
	                } 
	            catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        
	     //GEÇMÝÞÝ TABLEVÝEWDE LÝSTELEMEK ÝÇÝN KULLANDIM.
	        public List GecmisListele() throws SQLException{ //geçmiþi listelemek için
		        List list = new ArrayList();
	        	 Statement stmt=Baglan().createStatement();
		                ResultSet rs;	             
		                    rs = stmt.executeQuery("select * from tblgecmis");
		                    int num=0;              
		                    while(rs.next())
		                    {	   	                    	
		                    	 list.add(new GecmisBilgi(rs.getString(1),rs.getString(2)));	                    	
		                    }	  	                	          
		                    rs.close();
							return list;            	            	
		            }  
	        
	        
	        //SIK KULLANILANLARI MENUBUTONA EKLEMEK ÝÇÝN KULLANDIK.
	        public List SekmeBaslik() throws SQLException{ 
	        List list = new ArrayList();	       
        	 Statement stmt=Baglan().createStatement();
	                ResultSet rs;	             
	                    rs = stmt.executeQuery("select * from tblsekme");
	                    int num=0;              
	                    while(rs.next()) {list.add(rs.getString(1));}	  	                	          
	                    rs.close();
						return list;            	            	
	            }  
	        
	      //SEKMEDEKÝ TÝTLE'A GÖRE URL'YÝ BULUR.
	        public String SekmeAdresBul(String url) throws SQLException{ 
		       String adres="";		       	          	            
	        	 Statement stmt=Baglan().createStatement();
		                ResultSet rs;	             
		                    rs = stmt.executeQuery("select * from tblsekme");
		                    int num=0;              
		                    while(rs.next())
		                    {	
		                    	if(rs.getString(1).toString().equals(url)){adres=rs.getString(2);}
		                    }	  	                	          
		                    rs.close();
							return adres;            	            	
		            }       
	        //GEÇMÝÞÝ SÝLMEK ÝÇÝN 
	        public void GecmisSilme()
	        {	        	
	            try {		            	
	            	Statement stmt = Baglan().createStatement(); 
	            	String sorgu="Delete from tblgecmis";
	            	stmt.executeUpdate(sorgu); 	            		            	
	                } 
	            catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	       
	        public List SekmeListele() throws SQLException{ //geçmiþi listelemek için
		        List list = new ArrayList();
	        	 Statement stmt=Baglan().createStatement();
		                ResultSet rs;	             
		                    rs = stmt.executeQuery("select * from tblsekme");
		                    int num=0;              
		                    while(rs.next())
		                    {	   	                    	
		                    	 list.add(new SekmeBilgi(rs.getString(1),rs.getString(2)));	                    	
		                    }	  	                	          
		                    rs.close();
							return list;            	            	
		            } 
	      
	       
	        //SEÇÝLÝ SATIRIN ÝÇÝNDEKÝ VERÝYÝ ÇEKEBÝLMEK ÝÇÝN KULLANDIK.
	        public String FavoriSatirSecme(int satirno) throws SQLException
	        {
	        	int sayac=0;//gelen id ye kdar döngüyü döndürme için	        
	        	String bilgi="";//basliði geri döndürmek için	        	
	        	Statement stmt=Baglan().createStatement();
                ResultSet rs;	             
                    rs = stmt.executeQuery("select * from tblsekme");                    
                   do
                    {                   	                   
                    	rs.next();
                    	bilgi=rs.getString(1).toString(); 
                    	sayac++;
                    }while(satirno!=sayac);	          
                    rs.close();       	
	        	return bilgi;
	        }
	 
	        public void FavoriSil(String baslik)
	        {
	        	try {	
	        			        		
	        		PreparedStatement psDelete=Baglan().prepareStatement("Delete from tblsekme where sekmebaslik='"+baslik+"'");                              	
                    psDelete.executeUpdate();	
	                } 
	            catch (Exception e) {
	                    e.printStackTrace();
	                }
	        	
	        }
	        
}
