package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Festyn;
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
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class festynController {
    private DataBase dataBase;

    @FXML
    private TextField idField;
    @FXML
    private TextField hasloField;
    @FXML
    private TextField terminField;
    @FXML
    private ComboBox id_grupyBox;
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
        ObservableList<Long> listaGrup = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idgrupy FROM Grupaprzedszkolna p");
        listaGrup.addAll(query.getResultList());
        id_grupyBox.setItems(listaGrup);

        ObservableList<Long> listaPrzedszkolanek = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT p.idprac FROM Przedszkolanka p");
        listaPrzedszkolanek.addAll(query.getResultList());
        id_przedszkolankiBox.setItems(listaPrzedszkolanek);
    }


    @FXML
    public void add() {
        Festyn f = new Festyn();
        f.setHaslo(hasloField.getText());

        try {
            f.setTerminwydarzena(Time.valueOf(terminField.getText()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę wydarzenia - sprawdź, czy jest w formacie...");
            alert.showAndWait();
        }

        try {
            f.setIdfestynu(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        if (id_grupyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś numeru grupy");
            alert.showAndWait();
        } else {
            f.setGrupawystepujaca((Long) id_grupyBox.getValue());
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id przedszkolanki");
            alert.showAndWait();
        } else {
            f.setOsobaodpowiedzialna((Long) id_przedszkolankiBox.getValue());
        }

        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci- dodaje mimo błednej godziny rozwozenia, pomija ja
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into FESTYN(idfestynu, grupawystepujaca, osobaodpowiedzialna, terminwydarzena, haslo) values (?, ?, ?, ?, ?)");
            stmt.setLong(1, f.getIdfestynu());
            stmt.setLong(2, f.getGrupawystepujaca());
            stmt.setLong(3, f.getOsobaodpowiedzialna());
            stmt.setTime(4, f.getTerminwydarzena());
            stmt.setString(5, f.getHaslo());

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
