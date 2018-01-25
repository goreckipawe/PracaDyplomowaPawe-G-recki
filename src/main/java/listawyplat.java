
/**
 * Created by Paweł on 2017-08-13.
 */
import java.util.Arrays;
        import java.util.Collections;
public class listawyplat {
    private String imie;
    private String nazwisko;
    private String identyfikator;
    private String stanowisko;
    private String liczbadni;
    private String wypłata;
    public listawyplat(String imie, String nazwisko, String identyfikator, String stanowisko, String liczbadni, String wypłata ){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.identyfikator = identyfikator;
        this.stanowisko = stanowisko;
        this.liczbadni = liczbadni;
        this.wypłata = wypłata;
    }
    String getImie(){ return imie;}
    String getNazwisko(){ return nazwisko;}
    String getIdentyfikator(){ return identyfikator;}
    String getStanowisko(){ return stanowisko;}
    String getLiczbadni(){ return liczbadni;}
    String getWypłata(){ return wypłata;}

    public String toString() {
        return "Imię: "+imie+", Nazwisko: "+nazwisko+", Identyfikator: "+identyfikator+", Stanowisk: "+stanowisko+", Liczbadni: "+liczbadni+", Wypłata: "+wypłata;}
}