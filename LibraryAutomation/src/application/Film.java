package application;

import java.util.ArrayList;

public class Film {

    private String ad;
    private String vizyontarihi;
    private String tur;
    private int sure;
    private String yonetmen;
    private String yapimyili;


    public Film(String ad, String vizyontarihi, String tur, int sure, String yonetmen, String yapimyili) {
        this.ad = ad;
        this.vizyontarihi = vizyontarihi;
        this.tur = tur;
        this.sure = sure;
        this.yonetmen = yonetmen;
        this.yapimyili = yapimyili;
    }


    @Override
    public String toString() {
        return getAd() + "\t" + getVizyontarihi() + "\t" + getTur() + "\t" + getSure() + "\t" + getYonetmen() + "\t" + getYapimyili();
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getVizyontarihi() {
        return vizyontarihi;
    }

    public void setVizyontarihi(String vizyontarihi) {
        this.vizyontarihi = vizyontarihi;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }

    public String getYonetmen() {
        return yonetmen;
    }

    public void setYonetmen(String yonetmen) {
        this.yonetmen = yonetmen;
    }

    public String getYapimyili() {
        return yapimyili;
    }

    public void setYapimyili(String yapimyili) {
        this.yapimyili = yapimyili;
    }

}
