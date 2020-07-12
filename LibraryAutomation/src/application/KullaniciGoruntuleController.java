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

public class KullaniciGoruntuleController implements Initializable {

     ObservableList<Kullanici> kList = FXCollections.observableArrayList();
    
    @FXML
    private AnchorPane panel;

    @FXML
    private Button menuyedon;

    @FXML
    private TableView<Kullanici> kullaniciListele;

    @FXML
    private TableColumn<Kullanici, Integer> tKullaniciNo;

    @FXML
    private TableColumn<Kullanici, String> tAd;

    @FXML
    private TableColumn<Kullanici, String> tSoyad;

    @FXML
    private TableColumn<Kullanici, String> tTelefon;

    @FXML
    private TableColumn<Kullanici, String> tEposta;

    @FXML
    private TableColumn<Kullanici, String> tAdres;
    @FXML
    private Button kullaniciSil;
    @FXML
    private TextField txtAramaYap;

    @FXML
    void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);

    }

    private ObservableList<Kullanici> getKullaniciFromFile() {
        try {
            BufferedReader br = DosyaIslemleri.dosyaGetir("kullanici");
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                Kullanici k = new Kullanici(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5]);
                kList.add(k);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kList;
    }
    
    @FXML
    void kullaniciSil(ActionEvent event) {
        Kullanici seciliKullanici = kullaniciListele.getSelectionModel().getSelectedItem();
        kullaniciListele.getItems().remove(seciliKullanici);
        kList.remove(seciliKullanici);
        DosyaIslemleri.dosyayaYaz(kList, "kullanici");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tKullaniciNo.setCellValueFactory(new PropertyValueFactory<>("kullaniciNo"));
        tAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        tSoyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        tTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        tEposta.setCellValueFactory(new PropertyValueFactory<>("eposta"));
        tAdres.setCellValueFactory(new PropertyValueFactory<>("adres"));
        kullaniciListele.setItems(getKullaniciFromFile());
        
        //ARAMA
        FilteredList<Kullanici> filteredKullanici = new FilteredList<>(kList,b->true);
        
        txtAramaYap.textProperty().addListener(((observable,oldValue,newValue) -> {
            filteredKullanici.setPredicate(kullanici -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(String.valueOf(kullanici.getKullaniciNo()).toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else if(kullanici.getAd().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(kullanici.getSoyad().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                    return false;
                }
            });
            SortedList<Kullanici> sortedKullanici = new SortedList<>(filteredKullanici);
        
            sortedKullanici.comparatorProperty().bind(kullaniciListele.comparatorProperty());
        
            kullaniciListele.setItems(sortedKullanici);
        }));
        
        
    }


    @FXML
    private void kullaniciDuzenle(ActionEvent event) {
        if(kullaniciListele.getSelectionModel().getSelectedItem() == null) return;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciDuzenle.fxml"));
            Parent parent = loader.load();
            KullaniciDuzenleController kullaniciDuzenle = loader.<KullaniciDuzenleController>getController();
            Kullanici k = kullaniciListele.getSelectionModel().getSelectedItem();
            loader.setController(kullaniciDuzenle);
            kullaniciDuzenle.degerGetir(k);
            Stage duzenleStage = new Stage();
            Scene scene = new Scene(parent);
            duzenleStage.setTitle("Kitap Düzenle");
            kullaniciDuzenle.setKullaniciList(kList);
            
            duzenleStage.initModality(Modality.APPLICATION_MODAL);
            duzenleStage.setScene(scene);
            duzenleStage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
