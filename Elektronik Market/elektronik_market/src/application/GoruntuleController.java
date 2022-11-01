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

public class GoruntuleController {
	public GoruntuleController() {
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
    private TableView<urunler> tbl_urunler;

    @FXML
    private TableColumn<urunler, String> tbl_urungrup;

    @FXML
    private TableColumn<urunler, String> tbl_urunad;

    @FXML
    private TableColumn<urunler, Integer> tbl_fiyat;

    @FXML
    private TableColumn<urunler, Integer> tbl_adet;

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
     	sql="select * from urunler";
    	ObservableList<urunler> deger1=FXCollections.observableArrayList();
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while(getirilen.next()) {
				deger1.add(new urunler(getirilen.getString("URUNGRUP"), getirilen.getString("URUNAD"), getirilen.getInt("FIYAT"), getirilen.getInt("ADET")));			
			}
			tbl_urunad.setCellValueFactory(new PropertyValueFactory<>("urunad"));
			tbl_urungrup.setCellValueFactory(new PropertyValueFactory<>("urungrup"));  	
	    	tbl_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
	    	tbl_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
	    	tbl_urunler.setItems(deger1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}

    }

    @FXML
    void initialize() {
       
    }
    public static class urunler{
    	 String urungrup;
         String urunad;
         int fiyat;
         int adet;

    	public urunler() {
    		
    	}
    	urunler(String urungrup, String urunad, int fiyat, int adet){
    		this.urungrup=urungrup;
    		this.urunad=urunad;
    		this.fiyat=fiyat;
    		this.adet=adet;
    		
    	}
		public String getUrungrup() {
			return urungrup;
		}
		public void setUrungrup(String urungrup) {
			this.urungrup = urungrup;
		}
		public String getUrunad() {
			return urunad;
		}
		public void setUrunad(String urunad) {
			this.urunad = urunad;
		}
		public int getFiyat() {
			return fiyat;
		}
		public void setFiyat(int fiyat) {
			this.fiyat = fiyat;
		}
		public int getAdet() {
			return adet;
		}
		public void setAdet(int adet) {
			this.adet = adet;
		}
    }
}
