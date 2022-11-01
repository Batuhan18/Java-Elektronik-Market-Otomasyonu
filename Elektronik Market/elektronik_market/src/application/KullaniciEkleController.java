package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.util.elektronik_marketUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KullaniciEkleController {

	public KullaniciEkleController() {
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
    private TextField txt_kulad;

    @FXML
    private TextField txt_sifre;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_anamenu;

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
    	sql="insert into kullanicilar(KULLANICI_ADI, SIFRE) values(?,?)";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(2, txt_sifre.getText().trim());
			sorguifadesi.setString(1, txt_kulad.getText().trim());
		
			sorguifadesi.executeUpdate();
			
			Alert alert1=new Alert(AlertType.INFORMATION);
			alert1.setTitle("Bilgi Mesajý");
			alert1.setContentText("Kullanýcý ad eklendi");
			alert1.showAndWait();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		System.out.println(e.getMessage().toString());
		}

    }

    @FXML
    void btn_sil_click(ActionEvent event) {
    	sql="delete from kullanicilar where KULLANICI_ADI=? and SIFRE=?";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(2, txt_sifre.getText().trim());
			sorguifadesi.setString(1, txt_kulad.getText().trim());
		
			sorguifadesi.executeUpdate();
			
			Alert alert1=new Alert(AlertType.INFORMATION);
			alert1.setTitle("Bilgi Mesajý");
			alert1.setContentText("Kullanýcý ad silindi");
			alert1.showAndWait();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		System.out.println(e.getMessage().toString());
		}

    }

    @FXML
    void initialize() {
      

    }
}
