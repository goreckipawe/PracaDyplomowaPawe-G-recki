import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Paweł on 2017-11-03.
 */
public class baza {

    private static String sterownik = "org.sqlite.JDBC";
    private static String baza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private static Connection polaczenie;
    private static Statement st;
    private static ResultSet rs;

    public List<wiadomosc> baza(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer idw = rs.getInt("id_wiadomosc");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String tytul = rs.getString("tytul");
                String tresc = rs.getString("tresc");
                String rokz = rs.getString("data_godzina");
                Boolean typ = rs.getBoolean("typ_przeczytana_nieprzeczytana");
                Boolean plik = rs.getBoolean("plik_tak_nie");
                lll.add(new wiadomosc(idw, imie, nazwisko, tytul, tresc, rokz, typ, plik));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<dokumenty_ksiegowosic> baza2(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer idd = rs.getInt("id_dokumentu");
                Integer idp = rs.getInt("id_pracownika");
                String tytul = rs.getString("tytul");
                String data = rs.getString("data_godzina");
                String rodzaj = rs.getString("rodzaj");
                String html = rs.getString("plikhtml");
                lll.add(new dokumenty_ksiegowosic(idd, idp, tytul, data, rodzaj, html));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<wiadomosc_dzialy> baza3(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer idw = rs.getInt("id_wiadomosc");
                String idp = rs.getString("nazwa_dzialu");
                String tytul = rs.getString("tytul");
                String rokz = rs.getString("data_godzina");
                String tresc = rs.getString("tresc");
                Boolean typ = rs.getBoolean("typ_przeczytana_nieprzeczytana");
                lll.add(new wiadomosc_dzialy(idw, idp, tytul, tresc, rokz, typ));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<Osoby> baza4(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery( zapytanie);
            while (rs.next()) {
                Integer id = rs.getInt("id_pracownika");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                Long pesel = rs.getLong("pesel");
                Integer telefon = rs.getInt("telefon");
                String adres = rs.getString("adres");
                String dzial = rs.getString("nazwa_dzialu");
                String stanowisko = rs.getString("nazwa_stanowiska");
                String poczta = rs.getString("email");
                Integer numer = rs.getInt("identyfikator");
                String haslo = rs.getString("haslo");
                Integer dniu = rs.getInt("liczba_dni_urlopu");
                Integer dniuw = rs.getInt("liczba_dni_urlopu_wykorzystane");
                lll.add(new Osoby(id, imie, nazwisko, pesel, telefon, adres, dzial, stanowisko, poczta, numer, haslo, dniu, dniuw));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<lista2> baza5(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer id = rs.getInt("id_pojazdu");
                String marka = rs.getString("marka");
                String model = rs.getString("model");
                String rok = rs.getString("rok");
                String rokz = rs.getString("rok_zakupu");
                Integer pojemność = rs.getInt("pojemnosc");
                Integer przebieg = rs.getInt("przebieg");
                String rejestracja = rs.getString("nrrejestracyjny");
                String ubezpieczony = rs.getString("ubezpieczenie");
                String uszkodzony = rs.getString("uszkodzony");
                String zdjecie = rs.getString("zdjecie");
                lll.add(new lista2(id, marka, model, rok, rokz, pojemność, przebieg, rejestracja, ubezpieczony, uszkodzony, zdjecie));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<dokumenty_kierownictwo> baza6(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer idw = rs.getInt("id_raportu");
                Integer idp = rs.getInt("identyfikator");
                String tytul = rs.getString("nazwa");
                String rokz = rs.getString("data_czas");
                lll.add(new dokumenty_kierownictwo(idw, idp, tytul, rokz));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<produkty> baza7(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer id = rs.getInt("id_towaru");
                String produkt = rs.getString("nzwa_produktu");
                String nazwa = rs.getString("nazwa");
                Integer liczba = rs.getInt("liczba_sztuk_na_magazynie");
                Integer ocena = rs.getInt("ocena");
                String cena = rs.getString("cena");
                lll.add(new produkty(id, produkt, nazwa, liczba, ocena, cena));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<klienci> baza8(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer id = rs.getInt("id_regionu");
                String przedstawiciel = rs.getString("przedstawiciel");
                String firma = rs.getString("firma");
                String adres = rs.getString("adres");
                Integer liczbaZamowien = rs.getInt("liczba_zamowien");
                String poziom = rs.getString("poziom");
                String kodPocztowy = rs.getString("kod_pocztowy");
                String miejscowosc = rs.getString("miejscowosc");
                String telefon = rs.getString("telefon");
                String email = rs.getString("email");
                Integer numer = rs.getInt("numer");
                lll.add(new klienci(id, przedstawiciel, firma, adres, liczbaZamowien, poziom, kodPocztowy, miejscowosc, telefon, email, numer));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<zamowienia> baza9(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer id = rs.getInt("id_zamowienia");
                String firma = rs.getString("firma");
                String kwota = rs.getString("kwota");
                String status = rs.getString("status");
                String poziom = rs.getString("poziom");
                String dataCzas = rs.getString("data_czas");
                Integer liczbaProduktow = rs.getInt("liczba_produktow");
                String produkty = rs.getString("produkty");
                Integer numerZamuwienia = rs.getInt("numer_zamuwienia");
                Boolean plikTakNie = rs.getBoolean("plik_tak_nie");
                lll.add(new zamowienia(id, firma, kwota, status, poziom, dataCzas, liczbaProduktow, produkty, numerZamuwienia, plikTakNie));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<firmy> baza10(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer id = rs.getInt("id_regionu");
                String nazwa = rs.getString("firma");
                String adres = rs.getString("adres");
                String kod = rs.getString("kod_pocztowy");
                String miejscowosc = rs.getString("miejscowosc");
                lll.add(new firmy(id, nazwa, adres, kod, miejscowosc));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(brygadzista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }

    public List<dokumenty_pojazdy> baza11(String sql) {
        List lll = new LinkedList();
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = (sql);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                Integer id = rs.getInt("id_raportu_pojazdu");
                Integer id2 = rs.getInt("id_pojazdu");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String tytul = rs.getString("tytul");
                String data_godzina = rs.getString("data_godzina");
                String plikhtml = rs.getString("plikhtml");
                lll.add(new dokumenty_pojazdy(id, id2, imie, nazwisko, tytul, data_godzina, plikhtml));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lll;
    }
}
