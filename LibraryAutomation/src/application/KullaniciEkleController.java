package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class KullaniciEkleController implements Initializable {

    @FXML
    private AnchorPane panel;

    @FXML
    private Button kullaniciekle;

    @FXML
    private Button menuyedon;

    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtSoyad;

    @FXML
    private TextField txtTelefon;

    @FXML
    private TextField txtEposta;

    @FXML
    private TextField txtAdres;

    @FXML
    private TextField txtKullaniciNo;
    
    private ObservableList<Kullanici> kullaniciList = FXCollections.observableArrayList();

    @FXML
    void kullaniciEkle(ActionEvent event) throws IOException {
        kullaniciList.remove(kullaniciList);
        String ad = txtAd.getText().trim();
        String soyad = txtSoyad.getText().trim();
        String telefon = txtTelefon.getText().trim();
        String eposta = txtEposta.getText().trim();
        String adres = txtAdres.getText().trim();
        int kullanicino = Integer.parseInt(txtKullaniciNo.getText().trim());
        Kullanici k = new Kullanici(kullanicino, ad, soyad, telefon, eposta, adres);
        kullaniciList.addAll(Kullanici.dosyadanKullaniciGetir());
        kullaniciList.add(k);
        DosyaIslemleri.dosyayaYaz(kullaniciList, "kullanici");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);

    }

    @FXML
    void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);

    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
}
