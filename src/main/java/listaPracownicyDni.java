
/**
 * Created by Paweł on 2017-08-18.
 */
public class listaPracownicyDni {
    private String imie_nazwisko;
    private String identyfikator;
    private String liczba_dni;
    private String urlopd;
    private String urlopc;
    public listaPracownicyDni(String imie_nazwiskoo, String identyfikatorr, String liczba_dnii, String urlopdd, String urlopcc){
        this.imie_nazwisko = imie_nazwiskoo;
        this.identyfikator = identyfikatorr;
        this.liczba_dni = liczba_dnii;
        this.urlopd = urlopdd;
        this.urlopc = urlopcc;
    }

    public String getImie_nazwisko() {
        return imie_nazwisko;
    }

    public String getIdentyfikator() {
        return identyfikator;
    }

    public String getLiczba_dni() {
        return liczba_dni;
    }

    public String getUrlopd() {
        return urlopd;
    }

    public String getUrlopc() {
        return urlopc;
    }

    @Override
    public String toString() {
        return "listaPracownicyDni{" +
                "imie_nazwisko='" + imie_nazwisko + '\'' +
                ", identyfikator='" + identyfikator + '\'' +
                ", liczba_dni='" + liczba_dni + '\'' +
                ", urlopd='" + urlopd + '\'' +
                ", urlopc='" + urlopc + '\'' +
                '}';
    }
}
