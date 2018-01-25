import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by Paweł on 2016-05-25.
 */
public class l1 implements Initializable {

    private static Integer pruba = 0;
    private static String stanowisko = "";
    private static Connection poloczenie;
    private static String sterownik = "org.sqlite.JDBC";
    private static String id = "";
    private static logika log = new logika();
    private static komunikaty kom = new komunikaty();

    @FXML
    private Button wstecz;
    @FXML
    private Button logowanie;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField haslo;
    @FXML
    private Label h;
    @FXML
    private Label hl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        wstecz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        login.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });

        h.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                kom.komHasloZapomniane();
            }
        });

        hl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                kom.komHasloLoginZapomniane();
            }
        });

        logowanie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Class.forName(sterownik);
                    poloczenie = DriverManager.getConnection(String.valueOf("jdbc:mysql://sql126.main-hosting.eu/u224299645_praca?" + "user=u224299645_kajak&password=kajak66"));
                    Statement stz = poloczenie.createStatement();
                    ResultSet rsz = stz.executeQuery("Select id_pracownika,id_stanowiska from pracownicy WHERE identyfikator = "+login.getText()+" AND haslo = " +"\""+haslo.getText()+"\"");
                    while(rsz.next()) {
                        id = rsz.getString(1);
                        stanowisko = rsz.getString(2);
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if(!(stanowisko.equals("")) && !(id.equals(""))){
                    Parent opcje = null;
                    try {
                        if (stanowisko.equals("1")){
                            kierownik kierownik1 = new kierownik();
                            kierownik1.setText(login.getText(),haslo.getText(),id);
                            opcje = FXMLLoader.load(getClass().getResource("kierownik.fxml"));
                        }else if (stanowisko.equals("2")){
                            księgowość księgowość1 = new księgowość();
                            księgowość1.setText(login.getText(),haslo.getText(),id);
                            opcje = FXMLLoader.load(getClass().getResource("księgowość.fxml"));
                        }else if (stanowisko.equals("3")){
                            brygadzista brygadzista1 = new brygadzista();
                            brygadzista1.setText(login.getText(),haslo.getText(),id);
                            opcje = FXMLLoader.load(getClass().getResource("brygadzista.fxml"));
                        }else if (stanowisko.equals("4")){
                            magazynier magazynier1 = new magazynier();
                            magazynier1.setText(login.getText(),haslo.getText(),id);
                            opcje = FXMLLoader.load(getClass().getResource("magazynier.fxml"));
                        }else if (stanowisko.equals("5") || stanowisko.equals("6")){
                            kierowca kierowca1 = new kierowca();
                            kierowca1.setText(login.getText(),haslo.getText(),id);
                            opcje = FXMLLoader.load(getClass().getResource("kierowca.fxml"));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scenapocji = new Scene(opcje);
                    Stage estrada = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    estrada.hide();
                    estrada.setScene(scenapocji);
                    estrada.show();
                }else if(pruba > 2){
                    Parent opcje = null;
                    try {
                        opcje = FXMLLoader.load(getClass().getResource("zamknij.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scenapocji = new Scene(opcje);
                    Stage estrada = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    estrada.hide();
                    estrada.setScene(scenapocji);
                    estrada.show();
                }else {
                    pruba = pruba +1;
                    kom.komHasloLoginZle();
                }
            }
        });
    }
}