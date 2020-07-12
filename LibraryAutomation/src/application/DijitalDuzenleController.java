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
public class DijitalDuzenleController implements Initializable {

    @FXML
    private TextField txtBoyut;
    @FXML
    private TextField txtAd;
    @FXML
    private TextField txtBarkod;
    @FXML
    private TextField txtSuresi;
    @FXML
    private TextField txtYayinYili;
    @FXML
    private ComboBox<String> cmbTur;
    ObservableList<String> tur = FXCollections.observableArrayList("DVD", "Manyetik Teyp");
    
    private static ObservableList<Dijital> dList;

    public static void setdList(ObservableList<Dijital> dList) {
        DijitalDuzenleController.dList = dList;
    }
    
    
    
    Dijital secilenDijital;
    @FXML
    private TextField txtDil;
    
     void degerGetir(Dijital d) {
         secilenDijital = d;
         
         txtBarkod.setText(String.valueOf(secilenDijital.getBarkod()));
         txtAd.setText(secilenDijital.getAd());
         txtDil.setText(secilenDijital.getDil());
         txtBoyut.setText(secilenDijital.getBoyut());
         txtSuresi.setText(secilenDijital.getSure());
         txtYayinYili.setText(secilenDijital.getYayinyili());

    }
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cmbTur.setValue("DVD");
       cmbTur.setItems(tur);
    }    

    @FXML
    private void btnDuzenleKaydet(ActionEvent event) {
        int barkod = Integer.parseInt(txtBarkod.getText().trim());
        String ad = txtAd.getText().trim();
        String dil = txtDil.getText().trim();
        String boyut = txtBoyut.getText().trim();
        String sure = txtSuresi.getText().trim();
        String yayinYili = txtYayinYili.getText().trim();
        String tur = cmbTur.getSelectionModel().getSelectedItem();

        if (tur.equals("DVD")) {
            dList.remove(secilenDijital);
            Dijital dvd = new DVD(barkod, ad, dil, boyut, sure, yayinYili, tur);
            dList.add(dvd);
            DosyaIslemleri.dosyayaYaz(dList, "dijital");
        } else if (tur.equals("Manyetik Teyp")) {
            dList.remove(secilenDijital);
            Dijital manyetikTeyp = new ManyetikTeyp(barkod, ad, dil, boyut, sure, yayinYili, tur);
            dList.add(manyetikTeyp);
            DosyaIslemleri.dosyayaYaz(dList, "dijital");
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
