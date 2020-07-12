package application;

import java.util.ArrayList;

public class Dergi {

    private int barkod;
    private String ad;
    private int sayfa;
    private int baskiyili;
    private String dil;
    private String yayinevi;
    private String detay;
    private String aciklama;
    private String rafno;
    

    public Dergi(int barkod, String ad, int sayfa, int baskiyili, String dil, String yayinevi, String detay, String aciklama, String rafno) {

        this.barkod = barkod;
        this.ad = ad;
        this.sayfa = sayfa;
        this.baskiyili = baskiyili;
        this.dil = dil;
        this.yayinevi = yayinevi;
        this.detay = detay;
        this.aciklama = aciklama;
        this.rafno = rafno;
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

    @Override
    public String toString() {
        return getBarkod() + "\t" + getAd() + "\t" + getSayfa() + "\t" + getBaskiyili() + "\t" + getDil() + "\t" + getYayinevi() + "\t" + getDetay() + "\t" + getAciklama() + "\t" + getRafno() ;
    }
    

}
