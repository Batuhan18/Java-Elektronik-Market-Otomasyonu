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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class GirisController {

	public GirisController() {
	baglanti=elektronik_marketUtil.Baglan();
}
    @FXML
    private ResourceBundle resources;
    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;
    @FXML
    private URL location;

    @FXML
    private TextField txt_kul_ad;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_giris;

    @FXML
    void btn_giris_click(ActionEvent event) {
    	sql="select * from kullanicilar where KULLANICI_ADI=? and SIFRE=?";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_kul_ad.getText().trim());
			sorguifadesi.setString(2, txt_password.getText().trim());
			
			ResultSet getirilen=sorguifadesi.executeQuery();
			
			if(!getirilen.next() ) {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("Hatalý giriþ");
	    		alert.setContentText("Kullanýcý adý veya þifrenizi kontrol edin!");
	    		alert.showAndWait();
	    	
			}else if(txt_kul_ad.getText().trim()==(" ") || txt_password.getText().trim()==(" ")) {
				Alert alert1=new Alert(AlertType.ERROR);
				alert1.setTitle("Hatalý giriþ");
				alert1.setContentText("Kullanýcý adý veya þifrenizi kontrol edin!");
				alert1.showAndWait();
				
			}
			
			else {
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
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			
		}
				

    }

    @FXML
    void initialize() {
     

    }
}
