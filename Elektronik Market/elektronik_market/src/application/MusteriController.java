package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.util.elektronik_marketUtil;

import application.SatisGoruntuleController.satis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MusteriController {
	public MusteriController() {
		baglanti=elektronik_marketUtil.Baglan();
	}
	Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_anamenu;

    @FXML
    private Button btn_goruntule;
    
    @FXML
    private TableView<musteri> tbl_musteri; 

    @FXML
    private TableColumn<musteri, String> tbl_adsoyad;

    @FXML
    private TableColumn<musteri, String> tbl_telefon;

    @FXML
    private TableColumn<musteri, String> tbl_adres;

    @FXML
    void btn_anamenu_click(ActionEvent event) {
    	try {
    		Stage stage1= new Stage();
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("AraGecis.fxml"));
			Scene scene = new Scene(pane1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage1.setScene(scene);
			stage1.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    void btn_goruntule_click(ActionEvent event) {
       	sql="select * from musteriler";
    	ObservableList<musteri> deger1=FXCollections.observableArrayList();
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while(getirilen.next()) {
				deger1.add(new musteri(getirilen.getString("ADSOYAD"), getirilen.getString("TELEFON"), getirilen.getString("ADRES")));			
			}
			tbl_adsoyad.setCellValueFactory(new PropertyValueFactory<>("ad"));
			tbl_telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));  	
	    	tbl_adres.setCellValueFactory(new PropertyValueFactory<>("adres"));   	
	    	tbl_musteri.setItems(deger1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
  
    	

    }

    @FXML
    void initialize() {
       

    }
    public static class musteri{
    	String ad;
    	String telefon;
    	String adres;
    	public musteri() {
    		
    	}
    	musteri(String ad, String telefon, String adres){
    		this.ad=ad;
    		this.telefon=telefon;
    		this.adres=adres;
    		
    		
    	}
		public String getAd() {
			return ad;
		}
		public void setAd(String ad) {
			this.ad = ad;
		}
		public String getTelefon() {
			return telefon;
		}
		public void setTelefon(String telefon) {
			this.telefon = telefon;
		}
		public String getAdres() {
			return adres;
		}
		public void setAdres(String adres) {
			this.adres = adres;
		}
    }
}
