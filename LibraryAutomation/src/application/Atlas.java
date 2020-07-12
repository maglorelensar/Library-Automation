package application;

import java.util.ArrayList;

public class Atlas extends Kitap {

    private String bolum;

    public Atlas(int barkod, String ad, int sayfa, int baskiyili, String dil, String yayinevi, String yazar, String detay, String aciklama, String rafno, String bolum) {
        super(barkod, ad, sayfa, baskiyili, dil, yayinevi, yazar, detay, aciklama, rafno);
        this.bolum = bolum;
    }

    
   
    
     @Override
    public String toString() {
         return  getBarkod()+ "\t" + getAd()+ "\t" +  getSayfa()+ "\t"  + getBaskiyili()+ "\t" + getDil()+ "\t" +  getYayinevi()+ "\t" + getYazar() + "\t"+  getDetay()+ "\t" +  getAciklama()+ "\t" +  getRafno()+"\t"+getBolum(); 
    }


    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

}
