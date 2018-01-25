
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-09.
 */
public class wiadomosc {
    private final SimpleIntegerProperty id_wiadomosc;
    private final SimpleStringProperty imie;
    private final SimpleStringProperty nazwisko;
    private final SimpleStringProperty tytul;
    private final SimpleStringProperty tresc;
    private final SimpleStringProperty  data_godzina;
    private final SimpleBooleanProperty typ_przeczytana_nieprzeczytana;
    private final SimpleBooleanProperty plik_tak_nie;

    public wiadomosc(Integer id1, String imiee, String nazwiskoo, String tytull,String trescc, String data_godzinaa, Boolean typ, Boolean pliktn){
        this.id_wiadomosc = new SimpleIntegerProperty(id1);
        this.imie = new SimpleStringProperty(imiee);
        this.nazwisko = new SimpleStringProperty(nazwiskoo);
        this.tytul = new SimpleStringProperty(tytull);
        this.tresc = new SimpleStringProperty(trescc);
        this.data_godzina = new SimpleStringProperty(data_godzinaa);
        this.typ_przeczytana_nieprzeczytana = new SimpleBooleanProperty(typ);
        this.plik_tak_nie = new SimpleBooleanProperty(pliktn);
    }

    public Integer getId_wiadomosc(){

        return id_wiadomosc.get();
    }
    public void setId_wiadomosc(Integer id1){

        id_wiadomosc.set(id1);
    }

    public String getImie() {
        return imie.get();
    }

    public SimpleStringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public SimpleStringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getTytul(){
        return tytul.get();
    }
    public void setTytul(String tytull){
        tytul.set(tytull);
    }
    public String getTresc(){
        return tresc.get();
    }
    public void setTresc(String trescc){
        tresc.set(trescc);
    }
    public String getData_godzina(){
        return data_godzina.get();
    }
    public void setData_godzina(String data_godzinaa){
        data_godzina.set(data_godzinaa);
    }
    public Boolean getTyp_przeczytana_nieprzeczytana(){
        return typ_przeczytana_nieprzeczytana.get();
    }
    public void setTyp_przeczytana_nieprzeczytana(Boolean typ){
        typ_przeczytana_nieprzeczytana.set(typ);
    }
    public Boolean getPlik_tak_nie(){
        return plik_tak_nie.get();
    }
    public void setPlik_tak_nie(Boolean pliktn){
        plik_tak_nie.set(pliktn);
    }
}

