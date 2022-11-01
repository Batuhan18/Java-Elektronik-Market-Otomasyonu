package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.util.elektronik_marketUtil;

import application.SatisController.urunlergetir;
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

public class SatisGoruntuleController {
	public SatisGoruntuleController() {
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
    private TableView<satis> tbl_satislar;

    @FXML
    private TableColumn<satis, String> tbl_urunad;

    @FXML
    private TableColumn<satis, String> tbl_tarih;

    @FXML
    private TableColumn<satis, Integer> tbl_adet;

    @FXML
    private TableColumn<satis, Integer> tbl_fiyat;

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
    	sql="select * from satislar";
    	ObservableList<satis> deger1=FXCollections.observableArrayList();
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while(getirilen.next()) {
				deger1.add(new satis(getirilen.getString("URUN_AD"), getirilen.getString("TARIH"), getirilen.getInt("ADET"), getirilen.getInt("fiyat")));			
			}
			tbl_urunad.setCellValueFactory(new PropertyValueFactory<>("urunad"));
			tbl_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));  	
	    	tbl_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
	    	tbl_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
	    	tbl_satislar.setItems(deger1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
  

    }

    @FXML
    void initialize() {
      
    }
    
    public static class satis{
    	String urunad;
    	String tarih;
    	int adet;
    	int fiyat;
    	
    	public satis() {
    		
        	
        }
    	satis(String urunad, String tarih, int adet, int fiyat){
    		this.urunad=urunad;
    		this.tarih=tarih;
    		this.adet=adet;
    		this.fiyat=fiyat;
        	
        }
        public String getUrunad() {
			return urunad;
		}
		public void setUrunad(String urunad) {
			this.urunad = urunad;
		}
		public String getTarih() {
			return tarih;
		}
		public void setTarih(String tarih) {
			this.tarih = tarih;
		}
		public int getAdet() {
			return adet;
		}
		public void setAdet(int adet) {
			this.adet = adet;
		}
		public int getFiyat() {
			return fiyat;
		}
		public void setFiyat(int fiyat) {
			this.fiyat = fiyat;
		}
		
    }
    
}



