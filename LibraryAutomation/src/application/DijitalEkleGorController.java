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
import application.Dijital;

/**
 * FXML Controller class
 *
 * @author Monster
 */
public class DijitalEkleGorController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private TextField txtBarkod;
    @FXML
    private TextField txtDijitalAdi;
    @FXML
    private TextField txtDijitalDili;
    @FXML
    private TextField txtDijitalBoyut;
    @FXML
    private TextField txtDijitalSure;
    @FXML
    private TextField txtYayinYili;
    @FXML
    private Button menuyedon;
    @FXML
    private TableView<Dijital> dijitalListesi;
    @FXML
    private TableColumn<Dijital, Integer> tblClBarkod;
    @FXML
    private TableColumn<Dijital, String> tblClAd;
    @FXML
    private TableColumn<Dijital, String> tblClDil;
    @FXML
    private TableColumn<Dijital, String> tblClBoyut;
    @FXML
    private TableColumn<Dijital, String> tblClSure;
    @FXML
    private TableColumn<Dijital, String> tblClYayinYili;
    @FXML
    private TableColumn<Dijital, String> tblClTur;
    @FXML
    private ComboBox<String> cmbTur;

    ObservableList<String> tur = FXCollections.observableArrayList("DVD", "Manyetik Teyp");

    private ObservableList<Dijital> dijitalList = FXCollections.observableArrayList();
    @FXML
    private TextField txtAramaYap;

    private ObservableList<Dijital> dosyadanDijitalGetir() {
        try {
            BufferedReader br = DosyaIslemleri.dosyaGetir("dijital");
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                if (s[6].equals("DVD")) {
                    Dijital dvd = new DVD(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5], s[6]);
                    dijitalList.add(dvd);
                } else if (s[6].equals("Manyetik Teyp")) {
                    Dijital manyetikTeyp = new ManyetikTeyp(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5], s[6]);
                    dijitalList.add(manyetikTeyp);
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dijitalList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Combobox Eklemesi Yapılıyor
        cmbTur.setValue("Seçiniz");
        cmbTur.setItems(tur);

        //Tableview Eklemesi Yapılıyor
        tblClBarkod.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        tblClAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        tblClDil.setCellValueFactory(new PropertyValueFactory<>("dil"));
        tblClBoyut.setCellValueFactory(new PropertyValueFactory<>("boyut"));
        tblClSure.setCellValueFactory(new PropertyValueFactory<>("sure"));
        tblClYayinYili.setCellValueFactory(new PropertyValueFactory<>("yayinyili"));
        tblClTur.setCellValueFactory(new PropertyValueFactory<>("tur"));
        dijitalListesi.setItems(dosyadanDijitalGetir());
        dijitalListesi.setItems(dijitalList);

        //ARAMA
        FilteredList<Dijital> filtiredDijital = new FilteredList<>(dijitalList, b -> true);

        txtAramaYap.textProperty().addListener(((observable, oldValue, newValue) -> {
            filtiredDijital.setPredicate(dijital -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(dijital.getBarkod()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (dijital.getAd().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
            SortedList<Dijital> sortedDijital = new SortedList<>(filtiredDijital);

            sortedDijital.comparatorProperty().bind(dijitalListesi.comparatorProperty());

            dijitalListesi.setItems(sortedDijital);
        }));

    }

    @FXML
    private void dijitalEkle(ActionEvent event) {
        int barkod = Integer.parseInt(txtBarkod.getText().trim());
        String ad = txtDijitalAdi.getText().trim();
        String dil = txtDijitalDili.getText().trim();
        String boyut = txtDijitalBoyut.getText().trim();
        String sure = txtDijitalSure.getText().trim();
        String yayinYili = txtYayinYili.getText().trim();
        String tur = cmbTur.getSelectionModel().getSelectedItem();

        if (tur.equals("DVD")) {
            Dijital dvd = new DVD(barkod, ad, dil, boyut, sure, yayinYili, tur);
            dijitalList.add(dvd);
            DosyaIslemleri.dosyayaYaz(dijitalList, "dijital");
        } else if (tur.equals("Manyetik Teyp")) {
            Dijital manyetikTeyp = new ManyetikTeyp(barkod, ad, dil, boyut, sure, yayinYili, tur);
            dijitalList.add(manyetikTeyp);
            DosyaIslemleri.dosyayaYaz(dijitalList, "dijital");
        }

    }

    @FXML
    private void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);
    }

    @FXML
    private void dijitalDuzenle(ActionEvent event) {
        if (dijitalListesi.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DijitalDuzenle.fxml"));
            Parent parent = loader.load();
            DijitalDuzenleController dijitalDuzenle = loader.<DijitalDuzenleController>getController();
            Dijital d = dijitalListesi.getSelectionModel().getSelectedItem();
            loader.setController(dijitalDuzenle);
            dijitalDuzenle.degerGetir(d);
            Stage duzenleStage = new Stage();
            Scene scene = new Scene(parent);
            duzenleStage.setTitle("Dijital Düzenle");
            dijitalDuzenle.setdList(dijitalList);

            duzenleStage.initModality(Modality.APPLICATION_MODAL);
            duzenleStage.setScene(scene);
            duzenleStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void dijitalSil(ActionEvent event) {
        Dijital seciliDijital = dijitalListesi.getSelectionModel().getSelectedItem();
        dijitalListesi.getItems().remove(seciliDijital);
        dijitalList.remove(seciliDijital);
        DosyaIslemleri.dosyayaYaz(dijitalList, "dijital");
    }

}
