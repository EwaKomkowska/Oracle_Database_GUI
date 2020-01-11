package com.put.poznan.Controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainViewController {

    private DataBase dataBase;

    public MainViewController(){    }

    //============PRZEDSZKOLANKA--------------------------------\\
    @FXML
    private Button przedszkolankaAddButton;
    @FXML
    private Button przedszkolankaRemoveButton;
    @FXML
    private TableView<Przedszkolanka> przedszkolankaTableView;
    @FXML
    private TableColumn<Przedszkolanka, Number> idPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, String> imiePrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, String> nazwiskoPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, String> kwalifikacjePrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, Number> placaPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, Number> idGrupyPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, Number> idHospitacjiPrzedszkolankaColumn;

    private ObservableList<Przedszkolanka> przedszkolanki;

    //============DZIECKO--------------------------------\\

    @FXML
    private TableView<Dziecko> dzieckoTableView;

    @FXML
    private TableColumn<Dziecko, Number> idDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, String> imieDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, String> nazwiskoDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, Date> dataUrodzeniaDzieckoColumn; //TODO: Time
    @FXML
    private TableColumn<Dziecko, Number> grupaPrzedszkolnaDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, Number> idPosilkuDzieckoColumn;

    private ObservableList<Dziecko> dzieci;

    //============GRUPA_PRZEDSZKOLNA--------------------------------\\
    @FXML
    private TableView<Grupaprzedszkolna> grupa_przedszkolnaTableView;

    @FXML
    private TableColumn<Grupaprzedszkolna, Number> idGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, Number> salaGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, String> nazwaGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, Number> wiekDzieciGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, Number> idPracGrupaPrzedszkolnaColumn;

    private ObservableList<Grupaprzedszkolna> grupyPrzedszkolne;

    //============ETAT--------------------------------\\
    @FXML
    private TableView<Etaty> etatTableView;

    @FXML
    private TableColumn<Etaty, String> nazwaEtatColumn;
    @FXML
    private TableColumn<Etaty, Number> placaMinEtatColumn;
    @FXML
    private TableColumn<Etaty, Number> placaMaxEtatColumn;

    private ObservableList<Etaty> etaty;

    //============FESTYN--------------------------------\\

    @FXML
    private TableView<Festyn> festynTableView;

    @FXML
    private TableColumn<Festyn, Number> idFestynColumn;
    @FXML
    private TableColumn<Festyn, Number> grupaWystepujacaFestynColumn;
    @FXML
    private TableColumn<Festyn, Number> osobaOdpowiedzialnaFestynColumn;
    @FXML
    private TableColumn<Festyn, Date> terminWydarzeniaFestynColumn;
    @FXML
    private TableColumn<Festyn, String> nazwaFestynColumn;

    private ObservableList<Festyn> festyny;

    //============HOSPITACJA--------------------------------\\
    @FXML
    private TableView<Hospitacja> hospitacjaTableView;

    @FXML
    private TableColumn<Hospitacja, Number> idHospitacjaColumn;
    @FXML
    private TableColumn<Hospitacja, Date> terminHospitacjaColumn;
    @FXML
    private TableColumn<Hospitacja, Number> ktoNadzorowanyHospitacjaColumn;
    @FXML
    private TableColumn<Hospitacja, Number> ktoNadzorujeHospitacjaColumn;

    private ObservableList<Hospitacja> hospitacje;

    //============OPLATA--------------------------------\\
    @FXML
    private TableView<Oplata> oplataTableView;

    @FXML
    private TableColumn<Oplata, Number> idOplataColumn;
    @FXML
    private TableColumn<Oplata, Number> wielkoscOplataColumn;
    @FXML
    private TableColumn<Oplata, String> przedmiotOplataColumn;
    @FXML
    private TableColumn<Oplata, String> czestoscOplataColumn;

    private ObservableList<Oplata> oplaty;

    //============POMOC_DYDAKTYCZNA--------------------------------\\
    @FXML
    private TableView<Pomocdydaktyczna> pomoc_dydaktycznaTableView;

    @FXML
    private TableColumn<Pomocdydaktyczna, Number> idPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, String> rodzajPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> dodatkoweOplatyPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> grupaDocelowaPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> osobaOdpowiedzialnaPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> przedszkolankaIdPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> grupaPrzedszkolnaIdPomocDydatktycznaColumn;

    private ObservableList<Pomocdydaktyczna> pomoceDydaktyczne;

    //============POSILEK--------------------------------\\
    @FXML
    private TableView<Posilek> posilekTableView;

    @FXML
    private TableColumn<Posilek, Number> idPosilekColumn;
    @FXML
    private TableColumn<Posilek, String> nazwaPosilekColumn;
    @FXML
    private TableColumn<Posilek, Time> godzRozwozeniaPosilekColumn;
    @FXML
    private TableColumn<Posilek, String> dietaPosilekColumn;

    private ObservableList<Posilek> posliki;

    //============SEKRETARKA--------------------------------\\
    @FXML
    private TableView<Sekretarka> sekretarkaTableView;

    @FXML
    private TableColumn<Sekretarka, Number> idSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, String> imieSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, String> nazwiskoSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, String> kwalifikacjeSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, Number> placaSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, Time> godzRozpoczeciaPracySekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, Time> godzZakonczeniaPracySekretarkaColumn;

    private ObservableList<Sekretarka> sekretarki;

    //============ZAJECIA_DODATKOWE--------------------------------\\
    @FXML
    private TableView<Zajeciadodatkowe> zajecia_dodatkoweTableView;

    @FXML
    private TableColumn<Zajeciadodatkowe, Number> idZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, String> rodzajZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, Time> dataProwadzeniaZajeciaDodatkoweColumn; //TODO: date???
    @FXML
    private TableColumn<Zajeciadodatkowe, Number> oplatyZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, Number> czasTygodniowoZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, Number> dlaKogoZajeciaDodatkoweColumn;

    private ObservableList<Zajeciadodatkowe> zajeciaDodatkowe;

    //============ZEBRANIE_Z_RODZICAMI--------------------------------\\
    @FXML
    private TableView<Zebraniezrodzicami> zebranie_rodziceTableView;

    @FXML
    private TableColumn<Zebraniezrodzicami, Number> idZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Time> dataZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> grupaZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> miejscaSalaZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> prowadzacyZebranieZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, String> czyObowiazkoweZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> przedszkolankaIdHospitacjiZebranieRodziceColumn;

    private ObservableList<Zebraniezrodzicami> zebraniaZRodzicami;

    //-----------------------------------------------------


    @FXML
    public void initialize() {

        //przedszkolankaTableView.getColumns().addAll(idColumn, imieColumn, nazwiskoColumn, kwalifikacjeColumn, placaColumn, idGrupyColumn, idHospitacjiColumn);

        //idPrzedszkolankaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
        idPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("idprac"));
        imiePrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("imie"));
        nazwiskoPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("nazwisko"));
        kwalifikacjePrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("kwalifikacje"));
        placaPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("placa"));
        //FIXME: tych dwoch kolumn nie ma wgl w obiekcie przedszkolanka
        // idGrupyPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("idGrupy"));
        //FIXME: NIE MA ID TEGO
        // idHospitacjiPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("idHospitacji"));

        przedszkolankaTableView.setEditable(false);             //modyfikacja tylko przy przycisku

        //============DzieckoColumns--------------------------------\\
        idDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Number>("iddziecka"));
        imieDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, String>("imie"));
        nazwiskoDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, String>("nazwisko"));
        dataUrodzeniaDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Date>("dataurodzenia"));
        grupaPrzedszkolnaDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Number>("grupaprzedszkolnaIdgrupy"));
        idPosilkuDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Number>("posilekIdposilku"));

        //TODO: czy set eitable wszedzie tez???
        //============GrupaPrzedszkolnaColumns--------------------------------\\
        idGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("idgrupy"));
        salaGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("sala"));
        nazwaGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, String>("nazwa"));
        wiekDzieciGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("wiekdzieci"));
        idPracGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("idprac"));

        //============EtatColumns--------------------------------\\
        nazwaEtatColumn.setCellValueFactory(new PropertyValueFactory<Etaty, String>("nazwa"));
        placaMinEtatColumn.setCellValueFactory(new PropertyValueFactory<Etaty, Number>("placaMin"));
        placaMaxEtatColumn.setCellValueFactory(new PropertyValueFactory<Etaty, Number>("placaMax"));

        //============FestynColumns--------------------------------\\
        idFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Number>("idfestynu"));
        grupaWystepujacaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Number>("grupawystepujaca"));
        osobaOdpowiedzialnaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Number>("osobaodpowiedzialna"));
        terminWydarzeniaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Date>("terminwydarzena")); //TODO: date czy time
        nazwaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, String>("nazwaHaslo"));

        //============HospitacjaColumns--------------------------------\\
        idHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Number>("idhospitacji"));
        terminHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Date>("termin")); //TODO: date czy time
        ktoNadzorowanyHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Number>("ktonadzorowany"));
        ktoNadzorujeHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Number>("ktonadzoruje"));

        //============OplataColumns--------------------------------\\
        idOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, Number>("idoplaty"));
        wielkoscOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, Number>("wielkosc"));
        przedmiotOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, String>("przedmiotoplaty"));
        czestoscOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, String>("czestosc"));

        //============PomocDydatktycznaColumns--------------------------------\\
        idPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("idpomocy"));
        rodzajPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, String>("rodzaj"));
        dodatkoweOplatyPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("dodatkoweoplaty"));
        grupaDocelowaPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("grupadocelowa"));
        osobaOdpowiedzialnaPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("osobaodpowiedzialna"));
        przedszkolankaIdPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("przedszkolankaIdprac"));
        grupaPrzedszkolnaIdPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("grupaprzedszkolnaIdgrupy"));

        //============PosilekColumns--------------------------------\\
        idPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, Number>("idposilku"));
        nazwaPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, String>("nazwa"));
        godzRozwozeniaPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, Time>("godzrozwozenia"));
        dietaPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, String>("dieta"));

        //============SekretarkaColumns--------------------------------\\
        idSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Number>("idprac"));
        imieSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, String>("imie"));
        nazwiskoSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, String>("nazwisko"));
        kwalifikacjeSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, String>("kwalifikacje"));
        placaSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Number>("placa"));
        godzRozpoczeciaPracySekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Time>("godzrozpoczeciapracy"));
        godzZakonczeniaPracySekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Time>("godzzakonczeniapracy"));

        //============ZajeciaDodatkoweColumns--------------------------------\\
        idZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("idzajecia"));
        rodzajZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, String>("rodzaj"));
        dataProwadzeniaZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Time>("dataprowadzenia"));
        oplatyZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("oplaty"));
        czasTygodniowoZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("czastygodniowo"));
        dlaKogoZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("dlakogo"));

        //============ZebranieRodziceColumns--------------------------------\\
        idZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("idzebrania"));
        dataZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Time>("data"));
        grupaZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("grupa"));
        miejscaSalaZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("miejscaSala"));
        prowadzacyZebranieZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("prowadzacyzebranie"));
        czyObowiazkoweZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, String>("czyobowiazkowe"));
        przedszkolankaIdHospitacjiZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("przedszkolankaIdhospitacji"));


        przedszkolanki = FXCollections.observableList(new ArrayList<>());
        for(int i=0; i<5; i++) {
            Przedszkolanka p = new Przedszkolanka();
            p.setImie(Integer.toString(i));
            przedszkolanki.add(p);
        }
        System.out.println(przedszkolanki.toString());

        przedszkolankaTableView.setItems(przedszkolanki);
    }

    @FXML
    private void removePrzedszkolanka() {
        int idPrzed = 5;
        String statement = "DELETE FROM PRZEDSZKOLANKA WHERE IDPRAC = ?";

        remove(statement, idPrzed);
    }

    @FXML
    private void removeSekretarka() {
        int id = 5;
        String statement = "DELETE FROM SEKRETARKA WHERE IDPRAC = ?";

        remove(statement, id);
    }

    @FXML
    private void removeDziecko() {
        int id = 5;
        String statement = "DELETE FROM DZIECKO WHERE IDDZIECKA = ?";

        remove(statement, id);
    }

    @FXML
    private void removeFestyn() {
        int id = 5;
        String statement = "DELETE FROM FESTYN WHERE IDFESTYNU = ?";

        remove(statement, id);
    }

    @FXML
    private void removeOplata() {
        int id = 5;
        String statement = "DELETE FROM OPLATA WHERE IDOPLATY = ?";

        remove(statement, id);
    }


    @FXML
    private void removeZajecia() {
        int id = 5;
        String statement = "DELETE FROM ZAJECIADODATKOWE WHERE IDZAJECIA = ?";

        remove(statement, id);
    }

    @FXML
    private void removeZebranie() {
        int id = 5;
        String statement = "DELETE FROM ZEBRANIEZRODZICAMI WHERE IDZEBRANIA = ?";

        remove(statement, id);
    }

    @FXML
    private void removePosilek() {
        int id = 5;
        String statement = "DELETE FROM POSILEK WHERE IDPOSILKU = ?";

        remove(statement, id);
    }

    @FXML
    private void removeHospitacja() {
        int id = 5;
        String statement = "DELETE FROM HOSPITACJA WHERE IDHOSPITACJI = ?";

        remove(statement, id);
    }

    @FXML
    private void removeGrupa() {
        int id = 5;
        String statement = "DELETE FROM GRUPAPRZEDSZKOLNA WHERE IDGRUPY = ?";

        remove(statement, id);
    }

    @FXML
    private void removePomoc() {
        int id = 5;
        String statement = "DELETE FROM POMOCDYDAKTYCZNA WHERE IDPOMOCY = ?";

        remove(statement, id);
    }


    @FXML
    private void addPrzedszkolanka() throws IOException {
        //tu będzie wiecej zabawy, bo to chyba trzeba zrobić z pobieraniem danych w nowym oknie
        FXMLLoader loader = App.getFXMLLoader("przedszkolanka");
        Parent root = loader.load();
        PrzedszkolankaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    private void remove(String statement, int parameter) {
        PreparedStatement stmt;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        try {
            stmt = App.getDataBase().getConnection().prepareStatement(statement);
            stmt.setInt(1, parameter);        //ustaw pierwszy znak zapytania ma wartosc paramtetru

            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Jesteś pewien, że chcesz usunąć obekt o id: " + parameter + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int deletedRows = stmt.executeUpdate();         //ilosc usunietych wierszy

                if (deletedRows == 0) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("You can't remove this object. \nMaybe it's not exist?\nPlease, try again!\n Choose one of object that you can see on the list.");
                    alert.showAndWait();
                } else {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Usunięto poprawnie " + deletedRows + " obiektów!");
                    alert.showAndWait();
                }
            } /*else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Zrezygnowałeś z usuwania!");
                alert.showAndWait();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: CHECK IF THIS WORKS
    public MainViewController(DataBase dataBase){
        this.setDataBase(dataBase);
    }

    public void setDataBase(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @FXML
    private void logOut() throws IOException {
        this.dataBase.closeConnection();
        //Platform.exit(); //TODO: zamiast tego idz do login formularza od nowa
       // App.getStage().setScene(new Scene(App.loadFXML("login")) );
        FXMLLoader loader = App.getFXMLLoader("login");
        Parent root = loader.load();
        LoginController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
