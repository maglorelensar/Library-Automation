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
public class KitapDuzenleController implements Initializable {

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
    private TextField txtYazar;
    @FXML
    private TextField txtRafno;
    @FXML
    private TextField txtDetay;
    @FXML
    private TextField txtAciklama;
    @FXML
    private ComboBox<String> cmbBolum;
    
    private static ObservableList<Kitap> kitapList;
    private Kitap secilenKitap;
    
    ObservableList<String> bolum = FXCollections.observableArrayList("Akademik", "Atlas", "Hikaye", "Roman", "Siir", "Sozluk");

    public static void setKitapList(ObservableList<Kitap> kitapList) {
        KitapDuzenleController.kitapList = kitapList;
    }
    
    public void degerGetir(Kitap kitap){
        secilenKitap = kitap;
        txtBarkod.setText(String.valueOf(secilenKitap.getBarkod()));
        txtAd.setText(secilenKitap.getAd());
        txtSayfa.setText(String.valueOf(secilenKitap.getSayfa()));
        txtBaskiYili.setText(String.valueOf(secilenKitap.getBaskiyili()));
        txtDili.setText(secilenKitap.getDil());
        txtYayinEvi.setText(secilenKitap.getYayinevi());
        txtYazar.setText(secilenKitap.getYazar());
        txtRafno.setText(secilenKitap.getRafno());
        txtDetay.setText(secilenKitap.getDetay());
        txtAciklama.setText(secilenKitap.getAciklama());
    }
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbBolum.setValue("Akademik");
        cmbBolum.setItems(bolum);
    }    

    @FXML
    private void btnDuzenleKaydet(ActionEvent event) {
        int barkod = Integer.parseInt(txtBarkod.getText().trim());
        String ad = txtAd.getText().trim();
        int sayfa = Integer.parseInt(txtSayfa.getText().trim());
        int baskiYili = Integer.parseInt(txtBaskiYili.getText().trim());
        String dil = txtDili.getText().trim();
        String yayinEvi = txtYayinEvi.getText().trim();
        String yazar = txtYazar.getText().trim();
        String detay = txtDetay.getText();
        String aciklama = txtAciklama.getText();
        String rafno = txtRafno.getText();
        String bolum = cmbBolum.getSelectionModel().getSelectedItem().toString();

        if (bolum.equals("Akademik")) {
            kitapList.remove(secilenKitap);
            Akademik a = new Akademik(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.add(a);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Atlas")) {
            kitapList.remove(secilenKitap);
            Atlas at = new Atlas(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.add(at);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Hikaye")) {
            kitapList.remove(secilenKitap);
            Hikaye h = new Hikaye(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.add(h);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Roman")) {
            kitapList.remove(secilenKitap);
            Roman r = new Roman(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.add(r);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Siir")) {
            kitapList.remove(secilenKitap);
            Siir s = new Siir(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.add(s);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else {
            kitapList.remove(secilenKitap);
            Sozluk so = new Sozluk(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.add(so);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
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
