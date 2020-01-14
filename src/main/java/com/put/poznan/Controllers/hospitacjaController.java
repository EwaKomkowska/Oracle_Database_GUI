package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Hospitacja;
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
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;

public class hospitacjaController {
    private DataBase dataBase;

    @FXML
    private TextField idField;
    @FXML
    private TextField dataField;
    @FXML
    private ComboBox id_przedszkolankiBox;
    @FXML
    private ComboBox id_przedszkolanki2Box;


    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @FXML
    public void initialize() {
        ObservableList<Long> listaPrzedszkolanek = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idprac FROM Przedszkolanka p");
        listaPrzedszkolanek.addAll(query.getResultList());
        id_przedszkolankiBox.setItems(listaPrzedszkolanek);
        id_przedszkolanki2Box.setItems(listaPrzedszkolanek);
    }

    @FXML
    public void add() {
        //TODO: żeby przedszkolanka nie mogla nadzorowac samej siebie
        Hospitacja h = new Hospitacja();
        try {
            h.setIdhospitacji(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        try {
            h.setTermin(Time.valueOf(dataField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę hospitacji - sprawdź, czy jest w formacie...");
            alert.showAndWait();
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś nadzorowanego");
            alert.showAndWait();
        } else {
            h.setKtonadzorowany((long)id_przedszkolankiBox.getValue());
        }

        if (id_przedszkolanki2Box.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś nadzorujacego");
            alert.showAndWait();
        } else {
            h.setKtonadzoruje((byte[])id_przedszkolanki2Box.getValue());
        }

        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci- dodaje mimo błednej godziny rozwozenia, pomija ja
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into HOSPITACJA(idhospitacji, termin, ktonadzorowany, ktonadzoruje) values (?, ?, ?, ?)");
            stmt.setLong(1, h.getIdhospitacji());
            stmt.setTime(2, h.getTermin());
            stmt.setLong(3, h.getKtonadzorowany());
            stmt.setBytes(4, h.getKtonadzoruje());

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
