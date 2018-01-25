
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-09-22.
 */
public class dokumenty_pojazdy {
    private final SimpleIntegerProperty id_raportu_pojazdu;
    private final SimpleIntegerProperty id_pojazdu;
    private final SimpleStringProperty imie;
    private final SimpleStringProperty nazwisko;
    private final SimpleStringProperty  tytul;
    private final SimpleStringProperty data_godzina;
    private final SimpleStringProperty plikhtml;

    public dokumenty_pojazdy(Integer id1, Integer id2, String imiee, String nazwiskoo, String tytull, String data_godzinaa, String texthtmll){
        this.id_raportu_pojazdu = new SimpleIntegerProperty(id1);
        this.id_pojazdu = new SimpleIntegerProperty(id2);
        this.imie = new SimpleStringProperty(imiee);
        this.nazwisko = new SimpleStringProperty(nazwiskoo);
        this.tytul = new SimpleStringProperty(tytull);
        this.data_godzina = new SimpleStringProperty(data_godzinaa);
        this.plikhtml = new SimpleStringProperty(texthtmll);
    }

    public int getId_raportu_pojazdu() {
        return id_raportu_pojazdu.get();
    }

    public SimpleIntegerProperty id_raportu_pojazduProperty() {
        return id_raportu_pojazdu;
    }

    public void setId_raportu_pojazdu(int id_raportu_pojazdu) {
        this.id_raportu_pojazdu.set(id_raportu_pojazdu);
    }

    public int getId_pojazdu() {
        return id_pojazdu.get();
    }

    public SimpleIntegerProperty id_pojazduProperty() {
        return id_pojazdu;
    }

    public void setId_pojazdu(int id_pojazdu) {
        this.id_pojazdu.set(id_pojazdu);
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

    public String getTytul() {
        return tytul.get();
    }

    public SimpleStringProperty tytulProperty() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul.set(tytul);
    }

    public String getData_godzina() {
        return data_godzina.get();
    }

    public SimpleStringProperty data_godzinaProperty() {
        return data_godzina;
    }

    public void setData_godzina(String data_godzina) {
        this.data_godzina.set(data_godzina);
    }

    public String getPlikhtml() {
        return plikhtml.get();
    }

    public SimpleStringProperty plikhtmlProperty() {
        return plikhtml;
    }

    public void setPlikhtml(String plikhtml) {
        this.plikhtml.set(plikhtml);
    }
}
