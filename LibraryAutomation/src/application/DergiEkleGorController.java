/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.Dergi;

/**
 * FXML Controller class
 *
 * @author Monster
 */
public class DergiEkleGorController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private TextField txtBarkod;
    @FXML
    private TextField txtDergiAd;
    @FXML
    private TextField txtDergiSayfa;
    @FXML
    private TextField txtDergiBaskiyil;
    @FXML
    private TextField txtDergiDil;
    @FXML
    private TextField txtYayinEvi;
    @FXML
    private TextField txtDetay;
    @FXML
    private TextField txtAciklama;
    @FXML
    private TextField txtRafNo;
    @FXML
    private ComboBox<String> cmbTur;
    @FXML
    private Button menuyedon;
    @FXML
    private TableView<Dergi> dergiListesi;
    @FXML
    private TableColumn<Dergi, Integer> tblClBarkod;
    @FXML
    private TableColumn<Dergi, String> tblClAd;
    @FXML
    private TableColumn<Dergi, Integer> tblClSayfa;
    @FXML
    private TableColumn<Dergi, Integer> tblClBaskiYili;
    @FXML
    private TableColumn<Dergi, String> tblClDil;
    @FXML
    private TableColumn<Dergi, String> tblClYayinEvi;
    @FXML
    private TableColumn<Dergi, String> tblClDetay;
    @FXML
    private TableColumn<Dergi, String> tblClAciklama;
    @FXML
    private TableColumn<Dergi, String> tblClRafNo;
    @FXML
    private TableColumn<Dergi, String> tblClTur;

    ObservableList<String> tur = FXCollections.observableArrayList("Bilimsel", "Tarih", "Edebiyat");
    private ObservableList<Dergi> dergiList = FXCollections.observableArrayList();
    @FXML
    private TextField txtAramaYap;

    private ObservableList<Dergi> dosyadanDergiGetir() {
        try {
            BufferedReader br =DosyaIslemleri.dosyaGetir("dergi");
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                if (s[9].equals("Bilimsel")) {
                    Dergi bilimselDergi = new BilimselDergi(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9]);
                    dergiList.add(bilimselDergi);
                } else if (s[9].equals("Tarih")) {
                    Dergi tarihDergi = new TarihDergi(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9]);
                    dergiList.add(tarihDergi);

                } else if (s[9].equals("Edebiyat")) {
                    Dergi edebiyatDergi = new EdebiyatDergi(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9]);
                    dergiList.add(edebiyatDergi);

                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dergiList;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setValue("Seçiniz");
        cmbTur.setItems(tur);

        //Tablo Doldurulması
        tblClBarkod.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        tblClAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        tblClSayfa.setCellValueFactory(new PropertyValueFactory<>("sayfa"));
        tblClBaskiYili.setCellValueFactory(new PropertyValueFactory<>("baskiyili"));
        tblClDil.setCellValueFactory(new PropertyValueFactory<>("dil"));
        tblClYayinEvi.setCellValueFactory(new PropertyValueFactory<>("yayinevi"));
        tblClDetay.setCellValueFactory(new PropertyValueFactory<>("detay"));
        tblClAciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
        tblClRafNo.setCellValueFactory(new PropertyValueFactory<>("rafno"));
        tblClTur.setCellValueFactory(new PropertyValueFactory<>("tur"));
        dergiListesi.setItems(dosyadanDergiGetir());
        dergiListesi.setItems(dergiList);

        //ARAMA
        FilteredList<Dergi> filteredDergi = new FilteredList<>(dergiList, b -> true);

        txtAramaYap.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredDergi.setPredicate(dergi -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(dergi.getBarkod()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (dergi.getAd().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (dergi.getRafno().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });

            SortedList<Dergi> sortedDergi = new SortedList<>(filteredDergi);

            sortedDergi.comparatorProperty().bind(dergiListesi.comparatorProperty());

            dergiListesi.setItems(sortedDergi);
        }));

    }

    @FXML
    private void dergiEkle(ActionEvent event) {
        int barkod = Integer.parseInt(txtBarkod.getText().trim());
        String ad = txtDergiAd.getText().trim();
        int sayfa = Integer.parseInt(txtDergiSayfa.getText().trim());
        int baskiYili = Integer.parseInt(txtDergiBaskiyil.getText().trim());
        String dil = txtDergiDil.getText().trim();
        String yayinEvi = txtYayinEvi.getText().trim();
        String detay = txtDetay.getText();
        String aciklama = txtAciklama.getText();
        String rafno = txtRafNo.getText();
        String tur = cmbTur.getSelectionModel().getSelectedItem().toString();

        if (tur.equals("Bilimsel")) {
            Dergi bilimselDergi = new BilimselDergi(barkod, ad, sayfa, baskiYili, dil, yayinEvi, detay, aciklama, rafno, tur);
            dergiList.add(bilimselDergi);
            DosyaIslemleri.dosyayaYaz(dergiList, "dergi");
        } else if (tur.equals("Tarih")) {
            Dergi tarihDergi = new TarihDergi(barkod, ad, sayfa, baskiYili, dil, yayinEvi, detay, aciklama, rafno, tur);
            dergiList.add(tarihDergi);
            DosyaIslemleri.dosyayaYaz(dergiList, "dergi");
        } else if (tur.equals("Edebiyat")) {
            Dergi edebiyatDergi = new EdebiyatDergi(barkod, ad, sayfa, baskiYili, dil, yayinEvi, detay, aciklama, rafno, tur);
            dergiList.add(edebiyatDergi);
            DosyaIslemleri.dosyayaYaz(dergiList, "dergi");
        }

    }

    @FXML
    private void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);
    }

    @FXML
    private void dergiDuzenle(ActionEvent event) {
        if (dergiListesi.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DergiDuzenle.fxml"));
            Parent parent = loader.load();
            DergiDuzenleController dergiDuzenle = loader.<DergiDuzenleController>getController();
            Dergi d = dergiListesi.getSelectionModel().getSelectedItem();
            loader.setController(dergiDuzenle);
            dergiDuzenle.degerGetir(d);
            Stage duzenleStage = new Stage();
            Scene scene = new Scene(parent);
            duzenleStage.setTitle("Dergi Düzenle");
            dergiDuzenle.setDergiList(dergiList);

            duzenleStage.initModality(Modality.APPLICATION_MODAL);
            duzenleStage.setScene(scene);
            duzenleStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void dergiSil(ActionEvent event) {
        Dergi seciliDergi = dergiListesi.getSelectionModel().getSelectedItem();
        dergiListesi.getItems().remove(seciliDergi);
        dergiList.remove(seciliDergi);
        DosyaIslemleri.dosyayaYaz(dergiList, "dergi");
    }

}
