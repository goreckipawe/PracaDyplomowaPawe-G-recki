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
import org.apache.commons.net.ftp.FTPClient;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class brygadzista implements Initializable {

    private static String baza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private PDFParser parser;
    private PDFTextStripper pdfst;
    private PDDocument pddoc;
    private COSDocument cosdoc;

    public String Text ;
    private String drogaplik;
    private File plikg;

    private static String droga;
    private static String txt;
    private static String t;
    private static String sql;
    private static String sql2;
    private static String sql3;
    private static String sql5;
    private static String sql6;
    private static String sql7;
    private static String sql8;
    private static String htmlText;
    private static String plik;
    private static String tekst;
    private static Connection polaczenie;
    private static Statement st;
    private static ResultSet rs;
    private static String sterownik = "org.sqlite.JDBC";
    private static String dzien = "";
    private static PdfWriter pw;
    private static long dni = 0;
    private static long dnio = 0;
    private static long dniow = 0;
    private static int id;
    private static String identyfikator = "";
    private static String identyfikator2 = "";
    private static String haslodane = "";
    private static String idpracownika = "1";
    private static String idpracownikan = "3";
    private static String idwiadomosc;
    private static String droga_do_plikuz = "";
    private static String droga_do_pliku;
    private static Boolean zalacznikplik = false;
    private static LocalDateTime dzisiaj = LocalDateTime.now();
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private static LocalDateTime dzisiaj2 = LocalDateTime.now();
    private static DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static List<String> listap = new ArrayList<String>();
    private static List<String> listaw = new ArrayList<String>();
    private static List<String> listar = new ArrayList<String>();
    private static List<String> wdu = new ArrayList<String>();
    private static List<String> dok = new ArrayList<String>();
    private static List<String> dokp = new ArrayList<String>();
    private static List<String> du = new ArrayList<String>();
    private static List<String> duc = new ArrayList<String>();
    private static List<listaPracownicyDni> listadoc = new ArrayList<listaPracownicyDni>();

    private static ObservableList<String> lista = FXCollections.observableArrayList(
            "Raport", "Pracownicy wypłaty", "Zyski firmy", "Straty firmy", "Inny", "Raport pojazdy", "Przydział regionów i pojazdów"
    );
    private static ObservableList<String> lista2 = FXCollections.observableArrayList(
            "Kierownictwo", "Księgowość", "Magazyn", "Brygadzista", "Transport/Pracownicy"
    );
    private static TextArea regionyprzydzial = new TextArea();
    private static TextArea raport2 = new TextArea();
    private static ObservableList<String> pracownicydzialu = FXCollections.observableArrayList();

    private static komunikaty kom = new komunikaty();
    private static logika log = new logika();
    private static baza baz = new baza();

    @FXML
    private JFXTextField pojazd;
    @FXML
    private JFXTextField tw;
    @FXML
    private JFXTextField dtw_pojazdy;
    @FXML
    private JFXTextField autor_pojazd;
    @FXML
    private JFXTextField tw_pojazdy;
    @FXML
    private JFXTextField dtw;
    @FXML
    private JFXTextField autor_wiadomosc;
    @FXML
    private WebView strony;
    @FXML
    private WebView dokument_podglad;
    @FXML
    private WebView strony_pojazdy;
    @FXML
    private JFXTextField id_pojazdu;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField droga_zalacznik;
    @FXML
    private JFXTextField pracownik;
    @FXML
    private JFXTextField uwagi;
    @FXML
    private JFXTextField tytul_wiadomosc;
    @FXML
    private JFXTextField imier;
    @FXML
    private JFXTextField nazwiskor;
    @FXML
    private JFXTextField identyfikatorr;
    @FXML
    private JFXTextField liczba_dni;
    @FXML
    private JFXTextField imie_nazwisko;
    @FXML
    private DatePicker datau;
    @FXML
    private DatePicker datauod;
    @FXML
    private DatePicker dataudo;
    @FXML
    private HTMLEditor wiadomość2;
    @FXML
    private JFXTextField haslo;
    @FXML
    private JFXTextField tytul_dokumentu;
    @FXML
    private JFXTextField tytul_dokumentu2;
    @FXML
    private ComboBox rodzaj_dokumentu;
    @FXML
    private ComboBox rodzaj_dokumentu2;
    @FXML
    private JFXCheckBox czy_html;
    @FXML
    private WebView eraport2;
    @FXML
    private WebView eraport3;
    @FXML
    private Label zajete;
    @FXML
    private HTMLEditor przydział;
    @FXML
    private WebView web;
    @FXML
    private JFXTextArea raport;
    @FXML
    private JFXTextArea dokument;
    @FXML
    private JFXButton wraport;
    @FXML
    private JFXButton wraport2;
    @FXML
    private JFXButton dokumentpdf1;
    @FXML
    private JFXButton dokumentpdf2;
    @FXML
    private JFXButton przydzialypokaz;
    @FXML
    private JFXButton usun_urlop_dzien;
    @FXML
    private JFXButton dodaj_urlop_dzien;
    @FXML
    private JFXButton usun_urlop_ciagly;
    @FXML
    private JFXButton dodaj_urlop_ciagly;
    @FXML
    private JFXButton usun_zaznaczone_dokumenty_pojazdy;
    @FXML
    private JFXButton generuj_pdf_pracownicy_dni;
    @FXML
    private JFXButton dodaj_wpis;
    @FXML
    private JFXButton wiadomość3;
    @FXML
    private JFXButton wiadomość4;
    @FXML
    private JFXButton zalacznik;
    @FXML
    private JFXButton zprzydział;
    @FXML
    private JFXButton przydział2;
    @FXML
    private JFXButton wiadomosc_usun;
    @FXML
    private JFXButton uraport;
    @FXML
    private JFXButton uraport2;
    @FXML
    private JFXButton craport;
    @FXML
    private JFXButton wstecz;
    @FXML
    private JFXButton dane2;
    @FXML
    private JFXButton podglad2;
    @FXML
    private JFXButton usun_zaznaczone_dokumenty;
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
    private HTMLEditor eraport;
    @FXML
    private HTMLEditor htmlraport;
    @FXML
    private MenuItem zamknij;
    @FXML
    private MenuItem opis;
    @FXML
    private MenuItem autorzy;
    @FXML
    private MenuItem wersja;
    @FXML
    private DatePicker data;
    @FXML
    private TableView<Osoby> tabela;
    @FXML
    private TableView<Osoby> tabela2;
    @FXML
    private TableView<lista2> tabela3;
    @FXML
    private TableView<firmy> tabela4;
    @FXML
    private TableView<Osoby> tabela5;
    @FXML
    private TableView<wiadomosc> tabela6;
    @FXML
    private TableView<dokumenty_ksiegowosic> tabela7;
    @FXML
    private TableView<wiadomosc_dzialy> tabela8;
    @FXML
    private TableView<dokumenty_pojazdy> tabela9;
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
    private TableColumn kolumna9;
    @FXML
    private TableColumn kolumna10;
    @FXML
    private TableColumn kolumna11;
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
        tabela8.setTableMenuButtonVisible(true);
        tabela9.setTableMenuButtonVisible(true);
        int m = dzisiaj2.getMonthValue();

        raport2.appendText("Raport na dzień: " + dzisiaj2.format(format2) + " miesiąc: " + log.miesiac(m));

        rodzaj_dokumentu.setItems(lista);
        rodzaj_dokumentu2.setItems(lista);
        dzialbox.setItems(lista2);

        String url = getClass().getResource("/web/viewer.html").toExternalForm();

        WebEngine silnik2 = eraport3.getEngine();
        silnik2.setJavaScriptEnabled(true);
        silnik2.load(url);

        WebEngine silnik = eraport2.getEngine();
        silnik.setJavaScriptEnabled(true);
        silnik.load(url);

        WebEngine silnik3 = dokument_podglad.getEngine();
        silnik3.setJavaScriptEnabled(true);
        silnik3.load(url);

        pokazBazePracownikow();
        pokazBazePojazdow();
        pokazBazeRegionow();
        pokazBazeWiadomosc();
        pokazBazeDokumentuwBrygadzisty();
        pokazBazeWiadomoscDzialy();
        pokazBazeDokumentuwPojazdy();

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
                kom.komPomocBrygadzista();
            }
        });

        pojazd.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        pracownik.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });

        login.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        liczba_dni.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
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

        zprzydział.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String html = przydział.getHtmlText();

                String zapytanie;
                PreparedStatement ps;
                System.out.print(html);
                if (!(html.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || html.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || html.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>") || html.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\">Przydział na dzień 2017-10-04Przydział na dzień 2017-10-04</body></html>") || html.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\">Przydział na dzień 2017-10-04</body></html>"))){
                try {
                    zapytanie = ("INSERT INTO dokumenty_brygadzista VALUES(null,?,?,?,?,null,?)");
                    Class.forName(sterownik);
                    polaczenie = DriverManager.getConnection(String.valueOf(baza));
                    ps = polaczenie.prepareStatement(zapytanie);
                    ps.setString(1, idpracownika);
                    ps.setString(2, "Przydział na dzień "+ dzisiaj2.format(format2));
                    ps.setString(3, dzisiaj.format(format));
                    ps.setString(4, "6");
                    ps.setString(5, html);
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.dokumentGenerujPDF(html);
                listap.clear();
                listaw.clear();
                listar.clear();
                regionyprzydzial.setText("");
                przydział.setHtmlText("");
                }else {
                    kom.komPrzydzialy();
                }
            }
        });

        wraport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu.getText().equals(""))) {
                    if (!(rodzaj_dokumentu.getValue() == null)) {

                        String html = "";
                        if (htmlraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || htmlraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || htmlraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>")){
                            kom.komBrakTekstuHtml();
                        }else {
                            html = htmlraport.getHtmlText();

                            String zapytanie;
                            PreparedStatement ps;

                            try {
                                zapytanie = ("INSERT INTO dokumenty_brygadzista VALUES(null,?,?,?,?,null,?)");
                                Class.forName(sterownik);
                                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                ps = polaczenie.prepareStatement(zapytanie);
                                ps.setString(1, idpracownika);
                                ps.setString(2, tytul_dokumentu.getText());
                                ps.setString(3, dzisiaj.format(format));
                                ps.setString(4, rodzajDokumentu());
                                ps.setString(5, html);
                                ps.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        kom.komBrakRodzajuDokumentu();
                    }
                }else {
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        generuj_pdf_pracownicy_dni.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu2.getText().equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {
                        if (!(raport.getText().equals(""))) {
                FileChooser fch = new FileChooser();
                FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fch.getExtensionFilters().add(extentionFilter);
                String domowy = System.getProperty("user.home");
                File ulokacja = new File(domowy);
                if(!ulokacja.canRead()) {
                    ulokacja = new File("c:/");
                }
                fch.setInitialDirectory(ulokacja);
                fch.setTitle("Zapis raportu");
                File plik = fch.showSaveDialog(null);
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
                        ex.printStackTrace();
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

        wraport2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String html;
                int dlugosc;
                String zapytanie;
                PreparedStatement ps;
                if (!(droga_do_pliku.equals("") || tytul_dokumentu2.equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {
                        if (rodzaj_dokumentu2.getValue().equals("Raport pojazdy")) {
                            if (id_pojazdu.getText().equals("")) {
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

                                try {
                                    File plik = new File(droga_do_pliku);
                                    FileInputStream pis = new FileInputStream("C://" + tytul_dokumentu2.getText() + ".docx");
                                    dlugosc = (int) plik.length();
                                    zapytanie = ("INSERT INTO pojazdy_raporty VALUES(null,?,?,?,?,?,?)");
                                    Class.forName(sterownik);
                                    polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                    ps = polaczenie.prepareStatement(zapytanie);
                                    ps.setString(1, id_pojazdu.getText());
                                    ps.setString(2, idpracownika);
                                    ps.setString(3, tytul_dokumentu2.getText());
                                    ps.setString(4, dzisiaj2.format(format2));
                                    ps.setString(5, html);
                                    ps.setBinaryStream(6, pis, dlugosc);
                                    ps.executeUpdate();
                                    html = "";
                                    droga_do_pliku = "";
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    kom.komBrakDokumentu();
                                }
                            }else {

                            }
                        }else {
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

                            try {
                                File plik = new File(droga_do_pliku);
                                FileInputStream pis = new FileInputStream("C://" + tytul_dokumentu2.getText() + ".docx");
                                dlugosc = (int) plik.length();
                                zapytanie = ("INSERT INTO dokumenty_brygadzista VALUES(null,?,?,?,?,?,?)");
                                Class.forName(sterownik);
                                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                ps = polaczenie.prepareStatement(zapytanie);
                                ps.setString(1, idpracownika);
                                ps.setString(2, tytul_dokumentu2.getText());
                                ps.setString(3, dzisiaj2.format(format2));
                                ps.setString(4, rodzajDokumentu2());
                                ps.setBinaryStream(5, pis, dlugosc);
                                ps.setString(6, html);
                                ps.executeUpdate();
                                html = "";
                                droga_do_pliku = "";
                            } catch (Exception e) {
                                e.printStackTrace();
                                kom.komBrakDokumentu();
                            }
                        }
                    }else {
                        kom.komBrakRodzajuDokumentu();
                    }
                }else {
                    kom.komBrakDokumentu();
                }
            }
        });

        tabela4.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                firmy firma = tabela4.getSelectionModel().getSelectedItem();
                region.setText(firma.getFirma() +" "+ firma.getAdres() +" "+firma.getMiejscowosc());
                tabela4.setStyle("-fx-selection-bar: red; -fx-selection-bar-background-color: salmon; -fx-background-color: blue; ");

            }
        });

        tabela2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Osoby osoba = tabela2.getSelectionModel().getSelectedItem();
                pracownik.setText(osoba.getImię() +" "+ osoba.getImię() +" "+ osoba.getNazwisko());
            }
        });

        tabela3.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lista2 lista = tabela3.getSelectionModel().getSelectedItem();
                pojazd.setText(lista.getMarka() +" "+lista.getModel());
            }
        });

        przydział2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String p = pracownik.getText();
                String w = pojazd.getText();
                String r = region.getText();

                if (!(p.equals("") || w.equals("") || r.equals(""))) {
                    if (prydzialIstnieje(pracownik.getText(), pojazd.getText(), region.getText()) == false) {
                        zajete.setText("");
                        if (!(uwagi.getText().equals(""))) {
                            listap.add(pracownik.getText());
                            listaw.add(pojazd.getText());
                            listar.add(region.getText());
                            regionyprzydzial.appendText("<p>Pracownik : " + pracownik.getText() + " został przydzielony pojazd : " + pojazd.getText() + " i region : " + region.getText() + " Uwagi : " + uwagi.getText() + "</p>\n");
                        } else {
                            listap.add(pracownik.getText());
                            listaw.add(pojazd.getText());
                            listar.add(region.getText());
                            regionyprzydzial.appendText("<p>Pracownik : " + pracownik.getText() + " został przydzielony pojazd : " + pojazd.getText() + " i region : " + region.getText() + " Uwagi : brak</p>\n");
                        }
                    } else {
                        zajete.setText("Dane zajęte");
                        kom.komPrzydzialIstnieje();
                    }
                }else {
                    kom.komWszystkieDane();
                }
            }
        });

        wiadomosc_usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(wdu.isEmpty())) {
                    for (String w : wdu) {
                        log.wiadomoscUsuwanie(w,"wiadomosc_brygadzista");
                    }
                    pokazBazeWiadomosc();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        przydzialypokaz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(regionyprzydzial.getText() == "")) {
                    if (data.getValue() == null){
                        dzien = dzisiaj2.format(format2);
                    }else {
                        dzien = data.getValue().toString();
                    }
                    regionyprzydzial.appendText("Przydział na dzień " + dzien);
                    przydział.setHtmlText(regionyprzydzial.getText());
                }else {
                    kom.komBrakPrzydzialow();
                }
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
                silnik.executeScript("openFileFromBase64('" + postac64 + "')");
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
                silnik2.executeScript("openFileFromBase64('" + postac64 + "')");
            }
        });

        tabela6.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                wiadomosc wiadomosc = tabela6.getSelectionModel().getSelectedItem();
                idwiadomosc = wiadomosc.getId_wiadomosc().toString();

                tw.setText(wiadomosc.getTytul());
                dtw.setText(wiadomosc.getData_godzina());
                autor_wiadomosc.setText(wiadomosc.getImie() + " " + wiadomosc.getNazwisko());
                strony.getEngine().loadContent(wiadomosc.getTresc());
            }
        });

        tabela8.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                wiadomosc_dzialy wiadomosc = tabela8.getSelectionModel().getSelectedItem();

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

        tabela9.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                dokumenty_pojazdy dokumenty_pojazdy = tabela9.getSelectionModel().getSelectedItem();

                tw_pojazdy.setText(dokumenty_pojazdy.getTytul());
                dtw_pojazdy.setText(dokumenty_pojazdy.getData_godzina());
                autor_pojazd.setText(String.valueOf(dokumenty_pojazdy.getImie() + " " + dokumenty_pojazdy.getNazwisko()));
                strony_pojazdy.getEngine().loadContent(dokumenty_pojazdy.getPlikhtml());
            }
        });

        uraport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu2.getText().equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {
                        if (!(raport.getText().equals(""))) {
                    droga_do_pliku = "C:\\" + tytul_dokumentu2.getText() + ".docx";
                    XWPFDocument dokument = new XWPFDocument();
                    FileOutputStream poup = null;
                    try {
                        poup = new FileOutputStream(new File("C:\\" + tytul_dokumentu2.getText() + ".docx"));
                        XWPFParagraph paragraf = dokument.createParagraph();
                        XWPFRun tekst = paragraf.createRun();
                        tekst.setText("Tytuł: " + tytul_dokumentu2.getText() + "\n Typ raportu: " + rodzaj_dokumentu2.getValue() + "\n Na dzień: " + dzisiaj.format(format));
                        XWPFTable tabela = dokument.createTable();

                        XWPFTableRow wiersz = tabela.getRow(0);
                        wiersz.getCell(0).setText("Imie Nazwisko");
                        wiersz.addNewTableCell().setText("Identyfikator");
                        wiersz.addNewTableCell().setText("Dni przepracowane");
                        wiersz.addNewTableCell().setText("Dni urlopu");
                        wiersz.addNewTableCell().setText("Urlop");

                        for (listaPracownicyDni ld : listadoc) {
                            XWPFTableRow wierszDane = tabela.createRow();
                            wierszDane.getCell(0).setText(ld.getImie_nazwisko());
                            wierszDane.getCell(1).setText(ld.getIdentyfikator());
                            wierszDane.getCell(2).setText(ld.getLiczba_dni());
                            wierszDane.getCell(3).setText(ld.getUrlopd());
                            wierszDane.getCell(4).setText(ld.getUrlopc());
                        }
                        dokument.write(poup);
                        poup.close();
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

        uraport2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu2.getText().equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {
                        if (!(eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fc.getExtensionFilters().add(extentionFilter);
                String udom = System.getProperty("user.home");
                File lokacja = new File(udom);
                if (!lokacja.canRead()) {
                    lokacja = new File("c:/");
                }
                fc.setInitialDirectory(lokacja);
                fc.setTitle("Zapis raportu");
                File plik = fc.showSaveDialog(null);
                if (plik != null) {
                    try {
                        Document pdf2 = new Document();
                        try {
                            pw = PdfWriter.getInstance(pdf2, new FileOutputStream(plik));
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                        pdf2.open();
                        String str = eraport.getHtmlText();

                        XMLWorkerHelper xmlwh = XMLWorkerHelper.getInstance();
                        InputStream ist = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
                        xmlwh.parseXHtml(pw, pdf2, ist, Charset.forName("UTF-8"));
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

        zalacznik.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                droga_do_plikuz = log.drogaDoPliku();
                droga_zalacznik.setText(droga_do_plikuz);
                zalacznikplik = true;
            }
        });

        wiadomość3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tytul = tytul_wiadomosc.getText();
                String tresc = wiadomość2.getHtmlText();
                if (!(tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                    if (!(tytul.equals(""))) {
                        log.wiadomoscWysylanie3(zalacznikplik,droga_do_plikuz,"1",idpracownikan,tytul_wiadomosc.getText(),wiadomość2.getHtmlText(),"wiadomosc_pracownicy",tytul_wiadomosc.getText());
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
                            log.wiadomoscWysylanie3(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_pracownicy",tytul_wiadomosc.getText());
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
                Statement ko = polaczenie.createStatement();
                ResultSet res = ko.executeQuery(zapytanie);
                while(res.next()) {
                    pracownicydzialu.add(res.getString(1));
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
                Statement ko = polaczenie.createStatement();
                ResultSet res = ko.executeQuery("SELECT id_pracownika, imie, nazwisko FROM pracownicy WHERE identyfikator ="+pracownikbox.getSelectionModel().getSelectedItem());
                while(res.next()) {
                    imie_nazwisko.setText(res.getString(2)+ " " + res.getString(3));
                    identyfikator2 = res.getString(1);
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        craport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(raport2.getText().equals(""))) {
                    eraport.setHtmlText(raport2.getText());
                }else {
                    kom.komDokumentBrakTresc();
                }
            }
        });

        podglad2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                byte[] dane = new byte[0];
                try {
                    dane = FileUtils.readFileToByteArray(new File(log.drogaDoPliku2()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String postac64 = Base64.getEncoder().encodeToString(dane);
                silnik3.executeScript("openFileFromBase64('" + postac64 + "')");
            }
        });

        usun_zaznaczone_dokumenty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dok.isEmpty())) {
                    for (String d : dok) {
                        log.dokumentUsuwanie(d,"dokumenty_brygadzista");
                    }
                    pokazBazeDokumentuwBrygadzisty();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        usun_zaznaczone_dokumenty_pojazdy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dokp.isEmpty())) {
                    for (String d : dokp) {
                        log.dokumentUsuwanie(d,"dokumenty_brygadzista");
                    }
                    pokazBazeDokumentuwBrygadzisty();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        dodaj_wpis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                YearMonth my = YearMonth.of(dzisiaj.getYear(), dzisiaj.getMonth());
                if (!(liczba_dni.getText().equals(""))) {
                    int dni_wpisane = Integer.parseInt(liczba_dni.getText());
                    int dni_miesiaca = my.lengthOfMonth();
                    if (!(dni_wpisane > dni_miesiaca || dni_wpisane < 1)) {
                        String urlopd = "";
                        String urlopc = "";
                        if (!(du.isEmpty())) {
                            for (String d : du) {
                                urlopd = urlopd + " " + d + " ";
                            }
                        } else {
                            urlopd = " brak";
                        }
                        if (!(duc.isEmpty())) {
                            for (String dc : duc) {
                                urlopc = urlopc + " " + dc + " ";
                            }
                        } else {
                            urlopc = " brak";
                        }

                        if (dni > 0) {
                            dniow = dniow + dni;
                            if (!(dnio == 0 || dniow == 0)) {
                                if (dniow > dnio) {
                                    ButtonType tak = new ButtonType("Tak", ButtonBar.ButtonData.OK_DONE);
                                    ButtonType nie = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Pracownik wykorzystał wiecej dni urlopu niż przysługuje mu na tym stanowisku. Czy mimo to chcesz potwierdzić liczbe wykorzystanych dni urlopu przez pracownika?", tak, nie);

                                    alert.setTitle("Wykorzystany urlop");
                                    Optional<ButtonType> wynik = alert.showAndWait();

                                    if (wynik.isPresent() && wynik.get() == tak) {
                                        try {
                                            Class.forName(sterownik);
                                            polaczenie = DriverManager.getConnection(baza);
                                            PreparedStatement ps = polaczenie.prepareStatement("UPDATE pracownicy SET liczba_dni_urlopu_wykorzystane = ? WHERE id_pracownika = ?");
                                            ps.setString(1, String.valueOf(dni));
                                            ps.setString(2, String.valueOf(id));
                                            int status = ps.executeUpdate();
                                            dni = 0;
                                            dnio = 0;
                                            dniow = 0;
                                        } catch (ClassNotFoundException ex) {
                                            ex.printStackTrace();
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                } else {
                                    try {
                                        Class.forName(sterownik);
                                        polaczenie = DriverManager.getConnection(baza);
                                        PreparedStatement ps = polaczenie.prepareStatement("UPDATE pracownicy SET liczba_dni_urlopu_wykorzystane = ? WHERE id_pracownika = ?");
                                        ps.setString(1, String.valueOf(dni));
                                        ps.setString(2, String.valueOf(id));
                                        int status = ps.executeUpdate();
                                        dni = 0;
                                        dnio = 0;
                                        dniow = 0;
                                    } catch (ClassNotFoundException ex) {
                                        ex.printStackTrace();
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            } else {
                                kom.komPracownikDane();
                            }
                        }

                        if (!(imier.getText() == "" || nazwiskor.getText() == "" || identyfikatorr.getText() == "" || liczba_dni.getText() == "")) {
                            String wpis = "Pracownik " + imier.getText() + " " + nazwiskor.getText() + " Identyfikator: " + identyfikatorr.getText() + " liczba przepracowanych dni w miesiącu: " + liczba_dni.getText() + " Dni wolne: " + urlopd + " Urlopy: " + urlopc + "\n";
                            listadoc.add(new listaPracownicyDni(imier.getText() + " " + nazwiskor.getText(), identyfikatorr.getText(), liczba_dni.getText(), urlopd, urlopc));
                            raport.appendText(wpis);
                            raport2.appendText("<p>" + wpis + "</p>");
                            du.clear();
                            duc.clear();
                            urlopd = "";
                            urlopc = "";
                        } else {
                            kom.komWszystkieDane();
                        }
                    } else {
                        kom.komLiczbaDni();
                    }
                }else {
                    kom.komLiczbaDniPusta();
                }
            }
        });

        dodaj_urlop_dzien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean czy_jest = false;
                if (!(datau.getValue() == null)) {
                    for (String u: du) {
                        if (u.equals(datau.getValue().toString())){
                            czy_jest = true;
                            kom.komUrlopDzien();
                        }
                    }
                    if (czy_jest == false) {
                        du.add(datau.getValue().toString());
                        dni++;
                    }
                }else {
                    kom.komUrlopDzienBrak();
                }
            }
        });

        usun_urlop_dzien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(datau.getValue() == null)) {
                    for (String u: du) {
                        if (u.equals(datau.getValue().toString())){
                            if (dni > 0){
                                dni--;
                                du.remove(datau.getValue().toString());
                            }
                        }
                    }
                }else {
                    kom.komUrlopDzienBrak();
                }
            }
        });

        dodaj_urlop_ciagly.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean czy_jest = false;
                if (!(datauod.getValue() == null || dataudo.getValue() == null)) {
                    if (datauod.getValue().isBefore(dataudo.getValue())) {
                        for (String u : duc) {
                            if (u.equals(datauod.getValue().toString() + " - " + dataudo.getValue().toString())) {
                                czy_jest = true;
                                kom.komUrlop();
                            }
                        }
                        if (czy_jest == false) {
                            duc.add(datauod.getValue().toString() + " - " + dataudo.getValue().toString());
                            dni = dni + ChronoUnit.DAYS.between(datauod.getValue(), dataudo.getValue());
                        }
                    }else {
                        kom.komUrlopDaty();
                    }
                }else {
                    kom.komUrlopBrak();
                }
            }
        });

        usun_urlop_ciagly.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(datauod.getValue() == null || dataudo.getValue() == null)) {
                    if (datauod.getValue().isBefore(dataudo.getValue())) {
                    for (String u: duc) {
                        if (u.equals(datauod.getValue().toString() + " - " + dataudo.getValue().toString())){
                            if (dni > 0 && dni >= ChronoUnit.DAYS.between(datauod.getValue(), dataudo.getValue())) {
                                dni = dni - ChronoUnit.DAYS.between(datauod.getValue(), dataudo.getValue());
                                duc.remove(datauod.getValue().toString() + " - " + dataudo.getValue().toString());
                            }
                        }
                    }
                    }else {
                        kom.komUrlopDaty();
                    }
                }else {
                    kom.komUrlopBrak();
                }
            }
        });

        tabela7.getSelectionModel().selectedItemProperty().addListener((obs, nowyWybor, staryWybor) -> {
            if (staryWybor != null) {
                dokumenty_ksiegowosic dokument = tabela7.getSelectionModel().getSelectedItem();

                web.getEngine().loadContent(dokument.getPlikhtml());
            }
        });

        tabela5.getSelectionModel().selectedItemProperty().addListener((obs, nowyWybor, staryWybor) -> {
            if (staryWybor != null) {
                Osoby osoba = tabela5.getSelectionModel().getSelectedItem();

                imier.setText(osoba.getImię());
                nazwiskor.setText(osoba.getNazwisko());
                identyfikatorr.setText(osoba.getIdentyfikator().toString());
                dnio = osoba.getDniu();
                dniow = osoba.getDniuw();
                id = osoba.getId_pracownika();
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

    public void pokazBazePracownikow(){
        sql = "Select pracownicy.id_pracownika, dzial.nazwa_dzialu, stanowiska.nazwa_stanowiska, pracownicy.imie, pracownicy.nazwisko, pracownicy.pesel, pracownicy.telefon, pracownicy.adres, pracownicy.email, pracownicy.identyfikator, pracownicy.haslo, pracownicy.liczba_dni_urlopu_wykorzystane, stanowiska.liczba_dni_urlopu From pracownicy JOIN dzial ON pracownicy.id_dzialu = dzial.id_dzialu JOIN stanowiska ON pracownicy.id_stanowiska = stanowiska.id_stanowiska";
        kolumna2.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("id_pracownika"));
        kolumna1.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Imię"));
        kolumna3.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Nazwisko"));
        kolumna4.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("PESEL"));
        kolumna5.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("Nrtelefonu"));
        kolumna6.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Adres"));
        kolumna7.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Działp"));
        kolumna8.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Stanowisko"));
        kolumna9.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Adresemail"));
        kolumna10.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("Identyfikator"));
        tabela.getItems().setAll(baz.baza4(sql));
        kolumna13.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("id_pracownika"));
        kolumna14.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Imię"));
        kolumna15.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Nazwisko"));
        tabela2.getItems().setAll(baz.baza4(sql));
        kolumna19.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("id_pracownika"));
        kolumna20.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Imię"));
        kolumna21.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Nazwisko"));
        tabela5.getItems().setAll(baz.baza4(sql));
    }
    public void pokazBazePojazdow(){
        sql2 = "Select * from pojazdy";
        kolumna16.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("id_pojazdu"));
        kolumna11.setCellValueFactory(new PropertyValueFactory<lista2, String>("Marka"));
        kolumna12.setCellValueFactory(new PropertyValueFactory<lista2, String>("Model"));
        tabela3.getItems().setAll(baz.baza5(sql2));
    }
    public void pokazBazeRegionow(){
        sql3 = "Select * from regiony_dostaw";
        kolumna17.setCellValueFactory(new PropertyValueFactory<firmy, Integer>("id_regionu"));
        kolumna18.setCellValueFactory(new PropertyValueFactory<firmy, String>("firma"));
        tabela4.getItems().setAll(baz.baza10(sql3));
    }
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
    public Boolean prydzialIstnieje(String pp, String pw, String pr){
        Boolean p = false;
        for (String lp : listap) {
            if (lp.equals(pp)){
                p = true;
            }
        }
        for (String lw : listaw) {
            if (lw.equals(pw)){
                p = true;
            }
        }
        for (String lr : listar) {
            if (lr.equals(pr)){
                p = true;
            }
        }
        return p;
    }
    public void pokazBazeWiadomosc(){
        sql6 = "Select wiadomosc_brygadzista.id_wiadomosc, pracownicy.imie, pracownicy.nazwisko, wiadomosc_brygadzista.tytul, wiadomosc_brygadzista.tresc, wiadomosc_brygadzista.data_godzina, wiadomosc_brygadzista.typ_przeczytana_nieprzeczytana, wiadomosc_brygadzista.plik_tak_nie from wiadomosc_brygadzista JOIN pracownicy ON pracownicy.id_pracownika = wiadomosc_brygadzista.id_pracownikan where wiadomosc_brygadzista.id_pracownika =" + idpracownikan + " ORDER BY id_wiadomosc DESC";
        kolumna22.setCellValueFactory(new PropertyValueFactory<wiadomosc, String>("tytul"));
        kolumna23.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
        kolumna23.setCellFactory(celaf);
        kolumna24.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna24.setCellFactory(celaf2);
        kolumna25.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

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
                                log.pobierzZalacznik(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_brygadzista");
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
        kolumna25.setCellFactory(celaf3);
        kolumna26.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

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
                                log.wiadomoscUsuwanie(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_brygadzista");
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
        kolumna26.setCellFactory(celaf4);
        tabela6.getItems().setAll(baz.baza(sql6));
    }
    public void pokazBazeWiadomoscDzialy(){
        sql7 = "Select wiadomosci_dzialy.id_wiadomosc, dzial.nazwa_dzialu, wiadomosci_dzialy.tytul, wiadomosci_dzialy.data_godzina, wiadomosci_dzialy.tresc, wiadomosci_dzialy.typ_przeczytana_nieprzeczytana from wiadomosci_dzialy JOIN dzial ON wiadomosci_dzialy.id_dzialu = dzial.id_dzialu WHERE nazwa_dzialu = 'Brygadzista' ORDER BY id_wiadomosc DESC";
        kolumna34.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("tytul"));
        kolumna35.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
        kolumna35.setCellFactory(celaf);
        kolumna36.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("data_godzina"));
        kolumna37.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna37.setCellFactory(celaf2);
        tabela8.getItems().setAll(baz.baza3(sql7));
    }
    public void pokazBazeDokumentuwBrygadzisty(){
        sql5 = "Select dokumenty_brygadzista.id_dokumentu, dokumenty_brygadzista.id_pracownika, dokumenty_brygadzista.tytul, dokumenty_brygadzista.data_godzina, dokumenty_rodzaje.rodzaj, dokumenty_brygadzista.plikhtml from dokumenty_brygadzista JOIN dokumenty_rodzaje ON dokumenty_rodzaje.id_rodzaju_dokumentu = dokumenty_brygadzista.id_rodzaju_dokumentu where id_pracownika =" +idpracownikan;
        kolumna27.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("tytul"));
        kolumna28.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("data_godzina"));
        kolumna29.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("rodzaj"));
        kolumna30.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna30.setCellFactory(celaf);
        kolumna33.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
                                log.dokumentUsuwanie(dokument.getId_dokumentu().toString(),"dokumenty_brygadzista");
                                pokazBazeDokumentuwBrygadzisty();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna33.setCellFactory(celaf2);
        kolumna31.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

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
                            if (dokument.getPlikhtml() == "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>") {
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
        kolumna31.setCellFactory(celaf3);
        kolumna32.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

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
                                log.dokumentPobierz(dokument.getId_dokumentu().toString(),"dokumenty_brygadzista");
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna32.setCellFactory(celaf4);
        tabela7.getItems().setAll(baz.baza2(sql5));
    }
    public void pokazBazeDokumentuwPojazdy(){
        sql8 = "Select pojazdy_raporty.id_raportu_pojazdu, pojazdy_raporty.id_pojazdu, pracownicy.imie, pracownicy.nazwisko, pojazdy_raporty.tytul, pojazdy_raporty.data_godzina, pojazdy_raporty.plikhtml from pojazdy_raporty JOIN pracownicy ON pracownicy.id_pracownika = pojazdy_raporty.id_pracownika";
        kolumna38.setCellValueFactory(new PropertyValueFactory<dokumenty_pojazdy, String>("tytul"));
        kolumna39.setCellValueFactory(new PropertyValueFactory<dokumenty_pojazdy, String>("data_godzina"));
        kolumna40.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>> celaf = new Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_pojazdy, String> p) {
                final TableCell<dokumenty_pojazdy, String> cela = new TableCell<dokumenty_pojazdy, String>() {

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
                                        dokumenty_pojazdy dokument = getTableView().getItems().get(getIndex());
                                        dokp.add(String.valueOf(dokument.getId_raportu_pojazdu()));
                                    } else {
                                        dokumenty_pojazdy dokument = getTableView().getItems().get(getIndex());
                                        dokp.remove(String.valueOf(dokument.getId_raportu_pojazdu()));
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
        kolumna40.setCellFactory(celaf);
        kolumna43.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>> celaf2 = new Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_pojazdy, String> p) {
                final TableCell<dokumenty_pojazdy, String> cela = new TableCell<dokumenty_pojazdy, String>() {

                    final JFXButton btn = new JFXButton("Usuń dokument");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_pojazdy dokument = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.dokumentUsuwanie(String.valueOf(dokument.getId_raportu_pojazdu()),"pojazdy_raporty");
                                pokazBazeDokumentuwPojazdy();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna43.setCellFactory(celaf2);
        kolumna41.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

        Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>> celaf3 = new Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_pojazdy, String> p) {
                final TableCell<dokumenty_pojazdy, String> cela = new TableCell<dokumenty_pojazdy, String>() {

                    final JFXButton btn = new JFXButton("Generuj PDF");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_pojazdy dokument = getTableView().getItems().get(getIndex());
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
        kolumna41.setCellFactory(celaf3);
        kolumna42.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

        Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>> celaf4 = new Callback<TableColumn<dokumenty_pojazdy, String>, TableCell<dokumenty_pojazdy, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_pojazdy, String> p) {
                final TableCell<dokumenty_pojazdy, String> cela = new TableCell<dokumenty_pojazdy, String>() {

                    final JFXButton btn = new JFXButton("Pobierz raport");

                    @Override
                    public void updateItem(String pozycja, boolean pusto) {
                        super.updateItem(pozycja, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_pojazdy dokument = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                log.dokumentPobierz(String.valueOf(dokument.getId_raportu_pojazdu()),"pojazdy_raporty");
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna42.setCellFactory(celaf4);
        tabela9.getItems().setAll(baz.baza11(sql8));
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
    public void setText(String login, String haslo, String id){
        identyfikator = login;
        haslodane = haslo;
        idpracownikan = id;
    }
}