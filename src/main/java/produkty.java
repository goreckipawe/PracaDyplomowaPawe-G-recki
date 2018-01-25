
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-19.
 */
public class produkty {
    private final SimpleIntegerProperty id_towaru;
    private final SimpleStringProperty produkt;
    private final SimpleStringProperty nazwa;
    private final SimpleIntegerProperty liczba_sztuk_na_magazynie;
    private final SimpleIntegerProperty ocena;
    private final SimpleStringProperty cena;

    public produkty (Integer id_towaruu,String produktt, String nazwaa,Integer liczba,Integer ocenaa, String cenaa){
        this.id_towaru = new SimpleIntegerProperty(id_towaruu);
        this.produkt = new SimpleStringProperty(produktt);
        this.nazwa = new SimpleStringProperty(nazwaa);
        this.liczba_sztuk_na_magazynie = new SimpleIntegerProperty(liczba);
        this.ocena = new SimpleIntegerProperty(ocenaa);
        this.cena = new SimpleStringProperty(cenaa);
    }

    public int getId_towaru() {
        return id_towaru.get();
    }

    public SimpleIntegerProperty id_towaruProperty() {
        return id_towaru;
    }

    public void setId_towaru(int id_towaru) {
        this.id_towaru.set(id_towaru);
    }

    public String getProdukt() {
        return produkt.get();
    }

    public SimpleStringProperty produktProperty() {
        return produkt;
    }

    public void setProdukt(String produkt) {
        this.produkt.set(produkt);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public SimpleStringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public int getLiczba_sztuk_na_magazynie() {
        return liczba_sztuk_na_magazynie.get();
    }

    public SimpleIntegerProperty liczba_sztuk_na_magazynieProperty() {
        return liczba_sztuk_na_magazynie;
    }

    public void setLiczba_sztuk_na_magazynie(int liczba_sztuk_na_magazynie) {
        this.liczba_sztuk_na_magazynie.set(liczba_sztuk_na_magazynie);
    }

    public int getOcena() {
        return ocena.get();
    }

    public SimpleIntegerProperty ocenaProperty() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena.set(ocena);
    }

    public String getCena() {
        return cena.get();
    }

    public SimpleStringProperty cenaProperty() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena.set(cena);
    }
}

