
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2016-02-06.
 */
public class Osoby {
    private final SimpleIntegerProperty id_pracownika;
    private final SimpleStringProperty Imię;
    private final SimpleStringProperty Nazwisko;
    private final SimpleLongProperty PESEL;
    private final SimpleIntegerProperty Nrtelefonu;
    private final SimpleStringProperty Adres;
    private final SimpleStringProperty Działp;
    private final SimpleStringProperty Stanowisko;
    private final SimpleStringProperty Adresemail;
    private final SimpleIntegerProperty Identyfikator;
    private final SimpleStringProperty Hasło;
    private final SimpleIntegerProperty Dniu;
    private final SimpleIntegerProperty Dniuw;

    public Osoby(Integer id,String imie, String nazwisko,Long pesel,Integer telefon, String adres, String dzial,String stanowisko, String poczta, Integer numer, String haslo, Integer dniu, Integer dniuw){
        this.id_pracownika = new SimpleIntegerProperty(id);
        this.Imię = new SimpleStringProperty(imie);
        this.Nazwisko = new SimpleStringProperty(nazwisko);
        this.PESEL = new SimpleLongProperty(pesel);
        this.Nrtelefonu = new SimpleIntegerProperty(telefon);
        this.Adres = new SimpleStringProperty(adres);
        this.Działp = new SimpleStringProperty(dzial);
        this.Stanowisko = new SimpleStringProperty(stanowisko);
        this.Adresemail = new SimpleStringProperty(poczta);
        this.Identyfikator = new SimpleIntegerProperty(numer);
        this.Hasło = new SimpleStringProperty(haslo);
        this.Dniu = new SimpleIntegerProperty(dniu);
        this.Dniuw = new SimpleIntegerProperty(dniuw);
    }
    public Integer getId_pracownika(){

        return id_pracownika.get();
    }
    public void setId_pracownika(Integer id){

        id_pracownika.set(id);
    }
    public String getImię(){

        return Imię.get();
    }
    public void setImię(String imie){

        Imię.set(imie);
    }
    public String getNazwisko(){
        return Nazwisko.get();
    }
    public void setNazwisko(String nazwisko){
        Nazwisko.set(nazwisko);
    }
    public Long getPESEL(){
        return PESEL.get();
    }
    public void setPESEL(Long pesel){
        PESEL.set(pesel);
    }
    public Integer getNrtelefonu(){
        return Nrtelefonu.get();
    }
    public void setNrtelefonu(Integer telefon){
        Nrtelefonu.set(telefon);
    }
    public String getAdres(){
        return Adres.get();
    }
    public void setAdres(String adres){
        Adres.set(adres);
    }
    public String getDziałp(){
        return Działp.get();
    }
    public void setDziałp(String dzial){
        Działp.set(dzial);
    }
    public String getStanowisko(){
        return Stanowisko.get();
    }
    public void setStanowisko(String stanowisko){
        Stanowisko.set(stanowisko);
    }
    public String getAdresemail(){
        return Adresemail.get();
    }
    public void setAdresemail(String poczta){
        Adresemail.set(poczta);
    }
    public Integer getIdentyfikator(){
        return Identyfikator.get();
    }
    public void setIdentyfikator(Integer numer){
        Identyfikator.set(numer);
    }
    public String getHasło(){
        return Hasło.get();
    }
    public void setHasło(String haslo){
        Hasło.set(haslo);
    }

    public int getDniu() {
        return Dniu.get();
    }

    public SimpleIntegerProperty dniuProperty() {
        return Dniu;
    }

    public void setDniu(int dniu) {
        this.Dniu.set(dniu);
    }

    public int getDniuw() {
        return Dniuw.get();
    }

    public SimpleIntegerProperty dniuwProperty() {
        return Dniuw;
    }

    public void setDniuw(int dniuw) {
        this.Dniuw.set(dniuw);
    }
}