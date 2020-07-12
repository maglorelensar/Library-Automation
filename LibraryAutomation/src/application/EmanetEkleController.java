package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

public class EmanetEkleController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private Button Menü;

    @FXML
    private Button emanetEkle;
    
    @FXML
    private TextField txtEmanetNo;

    @FXML
    private TextField txtEmanetVermeTarihi;

    @FXML
    private TextField txtEmanetAlmaTarihi;

    @FXML
    private TextField txtEmanetIslemTarihi;

    @FXML
    private RadioButton rdTeslimEdildi;

    @FXML
    private RadioButton rdTeslimEdilmedi;
    @FXML
    private ComboBox<String> cmbUyeNo;
    @FXML
    private ComboBox<String> cmbKitapNo;
    
    public ObservableList<Emanet> emanetList = FXCollections.observableArrayList();
    
    

    @FXML
    public void emanetEkle(ActionEvent event) throws IOException {
        emanetList.remove(emanetList);
        int emanetno = Integer.parseInt(txtEmanetNo.getText().trim());
        String uyeno = cmbUyeNo.getValue();
        String kitapno = cmbKitapNo.getValue();
        String emanetvermetarihi = txtEmanetVermeTarihi.getText().trim();
        String emanetgerialmatarihi = txtEmanetAlmaTarihi.getText().trim();
        String emanetislemtarihi = txtEmanetIslemTarihi.getText().trim();
        String emanetTeslim = "";
        if(rdTeslimEdildi.isSelected()){
            emanetTeslim = "Teslim Edildi";
        }else if(rdTeslimEdilmedi.isSelected()){
            emanetTeslim = "Teslim Edilmedi" ;
        }
        Emanet e = new Emanet(emanetno, uyeno, kitapno, emanetvermetarihi, emanetgerialmatarihi, emanetislemtarihi, emanetTeslim);
        emanetList.addAll(Emanet.dosyadanEmanetGetir());
        emanetList.add(e);
        DosyaIslemleri.dosyayaYaz(emanetList, "emanet");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cmbUyeNo.setEditable(true);
        cmbKitapNo.setEditable(true);
        Emanet emanet = new Emanet();
        cmbUyeNo.setItems(emanet.getUyeAdlari());
        cmbKitapNo.setItems(emanet.getKitapAdlari());
        TextFields.bindAutoCompletion(cmbUyeNo.getEditor(),emanet.getUyeAdi());
        TextFields.bindAutoCompletion(cmbKitapNo.getEditor(), emanet.getKitapAdi());
        
    }

    @FXML
    void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);

    }

    
}
