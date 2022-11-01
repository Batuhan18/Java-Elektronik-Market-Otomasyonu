package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.util.elektronik_marketUtil;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SatisController {
	public SatisController() {
		baglanti=elektronik_marketUtil.Baglan();
	}
	Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql, sql1, sql2;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_satis;

    @FXML
    private Button btn_anamenu;

    @FXML
    private TableView<urunlergetir> tbl_getir;

    @FXML
    private TableColumn<urunlergetir, String> tbl_kategori;

    @FXML
    private TableColumn<urunlergetir, String> tbl_urunad;

    @FXML
    private TableColumn<urunlergetir, Integer> tbl_adet;

    @FXML
    private TableColumn<urunlergetir, Integer> tbl_fiyat;

    @FXML
    private TextField txt_adres;

    @FXML
    private TextField txt_ad;
    
    @FXML
    private TextField txt_tarih;

    @FXML
    private TextField txt_telefon;
    
    @FXML
    private TextField txt_fiyat;

    @FXML
    private ComboBox<String> combo_kategori;
    

    @FXML
    private TextField txt_adet;

    @FXML
    private ComboBox<String> combo_urunadi;
	private Object kategori1;

    @FXML
    void combo_kategori_click(ActionEvent event) {
    	String kategori=combo_kategori.getSelectionModel().getSelectedItem();
    }
    @FXML
    void combo_urunadi_click(ActionEvent event) {
    	combo_urunadi.getSelectionModel().getSelectedItem();
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
    	
    	
    	
    	sql="insert into sepet(fiyat, urunadi, adet, kategori) values(?,?,?,?)";
    	
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_fiyat.getText().trim());
			
			sorguifadesi.setString(2,combo_urunadi.getSelectionModel().getSelectedItem().toString().trim());
			sorguifadesi.setString(3, txt_adet.getText().trim());
			sorguifadesi.setString(4, combo_kategori.getSelectionModel().getSelectedItem().toString().trim());
			sorguifadesi.executeUpdate();
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage().toString());
				
			}
       	sql1="select fiyat,urunadi,adet,kategori from sepet";
    	ObservableList<urunlergetir> deger1=FXCollections.observableArrayList();
    	try {
			sorguifadesi=baglanti.prepareStatement(sql1);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while(getirilen.next()) {
				deger1.add(new urunlergetir(getirilen.getInt("fiyat"), getirilen.getString("urunadi"), getirilen.getInt("adet"), getirilen.getString("kategori")));			
			}
			tbl_kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
	    	tbl_urunad.setCellValueFactory(new PropertyValueFactory<>("urunadi"));
	    	tbl_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
	    	tbl_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
	    	tbl_getir.setItems(deger1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
  
      	sql2="insert into satislar(TARIH, URUN_AD, ADET, fiyat) values(?,?,?,?)";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql2);
			sorguifadesi.setString(1, txt_tarih.getText().trim());
			sorguifadesi.setString(2,combo_urunadi.getSelectionModel().getSelectedItem().toString().trim());
			sorguifadesi.setString(3, txt_adet.getText().trim());
			sorguifadesi.setString(4, txt_fiyat.getText().trim());
			sorguifadesi.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}

    }

    @FXML
    void btn_satis_click(ActionEvent event) {
    	sql="insert into musteriler(ADSOYAD, TELEFON, ADRES) values(?,?,?)";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_ad.getText().trim());
			sorguifadesi.setString(2, txt_telefon.getText().trim());
			sorguifadesi.setString(3, txt_adres.getText().trim());
			sorguifadesi.executeUpdate();
			
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgi mesajý");
			alert.setContentText("Ürün satýlmýþtýr");
			alert.showAndWait();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    	sql1="select sum (fiyat) from sepet";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql1);
			ResultSet getirilen=sorguifadesi.executeQuery();
			 while(getirilen.next()){
				int a=getirilen.getInt("fiyat");
				String as=Integer.toString(a);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			}
    	
		sql2="delete from sepet ";
		try {
			sorguifadesi=baglanti.prepareStatement(sql2);
			sorguifadesi.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());		}
    	
    		
    	

    }

    @FXML
    void btn_toplam_click(ActionEvent event) {
    	
	
    }

    @FXML
    void initialize() {
    	sql="select distinct URUNGRUP from urunler";
        
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				String doldur=getirilen.getString("URUNGRUP");
				combo_kategori.getItems().add(doldur);
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    	
    	sql="select distinct URUNAD from urunler ";
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				String doldur=getirilen.getString("URUNAD");
				combo_urunadi.getItems().add(doldur);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    public static class urunlergetir{
    	String kategori;
    	String urunadi;
    	int adet;
    	int fiyat;
    	
    
    	
    	public urunlergetir() {
    		
    	}
    	urunlergetir(int fiyat, String urunadi, int adet,String kategori){
    		this.fiyat=fiyat;
    		this.urunadi=urunadi;
    		this.adet=adet;
    		this.kategori=kategori;
    	}
    	public String getKategori() {
			return kategori;
		}
		public void setKategori(String kategori) {
			this.kategori = kategori;
		}
		public String getUrunadi() {
			return urunadi;
		}
		public void setUrunadi(String urunadi) {
			this.urunadi = urunadi;
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
