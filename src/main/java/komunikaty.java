
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Paweł on 2017-09-30.
 */
public class komunikaty {
    public Alert komDokumentBrakRodzajuDokumetu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dokument brak danych");
        alert.setContentText("Nie wybrano rodzaju dokumentu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komDokumentBrakTresc(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dokument brak danych");
        alert.setContentText("Chcesz utworzyć pusty dokument word. Dokument nie może być pusty. Podaj treść dokumentu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komDokumentBrakTytulu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dokument brak danych");
        alert.setContentText("Nie podano tytułu dokumentu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komDokumentTekstPoFormatowniuPusty(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dokument po formatowaniu");
        alert.setContentText("Teks po formatowaniu jest pusty więc nie zostanie dołączony do dokumentu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komPrzepracowaneDni(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dane pracownika");
        alert.setContentText("Liczba przepracowanych dni nie może być większa niż liczba dni w miesiącu i nie może być równa lub mniejsza miż 0!");
        alert.showAndWait();
        return alert;
    }
    public Alert komBrakTekstuHtml(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dokument");
        alert.setContentText("Brak sformatowanego tekstu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komWiadomoscTytul(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Wiadomość");
        alert.setContentText("Nie podano tytułu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komBrakRoku(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Rok dochodów");
        alert.setContentText("Nie wybrano roku dochodów!");
        alert.showAndWait();
        return alert;
    }
    public Alert komWiadomoscBrakDzialu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Wiadomość");
        alert.setContentText("Nie wybrano działu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komWiadomoscBrakPracownika(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Wiadomość");
        alert.setContentText("Nie wybrano pracownika!");
        alert.showAndWait();
        return alert;
    }
    public Alert komWszystkieDane(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie uzupełniono wszystkich danych!");
        alert.showAndWait();
        return alert;
    }
    public Alert komPracownikDane(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Pracownik brak danych");
        alert.setContentText("Nie wybrano pracownika lub nie podano wszystkich danych pracownika!");
        alert.showAndWait();
        return alert;
    }
    public Alert komWiadomoscBrakTytulu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Wiadomość brak danych");
        alert.setContentText("Nie podano tytułu Wiadomość!");
        alert.showAndWait();
        return alert;
    }
    public Alert komWiadomoscBrakTresc(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Wiadomość brak danych");
        alert.setContentText("Wiadomość jest pusta!");
        alert.showAndWait();
        return alert;
    }
    public Alert komBrakDokumentu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak dokumentu");
        alert.setContentText("Nie utworzono nowego dokumentu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komBrakRodzajuDokumentu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dokument");
        alert.setContentText("Nie wybrano rodzaju dokumentu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komNumerDlugosc(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Numer indywidualny klienta");
        alert.setContentText("Numer klienta jest zbyt długi lub zbyt krótki!");
        alert.showAndWait();
        return alert;
    }
    public Alert komTelefonDlugosc(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Numer telefon");
        alert.setContentText("Numer telefon jest zbyt długi lub zbyt krótki!");
        alert.showAndWait();
        return alert;
    }
    public Alert komKodPocztowyDlugosc(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Kod pocztowy");
        alert.setContentText("Kod pocztowy jest zbyt długi lub zbyt krótki!");
        alert.showAndWait();
        return alert;
    }
    public Alert komNumerIstnieje(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Numer indywidualny klienta");
        alert.setContentText("Taki numer istnieje już w bazie danych!");
        alert.showAndWait();
        return alert;
    }
    public Alert komKlientBrak(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Klienta");
        alert.setContentText("Nie wybrano klienta!");
        alert.showAndWait();
        return alert;
    }
    public Alert komKategoriaBrak(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Produkt");
        alert.setContentText("Nie wybrano kategori produktu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komProduktBrak(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Produkt");
        alert.setContentText("Nie wybrano produktu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komZamowieniePuste(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Zamówienie");
        alert.setContentText("Nie zatwierdzono zamówienia lub zamówienie jest puste!");
        alert.showAndWait();
        return alert;
    }
    public Alert komZleDane(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Podałeś złe dane");
        alert.setContentText("Podałeś zły lub login lub hasło albo nie swoje dane!");
        alert.showAndWait();
        return alert;
    }
    public Alert komTypProduktu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie podano typu produktu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komOcenaProduktu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie podano oceny produktu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komLiczbaSztuk(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błędne dane");
        alert.setContentText("Zbyt duża lub zbyt mała liczba sztuk!");
        alert.showAndWait();
        return alert;
    }
    public Alert komCena(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błędne dane");
        alert.setContentText("Podana cena jest błędna!");
        alert.showAndWait();
        return alert;
    }
    public Alert komGrosze(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błędne dane");
        alert.setContentText("Zbyt dużo lub zbyt mało groszy!");
        alert.showAndWait();
        return alert;
    }
    public Alert komPodalesLitery(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błędne dane");
        alert.setContentText("Podałeś litery lub znaki!");
        alert.showAndWait();
        return alert;
    }
    public Alert komPrzydzialy(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Przydziały");
        alert.setContentText("Przydziały są puste!");
        alert.showAndWait();
        return alert;
    }
    public Alert komUrlopDzien(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Urlop");
        alert.setContentText("Ten dzień urlopu został już dodany dla tego pracownika!");
        alert.showAndWait();
        return alert;
    }
    public Alert komUrlopDzienBrak(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Urlop");
        alert.setContentText("Nie podano dnia urlopu!");
        alert.showAndWait();
        return alert;
    }
    public Alert komUrlop(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Urlop");
        alert.setContentText("Ten termin urlopu został już dodany dla tego pracownika!");
        alert.showAndWait();
        return alert;
    }
    public Alert komUrlopBrak(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Urlop");
        alert.setContentText("Nie podano dnia dat urlopu od do!");
        alert.showAndWait();
        return alert;
    }
    public Alert komUrlopDaty(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Urlop");
        alert.setContentText("Data początkowa nie może być wieksza od daty końcowej!");
        alert.showAndWait();
        return alert;
    }
    public Alert komLiczbaDniPusta(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Dane");
        alert.setContentText("Nie podano liczby przepracowanych dni!");
        alert.showAndWait();
        return alert;
    }
    public Alert komPrzydzialIstnieje(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Przydział");
        alert.setContentText("Istnieje już przydział z tymi danymi!");
        alert.showAndWait();
        return alert;
    }
    public Alert komBrakPrzydzialow(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie utworzono żadnych przydziałów!");
        alert.showAndWait();
        return alert;
    }
    public Alert komLiczbaDni(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Przepracowane dni");
        alert.setContentText("Podałeś za dużą lub za małom liczbe przepracowanych dni!");
        alert.showAndWait();
        return alert;
    }
    public Alert komPowtorzHaslo(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Powtórz hasło.");
        alert.showAndWait();
        return alert;
    }
    public Alert komHaslo(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Hasło");
        alert.setContentText("Pole hasło jest puste.");
        alert.showAndWait();
        return alert;
    }
    public Alert komHasloPowtorzD(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Hasło");
        alert.setContentText("Pole powtórz hasło nie może mieć więcej niż 13 znaków i nie mniej niż 1 znak.");
        alert.showAndWait();
        return alert;
    }
    public Alert komHasloPowtorz(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Hasło");
        alert.setContentText("Pole powtórz hasło jest puste.");
        alert.showAndWait();
        return alert;
    }
    public Alert komHasloD(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Hasło");
        alert.setContentText("Pole hasło nie może mieć więcej niż 13 znaków i nie mniej niż 1 znak.");
        alert.showAndWait();
        return alert;
    }
    public Alert komPeselD(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Pesel");
        alert.setContentText("Pole pesel musi posiadać 11 cyfr.");
        alert.showAndWait();
        return alert;
    }
    public Alert komIdentyfikatorD(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Identyfikator");
        alert.setContentText("Pole identyfikator musi posiadać 9 cyfr.");
        alert.showAndWait();
        return alert;
    }
    public Alert komPrzebiegD(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Przebieg");
        alert.setContentText("Pole przebieg nie może mieć więcej niż 9 cyfr i nie mniej niż 1 cyfr.");
        alert.showAndWait();
        return alert;
    }
    public Alert komRejestracjaD(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Nr rejestracyjny");
        alert.setContentText("Pole nr rejestracyjny musi posiadać 8 znaków.");
        alert.showAndWait();
        return alert;
    }

    public Alert komWiadomoscDoUsuniecia(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Wiadomość");
        alert.setContentText("Nie wybrano wiadomość do usunięcia.");
        alert.showAndWait();
        return alert;
    }

    public Alert komPojazdNieWybrano(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie wybrano pojazdu.");
        alert.showAndWait();
        return alert;
    }

    public Alert komZdjecieAktualizacja(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aktualizacja danych");
        alert.setHeaderText("Zdjęcie pojazdu.");
        alert.setContentText("Zaktualizowano zdjęcie.");
        alert.showAndWait();
        return alert;
    }

    public Alert komZdjecieAktualizacjaBlad(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aktualizacja danych");
        alert.setHeaderText("Zdjęcie pojazdu.");
        alert.setContentText("Nie udało się zaktualizować zdjęcia pojazdu.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychImie(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole imie jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychNazwisko(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole nazwisko jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychTelefon(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole telefon jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychStanowisko(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie wybrano stanowisko.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychPesel(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole pesel jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychAdres(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole adres jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychDzial(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie wybrano działu.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychEmail(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole email jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychIdentyfikator(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole identyfikator jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychWszystkie(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie wszystkie pola zostały uzupełnione.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychMarka(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole marka jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychStan(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole uszkodzony jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychUbezpieczenie(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole ubezpieczenie jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychPrzebieg(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole przebieg jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychModel(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole model jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komRokPrzyszly(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błąd danych");
        alert.setContentText("Rocznik pojazdu nie może być przekraczać roku obecnego!");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychRok(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie podałeś rocznika pojazdu!");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychPojemnosc(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole pojemnosc jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komBrakDanychRejestracja(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Pole nrrejestracyjny jest puste.");
        alert.showAndWait();
        return alert;
    }

    public Alert komRokZakupuPrzyszly(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błąd danych");
        alert.setContentText("Data zakupu nie może przekroczyć daty obecnej!");
        alert.showAndWait();
        return alert;
    }

    public Alert komRokZakupu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Brak danych");
        alert.setContentText("Nie podałeś daty zakupu pojazdu!");
        alert.showAndWait();
        return alert;
    }

    public Alert komHasloZapomniane(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logowanie");
        alert.setHeaderText("Zapomniałeś hasła?");
        alert.setContentText("Jesili zapomniałeś chasła niestety musisz skontaktować się z przełorzonym czyli kierownikiem lub administratorem systemu");
        alert.showAndWait();
        return alert;
    }

    public Alert komHasloLoginZapomniane(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logowanie");
        alert.setHeaderText("Zapomniałeś hasła i loginu?");
        alert.setContentText("Jesili zapomniałeś loginu i chasła niestety musisz skontaktować się z przełorzonym czyli kierownikiem lub administratorem systemu");
        alert.showAndWait();
        return alert;
    }

    public Alert komHasloLoginZle(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logowanie");
        alert.setHeaderText("Błąd danych");
        alert.setContentText("Złe hasło lub login!");
        alert.showAndWait();
        return alert;
    }

    public Alert komAutor(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Autor programu");
        alert.setHeaderText("Autor:");
        alert.setContentText("Paweł Górecki 8899");
        alert.showAndWait();
        return alert;
    }

    public Alert komWersjaProgramu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wersja programu");
        alert.setHeaderText("Wersja:");
        alert.setContentText("1.0.7.324354354");
        alert.showAndWait();
        return alert;
    }

    public Alert komPomocKierownik(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Witaj w dziale pomocy");
        alert.setContentText("Opis zakładek rozwini aby zobaczyć");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String tekst_pomoc = sw.toString();

        Label l = new Label("Opis poszczegulnych zakładek:");

        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setWrapText(true);
        String Opis1="Zakładka - Dokumenty, poczta i wykresy:\n"
                + "Zakładka ta zawiera wszystkie zakładki związane z obróbką dokómentów, pocztą pracowników oraz prezętacją danych.\n"
                + "\n"
                + "Zakładka - Podgląd dokumentów:\n"
                + "Zakładka umożliwia podgląd dokómentów przy jednoczesnym sporządzaniu dokómentów/raportów.\n"
                + "\n"
                + "Zakładka - Wyślij wiadomość:\n"
                + "Urzytkownik kożystakąc z tej zakładki może wysłać wiadomość do poszczególnych pracowników, całego działu w firmie lub do wszystkich pracowników w firmie.\n"
                + "\n"
                + "Zakładka - Wiadomości działy:\n"
                + "Urzytkownik może w niej przeglądać wszystkie wiadomość skierowane do wszystkich działów w firmie.\n"
                + "\n"
                + "Zakładka - Poczta:\n"
                + "Znajduje się tu poczta firmowa od wszystkich pracowników firmy którą urzytkownik może zarządzać.\n"
                + "\n"
                + "Zakładka - Pliki HTML:\n"
                + "Zakładka ta pozwala na podgląd dokómentów w postać HTML.\n"
                + "\n"
                + "Zakładka - Dochody firmy:\n"
                + "Urzytkownik może w tej zakładce przyjżeć się danym związanym z dochodami firmy w poszczególnych miesiącach które są przedstawione w postać wykresu.\n"
                + "\n"
                + "Zakładka - Dochody firmy na przestrzeni lat:\n"
                + "Urzytkownik może w tej zakładce przyjżeć się danym związanym z dochodami firmy na przestrzeni lat które są przedstawione w postać wykresu.\n"
                + "\n"
                + "Zakładka - Operacje na bazach danych:\n"
                + "Zakładka ta zawiera wszystkie zakładki związane z dzanymi pracowników jak i pojazdów.\n"
                + "\n"
                + "Zakładka - Baza danych pracowników:\n"
                + "Zakładka daje urzytkownikowi możliwość podglądu jak i modyfikacji danych pracowników.\n"
                + "\n"
                + "Zakładka - Baza danych pojazdów:\n"
                + "Zakładka daje urzytkownikowi możliwość podglądu jak i modyfikacji danych pojazdów firmowych.\n"
                + "\n"
                + "Zakładka - Dodawanie urzytkownika/pojazdu:\n"
                + "Zakładka ta umożliwia dodanie danych niowego pracownika lub pojazdu do bazy danych.\n"
                + "\n"
                + "Zakładka - Usuwanie urzytkownika/pojazdu:\n"
                + "Zakładka pozwala na usóniecie pracownika lub pojazdu z bazy danych z możliwośćą usunięcia także  dokómętów związanych z nimi\n"
                + "\n";

        ta.appendText(Opis1);

        ta.setMaxWidth(Double.MAX_VALUE);
        ta.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(ta, Priority.ALWAYS);
        GridPane.setHgrow(ta, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(l, 0, 0);
        expContent.add(ta, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
        return alert;
    }

    public Alert komPomocKsięgowość(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Witaj w dziale pomocy");
        alert.setContentText("Opis zakładek rozwini aby zobaczyć");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String tekst_pomoc = sw.toString();

        Label l = new Label("Opis poszczegulnych zakładek:");

        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setWrapText(true);
        String Opis1="Zakładka - Podgląd danych pracowników \n"
                + "Zakładka zawiera dane wszystkich pracowników firmy.\n"
                + "\n"
                + "Zakładka - Obliczanie wysokości wypłat :\n"
                + "Zakładka ta pozwala na tworzenie raportów związanych z pensjami pracowników i nie tylko oraz na tworzenie innych dokómentów.\n"
                + "\n"
                + "Zakładka - Podgląd dokumentów:\n"
                + "Zakładka umożliwia podgląd dokómentów przy jednoczesnym sporządzaniu dokómentów/raportów.\n"
                + "\n"
                + "Zakładka - Obliczanie dochodów firmy:\n"
                + "Zakładka ta pozwala na tworzenie raportów związanych z dochodami firmy i nie tylko oraz na tworzenie innych dokómentów.\n"
                + "\n"
                + "Zakładka - Wysyłanie wiadomości:\n"
                + "Urzytkownik kożystakąc z tej zakładki może wysłać wiadomość do przełorzonych jak i innych pracowników.\n"
                + "\n"
                + "Zakładka - Wiadomość dział:\n"
                + "Pracownik może w niej przeglądać wiadomość skierowane do całego działu.\n"
                + "\n"
                + "Zakładka - Poczta:\n"
                + "Znajduje się tu poczta firmowa pracownika którą pracownik może zarządzać.\n"
                + "\n"
                + "Zakładka - Dochody firmy w danym miesiącu:\n"
                + "Urzytkownik może w tej zakładce przyjżeć się danym związanym z dochodami firmy w poszczególnych miesiącach które są przedstawione w postać wykresu.\n"
                + "\n"
                + "Zakładka - Dochody firmy na przestrzeni lat:\n"
                + "Urzytkownik może w tej zakładce przyjżeć się danym związanym z dochodami firmy na przestrzeni lat które są przedstawione w postać wykresu.\n"
                + "\n"
                + "Zakładka - Pliki zaawansowane:\n"
                + "Zakładka umożliwia podgląd dokómentów przy jednoczesnym sporządzaniu dokómentów/raportów.\n"
                + "\n";

        ta.appendText(Opis1);

        ta.setMaxWidth(Double.MAX_VALUE);
        ta.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(ta, Priority.ALWAYS);
        GridPane.setHgrow(ta, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(l, 0, 0);
        expContent.add(ta, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
        return alert;
    }

    public Alert komPomocKierowca(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Witaj w dziale pomocy");
        alert.setContentText("Opis zakładek rozwini aby zobaczyć");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String tekst_pomoc = sw.toString();

        Label l = new Label("Opis poszczegulnych zakładek:");

        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setWrapText(true);
        String Opis1="Zakładka - Tworzenie raportu\n"
                + "Zakładka ta pozwala na tworzenie raportów oraz na tworzenie innych dokómentów.\n"
                + "\n"
                + "Zakładka - Poczta:\n"
                + "Znajduje się tu poczta firmowa pracownika którą pracownik może zarządzać.\n"
                + "\n"
                + "Zakładka - Dane pracownika:\n"
                + "Umorzliwia pracownikowi podgląd swoich danych.\n"
                + "\n"
                + "Zakładka - Wyślij wiadomość:\n"
                + "Urzytkownik kożystakąc z tej zakładki może wysłać wiadomość do przełorzonych jak i innych pracowników.\n"
                + "\n"
                + "Zakładka - Wiadomość dział:\n"
                + "Pracownik może w niej przeglądać wiadomość skierowane do całego działu.\n"
                + "\n"
                + "Zakładka - Podgląd dokumentów:\n"
                + "Zakładka umożliwia podgląd dokómentów przy jednoczesnym sporządzaniu dokómentów/raportów.\n"
                + "\n";

        ta.appendText(Opis1);

        ta.setMaxWidth(Double.MAX_VALUE);
        ta.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(ta, Priority.ALWAYS);
        GridPane.setHgrow(ta, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(l, 0, 0);
        expContent.add(ta, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
        return alert;
    }

    public Alert komPomocMagazynier(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Witaj w dziale pomocy");
        alert.setContentText("Opis zakładek rozwini aby zobaczyć");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String tekst_pomoc = sw.toString();

        Label l = new Label("Opis poszczegulnych zakładek:");

        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setWrapText(true);
        String Opis1="Zakładka - Tworzenie raportu:\n"
                + "Zakładka ta pozwala na tworzenie raportów związanych z zamówieniami i nie tylko oraz na tworzenie innych dokómentów.\n"
                + "\n"
                + "Zakładka - Poczta:\n"
                + "Znajduje się tu poczta firmowa pracownika którą pracownik może zarządzać.\n"
                + "\n"
                + "Zakładka - Dane pracownika:\n"
                + "Umorzliwia pracownikowi podgląd swoich danych.\n"
                + "\n"
                + "Zakładka - Towary:\n"
                + "Zakładka pozwala przeglądać towary dostępne w magazynie oraz na zmiane ich dostępnej ilość.\n"
                + "\n"
                + "Zakładka - Dodaj produkt do magazynu:\n"
                + "Dzięki tej zakładce urzytkownik może dodać produkt do magazynu wraz z jego zdjęciem.\n"
                + "\n"
                + "Zakładka - Zamówienia:\n"
                + "Pracownik może tu przeglądać liste aktualnie wykonywanych zmówieni oraz zmienić ich stan realizacji.\n"
                + "\n"
                + "Zakładka - Klienci:\n"
                + "Można tu przeglądać dane klientów firmy.\n"
                + "\n"
                + "Zakładka - Dokumenty dział:\n"
                + "Zakładka daje możliwość przeglądó firmowych dokómentów.\n"
                + "\n"
                + "Zakładka - Wyślij wiadomość:\n"
                + "Urzytkownik kożystakąc z tej zakładki może wysłać wiadomość do przełorzonych jak i innych pracowników.\n"
                + "\n"
                + "Zakładka - Wiadomość dział:\n"
                + "Pracownik może w niej przeglądać wiadomość skierowane do całego działu.\n"
                + "\n"
                + "Zakładka - Podgląd dokumentów:\n"
                + "Zakładka umożliwia podgląd dokómentów przy jednoczesnym sporządzaniu dokómentów/raportów.\n"
                + "\n"
                + "Zakładka - Dodawanie klienta:\n"
                + "Zakładka służy do dodania do bazy nowego klienta firmy wraz z jego danymi.\n"
                + "\n"
                + "Zakładka - Dodawanie zamówienia:\n"
                + "Zakładka służy do dodania do bazy nowego zamówienia.\n";

        ta.appendText(Opis1);

        ta.setMaxWidth(Double.MAX_VALUE);
        ta.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(ta, Priority.ALWAYS);
        GridPane.setHgrow(ta, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(l, 0, 0);
        expContent.add(ta, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
        return alert;
    }

    public Alert komPomocBrygadzista(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Witaj w dziale pomocy");
        alert.setContentText("Opis zakładek rozwini aby zobaczyć");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String tekst_pomoc = sw.toString();

        Label l = new Label("Opis poszczegulnych zakładek:");

        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setWrapText(true);
        String Opis1="Zakładka - Podgląd danych pracowników \n"
                + "Zakładka zawiera dane wszystkich pracowników firmy.\n"
                + "\n"
                + "Zakładka - Przydzielenie pojazdu oraz regionu dostawy dla Kierowcy:\n"
                + "Urzytkownik może w tej zakładce przydzielić pojazdy kierowcą jak równierz przydzielić im regiony dostaw zamówień. Urzytkownik może też w niej sporządzić biwrzący raport o przydziałach.\n"
                + "\n"
                + "Zakładka - Tworzenie raportów:\n"
                + "Zakładka ta pozwala na tworzenie raportów związanych z urlopami pracowników, przydziałem pojazdów i nie tylko oraz na tworzenie innych dokómentów.\n"
                + "\n"
                + "Zakładka - Podgląd dokumentów:\n"
                + "Zakładka umożliwia podgląd dokómentów przy jednoczesnym sporządzaniu dokómentów/raportów.\n"
                + "\n"
                + "Zakładka - Poczta:\n"
                + "Znajduje się tu poczta firmowa pracownika którą pracownik może zarządzać.\n"
                + "\n"
                + "Zakładka - Wyślij wiadomość:\n"
                + "Urzytkownik kożystakąc z tej zakładki może wysłać wiadomość do przełorzonych jak i innych pracowników.\n"
                + "\n"
                + "Zakładka - Wiadomość dział:\n"
                + "Pracownik może w niej przeglądać wiadomość skierowane do całego działu.\n"
                + "\n"
                + "Zakładka - Dokumenty dział:\n"
                + "Zakładka daje możliwość przeglądó firmowych dokómentów.\n"
                + "\n"
                + "Zakładka - Dane pracownika:\n"
                + "Umorzliwia pracownikowi podgląd swoich danych.\n"
                + "\n"
                + "Zakładka - Pojazdy podgląd dokumentów:\n"
                + "Zakładka pozwala na podgląd dokómętów związanych z pojazdami i ich pobranie.\n"
                + "\n";


        ta.appendText(Opis1);

        ta.setMaxWidth(Double.MAX_VALUE);
        ta.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(ta, Priority.ALWAYS);
        GridPane.setHgrow(ta, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(l, 0, 0);
        expContent.add(ta, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
        return alert;
    }

}
