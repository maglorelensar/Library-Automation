package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Kullanici {

    private int kullaniciNo;
    private String ad;
    private String soyad;
    private String telefon;
    private String eposta;
    private String adres;

    private ObservableList<Kullanici> kullaniciList = FXCollections.observableArrayList();

    public Kullanici(int kullaniciNo, String ad, String soyad, String telefon, String eposta, String adres) {
        this.kullaniciNo = kullaniciNo;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.eposta = eposta;
        this.adres = adres;
        
    }

    public static ObservableList<Kullanici> dosyadanKullaniciGetir() {
        ObservableList<Kullanici> geciciList = FXCollections.observableArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("kullanici")));
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                Kullanici k = new Kullanici(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5]);
                geciciList.add(k);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciList;
    }
    
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getKullaniciNo() {
        return kullaniciNo;
    }

    public void setKullaniciNo(int kullaniciNo) {
        this.kullaniciNo = kullaniciNo;
    }

    public ObservableList<Kullanici> getKullaniciList() {
        return kullaniciList;
    }

    public void setKullaniciList(ObservableList<Kullanici> kullaniciList) {
        this.kullaniciList = kullaniciList;
    }

    @Override
    public String toString() {
        return getKullaniciNo() + "\t" + getAd() + "\t" + getSoyad() + "\t" + getTelefon() + "\t" + getEposta() + "\t" + getAdres();
    }

}
