import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Paweł on 2016-05-25.
 */
public class magazynier implements Initializable {

    private static String baza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private static Connection polaczenie;
    private static Statement st;
    private static ResultSet rs;
    private static String sterownik = "org.sqlite.JDBC";
    private static String plik;
    private static String text;
    private static String sql;
    private static String sql2;
    private static String sql3;
    private static String sql4;
    private static String sql5;
    private static String sql7;
    private static PdfWriter pdfw;
    private static int liczba_towarow = 0;
    private static String id_regionu;
    int wstep = 0;
    int wstep2 = 0;
    double sumaogulna = 0;
    private static String droga_do_plikuz;
    private static String droga_do_plikuz2;
    private static String droga_do_pliku;
    private static String identyfikator = "";
    private static String haslodane = "";
    private static String idwiadomosc;
    private static String idpracownika = "1";
    private static String poziom = "1";
    private static String produkty;
    private static String idpracownikan = "3";
    private static String identyfikator2 = "";
    private static Boolean zalacznikplik = false;
    private static List<String> dok=new ArrayList<String>();
    private static List<String> wdu=new ArrayList<String>();
    private static List<String> numerk=new ArrayList<String>();
    private static TextArea zamowienia=new TextArea();
    private static TextArea raporttext=new TextArea();
    private static List<listaZaowienRaport> listadoc=new ArrayList<listaZaowienRaport>();

    private static ObservableList<String> lista = FXCollections.observableArrayList(
            "Płayta główna", "Dysk", "Pamięć RAM", "Karta graficzna", "Procesor"
    );
    private static ObservableList<String> lista2 = FXCollections.observableArrayList(
            "Bardzo dobry", "Dobry", "Dostateczny", "Zły", "Bardzo zły"
    );
    private static ObservableList<String> lista3 = FXCollections.observableArrayList(
            "Raport", "Pracownicy wypłaty", "Zyski firmy", "Straty firmy", "Inny"
    );
    private static ObservableList<String> lista4 = FXCollections.observableArrayList(
            "Bardzo dobry", "Dobry", "Dostateczny", "Zły", "Bardzo zły"
    );
    private static ObservableList<String> lista7 = FXCollections.observableArrayList(
            "W przygotowaniu", "Wysłane", "W drodze", "Dostarczone", "Oczekiwanie", "Zapłacone", "Niezapłacone"
    );
    private static ObservableList<String> lista5 = FXCollections.observableArrayList();
    private static ObservableList<String> lista6 = FXCollections.observableArrayList();
    private static ObservableList<String> lista8 = FXCollections.observableArrayList(
            "Kierownictwo", "Księgowość", "Magazyn", "Brygadzista", "Transport/Pracownicy"
    );
    private static ObservableList<String> pracownicydzialu = FXCollections.observableArrayList();

    private static LocalDateTime dzisiaj = LocalDateTime.now();
    private static DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    private static komunikaty kom = new komunikaty();
    private static logika log = new logika();
    private static baza baz = new baza();

    @FXML
    private JFXTextArea raport;
    @FXML
    private JFXButton dane2;
    @FXML
    private JFXButton dodaj;
    @FXML
    private JFXTextField haslo;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField tw;
    @FXML
    private JFXTextField dtw;
    @FXML
    private JFXTextField autor_wiadomosc;
    @FXML
    private HTMLEditor htmlraport;
    @FXML
    private HTMLEditor wiadomość2;
    @FXML
    private WebView dokument_podglad;
    @FXML
    private WebView eraport2;
    @FXML
    private WebView eraport3;
    @FXML
    private WebView strony;
    @FXML
    private WebView web;
    @FXML
    private JFXCheckBox czy_html;
    @FXML
    private JFXTextField nazwad;
    @FXML
    private JFXTextField liczbad;
    @FXML
    private JFXTextField cenad;
    @FXML
    private JFXTextField cenad2;
    @FXML
    private JFXTextField nazwa;
    @FXML
    private JFXTextField cena;
    @FXML
    private JFXTextField status;
    @FXML
    private JFXTextField status_zamawiającego;
    @FXML
    private JFXTextField data_zamuwienia;
    @FXML
    private JFXTextField uwagi;
    @FXML
    private JFXTextField tytul_wiadomosc;
    @FXML
    private JFXTextField tytul_dokumentu2;
    @FXML
    private JFXTextField droga_zalacznik;
    @FXML
    private JFXTextField tytul_dokumentu;
    @FXML
    private JFXTextField droga_zdjecie;
    @FXML
    private JFXTextField osoba;
    @FXML
    private JFXTextField firma;
    @FXML
    private JFXTextField adres;
    @FXML
    private JFXTextField kod;
    @FXML
    private JFXTextField miejscowosc;
    @FXML
    private JFXTextField telefon;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField numer;
    @FXML
    private JFXTextField wiarygodnosc_poziom;
    @FXML
    private JFXTextField cena_produktu2;
    @FXML
    private JFXTextField zamowienie_klient_nazwa;
    @FXML
    private JFXTextField liczba_sztuk;
    @FXML
    private JFXTextArea zamowienie_demo;
    @FXML
    private ComboBox typ_produktu;
    @FXML
    private ComboBox ocena_produktu;
    @FXML
    private ComboBox rodzaj_dokumentu;
    @FXML
    private ComboBox rodzaj_dokumentu2;
    @FXML
    private ComboBox zaufanie;
    @FXML
    private ComboBox firmabox;
    @FXML
    private ComboBox kategoriabox;
    @FXML
    private ComboBox produktbox;
    @FXML
    private ImageView zdjecie;
    @FXML
    private JFXButton uraport;
    @FXML
    private JFXButton wiadomość3;
    @FXML
    private JFXButton wiadomość4;
    @FXML
    private JFXButton wraport2;
    @FXML
    private JFXButton craport;
    @FXML
    private JFXButton zraport;
    @FXML
    private JFXButton podglad2;
    @FXML
    private JFXButton wstecz;
    @FXML
    private JFXButton dodaj_produkt;
    @FXML
    private JFXButton dodaj_zdjecie;
    @FXML
    private JFXButton generuj_pdf_z_raport;
    @FXML
    private JFXButton usun_zaznaczone_dokumenty;
    @FXML
    private JFXButton wraport3;
    @FXML
    private JFXButton dokumentpdf1;
    @FXML
    private JFXButton dokumentpdf2;
    @FXML
    private JFXButton zalacznik;
    @FXML
    private JFXButton nowy_produkt;
    @FXML
    private JFXButton zamowienie_dodaj;
    @FXML
    private JFXButton zamowienie_suma;
    @FXML
    private JFXButton dodaj_raport;
    @FXML
    private JFXButton wiadomosc_usun;
    @FXML
    private HTMLEditor eraport;
    @FXML
    private HTMLEditor ezamowienie;
    @FXML
    private MenuItem zamknij;
    @FXML
    private MenuItem opis;
    @FXML
    private MenuItem autorzy;
    @FXML
    private MenuItem wersja;
    @FXML
    private TableView<produkty> tabela;
    @FXML
    private TableColumn kolumna1;
    @FXML
    private TableColumn kolumna2;
    @FXML
    private TableColumn kolumna3;
    @FXML
    private TableColumn kolumna4;
    @FXML
    private TableColumn kolumna5;
    @FXML
    private TableColumn kolumna6;
    @FXML
    private TableColumn kolumna7;
    @FXML
    private TableColumn kolumna8;
    @FXML
    private TableView<zamowienia> tabela2;
    @FXML
    private TableColumn kolumna9;
    @FXML
    private TableColumn kolumna10;
    @FXML
    private TableColumn kolumna11;
    @FXML
    private TableView<zamowienia> tabela3;
    @FXML
    private TableColumn kolumna12;
    @FXML
    private TableColumn kolumna13;
    @FXML
    private TableColumn kolumna14;
    @FXML
    private TableColumn kolumna15;
    @FXML
    private TableColumn kolumna16;
    @FXML
    private TableColumn kolumna17;
    @FXML
    private TableColumn kolumna18;
    @FXML
    private TableColumn kolumna19;
    @FXML
    private TableColumn kolumna20;
    @FXML
    private TableColumn kolumna21;
    @FXML
    private TableColumn kolumna22;
    @FXML
    private TableView<klienci> tabela4;
    @FXML
    private TableColumn kolumna23;
    @FXML
    private TableColumn kolumna24;
    @FXML
    private TableColumn kolumna25;
    @FXML
    private TableColumn kolumna26;
    @FXML
    private TableColumn kolumna27;
    @FXML
    private TableColumn kolumna28;
    @FXML
    private TableColumn kolumna29;
    @FXML
    private TableColumn kolumna30;
    @FXML
    private TableColumn kolumna31;
    @FXML
    private TableColumn kolumna32;
    @FXML
    private TableColumn kolumna45;
    @FXML
    private TableColumn kolumna46;
    @FXML
    private TableView<wiadomosc> tabela5;
    @FXML
    private TableColumn kolumna33;
    @FXML
    private TableColumn kolumna34;
    @FXML
    private TableColumn kolumna35;
    @FXML
    private TableColumn kolumna36;
    @FXML
    private TableColumn kolumna37;
    @FXML
    private TableView<dokumenty_ksiegowosic> tabela6;
    @FXML
    private TableColumn kolumna38;
    @FXML
    private TableColumn kolumna39;
    @FXML
    private TableColumn kolumna40;
    @FXML
    private TableColumn kolumna41;
    @FXML
    private TableColumn kolumna42;
    @FXML
    private TableColumn kolumna43;
    @FXML
    private TableColumn kolumna44;
    @FXML
    private Label limię;
    @FXML
    private Label lnazwisko;
    @FXML
    private Label lpesel;
    @FXML
    private Label ltelefon;
    @FXML
    private Label ladres;
    @FXML
    private Label ldzial;
    @FXML
    private Label lstanowisko;
    @FXML
    private Label lemail;
    @FXML
    private Label lidentyfikator;
    @FXML
    private Label lhaslo;
    @FXML
    private Label ldu;
    @FXML
    private Label lduw;
    @FXML
    private JFXButton wyslij_raport_do_kierownictwa;
    @FXML
    private JFXButton wyslij_wiadomosc_do_ksiegowosc;
    @FXML
    private JFXButton wyslij_raport_do_ksiegowosc;
    @FXML
    private ComboBox dzialbox;
    @FXML
    private ComboBox pracownikbox;
    @FXML
    private JFXTextField imie_nazwisko;
    @FXML
    private TableView<wiadomosc_dzialy> tabela7;
    @FXML
    private TableColumn kolumna47;
    @FXML
    private TableColumn kolumna48;
    @FXML
    private TableColumn kolumna49;
    @FXML
    private TableColumn kolumna50;
    @FXML
    private JFXTextField tw2;
    @FXML
    private JFXTextField dtw2;
    @FXML
    private WebView strony2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tabela.setTableMenuButtonVisible(true);
        tabela2.setTableMenuButtonVisible(true);
        tabela3.setTableMenuButtonVisible(true);
        tabela4.setTableMenuButtonVisible(true);
        tabela5.setTableMenuButtonVisible(true);
        tabela6.setTableMenuButtonVisible(true);
        tabela7.setTableMenuButtonVisible(true);

        typ_produktu.setItems(lista);
        ocena_produktu.setItems(lista2);
        rodzaj_dokumentu.setItems(lista3);
        rodzaj_dokumentu2.setItems(lista3);
        zaufanie.setItems(lista4);
        kategoriabox.setItems(lista);
        dzialbox.setItems(lista8);

        pokazBazeMagazynu();
        pokazBazeDokumentuwMagazyniera();
        pokazBazeKlientow();
        pokazBazeWiadomosc();
        pokazBazeZamowien();
        listaKlientow();
        pokazBazeWiadomoscDzialy();

        String url = getClass().getResource("/web/viewer.html").toExternalForm();

        WebEngine silnik = dokument_podglad.getEngine();
        silnik.setJavaScriptEnabled(true);
        silnik.load(url);

        WebEngine silnik2 = eraport2.getEngine();
        silnik2.setJavaScriptEnabled(true);
        silnik2.load(url);

        WebEngine silnik3 = eraport3.getEngine();
        silnik3.setJavaScriptEnabled(true);
        silnik3.load(url);

        login.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        liczbad.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        cenad.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        cenad2.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        telefon.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        numer.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        liczba_sztuk.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        osoba.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });

        miejscowosc.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });

        autorzy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kom.komAutor();
            }
        });

        wersja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kom.komWersjaProgramu();
            }
        });

        zamknij.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        opis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kom.komPomocMagazynier();
            }
        });

        wstecz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setText("","","");
                Parent opcje = null;
                try {
                    opcje = FXMLLoader.load(getClass().getResource("logowanie1.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scenapocji = new Scene(opcje);
                Stage estrada = (Stage) ((Node) event.getSource()).getScene().getWindow();
                estrada.hide();
                estrada.setScene(scenapocji);
                estrada.show();
            }
        });

        kategoriabox.setOnAction((e) -> {
            lista5.clear();
            String zapytanie = "";
            if(kategoriabox.getSelectionModel().getSelectedItem() == "Płayta główna"){
                zapytanie = "SELECT nazwa FROM szczegoly_produktu WHERE id_produktu = 1";
            }else if (kategoriabox.getSelectionModel().getSelectedItem() == "Dysk"){
                zapytanie = "SELECT nazwa FROM szczegoly_produktu WHERE id_produktu = 2";
            }else if (kategoriabox.getSelectionModel().getSelectedItem() == "Pamięć RAM"){
                zapytanie = "SELECT nazwa FROM szczegoly_produktu WHERE id_produktu = 3";
            }else if (kategoriabox.getSelectionModel().getSelectedItem() == "Karta graficzna"){
                zapytanie = "SELECT nazwa FROM szczegoly_produktu WHERE id_produktu = 4";
            }else if (kategoriabox.getSelectionModel().getSelectedItem() == "Procesor"){
                zapytanie = "SELECT nazwa FROM szczegoly_produktu WHERE id_produktu = 5";
            }
            try {
                Class.forName(sterownik);
                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement st = polaczenie.createStatement();
                ResultSet rs = st.executeQuery(zapytanie);
                while(rs.next()) {
                    lista5.add(rs.getString(1));
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            produktbox.setItems(lista5);
        });

        produktbox.setOnAction((e) -> {
            try {
                Class.forName(sterownik);
                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement st = polaczenie.createStatement();
                ResultSet rs = st.executeQuery("SELECT cena FROM szczegoly_produktu WHERE nazwa = '"+produktbox.getSelectionModel().getSelectedItem()+"'");
                while(rs.next()) {
                    cena_produktu2.setText(rs.getString(1));
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        firmabox.setOnAction((e) -> {
            try {
                Class.forName(sterownik);
                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement st = polaczenie.createStatement();
                ResultSet rs = st.executeQuery("SELECT regiony_dostaw.id_regionu, regiony_dostaw.numer, wiarygodnosc.poziom, regiony_dostaw.id_poziomu FROM regiony_dostaw JOIN wiarygodnosc ON regiony_dostaw.id_poziomu=wiarygodnosc.id_poziomu WHERE firma= '"+firmabox.getSelectionModel().getSelectedItem()+"'");
                while(rs.next()) {
                    id_regionu = rs.getString(1);
                    zamowienie_klient_nazwa.setText(rs.getString(2));
                    wiarygodnosc_poziom.setText(rs.getString(3));
                    poziom = rs.getString(4);
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        nowy_produkt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(firmabox.getValue() == null)){
                    if (!(kategoriabox.getValue() == null)){
                        if (!(produktbox.getValue() == null)){
                            if (!(zamowienie_klient_nazwa.getText().equals("") || wiarygodnosc_poziom.getText().equals("") || cena_produktu2.getText().equals("") || liczba_sztuk.getText().equals(""))){
                                if (wstep == 0) {
                                    zamowienia.appendText("<p>Zamawiający: " + firmabox.getValue() + "</p>");
                                    zamowienia.appendText("<p>Numer klienta: " + zamowienie_klient_nazwa.getText() + "</p>");
                                    zamowienia.appendText("<p>Poziom wiarygodności: " + wiarygodnosc_poziom.getText() + "</p>");
                                    zamowienia.appendText("<p>Data: " + dzisiaj.format(formatd) + "</p>");
                                    zamowienia.appendText("<p>Wiarygodność: " + wiarygodnosc_poziom.getText() + "</p>");
                                    wstep = 1;
                                }
                                int liczba = Integer.parseInt(liczba_sztuk.getText());
                                double cena_sztuka = Double.parseDouble(cena_produktu2.getText());
                                double suma = liczba*cena_sztuka;
                                sumaogulna = sumaogulna + suma;
                                zamowienia.appendText("<p>Produkt: " + produktbox.getValue() + " Cena za sztukę: " + cena_produktu2.getText() + " zł Liczba sztuk: " + liczba_sztuk.getText() + " Cena łączna: " + suma + " zł </p>");
                                zamowienie_demo.appendText("Produkt: " + produktbox.getValue() + " Cena za sztukę: " + cena_produktu2.getText() + " zł Liczba sztuk: " + liczba_sztuk.getText() + " Cena łączna: " + suma + " zł\n");
                                produkty = produkty + "Produkt: " + produktbox.getValue() + " Cena za sztukę: " + cena_produktu2.getText() + " zł Liczba sztuk: " + liczba_sztuk.getText() + " Cena łączna: " + suma + " zł\n";
                                liczba_towarow++;
                            }else{
                                kom.komWszystkieDane();
                            }
                        }else{
                            kom.komProduktBrak();
                        }
                    }else{
                        kom.komKategoriaBrak();
                    }
                }else{
                    kom.komKlientBrak();
                }
            }
        });

        zamowienie_dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(firmabox.getValue() == null)){
                    if (!(kategoriabox.getValue() == null)){
                        if (!(produktbox.getValue() == null)){
                            if (!(zamowienie_klient_nazwa.getText().equals("") && wiarygodnosc_poziom.getText().equals("") && cena_produktu2.getText().equals("") && liczba_sztuk.getText().equals(""))){
                                if (!(ezamowienie.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || ezamowienie.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || ezamowienie.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>") && produkty.equals(""))) {
                                    String numerz = log.numerLosowanie();
                                    String droga = "D://Zamówienie numer " + numerz + ".pdf";
                                    try {
                                        Document pdf2 = new Document();
                                        OutputStream targetFile = new FileOutputStream(droga);
                                        try {
                                            pdfw = PdfWriter.getInstance(pdf2, targetFile);
                                        } catch (DocumentException e) {
                                            e.printStackTrace();
                                        }
                                        pdf2.open();
                                        String str = ezamowienie.getHtmlText();

                                        XMLWorkerHelper xmlwh = XMLWorkerHelper.getInstance();
                                        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
                                        xmlwh.parseXHtml(pdfw, pdf2, is, Charset.forName("UTF-8"));
                                        pdf2.close();
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    int dlugosc;
                                    String zapytanie;
                                    PreparedStatement ps;

                                    try {
                                        Class.forName(sterownik);
                                        polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                        File plik = new File(droga);
                                        FileInputStream pfis = new FileInputStream(plik);
                                        dlugosc = (int) plik.length();
                                        zapytanie = ("INSERT INTO zamowienia VALUES(null,?,?,?,?,?,?,?,?,?,?)");
                                        ps = polaczenie.prepareStatement(zapytanie);
                                        ps.setString(1, id_regionu);
                                        ps.setString(2, String.valueOf(sumaogulna));
                                        ps.setString(3, "1");
                                        ps.setString(4, poziom);
                                        ps.setString(5, dzisiaj.format(formatd));
                                        ps.setString(6, String.valueOf(liczba_towarow));
                                        ps.setString(7, produkty);
                                        ps.setString(8, numerz);
                                        ps.setString(9, "1");
                                        ps.setBinaryStream(10, pfis, dlugosc);
                                        ps.executeUpdate();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    kom.komZamowieniePuste();
                                }
                            }else{
                                kom.komWszystkieDane();
                            }
                        }else{
                            kom.komProduktBrak();
                        }
                    }else{
                        kom.komKategoriaBrak();
                    }
                }else{
                    kom.komKlientBrak();
                }
            }
        });

        zamowienie_suma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                zamowienia.appendText("<p>___________________________________________________________________________</p>");
                zamowienia.appendText("<p>Suma łączna: " + sumaogulna + " zł </p>");
                ezamowienie.setHtmlText(zamowienia.getText());
            }
        });

        zraport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu.getText().equals(""))) {
                    if (!(rodzaj_dokumentu.getValue() == null)) {
                        if (!(eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
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
                            pdfw = PdfWriter.getInstance(pdf2, new FileOutputStream(plik));
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                        pdf2.open();
                        String str = eraport.getHtmlText();

                        XMLWorkerHelper xmlwh = XMLWorkerHelper.getInstance();
                        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
                        xmlwh.parseXHtml(pdfw, pdf2, is, Charset.forName("UTF-8"));
                        pdf2.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                        }else {
                            kom.komBrakTekstuHtml();
                        }
                    }else {
                        kom.komDokumentBrakRodzajuDokumetu();
                    }
                }else {
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        tabela7.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                wiadomosc_dzialy wiadomosc = tabela7.getSelectionModel().getSelectedItem();

                tw2.setText(wiadomosc.getTytul());
                dtw2.setText(wiadomosc.getData_godzina());
                strony2.getEngine().loadContent(wiadomosc.getTresc());
                if (wiadomosc.getTyp_przeczytana_nieprzeczytana() == false) {
                    try {
                        Class.forName(sterownik);
                        polaczenie = DriverManager.getConnection(baza);
                        PreparedStatement ps = polaczenie.prepareStatement("UPDATE wiadomosci_dzialy SET typ_przeczytana_nieprzeczytana = ? WHERE id_wiadomosc = ?");
                        ps.setString(1, "1");
                        ps.setString(2, String.valueOf(wiadomosc.getId_wiadomosc()));
                        ps.executeUpdate();
                        pokazBazeWiadomoscDzialy();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        tabela5.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                wiadomosc wiadomosc = tabela5.getSelectionModel().getSelectedItem();
                idwiadomosc = wiadomosc.getId_wiadomosc().toString();

                tw.setText(wiadomosc.getTytul());
                dtw.setText(wiadomosc.getData_godzina());
                autor_wiadomosc.setText(wiadomosc.getImie() + " " + wiadomosc.getNazwisko());
                strony.getEngine().loadContent(wiadomosc.getTresc());
            }
        });

        podglad2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                byte[] dane = new byte[0];
                try {
                    dane = FileUtils.readFileToByteArray(new File(log.drogaDoPliku2()));
                } catch (IOException e) {
                }
                String postac64 = Base64.getEncoder().encodeToString(dane);
                silnik.executeScript("openFileFromBase64('" + postac64 + "')");
            }
        });

        usun_zaznaczone_dokumenty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dok.isEmpty())) {
                    for (String d : dok) {
                        log.dokumentUsuwanie(d,"dokumenty_magazynier");
                    }
                    pokazBazeDokumentuwMagazyniera();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        tabela6.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                dokumenty_ksiegowosic dokument = tabela6.getSelectionModel().getSelectedItem();

                web.getEngine().loadContent(dokument.getPlikhtml());
            }
        });

        dokumentpdf1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                byte[] dane = new byte[0];
                try {
                    dane = FileUtils.readFileToByteArray(new File(log.drogaDoPliku2()));
                } catch (IOException e) {
                }
                String postac64 = Base64.getEncoder().encodeToString(dane);
                silnik2.executeScript("openFileFromBase64('" + postac64 + "')");
            }
        });

        dokumentpdf2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                byte[] dane = new byte[0];
                try {
                    dane = FileUtils.readFileToByteArray(new File(log.drogaDoPliku2()));
                } catch (IOException e) {
                }
                String postac64 = Base64.getEncoder().encodeToString(dane);
                silnik3.executeScript("openFileFromBase64('" + postac64 + "')");
            }
        });

        wiadomosc_usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(wdu.isEmpty())) {
                    for (String w : wdu) {
                        log.wiadomoscUsuwanie(w,"dokumenty_magazyniera");
                    }
                    pokazBazeWiadomosc();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        uraport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu.getText().equals(""))) {
                    if (!(rodzaj_dokumentu.getValue() == null)) {
                        if (!(raport.getText().equals(""))) {
                    droga_do_pliku = "C:\\" + tytul_dokumentu.getText() + ".docx";
                    XWPFDocument dokument = new XWPFDocument();
                    FileOutputStream pout = null;
                    try {
                        pout = new FileOutputStream(new File("C:\\" + tytul_dokumentu.getText() + ".docx"));
                        XWPFParagraph paragraf = dokument.createParagraph();
                        XWPFRun xwpfrun = paragraf.createRun();
                        xwpfrun.setText("Tytuł dokómętu: " + tytul_dokumentu.getText() + "\n Rodzaj dokumentu: " + rodzaj_dokumentu.getValue() + "\n Na dzin" + dzisiaj.format(formatd));
                        XWPFTable tabela = dokument.createTable();

                        XWPFTableRow tableRowOne = tabela.getRow(0);
                        tableRowOne.getCell(0).setText("Nazwa");
                        tableRowOne.addNewTableCell().setText("Cena (zł)");
                        tableRowOne.addNewTableCell().setText("Status przesyłki");
                        tableRowOne.addNewTableCell().setText("Statys zamawiającego");
                        tableRowOne.addNewTableCell().setText("Data zamówienia");
                        tableRowOne.addNewTableCell().setText("Uwagi");

                        for (listaZaowienRaport lz : listadoc) {
                            XWPFTableRow tableRowThree = tabela.createRow();
                            tableRowThree.getCell(0).setText(lz.getNazwa());
                            tableRowThree.getCell(1).setText(lz.getCena());
                            tableRowThree.getCell(2).setText(lz.getStatus());
                            tableRowThree.getCell(3).setText(lz.getStatus_zamawiającego());
                            tableRowThree.getCell(4).setText(lz.getData_zamuwienia());
                            tableRowThree.getCell(5).setText(lz.getUwagi());
                        }
                        dokument.write(pout);
                        pout.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        }else {
                            kom.komDokumentBrakTresc();
                        }
                    }else {
                        kom.komDokumentBrakRodzajuDokumetu();
                    }
                }else {
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        dodaj_raport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(nazwa.getText().equals("") && cena.getText().equals("") && status.getText().equals("") && status_zamawiającego.getText().equals("") && data_zamuwienia.getText().equals(""))) {
                    if (wstep2 == 0) {
                        raporttext.appendText("<p>Tytuł: " + tytul_dokumentu.getText() + "</p>");
                        raporttext.appendText("<p>Data utworzenia: " + dzisiaj.format(formatd) + "</p>");
                        raporttext.appendText("<p>Rodzaj dokumentu: " + rodzaj_dokumentu.getValue() + "</p>");
                        raporttext.appendText("<p>____________________________________________________________</p>");
                        wstep2 = 1;
                    }
                    if (!(uwagi.getText().equals(""))) {
                        raport.appendText("Zamawiający: " + nazwa.getText() + " Kwota: " + cena.getText() + " Końcowy status zamówienia: " + status.getText() + " Poziom wiarygodności zamawiającego: " + status_zamawiającego.getText() + " Data zamówienia: " + data_zamuwienia.getText() + " Uwagi: " + uwagi.getText()+"\n");
                        listadoc.add(new listaZaowienRaport(nazwa.getText(), cena.getText(), status.getText(), status_zamawiającego.getText(), data_zamuwienia.getText(), uwagi.getText()));
                    } else {
                        raport.appendText("Zamawiający: " + nazwa.getText() + " Kwota: " + cena.getText() + " Końcowy status zamówienia: " + status.getText() + " Poziom wiarygodności zamawiającego: " + status_zamawiającego.getText() + " Data zamówienia: " + data_zamuwienia.getText() + " Uwagi: brak\n");
                        listadoc.add(new listaZaowienRaport(nazwa.getText(), cena.getText(), status.getText(), status_zamawiającego.getText(), data_zamuwienia.getText(), "brak"));
                    }
                }else {
                    kom.komWszystkieDane();
                }
            }
        });

        tabela2.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                zamowienia zamowienie = tabela2.getSelectionModel().getSelectedItem();
                nazwa.setText(zamowienie.getFirma());
                cena.setText(zamowienie.getKwota() + " zł");
                status.setText(zamowienie.getStatus());
                status_zamawiającego.setText(zamowienie.getPoziom());
                data_zamuwienia.setText(zamowienie.getData_czas());
            }
        });

        craport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(raport.getText().equals(""))) {
                    eraport.setHtmlText(raport.getText());
                }else {
                    kom.komDokumentBrakTresc();
                }
            }
        });

        wraport2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(droga_do_pliku.equals("") || tytul_dokumentu.equals(""))) {
                    if (!(rodzaj_dokumentu.getValue() == null)) {

                        String html;
                        if (czy_html.isSelected()) {
                            if (eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>")){
                                html = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>";
                                kom.komDokumentTekstPoFormatowniuPusty();
                            }else {
                                html = eraport.getHtmlText();
                            }
                        } else {
                            html = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>";
                        }

                        int dlugosc;
                        String zapytanie;
                        PreparedStatement ps;

                        try {
                            File plik = new File(droga_do_pliku);
                            FileInputStream pfis = new FileInputStream("C://" + tytul_dokumentu.getText() + ".docx");
                            dlugosc = (int) plik.length();
                            zapytanie = ("INSERT INTO dokumenty_magazyniera VALUES(null,?,?,?,?,?,?)");
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                            ps = polaczenie.prepareStatement(zapytanie);
                            ps.setString(1, idpracownika);
                            ps.setString(2, tytul_dokumentu.getText());
                            ps.setString(3, dzisiaj.format(formatd));
                            ps.setString(4, rodzajDokumentu());
                            ps.setBinaryStream(5, pfis, dlugosc);
                            ps.setString(6, html);
                            ps.executeUpdate();
                            html = "";
                            droga_do_pliku = "";
                        } catch (Exception e) {
                            e.printStackTrace();
                            kom.komBrakDokumentu();
                        }
                    }else {
                        kom.komBrakRodzajuDokumentu();
                    }
                }else {
                    kom.komBrakDokumentu();
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        wraport3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu2.equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {

                        String html;
                        if (czy_html.isSelected()) {
                            if (htmlraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || htmlraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || htmlraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>")){
                                html = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>";
                                kom.komDokumentTekstPoFormatowniuPusty();
                            }else {
                                html = htmlraport.getHtmlText();
                            }
                        } else {
                            html = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>";
                        }

                        String zapytanie;
                        PreparedStatement ps;

                        try {
                            zapytanie = ("INSERT INTO dokumenty_magazyniera VALUES(null,?,?,?,?,null,?)");
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                            ps = polaczenie.prepareStatement(zapytanie);
                            ps.setString(1, idpracownika);
                            ps.setString(2, tytul_dokumentu2.getText());
                            ps.setString(3, dzisiaj.format(formatd));
                            ps.setString(4, rodzajDokumentu2());
                            ps.setString(5, html);
                            ps.executeUpdate();
                            html = "";
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        kom.komBrakRodzajuDokumentu();
                    }
                }else {
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        dodaj_zdjecie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                                        FileChooser fc = new FileChooser();
                                        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                                        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                                        fc.getExtensionFilters().add(filter);
                                        fc.getExtensionFilters().add(filter2);

                                        String uds = System.getProperty("user.home");
                                        File ud = new File(uds);
                                        if (!ud.canRead()) {
                                            ud = new File("c:/");
                                        }
                                        fc.setInitialDirectory(ud);

                                        File plik = fc.showOpenDialog(null);
                                        String droga;
                                        if (plik != null) {
                                            droga = plik.getPath();
                                            droga_do_plikuz = droga;
                                            try {
                                                droga_do_plikuz2 = plik.toURI().toURL().toString();
                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                            }
                                            droga_zdjecie.setText(droga);
                                            Image image = new Image(droga_do_plikuz2);
                                            zdjecie.setImage(image);
                                        } else {
                                            droga = null;
                                            droga_do_plikuz2 = null;
                                            droga_zdjecie.setText("");
                                        }
            }
        });

        generuj_pdf_z_raport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu.getText().equals(""))) {
                    if (!(rodzaj_dokumentu.getValue() == null)) {
                        if (!(raport.getText().equals(""))) {
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fc.getExtensionFilters().add(filter);
                String uds = System.getProperty("user.home");
                File ud = new File(uds);
                if(!ud.canRead()) {
                    ud = new File("c:/");
                }
                fc.setInitialDirectory(ud);
                fc.setTitle("Zapis raportu");
                File plik = fc.showSaveDialog(null);
                if (plik != null) {
                    try {
                        Document pdf2 = new Document();
                        try {
                            PdfWriter.getInstance(pdf2,new FileOutputStream(plik));
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                        pdf2.open();
                        try {
                            pdf2.add(new Paragraph(raport.getText()));
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                        pdf2.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                        }else {
                            kom.komDokumentBrakTresc();
                        }
                    }else {
                        kom.komDokumentBrakRodzajuDokumetu();
                    }
                }else {
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        zalacznik.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                droga_do_plikuz = log.drogaDoPliku();
                droga_zalacznik.setText(droga_do_plikuz);
                zalacznikplik = true;
            }
        });

        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String zapytanie;
                PreparedStatement ps;
                if (!(osoba.getText().equals("") || firma.getText().equals("") || adres.getText().equals("") || kod.getText().equals("") || miejscowosc.getText().equals("") || telefon.getText().equals("") || email.getText().equals("") || numer.getText().equals(""))) {
                    if (numer.getText().length() > 8 && numer.getText().length() < 10) {
                        if (kod.getText().length() > 5 && kod.getText().length() < 7) {
                            if (telefon.getText().length() > 8 && telefon.getText().length() < 15) {
                                String numerklienta;
                                try {
                                    Class.forName(sterownik);
                                    polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                    zapytanie = "SELECT numer FROM regiony_dostaw";
                                    Statement s = polaczenie.createStatement();
                                    ResultSet rs = s.executeQuery(zapytanie);
                                    if (rs.next()) {
                                        numerklienta = rs.getString(1);
                                        numerk.add(numerklienta);
                                    }
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                Boolean tak_nie_numer = false;
                                String wiarygodnosc = "";
                                for (String n : numerk) {
                                    if (n.equals(numer.getText())) {
                                        tak_nie_numer = true;
                                    }
                                }
                                if (tak_nie_numer == false) {
                                    if (zaufanie.getValue() == null) {
                                        wiarygodnosc = "3";
                                    } else {
                                        wiarygodnosc = poziomZaufania();
                                    }
                                    try {
                                        Class.forName(sterownik);
                                        polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                        zapytanie = ("INSERT INTO regiony_dostaw VALUES(null,?,?,?,?,?,?,?,?,?,?)");
                                        ps = polaczenie.prepareStatement(zapytanie);
                                        ps.setString(1, osoba.getText());
                                        ps.setString(2, firma.getText());
                                        ps.setString(3, adres.getText());
                                        ps.setString(4, "0");
                                        ps.setString(5, wiarygodnosc);
                                        ps.setString(6, kod.getText());
                                        ps.setString(7, miejscowosc.getText());
                                        ps.setString(8, telefon.getText());
                                        ps.setString(9, email.getText());
                                        ps.setString(10, numer.getText());
                                        ps.executeUpdate();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    kom.komNumerIstnieje();
                                }
                            }else {
                                kom.komTelefonDlugosc();
                            }
                        }else {
                            kom.komKodPocztowyDlugosc();
                        }
                    }else {
                        kom.komNumerDlugosc();
                    }
                }else {
                    kom.komWszystkieDane();
                }
            }
        });

        wiadomość3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tytul = tytul_wiadomosc.getText();
                String tresc = wiadomość2.getHtmlText();
                if (!(tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                    if (!(tytul.equals(""))) {
                        log.wiadomoscWysylanie3(zalacznikplik,droga_do_plikuz,"1",idpracownikan,tytul_wiadomosc.getText(),wiadomość2.getHtmlText(),"wiadomosc_pracownicy", tytul_wiadomosc.getText());
                    } else {
                        kom.komWiadomoscBrakTytulu();
                    }
                }else {
                    kom.komWiadomoscBrakTresc();
                }
            }
        });

        wyslij_wiadomosc_do_ksiegowosc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tytul = tytul_wiadomosc.getText();
                String tresc = wiadomość2.getHtmlText();
                if (!(tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                    if (!(tytul.equals(""))) {
                        log.wiadomoscWysylanie2(zalacznikplik,droga_do_plikuz,"2",idpracownikan,tytul_wiadomosc.getText(),wiadomość2.getHtmlText(),"wiadomosc_ksiegowosic");
                    } else {
                        kom.komWiadomoscBrakTytulu();
                    }
                }else {
                    kom.komWiadomoscBrakTresc();
                }
            }
        });

        wyslij_raport_do_kierownictwa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_wiadomosc.getText().equals(""))){
                    log.raportKierownictwoWysylanie(log.drogaDoPliku(),idpracownikan,tytul_wiadomosc.getText(),"raporty_kierownictwo");
                }else {
                    kom.komWiadomoscTytul();
                }
            }
        });

        wyslij_raport_do_ksiegowosc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_wiadomosc.getText().equals(""))){
                    log.raportKsiegowoscWysylanie(log.drogaDoPliku(),idpracownikan,tytul_wiadomosc.getText(),"dokumenty_ksiegowosic");
                }else {
                    kom.komWiadomoscTytul();
                }
            }
        });

        wiadomość4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tytul = tytul_wiadomosc.getText();
                String tresc = wiadomość2.getHtmlText();
                if (!(tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                    if (!(tytul.equals(""))) {
                        if (!(dzialbox.getValue() == null)) {
                            if (!(pracownikbox.getValue() == null)) {
                                if (dzialbox.getSelectionModel().getSelectedItem() == "Kierownictwo") {
                                    log.wiadomoscWysylanie3(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_pracownicy", tytul_wiadomosc.getText());
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Księgowość") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_ksiegowosic");
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Magazyn") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_magazyniera");
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Brygadzista") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_brygadzista");
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Transport/Pracownicy") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_kierowcyPracownicy");
                                }
                            }else {
                                kom.komWiadomoscBrakPracownika();
                            }
                        }else {
                            kom.komWiadomoscBrakDzialu();
                        }
                    } else {
                        kom.komWiadomoscBrakTytulu();
                    }
                }else {
                    kom.komWiadomoscBrakTresc();
                }
            }
        });

        dzialbox.setOnAction((e) -> {
            pracownicydzialu.clear();
            String zapytanie = "";
            if(dzialbox.getSelectionModel().getSelectedItem() == "Kierownictwo"){
                zapytanie = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 1";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Księgowość"){
                zapytanie = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 2";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Magazyn"){
                zapytanie = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 3";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Brygadzista"){
                zapytanie = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 4";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Transport/Pracownicy"){
                zapytanie = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 5";
            }
            try {
                Class.forName(sterownik);
                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement s = polaczenie.createStatement();
                ResultSet rs = s.executeQuery(zapytanie);
                while(rs.next()) {
                    pracownicydzialu.add(rs.getString(1));
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            pracownikbox.setItems(pracownicydzialu);
        });

        pracownikbox.setOnAction((e) -> {
            try {
                Class.forName(sterownik);
                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement s = polaczenie.createStatement();
                ResultSet rs = s.executeQuery("SELECT id_pracownika, imie, nazwisko FROM pracownicy WHERE identyfikator ="+pracownikbox.getSelectionModel().getSelectedItem());
                while(rs.next()) {
                    imie_nazwisko.setText(rs.getString(2)+ " " + rs.getString(3));
                    identyfikator2 = rs.getString(1);
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        dodaj_produkt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(nazwad.getText() == "" || liczbad.getText() == "" || cenad.getText() == "")) {
                    if (!(typ_produktu.getValue() == null)) {
                        if (!(ocena_produktu.getValue() == null)) {
                            if (liczbad.getText().length() <= 6 && liczbad.getText().length() > 0) {
                                if (cenad.getText().length() > 0 && cenad.getText().length() <= 6 && !(cenad.getText().equals("0"))) {
                                    if (cenad2.getText().length() > 1 && cenad2.getText().length() < 3) {

                                        int dlugosc;
                                        String zapytanie;
                                        PreparedStatement ps;

                                        try {
                                            Class.forName(sterownik);
                                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                            if (!(droga_do_plikuz == null)) {
                                                File file = new File(droga_do_plikuz);
                                                FileInputStream fis = new FileInputStream(file);
                                                dlugosc = (int) file.length();
                                                zapytanie = ("INSERT INTO szczegoly_produktu VALUES(null,?,?,?,?,?,?,?)");
                                                ps = polaczenie.prepareStatement(zapytanie);
                                                ps.setString(1, typProduktu());
                                                ps.setString(2, nazwad.getText());
                                                ps.setString(3, liczbad.getText());
                                                ps.setString(4, ocenaProduktu());
                                                ps.setString(5, cenad.getText()+"."+cenad2.getText());
                                                ps.setString(6, dzisiaj.format(formatd));
                                                ps.setBinaryStream(7, fis, dlugosc);
                                            } else {
                                                zapytanie = ("INSERT INTO szczegoly_produktu VALUES(null,?,?,?,?,?,?,null)");
                                                ps = polaczenie.prepareStatement(zapytanie);
                                                ps.setString(1, typProduktu());
                                                ps.setString(2, nazwad.getText());
                                                ps.setString(3, liczbad.getText());
                                                ps.setString(4, ocenaProduktu());
                                                ps.setString(5, cenad.getText()+"."+cenad2.getText());
                                                ps.setString(6, dzisiaj.format(formatd));
                                            }
                                            ps.executeUpdate();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else {
                                        kom.komGrosze();
                                    }
                                } else {
                                    kom.komCena();
                                }
                            }else {
                                kom.komLiczbaSztuk();
                            }
                        }else {
                            kom.komOcenaProduktu();
                        }
                    }else {
                        kom.komTypProduktu();
                    }
                }else {
                    kom.komWszystkieDane();
                }
            }
        });

        dane2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (login.getText().equals(identyfikator) || haslo.getText().equals(haslodane)) {
                    pokazMojeDane(login.getText(), haslo.getText());
                }else {
                    kom.komZleDane();
                }
            }
        });
    }

    public void pokazBazeMagazynu(){
        sql = "Select szczegoly_produktu.id_towaru, produkty.nzwa_produktu, szczegoly_produktu.nazwa, szczegoly_produktu.liczba_sztuk_na_magazynie, szczegoly_produktu.ocena, szczegoly_produktu.cena from szczegoly_produktu JOIN produkty ON szczegoly_produktu.id_produktu=produkty.id_produktu;";
        kolumna1.setCellValueFactory(new PropertyValueFactory<produkty, Integer>("id_towaru"));
        kolumna2.setCellValueFactory(new PropertyValueFactory<produkty, String>("produkt"));
        kolumna3.setCellValueFactory(new PropertyValueFactory<produkty, String>("nazwa"));
        kolumna4.setCellValueFactory(new PropertyValueFactory<produkty, Integer>("liczba_sztuk_na_magazynie"));
        kolumna5.setCellValueFactory(new PropertyValueFactory<produkty, Integer>("ocena"));
        kolumna6.setCellValueFactory(new PropertyValueFactory<produkty, String>("cena"));
        kolumna7.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<produkty, String>, TableCell<produkty, String>> celaf = new Callback<TableColumn<produkty, String>, TableCell<produkty, String>>() {
            @Override
            public TableCell call(final TableColumn<produkty, String> p) {
                final TableCell<produkty, String> cela = new TableCell<produkty, String>() {

                    final JFXButton btn = new JFXButton("Zmień stan");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            produkty produkt = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                    TextInputDialog dialog = new TextInputDialog("Podaj liczbę");
                                    dialog.setTitle("Stan na magazynie");
                                    dialog.setHeaderText("Podaj stan magazynu");
                                    dialog.setContentText("Podaj liczbę sztuk:");

                                    Optional<String> result = dialog.showAndWait();
                                    if (result.isPresent()){
                                        if (result.get().matches("-?([1-9][0-9]*)?")) {
                                            int liczbasztuk = Integer.parseInt(result.get());
                                            try {
                                                Class.forName(sterownik);
                                                polaczenie = DriverManager.getConnection(baza);
                                                PreparedStatement ps = polaczenie.prepareStatement("UPDATE szczegoly_produktu SET liczba_sztuk_na_magazynie = ? WHERE id_towaru = ?");
                                                ps.setString(1, String.valueOf(liczbasztuk));
                                                ps.setString(2, String.valueOf(produkt.getId_towaru()));
                                                int status = ps.executeUpdate();
                                                pokazBazeMagazynu();
                                            } catch (ClassNotFoundException ex) {
                                                ex.printStackTrace();
                                            } catch (SQLException ex) {
                                                ex.printStackTrace();
                                            }
                                        }else {
                                            kom.komPodalesLitery();
                                        }
                                    }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna7.setCellFactory(celaf);
        kolumna8.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<produkty, String>, TableCell<produkty, String>> celaf2 = new Callback<TableColumn<produkty, String>, TableCell<produkty, String>>() {
            @Override
            public TableCell call(final TableColumn<produkty, String> p) {
                final TableCell<produkty, String> cela = new TableCell<produkty, String>() {

                    final JFXButton btn = new JFXButton("Usuń produkt");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            produkty produkt = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.towarUsuwanie(String.valueOf(produkt.getId_towaru()));
                                pokazBazeMagazynu();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna8.setCellFactory(celaf2);
        tabela.getItems().setAll(baz.baza7(sql));
    }
    public void pokazBazeKlientow(){
        sql4 = "Select regiony_dostaw.id_regionu, regiony_dostaw.przedstawiciel, regiony_dostaw.firma, regiony_dostaw.adres, regiony_dostaw.liczba_zamowien, wiarygodnosc.poziom, regiony_dostaw.kod_pocztowy, regiony_dostaw.miejscowosc, regiony_dostaw.telefon, regiony_dostaw.email, regiony_dostaw.numer from regiony_dostaw JOIN wiarygodnosc ON regiony_dostaw.id_poziomu=wiarygodnosc.id_poziomu;";
        kolumna23.setCellValueFactory(new PropertyValueFactory<klienci, Integer>("id_regionu"));
        kolumna24.setCellValueFactory(new PropertyValueFactory<klienci, String>("przedstawiciel"));
        kolumna25.setCellValueFactory(new PropertyValueFactory<klienci, String>("firma"));
        kolumna26.setCellValueFactory(new PropertyValueFactory<klienci, String>("adres"));
        kolumna27.setCellValueFactory(new PropertyValueFactory<klienci, Integer>("liczba_zamowien"));
        kolumna28.setCellValueFactory(new PropertyValueFactory<klienci, String>("poziom"));
        kolumna45.setCellValueFactory(new PropertyValueFactory<klienci, String>("kod_pocztowy"));
        kolumna46.setCellValueFactory(new PropertyValueFactory<klienci, String>("miejscowosc"));
        kolumna29.setCellValueFactory(new PropertyValueFactory<klienci, String>("telefon"));
        kolumna30.setCellValueFactory(new PropertyValueFactory<klienci, String>("email"));
        kolumna31.setCellValueFactory(new PropertyValueFactory<klienci, Integer>("numer"));
        kolumna32.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<klienci, String>, TableCell<klienci, String>> celaf = new Callback<TableColumn<klienci, String>, TableCell<klienci, String>>() {
            @Override
            public TableCell call(final TableColumn<klienci, String> p) {
                final TableCell<klienci, String> cela = new TableCell<klienci, String>() {

                    final JFXButton btn = new JFXButton("Usuń klienta");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            klienci klient = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.klientUsuwanie(String.valueOf(klient.getId_regionu()));
                                pokazBazeKlientow();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna32.setCellFactory(celaf);
        tabela4.getItems().setAll(baz.baza8(sql4));
    }
    public void pokazBazeZamowien(){
        sql5 = "Select zamowienia.id_zamowienia, regiony_dostaw.firma, zamowienia.kwota, status_przesylki.status, wiarygodnosc.poziom, zamowienia.data_czas, zamowienia.numer_zamuwienia, zamowienia.liczba_produktow, zamowienia.produkty, zamowienia.plik_tak_nie from zamowienia JOIN regiony_dostaw ON regiony_dostaw.id_regionu=zamowienia.id_regionu JOIN status_przesylki ON status_przesylki.id_statusu=zamowienia.id_statusu JOIN wiarygodnosc ON wiarygodnosc.id_poziomu=zamowienia.id_poziomu;";
        kolumna12.setCellValueFactory(new PropertyValueFactory<zamowienia, Integer>("id_zamowienia"));
        kolumna13.setCellValueFactory(new PropertyValueFactory<zamowienia, String>("firma"));
        kolumna14.setCellValueFactory(new PropertyValueFactory<zamowienia, String>("kwota"));
        kolumna15.setCellValueFactory(new PropertyValueFactory<zamowienia, String>("status"));
        kolumna16.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>> celaf = new Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>>() {
            @Override
            public TableCell call(final TableColumn<zamowienia, String> p) {
                final TableCell<zamowienia, String> cela = new TableCell<zamowienia, String>() {

                    final JFXComboBox btn = new JFXComboBox();

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            zamowienia zamuwienie = getTableView().getItems().get(getIndex());
                            btn.setItems(lista7);
                            btn.setValue(zamuwienie.getStatus());
                            btn.setOnAction((e) -> {
                                        try {
                                            Class.forName(sterownik);
                                            polaczenie = DriverManager.getConnection(baza);
                                            PreparedStatement ps = polaczenie.prepareStatement("UPDATE zamowienia SET id_statusu = ? WHERE id_zamowienia = ?");
                                            ps.setString(1,log.ststusSet(btn.getSelectionModel().getSelectedItem().toString()));
                                            ps.setString(2, String.valueOf(zamuwienie.getId_zamowienia()));
                                            int status = ps.executeUpdate();
                                        } catch (ClassNotFoundException ex) {
                                            ex.printStackTrace();
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                        }
                                    });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna16.setCellFactory(celaf);
        kolumna17.setCellValueFactory(new PropertyValueFactory<zamowienia, String>("data_czas"));
        kolumna18.setCellValueFactory(new PropertyValueFactory<zamowienia, String>("numer_zamuwienia"));
        kolumna19.setCellValueFactory(new PropertyValueFactory<zamowienia, String>("liczba_produktow"));
        kolumna20.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>> celaf2 = new Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>>() {
            @Override
            public TableCell call(final TableColumn<zamowienia, String> p) {
                final TableCell<zamowienia, String> cela = new TableCell<zamowienia, String>() {

                    final JFXTextArea btn = new JFXTextArea();

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            zamowienia zamuwienie = getTableView().getItems().get(getIndex());
                            btn.setText(zamuwienie.getProdukty());
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna20.setCellFactory(celaf2);
        kolumna21.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

        Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>> celaf3 = new Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>>() {
            @Override
            public TableCell call(final TableColumn<zamowienia, String> p) {
                final TableCell<zamowienia, String> cela = new TableCell<zamowienia, String>() {

                    final JFXButton btn = new JFXButton("Pobierz raport");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            zamowienia zamuwienie = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.dokumentPobierz2(String.valueOf(zamuwienie.getId_zamowienia()));
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna21.setCellFactory(celaf3);
        kolumna22.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

        Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>> celaf4 = new Callback<TableColumn<zamowienia, String>, TableCell<zamowienia, String>>() {
            @Override
            public TableCell call(final TableColumn<zamowienia, String> p) {
                final TableCell<zamowienia, String> cela = new TableCell<zamowienia, String>() {

                    final JFXButton btn = new JFXButton("Usuń klienta");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            zamowienia zamuwienie = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.klientUsuwanie(String.valueOf(zamuwienie.getId_zamowienia()));
                                pokazBazeKlientow();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna22.setCellFactory(celaf4);
        tabela3.getItems().setAll(baz.baza9(sql5));
        kolumna9.setCellValueFactory(new PropertyValueFactory<klienci, Integer>("id_zamowienia"));
        kolumna10.setCellValueFactory(new PropertyValueFactory<klienci, String>("firma"));
        kolumna11.setCellValueFactory(new PropertyValueFactory<klienci, String>("kwota"));
        tabela2.getItems().setAll(baz.baza9(sql5));
    }
    public String typProduktu(){
        String s = "";
        if(typ_produktu.getValue() == "Płayta główna"){
            s = "1";
        }else if (typ_produktu.getValue() == "Dysk"){
            s = "2";
        }else if (typ_produktu.getValue() == "Pamięć RAM"){
            s = "3";
        }else if (typ_produktu.getValue() == "Karta graficzna"){
            s = "4";
        }else if (typ_produktu.getValue() == "Procesor"){
            s = "5";
        }
        return s;
    }
    public String ocenaProduktu(){
        String s = "";
        if(ocena_produktu.getValue() == "Bardzo dobry"){
            s = "5";
        }else if (ocena_produktu.getValue() == "Dobry"){
            s = "4";
        }else if (ocena_produktu.getValue() == "Dostateczny"){
            s = "3";
        }else if (ocena_produktu.getValue() == "Zły"){
            s = "2";
        }else if (ocena_produktu.getValue() == "Bardzo zły"){
            s = "1";
        }
        return s;
    }
    public void pokazMojeDane(String login, String haslo){
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(baza);
            st = polaczenie.createStatement();
            String zapytanie = ("Select dzial.nazwa_dzialu, stanowiska.nazwa_stanowiska, pracownicy.imie, pracownicy.nazwisko, pracownicy.pesel, pracownicy.telefon, pracownicy.adres, pracownicy.email, pracownicy.identyfikator, pracownicy.haslo, pracownicy.liczba_dni_urlopu_wykorzystane, stanowiska.liczba_dni_urlopu From pracownicy JOIN dzial ON pracownicy.id_dzialu = dzial.id_dzialu JOIN stanowiska ON pracownicy.id_stanowiska = stanowiska.id_stanowiska WHERE identyfikator = " + login + " AND haslo = " + "'" + haslo + "'");
            rs = st.executeQuery(zapytanie);
            while (rs.next()) {
                limię.setText(rs.getString(3));
                lnazwisko.setText(rs.getString(4));
                lpesel.setText(rs.getString(5));
                ltelefon.setText(rs.getString(6));
                ladres.setText(rs.getString(7));
                ldzial.setText(rs.getString(1));
                lemail.setText(rs.getString(8));
                lstanowisko.setText(rs.getString(2));
                lidentyfikator.setText(rs.getString(9));
                lhaslo.setText(rs.getString(10));
                lduw.setText(rs.getString(11));
                ldu.setText(rs.getString(12));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public String rodzajDokumentu(){
        String s;
        if(rodzaj_dokumentu.getValue() == "Raport"){
            s = "1";
        }else if (rodzaj_dokumentu.getValue() == "Pracownicy wypłaty"){
            s = "2";
        }else if (rodzaj_dokumentu.getValue() == "Zyski firmy"){
            s = "3";
        }else if (rodzaj_dokumentu.getValue() == "Straty firmy"){
            s = "4";
        }else if (rodzaj_dokumentu.getValue() == "Inny"){
            s = "5";
        }else {
            s = "";
        }
        return s;
    }
    public String rodzajDokumentu2(){
        String s;
        if(rodzaj_dokumentu2.getValue() == "Raport"){
            s = "1";
        }else if (rodzaj_dokumentu2.getValue() == "Pracownicy wypłaty"){
            s = "2";
        }else if (rodzaj_dokumentu2.getValue() == "Zyski firmy"){
            s = "3";
        }else if (rodzaj_dokumentu2.getValue() == "Straty firmy"){
            s = "4";
        }else if (rodzaj_dokumentu2.getValue() == "Inny"){
            s = "5";
        }else {
            s = "";
        }
        return s;
    }
    public void pokazBazeDokumentuwMagazyniera(){
        sql3 = "Select dokumenty_magazyniera.id_dokumentu, dokumenty_magazyniera.id_pracownika, dokumenty_magazyniera.tytul, dokumenty_magazyniera.data_godzina, dokumenty_rodzaje.rodzaj, dokumenty_magazyniera.plikhtml from dokumenty_magazyniera JOIN dokumenty_rodzaje ON dokumenty_rodzaje.id_rodzaju_dokumentu = dokumenty_magazyniera.id_rodzaju_dokumentu where id_pracownika =" +idpracownikan;
        kolumna38.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("tytul"));
        kolumna39.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("data_godzina"));
        kolumna40.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("rodzaj"));
        kolumna41.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>> celaf = new Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_ksiegowosic, String> p) {
                final TableCell<dokumenty_ksiegowosic, String> cela = new TableCell<dokumenty_ksiegowosic, String>() {

                    final JFXCheckBox check = new JFXCheckBox();

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                                    if (t1) {
                                        dokumenty_ksiegowosic dokument = getTableView().getItems().get(getIndex());
                                        dok.add(dokument.getId_dokumentu().toString());
                                    } else {
                                        dokumenty_ksiegowosic dokument = getTableView().getItems().get(getIndex());
                                        dok.remove(dokument.getId_dokumentu().toString());
                                    }
                                }
                            });
                            setGraphic(check);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna41.setCellFactory(celaf);
        kolumna42.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>> celaf2 = new Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_ksiegowosic, String> p) {
                final TableCell<dokumenty_ksiegowosic, String> cela = new TableCell<dokumenty_ksiegowosic, String>() {

                    final JFXButton btn = new JFXButton("Usuń dokument");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_ksiegowosic dokument = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.dokumentUsuwanie(dokument.getId_dokumentu().toString(),"dokumenty_magazynier");
                                pokazBazeDokumentuwMagazyniera();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna42.setCellFactory(celaf2);
        kolumna43.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

        Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>> celaf3 = new Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_ksiegowosic, String> p) {
                final TableCell<dokumenty_ksiegowosic, String> cela = new TableCell<dokumenty_ksiegowosic, String>() {

                    final JFXButton btn = new JFXButton("Generuj PDF");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_ksiegowosic dokument = getTableView().getItems().get(getIndex());
                            if (dokument.getPlikhtml().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>")) {
                                btn.setDisable(true);
                            }else {
                                btn.setDisable(false);
                                btn.setOnAction(event -> {
                                    log.dokumentGenerujPDF(dokument.getPlikhtml());
                                });
                            }
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna43.setCellFactory(celaf3);
        kolumna44.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

        Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>> celaf4 = new Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_ksiegowosic, String> p) {
                final TableCell<dokumenty_ksiegowosic, String> cela = new TableCell<dokumenty_ksiegowosic, String>() {

                    final JFXButton btn = new JFXButton("Pobierz raport");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_ksiegowosic dokument = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.dokumentPobierz(dokument.getId_dokumentu().toString(),"dokumenty_magazyniera");
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna44.setCellFactory(celaf4);
        tabela6.getItems().setAll(baz.baza2(sql3));
    }
    public void pokazBazeWiadomosc(){
        sql2 = "Select wiadomosc_magazyniera.id_wiadomosc, pracownicy.imie, pracownicy.nazwisko, wiadomosc_magazyniera.tytul, wiadomosc_magazyniera.tresc, wiadomosc_magazyniera.data_godzina, wiadomosc_magazyniera.typ_przeczytana_nieprzeczytana, wiadomosc_magazyniera.plik_tak_nie from wiadomosc_magazyniera JOIN pracownicy ON pracownicy.id_pracownika = wiadomosc_magazyniera.id_pracownikan where wiadomosc_magazyniera.id_pracownika =" + idpracownikan + " ORDER BY id_wiadomosc DESC";
        kolumna33.setCellValueFactory(new PropertyValueFactory<wiadomosc, String>("tytul"));
        kolumna34.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> celaf = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cela = new TableCell<wiadomosc, String>() {

                    final Label lab = new Label();

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                            if (wiadomosc.getTyp_przeczytana_nieprzeczytana()){
                                lab.setText("Przeczytana");
                            }else {
                                lab.setText("Nieprzeczytana");
                            }
                            setGraphic(lab);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna34.setCellFactory(celaf);
        kolumna35.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> celaf2 = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cela = new TableCell<wiadomosc, String>() {

                    final JFXCheckBox check = new JFXCheckBox();

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                                    if (t1) {
                                        wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                                        wdu.add(wiadomosc.getId_wiadomosc().toString());
                                    } else {
                                        wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                                        wdu.remove(wiadomosc.getId_wiadomosc().toString());
                                    }
                                }
                            });
                            setGraphic(check);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna35.setCellFactory(celaf2);
        kolumna36.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> celaf3 = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cela = new TableCell<wiadomosc, String>() {

                    final JFXButton btn = new JFXButton("Pobierz załącznik");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                            if (wiadomosc.getPlik_tak_nie().equals("1")) {
                                btn.setDisable(false);
                                btn.setOnAction(event -> {
                                    log.pobierzZalacznik(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_magazyniera");
                                    pokazBazeWiadomosc();
                                });
                            }else {
                                btn.setDisable(true);
                            }
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna36.setCellFactory(celaf3);
        kolumna37.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> celaf4 = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cela = new TableCell<wiadomosc, String>() {

                    final JFXButton btn = new JFXButton("Usuń wiadomość");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.wiadomoscUsuwanie(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_magazyniera");
                                pokazBazeWiadomosc();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna37.setCellFactory(celaf4);
        tabela5.getItems().setAll(baz.baza(sql2));
    }
    public String poziomZaufania(){
        String z = "";
        if (zaufanie.getValue() == "Bardzo dobry"){
            z = "1";
        }else if (zaufanie.getValue() == "Dobry"){
            z = "2";
        }else if (zaufanie.getValue() == "Dostateczny"){
            z = "3";
        }else if (zaufanie.getValue() == "Zły"){
            z = "4";
        }else if (zaufanie.getValue() == "Bardzo zły"){
            z = "5";
        }
        return z;
    }
    public void listaKlientow(){
        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            Statement s = polaczenie.createStatement();
            ResultSet rs = s.executeQuery("SELECT firma FROM regiony_dostaw");
            while(rs.next()) {
                lista6.add(rs.getString(1));
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        firmabox.setItems(lista6);
    }
    public void setText(String login, String haslo, String id){
        identyfikator = login;
        haslodane = haslo;
        idpracownikan = id;
    }
    public void pokazBazeWiadomoscDzialy(){
        sql7 = "Select wiadomosci_dzialy.id_wiadomosc, dzial.nazwa_dzialu, wiadomosci_dzialy.tytul, wiadomosci_dzialy.data_godzina, wiadomosci_dzialy.tresc, wiadomosci_dzialy.typ_przeczytana_nieprzeczytana from wiadomosci_dzialy JOIN dzial ON wiadomosci_dzialy.id_dzialu = dzial.id_dzialu WHERE nazwa_dzialu = 'Magazyn' ORDER BY id_wiadomosc DESC";
        kolumna47.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("tytul"));
        kolumna48.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>> celaf = new Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc_dzialy, String> p) {
                final TableCell<wiadomosc_dzialy, String> cela = new TableCell<wiadomosc_dzialy, String>() {

                    final Label lab = new Label();

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc_dzialy wiadomosc = getTableView().getItems().get(getIndex());
                            if (wiadomosc.getTyp_przeczytana_nieprzeczytana()){
                                lab.setText("Przeczytana");
                            }else {
                                lab.setText("Nieprzeczytana");
                            }
                            setGraphic(lab);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna48.setCellFactory(celaf);
        kolumna49.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("data_godzina"));
        kolumna50.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>> celaf2 = new Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc_dzialy, String> p) {
                final TableCell<wiadomosc_dzialy, String> cela = new TableCell<wiadomosc_dzialy, String>() {

                    final JFXButton btn = new JFXButton("Pobierz wiadomość");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc_dzialy wiadomosc = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.pobierzWiadomosc(String.valueOf(wiadomosc.getTresc()));
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna50.setCellFactory(celaf2);
        tabela7.getItems().setAll(baz.baza3(sql7));
    }
}