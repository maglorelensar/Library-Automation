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

public class EmanetGoruntuleController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private Button menuyedon;
    @FXML
    private TableColumn<Emanet, Integer> tblClEmanetno;
    @FXML
    private TableColumn<Emanet, String> tblClVermetarihi;
    @FXML
    private TableColumn<Emanet, String> tblClAlmatarihi;
    @FXML
    private TableColumn<Emanet, String> tblClİslemtarihi;
    @FXML
    private TableColumn<Emanet, String> tblClKullaniciAdi;
    @FXML
    private TableColumn<Emanet, String> tblClKitapAdi;

    ObservableList<Emanet> eList = FXCollections.observableArrayList();
    @FXML
    private TableView<Emanet> emanetListele;
    @FXML
    private TextField txtAramaYap;

    @FXML
    void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);

    }

    public ObservableList<Emanet> getEmanetFromFile() {
        try {
            BufferedReader br = DosyaIslemleri.dosyaGetir("emanet");
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                Emanet e = new Emanet(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5], s[6]);
                eList.add(e);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eList;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tblClEmanetno.setCellValueFactory(new PropertyValueFactory<>("emanetno"));
        tblClKitapAdi.setCellValueFactory(new PropertyValueFactory<>("kitapAdi"));
        tblClKullaniciAdi.setCellValueFactory(new PropertyValueFactory<>("uyeAdi"));
        tblClVermetarihi.setCellValueFactory(new PropertyValueFactory<>("emanevermetarihi"));
        tblClAlmatarihi.setCellValueFactory(new PropertyValueFactory<>("emanetgerialmatarihi"));
        tblClİslemtarihi.setCellValueFactory(new PropertyValueFactory<>("emanetislemtarihi"));

        emanetListele.setItems(getEmanetFromFile());

        //ARAMA
        FilteredList<Emanet> filteredEmanet = new FilteredList<>(eList, b -> true);

        txtAramaYap.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredEmanet.setPredicate(emanet -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(emanet.getEmanetno()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (emanet.getUyeAdi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (emanet.getKitapAdi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
            SortedList<Emanet> sortedEmanet = new SortedList<>(filteredEmanet);

            sortedEmanet.comparatorProperty().bind(emanetListele.comparatorProperty());

            emanetListele.setItems(sortedEmanet);
        }));

    }

    @FXML
    private void emanetSil(ActionEvent event) {
        Emanet seciliEmanet = emanetListele.getSelectionModel().getSelectedItem();
        emanetListele.getItems().remove(seciliEmanet);
        eList.remove(seciliEmanet);
        DosyaIslemleri.dosyayaYaz(eList, "emanet");
    }

    @FXML
    private void emanetDuzenle(ActionEvent event) {
        if (emanetListele.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmanetDuzenle.fxml"));
            Parent parent = loader.load();
            EmanetDuzenleController emanetDuzenle = loader.<EmanetDuzenleController>getController();
            Emanet e = emanetListele.getSelectionModel().getSelectedItem();
            loader.setController(emanetDuzenle);
            emanetDuzenle.degerGetir(e);
            Stage duzenleStage = new Stage();
            Scene scene = new Scene(parent);
            duzenleStage.setTitle("Emanet Düzenle");
            emanetDuzenle.setEmanetList(eList);

            duzenleStage.initModality(Modality.APPLICATION_MODAL);
            duzenleStage.setScene(scene);
            duzenleStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
