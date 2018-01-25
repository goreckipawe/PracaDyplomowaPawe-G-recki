import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-20.
 */
public class klienci {
    private final SimpleIntegerProperty id_regionu;
    private final SimpleStringProperty przedstawiciel;
    private final SimpleStringProperty firma;
    private final SimpleStringProperty adres;
    private final SimpleIntegerProperty liczba_zamowien;
    private final SimpleStringProperty poziom;
    private final SimpleStringProperty kod_pocztowy;
    private final SimpleStringProperty miejscowosc;
    private final SimpleStringProperty telefon;
    private final SimpleStringProperty email;
    private final SimpleIntegerProperty numer;

    public klienci(Integer id,String przedstawiciell, String firmaa,String adress, Integer liczba_zamowienaa, String wiarygodnoscc, String kod_pocztowyy, String miejscowoscc, String telefonn, String emaill, Integer numerr){
        this.id_regionu = new SimpleIntegerProperty(id);
        this.przedstawiciel = new SimpleStringProperty(przedstawiciell);
        this.firma = new SimpleStringProperty(firmaa);
        this.adres = new SimpleStringProperty(adress);
        this.liczba_zamowien = new SimpleIntegerProperty(liczba_zamowienaa);
        this.poziom = new SimpleStringProperty(wiarygodnoscc);
        this.kod_pocztowy = new SimpleStringProperty(kod_pocztowyy);
        this.miejscowosc = new SimpleStringProperty(miejscowoscc);
        this.telefon = new SimpleStringProperty(telefonn);
        this.email = new SimpleStringProperty(emaill);
        this.numer = new SimpleIntegerProperty(numerr);
    }

    public int getId_regionu() {
        return id_regionu.get();
    }

    public SimpleIntegerProperty id_regionuProperty() {
        return id_regionu;
    }

    public void setId_regionu(int id_regionu) {
        this.id_regionu.set(id_regionu);
    }

    public String getPrzedstawiciel() {
        return przedstawiciel.get();
    }

    public SimpleStringProperty przedstawicielProperty() {
        return przedstawiciel;
    }

    public void setPrzedstawiciel(String przedstawiciel) {
        this.przedstawiciel.set(przedstawiciel);
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

    public String getAdres() {
        return adres.get();
    }

    public SimpleStringProperty adresProperty() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres.set(adres);
    }

    public int getLiczba_zamowien() {
        return liczba_zamowien.get();
    }

    public SimpleIntegerProperty liczba_zamowienProperty() {
        return liczba_zamowien;
    }

    public void setLiczba_zamowien(int liczba_zamowien) {
        this.liczba_zamowien.set(liczba_zamowien);
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

    public void setNumer(int numer) {
        this.numer.set(numer);
    }

    public String getKod_pocztowy() {
        return kod_pocztowy.get();
    }

    public SimpleStringProperty kod_pocztowyProperty() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy.set(kod_pocztowy);
    }

    public String getMiejscowosc() {
        return miejscowosc.get();
    }

    public SimpleStringProperty miejscowoscProperty() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc.set(miejscowosc);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public SimpleStringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Integer getNumer() {
        return numer.get();
    }

    public SimpleIntegerProperty numerProperty() {
        return numer;
    }

    public void setNumer(Integer numer) {
        this.numer.set(numer);
    }
}
