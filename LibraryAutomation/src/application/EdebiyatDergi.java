package application;

public class EdebiyatDergi extends Dergi {

    private String tur;

    public EdebiyatDergi(int barkod, String ad, int sayfa, int baskiyili, String dil, String yayinevi, String detay, String aciklama, String rafno, String tur) {
        super(barkod, ad, sayfa, baskiyili, dil, yayinevi, detay, aciklama, rafno);
        this.tur = tur;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    @Override
    public String toString() {
        return getBarkod() + "\t" + getAd() + "\t" + getSayfa() + "\t" + getBaskiyili() + "\t" + getDil() + "\t" + getYayinevi() + "\t" + getDetay() + "\t" + getAciklama() + "\t" + getRafno() + "\t" + getTur();
    }

}
