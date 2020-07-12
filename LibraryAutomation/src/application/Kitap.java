package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Kitap {

    private int barkod;
    private String ad;
    private int sayfa;
    private int baskiyili;
    private String dil;
    private String yayinevi;
    private String yazar;
    private String detay;
    private String aciklama;
    private String rafno;

    public Kitap() {

    }

    public Kitap(int barkod, String ad, int sayfa, int baskiyili, String dil, String yayinevi, String yazar,
            String detay, String aciklama, String rafno) {

        this.barkod = barkod;
        this.ad = ad;
        this.sayfa = sayfa;
        this.baskiyili = baskiyili;
        this.dil = dil;
        this.yayinevi = yayinevi;
        this.yazar = yazar;
        this.detay = detay;
        this.aciklama = aciklama;
        this.rafno = rafno;

    }

     public static ObservableList<Kitap> dosyadanKitapGetir() {
        ObservableList<Kitap> geciciList = FXCollections.observableArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("kitap")));
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                if (s[10].equals("Akademik")) {
                    Akademik a = new Akademik(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    geciciList.add(a);
                } else if (s[10].equals("Atlas")) {
                    Atlas at = new Atlas(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    geciciList.add(at);
                } else if (s[10].equals("Hikaye")) {
                    Hikaye h = new Hikaye(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    geciciList.add(h);
                } else if (s[10].equals("Roman")) {
                    Roman r = new Roman(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    geciciList.add(r);
                } else if (s[10].equals("Siir")) {
                    Siir si = new Siir(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    geciciList.add(si);
                } else {
                    Sozluk so = new Sozluk(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4], s[5], s[6], s[7], s[8], s[9], s[10]);
                    geciciList.add(so);
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciList;
    }

    @Override
    public String toString() {
        return  getBarkod()+ "\t" + getAd()+ "\t" +  getSayfa()+ "\t"  + getBaskiyili()+ "\t" + getDil()+ "\t" +  getYayinevi()+ "\t" + getYazar() + "\t"+  getDetay()+ "\t" +  getAciklama()+ "\t" +  getRafno() ;
    }

    public int getBarkod() {
        return barkod;
    }

    public void setBarkod(int barkod) {
        this.barkod = barkod;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getSayfa() {
        return sayfa;
    }

    public void setSayfa(int sayfa) {
        this.sayfa = sayfa;
    }

    public int getBaskiyili() {
        return baskiyili;
    }

    public void setBaskiyili(int baskiyili) {
        this.baskiyili = baskiyili;
    }

    public String getDil() {
        return dil;
    }

    public void setDil(String dil) {
        this.dil = dil;
    }

    public String getYayinevi() {
        return yayinevi;
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getRafno() {
        return rafno;
    }

    public void setRafno(String rafno) {
        this.rafno = rafno;
    }
  

}
