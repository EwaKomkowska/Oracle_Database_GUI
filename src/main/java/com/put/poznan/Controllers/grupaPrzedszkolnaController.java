package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Festyn;
import com.put.poznan.SchemaObjects.Grupaprzedszkolna;
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

public class grupaPrzedszkolnaController {
    private DataBase dataBase;

    @FXML
    private TextField idField;
    @FXML
    private TextField salaField;
    @FXML
    private TextField nazwaField;
    @FXML
    private TextField wiekField;
    @FXML
    private ComboBox id_przedszkolankiBox;

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
    }


    @FXML
    public void add() {
        Grupaprzedszkolna g = new Grupaprzedszkolna();
        g.setNazwa(nazwaField.getText());

        try {
            g.setIdgrupy(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        try {
            g.setWiekdzieci((long) Integer.parseInt(wiekField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś nieodpowiedni wiek dzieci!");
            alert.showAndWait();
        }

        try {
            g.setSala((long) Integer.parseInt(salaField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Sala musi być liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id przedszkolanki");
            alert.showAndWait();
        } else {
            g.setIdprac((Long) id_przedszkolankiBox.getValue());
        }


        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci- dodaje mimo błednej godziny rozwozenia, pomija ja
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into GRUPAPRZEDSZKOLNA(idgrupy, sala, nazwa, wiekdzieci, idprac) values (?, ?, ?, ?, ?)");
            stmt.setLong(1, g.getIdgrupy());
            stmt.setLong(2, g.getSala());
            stmt.setString(3, g.getNazwa());
            stmt.setLong(4, g.getWiekdzieci());
            stmt.setLong(5, g.getIdprac());

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
