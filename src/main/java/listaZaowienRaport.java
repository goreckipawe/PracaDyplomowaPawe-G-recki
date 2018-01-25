
/**
 * Created by Paweł on 2017-08-24.
 */
public class listaZaowienRaport {
    private String nazwa;
    private String cena;
    private String status;
    private String status_zamawiającego;
    private String data_zamuwienia;
    private String uwagi;
    public listaZaowienRaport(String nazwaa, String cenaa, String statuss, String status_zamawiającegoo, String data_zamuwieniaa, String uwagii){
        this.nazwa = nazwaa;
        this.cena = cenaa;
        this.status = statuss;
        this.status_zamawiającego = status_zamawiającegoo;
        this.data_zamuwienia = data_zamuwieniaa;
        this.uwagi = uwagii;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getCena() {
        return cena;
    }

    public String getStatus() {
        return status;
    }

    public String getStatus_zamawiającego() {
        return status_zamawiającego;
    }

    public String getData_zamuwienia() {
        return data_zamuwienia;
    }

    public String getUwagi() {
        return uwagi;
    }

    @Override
    public String toString() {
        return "listaZaowienRaport{" +
                "nazwa='" + nazwa + '\'' +
                ", cena='" + cena + '\'' +
                ", status='" + status + '\'' +
                ", status_zamawiającego='" + status_zamawiającego + '\'' +
                ", data_zamuwienia='" + data_zamuwienia + '\'' +
                ", uwagi='" + uwagi + '\'' +
                '}';
    }
}
