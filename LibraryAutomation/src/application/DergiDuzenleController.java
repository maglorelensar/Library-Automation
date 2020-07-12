/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monster
 */
public class DergiDuzenleController implements Initializable {

    @FXML
    private TextField txtBaskiYili;
    @FXML
    private TextField txtAd;
    @FXML
    private TextField txtSayfa;
    @FXML
    private TextField txtBarkod;
    @FXML
    private TextField txtDili;
    @FXML
    private TextField txtYayinEvi;
    @FXML
    private TextField txtAciklama;
    @FXML
    private TextField txtRafno;
    @FXML
    private TextField txtDetay;
    @FXML
    private ComboBox<String> cmbTur;
    
    private static ObservableList<Dergi> dergiList;
    
    private Dergi secilenDergi;
    
    ObservableList<String> tur = FXCollections.observableArrayList("Bilimsel", "Tarih", "Edebiyat");

    public static void setDergiList(ObservableList<Dergi> dergiList) {
        DergiDuzenleController.dergiList = dergiList;
    }
    
    
    
    public void degerGetir(Dergi d){
        secilenDergi =  d;
        txtBarkod.setText(String.valueOf(secilenDergi.getBarkod()));
        txtAd.setText(secilenDergi.getAd());
        txtSayfa.setText(String.valueOf(secilenDergi.getSayfa()));
        txtBaskiYili.setText(String.valueOf(secilenDergi.getBaskiyili()));
        txtDili.setText(secilenDergi.getDil());
        txtYayinEvi.setText(secilenDergi.getYayinevi());
        txtDetay.setText(secilenDergi.getDetay());
        txtAciklama.setText(secilenDergi.getAciklama());
        txtRafno.setText(secilenDergi.getRafno());
        
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cmbTur.setValue("Seçiniz.");
       cmbTur.setItems(tur);
    }    

    @FXML
    private void btnDuzenleKaydet(ActionEvent event) {
        int barkod = Integer.parseInt(txtBarkod.getText().trim());
        String ad = txtAd.getText().trim();
        int sayfa = Integer.parseInt(txtSayfa.getText().trim());
        int baskiYili = Integer.parseInt(txtBaskiYili.getText().trim());
        String dil = txtDili.getText().trim();
        String yayinEvi = txtYayinEvi.getText().trim();
        String detay = txtDetay.getText();
        String aciklama = txtAciklama.getText();
        String rafno = txtRafno.getText();
        String tur = cmbTur.getSelectionModel().getSelectedItem().toString();

        if (tur.equals("Bilimsel")) {
            dergiList.remove(secilenDergi);
            Dergi bilimselDergi = new BilimselDergi(barkod, ad, sayfa, baskiYili, dil, yayinEvi, detay, aciklama, rafno, tur);
            dergiList.add(bilimselDergi);
            DosyaIslemleri.dosyayaYaz(dergiList, "dergi");
        } else if (tur.equals("Tarih")) {
            dergiList.remove(secilenDergi);
            Dergi tarihDergi = new TarihDergi(barkod, ad, sayfa, baskiYili, dil, yayinEvi, detay, aciklama, rafno, tur);
            dergiList.add(tarihDergi);
            DosyaIslemleri.dosyayaYaz(dergiList, "dergi");
        } else if (tur.equals("Edebiyat")) {
            dergiList.remove(secilenDergi);
            Dergi edebiyatDergi = new EdebiyatDergi(barkod, ad, sayfa, baskiYili, dil, yayinEvi, detay, aciklama, rafno, tur);
            dergiList.add(edebiyatDergi);
            DosyaIslemleri.dosyayaYaz(dergiList, "dergi");
        }

        kapat(event);
    }

    @FXML
    private void btnDuzenleIptal(ActionEvent event) {
        kapat(event);
    }
    
    private void kapat(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    
}
