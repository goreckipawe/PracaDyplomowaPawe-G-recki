//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Paweł on 2016-05-10.
 */
public class kierownik implements Initializable {

    private static String baza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private static PDFTextStripper pdfs;
    private static PDDocument pddocg;
    private static COSDocument cosdocg;

    private static String Text ;

    private static String txt;
    private static String t;
    private static String htmlText;
    private static String linkbaza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private static String sterownik = "org.sqlite.JDBC";
    private static String sql;
    private static String sql2;
    private static String sql3;
    private static String sql4;
    private static String sql5;
    private static String sql6;
    private static String sql7;
    private static String pole;
    private static String pole2;
    private static String droga;
    private static Connection poloczenie;
    private static Statement st;
    private static ResultSet rs;
    private static WebEngine silnik;
    private static WebEngine silnik2;
    private static PdfWriter writer;
    private static String idpracownika;
    private static String idpojazdu;
    private static String idwiadomosc;
    private static String identyfikator = "";
    private static String haslodane = "";
    private static String idpracownikan = "";
    private static String droga_do_plikuz = "";
    private static String identyfikator2 = "";
    private static String dzial = "";
    private static String stanowisko = "";
    private static Boolean zalacznikplik = false;
    private static DateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
    private static List<String> dok=new ArrayList<String>();
    private static LocalDate dzisiaj = LocalDate.now();
    private static DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static ObservableList<String> lista1 = FXCollections.observableArrayList(
            "Kierownictwo", "Księgowość", "Magazyn", "Brygadzista", "Transport/Pracownicy"
    );
    private static ObservableList<String> lista2 = FXCollections.observableArrayList(
            "Właściciel", "Księgowy", "Brygadzista", "Magazynier", "Pracownik", "Kierowca"
    );
    private static ObservableList<String> lista3 = FXCollections.observableArrayList(
            "Sprawny", "Uszkodzony", "W naprawie"
    );
    private static ObservableList<String> lista4 = FXCollections.observableArrayList(
            "Kierownictwo", "Księgowość", "Magazyn", "Brygadzista", "Transport/Pracownicy"
    );

    private static ObservableList<String> pracownicydzialu = FXCollections.observableArrayList();

    private static List<String> wdu=new ArrayList<String>();
    private static List<String> wdu2=new ArrayList<String>();

    private static komunikaty kom = new komunikaty();
    private static logika log = new logika();
    private static baza baz = new baza();

    @FXML
    private ImageView obraz;
    @FXML
    private LineChart<String,Number> dochody;
    @FXML
    private BarChart<String,Number> dochody2;
    @FXML
    private DatePicker drokz;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
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
    private Label lurlop;
    @FXML
    private Label markal;
    @FXML
    private Label modell;
    @FXML
    private Label rokl;
    @FXML
    private Label pojemnoscl;
    @FXML
    private Label przebiegl;
    @FXML
    private Label nrrejestracyjnyl;
    @FXML
    private Label ubezpieczeniel;
    @FXML
    private Label uszkodzonyl;
    @FXML
    private Label data_zakupul;
    @FXML
    private JFXButton wykres1;
    @FXML
    private JFXButton wykres2;
    @FXML
    private JFXButton otwurz;
    @FXML
    private JFXButton magazynier1;
    @FXML
    private JFXButton brygadzista1;
    @FXML
    private JFXButton księgowy1;
    @FXML
    private JFXButton historia2;
    @FXML
    private JFXButton podglad;
    @FXML
    private JFXButton wstecz;
    @FXML
    private JFXButton aimie;
    @FXML
    private JFXButton anazwisko;
    @FXML
    private JFXButton atelefon;
    @FXML
    private JFXButton ahaslo;
    @FXML
    private JFXButton astanowisko;
    @FXML
    private JFXButton awszystko;
    @FXML
    private JFXButton amarka;
    @FXML
    private JFXButton astan;
    @FXML
    private JFXButton aoc;
    @FXML
    private JFXButton aprzebieg;
    @FXML
    private JFXButton arokzakupu;
    @FXML
    private JFXButton awszystko2;
    @FXML
    private JFXButton bdodaj1;
    @FXML
    private JFXButton bdodaj2;
    @FXML
    private JFXButton bu1;
    @FXML
    private JFXButton bu2;
    @FXML
    private JFXButton baza1;
    @FXML
    private JFXButton baza2;
    @FXML
    private JFXButton apesel;
    @FXML
    private JFXButton aadres;
    @FXML
    private JFXButton adział;
    @FXML
    private JFXButton amail;
    @FXML
    private JFXButton aidentyfikator;
    @FXML
    private JFXButton zapisz_do_plikul;
    @FXML
    private JFXButton amodel;
    @FXML
    private JFXButton arok;
    @FXML
    private JFXButton apojemnosc;
    @FXML
    private JFXButton arejestracja;
    @FXML
    private JFXButton wiadomosc_usun2;
    @FXML
    private JFXButton zmien_zdjęcie_pojazdu;
    @FXML
    private JFXButton usun_zaznaczone_dokumenty;
    @FXML
    private JFXButton pracownik1;
    @FXML
    private WebView strony;
    @FXML
    private WebView strona;
    @FXML
    private WebView dok1;
    @FXML
    private WebView dok2;
    @FXML
    private MenuItem zamknij;
    @FXML
    private MenuItem opis;
    @FXML
    private MenuItem autorzy;
    @FXML
    private MenuItem wersja;
    @FXML
    private TableView<Osoby> tabela;
    @FXML
    private TableView<lista2> tabela2;
    @FXML
    private TableView<wiadomosc> tabela3;
    @FXML
    private TableView<dokumenty_kierownictwo> tabela4;
    @FXML
    private TableView<wiadomosc_dzialy> tabela5;
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
    private JFXTextField tw2;
    @FXML
    private JFXTextField dtw2;
    @FXML
    private WebView strony2;
    @FXML
    private JFXTextField ai;
    @FXML
    private JFXTextField an;
    @FXML
    private JFXTextField at;
    @FXML
    private JFXTextField ah;
    @FXML
    private ComboBox<String> as;
    @FXML
    private ComboBox<String> as2;
    @FXML
    private JFXTextField am;
    @FXML
    private JFXTextField ao;
    @FXML
    private JFXTextField ap;
    @FXML
    private DatePicker arz;
    @FXML
    private JFXTextField di;
    @FXML
    private JFXTextField dn;
    @FXML
    private JFXTextField dt;
    @FXML
    private JFXTextField dh;
    @FXML
    private ComboBox<String> dsta;
    @FXML
    private JFXTextField dm;
    @FXML
    private ComboBox<String> dst;
    @FXML
    private JFXTextField doc;
    @FXML
    private JFXTextField dprz;
    @FXML
    private JFXTextField dpe;
    @FXML
    private JFXTextField da;
    @FXML
    private ComboBox<String> dd;
    @FXML
    private JFXTextField did;
    @FXML
    private JFXTextField de;
    @FXML
    private JFXTextField dmo;
    @FXML
    private DatePicker dr;
    @FXML
    private JFXTextField dpj;
    @FXML
    private JFXTextField dnr;
    @FXML
    private JFXTextField uid;
    @FXML
    private JFXTextField uid2;
    @FXML
    private JFXTextField ape;
    @FXML
    private JFXTextField aa;
    @FXML
    private ComboBox<String> ad;
    @FXML
    private JFXTextField ama;
    @FXML
    private JFXTextField aide;
    @FXML
    private JFXTextField amo;
    @FXML
    private DatePicker ar;
    @FXML
    private JFXTextField apo;
    @FXML
    private JFXTextField anr;
    @FXML
    private JFXTextField drogazs;
    @FXML
    private JFXTextField powtorz_haslo;
    @FXML
    private JFXTextField powtorz_haslo1;
    @FXML
    private JFXCheckBox boxwp;
    @FXML
    private JFXCheckBox boxpp;
    @FXML
    private JFXCheckBox boxpop;
    @FXML
    private JFXTextField tw;
    @FXML
    private JFXTextField dtw;
    @FXML
    private JFXTextField autor_wiadomosc;
    @FXML
    private JFXButton wiadomość4;
    @FXML
    private JFXButton zalacznik;
    @FXML
    private JFXTextField tytul_wiadomosc;
    @FXML
    private HTMLEditor wiadomość2;
    @FXML
    private JFXTextField droga_zalacznik;
    @FXML
    private ComboBox dzialbox;
    @FXML
    private ComboBox pracownikbox;
    @FXML
    private JFXTextField imie_nazwisko;

    public void initialize(URL url, ResourceBundle rb) {
        pokazBazePracownikow();
        pokazBazePojazdow();
        pokazBazeWiadomosc();
        pokazBazeDokumentowKierownictwa();
        pokazBazeWiadomoscDzialy();
        ad.setItems(lista1);
        dd.setItems(lista1);
        as.setItems(lista2);
        dsta.setItems(lista2);
        as2.setItems(lista3);
        dst.setItems(lista3);
        dzialbox.setItems(lista4);

        tabela.setTableMenuButtonVisible(true);
        tabela2.setTableMenuButtonVisible(true);
        tabela3.setTableMenuButtonVisible(true);
        tabela4.setTableMenuButtonVisible(true);
        tabela5.setTableMenuButtonVisible(true);

        String link = getClass().getResource("/web/viewer.html").toExternalForm();

        silnik = dok1.getEngine();
        silnik.setJavaScriptEnabled(true);
        silnik.load(link);

        silnik2 = dok2.getEngine();
        silnik2.setJavaScriptEnabled(true);
        silnik2.load(link);

        at.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        ape.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        aide.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        ap.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        ar.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        apo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        dt.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        dprz.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        dpe.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        did.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        dr.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        dpj.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        uid.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        ai.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        an.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        am.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        ao.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        amo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        di.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        dn.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        dm.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        dmo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        uid2.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"123456789".contains(keyEvent.getCharacter())) {
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
                kom.komPomocKierownik();
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
                Scene scenaopocji = new Scene(opcje);
                Stage estrada = (Stage) ((Node) event.getSource()).getScene().getWindow();
                estrada.hide();
                estrada.setScene(scenaopocji);
                estrada.show();
            }
        });

        otwurz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                byte[] dane = new byte[0];
                try {
                    dane = FileUtils.readFileToByteArray(new File(log.drogaDoPliku2()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String postac64 = Base64.getEncoder().encodeToString(dane);
                silnik.executeScript("openFileFromBase64('" + postac64 + "')");
            }
        });

        magazynier1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                htmlText = "3";
                wiadomoscDzialy(htmlText);
            }
        });

        brygadzista1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                htmlText = "4";
                wiadomoscDzialy(htmlText);
            }
        });

        pracownik1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                htmlText = "5";
                wiadomoscDzialy(htmlText);
            }
        });

        księgowy1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                htmlText = "2";
                wiadomoscDzialy(htmlText);
            }
        });

        tabela5.getSelectionModel().selectedItemProperty().addListener((obs, stareZaznacczenie, noweZaznaczenie) -> {
            if (noweZaznaczenie != null) {
                wiadomosc_dzialy wiadomosc = tabela5.getSelectionModel().getSelectedItem();

                tw2.setText(wiadomosc.getTytul());
                dtw2.setText(wiadomosc.getData_godzina());
                strony2.getEngine().loadContent(wiadomosc.getTresc());
            }
        });

        historia2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(wdu.isEmpty())) {
                    for (String w : wdu) {
                        log.wiadomoscUsuwanie(w,"wiadomosc_pracownicy");
                    }
                    pokazBazeWiadomosc();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        wiadomosc_usun2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(wdu2.isEmpty())) {
                    for (String w : wdu2) {
                        wiadomoscUsuwanie2(w);
                    }
                    pokazBazeWiadomoscDzialy();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        usun_zaznaczone_dokumenty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dok.isEmpty())) {
                    for (String d : dok) {
                        log.dokumentUsuwanie(d,"raporty_kierownictwo");
                    }
                    pokazBazeDokumentowKierownictwa();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        zapisz_do_plikul.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (idpojazdu == null) {
                    kom.komPojazdNieWybrano();
                }else {
                FileChooser wybieraczp = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                wybieraczp.getExtensionFilters().add(filter);
                String uds = System.getProperty("user.home");
                File ud = new File(uds);
                if (!ud.canRead()) {
                    ud = new File("c:/");
                }
                 wybieraczp.setInitialDirectory(ud);
                 wybieraczp.setTitle("Zapis raportu");
                File plik =  wybieraczp.showSaveDialog(null);
                if (plik != null) {
                    try {
                        Document pdf2 = new Document();
                        try {
                            writer = PdfWriter.getInstance(pdf2, new FileOutputStream(plik));
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                        pdf2.open();
                        String tekst = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Dane pojazdu</p><p>Na dzien: "+ dzisiaj.format(formatd) +"</p><p>Marka: " + markal.getText() + "</p><p>Model: " + modell.getText() + "</p><p>Rocznik: " + rokl.getText() + "</p><p>Pojemność: " + pojemnoscl.getText() + "</p><p>Przebieg: " + przebiegl.getText() + "</p><p>Nr rejestracyjny: " + nrrejestracyjnyl.getText() + "</p><p>Ubezpieczenie: " + ubezpieczeniel.getText() + "</p><p>Stan: " + uszkodzonyl.getText() + "</p><p>Rok zakupu: " + data_zakupul.getText() + "</p></body></html>";

                        XMLWorkerHelper parserhtml = XMLWorkerHelper.getInstance();
                        InputStream is = new ByteArrayInputStream(tekst.getBytes(StandardCharsets.UTF_8));
                        parserhtml.parseXHtml(writer, pdf2, is, Charset.forName("UTF-8"));
                        pdf2.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                }
            }
        });

        podglad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser wybieraczp = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
                wybieraczp.getExtensionFilters().add(filter);

                String uds = System.getProperty("user.home");
                File ud = new File(uds);
                if(!ud.canRead()) {
                    ud = new File("c:/");
                }
                wybieraczp.setInitialDirectory(ud);

                File plik = wybieraczp.showOpenDialog(null);
                String droga;
                if(plik != null) {
                    droga = plik.getPath();
                } else {
                    droga = null;
                }
                File f = new File(droga);
                try {
                    strona.getEngine().load(f.toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        baza1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pokazBazePracownikow();
            }
        });

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, stareZaznacczenie, noweZaznaczenie) -> {
            if (noweZaznaczenie != null) {
                Osoby osoba = tabela.getSelectionModel().getSelectedItem();
                idpracownika = osoba.getId_pracownika().toString();
                limię.setText(osoba.getImię());
                lnazwisko.setText(osoba.getNazwisko());
                lpesel.setText(osoba.getPESEL().toString());
                ltelefon.setText(osoba.getNrtelefonu().toString());
                ladres.setText(osoba.getAdres());
                ldzial.setText(osoba.getDziałp());
                lemail.setText(osoba.getAdresemail());
                lstanowisko.setText(osoba.getStanowisko());
                lidentyfikator.setText(osoba.getIdentyfikator().toString());
                lhaslo.setText(osoba.getHasło());
                lurlop.setText(String.valueOf(osoba.getDniuw()));
                ai.setText(osoba.getImię());
                an.setText(osoba.getNazwisko());
                ape.setText(osoba.getPESEL().toString());
                at.setText(osoba.getNrtelefonu().toString());
                aa.setText(osoba.getAdres());
                ama.setText(osoba.getAdresemail());
                aide.setText(osoba.getIdentyfikator().toString());
                ah.setText(osoba.getHasło());
                ad.setValue(osoba.getDziałp());
                as.setValue(osoba.getStanowisko());
                dzial = log.dzial(ad.getValue());
                System.out.println(dzial);
                stanowisko = log.stanowisko(as.getValue());
                System.out.println(stanowisko);
            }
        });

        tabela2.getSelectionModel().selectedItemProperty().addListener((obs, stareZaznacczenie, noweZaznaczenie) -> {
            if (noweZaznaczenie != null) {
                lista2 lista2 = tabela2.getSelectionModel().getSelectedItem();
                idpojazdu = lista2.getId_pojazdu().toString();
                try {
                    Class.forName(sterownik);
                    poloczenie = DriverManager.getConnection(baza);
                    st = poloczenie.createStatement();
                    String zapytanie = ("Select zdjecie From pojazdy WHERE id_pojazdu = " + lista2.getId_pojazdu());
                    rs = st.executeQuery(zapytanie);
                    while (rs.next()) {
                        Blob b = rs.getBlob(1);
                        byte dane[] = b.getBytes(1,(int)b.length());
                        if (!(dane == null)) {
                            ByteArrayInputStream bais = new ByteArrayInputStream(dane);
                            try {
                                BufferedImage obrazb = ImageIO.read(bais);
                                obraz.setImage(SwingFXUtils.toFXImage(obrazb, null));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {

                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String rokw = lista2.getRok().substring(0,lista2.getRok().length()-6 );

                markal.setText(lista2.getMarka());
                modell.setText(lista2.getModel());
                rokl.setText(rokw);
                pojemnoscl.setText(lista2.getPojemność().toString());
                przebiegl.setText(lista2.getPrzebieg().toString());
                nrrejestracyjnyl.setText(lista2.getNrrejestracyjny());
                ubezpieczeniel.setText(lista2.getUbezpieczenie());
                uszkodzonyl.setText(lista2.getUszkodzony());
                data_zakupul.setText(lista2.getRokzakupu());

                am.setText(lista2.getMarka());
                amo.setText(lista2.getModel());
                LocalDate rok = LocalDate.parse(lista2.getRok());
                ar.setValue(rok);
                apo.setText(lista2.getPojemność().toString());
                anr.setText(lista2.getNrrejestracyjny());
                ao.setText(lista2.getUbezpieczenie());
                ap.setText(lista2.getPrzebieg().toString());
                LocalDate rokz = LocalDate.parse(lista2.getRokzakupu());
                arz.setValue(rokz);

                as2.setValue(lista2.getUszkodzony());
            }
        });

        tabela3.getSelectionModel().selectedItemProperty().addListener((obs, stareZaznacczenie, noweZaznaczenie) -> {
            if (noweZaznaczenie != null) {
                wiadomosc wiadomosc = tabela3.getSelectionModel().getSelectedItem();
                idwiadomosc = wiadomosc.getId_wiadomosc().toString();

                tw.setText(wiadomosc.getTytul());
                dtw.setText(wiadomosc.getData_godzina());
                autor_wiadomosc.setText(wiadomosc.getImie() + " " + wiadomosc.getNazwisko());
                strony.getEngine().loadContent(wiadomosc.getTresc());
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

        dzialbox.setOnAction((e) -> {
            pracownicydzialu.clear();
            String sqlzap = "";
            if(dzialbox.getSelectionModel().getSelectedItem() == "Kierownictwo"){
                sqlzap = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 1";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Księgowość"){
                sqlzap = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 2";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Magazyn"){
                sqlzap = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 3";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Brygadzista"){
                sqlzap = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 4";
            }else if (dzialbox.getSelectionModel().getSelectedItem() == "Transport/Pracownicy"){
                sqlzap = "SELECT identyfikator FROM pracownicy WHERE id_dzialu = 5";
            }
            try {
                Class.forName(sterownik);
                poloczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement st = poloczenie.createStatement();
                ResultSet r = st.executeQuery(sqlzap);
                while(r.next()) {
                    pracownicydzialu.add(r.getString(1));
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
                poloczenie = DriverManager.getConnection(String.valueOf(baza));
                Statement s = poloczenie.createStatement();
                ResultSet rsz = s.executeQuery("SELECT id_pracownika, imie, nazwisko FROM pracownicy WHERE identyfikator ="+pracownikbox.getSelectionModel().getSelectedItem());
                while(rsz.next()) {
                    imie_nazwisko.setText(rsz.getString(2)+ " " + rsz.getString(3));
                    identyfikator2 = rsz.getString(1);
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
                                    log.wiadomoscWysylanie(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_pracownicy");
                                    zalacznikplik = false;
                                    droga_do_plikuz = "";
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Księgowość") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_ksiegowosic");
                                    zalacznikplik = false;
                                    droga_do_plikuz = "";
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Magazyn") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_magazyniera");
                                    System.out.println(zalacznikplik);
                                    droga_zalacznik.setText("");
                                    zalacznikplik = false;
                                    droga_do_plikuz = "";
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Brygadzista") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_brygadzista");
                                    droga_zalacznik.setText("");
                                    System.out.println(zalacznikplik);
                                    zalacznikplik = false;
                                    droga_do_plikuz = "";
                                } else if (dzialbox.getSelectionModel().getSelectedItem() == "Transport/Pracownicy") {
                                    log.wiadomoscWysylanie2(zalacznikplik, droga_do_plikuz, identyfikator2,idpracownikan, tytul_wiadomosc.getText(), wiadomość2.getHtmlText(), "wiadomosc_kierowcyPracownicy");
                                    zalacznikplik = false;
                                    droga_do_plikuz = "";
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

        zmien_zdjęcie_pojazdu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser wybieracz = new FileChooser();

                FileChooser.ExtensionFilter eFJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter eFPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                wybieracz.getExtensionFilters().addAll(eFJPG,  eFPNG);

                File plik = wybieracz.showOpenDialog(null);
                drogazs.setText(plik.toString());

                BufferedImage bfi;
                try {
                    bfi = ImageIO.read(plik);
                    FileInputStream fis = new FileInputStream(plik);
                    int dlugosc = (int)plik.length();
                    poloczenie = DriverManager.getConnection(baza);
                    PreparedStatement psz = poloczenie.prepareStatement("UPDATE pojazdy SET zdjecie = ? WHERE id_pojazdu = ?");
                    psz.setBinaryStream(1,fis,dlugosc);
                    psz.setString(2, idpojazdu);
                    int status = psz.executeUpdate();
                    if(status>0)
                    {
                        kom.komZdjecieAktualizacja();
                    }
                    else
                    {
                        kom.komZdjecieAktualizacjaBlad();
                    }
                    poloczenie.close();
                } catch (IOException ex) {
                    Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(kierownik.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        baza2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pokazBazePojazdow();
            }
        });

        aimie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ai.getText().equals(""))) {
                    pole = ai.getText();
                    sql3 = "UPDATE pracownicy SET imie = ? WHERE id_pracownika = ?";
                    aktualizacja();
                    pokazBazePracownikow();
                }else {
                    kom.komBrakDanychImie();
                }
            }
        });

        anazwisko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(an.getText().equals(""))) {
                    pole = an.getText();
                sql3="UPDATE pracownicy SET nazwisko = ? WHERE id_pracownika = ?";
                aktualizacja();
                pokazBazePracownikow();
            }else {
                kom.komBrakDanychNazwisko();
            }
            }
        });

        atelefon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(at.getText().equals(""))) {
                    if (at.getText().length() > 8 && at.getText().length() < 15) {
                        pole = at.getText();
                        sql3 = "UPDATE pracownicy SET telefon = ? WHERE id_pracownika = ?";
                        aktualizacja();
                        pokazBazePracownikow();
                    }else {
                        kom.komTelefonDlugosc();
                    }
                }else {
                kom.komBrakDanychTelefon();
                }
            }
        });

        ahaslo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ah.getText().equals(""))) {
                    if (ah.getText().length() > 0 && ah.getText().length() < 13) {
                        if (!(powtorz_haslo1.getText().equals(""))) {
                            if (ah.getText().length() > 0 && ah.getText().length() < 13) {
                                if (!(powtorz_haslo1.getText().equals(pole))) {
                                    kom.komPowtorzHaslo();
                                } else {
                                    pole = log.kodowanie(ah.getText());
                                    sql3 = "UPDATE pracownicy SET haslo = ? WHERE id_pracownika = ?";
                                    aktualizacja();
                                    pokazBazePracownikow();
                                }
                            }else {
                                kom.komHasloPowtorzD();
                            }
                        } else {
                            kom.komHasloPowtorz();
                        }
                    }else {
                        kom.komHasloD();
                    }
                }else {
                    kom.komHaslo();
                }
            }
        });

        astanowisko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(as.getValue() == null)){
                    sql3="UPDATE pracownicy SET id_stanowiska = ? WHERE id_pracownika = ?";
                    pole = log.stanowisko(as.getValue());
                    aktualizacja();
                    pokazBazePracownikow();
                }else {
                    kom.komBrakDanychStanowisko();
                }
            }
        });

        apesel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ape.getText().equals(""))) {
                    if (ape.getText().length() < 12 && ape.getText().length() > 8) {
                        pole = ape.getText();
                        sql3 = "UPDATE pracownicy SET pesel = ? WHERE id_pracownika = ?";
                        aktualizacja();
                        pokazBazePracownikow();
                    }else {
                        kom.komPeselD();
                    }
                }else {
                kom.komBrakDanychPesel();
                }
            }
        });

        aadres.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(aa.getText().equals(""))) {
                    pole = aa.getText();
                sql3="UPDATE pracownicy SET adres = ? WHERE id_pracownika = ?";
                aktualizacja();
                pokazBazePracownikow();
                }else {
                kom.komBrakDanychAdres();
                }
            }
        });

        adział.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ad.getValue() == null)){
                    sql3="UPDATE pracownicy SET id_dzialu = ? WHERE id_pracownika = ?";
                    pole = log.dzial(ad.getValue());
                    aktualizacja();
                    pokazBazePracownikow();
                }else {
                    kom.komBrakDanychDzial();
                }
            }
        });

        amail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ama.getText().equals(""))) {
                    pole = ama.getText();
                sql3="UPDATE pracownicy SET email = ? WHERE id_pracownika = ?";
                aktualizacja();
                pokazBazePracownikow();
                }else {
                kom.komBrakDanychEmail();
                }
            }
        });

        aidentyfikator.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(aide.getText().equals(""))) {
                    if (aide.getText().length() < 10 && aide.getText().length() > 8) {
                        pole = aide.getText();
                        sql3 = "UPDATE pracownicy SET identyfikator = ? WHERE id_pracownika = ?";
                        aktualizacja();
                        pokazBazePracownikow();
                    }else {
                        kom.komIdentyfikatorD();
                    }
                }else {
                kom.komBrakDanychIdentyfikator();
                }
            }
        });

        awszystko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ai.getText().equals("") || (an.getText().equals("")) || (at.getText().equals("")) || (ah.getText().equals("")) || (ape.getText().equals("")) || (aa.getText().equals("")) || (ama.getText().equals("")) || (aide.getText().equals("")))) {
                    if (ape.getText().length() < 12 && ape.getText().length() > 8) {
                        if (aide.getText().length() < 10 && aide.getText().length() > 8) {
                            if (at.getText().length() > 8 && at.getText().length() < 15) {
                                if (!(ah.getText().equals(""))) {
                                    if (ah.getText().length() > 0 && ah.getText().length() < 13) {
                                        if (!(powtorz_haslo1.getText().equals(""))) {
                                            if (powtorz_haslo1.getText().length() > 0 && powtorz_haslo1.getText().length() < 13) {
                                                if (!(powtorz_haslo1.getText().equals(ah.getText()))) {
                                                    kom.komPowtorzHaslo();
                                                } else {
                                                    pole = ah.getText();
                                                    aktualizacjawszystko();
                                                    pokazBazePracownikow();
                                                }
                                            }else {
                                                kom.komHasloPowtorzD();
                                            }
                                        } else {
                                            kom.komHasloPowtorz();
                                        }
                                    }else {
                                        kom.komHasloD();
                                    }
                                }else {
                                    kom.komHaslo();
                                }
                            }else {
                                kom.komTelefonDlugosc();
                            }
                        }else {
                            kom.komIdentyfikatorD();
                        }
                    }else {
                        kom.komPeselD();
                    }
                }else {
                    kom.komBrakDanychWszystkie();
                }
            }
        });

        amarka.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(am.getText().equals(""))) {
                    pole2 = am.getText();
                sql4="UPDATE pojazdy SET marka = ? WHERE id_pojazdu = ?";
                aktualizacja2();
                pokazBazePojazdow();
                }else {
                    kom.komBrakDanychMarka();
                }
            }
        });

        astan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(as2.getValue() == null)) {
                    pole2 = as2.getValue();
                sql4="UPDATE pojazdy SET uszkodzony = ? WHERE id_pojazdu = ?";
                aktualizacja2();
                pokazBazePojazdow();
                }else {
                    kom.komBrakDanychStan();
                }
            }
        });

        aoc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(pole2.equals(""))) {
                    pole2 = ao.getText();
                sql4="UPDATE pojazdy SET ubezpieczenie = ? WHERE id_pojazdu = ?";
                aktualizacja2();
                pokazBazePojazdow();
                }else {
                    kom.komBrakDanychUbezpieczenie();
                }
            }
        });

        aprzebieg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ap.getText().equals(""))) {
                    if (ap.getText().length() > 0 && ap.getText().length() < 10) {
                        pole2 = ap.getText();
                        sql4 = "UPDATE pojazdy SET przebieg = ? WHERE id_pojazdu = ?";
                        aktualizacja2();
                        pokazBazePojazdow();
                    }else {
                        kom.komPrzebiegD();
                    }
                }else {
                    kom.komBrakDanychPrzebieg();
                }
            }
        });

        amodel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(amo.getText().equals(""))) {
                    pole2 = amo.getText();
                sql4="UPDATE pojazdy SET model = ? WHERE id_pojazdu = ?";
                aktualizacja2();
                pokazBazePojazdow();
                }else {
                    kom.komBrakDanychModel();
                }
            }
        });

        arok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    if (!(ar.getValue() == null)) {
                        LocalDate dzisiaj = LocalDate.now();
                        if (!(ar.getValue().getYear() > dzisiaj.getYear())) {
                            pole2 = ar.getValue().toString();
                sql4="UPDATE pojazdy SET rok = ? WHERE id_pojazdu = ?";
                aktualizacja2();
                pokazBazePojazdow();
        }else {
            kom.komRokPrzyszly();
        }
    } else {
        kom.komBrakDanychRok();
    }
            }
        });

        apojemnosc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(apo.getText().equals(""))) {
                    pole2 = apo.getText();
                sql4="UPDATE pojazdy SET pojemnosc = ? WHERE id_pojazdu = ?";
                aktualizacja2();
                pokazBazePojazdow();
                }else {
                    kom.komBrakDanychPojemnosc();
                }
            }
        });

        arejestracja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(anr.getText().equals(""))) {
                    if (anr.getText().length() > 7 && anr.getText().length() < 9) {
                        pole2 = anr.getText();
                        sql4 = "UPDATE pojazdy SET nrrejestracyjny = ? WHERE id_pojazdu = ?";
                        aktualizacja2();
                        pokazBazePojazdow();
                    }else {
                        kom.komRejestracjaD();
                    }
                }else {
                    kom.komBrakDanychRejestracja();
                }
            }
        });

        arz.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dTF=DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate dzrn)
            {
                if(dzrn==null)
                    return "";
                return dTF.format(dzrn);
            }

            @Override
            public LocalDate fromString(String dS)
            {
                if(dS==null || dS.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dS,dTF);
            }
        });

        arokzakupu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(arz.getValue() == null)) {
                        LocalDate dzisiaj = LocalDate.now();
                            if (!(arz.getValue().compareTo(dzisiaj) > 0)) {
                                pole2 = arz.getValue().toString();
                sql4="UPDATE pojazdy SET rok_zakupu = ? WHERE id_pojazdu = ?";
                aktualizacja2();
                pokazBazePojazdow();
                            }else {
                                kom.komRokZakupuPrzyszly();
                            }
                }else {
                    kom.komRokZakupu();
                }
            }
        });

        awszystko2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(am.getText().equals("") || amo.getText().equals("") || apo.getText().equals("") || anr.getText().equals("") || ao.getText().equals("") || ap.getText().equals("") || arz.getValue() == null)) {
                    if (ap.getText().length() > 0 && ap.getText().length() < 10) {
                        if (anr.getText().length() > 7 && anr.getText().length() < 9) {
                            if (!(as2.getValue() == null)) {
                            if (!(arz.getValue() == null)) {
                                if (!(ar.getValue() == null)) {
                                    LocalDate dzisiaj = LocalDate.now();
                                    if (ar.getValue().getYear() > dzisiaj.getYear()) {
                                        if (arz.getValue().compareTo(dzisiaj) > 0) {
                                            aktualizacjawszystko2();
                                            pokazBazePojazdow();
                                        } else {
                                            kom.komRokZakupuPrzyszly();
                                        }
                                    } else {
                                        kom.komRokPrzyszly();
                                    }
                                } else {
                                    kom.komBrakDanychRok();
                                }
                            } else {
                                kom.komRokZakupu();
                            }
                            }else {
                                kom.komBrakDanychStan();
                            }
                        } else {
                            kom.komRejestracjaD();
                        }
                    } else {
                        kom.komPrzebiegD();
                    }
                }else {
                    kom.komBrakDanychWszystkie();
                }
            }
        });

        bdodaj1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(di.getText().equals("") || dn.getText().equals("") || dpe.getText().equals("") || dt.getText().equals("") || da.getText().equals("") || de.getText().equals("") || did.getText().equals("") || dh.getText().equals("") || dsta.getValue() == null || dd.getValue() == null)) {
                    if (dpe.getText().length() < 12 && dpe.getText().length() > 8) {
                        if (did.getText().length() < 10 && did.getText().length() > 8) {
                            if (dt.getText().length() > 8 && dt.getText().length() < 15) {
                                if (!(dh.getText().equals(""))) {
                                    if (dh.getText().length() > 0 && dh.getText().length() < 13) {
                                        if (!(powtorz_haslo.getText().equals(""))) {
                                            if (powtorz_haslo.getText().length() > 0 && powtorz_haslo.getText().length() < 13) {
                                                if (!(powtorz_haslo.getText().equals(dh.getText()))) {
                                                    kom.komPowtorzHaslo();
                                                } else {
                                                    String haslou = log.kodowanie(dh.getText());
                                                     try {
                                                         Class.forName(sterownik);
                                                         poloczenie = DriverManager.getConnection(String.valueOf(baza));
                                                         st = poloczenie.createStatement();
                                                         st.executeUpdate("INSERT INTO pracownicy (id_dzialu, id_stanowiska, imie, nazwisko, pesel, telefon, adres, email, identyfikator, haslo) VALUES ('" + log.dzial(dd.getValue()) + "','" + log.stanowisko(dsta.getValue()) + "','" + di.getText() + "','" + dn.getText() + "','" + dpe.getText() + "','" + dt.getText() + "','" + da.getText() + "','" + de.getText() + "','" + did.getText() + "','" + haslou + "')");
                                                     } catch (ClassNotFoundException e) {
                                                         e.printStackTrace();
                                                     } catch (SQLException e) {
                                                         e.printStackTrace();
                                                     }
                                                }
                                            }else {
                                                kom.komHasloPowtorzD();
                                            }
                                        } else {
                                            kom.komHasloPowtorz();
                                        }
                                    }else {
                                        kom.komHasloD();
                                    }
                                }else {
                                    kom.komHaslo();
                                }
                            }else {
                                kom.komTelefonDlugosc();
                            }
                        }else {
                            kom.komIdentyfikatorD();
                        }
                    }else {
                        kom.komPeselD();
                    }
                }else {
                    kom.komBrakDanychWszystkie();
                }
                pokazBazePracownikow();
            }
        });

        drokz.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dTF=DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate lD)
            {
                if(lD==null)
                    return "";
                return dTF.format(lD);
            }

            @Override
            public LocalDate fromString(String dS)
            {
                if(dS==null || dS.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dS,dTF);
            }
        });

        dr.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dTF=DateTimeFormatter.ofPattern("yyyy");

            @Override
            public String toString(LocalDate lD)
            {
                if(lD==null)
                    return "";
                return dTF.format(lD);
            }

            @Override
            public LocalDate fromString(String dS)
            {
                if(dS==null || dS.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dS,dTF);
            }
        });

        ar.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dTF=DateTimeFormatter.ofPattern("yyyy");

            @Override
            public String toString(LocalDate lD)
            {
                if(lD==null)
                    return "";
                return dTF.format(lD);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dTF);
            }
        });

        bdodaj2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dm.getText().equals("") || dmo.getText().equals("") || dpj.getText().equals("") || dprz.getText().equals("") || dnr.getText().equals("") || doc.getText().equals("") || dst.getValue() == null)) {
                    if (dprz.getText().length() > 0 && dprz.getText().length() < 10) {
                        if (dnr.getText().length() > 7 && dnr.getText().length() < 9) {
                            if (!(dst.getValue() == null)) {
                                if (!(drokz.getValue() == null)) {
                                    if (!(dr.getValue() == null)) {
                                        LocalDate dzisiaj = LocalDate.now();
                                        if (!(dr.getValue().getYear() > dzisiaj.getYear())) {
                                            if (!(drokz.getValue().compareTo(dzisiaj) > 0)) {
                                    try {
                                        Class.forName(sterownik);
                                        poloczenie = DriverManager.getConnection(String.valueOf(baza));
                                        st = poloczenie.createStatement();
                                        st.executeUpdate("INSERT INTO pojazdy (marka, model, rok, rok_zakupu, pojemnosc, przebieg, nrrejestracyjny, ubezpieczenie, uszkodzony) VALUES ('" + dm.getText() + "','" + dmo.getText() + "','" + dr.getValue().getYear() + "','" + drokz.getValue() + "','" + dpj.getText() + "','" + dprz.getText() + "','" + dnr.getText() + "','" + doc.getText() + "','" + dst.getValue() + "')");
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    pokazBazePojazdow();
                                            } else {
                                                kom.komRokZakupuPrzyszly();
                                            }
                                        } else {
                                            kom.komRokPrzyszly();
                                        }
                                    } else {
                                        kom.komBrakDanychRok();
                                    }
                                } else {
                                    kom.komRokZakupu();
                                }
                            }else {
                                kom.komBrakDanychStan();
                            }
                            } else {
                                kom.komRejestracjaD();
                            }
                    } else {
                        kom.komPrzebiegD();
                    }
                }else {
                    kom.komBrakDanychWszystkie();
                }
            }
        });

        bu1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (uid.getText().equals("")) {
                    kom.komPracownikDane();
                }else {
                    String id = "";
                    try {
                        Class.forName(sterownik);
                        poloczenie = DriverManager.getConnection(String.valueOf(baza));
                        PreparedStatement stz = poloczenie.prepareStatement("DELETE FROM pracownicy WHERE identyfikator = ?");
                        stz.setString(1, uid.getText());
                        stz.executeUpdate();
                        try {
                            Class.forName(sterownik);
                            poloczenie = DriverManager.getConnection(String.valueOf(baza));
                            Statement s = poloczenie.createStatement();
                            rs = s.executeQuery("SELECT id_pracownika FROM pracownicy WHERE identyfikator = " + uid.getText());
                            while (rs.next()) {
                                id = rs.getString(1);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        if (boxwp.isSelected()){
                            Class.forName(sterownik);
                            poloczenie = DriverManager.getConnection(String.valueOf(baza));
                            PreparedStatement s2 = poloczenie.prepareStatement("DELETE FROM wiadomosc_pracownicy WHERE id_pracownikan = ?");
                            s2.setString(1, id);
                            s2.executeUpdate();
                        }
                        if (boxpp.isSelected()){
                            Class.forName(sterownik);
                            poloczenie = DriverManager.getConnection(String.valueOf(baza));
                            PreparedStatement s3 = poloczenie.prepareStatement("DELETE FROM raporty_kierownictwo WHERE id_pracownika = ?");
                            s3.setString(1, id);
                            s3.executeUpdate();
                        }
                        pokazBazePracownikow();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        bu2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (uid2.getText().equals("")) {
                    kom.komPojazdNieWybrano();
                }else {
                    try {
                        Class.forName(sterownik);
                        poloczenie = DriverManager.getConnection(String.valueOf(baza));
                        PreparedStatement stz = poloczenie.prepareStatement("DELETE FROM pojazdy WHERE id_pojazdu = ?");
                        stz.setString(1, uid2.getText());
                        stz.executeUpdate();
                        if (boxpop.isSelected()){
                            Class.forName(sterownik);
                            poloczenie = DriverManager.getConnection(String.valueOf(baza));
                            PreparedStatement stz2 = poloczenie.prepareStatement("DELETE FROM pojazdy_raporty WHERE id_pojazdu = ?");
                            stz2.setString(1, uid2.getText());
                            stz2.executeUpdate();
                        }
                        pokazBazePojazdow();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        wykres1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dochody.setAnimated(false);
                dochody.getData().clear();
                XYChart.Series<String,Number> dane1 = new XYChart.Series<String,Number>();
                try {
                    Class.forName(sterownik);
                    poloczenie = DriverManager.getConnection(baza);
                    st = poloczenie.createStatement();
                    String recordQuery = ("Select miesiąc,kwota_brutto from zarobki_z_podzialem_na_miesiace");
                    rs = st.executeQuery(recordQuery);
                    while (rs.next()) {
                        dane1.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                    }
                    dane1.setName("Dochód");
                    dochody.getData().addAll(dane1);

                    for (final XYChart.Data<String,Number> d : dane1.getData()){
                        d.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                l1.setText(String.valueOf(d.getXValue()));
                                l2.setText(d.getYValue()+" zł");
                                Tooltip.install(d.getNode(),new Tooltip(String.valueOf(d.getXValue() + " " + d.getYValue()+" zł")));
                            }
                        });
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        wykres2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dochody2.setAnimated(false);
                dochody2.getData().clear();
                XYChart.Series<String,Number> dane2 = new XYChart.Series<String,Number>();
                try {
                    Class.forName(sterownik);
                    poloczenie = DriverManager.getConnection(baza);
                    st = poloczenie.createStatement();
                    String recordQuery = ("Select rok,kwota_brutto from zarobki_z_podzialem_na_lata");
                    rs = st.executeQuery(recordQuery);
                    while (rs.next()) {
                        dane2.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                    }
                    dane2.setName("Dochód");
                    dochody2.getData().addAll(dane2);

                    for (final XYChart.Data<String,Number> d : dane2.getData()){
                        d.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                l3.setText(String.valueOf(d.getXValue()));
                                l4.setText(d.getYValue()+" zł");
                                Tooltip.install(d.getNode(),new Tooltip(String.valueOf(d.getXValue() + " " + d.getYValue()+" zł")));
                            }
                        });
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void aktualizacja(){
        if (idpracownika == null) {
            kom.komPracownikDane();
        }else {
            try {
                Class.forName(sterownik);
                poloczenie = DriverManager.getConnection(String.valueOf(linkbaza));
                PreparedStatement stz = poloczenie.prepareStatement(sql3);
                stz.setString(1, pole);
                stz.setString(2, idpracownika);
                stz.executeUpdate();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void aktualizacja2(){
        if (idpojazdu == null) {
            kom.komPojazdNieWybrano();
        }else {
            try {
                Class.forName(sterownik);
                poloczenie = DriverManager.getConnection(String.valueOf(linkbaza));
                PreparedStatement stz = poloczenie.prepareStatement(sql4);
                stz.setString(1, pole2);
                stz.setString(2, idpojazdu);
                stz.executeUpdate();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void aktualizacjawszystko(){
        if (idpracownika == null) {
            kom.komPracownikDane();
        }else {
            if (stanowisko.equals("")) {
                kom.komBrakDanychStanowisko();
            } else if (dzial.equals("")) {
                kom.komBrakDanychDzial();
            } else {
                try {
                    Class.forName(sterownik);
                    poloczenie = DriverManager.getConnection(String.valueOf(baza));
                    PreparedStatement stz = poloczenie.prepareStatement("UPDATE pracownicy SET id_dzialu = ?, id_stanowiska = ?, imie = ?, nazwisko = ?, pesel = ?, telefon = ?, adres = ?, email = ?, identyfikator = ?, haslo = ? WHERE id_pracownika = ?");
                    stz.setString(1, log.dzial(ad.getValue()));
                    stz.setString(2, log.stanowisko(as.getValue()));
                    stz.setString(3, ai.getText());
                    stz.setString(4, an.getText());
                    stz.setString(5, ape.getText());
                    stz.setString(6, at.getText());
                    stz.setString(7, aa.getText());
                    stz.setString(8, ama.getText());
                    stz.setString(9, aide.getText());
                    stz.setString(10, ah.getText());
                    stz.setString(11, idpracownika);
                    stz.executeUpdate();
                    pokazBazePracownikow();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void aktualizacjawszystko2(){
        if (idpojazdu == null) {
            kom.komPojazdNieWybrano();
        }else {
            if (as2.getValue() == null) {
                kom.komBrakDanychStan();
            }else {
            try {
                Class.forName(sterownik);
                poloczenie = DriverManager.getConnection(String.valueOf(baza));
                PreparedStatement stz = poloczenie.prepareStatement("UPDATE pojazdy SET marka = ?, model = ?, rok = ?, rok_zakupu = ?, pojemnosc = ?, przebieg = ?, nrrejestracyjny = ?, ubezpieczenie = ?, uszkodzony= ? WHERE id_pojazdu = ?");
                stz.setString(1, am.getText());
                stz.setString(2, amo.getText());
                stz.setString(3, ar.getValue().toString());
                stz.setString(4, arz.getValue().toString());
                stz.setString(5, apo.getText());
                stz.setString(6, ap.getText());
                stz.setString(7, anr.getText());
                stz.setString(8, ao.getText());
                stz.setString(9, as2.getValue());
                stz.setString(10, idpojazdu);
                stz.executeUpdate();
                pokazBazePojazdow();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
        }
    }
    public void pokazBazePracownikow(){
        sql = "Select pracownicy.id_pracownika, dzial.nazwa_dzialu, stanowiska.nazwa_stanowiska, pracownicy.imie, pracownicy.nazwisko, pracownicy.pesel, pracownicy.telefon, pracownicy.adres, pracownicy.email, pracownicy.identyfikator, pracownicy.haslo, pracownicy.liczba_dni_urlopu_wykorzystane, stanowiska.liczba_dni_urlopu From pracownicy JOIN dzial ON pracownicy.id_dzialu = dzial.id_dzialu JOIN stanowiska ON pracownicy.id_stanowiska = stanowiska.id_stanowiska";
        kolumna2.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("id_pracownika"));
        kolumna1.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Imię"));
        kolumna3.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Nazwisko"));
        kolumna4.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("PESEL"));
        kolumna5.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("Nrtelefonu"));
        kolumna6.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Adres"));
        kolumna7.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("Działp"));
        kolumna8.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("Stanowisko"));
        kolumna9.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Adresemail"));
        kolumna10.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("Identyfikator"));
        kolumna12.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Hasło"));
        kolumna22.setCellValueFactory(new PropertyValueFactory<>("G"));

        Callback<TableColumn<Osoby, String>, TableCell<Osoby, String>> cela = new Callback<TableColumn<Osoby, String>, TableCell<Osoby, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Osoby, String> p) {
                        final TableCell<Osoby, String> cel = new TableCell<Osoby, String>() {

                            final JFXButton przycisk = new JFXButton("Usuń pracownika");

                            @Override
                            public void updateItem(String i, boolean pusto) {
                                super.updateItem(i, pusto);
                                if (pusto) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    przycisk.setOnAction(event -> {
                                        Osoby pracownik = getTableView().getItems().get(getIndex());
                                        try {
                                            Class.forName(sterownik);
                                            poloczenie = DriverManager.getConnection(String.valueOf(baza));
                                            PreparedStatement stz = poloczenie.prepareStatement("DELETE FROM pracownicy WHERE identyfikator = ?");
                                            stz.setString(1, pracownik.getIdentyfikator().toString());
                                            stz.executeUpdate();
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                    setGraphic(przycisk);
                                    setText(null);
                                }
                            }
                        };
                        return cel;
                    }
                };
        kolumna22.setCellFactory(cela);
        kolumna41.setCellValueFactory(new PropertyValueFactory<>("G2"));

        Callback<TableColumn<Osoby, String>, TableCell<Osoby, String>> cela2 = new Callback<TableColumn<Osoby, String>, TableCell<Osoby, String>>() {
            @Override
            public TableCell call(final TableColumn<Osoby, String> p) {
                final TableCell<Osoby, String> cel = new TableCell<Osoby, String>() {

                    final JFXButton przycisk = new JFXButton("Zeruj urlop");

                    @Override
                    public void updateItem(String i, boolean pusto) {
                        super.updateItem(i, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            przycisk.setOnAction(event -> {
                                Osoby pracownik = getTableView().getItems().get(getIndex());
                                try {
                                    Class.forName(sterownik);
                                    poloczenie = DriverManager.getConnection(String.valueOf(baza));
                                    PreparedStatement stz = poloczenie.prepareStatement("UPDATE pracownicy SET liczba_dni_urlopu_wykorzystane = ? WHERE id_pracownika = ?");
                                    stz.setString(1, "0");
                                    stz.setString(2, String.valueOf(pracownik.getId_pracownika()));
                                    stz.executeUpdate();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna41.setCellFactory(cela2);
        tabela.getItems().setAll(baz.baza4(sql));
    }
    public void pokazBazePojazdow(){
        sql2 = "Select * from pojazdy";
        kolumna13.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("id_pojazdu"));
        kolumna14.setCellValueFactory(new PropertyValueFactory<lista2, String>("Marka"));
        kolumna15.setCellValueFactory(new PropertyValueFactory<lista2, String>("Model"));
        kolumna16.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Rok"));
        kolumna17.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Pojemność"));
        kolumna18.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Przebieg"));
        kolumna19.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Nrrejestracyjny"));
        kolumna20.setCellValueFactory(new PropertyValueFactory<lista2, String>("Ubezpieczenie"));
        kolumna21.setCellValueFactory(new PropertyValueFactory<lista2, String>("Uszkodzony"));
        tabela2.getItems().setAll(baz.baza5(sql2));
    }
    public void pokazBazeWiadomosc(){
        sql5 = "Select wiadomosc_pracownicy.id_wiadomosc, pracownicy.imie, pracownicy.nazwisko, wiadomosc_pracownicy.tytul, wiadomosc_pracownicy.tresc, wiadomosc_pracownicy.data_godzina, wiadomosc_pracownicy.typ_przeczytana_nieprzeczytana, wiadomosc_pracownicy.plik_tak_nie from wiadomosc_pracownicy JOIN pracownicy ON pracownicy.id_pracownika = wiadomosc_pracownicy.id_pracownikan ORDER BY id_wiadomosc DESC";
        kolumna23.setCellValueFactory(new PropertyValueFactory<wiadomosc, String>("tytul"));
        kolumna24.setCellValueFactory(new PropertyValueFactory<>("G"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> cela = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cel = new TableCell<wiadomosc, String>() {

                    final Label labt = new Label();

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                            if (wiadomosc.getTyp_przeczytana_nieprzeczytana()){
                                labt.setText("Przeczytana");
                            }else {
                                labt.setText("Nieprzeczytana");
                            }
                            setGraphic(labt);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna24.setCellFactory(cela);
        kolumna25.setCellValueFactory(new PropertyValueFactory<>("G2"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> cela2 = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cel = new TableCell<wiadomosc, String>() {

                    final JFXCheckBox checkbox = new JFXCheckBox();

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
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
                            setGraphic(checkbox);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna25.setCellFactory(cela2);
        kolumna26.setCellValueFactory(new PropertyValueFactory<>("G3"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> cela3 = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cel = new TableCell<wiadomosc, String>() {

                    final JFXButton przycisk = new JFXButton("Pobierz załącznik");

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                            przycisk.setOnAction(event -> {
                                pobierzZalacznik(wiadomosc.getId_wiadomosc().toString());
                                pokazBazeWiadomosc();
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna26.setCellFactory(cela3);
        kolumna27.setCellValueFactory(new PropertyValueFactory<>("G4"));

        Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>> cela4 = new Callback<TableColumn<wiadomosc, String>, TableCell<wiadomosc, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc, String> p) {
                final TableCell<wiadomosc, String> cel = new TableCell<wiadomosc, String>() {

                    final JFXButton przycisk = new JFXButton("Usuń wiadomość");

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc wiadomosc = getTableView().getItems().get(getIndex());
                            przycisk.setOnAction(event -> {
                                log.wiadomoscUsuwanie(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_pracownicy");
                                pokazBazeWiadomosc();
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna27.setCellFactory(cela4);
        tabela3.getItems().setAll(baz.baza(sql5));
    }
    public void pobierzZalacznik(String x){
        byte[] bity;
        String nazwa;
        try {
            Class.forName(sterownik);
            poloczenie = DriverManager.getConnection(baza);
            st = poloczenie.createStatement();
            String zadanie = ("Select tytul, plik From wiadomosc_pracownicy WHERE id_wiadomosc = " + x);
            rs = st.executeQuery(zadanie);
            while (rs.next()) {
                nazwa = rs.getString(1);
                bity = rs.getBytes(2);
                OutputStream targetFile = new FileOutputStream("D://" + nazwa + ".pdf");
                targetFile.write(bity);
                targetFile.close();
                WebEngine silnik = strona.getEngine();
                String adres = getClass().getResource("/resources/web/viewer.html").toExternalForm();
                silnik.setJavaScriptEnabled(true);
                silnik.load(adres);

                String postac64 = Base64.getEncoder().encodeToString(bity);
                silnik.executeScript("openFileFromBase64('" + postac64 + "')");
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
    public void wiadomoscDzialy(String id){
        DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime dzisiaj = LocalDateTime.now();
        String data = dzisiaj.format(formatd);
        if (!(tytul_wiadomosc.getText().equals(""))) {
            if (!(wiadomość2.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || wiadomość2.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || wiadomość2.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                try {
                    Class.forName(sterownik);
                    poloczenie = DriverManager.getConnection(String.valueOf(baza));
                    st = poloczenie.createStatement();
                    st.executeUpdate("INSERT INTO wiadomosci_dzialy (id_dzialu, tytul, data_godzina, tresc, typ_przeczytana_nieprzeczytana) VALUES ('" + id + "','" + tytul_wiadomosc.getText() + "','" + data + "','" + wiadomość2.getHtmlText() + "',0)");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                kom.komWiadomoscBrakTresc();
            }
        } else {
            kom.komWiadomoscBrakTytulu();
        }
    }
    public void wiadomoscUsuwanie2(String id){
        try {
            Class.forName(sterownik);
            poloczenie = DriverManager.getConnection(String.valueOf(baza));
            PreparedStatement stz = poloczenie.prepareStatement("DELETE FROM wiadomosci_dzialy WHERE id_wiadomosc = ?");
            stz.setString(1, id);
            stz.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void pokazBazeDokumentowKierownictwa(){
        sql6 = "Select raporty_kierownictwo.id_raportu, pracownicy.identyfikator, raporty_kierownictwo.nazwa, raporty_kierownictwo.data_czas from raporty_kierownictwo JOIN pracownicy ON raporty_kierownictwo.id_pracownika=pracownicy.id_pracownika";
        kolumna28.setCellValueFactory(new PropertyValueFactory<dokumenty_kierownictwo, Integer>("id_pracownika"));
        kolumna29.setCellValueFactory(new PropertyValueFactory<dokumenty_kierownictwo, String>("nazwa"));
        kolumna30.setCellValueFactory(new PropertyValueFactory<dokumenty_kierownictwo, String>("data_czas"));
        kolumna31.setCellValueFactory(new PropertyValueFactory<>("G"));

        Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>> cela = new Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_kierownictwo, String> p) {
                final TableCell<dokumenty_kierownictwo, String> cel = new TableCell<dokumenty_kierownictwo, String>() {

                    final JFXButton przycisk = new JFXButton("Otwórz dokument");

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_kierownictwo dokument = getTableView().getItems().get(getIndex());
                            przycisk.setOnAction(event -> {
                                byte[] data = new byte[0];
                                try {
                                    Class.forName(sterownik);
                                    poloczenie = DriverManager.getConnection(String.valueOf(baza));
                                    Statement state = poloczenie.createStatement();
                                    ResultSet rs = state.executeQuery("SELECT plikpdf FROM raporty_kierownictwo WHERE id_raportu=" + dokument.getId_raportu());
                                    if (rs.next()) {
                                        data = rs.getBytes(1);
                                    }
                                    String base64 = Base64.getEncoder().encodeToString(data);
                                    silnik2.executeScript("openFileFromBase64('" + base64 + "')");
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna31.setCellFactory(cela);
        kolumna32.setCellValueFactory(new PropertyValueFactory<>("G2"));

        Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>> cela2 = new Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_kierownictwo, String> p) {
                final TableCell<dokumenty_kierownictwo, String> cel = new TableCell<dokumenty_kierownictwo, String>() {

                    final JFXButton przycisk = new JFXButton("Pobierz dokument");

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_kierownictwo dokument = getTableView().getItems().get(getIndex());
                            przycisk.setOnAction(event -> {
                                log.dokumentPobierz(String.valueOf(dokument.getId_raportu()),"raporty_kierownictwo");
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna32.setCellFactory(cela2);
        kolumna33.setCellValueFactory(new PropertyValueFactory<>("G3"));

        Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>> cela3 = new Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_kierownictwo, String> p) {
                final TableCell<dokumenty_kierownictwo, String> cel = new TableCell<dokumenty_kierownictwo, String>() {

                    final JFXCheckBox checkbox = new JFXCheckBox();

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                                    if (t1) {
                                        dokumenty_kierownictwo dokument = getTableView().getItems().get(getIndex());
                                        dok.add(String.valueOf(dokument.getId_raportu()));
                                    } else {
                                        dokumenty_kierownictwo dokument = getTableView().getItems().get(getIndex());
                                        dok.remove(String.valueOf(dokument.getId_raportu()));
                                    }
                                }
                            });
                            setGraphic(checkbox);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna33.setCellFactory(cela3);
        kolumna34.setCellValueFactory(new PropertyValueFactory<>("G4"));

        Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>> cela4 = new Callback<TableColumn<dokumenty_kierownictwo, String>, TableCell<dokumenty_kierownictwo, String>>() {
            @Override
            public TableCell call(final TableColumn<dokumenty_kierownictwo, String> p) {
                final TableCell<dokumenty_kierownictwo, String> cel = new TableCell<dokumenty_kierownictwo, String>() {

                    final JFXButton przycisk = new JFXButton("Usuń dokument");

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            dokumenty_kierownictwo dokument = getTableView().getItems().get(getIndex());
                            przycisk.setOnAction(event -> {
                                log.dokumentUsuwanie(String.valueOf(dokument.getId_raportu()),"raporty_kierownictwo");
                                pokazBazeDokumentowKierownictwa();
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna34.setCellFactory(cela4);
        tabela4.getItems().setAll(baz.baza6(sql6));
    }
    public void setText(String login, String haslo, String id){
        identyfikator = login;
        haslodane = haslo;
        idpracownikan = id;
    }
    public void pokazBazeWiadomoscDzialy(){
        sql7 = "Select wiadomosci_dzialy.id_wiadomosc, dzial.nazwa_dzialu, wiadomosci_dzialy.tytul, wiadomosci_dzialy.data_godzina, wiadomosci_dzialy.tresc, wiadomosci_dzialy.typ_przeczytana_nieprzeczytana from wiadomosci_dzialy JOIN dzial ON wiadomosci_dzialy.id_dzialu = dzial.id_dzialu ORDER BY id_wiadomosc DESC";
        kolumna35.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("tytul"));
        kolumna36.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("id_dzialu"));
        kolumna37.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("data_godzina"));
        kolumna38.setCellValueFactory(new PropertyValueFactory<>("G"));

        Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>> cela = new Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc_dzialy, String> p) {
                final TableCell<wiadomosc_dzialy, String> cel = new TableCell<wiadomosc_dzialy, String>() {

                    final JFXCheckBox checkbox = new JFXCheckBox();

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                                    if (t1) {
                                        wiadomosc_dzialy wiadomosc = getTableView().getItems().get(getIndex());
                                        wdu2.add(String.valueOf(wiadomosc.getId_wiadomosc()));
                                    } else {
                                        wiadomosc_dzialy wiadomosc = getTableView().getItems().get(getIndex());
                                        wdu2.remove(String.valueOf(wiadomosc.getId_wiadomosc()));
                                    }
                                }
                            });
                            setGraphic(checkbox);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna38.setCellFactory(cela);
        kolumna39.setCellValueFactory(new PropertyValueFactory<>("G2"));

        Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>> cela2 = new Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc_dzialy, String> p) {
                final TableCell<wiadomosc_dzialy, String> cel = new TableCell<wiadomosc_dzialy, String>() {

                    final JFXButton przycisk = new JFXButton("Pobierz wiadomość");

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc_dzialy wiadomosc = getTableView().getItems().get(getIndex());
                            przycisk.setOnAction(event -> {
                                log.pobierzWiadomosc(String.valueOf(wiadomosc.getTresc()));
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cel;
            }
        };
        kolumna39.setCellFactory(cela2);
        kolumna40.setCellValueFactory(new PropertyValueFactory<>("G3"));

        Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>> cela3 = new Callback<TableColumn<wiadomosc_dzialy, String>, TableCell<wiadomosc_dzialy, String>>() {
            @Override
            public TableCell call(final TableColumn<wiadomosc_dzialy, String> p) {
                final TableCell<wiadomosc_dzialy, String> cell = new TableCell<wiadomosc_dzialy, String>() {

                    final JFXButton przycisk = new JFXButton("Usuń wiadomość");

                    @Override
                    public void updateItem(String tekst, boolean pusto) {
                        super.updateItem(tekst, pusto);
                        if (pusto) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            wiadomosc_dzialy wiadomosc = getTableView().getItems().get(getIndex());
                            przycisk.setOnAction(event -> {
                                wiadomoscUsuwanie2(String.valueOf(wiadomosc.getId_wiadomosc()));
                                pokazBazeWiadomoscDzialy();
                            });
                            setGraphic(przycisk);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        kolumna40.setCellFactory(cela3);
        tabela5.getItems().setAll(baz.baza3(sql7));
    }
}
