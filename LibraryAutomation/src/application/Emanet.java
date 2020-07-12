package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Emanet implements IGenel {

    private int emanetno;
    private String uyeAdi;
    private String kitapAdi;
    private String emanevermetarihi;
    private String emanetgerialmatarihi;
    private String emanetislemtarihi;
    private String emanetteslimkutusu;
    
    public Emanet(){
    }

    public Emanet(int emanetno, String uyeAdi, String kitapAdi, String emanevermetarihi, String emanetgerialmatarihi, String emanetislemtarihi, String emanetteslimkutusu) {
        this.emanetno = emanetno;
        this.uyeAdi = uyeAdi;
        this.kitapAdi = kitapAdi;
        this.emanevermetarihi = emanevermetarihi;
        this.emanetgerialmatarihi = emanetgerialmatarihi;
        this.emanetislemtarihi = emanetislemtarihi;
        this.emanetteslimkutusu = emanetteslimkutusu;
    }

    public int getEmanetno() {
        return emanetno;
    }

    public void setEmanetno(int emanetno) {
        this.emanetno = emanetno;
    }

    public String getUyeAdi() {
        return uyeAdi;
    }

    public void setUyeAdi(String uyeAdi) {
        this.uyeAdi = uyeAdi;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    

    public String getEmanevermetarihi() {
        return emanevermetarihi;
    }

    public void setEmanevermetarihi(String emanevermetarihi) {
        this.emanevermetarihi = emanevermetarihi;
    }

    public String getEmanetgerialmatarihi() {
        return emanetgerialmatarihi;
    }

    public void setEmanetgerialmatarihi(String emanetgerialmatarihi) {
        this.emanetgerialmatarihi = emanetgerialmatarihi;
    }

    public String getEmanetislemtarihi() {
        return emanetislemtarihi;
    }

    public void setEmanetislemtarihi(String emanetislemtarihi) {
        this.emanetislemtarihi = emanetislemtarihi;
    }

    public String getEmanetteslimkutusu() {
        return emanetteslimkutusu;
    }

    public void setEmanetteslimkutusu(String emanetteslimkutusu) {
        this.emanetteslimkutusu = emanetteslimkutusu;
    }

    @Override
    public ObservableList getUyeAdlari() {
        ObservableList<String> kullaniciAdi = FXCollections.observableArrayList();
        try {
            BufferedReader br = DosyaIslemleri.dosyaGetir("kullanici");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                String kullanici = satir[1];
                kullaniciAdi.add(kullanici);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kullaniciAdi;
    }

    @Override
    public ObservableList getKitapAdlari() {
        ObservableList<String> kitapAdi = FXCollections.observableArrayList();
        try {
            BufferedReader br = DosyaIslemleri.dosyaGetir("kitap");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                String kitap = satir[1];
                kitapAdi.add(kitap);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kitapAdi;
    }
    
    public static ObservableList<Emanet> dosyadanEmanetGetir() {
        ObservableList<Emanet> geciciList = FXCollections.observableArrayList();
        try {
            BufferedReader br =DosyaIslemleri.dosyaGetir("emanet");
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                Emanet e = new Emanet(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5], s[6]);
                geciciList.add(e);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciList;
    }

    @Override
    public String toString() {
        return getEmanetno() + "\t" + getUyeAdi() + "\t" + getKitapAdi() + "\t" + getEmanevermetarihi() + "\t" + getEmanetgerialmatarihi() + "\t" + getEmanetislemtarihi() + "\t" + getEmanetteslimkutusu();
    }

    
    

}
