package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Zajeciadodatkowe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.persistence.Query;
import javax.persistence.Table;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;

public class zajeciaDodatkoweController {
    private DataBase dataBase;


    @FXML
    private TextField idField;
    @FXML
    private TextField rodzajField;
    @FXML
    private TextField dataField;
    @FXML
    private TextField czasField;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_oplatyBox;


    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @FXML
    public void initialize() {
        ObservableList<Long> listaGrup = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idgrupy FROM Grupaprzedszkolna p");
        listaGrup.addAll(query.getResultList());
        id_grupyBox.setItems(listaGrup);

        ObservableList<Long> listaOplat = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT o.idoplaty FROM Oplata o");
        listaOplat.addAll(query.getResultList());
        id_oplatyBox.setItems(listaOplat);
    }

    @FXML
    public void add() {
        Zajeciadodatkowe zd = new Zajeciadodatkowe();
        zd.setRodzaj(rodzajField.getText());

        try {
            zd.setDataprowadzenia(Time.valueOf(dataField.getText()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę wydarzenia - sprawdź, czy jest w formacie...");
            alert.showAndWait();
        }

        try {
            zd.setIdzajecia(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        try {
            zd.setCzastygodniowo((long) Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędny czas tygodniowo - sprawdź, czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        if (id_grupyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś numeru grupy");
            alert.showAndWait();
        } else {
            zd.setDlakogo((Long) id_grupyBox.getValue());
        }

        if (id_oplatyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id oplaty");
            alert.showAndWait();
        } else {
            zd.setOplaty((Long) id_oplatyBox.getValue());
        }

        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci- dodaje mimo błednej godziny rozwozenia, pomija ja
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into ZAJECIADODATKOWE(idzajecia, rodzaj, dataprowadzenia, oplaty, czastygodniowo, dlakogo) values (?, ?, ?, ?, ?, ?)");
            stmt.setLong(1, zd.getIdzajecia());
            stmt.setString(2, zd.getRodzaj());
            stmt.setTime(3, zd.getDataprowadzenia());
            stmt.setLong(4, zd.getOplaty());
            stmt.setLong(5, zd.getCzastygodniowo());
            stmt.setLong(6, zd.getDlakogo());

            stmt.executeQuery();

            MainViewController.add(this.dataBase);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void cancel() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("primary");
        Parent root = loader.load();
        MainViewController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    public void clear() {
        //TODO: czy da sie jakos wyczyscic wcisniety tylko
    }
}