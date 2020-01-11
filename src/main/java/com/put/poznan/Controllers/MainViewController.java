package com.put.poznan.Controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Optional;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Przedszkolanka;
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
    private TableView dzieckoTableView;

    //============GRUPA_PRZEDSZKOLNA--------------------------------\\
    @FXML
    private TableView grupa_przedszkolnaTableView;

    //============FESTYN--------------------------------\\
    @FXML
    private TableView festynTableView;

    //============HOSPITACJA--------------------------------\\
    @FXML
    private TableView hospitacjaTableView;

    //============OPLATA--------------------------------\\
    @FXML
    private TableView oplataTableView;

    //============POMOC_DYDAKTYCZNA--------------------------------\\
    @FXML
    private TableView pomoc_dydaktycznaTableView;

    //============POSILEK--------------------------------\\
    @FXML
    private TableView posilekTableView;

    //============SEKRETARKA--------------------------------\\
    @FXML
    private TableView sekretarkaTableView;

    //============ZAJECIA_DODATKOWE--------------------------------\\
    @FXML
    private TableView zajecia_dodatkoweTableView;

    //============ZEBRANIE_Z_RODZICAMI--------------------------------\\
    @FXML
    private TableView zebranie_rodziceTableView;

    //-----------------------------------------------------

    @FXML
    public void initialize() {

        //przedszkolankaTableView.getColumns().addAll(idColumn, imieColumn, nazwiskoColumn, kwalifikacjeColumn, placaColumn, idGrupyColumn, idHospitacjiColumn);

        //idPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("id"));
        idPrzedszkolankaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
        imiePrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("imie"));
        nazwiskoPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("nazwisko"));
        kwalifikacjePrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("kwalifikacje"));
        placaPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("placa"));
        idGrupyPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("idGrupy"));
        idHospitacjiPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("idHospitacji"));

        przedszkolankaTableView.setEditable(false);             //modyfikacja tylko przy przycisku

        przedszkolanki = FXCollections.observableList(new ArrayList<>());
        Przedszkolanka p = new Przedszkolanka();
        p.setImie("Ania");
        p.setIdHospitacji(2);
        p.setId(232);
        System.out.println("Id: " + p.getId() + " imie:  " + p.getImie() + " placa: " + p.getPlaca() + " idHos: " + p.getIdHospitacji());
        przedszkolanki.add(p);
        for(int i=0; i<5; i++) {
            przedszkolanki.add(new Przedszkolanka());
        }
        System.out.println(przedszkolanki.toString());

        przedszkolankaTableView.setItems(przedszkolanki);
        System.out.println(idPrzedszkolankaColumn.getCellObservableValue(0).toString());

/*
        dzieckoTableView.setEditable(false);             //modyfikacja tylko przy przycisku
        TableColumn dataUrodzenia = new TableColumn("data urodzenia");
        dataUrodzenia.setPrefWidth(100.0);
        TableColumn idPosilku = new TableColumn("id posiłku");

       // dzieckoTableView.getColumns().setAll(idColumn, imieColumn, nazwiskoColumn, dataUrodzenia, idGrupyColumn, idPosilku);


        grupa_przedszkolnaTableView.setEditable(false);
        TableColumn miejsce = new TableColumn("sala");
        TableColumn nazwa = new TableColumn("nazwa");
        TableColumn wiek = new TableColumn("wiek");
        TableColumn osobaOdpowiedzialna = new TableColumn("id przedszkolanki");

        grupa_przedszkolnaTableView.getColumns().setAll(idColumn, miejsce, nazwa, wiek, osobaOdpowiedzialna);


        festynTableView.setEditable(false);
        osobaOdpowiedzialna.setPrefWidth(150.0);
        TableColumn data = new TableColumn("data");
        TableColumn haslo = new TableColumn("hasło");

       // festynTableView.getColumns().setAll(idColumn, idGrupyColumn, osobaOdpowiedzialna, data, haslo);


        hospitacjaTableView.setEditable(false);
        TableColumn osobaNadzorujaca = new TableColumn("id nadzorującego");
        osobaNadzorujaca.setPrefWidth(150.0);

        hospitacjaTableView.getColumns().setAll(idColumn, data, osobaOdpowiedzialna, osobaNadzorujaca);


        oplataTableView.setEditable(false);
        TableColumn wielkosc = new TableColumn("wysokosc");
        TableColumn przedmiot = new TableColumn("przedmiot");
        TableColumn czestosc = new TableColumn("częstość");

        //polaczenia chyba nie powinny być widoczne, wiec to starczy???
        oplataTableView.getColumns().setAll(idColumn, wielkosc, przedmiot, czestosc);

        pomoc_dydaktycznaTableView.setEditable(false);
        TableColumn oplata = new TableColumn("oplaty");

        //pomoc_dydaktycznaTableView.getColumns().setAll(idColumn, przedmiot, oplata, idGrupyColumn, osobaOdpowiedzialna);


        posilekTableView.setEditable(false);
        TableColumn godzina = new TableColumn("godzina rozdawania");
        godzina.setPrefWidth(120.0);
        TableColumn dieta = new TableColumn("dieta");

        posilekTableView.getColumns().setAll(idColumn, nazwa, godzina, dieta);


        sekretarkaTableView.setEditable(false);
        TableColumn pocz = new TableColumn("poczatek pracy");
        pocz.setPrefWidth(100.0);
        TableColumn koniec = new TableColumn("koniec pracy");

        sekretarkaTableView.getColumns().setAll(idColumn, imieColumn, nazwiskoColumn, kwalifikacjeColumn, placaColumn, pocz, koniec);


        zajecia_dodatkoweTableView.setEditable(false);
        TableColumn czas = new TableColumn("czas tygodniowo");
        czas.setPrefWidth(100.0);

       // zajecia_dodatkoweTableView.getColumns().setAll(idColumn, nazwa, data, oplata, czas, idGrupyColumn);


        zebranie_rodziceTableView.setEditable(false);
        TableColumn czyobow = new TableColumn("obowiązkowe");
        czyobow.setPrefWidth(100.0);

       // zebranie_rodziceTableView.getColumns().setAll(idColumn, data, idGrupyColumn, miejsce, osobaOdpowiedzialna, czyobow);*/
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
        FXMLLoader loader = App.getFXMLLoader("przedszkolanka");
        Parent root = loader.load();
        PrzedszkolankaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    private void addPosilek() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("posilek");
        Parent root = loader.load();
        posilekController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addDziecko() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("dziecko");
        Parent root = loader.load();
        dzieckoController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addFestyn() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("festyn");
        Parent root = loader.load();
        dzieckoController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addGrupa() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("grupa");
        Parent root = loader.load();
        grupaPrzedszkolnaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addHospitacja() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("hospitacja");
        Parent root = loader.load();
        hospitacjaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addOplata() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("oplata");
        Parent root = loader.load();
        oplataController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addPomoc() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("pomoc");
        Parent root = loader.load();
        pomocDydaktycznaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addZajecia() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("zajecia");
        Parent root = loader.load();
        zajeciaDodatkoweController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    private void addZebranie() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("zebranie");
        Parent root = loader.load();
        zebranieRodziceController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addSekretarka() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("sekretarka");
        Parent root = loader.load();
        sekretarkaController c = loader.getController();
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
