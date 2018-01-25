
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-28.
 */
public class wiadomosc_dzialy {
    private final SimpleIntegerProperty id_wiadomosc;
    private final SimpleStringProperty id_dzialu;
    private final SimpleStringProperty tytul;
    private final SimpleStringProperty  data_godzina;
    private final SimpleStringProperty tresc;
    private final SimpleBooleanProperty typ_przeczytana_nieprzeczytana;

    public wiadomosc_dzialy(Integer id1,String id2, String tytull,String trescc, String data_godzinaa, Boolean typ){
        this.id_wiadomosc = new SimpleIntegerProperty(id1);
        this.id_dzialu = new SimpleStringProperty(id2);
        this.tytul = new SimpleStringProperty(tytull);
        this.tresc = new SimpleStringProperty(trescc);
        this.data_godzina = new SimpleStringProperty(data_godzinaa);
        this.typ_przeczytana_nieprzeczytana = new SimpleBooleanProperty(typ);
    }

    public int getId_wiadomosc() {
        return id_wiadomosc.get();
    }

    public SimpleIntegerProperty id_wiadomoscProperty() {
        return id_wiadomosc;
    }

    public void setId_wiadomosc(int id_wiadomosc) {
        this.id_wiadomosc.set(id_wiadomosc);
    }

    public String getId_dzialu() {
        return id_dzialu.get();
    }

    public SimpleStringProperty id_dzialuProperty() {
        return id_dzialu;
    }

    public void setId_dzialu(String id_dzialu) {
        this.id_dzialu.set(id_dzialu);
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

    public String getTresc() {
        return tresc.get();
    }

    public SimpleStringProperty trescProperty() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc.set(tresc);
    }

    public boolean isTyp_przeczytana_nieprzeczytana() {
        return typ_przeczytana_nieprzeczytana.get();
    }

    public Boolean getTyp_przeczytana_nieprzeczytana(){
        return typ_przeczytana_nieprzeczytana.get();
    }
    public void setTyp_przeczytana_nieprzeczytana(Boolean typ){
        typ_przeczytana_nieprzeczytana.set(typ);
    }
}
