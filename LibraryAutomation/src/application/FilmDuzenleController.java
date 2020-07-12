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
public class FilmDuzenleController implements Initializable {

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
    private static ObservableList<Film> filmList;
    
    Film secilenFilm;

    public static void setFilmList(ObservableList<Film> filmList) {
        FilmDuzenleController.filmList = filmList;
    }
    
    public void degerGetir(Film film) {
        secilenFilm = film;
        txtFilmAdi.setText(secilenFilm.getAd());
        txtVizyon.setText(secilenFilm.getVizyontarihi());
        txtTur.setText(secilenFilm.getTur());
        txtSuresi.setText(String.valueOf(secilenFilm.getSure()));
        txtYonetmen.setText(secilenFilm.getYonetmen());
        txtYapimYili.setText(secilenFilm.getYapimyili());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDuzenleKaydet(ActionEvent event) {
        filmList.remove(secilenFilm);
        String filmAdi = txtFilmAdi.getText().trim();
        String vizyon = txtVizyon.getText().trim();
        String tur = txtTur.getText().trim();
        int sure = Integer.parseInt(txtSuresi.getText().trim());
        String yonetmen = txtYonetmen.getText().trim();
        String yapimYili = txtYapimYili.getText().trim();
        Film f = new Film(filmAdi, vizyon, tur, sure, yonetmen, yapimYili);
        filmList.add(f);
        DosyaIslemleri.dosyayaYaz(filmList, "film");
                
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
