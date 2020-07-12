package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class KitapEkleController implements Initializable {

    @FXML
    private AnchorPane panel;

    @FXML
    private Button menuyedon;

    @FXML
    private Button kitapEkle;

    @FXML
    private TextField txtBarkod;

    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtSayfa;

    @FXML
    private TextField txtBaskiYili;

    @FXML
    private TextField txtDili;

    @FXML
    private TextField txtYayinEvi;

    @FXML
    private TextField txtYazar;

    @FXML
    private TextField txtDetay;

    @FXML
    private TextField txtAciklama;

    @FXML
    private ComboBox<String> cmbBolum = new ComboBox();

    @FXML
    private TextField txtRafNo;

    ObservableList<String> bolum = FXCollections.observableArrayList("Akademik", "Atlas", "Hikaye", "Roman", "Siir", "Sozluk");
    private ObservableList<Kitap> kitapList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg, ResourceBundle rb) {

        cmbBolum.setValue("Akademik");
        cmbBolum.setItems(bolum);

    }

    @FXML
    void kitapEkle(ActionEvent event) throws IOException {
        kitapList.remove(kitapList);
        int barkod = Integer.parseInt(txtBarkod.getText().trim());
        String ad = txtAd.getText().trim();
        int sayfa = Integer.parseInt(txtSayfa.getText().trim());
        int baskiYili = Integer.parseInt(txtBaskiYili.getText().trim());
        String dil = txtDili.getText().trim();
        String yayinEvi = txtYayinEvi.getText().trim();
        String yazar = txtYazar.getText().trim();
        String detay = txtDetay.getText();
        String aciklama = txtAciklama.getText();
        String rafno = txtRafNo.getText();
        String bolum = cmbBolum.getSelectionModel().getSelectedItem().toString();

        if (bolum.equals("Akademik")) {
            Akademik a = new Akademik(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.addAll(Kitap.dosyadanKitapGetir());
            kitapList.add(a);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Atlas")) {
            Atlas at = new Atlas(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.addAll(Kitap.dosyadanKitapGetir());
            kitapList.add(at);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Hikaye")) {
            Hikaye h = new Hikaye(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.addAll(Kitap.dosyadanKitapGetir());
            kitapList.add(h);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Roman")) {
            Roman r = new Roman(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.addAll(Kitap.dosyadanKitapGetir());
            kitapList.add(r);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else if (bolum.equals("Siir")) {
            Siir s = new Siir(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.addAll(Kitap.dosyadanKitapGetir());
            kitapList.add(s);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        } else {
            Sozluk so = new Sozluk(barkod, ad, sayfa, baskiYili, dil, yayinEvi, yazar, detay, aciklama, rafno, bolum);
            kitapList.addAll(Kitap.dosyadanKitapGetir());
            kitapList.add(so);
            DosyaIslemleri.dosyayaYaz(kitapList, "kitap");
        }
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);
    }

    @FXML
    void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);

    }

    public void setKitapList(ObservableList<Kitap> kitapList) {
        this.kitapList = kitapList;
    }

    @FXML
    void initialize() {

    }

}
