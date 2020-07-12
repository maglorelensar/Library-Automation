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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monster
 */
public class FilmEkleGorController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private TextField txtFilmAdi;
    @FXML
    private TextField txtVizyon;
    @FXML
    private TextField txtTur;
    @FXML
    private TextField txtSuresi;
    @FXML
    private TextField txtYonetmen;
    @FXML
    private TextField txtYapimYili;
    @FXML
    private Button menuyedon;
    @FXML
    private TableView<Film> filmListesi;
    @FXML
    private TableColumn<Film, String> tblClFilmAdi;
    @FXML
    private TableColumn<Film, String> tblClTarihi;
    @FXML
    private TableColumn<Film, String> tblClTuru;
    @FXML
    private TableColumn<Film, Integer> tblClSuresi;
    @FXML
    private TableColumn<Film, String> tblClYonetmen;
    @FXML
    private TableColumn<Film, String> tblClYapimYili;

    private ObservableList<Film> filmList = FXCollections.observableArrayList();
    @FXML
    private TextField txtAramaYap;
    

    private ObservableList<Film> dosyadanFilmGetir() {
        try {
            BufferedReader br = DosyaIslemleri.dosyaGetir("film");
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                Film f = new Film(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4], s[5]);
                filmList.add(f);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return filmList;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblClFilmAdi.setCellValueFactory(new PropertyValueFactory("ad"));
        tblClTarihi.setCellValueFactory(new PropertyValueFactory<>("vizyontarihi"));
        tblClTuru.setCellValueFactory(new PropertyValueFactory<>("tur"));
        tblClSuresi.setCellValueFactory(new PropertyValueFactory<>("sure"));
        tblClYonetmen.setCellValueFactory(new PropertyValueFactory<>("yonetmen"));
        tblClYapimYili.setCellValueFactory(new PropertyValueFactory<>("yapimyili"));
        filmListesi.setItems(dosyadanFilmGetir());
        filmListesi.setItems(filmList);
        
        
        
        //ARAMA
        FilteredList<Film> filteredFilm = new FilteredList<>(filmList,b->true);
        
        txtAramaYap.textProperty().addListener(((observable,oldValue,newValue) -> {
            filteredFilm.setPredicate(film -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(film.getAd().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else if(film.getTur().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                    return false;
                }
            });
            SortedList<Film> sortedFilm = new SortedList<>(filteredFilm);
        
            sortedFilm.comparatorProperty().bind(filmListesi.comparatorProperty());
        
            filmListesi.setItems(sortedFilm);
        }));
        
        
        

    }

    @FXML
    private void filmEkle(ActionEvent event) {
        String ad = txtFilmAdi.getText().trim();
        String vizyonTarihi = txtVizyon.getText().trim();
        String tur = txtTur.getText().trim();
        int sure = Integer.parseInt(txtSuresi.getText());
        String yonetmen = txtYonetmen.getText().trim();
        String yapimYili = txtYapimYili.getText().trim();
        Film film = new Film(ad, vizyonTarihi, tur, sure, yonetmen, yapimYili);
        filmList.add(film);
        DosyaIslemleri.dosyayaYaz(filmList, "film");
    }

    @FXML
    private void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);
    }

    @FXML
    private void filmDuzenle(ActionEvent event) {
        if (filmListesi.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FilmDuzenle.fxml"));
            Parent parent = loader.load();
            FilmDuzenleController filmDuzenle = loader.<FilmDuzenleController>getController();
            Film f = filmListesi.getSelectionModel().getSelectedItem();
            loader.setController(filmDuzenle);
            filmDuzenle.degerGetir(f);
            Stage duzenleStage = new Stage();
            Scene scene = new Scene(parent);
            duzenleStage.setTitle("Kitap Düzenle");
            filmDuzenle.setFilmList(filmList);

            duzenleStage.initModality(Modality.APPLICATION_MODAL);
            duzenleStage.setScene(scene);
            duzenleStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void filmSil(ActionEvent event) {

        Film seciliFilm = filmListesi.getSelectionModel().getSelectedItem();
        filmListesi.getItems().remove(seciliFilm);
        filmList.remove(seciliFilm);
        DosyaIslemleri.dosyayaYaz(filmList, "film");
    }

}
