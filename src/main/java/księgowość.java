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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Created by Paweł on 2016-05-10.
 */
public class księgowość implements Initializable {

    private static String baza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private PDFParser parser;
    private PDFTextStripper pdfts;
    private PDDocument pdd;
    private COSDocument cosd;

    public String Text ;
    private File plikg;

    private static String droga;
    private static String txt;
    private static String t;
    private static String sql;
    private static String sql2;
    private static String htmltekst;
    private static String plik;
    private static String sql3;
    private static String sql4;
    private static String sql5;
    private static String droga_do_pliku;
    private static String droga_do_pliku2;
    private static String droga_do_plikuz = "";
    private static Boolean zalacznikplik = false;
    private static Connection polaczenie;
    private static Statement s;
    private static ResultSet rs;
    private static PdfWriter pdfw;
    private static String idpracownika = "";
    private static String identyfikatorr = "";
    private static String haslodane = "";
    private static String idwiadomosc;
    private static String idpracownikan = "";
    private static String identyfikator2 = "";
    private static String sterownik = "org.sqlite.JDBC";
    private static LocalDate dzisiaj = LocalDate.now();
    private static DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static LocalDateTime dzisiaj2 = LocalDateTime.now();
    private static DateTimeFormatter formatd2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private static List<String> dok=new ArrayList<String>();

    private static List<listawyplat> listadoc=new ArrayList<listawyplat>();
    private static List<listaDochodyFirmy> listadoc2=new ArrayList<listaDochodyFirmy>();
    private static List<String> wdu=new ArrayList<String>();

    private static ObservableList<String> lista1 = FXCollections.observableArrayList(
            "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"
    );
    private static ObservableList<String> lista2 = FXCollections.observableArrayList(
            "Właściciel", "Księgowy", "Magazynier", "Sprzedawca", "Kierowca"
    );
    private static ObservableList<Integer> lista3 = FXCollections.observableArrayList(
            20 , 15, 7, 10, 15
    );
    private static ObservableList<String> lista4 = FXCollections.observableArrayList(
            "I kwartał", "II kwartał", "III kwartał", "IV kwartał"
    );
    private static ObservableList<String> lista5 = FXCollections.observableArrayList(
            "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"
    );
    private static ObservableList<Integer> lista6 = FXCollections.observableArrayList(
          6, 7, 8 , 12, 24
    );
    private static ObservableList<Integer> lista7 = FXCollections.observableArrayList(
            28, 29, 30, 31
    );
    private static ObservableList<String> lista8 = FXCollections.observableArrayList(
            "Raport", "Pracownicy wypłaty", "Zyski firmy", "Straty firmy", "Inny"
    );
    private static ObservableList<String> lista9 = FXCollections.observableArrayList(
            "Kierownictwo", "Księgowość", "Magazyn", "Brygadzista", "Transport/Pracownicy"
    );
    private static ObservableList<String> listalat = FXCollections.observableArrayList();
    private static ObservableList<String> pracownicydzialu = FXCollections.observableArrayList();

    private static komunikaty kom = new komunikaty();
    private static logika log = new logika();
    private static baza baz = new baza();

    @FXML
    private JFXTextArea wiadomość;
    @FXML
    private JFXTextArea raport;
    @FXML
    private LineChart<String,Number> dochody;
    @FXML
    private BarChart<String,Number> dochody2;
    @FXML
    private WebView web;
    @FXML
    private JFXButton wykres1;
    @FXML
    private JFXButton wykres2;
    @FXML
    private JFXButton zatwierdzi;
    @FXML
    private JFXButton uraport;
    @FXML
    private JFXButton wraport;
    @FXML
    private JFXButton wraport3;
    @FXML
    private JFXButton craport;
    @FXML
    private JFXButton uraport2;
    @FXML
    private JFXButton podglad2;
    @FXML
    private JFXButton zatwierdzi2;
    @FXML
    private JFXButton uraport3;
    @FXML
    private JFXButton wraport2;
    @FXML
    private JFXButton historia2;
    @FXML
    private JFXButton dodaj_rok;
    @FXML
    private JFXButton uraport4;
    @FXML
    private JFXButton craport2;
    @FXML
    private JFXButton wiadomość3;
    @FXML
    private JFXButton wiadomość4;
    @FXML
    private JFXButton wstecz;
    @FXML
    private JFXButton zalacznik;
    @FXML
    private JFXButton usun_zaznaczone_dokumenty;
    @FXML
    private JFXCheckBox czy_html;
    @FXML
    private JFXCheckBox czy_html2;
    @FXML
    private JFXCheckBox czy_html3;
    @FXML
    private JFXButton generuj_pdf_wyplaty;
    @FXML
    private JFXButton generuj_pdf_dochody;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private JFXTextField imie;
    @FXML
    private JFXTextField nazwisko;
    @FXML
    private JFXTextField identyfikator;
    @FXML
    private JFXTextField dni;
    @FXML
    private JFXTextField ktowary;
    @FXML
    private JFXTextField knaprawy;
    @FXML
    private JFXTextField kpaliwo;
    @FXML
    private JFXTextField przychody;
    @FXML
    private JFXTextField podatki;
    @FXML
    private JFXTextField pensje;
    @FXML
    private JFXTextField tytul_wiadomosc;
    @FXML
    private JFXTextField droga_zalacznik;
    @FXML
    private JFXTextField tytul_dokumentu;
    @FXML
    private JFXTextField tytul_dokumentu2;
    @FXML
    private JFXTextField tytul_dokumentu3;
    @FXML
    private ComboBox<String> rokbox;
    @FXML
    private ComboBox<String> miesiąc1;
    @FXML
    private ComboBox<String> miesiąc2;
    @FXML
    private ComboBox<Integer> stawka;
    @FXML
    private ComboBox<Integer> zmiana;
    @FXML
    private ComboBox<Integer> mdni;
    @FXML
    private ComboBox<String> stanowisko;
    @FXML
    private ComboBox<String> kwartał;
    @FXML
    private ComboBox<String> rodzaj_dokumentu;
    @FXML
    private ComboBox<String> rodzaj_dokumentu2;
    @FXML
    private ComboBox<String> rodzaj_dokumentu3;
    @FXML
    private HTMLEditor eraport;
    @FXML
    private HTMLEditor eraport3;
    @FXML
    private HTMLEditor eraport5;
    @FXML
    private WebView eraport2;
    @FXML
    private WebView eraport4;
    @FXML
    private WebView dokument;
    @FXML
    private JFXTextArea raport2;
    @FXML
    private JFXTextArea raport3;
    @FXML
    private HTMLEditor wiadomość2;
    @FXML
    private MenuItem zamknij;
    @FXML
    private MenuItem opis;
    @FXML
    private MenuItem autorzy;
    @FXML
    private MenuItem wersja;
    @FXML
    private DatePicker data2;
    @FXML
    private TableView<Osoby> tabela;
    @FXML
    private TableView<Osoby> tabela2;
    @FXML
    private TableView<dokumenty_ksiegowosic> tabela3;
    @FXML
    private TableView<lista2> tabela4;
    @FXML
    private TableView<wiadomosc> tabela5;
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
    private DatePicker data1;
    @FXML
    private JFXTextField tw;
    @FXML
    private JFXTextField dtw;
    @FXML
    private JFXTextField autor_wiadomosc;
    @FXML
    private WebView strony;
    @FXML
    private JFXButton dokumentpdf1;
    @FXML
    private JFXButton dokumentpdf2;
    @FXML
    private JFXButton wyslij_raport_do_kierownictwa;
    @FXML
    private ComboBox dzialbox;
    @FXML
    private ComboBox pracownikbox;
    @FXML
    private JFXTextField imie_nazwisko;
    @FXML
    private TableView<wiadomosc_dzialy> tabela6;
    @FXML
    private TableColumn kolumna35;
    @FXML
    private TableColumn kolumna36;
    @FXML
    private TableColumn kolumna37;
    @FXML
    private TableColumn kolumna38;
    @FXML
    private JFXTextField tw2;
    @FXML
    private JFXTextField dtw2;
    @FXML
    private WebView strony2;

    public void initialize(URL url, ResourceBundle rb) {
        tabela.setTableMenuButtonVisible(true);
        tabela2.setTableMenuButtonVisible(true);
        tabela3.setTableMenuButtonVisible(true);
        tabela4.setTableMenuButtonVisible(true);
        tabela5.setTableMenuButtonVisible(true);
        tabela6.setTableMenuButtonVisible(true);

        pokazBazePracownikow();

        miesiąc1.setItems(lista1);

        miesiąc2.setItems(lista5);

        stawka.setItems(lista3);

        stanowisko.setItems(lista2);

        kwartał.setItems(lista4);

        zmiana.setItems(lista6);

        mdni.setItems(lista7);

        dzialbox.setItems(lista9);

        rodzaj_dokumentu.setItems(lista8);
        rodzaj_dokumentu2.setItems(lista8);
        rodzaj_dokumentu3.setItems(lista8);

        try {
            Class.forName(sterownik);
            polaczenie = DriverManager.getConnection(String.valueOf(baza));
            Statement s = polaczenie.createStatement();
            rs = s.executeQuery("SELECT rok FROM zarobki_z_podzialem_na_lata");
            while (rs.next()) {
                listalat.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        rokbox.setItems(listalat);

        pokazBazePojazdow();
        pokazBazeWiadomosc();
        pokazBazeWiadomoscDzialy();
        pokazBazeDokumentuwKsiegowych();

        String url2 = getClass().getResource("/web/viewer.html").toExternalForm();

        WebEngine silnik = dokument.getEngine();
        silnik.setJavaScriptEnabled(true);
        silnik.load(url2);

        WebEngine silnik2 = eraport2.getEngine();
        silnik2.setJavaScriptEnabled(true);
        silnik2.load(url2);

        WebEngine silnik3 = eraport4.getEngine();
        silnik3.setJavaScriptEnabled(true);
        silnik3.load(url2);

        YearMonth rokMiesiac = YearMonth.of(dzisiaj.getYear(), dzisiaj.getMonth());
        int dnitm = rokMiesiac.lengthOfMonth();
        mdni.setValue(dnitm);
        miesiąc1.setValue(log.miesiac(dzisiaj.getMonthValue()));
        miesiąc2.setValue(log.miesiac(dzisiaj.getMonthValue()));
        kwartał.setValue(log.kwartalObecny(dzisiaj.getMonthValue()));


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
                kom.komPomocKsięgowość();
            }
        });

        identyfikator.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        imie.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        nazwisko.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!keyEvent.getCharacter().matches("[A-Za-z]")) {
                    keyEvent.consume();
                }
            }
        });
        dni.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        ktowary.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789 ".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        knaprawy.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789 ".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        kpaliwo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789 ".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        przychody.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789 ".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        podatki.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789 ".contains(keyEvent.getCharacter())) {
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

        zatwierdzi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dzisiaj.format(formatd);
                if (!(stanowisko.getValue() == null || imie.getText().equals("") || nazwisko.getText().equals("") || identyfikator.getText().equals(""))) {
                    if (!(stawka.getValue() == null || mdni.getValue() == null || dni.getText().equals("") || zmiana.getValue() == null || miesiąc1.getValue() == null)) {
                        int wypłata = stawka.getValue() * zmiana.getValue() * mdni.getValue();
                        int dniliczba = Integer.parseInt(dni.getText());
                        int dniliczba2 = rokMiesiac.lengthOfMonth();
                        if (dniliczba <= dniliczba2 && dniliczba > 0) {
                            if (data1.getValue() == null) {
                                raport.appendText(imie.getText() + " " + nazwisko.getText() + " " + identyfikator.getText() + " " + stanowisko.getValue() + " przepracował " + dni.getText() + " dni w miesiącu " + miesiąc1.getValue() + " : Wpłata " + wypłata + " W dniu : " + dzisiaj + "\n");
                                listadoc.add(new listawyplat(imie.getText(), nazwisko.getText(), identyfikator.getText(), stanowisko.getValue(), dni.getText(), String.valueOf(wypłata)));
                            } else {
                                raport.appendText(imie.getText() + " " + nazwisko.getText() + " " + identyfikator.getText() + " " + stanowisko.getValue() + " przepracował " + dni.getText() + " dni w miesiącu " + miesiąc1.getValue() + " : Wpłata " + wypłata + " W dniu : " + data1.getValue() + "\n");
                                listadoc.add(new listawyplat(imie.getText(), nazwisko.getText(), identyfikator.getText(), stanowisko.getValue(), dni.getText(), String.valueOf(wypłata)));
                            }
                        }else {
                            kom.komPrzepracowaneDni();
                        }
                    } else {
                        kom.komWszystkieDane();
                    }
                }else {
                    kom.komPracownikDane();
                }
            }
        });

        uraport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tytul = tytul_dokumentu.getText();
                if (!(tytul.equals(""))) {
                    if (!(rodzaj_dokumentu.getValue() == null)) {
                        if (!(raport.getText().equals(""))) {
                            droga_do_pliku = "C:\\" + tytul_dokumentu.getText() + ".docx";
                            XWPFDocument document = new XWPFDocument();
                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(new File("C:\\" + tytul_dokumentu.getText() + ".docx"));
                                XWPFParagraph paragraph = document.createParagraph();
                                XWPFRun xwpfrun = paragraph.createRun();
                                xwpfrun.setText("Tytuł dokómętu: " + tytul_dokumentu.getText() + "\n Rodzaj dokumentu: " + rodzaj_dokumentu.getValue() + "\n Na dzin" + dzisiaj.format(formatd));
                                XWPFTable tabela = document.createTable();

                                XWPFTableRow rekord = tabela.getRow(0);
                                rekord.getCell(0).setText("Imie");
                                rekord.addNewTableCell().setText("Nazwisko");
                                rekord.addNewTableCell().setText("Identyfikator");
                                rekord.addNewTableCell().setText("Stanowisko");
                                rekord.addNewTableCell().setText("Liczba dni");
                                rekord.addNewTableCell().setText("Wypłata");

                                for (listawyplat lw : listadoc) {
                                    XWPFTableRow trd = tabela.createRow();
                                    trd.getCell(0).setText(lw.getImie());
                                    trd.getCell(1).setText(lw.getNazwisko());
                                    trd.getCell(2).setText(lw.getIdentyfikator());
                                    trd.getCell(3).setText(lw.getStanowisko());
                                    trd.getCell(4).setText(lw.getLiczbadni());
                                    trd.getCell(5).setText(lw.getWypłata());
                                }

                                document.write(fos);
                                fos.close();
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

        generuj_pdf_wyplaty.setOnAction(new EventHandler<ActionEvent>() {
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
                fc.setInitialFileName(tytul_dokumentu.getText());
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
                            pdf2.add(new Paragraph("Tytuł dokumentu: \n" + tytul_dokumentu.getText()+ "\n\n" + "Rodzaj dokumentu: \n" + rodzaj_dokumentu.getValue() + "\n\n" + "Treść: \n" + raport.getText()));
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

        generuj_pdf_dochody.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu2.getText().equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {
                        if (!(raport2.getText().equals(""))) {
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
                            fc.setInitialFileName(tytul_dokumentu2.getText());
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
                                        pdf2.add(new Paragraph("Tytuł dokumentu: \n" + tytul_dokumentu2.getText()+ "\n\n" + "Rodzaj dokumentu: \n" + rodzaj_dokumentu2.getValue() + "\n\n" + "Treść: \n" + raport2.getText()));
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

        wraport.setOnAction(new EventHandler<ActionEvent>() {
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
                            FileInputStream plikfis = new FileInputStream("C://" + tytul_dokumentu.getText() + ".docx");
                            dlugosc = (int) plik.length();
                            zapytanie = ("INSERT INTO dokumenty_ksiegowosic VALUES(null,?,?,?,?,?,?)");
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                            ps = polaczenie.prepareStatement(zapytanie);
                            ps.setString(1, idpracownikan);
                            ps.setString(2, tytul_dokumentu.getText());
                            ps.setString(3, dzisiaj2.format(formatd2));
                            ps.setString(4, rodzajDokumentu());
                            ps.setBinaryStream(5, plikfis, dlugosc);
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
                }
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

        data1.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dataFormat =DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate datal)
            {
                if(datal==null)
                    return "";
                return dataFormat.format(datal);
            }

            @Override
            public LocalDate fromString(String datas)
            {
                if(datas==null || datas.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(datas, dataFormat);
            }
        });

        data2.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dataFormat =DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate datal)
            {
                if(datal==null)
                    return "";
                return dataFormat.format(datal);
            }

            @Override
            public LocalDate fromString(String datas)
            {
                if(datas==null || datas.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(datas, dataFormat);
            }
        });

        uraport2.setOnAction(new EventHandler<ActionEvent>() {
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
                            fc.setInitialFileName(tytul_dokumentu.getText());
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
                                    String tekst = eraport.getHtmlText();

                                    XMLWorkerHelper xmlwh = XMLWorkerHelper.getInstance();
                                    InputStream is = new ByteArrayInputStream(tekst.getBytes(StandardCharsets.UTF_8));
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

        dodaj_rok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String zapytanie;
                PreparedStatement ps;
                boolean istnieje = false;

                try {
                    Class.forName(sterownik);
                    polaczenie = DriverManager.getConnection(String.valueOf(baza));
                    zapytanie = "SELECT rok FROM zarobki_z_podzialem_na_lata";
                    Statement s = polaczenie.createStatement();
                    rs = s.executeQuery(zapytanie);
                    while (rs.next()) {
                        if (rs.getString(1).equals(String.valueOf(dzisiaj.getYear()))){
                            istnieje = true;
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (istnieje == false) {
                    try {
                        Class.forName(sterownik);
                        polaczenie = DriverManager.getConnection(String.valueOf(baza));
                        zapytanie = ("INSERT INTO zarobki_z_podzialem_na_lata VALUES(null,?,null)");
                        ps = polaczenie.prepareStatement(zapytanie);
                        ps.setString(1, String.valueOf(dzisiaj.getYear()));
                        ps.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        zatwierdzi2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dzisiaj.format(formatd);
                if (!(ktowary.equals("") || knaprawy.equals("") || kpaliwo.equals("") || podatki.equals("") || przychody.equals("") || pensje.equals(""))) {
                    Double przychodyfirmy = Double.parseDouble(przychody.getText().replaceAll(" ","."));
                    Double podatkifirmy = Double.parseDouble(podatki.getText().replaceAll(" ","."));
                    Double koszttowarów = Double.parseDouble(ktowary.getText().replaceAll(" ","."));
                    Double kosztpaliwa = Double.parseDouble(kpaliwo.getText().replaceAll(" ","."));
                    Double kosztnaprawy = Double.parseDouble(knaprawy.getText().replaceAll(" ","."));
                    Double pensja = Double.parseDouble(pensje.getText().replaceAll(" ","."));
                    Double zarobek = przychodyfirmy - podatkifirmy - koszttowarów - kosztpaliwa - kosztnaprawy - pensja;

                    String zapytanie;
                    PreparedStatement ps;
                    String id_roku = "";
                    int suma = 0;

                    try {
                        Class.forName(sterownik);
                        polaczenie = DriverManager.getConnection(String.valueOf(baza));
                        zapytanie = "SELECT id_roku FROM zarobki_z_podzialem_na_lata WHERE rok=" + dzisiaj.getYear();
                        Statement s = polaczenie.createStatement();
                        rs = s.executeQuery(zapytanie);
                        while (rs.next()) {
                            id_roku = rs.getString(1);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (!(id_roku.equals(""))) {
                    if (dzisiaj.getMonthValue() == 12){
                        try {
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                            zapytanie = "SELECT kwota_brutto FROM zarobki_z_podzialem_na_miesiace WHERE id_roku=" + id_roku;
                            Statement s = polaczenie.createStatement();
                            rs = s.executeQuery(zapytanie);
                            while (rs.next()) {
                                suma = suma + rs.getInt(1);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        try {
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(url));
                            PreparedStatement st = polaczenie.prepareStatement("UPDATE zarobki_z_podzialem_na_lata SET kwota_brutto = ? WHERE rok = ?");
                            st.setString(1, String.valueOf(suma));
                            st.setString(2, String.valueOf(dzisiaj.getYear()));
                            st.executeUpdate();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                        boolean istnieje = false;

                        try {
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                            zapytanie = "SELECT zarobki_z_podzialem_na_miesiace.miesiąc, zarobki_z_podzialem_na_lata.rok FROM zarobki_z_podzialem_na_miesiace JOIN zarobki_z_podzialem_na_lata ON zarobki_z_podzialem_na_lata.id_roku = zarobki_z_podzialem_na_miesiace.id_roku";
                            Statement s = polaczenie.createStatement();
                            rs = s.executeQuery(zapytanie);
                            while (rs.next()) {
                                if (rs.getString(1).equals(log.miesiac(dzisiaj.getMonthValue())) && rs.getString(2).equals(String.valueOf(dzisiaj.getYear()))){
                                    istnieje = true;
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        if (istnieje == false) {
                            try {
                                Class.forName(sterownik);
                                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                zapytanie = ("INSERT INTO zarobki_z_podzialem_na_miesiace VALUES(null,?,?,?)");
                                ps = polaczenie.prepareStatement(zapytanie);
                                ps.setString(1, log.miesiac(dzisiaj.getMonthValue()));
                                ps.setString(2, String.valueOf(zarobek));
                                ps.setString(3, id_roku);
                                ps.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (data1.getValue() == null) {
                        raport2.appendText("Przychody frmy w miesiącu " + miesiąc2.getValue() + " wynoszą : " + przychodyfirmy + "zł \nWydatki firmy w miesiącu : " + miesiąc2.getValue() + "\nKoszt paliwa dla pojazdów : " + kosztpaliwa + "zł \nKoszt naprawy uszkodzonych pojazdów : " + kosztnaprawy
                                + "zł \nKoszt towarów : " + koszttowarów + "zł \nPodatki : " + podatkifirmy + "zł \nPensje dla pracowników : " + pensja + "zł \nDochud firmy w miesiącu " + miesiąc2.getValue() + " " + kwartał.getValue() + " wynoś : " + zarobek + "zł \n" + "Obliczenia wykonane w dniu : " + dzisiaj + "\n");
                        listadoc2.add(new listaDochodyFirmy(koszttowarów.toString(), kosztnaprawy.toString(), kosztpaliwa.toString(), podatkifirmy.toString(), przychodyfirmy.toString(), pensja.toString(), miesiąc2.getValue(), kwartał.getValue(), dzisiaj.toString()));
                    }else {
                        raport2.appendText("Przychody frmy w miesiącu " + miesiąc2.getValue() + " wynoszą : " + przychodyfirmy + "zł \nWydatki firmy w miesiącu : " + miesiąc2.getValue() + "zł \nKoszt paliwa dla pojazdów : " + kosztpaliwa + "zł \nKoszt naprawy uszkodzonych pojazdów : " + kosztnaprawy
                                + "zł \nKoszt towarów : " + koszttowarów + "zł \nPodatki : " +podatkifirmy + "zł \nPensje dla pracowników : " + pensja + "zł \nDochud firmy w miesiącu " + miesiąc2.getValue() + " " + kwartał.getValue() + " wynoś : " + zarobek + "zł \n" + "Obliczenia wykonane w dniu : " + data2.getValue() + "\n");
                        listadoc2.add(new listaDochodyFirmy(koszttowarów.toString(), kosztnaprawy.toString(), kosztpaliwa.toString(), podatkifirmy.toString(), przychodyfirmy.toString(), pensja.toString(), miesiąc2.getValue(), kwartał.getValue(), data2.getValue().toString()));
                    }
                    }else {
                    kom.komWszystkieDane();
                }
            }
        });

        uraport3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tytul = tytul_dokumentu2.getText();
                if (!(tytul.equals(""))) {
                    droga_do_pliku2 = "C:\\" + tytul_dokumentu2.getText() + ".docx";
                    XWPFDocument document = new XWPFDocument();
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(new File("C:\\" + tytul_dokumentu2.getText() + ".docx"));
                        XWPFParagraph paragraph = document.createParagraph();
                        XWPFRun xwpfrun = paragraph.createRun();
                        xwpfrun.setText("Tytuł dokómętu: " + tytul_dokumentu2.getText() + "\n Rodzaj dokumentu: " + rodzaj_dokumentu2.getValue() + "\n Na dzin" + dzisiaj.format(formatd));
                        XWPFTable tabela = document.createTable();

                        XWPFTableRow rekord = tabela.getRow(0);
                        rekord.getCell(0).setText("Koszt towarów");
                        rekord.addNewTableCell().setText("Koszt naprawy pojazdów");
                        rekord.addNewTableCell().setText("Koszt paliwa");
                        rekord.addNewTableCell().setText("Podatki/Opłaty");
                        rekord.addNewTableCell().setText("Przychody");
                        rekord.addNewTableCell().setText("Pensje (Łączna kwota)");
                        rekord.addNewTableCell().setText("Miesiąc");
                        rekord.addNewTableCell().setText("Kwartał");
                        rekord.addNewTableCell().setText("Data");

                        for (listaDochodyFirmy ld : listadoc2) {
                            XWPFTableRow trd = tabela.createRow();
                            trd.getCell(0).setText(ld.getKosztTowarow());
                            trd.getCell(1).setText(ld.getKosztNaprawyPojazdow());
                            trd.getCell(2).setText(ld.getKosztPaliwa());
                            trd.getCell(3).setText(ld.getPodatkiOplaty());
                            trd.getCell(4).setText(ld.getPrzychody());
                            trd.getCell(5).setText(ld.getPensjeLacznaKwota());
                            trd.getCell(6).setText(ld.getMiesiac());
                            trd.getCell(7).setText(ld.getKwartal());
                            trd.getCell(8).setText(ld.getData());
                        }
                        document.write(fos);
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        wraport2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(droga_do_pliku2.equals("") || tytul_dokumentu2.equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {

                        String html;
                        if (czy_html2.isSelected()) {
                            if (eraport3.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || eraport3.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || eraport3.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>")){
                                html = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>";
                                kom.komDokumentTekstPoFormatowniuPusty();
                            }else {
                                html = eraport3.getHtmlText();
                            }
                        } else {
                            html = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p>Podglad niedostępny</p></body></html>";
                        }

                        int dlugosc;
                        String zapytanie;
                        PreparedStatement ps;

                        try {
                            File plik = new File(droga_do_pliku2);
                            FileInputStream fis = new FileInputStream("C://" + tytul_dokumentu2.getText() + ".docx");
                            dlugosc = (int) plik.length();
                            zapytanie = ("INSERT INTO dokumenty_ksiegowosic VALUES(null,?,?,?,?,?,?)");
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                            ps = polaczenie.prepareStatement(zapytanie);
                            ps.setString(1, idpracownika);
                            ps.setString(2, tytul_dokumentu2.getText());
                            ps.setString(3, dzisiaj2.format(formatd2));
                            ps.setString(4, rodzajDokumentu2());
                            ps.setBinaryStream(5, fis, dlugosc);
                            ps.setString(6, html);
                            ps.executeUpdate();
                            html = "";
                            droga_do_pliku2 = "";
                        } catch (Exception e) {
                            e.printStackTrace();
                            kom.komBrakDokumentu();
                        }
                    }else {
                        kom.komBrakRodzajuDokumentu();
                    }
                }else {
                    kom.komBrakDokumentu();
                }
            }
        });

        wraport3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu3.equals(""))) {
                    if (!(rodzaj_dokumentu3.getValue() == null)) {

                        String html = "";

                            if (eraport5.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || eraport5.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || eraport5.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>")){
                                kom.komBrakTekstuHtml();
                            }else {
                                html = eraport5.getHtmlText();

                        String zapytanie;
                        PreparedStatement ps;

                        try {
                            zapytanie = ("INSERT INTO dokumenty_ksiegowosic VALUES(null,?,?,?,?,null,?)");
                            Class.forName(sterownik);
                            polaczenie = DriverManager.getConnection(String.valueOf(baza));
                            ps = polaczenie.prepareStatement(zapytanie);
                            ps.setString(1, idpracownika);
                            ps.setString(2, tytul_dokumentu3.getText());
                            ps.setString(3, dzisiaj2.format(formatd2));
                            ps.setString(4, rodzajDokumentu3());
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
                        log.wiadomoscWysylanie3(zalacznikplik,droga_do_plikuz,"1",idpracownikan,tytul_wiadomosc.getText(),wiadomość2.getHtmlText(),"wiadomosc_pracownicy", tytul_wiadomosc.getText());
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

        historia2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(wdu.isEmpty())) {
                    for (String w : wdu) {
                        log.wiadomoscUsuwanie(w,"wiadomosc_ksiegowosic");
                    }
                    pokazBazeWiadomosc();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        uraport4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu2.getText().equals(""))) {
                    if (!(rodzaj_dokumentu2.getValue() == null)) {
                        if (!(eraport3.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || eraport3.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || eraport3.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter format = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fc.getExtensionFilters().add(format);
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
                        String tekst = eraport3.getHtmlText();

                        XMLWorkerHelper xmlwh = XMLWorkerHelper.getInstance();
                        InputStream is = new ByteArrayInputStream(tekst.getBytes(StandardCharsets.UTF_8));
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

        usun_zaznaczone_dokumenty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dok.isEmpty())) {
                    for (String d : dok) {
                        log.dokumentUsuwanie(d,"dokumenty_ksiegowosic");
                    }
                    pokazBazeDokumentuwKsiegowych();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        tabela3.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                dokumenty_ksiegowosic dokument = tabela3.getSelectionModel().getSelectedItem();

                web.getEngine().loadContent(dokument.getPlikhtml());
            }
        });

        craport2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(raport2.getText().equals(""))) {
                    eraport3.setHtmlText(raport2.getText());
                }else {
                    kom.komDokumentBrakTresc();
                }
            }
        });

        tabela2.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                Osoby osoba = tabela2.getSelectionModel().getSelectedItem();
                idpracownika = osoba.getId_pracownika().toString();

                imie.setText(osoba.getImię());
                nazwisko.setText(osoba.getNazwisko());
                identyfikator.setText(osoba.getIdentyfikator().toString());
                stanowisko.setValue(osoba.getStanowisko());
            }
        });

        tabela6.getSelectionModel().selectedItemProperty().addListener((obs, staryWybor, nowyWybor) -> {
            if (nowyWybor != null) {
                wiadomosc_dzialy wiadomosc = tabela6.getSelectionModel().getSelectedItem();

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

        wykres1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dochody.setAnimated(false);
                dochody.getData().clear();
                XYChart.Series<String,Number> seria = new XYChart.Series<String,Number>();

                if (!(rokbox.getValue() == null)) {
                    try {
                        Class.forName(sterownik);
                        polaczenie = DriverManager.getConnection(baza);
                        s = polaczenie.createStatement();
                        String zapytanie = ("Select zarobki_z_podzialem_na_miesiace.miesiąc, zarobki_z_podzialem_na_miesiace.kwota_brutto, zarobki_z_podzialem_na_lata.rok from zarobki_z_podzialem_na_miesiace JOIN zarobki_z_podzialem_na_lata ON zarobki_z_podzialem_na_lata.id_roku = zarobki_z_podzialem_na_miesiace.id_roku where rok =" + rokbox.getValue());
                        rs = s.executeQuery(zapytanie);
                        while (rs.next()) {
                            seria.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
                        }
                        seria.setName("Dochód");
                        dochody.getData().addAll(seria);

                        for (final XYChart.Data<String, Number> dane : seria.getData()) {
                            dane.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    l1.setText(String.valueOf(dane.getXValue()));
                                    l2.setText(dane.getYValue() + " zł");
                                    Tooltip.install(dane.getNode(), new Tooltip(String.valueOf(dane.getXValue() + " " + dane.getYValue() + " zł")));
                                }
                            });
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else {
                    kom.komBrakRoku();
                }
            }
        });

        wykres2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dochody2.setAnimated(false);
                dochody2.getData().clear();
                XYChart.Series<String,Number> seria2 = new XYChart.Series<String,Number>();
                try {
                    Class.forName(sterownik);
                    polaczenie = DriverManager.getConnection(baza);
                    s = polaczenie.createStatement();
                    String zapytanie = ("Select rok,kwota_brutto from zarobki_z_podzialem_na_lata");
                    rs = s.executeQuery(zapytanie);
                    while (rs.next()) {
                        seria2.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                    }
                    seria2.setName("Dochód");
                    dochody2.getData().addAll(seria2);

                    for (final XYChart.Data<String,Number> dane : seria2.getData()){
                        dane.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                l3.setText(String.valueOf(dane.getXValue()));
                                l4.setText(dane.getYValue()+" zł");
                                Tooltip.install(dane.getNode(),new Tooltip(String.valueOf(dane.getXValue() + " " + dane.getYValue()+" zł")));
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
        tabela.getItems().setAll(baz.baza4(sql));
        kolumna13.setCellValueFactory(new PropertyValueFactory<Osoby, Integer>("id_pracownika"));
        kolumna14.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Imię"));
        kolumna15.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Nazwisko"));
        tabela2.getItems().setAll(baz.baza4(sql));
    }
    public void pokazBazeDokumentuwKsiegowych(){
        sql3 = "Select dokumenty_ksiegowosic.id_dokumentu, dokumenty_ksiegowosic.id_pracownika, dokumenty_ksiegowosic.tytul, dokumenty_ksiegowosic.data_godzina, dokumenty_rodzaje.rodzaj, dokumenty_ksiegowosic.plikhtml from dokumenty_ksiegowosic JOIN dokumenty_rodzaje ON dokumenty_rodzaje.id_rodzaju_dokumentu = dokumenty_ksiegowosic.id_rodzaju_dokumentu where id_pracownika =" +idpracownikan;
        kolumna11.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("tytul"));
        kolumna12.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("data_godzina"));
        kolumna16.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("rodzaj"));
        kolumna17.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
        kolumna17.setCellFactory(celaf);
        kolumna18.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
                                log.dokumentUsuwanie(dokument.getId_dokumentu().toString(),"dokumenty_ksiegowosic");
                                pokazBazeDokumentuwKsiegowych();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna18.setCellFactory(celaf2);
        kolumna19.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

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
        kolumna19.setCellFactory(celaf3);
        kolumna20.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

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
                                log.dokumentPobierz(dokument.getId_dokumentu().toString(),"dokumenty_ksiegowosic");
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna20.setCellFactory(celaf4);
        tabela3.getItems().setAll(baz.baza2(sql3));
    }
    public void pokazBazeWiadomosc(){
        sql4 = "Select wiadomosc_ksiegowosic.id_wiadomosc, pracownicy.imie, pracownicy.nazwisko, wiadomosc_ksiegowosic.tytul, wiadomosc_ksiegowosic.tresc, wiadomosc_ksiegowosic.data_godzina, wiadomosc_ksiegowosic.typ_przeczytana_nieprzeczytana, wiadomosc_ksiegowosic.plik_tak_nie from wiadomosc_ksiegowosic JOIN pracownicy ON pracownicy.id_pracownika = wiadomosc_ksiegowosic.id_pracownikan where wiadomosc_ksiegowosic.id_pracownika =" + idpracownikan + " ORDER BY id_wiadomosc DESC";
        kolumna30.setCellValueFactory(new PropertyValueFactory<wiadomosc, String>("tytul"));
        kolumna32.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
        kolumna32.setCellFactory(celaf);
        kolumna33.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna33.setCellFactory(celaf2);
        kolumna31.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

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
                                    log.pobierzZalacznik(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_ksiegowosic");
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
        kolumna31.setCellFactory(celaf3);
        kolumna34.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

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
                                log.wiadomoscUsuwanie(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_ksiegowosic");
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
        kolumna34.setCellFactory(celaf4);
        tabela5.getItems().setAll(baz.baza(sql4));
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
    public String rodzajDokumentu3(){
        String s;
        if(rodzaj_dokumentu3.getValue() == "Raport"){
            s = "1";
        }else if (rodzaj_dokumentu3.getValue() == "Pracownicy wypłaty"){
            s = "2";
        }else if (rodzaj_dokumentu3.getValue() == "Zyski firmy"){
            s = "3";
        }else if (rodzaj_dokumentu3.getValue() == "Straty firmy"){
            s = "4";
        }else if (rodzaj_dokumentu3.getValue() == "Inny"){
            s = "5";
        }else {
            s = "";
        }
        return s;
    }
    public void pokazBazePojazdow(){
        sql2 = "Select * from pojazdy";
        kolumna21.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("id_pojazdu"));
        kolumna22.setCellValueFactory(new PropertyValueFactory<lista2, String>("Marka"));
        kolumna23.setCellValueFactory(new PropertyValueFactory<lista2, String>("Model"));
        kolumna24.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Rok"));
        kolumna25.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Pojemność"));
        kolumna26.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Przebieg"));
        kolumna27.setCellValueFactory(new PropertyValueFactory<lista2, Integer>("Nrrejestracyjny"));
        kolumna28.setCellValueFactory(new PropertyValueFactory<lista2, String>("Ubezpieczenie"));
        kolumna29.setCellValueFactory(new PropertyValueFactory<lista2, String>("Uszkodzony"));
        tabela4.getItems().setAll(baz.baza5(sql2));
    }
    public void setText(String login, String haslo, String id){
        idpracownika = login;
        haslodane = haslo;
        idpracownikan = id;
    }
    public void pokazBazeWiadomoscDzialy(){
        sql5 = "Select wiadomosci_dzialy.id_wiadomosc, dzial.nazwa_dzialu, wiadomosci_dzialy.tytul, wiadomosci_dzialy.data_godzina, wiadomosci_dzialy.tresc, wiadomosci_dzialy.typ_przeczytana_nieprzeczytana from wiadomosci_dzialy JOIN dzial ON wiadomosci_dzialy.id_dzialu = dzial.id_dzialu WHERE nazwa_dzialu = 'Księgowość' ORDER BY id_wiadomosc DESC";
        kolumna35.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("tytul"));
        kolumna36.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
        kolumna36.setCellFactory(celaf);
        kolumna37.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("data_godzina"));
        kolumna38.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna38.setCellFactory(celaf2);
        tabela6.getItems().setAll(baz.baza3(sql5));
    }
}