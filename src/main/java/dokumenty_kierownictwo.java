
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-27.
 */
public class dokumenty_kierownictwo {
    private final SimpleIntegerProperty id_raportu;
    private final SimpleIntegerProperty id_pracownika;
    private final SimpleStringProperty nazwa;
    private final SimpleStringProperty data_czas;

    public dokumenty_kierownictwo(Integer id,Integer identyfikatorr, String nazwaa,String data_czass){
        this.id_raportu = new SimpleIntegerProperty(id);
        this.id_pracownika = new SimpleIntegerProperty(identyfikatorr);
        this.nazwa = new SimpleStringProperty(nazwaa);
        this.data_czas = new SimpleStringProperty(data_czass);
    }

    public int getId_raportu() {
        return id_raportu.get();
    }

    public SimpleIntegerProperty id_raportuProperty() {
        return id_raportu;
    }

    public void setId_raportu(int id_raportu) {
        this.id_raportu.set(id_raportu);
    }

    public int getId_pracownika() {
        return id_pracownika.get();
    }

    public SimpleIntegerProperty id_pracownikaProperty() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika.set(id_pracownika);
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

    public String getData_czas() {
        return data_czas.get();
    }

    public SimpleStringProperty data_czasProperty() {
        return data_czas;
    }

    public void setData_czas(String data_czas) {
        this.data_czas.set(data_czas);
    }
}
