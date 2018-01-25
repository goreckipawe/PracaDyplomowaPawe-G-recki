
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import javafx.stage.FileChooser;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Paweł on 2017-10-15.
 */
public class logika {

    private static String sterownik = "org.sqlite.JDBC";
    private static String baza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private static Connection polaczenie;
    private static Statement st;
    private static ResultSet rs;
    
    public static String kodowanie(String haslo){
    String wynik = null;

    if(null == haslo) return null;

    try {

        MessageDigest szyfr = MessageDigest.getInstance("MD5");

        szyfr.update(haslo.getBytes(), 0, haslo.length());
        
        wynik = new BigInteger(1, szyfr.digest()).toString(16);

    } catch (NoSuchAlgorithmException e) {

        e.printStackTrace();
    }
    return wynik;
}
    public String drogaDoPliku(){
        String droga = "";
        FileChooser wybieracz = new FileChooser();
        FileChooser.ExtensionFilter rodzaj = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter rodzaj2 = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        FileChooser.ExtensionFilter rodzaj3 = new FileChooser.ExtensionFilter("DOCX files (*.docx)", "*.docx");
        wybieracz.getExtensionFilters().add(rodzaj);
        wybieracz.getExtensionFilters().add(rodzaj2);
        wybieracz.getExtensionFilters().add(rodzaj3);

        String domowy = System.getProperty("user.home");
        File plik = new File(domowy);
        if(!plik.canRead()) {
            plik = new File("c:/");
        }
        wybieracz.setInitialDirectory(plik);

        File plik2 = wybieracz.showOpenDialog(null);
        String drogaplik;
        if(plik2 != null) {
            drogaplik = plik2.getPath();
            droga = drogaplik;
        } else {
            drogaplik = null;
        }
        return droga;
    }
    public void pobierzWiadomosc(String x){
        PdfWriter pisarz = null;
        FileChooser wybieracz = new FileChooser();
        FileChooser.ExtensionFilter rodzaj = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        wybieracz.getExtensionFilters().add(rodzaj);
        String domowy = System.getProperty("user.home");
        File plik = new File(domowy);
        if (!plik.canRead()) {
            plik = new File("c:/");
        }
        wybieracz.setInitialDirectory(plik);
        wybieracz.setTitle("Zapis raportu");
        File plik2 = wybieracz.showSaveDialog(null);
        if (plik2 != null) {
            try {
                Document pdf2 = new Document();
                try {
                    pisarz = PdfWriter.getInstance(pdf2, new FileOutputStream(plik2));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                pdf2.open();

                XMLWorkerHelper parserhtml = XMLWorkerHelper.getInstance();
                InputStream isp = new ByteArrayInputStream(x.getBytes(StandardCharsets.UTF_8));
                parserhtml.parseXHtml(pisarz, pdf2, isp, Charset.forName("UTF-8"));
                pdf2.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public void wiadomoscWysylanie2(Boolean czyZalocznik,String drogaDoPliku,String pracownik,String pracownikn,String tytul,String wiadomosc,String tabela){

        Connection c;
        LocalDateTime dzisiaj = LocalDateTime.now();
        DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        int dl;
        String zapytanie;
        PreparedStatement ps;
        String z = "";

        if (czyZalocznik == true) {
            z = "1";
        } else {
            z = "0";
        }
        try {
            Class.forName(sterownik);
            c = DriverManager.getConnection(String.valueOf(baza));
            if (!(drogaDoPliku.equals(""))) {
                File plik = new File(drogaDoPliku);
                FileInputStream fis = new FileInputStream(plik);
                dl = (int) plik.length();
                zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,?,?,?)");
                ps = c.prepareStatement(zapytanie);
                ps.setString(1, pracownik);
                ps.setString(2, pracownikn);
                ps.setString(3, tytul);
                ps.setString(4, dzisiaj.format(formatd));
                ps.setString(5, wiadomosc);
                ps.setString(6, "0");
                ps.setString(7, z);
                ps.setBinaryStream(8, fis, dl);
            }else{
                zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,?,?,null)");
                ps = c.prepareStatement(zapytanie);
                ps.setString(1, pracownik);
                ps.setString(2, pracownikn);
                ps.setString(3, tytul);
                ps.setString(4, dzisiaj.format(formatd));
                ps.setString(5, wiadomosc);
                ps.setString(6, "0");
                ps.setString(7, z);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    public void wiadomoscWysylanie(Boolean czyZalocznik,String drogaDoPliku,String pracownik,String pracownikn,String tytul,String wiadomosc,String tabela){

        Connection c;
        LocalDateTime dzisiaj = LocalDateTime.now();
        DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        int dl;
        String zapytanie;
        PreparedStatement ps;
        String z = "";

        if (czyZalocznik == true) {
            z = "1";
        } else {
            z = "0";
        }
        try {
            Class.forName(sterownik);
            c = DriverManager.getConnection(String.valueOf(baza));
            if (!(drogaDoPliku.equals(""))) {
                File plik = new File(drogaDoPliku);
                FileInputStream fis = new FileInputStream(plik);
                dl = (int) plik.length();
                zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,?,?,?)");
                ps = c.prepareStatement(zapytanie);
                ps.setString(1, pracownik);
                ps.setString(2, pracownikn);
                ps.setString(3, tytul);
                ps.setString(4, wiadomosc);
                ps.setString(5, dzisiaj.format(formatd));
                ps.setString(6, "0");
                ps.setString(7, z);
                ps.setBinaryStream(8, fis, dl);
            }else{
                zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,?,?,null)");
                ps = c.prepareStatement(zapytanie);
                ps.setString(1, pracownik);
                ps.setString(2, pracownikn);
                ps.setString(3, tytul);
                ps.setString(4, wiadomosc);
                ps.setString(5, dzisiaj.format(formatd));
                ps.setString(6, "0");
                ps.setString(7, z);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    public String drogaDoPliku2(){
        String droga = "";
        FileChooser wybieracz = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        wybieracz.getExtensionFilters().add(filter);

        String domowyk = System.getProperty("user.home");
        File plik = new File(domowyk);
        if(!plik.canRead()) {
            plik = new File("c:/");
        }
        wybieracz.setInitialDirectory(plik);

        File plik2 = wybieracz.showOpenDialog(null);
        String drogaplik;
        if(plik2 != null) {
            drogaplik = plik2.getPath();
            droga = drogaplik;
        } else {
            drogaplik = null;
        }
        return droga;
    }
    public String stanowisko(String stanowisko){
        String s = "";
        if(stanowisko.equals("Właściciel")){
            s = "1";
        }else if (stanowisko.equals("Księgowy")){
            s = "2";
        }else if (stanowisko.equals("Brygadzista")){
            s = "3";
        }else if (stanowisko.equals("Magazynier")){
            s = "4";
        }else if (stanowisko.equals("Pracownik")){
            s = "5";
        }else if (stanowisko.equals("Kierowca")){
            s = "6";
        }else {
            s = "";
        }
        return s;
    }
    public String dzial(String dział){
        String d = "";
        if(dział.equals("Kierownictwo")){
            d = "1";
        }else if (dział.equals("Księgowość")){
            d = "2";
        }else if (dział.equals("Magazyn")){
            d = "3";
        }else if (dział.equals("Brygadzista")){
            d = "4";
        }else if (dział.equals("Transport/Pracownicy")){
            d = "5";
        }else {
            d = "";
        }
        return d;
    }
    public String miesiac(int x){
        String m = "";
        if (x == 1){
            m = "Styczeń";
        }else if (x == 2){
            m = "Luty";
        }else if (x == 3){
            m = "Marzec";
        }else if (x == 4){
            m = "Kwiecień";
        }else if (x == 5){
            m = "Maj";
        }else if (x == 6){
            m = "Czerwiec";
        }else if (x == 7){
            m = "Lipiec";
        }else if (x == 8){
            m = "Sierpień";
        }else if (x == 9){
            m = "Wrzesień";
        }else if (x == 10){
            m = "Październik";
        }else if (x == 11){
            m = "Listopad";
        }else if (x == 12){
            m = "Grudzień";
        }
        return m;
    }
    public void dokumentPobierz(String id, String tabela){

        byte[] plikczesci;
        String zapytanie;
        String nazwa;

        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            zapytanie = "SELECT tytul,plik FROM "+tabela+" WHERE id_dokumentu=" + id;
            Statement k = polaczenie.createStatement();
            ResultSet rs = k.executeQuery(zapytanie);
            if (rs.next()) {
                nazwa = rs.getString(1);
                plikczesci = rs.getBytes(2);
                OutputStream plik=  new FileOutputStream(
                        "D://"+ nazwa +" DokumentPobrany.docx");
                plik.write(plikczesci);
                plik.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    public void dokumentUsuwanie(String id, String tabela){
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            PreparedStatement st = polaczenie.prepareStatement("DELETE FROM "+tabela+" WHERE id_dokumentu = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void raportKsiegowoscWysylanie(String drogaDoPliku,String pracownik,String tytul,String tabela){

        Connection polaczenie;
        LocalDateTime dzisiaj = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        int dlugosc;
        String zapytanie;
        PreparedStatement ps;

        try {
            File plik = new File(drogaDoPliku);
            FileInputStream plikis = new FileInputStream(plik);
            dlugosc = (int) plik.length();
            zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,null)");
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            ps = polaczenie.prepareStatement(zapytanie);
            ps.setString(1, pracownik);
            ps.setString(2, tytul);
            ps.setString(3, dzisiaj.format(format));
            ps.setString(4, "1");
            ps.setBinaryStream(5, plikis, dlugosc);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    public void raportKierownictwoWysylanie(String drogaDoPliku,String pracownik,String tytul,String tabela){

        Connection polaczenie;
        LocalDateTime dzisiaj = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        int dlugosc;
        String zapytanie;
        PreparedStatement ps;

        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            File plik = new File(drogaDoPliku);
            FileInputStream plikis = new FileInputStream(plik);
            dlugosc = (int) plik.length();
            zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?)");
            ps = polaczenie.prepareStatement(zapytanie);
            ps.setString(1, pracownik);
            ps.setString(2, tytul);
            ps.setString(3, dzisiaj.format(format));
            ps.setBinaryStream(4, plikis, dlugosc);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    public void raportBrygadzistaWysylanie(String drogaDoPliku,String pracownik,String tytul,String tabela){

        Connection polaczenie;
        LocalDateTime dzisiaj = LocalDateTime.now();
        DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        int len;
        String zapytanie;
        PreparedStatement ps;

        try {
            File file = new File(drogaDoPliku);
            FileInputStream fis = new FileInputStream(file);
            len = (int) file.length();
            zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,null)");
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            ps = polaczenie.prepareStatement(zapytanie);
            ps.setString(1, pracownik);
            ps.setString(2, tytul);
            ps.setString(3, dzisiaj.format(formatd));
            ps.setString(4, "1");
            ps.setBinaryStream(5, fis, len);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    public void wiadomoscWysylanie3(Boolean czyZalocznik, String drogaDoPliku, String pracownik, String pracownikn, String tytul, String wiadomosc, String tabela, String nazwa){

        Connection polaczenie;
        LocalDateTime dzisiaj = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        int dlugosc;
        String zapytanie;
        PreparedStatement ps;
        String z = "";

        if (czyZalocznik == true) {
            z = "1";
        } else {
            z = "0";
        }
        if (!(drogaDoPliku.equals(""))) {
            File plik = new File(drogaDoPliku);
            FileInputStream plikis = null;
            FTPClient c = new FTPClient();

            try {
                c.connect("ftp.pracai.hol.es");
                c.login("u224299645", "kajak66");

                String pliknazwa = plik.toString();
                plikis = new FileInputStream(pliknazwa);

                c.storeFile(nazwa, plikis);
                c.logout();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (plikis != null) {
                        plikis.close();
                    }
                    c.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!(drogaDoPliku.equals(""))) {
            try {
                Class.forName(sterownik);
                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                File plik = new File(drogaDoPliku);
                FileInputStream plikis = new FileInputStream(plik);
                dlugosc = (int) plik.length();
                zapytanie = ("INSERT INTO dokumenty_kierownictwo VALUES(null,?,?,?,?,?)");
                ps = polaczenie.prepareStatement(zapytanie);
                ps.setString(1, tytul);
                ps.setString(2, dzisiaj.format(format));
                ps.setString(3, "1");
                ps.setString(4, nazwa);
                ps.setString(5, wiadomosc);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            if (!(drogaDoPliku.equals(""))) {
                File plik = new File(drogaDoPliku);
                FileInputStream plikis = new FileInputStream(plik);
                dlugosc = (int) plik.length();
                zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,?,?,?,?)");
                ps = polaczenie.prepareStatement(zapytanie);
                ps.setString(1, pracownik);
                ps.setString(2, pracownikn);
                ps.setString(3, tytul);
                ps.setString(4, wiadomosc);
                ps.setString(5, dzisiaj.format(format));
                ps.setString(6, "0");
                ps.setString(7, z);
                ps.setString(8, nazwa);
                ps.setBinaryStream(9, plikis, dlugosc);
            }else{
                zapytanie = ("INSERT INTO " + tabela + " VALUES(null,?,?,?,?,?,?,?,null,null)");
                ps = polaczenie.prepareStatement(zapytanie);
                ps.setString(1, pracownik);
                ps.setString(2, pracownikn);
                ps.setString(3, tytul);
                ps.setString(4, wiadomosc);
                ps.setString(5, dzisiaj.format(format));
                ps.setString(6, "0");
                ps.setString(7, z);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    public void dokumentGenerujPDF(String html){
        PdfWriter pisarz = null;
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fc.getExtensionFilters().add(filter);
        String uds = System.getProperty("user.home");
        File ud = new File(uds);
        if (!ud.canRead()) {
            ud = new File("c:/");
        }
        fc.setInitialDirectory(ud);
        fc.setTitle("Zapis raportu");
        File plik = fc.showSaveDialog(null);
        if (plik != null) {
            try {
                Document pdf2 = new Document();
                try {
                    pisarz = PdfWriter.getInstance(pdf2, new FileOutputStream(plik));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                pdf2.open();
                String str = html;

                XMLWorkerHelper xmlwh = XMLWorkerHelper.getInstance();
                InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
                xmlwh.parseXHtml(pisarz, pdf2, is, Charset.forName("UTF-8"));
                pdf2.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    };
    public String numerLosowanie(){
        String numer = "";
        Boolean istnieje = false;
        do {
            for (int i = 1; i < 10; i++) {
                int randomnumer = ThreadLocalRandom.current().nextInt(1, 9 + 1);
                numer = numer + String.valueOf(randomnumer);
            }
            try {
                Class.forName(sterownik);
                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement s = polaczenie.createStatement();
                ResultSet rs = s.executeQuery("SELECT numer_zamuwienia FROM zamowienia");
                while (rs.next()) {
                    if (numer.equals(rs.getString(1))) {
                        istnieje = true;
                        numer = "";
                    }
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }while (istnieje == true);

        return numer;
    }
    public String ststusSet(String x){
        String liczba = "";
        if (x.equals("W przygotowaniu")){
            liczba = "1";
        }else if (x.equals("Wysłane")){
            liczba = "2";
        }else if (x.equals("W drodze")){
            liczba = "3";
        }else if (x.equals("Dostarczone")){
            liczba = "4";
        }else if (x.equals("Oczekiwanie")){
            liczba = "5";
        }else if (x.equals("Zapłacone")){
            liczba = "6";
        }else if (x.equals("Niezapłacone")){
            liczba = "7";
        }
        return liczba;
    }
    public void dokumentPobierz2(String id){

        byte[] dane;
        String zapytanie;
        String nazwa;

        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            zapytanie = "SELECT tytul,plik FROM zamowienia WHERE id_zamowienia=" + id;
            Statement s = polaczenie.createStatement();
            ResultSet rs = s.executeQuery(zapytanie);
            if (rs.next()) {
                nazwa = rs.getString(1);
                dane = rs.getBytes(2);
                OutputStream plik=  new FileOutputStream(
                        "D://"+ nazwa +" DokumentPobrany.pdf");
                plik.write(dane);
                plik.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    public void towarUsuwanie(String id){
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            PreparedStatement st = polaczenie.prepareStatement("DELETE FROM szczegoly_produktu WHERE id_towaru = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void klientUsuwanie(String id){
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            PreparedStatement st = polaczenie.prepareStatement("DELETE FROM regiony_dostaw WHERE id_regionu = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void pobierzZalacznik(String x, String tabela){
        byte[] dane;
        String nazwa;
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = ("Select tytul, plik From " + tabela + " WHERE id_wiadomosc = " + x);
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                nazwa = rs.getString(1);
                dane = rs.getBytes(2);
                OutputStream plik = new FileOutputStream("D://" + nazwa + ".pdf");
                plik.write(dane);
                plik.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    public void wiadomoscUsuwanie(String id, String tabela){
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            PreparedStatement st = polaczenie.prepareStatement("DELETE FROM " + tabela + " WHERE id_wiadomosc = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String kwartalObecny(int x){
        String k = "";
        if (x == 1 || x == 2 || x == 3){
            k = "I kwartał";
        }else if (x == 4 || x == 5 || x == 6){
            k = "II kwartał";
        }else if (x == 7 || x == 8 || x == 9){
            k = "III kwartał";
        }else if (x == 10 || x == 11 || x == 12){
            k = "IV kwartał";
        }
        return k;
    }

}