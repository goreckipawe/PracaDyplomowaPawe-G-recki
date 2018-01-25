import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-17.
 */
public class firmy {
    private final SimpleIntegerProperty id_regionu;
    private final SimpleStringProperty firma;
    private final SimpleStringProperty adres;
    private final SimpleStringProperty kod_pocztowy;
    private final SimpleStringProperty miejscowosc;

    public firmy(Integer id, String firmaa, String adress,String kod_pocztowyy, String miejscowoscc){
        this.id_regionu = new SimpleIntegerProperty(id);
        this.firma = new SimpleStringProperty(firmaa);
        this.adres = new SimpleStringProperty(adress);
        this.kod_pocztowy = new SimpleStringProperty(kod_pocztowyy);
        this.miejscowosc = new SimpleStringProperty(miejscowoscc);
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
}
