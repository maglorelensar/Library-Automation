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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Monster
 */
public class EmanetDuzenleController implements Initializable {

    @FXML
    private TextField txtEmanetNo;
    @FXML
    private TextField txtEmanetVermeTarihi;
    @FXML
    private TextField txtEmanetAlmaTarihi;
    @FXML
    private TextField txtEmanetIslemTarihi;
    @FXML
    private ComboBox<String> cmbUyeAdi;
    @FXML
    private ComboBox<String> cmbKitapAdi;
    
    Emanet seciliEmanet;
    @FXML
    private RadioButton rdTeslimEdildi;
    @FXML
    private RadioButton rdTeslimEdilmedi;
    
    private ObservableList<Emanet> emanetList = FXCollections.observableArrayList();

    public void setEmanetList(ObservableList<Emanet> emanetList) {
        this.emanetList = emanetList;
    }
    
    
    void degerGetir(Emanet emanet) {
        seciliEmanet = emanet;
        txtEmanetNo.setText(String.valueOf(seciliEmanet.getEmanetno()));
        cmbUyeAdi.setValue(seciliEmanet.getUyeAdi());
        cmbKitapAdi.setValue(seciliEmanet.getKitapAdi());
        txtEmanetVermeTarihi.setText(seciliEmanet.getEmanevermetarihi());
        txtEmanetAlmaTarihi.setText(seciliEmanet.getEmanetgerialmatarihi());
        txtEmanetIslemTarihi.setText(seciliEmanet.getEmanetislemtarihi());
        if(seciliEmanet.getEmanetteslimkutusu().equals("Teslim Edildi")){
            rdTeslimEdildi.setSelected(true);
        }else if(seciliEmanet.getEmanetteslimkutusu().equals("Teslim Edilmedi")){
            rdTeslimEdilmedi.setSelected(true);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbUyeAdi.setEditable(true);
        cmbKitapAdi.setEditable(true);
        Emanet emanet = new Emanet();
        cmbUyeAdi.setItems(emanet.getUyeAdlari());
        cmbKitapAdi.setItems(emanet.getKitapAdlari());
        TextFields.bindAutoCompletion(cmbUyeAdi.getEditor(),emanet.getUyeAdi());
        TextFields.bindAutoCompletion(cmbKitapAdi.getEditor(), emanet.getKitapAdi());
    }    

    @FXML
    private void btnDuzenleIptal(ActionEvent event) {
        kapat(event);
    }

    @FXML
    private void btnDuzenleKaydet(ActionEvent event) {
        emanetList.remove(seciliEmanet);
        int emanetno = Integer.parseInt(txtEmanetNo.getText().trim());
        String uyeno = cmbUyeAdi.getValue();
        String kitapno = cmbKitapAdi.getValue();
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
        emanetList.add(e);
        DosyaIslemleri.dosyayaYaz(emanetList, "emanet");
        
        kapat(event);
    }
    
    private void kapat(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    
    
}
