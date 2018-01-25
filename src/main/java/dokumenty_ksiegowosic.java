
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2017-08-13.
 */
public class dokumenty_ksiegowosic {
    private final SimpleIntegerProperty id_dokumentu;
    private final SimpleIntegerProperty id_pracownika;
    private final SimpleStringProperty tytul;
    private final SimpleStringProperty  data_godzina;
    private final SimpleStringProperty rodzaj;
    private final SimpleStringProperty plikhtml;

    public dokumenty_ksiegowosic(Integer id1,Integer id2, String tytull,String data_godzinaa, String rodzajj, String texthtml){
        this.id_dokumentu = new SimpleIntegerProperty(id1);
        this.id_pracownika = new SimpleIntegerProperty(id2);
        this.tytul = new SimpleStringProperty(tytull);
        this.data_godzina = new SimpleStringProperty(data_godzinaa);
        this.rodzaj = new SimpleStringProperty(rodzajj);
        this.plikhtml = new SimpleStringProperty(texthtml);
    }

    public Integer getId_dokumentu(){

        return id_dokumentu.get();
    }
    public void setId_dokumentu(Integer id1){

        id_dokumentu.set(id1);
    }
    public Integer getId_pracownika(){

        return id_pracownika.get();
    }
    public void setId_pracownika(Integer id2){

        id_pracownika.set(id2);
    }
    public String getTytul(){
        return tytul.get();
    }
    public void setTytul(String tytull){
        tytul.set(tytull);
    }
    public String getData_godzina(){
        return data_godzina.get();
    }
    public void setData_godzina(String data_godzinaa){
        data_godzina.set(data_godzinaa);
    }

    public String getRodzaj() {
        return rodzaj.get();
    }

    public SimpleStringProperty rodzajProperty() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj.set(rodzaj);
    }

    public String getPlikhtml(){
        return plikhtml.get();
    }
    public void setPlikhtml(String texthtml){
        plikhtml.set(texthtml);
    }
}
