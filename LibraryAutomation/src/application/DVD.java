package application;

public class DVD extends Dijital {

    private String tur;

    public DVD(int barkod, String ad, String dil, String boyut, String sure, String yayinyili, String tur) {
        super(barkod, ad, dil, boyut, sure, yayinyili);
        this.tur = tur;
    }

    @Override
    public String toString() {
        return getBarkod() + "\t" + getAd() + "\t" + getDil() + "\t" + getBoyut() + "\t" + getSure() + "\t" + getYayinyili() + "\t" + getTur();
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

}
