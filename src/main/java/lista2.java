
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Paweł on 2016-05-26.
 */
public class lista2 {
    private  final SimpleIntegerProperty id_pojazdu;
    private final SimpleStringProperty Marka;
    private final SimpleStringProperty Model;
    private  final SimpleStringProperty Rok;
    private  final SimpleStringProperty Rokzakupu;
    private  final SimpleIntegerProperty Pojemność;
    private  final SimpleIntegerProperty Przebieg;
    private  final SimpleStringProperty Nrrejestracyjny;
    private final SimpleStringProperty Ubezpieczenie;
    private  final SimpleStringProperty Uszkodzony;
    private  final SimpleStringProperty Zdjecie;

    public lista2 (Integer id, String marka, String model, String rok, String rokz, Integer pojemnosc, Integer przebieg, String rejestracja, String ubezpieczony, String uszkodzony, String zdjecie){
        this.id_pojazdu = new SimpleIntegerProperty(id);
        this.Marka = new SimpleStringProperty(marka);
        this.Model = new SimpleStringProperty(model);
        this.Rok = new SimpleStringProperty(rok);
        this.Rokzakupu = new SimpleStringProperty(rokz);
        this.Pojemność = new SimpleIntegerProperty(pojemnosc);
        this.Przebieg = new SimpleIntegerProperty(przebieg);
        this.Nrrejestracyjny = new SimpleStringProperty(rejestracja);
        this.Ubezpieczenie = new SimpleStringProperty(ubezpieczony);
        this.Uszkodzony = new SimpleStringProperty(uszkodzony);
        this.Zdjecie = new SimpleStringProperty(zdjecie);
    };
    public Integer getId_pojazdu(){

        return id_pojazdu.get();
    }
    public void setId_pojazdu(Integer id){

        id_pojazdu.set(id);
    }
    public String getMarka(){

        return Marka.get();
    }
    public void setMarka(String marka){

        Marka.set(marka);
    }
    public String getModel(){
        return Model.get();
    }
    public void setModel(String model){
        Model.set(model);
    }
    public String getRok(){
        return Rok.get();
    }
    public void setRok(String rok){
        Rok.set(rok);
    }
    public String getRokzakupu(){
        return Rokzakupu.get();
    }
    public void setRokzakupu(String rokz){
        Rokzakupu.set(rokz);
    }
    public Integer getPojemność(){
        return Pojemność.get();
    }
    public void setPojemność(Integer pojemność){
        Pojemność.set(pojemność);
    }
    public Integer getPrzebieg(){
        return Przebieg.get();
    }
    public void setPrzebieg(Integer przebieg){
        Przebieg.set(przebieg);
    }
    public String getNrrejestracyjny(){
        return Nrrejestracyjny.get();
    }
    public void setNrrejestracyjny(String rejestracja){
        Nrrejestracyjny.set(rejestracja);
    }
    public String getUbezpieczenie(){
        return Ubezpieczenie.get();
    }
    public void setUbezpieczenie(String ubezpieczenie){
        Ubezpieczenie.set(ubezpieczenie);
    }
    public String getUszkodzony(){
        return Uszkodzony.get();
    }
    public void setUszkodzony(String uszkodzony){
        Uszkodzony.set(uszkodzony);
    }
    public String getZdjecie(){
        return Zdjecie.get();
    }
    public void setZdjecie(String zdjecie){
        Zdjecie.set(zdjecie);
    }
}