package application;

public class Dijital {

    private int barkod;
    private String ad;
    private String dil;
    private String boyut;
    private String sure;
    private String yayinyili;

    public Dijital(int barkod, String ad, String dil, String boyut, String sure, String yayinyili) {
        this.barkod = barkod;
        this.ad = ad;
        this.dil = dil;
        this.boyut = boyut;
        this.sure = sure;
        this.yayinyili = yayinyili;
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

    public String getDil() {
        return dil;
    }

    public void setDil(String dil) {
        this.dil = dil;
    }

    public String getBoyut() {
        return boyut;
    }

    public void setBoyut(String boyut) {
        this.boyut = boyut;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }

    public String getYayinyili() {
        return yayinyili;
    }

    public void setYayinyili(String yayinyili) {
        this.yayinyili = yayinyili;
    }

    @Override
    public String toString() {
        return getBarkod() + "\t" + getAd() + "\t" + getDil() + "\t" + getBoyut() + "\t" + getSure() + "\t" + getYayinyili();
    }

}
