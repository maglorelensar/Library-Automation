package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private Button kitapgoruntule;
    @FXML
    private Button kullaniciekle;
    @FXML
    private Button kullanicigoruntule;
    @FXML
    private Button emanetekle;
    @FXML
    private Button emanetgoruntule;
    @FXML
    private Button kitapekle1;
    @FXML
    private Button filmEkleGor;

    @FXML
    void kitapEkle(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("KitapEkle.fxml"));
        panel.getChildren().setAll(pane);

    }

    @FXML
    void kitapGoruntule(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("KitapGoruntule.fxml"));
        panel.getChildren().setAll(pane);

    }

    @FXML
    void kullaniciEkle(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("KullaniciEkle.fxml"));
        panel.getChildren().setAll(pane);

    }

    @FXML
    void kullaniciGoruntule(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("KullaniciGoruntule.fxml"));
        panel.getChildren().setAll(pane);

    }

    @FXML
    void emanetEkle(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("EmanetEkle.fxml"));
        panel.getChildren().setAll(pane);

    }

    @FXML
    void emanetGoruntule(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("EmanetGoruntule.fxml"));
        panel.getChildren().setAll(pane);

    }

    void initialize() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void filmEkleGor(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("FilmEkleGor.fxml"));
        panel.getChildren().setAll(pane);
    }

    @FXML
    private void DergiEkleGor(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("DergiEkleGor.fxml"));
        panel.getChildren().setAll(pane);
    }

    @FXML
    private void dijitalEkleGor(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("DijitalEkleGor.fxml"));
        panel.getChildren().setAll(pane);
    }

}
