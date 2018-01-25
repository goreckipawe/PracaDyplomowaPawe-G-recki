
/**
 * Created by Paweł on 2017-08-15.
 */
public class listaDochodyFirmy {
    private String kosztTowarow;
    private String kosztNaprawyPojazdow;
    private String kosztPaliwa;
    private String podatkiOplaty;
    private String przychody;
    private String pensjeLacznaKwota;
    private String miesiac;
    private String kwartal;
    private String data;
    public listaDochodyFirmy(String kosztTowaroww, String kosztNaprawyPojazdoww, String kosztPaliwaa, String podatkiOplatyy, String przychodyy, String pensjeLacznaKwotaa, String miesiacc, String kwartall, String dataa){
        this.kosztTowarow = kosztTowaroww;
        this.kosztNaprawyPojazdow = kosztNaprawyPojazdoww;
        this.kosztPaliwa = kosztPaliwaa;
        this.podatkiOplaty = podatkiOplatyy;
        this.przychody = przychodyy;
        this.pensjeLacznaKwota = pensjeLacznaKwotaa;
        this.miesiac = miesiacc;
        this.kwartal = kwartall;
        this.data = dataa;
    }

    public String getKosztTowarow() {
        return kosztTowarow;
    }

    public String getKosztNaprawyPojazdow() {
        return kosztNaprawyPojazdow;
    }

    public String getKosztPaliwa() {
        return kosztPaliwa;
    }

    public String getPodatkiOplaty() {
        return podatkiOplaty;
    }

    public String getPrzychody() {
        return przychody;
    }

    public String getPensjeLacznaKwota() {
        return pensjeLacznaKwota;
    }

    public String getMiesiac() {
        return miesiac;
    }

    public String getKwartal() {
        return kwartal;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "listaDochodyFirmy{" +
                "kosztTowarow='" + kosztTowarow + '\'' +
                ", kosztNaprawyPojazdow='" + kosztNaprawyPojazdow + '\'' +
                ", kosztPaliwa='" + kosztPaliwa + '\'' +
                ", podatkiOplaty='" + podatkiOplaty + '\'' +
                ", przychody='" + przychody + '\'' +
                ", pensjeLacznaKwota='" + pensjeLacznaKwota + '\'' +
                ", miesiac='" + miesiac + '\'' +
                ", kwartal='" + kwartal + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}