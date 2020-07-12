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

public class KitapGoruntuleController implements Initializable {

    @FXML
    private AnchorPane panel;

    @FXML
    private TableView<Kitap> kitapListele;

    @FXML
    private TableColumn<Kitap, Integer> tBarkod;

    @FXML
    private TableColumn<Kitap, String> tAd;

    @FXML
    private TableColumn<Kitap, Integer> tSayfa;

    @FXML
    private TableColumn<Kitap, Integer> tBasYil;

    @FXML
    private TableColumn<Kitap, String> tDil;

    @FXML
    private TableColumn<Kitap, String> tYayinEvi;

    @FXML
    private TableColumn<Kitap, String> rYazar;

    @FXML
    private TableColumn<Kitap, String> tDetay;

    @FXML
    private TableColumn<Kitap, String> tAciklama;

    @FXML
    private TableColumn<Kitap, String> tRafNo;
    @FXML
    private TableColumn<Kitap, String> tBolum;


    @FXML
    private Button kitapSil;

    @FXML
    private Button menuyedon;

    ObservableList<Kitap> kList = FXCollections.observableArrayList();
    @FXML
    private TextField txtAramaYap;
    

    @FXML
    void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);

    }

    private ObservableList<Kitap> getKitapFromFile() {
        try {
            BufferedReader br = DosyaIslemleri.dosyaGetir("kitap");
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                if (s[10].equals("Akademik")) {
                    Akademik a = new Akademik(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    kList.add(a);
                } else if (s[10].equals("Atlas")) {
                    Atlas at = new Atlas(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    kList.add(at);
                } else if (s[10].equals("Hikaye")) {
                    Hikaye h = new Hikaye(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    kList.add(h);
                } else if (s[10].equals("Roman")) {
                    Roman r = new Roman(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    kList.add(r);
                } else if (s[10].equals("Siir")) {
                    Siir si = new Siir(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    kList.add(si);
                } else {
                    Sozluk so = new Sozluk(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    kList.add(so);
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kList;
    }

    @FXML
    void kitapSil(ActionEvent event) {

        Kitap seciliKitap = kitapListele.getSelectionModel().getSelectedItem();
        kitapListele.getItems().remove(seciliKitap);
        kList.remove(seciliKitap);
        DosyaIslemleri.dosyayaYaz(kList, "kitap");

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tBarkod.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        tAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        tSayfa.setCellValueFactory(new PropertyValueFactory<>("sayfa"));
        tBasYil.setCellValueFactory(new PropertyValueFactory<>("baskiyili"));
        tDil.setCellValueFactory(new PropertyValueFactory<>("dil"));
        tYayinEvi.setCellValueFactory(new PropertyValueFactory<>("yayinevi"));
        rYazar.setCellValueFactory(new PropertyValueFactory<>("yazar"));
        tDetay.setCellValueFactory(new PropertyValueFactory<>("detay"));
        tAciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
        tRafNo.setCellValueFactory(new PropertyValueFactory<>("rafno"));
        tBolum.setCellValueFactory(new PropertyValueFactory<>("bolum"));
        kitapListele.setItems(getKitapFromFile());
        
        //ARAMA
        FilteredList<Kitap> filteredKitap = new FilteredList<>(kList,b->true);
        
        txtAramaYap.textProperty().addListener(((observable,oldValue,newValue) -> {
            filteredKitap.setPredicate(kitap -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(String.valueOf(kitap.getBarkod()).toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else if(kitap.getAd().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(kitap.getRafno().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                    return false;
                }
            });
            SortedList<Kitap> sortedKitap = new SortedList<>(filteredKitap);
        
            sortedKitap.comparatorProperty().bind(kitapListele.comparatorProperty());
        
            kitapListele.setItems(sortedKitap);
        }));
        
        
        
       

    }

    @FXML
    private void kitapDuzenle(ActionEvent event) {
        if (kitapListele.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KitapDuzenle.fxml"));
            Parent parent = loader.load();
            KitapDuzenleController kitapDuzenle = loader.<KitapDuzenleController>getController();
            Kitap d = kitapListele.getSelectionModel().getSelectedItem();
            loader.setController(kitapDuzenle);
            kitapDuzenle.degerGetir(d);
            Stage duzenleStage = new Stage();
            Scene scene = new Scene(parent);
            duzenleStage.setTitle("Kitap Düzenle");
            kitapDuzenle.setKitapList(kList);

            duzenleStage.initModality(Modality.APPLICATION_MODAL);
            duzenleStage.setScene(scene);
            duzenleStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
