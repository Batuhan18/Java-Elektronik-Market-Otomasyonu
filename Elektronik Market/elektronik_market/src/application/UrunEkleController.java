package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import com.isteMySQL.util.elektronik_marketUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UrunEkleController {

	public UrunEkleController() {
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
    private ComboBox<String> combo_kategori;

    @FXML
    private TextField txt_urun_adý;

    @FXML
    private TextField txt_fiyat;

    @FXML
    private TextField txt_adet;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_anamenu;
    
    @FXML
    void combo_kategori_click(ActionEvent event) {

    }

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
    void btn_ekle_click(ActionEvent event) {
    	sql="insert into urunler(URUNAD,URUNGRUP, FIYAT, ADET) values(?,?,?,?)";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_urun_adý.getText().trim());
			sorguifadesi.setString(2,  combo_kategori.getSelectionModel().getSelectedItem().toString().trim());
			sorguifadesi.setString(3, txt_fiyat.getText().trim());
			sorguifadesi.setString(4, txt_adet.getText().trim());
		
			sorguifadesi.executeUpdate();
			
			Alert alert1=new Alert(AlertType.INFORMATION);
			alert1.setTitle("Bilgi Mesajý");
			alert1.setContentText("Ürün eklendi");
			alert1.showAndWait();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		System.out.println(e.getMessage().toString());
		}

    }

    @FXML
    void btn_sil_click(ActionEvent event) {

       	sql="delete from urunler where URUNAD=?, FIYAT=?, ADET=? ";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_urun_adý.getText().trim());
			sorguifadesi.setString(2, txt_fiyat.getText().trim());
			sorguifadesi.setString(3, txt_adet.getText().trim());
			sorguifadesi.executeUpdate();
			
			Alert alert1=new Alert(AlertType.INFORMATION);
			alert1.setTitle("Bilgi Mesajý");
			alert1.setContentText("Ürün silindi");
			alert1.showAndWait();
			
		} catch (Exception e) {
			// TODO: handle exception
		System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void txt_urun_adý_click(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	sql="select distinct URUNGRUPAD from ürün_grup";
        
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				String combodoldur=getirilen.getString("URUNGRUPAD");
				combo_kategori.getItems().add(combodoldur);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    	
       

    }
}
