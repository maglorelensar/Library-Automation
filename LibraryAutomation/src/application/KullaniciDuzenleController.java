/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monster
 */
public class KullaniciDuzenleController implements Initializable {

    @FXML
    private TextField txtAd;
    @FXML
    private TextField txtSoyad;
    @FXML
    private TextField txtTelefon;
    @FXML
    private TextField txtEposta;
    @FXML
    private TextField txtKullaniciNo;
    @FXML
    private TextField txtAdres;
    
    private static ObservableList<Kullanici> kullaniciList;
    Kullanici secilenKullanici;

    public static void setKullaniciList(ObservableList<Kullanici> kullaniciList) {
        KullaniciDuzenleController.kullaniciList = kullaniciList;
    }

    void degerGetir(Kullanici kullanici) {
       secilenKullanici = kullanici;
       txtAd.setText(secilenKullanici.getAd());
       txtSoyad.setText(secilenKullanici.getSoyad());
       txtTelefon.setText(secilenKullanici.getTelefon());
       txtEposta.setText(secilenKullanici.getEposta());
       txtKullaniciNo.setText(String.valueOf(secilenKullanici.getKullaniciNo()));
       txtAdres.setText(secilenKullanici.getAdres());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDuzenleIptal(ActionEvent event) {
        kapat(event);
    }

    @FXML
    private void btnDuzenleKaydet(ActionEvent event) {
        kullaniciList.remove(secilenKullanici);
        String ad = txtAd.getText().trim();
        String soyad = txtSoyad.getText().trim();
        String telefon = txtTelefon.getText().trim();
        String eposta = txtEposta.getText().trim();
        int kullaniciNo = Integer.parseInt(txtKullaniciNo.getText().trim());
        String adres = txtAdres.getText().trim();
        
        Kullanici k = new Kullanici(kullaniciNo, ad, soyad, telefon, eposta, adres);
        kullaniciList.add(k);
        DosyaIslemleri.dosyayaYaz(kullaniciList, "kullanici");
        
        
        
        kapat(event);
    }

    
    private void kapat(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    
}
