import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Paweł on 2016-05-25.
 */
public class kierowca implements Initializable {

    private static String baza = "jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66";
    private static Connection polaczenie;
    private static Statement st;
    private static ResultSet rs;
    private static String sterownik = "org.sqlite.JDBC";
    private static String plik;
    private static String tekst;
    private static String sql;
    private static String sql2;
    private static String sql3;
    private static PdfWriter writer;
    private static String identyfikator = "";
    private static String haslodane = "";
    private static String idwiadomosc;
    private static String droga_do_plikuz;
    private static Boolean zalacznikplik = false;
    private static String idpracownika = "";
    private static String idpracownikan = "";
    private static String identyfikator2 = "";
    private static LocalDateTime dzisiaj = LocalDateTime.now();
    private static DateTimeFormatter formatd = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private static List<String> wdu=new ArrayList<String>();
    private static List<String> dok=new ArrayList<String>();
    private static ObservableList<String> pracownicydzialu = FXCollections.observableArrayList();
    private static ObservableList<String> lista4 = FXCollections.observableArrayList(
            "Kierownictwo", "Księgowość", "Magazyn", "Brygadzista", "Transport/Pracownicy"
    );

    private static komunikaty kom = new komunikaty();
    private static logika log = new logika();
    private static baza baz = new baza();

    @FXML
    private JFXButton dane2;
    @FXML
    private JFXTextField haslo;
    @FXML
    private JFXTextField login;
    @FXML
    private WebView raport;
    @FXML
    private WebView web;
    @FXML
    private WebView dokument_podglad;
    @FXML
    private JFXButton wraport;
    @FXML
    private JFXButton oraport;
    @FXML
    private JFXButton wstecz;
    @FXML
    private JFXButton wiadomość3;
    @FXML
    private JFXButton wiadomość4;
    @FXML
    private JFXButton podglad2;
    @FXML
    private JFXButton zalacznik;
    @FXML
    private JFXButton usun_zaznaczone_dokumenty;
    @FXML
    private HTMLEditor wiadomość2;
    @FXML
    private HTMLEditor eraport;
    @FXML
    private JFXTextField tytul_wiadomosc;
    @FXML
    private JFXTextField droga_zalacznik;
    @FXML
    private JFXTextField tytul_dokumentu;
    @FXML
    private MenuItem zamknij;
    @FXML
    private MenuItem opis;
    @FXML
    private MenuItem autorzy;
    @FXML
    private MenuItem wersja;
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
    private TableView<wiadomosc> tabela;
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
    private TableView<dokumenty_ksiegowosic> tabela2;
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
    private JFXTextField tw;
    @FXML
    private JFXTextField dtw;
    @FXML
    private JFXTextField autor_wiadomosc;
    @FXML
    private WebView strony;
    @FXML
    private JFXButton wiadomosc_usun;
    @FXML
    private JFXButton wyslij_raport_do_kierownictwa;
    @FXML
    private JFXButton wyslij_wiadomosc_do_brygadzisty;
    @FXML
    private JFXButton wyslij_raport_do_brygadzisty;
    @FXML
    private JFXComboBox dzialbox;
    @FXML
    private JFXComboBox pracownikbox;
    @FXML
    private JFXTextField imie_nazwisko;
    @FXML
    private TableView<wiadomosc_dzialy> tabela3;
    @FXML
    private TableColumn kolumna12;
    @FXML
    private TableColumn kolumna13;
    @FXML
    private TableColumn kolumna14;
    @FXML
    private TableColumn kolumna15;
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
        dzialbox.setItems(lista4);

        String url = getClass().getResource("/web/viewer.html").toExternalForm();

        WebEngine silnik = raport.getEngine();
        silnik.setJavaScriptEnabled(true);
        silnik.load(url);

        WebEngine silnik2 = dokument_podglad.getEngine();
        silnik2.setJavaScriptEnabled(true);
        silnik2.load(url);

        pokazBazeDokumentuwPracownika();
        pokazBazeWiadomosc();
        pokazBazeWiadomoscDzialy();

        login.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
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
                kom.komPomocKierowca();
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

        wiadomosc_usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(wdu.isEmpty())) {
                    for (String w : wdu) {
                        log.wiadomoscUsuwanie(w,"wiadomosc_kierowcyPracownicy");
                    }
                    pokazBazeWiadomosc();
                }else {
                    kom.komWiadomoscDoUsuniecia();
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
                silnik2.executeScript("openFileFromBase64('" + postac64 + "')");
            }
        });

        usun_zaznaczone_dokumenty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(dok.isEmpty())) {
                    for (String d : dok) {
                        log.dokumentUsuwanie(d,"dokumenty_kierowcyPracownicy");
                    }
                    pokazBazeDokumentuwPracownika();
                }else {
                    kom.komWiadomoscDoUsuniecia();
                }
            }
        });

        tabela2.getSelectionModel().selectedItemProperty().addListener((obs, staraWartosc, nowaWartosc) -> {
            if (nowaWartosc != null) {
                dokumenty_ksiegowosic dokument = tabela2.getSelectionModel().getSelectedItem();

                web.getEngine().loadContent(dokument.getPlikhtml());
            }
        });

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, staraWartosc, nowaWartosc) -> {
            if (nowaWartosc != null) {
                wiadomosc wiadomosc = tabela.getSelectionModel().getSelectedItem();
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

        wyslij_wiadomosc_do_brygadzisty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tytul = tytul_wiadomosc.getText();
                String tresc = wiadomość2.getHtmlText();
                if (!(tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || tresc.equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>"))) {
                    if (!(tytul.equals(""))) {
                        log.wiadomoscWysylanie2(zalacznikplik,droga_do_plikuz,"2",idpracownikan,tytul_wiadomosc.getText(),wiadomość2.getHtmlText(),"wiadomosc_brygadzista");
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

        wyslij_raport_do_brygadzisty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_wiadomosc.getText().equals(""))){
                    log.raportBrygadzistaWysylanie(log.drogaDoPliku(),idpracownikan,tytul_wiadomosc.getText(),"dokumenty_brygadzista");
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

        oraport.setOnAction(new EventHandler<ActionEvent>() {
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

        wraport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(tytul_dokumentu.getText().equals(""))) {

                    String html = "";

                    if (eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") || eraport.getHtmlText().equals("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><br></body></html>")){
                        kom.komBrakTekstuHtml();
                    }else {
                        html = eraport.getHtmlText();
                            PreparedStatement ps;

                            try {
                                Class.forName(sterownik);
                                polaczenie = DriverManager.getConnection(String.valueOf(baza));
                                ps = polaczenie.prepareStatement("INSERT INTO dokumenty_kierowcyPracownicy VALUES(null,?,?,?,?,null,?)");
                                ps.setString(1, idpracownika);
                                ps.setString(2, tytul_dokumentu.getText());
                                ps.setString(3, dzisiaj.format(formatd));
                                ps.setString(4, "1");
                                ps.setString(5, html);
                                ps.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                }else {
                    kom.komDokumentBrakTytulu();
                }
            }
        });

        tabela3.getSelectionModel().selectedItemProperty().addListener((obs, staraWartosc, nowaWartosc) -> {
            if (nowaWartosc != null) {
                wiadomosc_dzialy wiadomosc = tabela3.getSelectionModel().getSelectedItem();

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
    public void pokazBazeWiadomosc(){
        sql = "Select wiadomosc_kierowcyPracownicy.id_wiadomosc, pracownicy.imie, pracownicy.nazwisko, wiadomosc_kierowcyPracownicy.tytul, wiadomosc_kierowcyPracownicy.tresc, wiadomosc_kierowcyPracownicy.data_godzina, wiadomosc_kierowcyPracownicy.typ_przeczytana_nieprzeczytana, wiadomosc_kierowcyPracownicy.plik_tak_nie from wiadomosc_kierowcyPracownicy JOIN pracownicy ON pracownicy.id_pracownika = wiadomosc_kierowcyPracownicy.id_pracownikan ORDER BY id_wiadomosc DESC";
        kolumna1.setCellValueFactory(new PropertyValueFactory<wiadomosc, String>("tytul"));
        kolumna2.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
        kolumna2.setCellFactory(celaf);
        kolumna3.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna3.setCellFactory(celaf2);
        kolumna4.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

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
                                    log.pobierzZalacznik(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_kierowcyPracownicy");
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
        kolumna4.setCellFactory(celaf3);
        kolumna5.setCellValueFactory(new PropertyValueFactory<>("DUMMY4"));

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
                                log.wiadomoscUsuwanie(wiadomosc.getId_wiadomosc().toString(),"wiadomosc_kierowcyPracownicy");
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
        kolumna5.setCellFactory(celaf4);
        tabela.getItems().setAll(baz.baza(sql));
    }
    public void pokazBazeDokumentuwPracownika(){
        sql2 = "Select dokumenty_kierowcyPracownicy.id_dokumentu, dokumenty_kierowcyPracownicy.id_pracownika, dokumenty_kierowcyPracownicy.tytul, dokumenty_kierowcyPracownicy.data_godzina, dokumenty_rodzaje.rodzaj, dokumenty_kierowcyPracownicy.plikhtml from dokumenty_kierowcyPracownicy JOIN dokumenty_rodzaje ON dokumenty_rodzaje.id_rodzaju_dokumentu = dokumenty_kierowcyPracownicy.id_rodzaju_dokumentu ";
        kolumna6.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("tytul"));
        kolumna7.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("data_godzina"));
        kolumna8.setCellValueFactory(new PropertyValueFactory<dokumenty_ksiegowosic, String>("rodzaj"));
        kolumna9.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna9.setCellFactory(celaf);
        kolumna10.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

        Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>> celaf2 = new Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>>() {
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
        kolumna10.setCellFactory(celaf2);
        kolumna11.setCellValueFactory(new PropertyValueFactory<>("DUMMY3"));

        Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>> celaf3 = new Callback<TableColumn<dokumenty_ksiegowosic, String>, TableCell<dokumenty_ksiegowosic, String>>() {
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
                                log.dokumentUsuwanie(dokument.getId_dokumentu().toString(),"dokumenty_kierowcyPracownicy");
                                pokazBazeDokumentuwPracownika();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cela;
            }
        };
        kolumna11.setCellFactory(celaf3);
        tabela2.getItems().setAll(baz.baza2(sql2));
    }
    public void setText(String login, String haslo, String id){
        identyfikator = login;
        haslodane = haslo;
        idpracownikan = id;
    }
    public void pokazBazeWiadomoscDzialy(){
        sql3 = "Select wiadomosci_dzialy.id_wiadomosc, dzial.nazwa_dzialu, wiadomosci_dzialy.tytul, wiadomosci_dzialy.data_godzina, wiadomosci_dzialy.tresc, wiadomosci_dzialy.typ_przeczytana_nieprzeczytana from wiadomosci_dzialy JOIN dzial ON wiadomosci_dzialy.id_dzialu = dzial.id_dzialu WHERE nazwa_dzialu = 'Transport/Pracownicy' ORDER BY id_wiadomosc DESC";
        kolumna12.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("tytul"));
        kolumna13.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
        kolumna13.setCellFactory(celaf);
        kolumna14.setCellValueFactory(new PropertyValueFactory<wiadomosc_dzialy, String>("data_godzina"));
        kolumna15.setCellValueFactory(new PropertyValueFactory<>("DUMMY2"));

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
        kolumna15.setCellFactory(celaf2);
        tabela3.getItems().setAll(baz.baza3(sql3));
    }
}