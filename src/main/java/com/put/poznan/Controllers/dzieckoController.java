package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Posilek;
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

public class dzieckoController {
    private DataBase dataBase;

    @FXML
    private TextField imieField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private TextField dataField;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_posilkuBox;


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

        ObservableList<Long> listaPosilkow = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT p.idposilku FROM Posilek p");
        listaPosilkow.addAll(query.getResultList());
        id_posilkuBox.setItems(listaPosilkow);
    }

    @FXML
    public void add() {
        Dziecko d = new Dziecko();
        d.setImie(imieField.getText());
        d.setNazwisko(nazwiskoField.getText());

        try {
            d.setDataurodzenia(Time.valueOf(dataField.getText()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę urodzenia - sprawdź, czy jest w formacie...");
            alert.showAndWait();
        }

        try {
            d.setIddziecka(Integer.parseInt(idField.getText()));
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
            d.setGrupaprzedszkolnaIdgrupy((Long) id_grupyBox.getValue());
        }

        if (id_posilkuBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś numeru posilku");
            alert.showAndWait();
        } else {
            d.setPosilekIdposilku((Long) id_posilkuBox.getValue());
        }

        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci- dodaje mimo błednej godziny rozwozenia, pomija ja
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into DZIECKO(iddziecka, imie, nazwisko, dataurodzenia, grupaprzedszkolna_idgrupy, posilek_idposilku) values (?, ?, ?, ?, ?, ?)");
            stmt.setLong(1, d.getIddziecka());
            stmt.setString(2,d.getImie());
            stmt.setString(3, d.getNazwisko());
            stmt.setTime(4, d.getDataurodzenia());
            stmt.setLong(5, d.getGrupaprzedszkolnaIdgrupy());
            stmt.setLong(6, d.getPosilekIdposilku());

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

