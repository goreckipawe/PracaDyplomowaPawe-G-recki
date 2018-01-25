
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-20.
 */
public class zamowienia {
    private final SimpleIntegerProperty id_zamowienia;
    private final SimpleStringProperty firma;
    private final SimpleStringProperty kwota;
    private final SimpleStringProperty status;
    private final SimpleStringProperty poziom;
    private final SimpleStringProperty data_czas;
    private final SimpleIntegerProperty liczba_produktow;
    private final SimpleStringProperty produkty;
    private final SimpleIntegerProperty numer_zamuwienia;
    private final SimpleBooleanProperty plik_tak_nie;

    public zamowienia(Integer id,String regionn, String kwotaa,String statuss, String poziomm, String data_czass, Integer liczba_produktoww, String produktyy, Integer numer_zamuwieniaa, Boolean plik_tak_niee){
        this.id_zamowienia = new SimpleIntegerProperty(id);
        this.firma = new SimpleStringProperty(regionn);
        this.kwota = new SimpleStringProperty(kwotaa);
        this.status = new SimpleStringProperty(statuss);
        this.poziom = new SimpleStringProperty(poziomm);
        this.data_czas = new SimpleStringProperty(data_czass);
        this.liczba_produktow = new SimpleIntegerProperty(liczba_produktoww);
        this.produkty = new SimpleStringProperty(produktyy);
        this.numer_zamuwienia = new SimpleIntegerProperty(numer_zamuwieniaa);
        this.plik_tak_nie = new SimpleBooleanProperty(plik_tak_niee);
    }

    public int getId_zamowienia() {
        return id_zamowienia.get();
    }

    public SimpleIntegerProperty id_zamowieniaProperty() {
        return id_zamowienia;
    }

    public void setId_zamowienia(int id_zamowienia) {
        this.id_zamowienia.set(id_zamowienia);
    }

    public String getFirma() {
        return firma.get();
    }

    public SimpleStringProperty firmaProperty() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma.set(firma);
    }

    public String getKwota() {
        return kwota.get();
    }

    public SimpleStringProperty kwotaProperty() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota.set(kwota);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getPoziom() {
        return poziom.get();
    }

    public SimpleStringProperty poziomProperty() {
        return poziom;
    }

    public void setPoziom(String poziom) {
        this.poziom.set(poziom);
    }

    public String getData_czas() {
        return data_czas.get();
    }

    public SimpleStringProperty data_czasProperty() {
        return data_czas;
    }

    public void setData_czas(String data_czas) {
        this.data_czas.set(data_czas);
    }

    public int getLiczba_produktow() {
        return liczba_produktow.get();
    }

    public SimpleIntegerProperty liczba_produktowProperty() {
        return liczba_produktow;
    }

    public void setLiczba_produktow(int liczba_produktow) {
        this.liczba_produktow.set(liczba_produktow);
    }

    public String getProdukty() {
        return produkty.get();
    }

    public SimpleStringProperty produktyProperty() {
        return produkty;
    }

    public void setProdukty(String produkty) {
        this.produkty.set(produkty);
    }

    public int getNumer_zamuwienia() {
        return numer_zamuwienia.get();
    }

    public SimpleIntegerProperty numer_zamuwieniaProperty() {
        return numer_zamuwienia;
    }

    public void setNumer_zamuwienia(int numer_zamuwienia) {
        this.numer_zamuwienia.set(numer_zamuwienia);
    }

    public boolean isPlik_tak_nie() {
        return plik_tak_nie.get();
    }

    public SimpleBooleanProperty plik_tak_nieProperty() {
        return plik_tak_nie;
    }

    public void setPlik_tak_nie(boolean plik_tak_nie) {
        this.plik_tak_nie.set(plik_tak_nie);
    }
}
