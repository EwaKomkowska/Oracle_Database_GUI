package com.put.poznan.Controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.PropertyResourceBundle;

import com.put.poznan.JDBC.DataBase;
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

    @FXML
    private Button przedszkolankaAddButton;
    @FXML
    private Button przedszkolankaRemoveButton;
    @FXML
    private TableView <Przedszkolanka> przedszkolankaTableView;
    @FXML
    private TableView dzieckoTableView;
    @FXML
    private TableView grupa_przedszkolnaTableView;
    @FXML
    private TableView festynTableView;
    @FXML
    private TableView hospitacjaTableView;
    @FXML
    private TableView oplataTableView;
    @FXML
    private TableView pomoc_dydaktycznaTableView;
    @FXML
    private TableView posilekTableView;
    @FXML
    private TableView sekretarkaTableView;
    @FXML
    private TableView zajecia_dodatkoweTableView;
    @FXML
    private TableView zebranie_rodziceTableView;

    @FXML
    public void initialize() {
        przedszkolankaTableView.setEditable(false);             //modyfikacja tylko przy przycisku
        TableColumn<Przedszkolanka, Integer> id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Przedszkolanka, String> imie = new TableColumn<>("imie");
        imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        TableColumn<Przedszkolanka, String> nazwisko = new TableColumn<>("nazwisko");
        TableColumn<Przedszkolanka, String> kwalifikacje = new TableColumn<>("kwalifikacje");
        TableColumn<Przedszkolanka, Double> placa = new TableColumn<>("płaca");
        TableColumn<Przedszkolanka, Integer> idGrupy = new TableColumn<>("id grupy");
        TableColumn<Przedszkolanka, Integer> idHospitacji= new TableColumn<>("id hospitacji");

        ObservableList<Przedszkolanka> products = FXCollections.observableArrayList();
        Przedszkolanka p = new Przedszkolanka();
        p.setImie("Ania");
        System.out.println(p.getImie());
        products.add(p);
        System.out.println(products.toString());

        przedszkolankaTableView.setItems(products);
        przedszkolankaTableView.getColumns().addAll(id, imie, nazwisko, kwalifikacje, placa, idGrupy, idHospitacji);


        dzieckoTableView.setEditable(false);             //modyfikacja tylko przy przycisku
        TableColumn dataUrodzenia = new TableColumn("data urodzenia");
        dataUrodzenia.setPrefWidth(100.0);
        TableColumn idPosilku = new TableColumn("id posiłku");

        dzieckoTableView.getColumns().setAll(id, imie, nazwisko, dataUrodzenia, idGrupy, idPosilku);


        grupa_przedszkolnaTableView.setEditable(false);
        TableColumn miejsce = new TableColumn("sala");
        TableColumn nazwa = new TableColumn("nazwa");
        TableColumn wiek = new TableColumn("wiek");
        TableColumn osobaOdpowiedzialna = new TableColumn("id przedszkolanki");

        grupa_przedszkolnaTableView.getColumns().setAll(id, miejsce, nazwa, wiek, osobaOdpowiedzialna);


        festynTableView.setEditable(false);
        osobaOdpowiedzialna.setPrefWidth(150.0);
        TableColumn data = new TableColumn("data");
        TableColumn haslo = new TableColumn("hasło");

        festynTableView.getColumns().setAll(id, idGrupy, osobaOdpowiedzialna, data, haslo);


        hospitacjaTableView.setEditable(false);
        TableColumn osobaNadzorujaca = new TableColumn("id nadzorującego");
        osobaNadzorujaca.setPrefWidth(150.0);

        hospitacjaTableView.getColumns().setAll(id, data, osobaOdpowiedzialna, osobaNadzorujaca);


        oplataTableView.setEditable(false);
        TableColumn wielkosc = new TableColumn("wysokosc");
        TableColumn przedmiot = new TableColumn("przedmiot");
        TableColumn czestosc = new TableColumn("częstość");

        //polaczenia chyba nie powinny być widoczne, wiec to starczy???
        oplataTableView.getColumns().setAll(id, wielkosc, przedmiot, czestosc);

        pomoc_dydaktycznaTableView.setEditable(false);
        TableColumn oplata = new TableColumn("oplaty");

        pomoc_dydaktycznaTableView.getColumns().setAll(id, przedmiot, oplata, idGrupy, osobaOdpowiedzialna);


        posilekTableView.setEditable(false);
        TableColumn godzina = new TableColumn("godzina rozdawania");
        godzina.setPrefWidth(120.0);
        TableColumn dieta = new TableColumn("dieta");

        posilekTableView.getColumns().setAll(id, nazwa, godzina, dieta);


        sekretarkaTableView.setEditable(false);
        TableColumn pocz = new TableColumn("poczatek pracy");
        pocz.setPrefWidth(100.0);
        TableColumn koniec = new TableColumn("koniec pracy");

        sekretarkaTableView.getColumns().setAll(id, imie, nazwisko, kwalifikacje, placa, pocz, koniec);


        zajecia_dodatkoweTableView.setEditable(false);
        TableColumn czas = new TableColumn("czas tygodniowo");
        czas.setPrefWidth(100.0);

        zajecia_dodatkoweTableView.getColumns().setAll(id, nazwa, data, oplata, czas, idGrupy);


        zebranie_rodziceTableView.setEditable(false);
        TableColumn czyobow = new TableColumn("obowiązkowe");
        czyobow.setPrefWidth(100.0);

        zebranie_rodziceTableView.getColumns().setAll(id, data, idGrupy, miejsce, osobaOdpowiedzialna, czyobow);
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
        Przedszkolanka c = loader.getController();
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
