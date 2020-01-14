package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Zebraniezrodzicami;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.type.TrueFalseType;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;

public class zebranieRodziceController {
    private DataBase dataBase;


    @FXML
    private TextField idField;
    @FXML
    private TextField dataField;
    @FXML
    private TextField salaField;
    @FXML
    private ComboBox obowiazkoweBox;
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

        ObservableList<Boolean> obowiazek = FXCollections.observableList(new ArrayList<>());
        obowiazek.addAll(true, false);
        obowiazkoweBox.setItems(obowiazek);
    }

    @FXML
    public void add() {
        Zebraniezrodzicami zr = new Zebraniezrodzicami();

        try {
            zr.setData(Time.valueOf(dataField.getText()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę zebrania - sprawdź, czy jest w formacie...");
            alert.showAndWait();
        }

        try {
            zr.setIdzebrania(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        try {
            zr.setMiejsca((long) Integer.parseInt(salaField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną salę - sprawdź, czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        if (id_grupyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś numeru grupy");
            alert.showAndWait();
        } else {
            zr.setGrupa((Long) id_grupyBox.getValue());
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id przedszkolanki");
            alert.showAndWait();
        } else {
            zr.setProwadzacyzebranie((Long) id_przedszkolankiBox.getValue());
        }

        if (obowiazkoweBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś wariantu obowiązkowości");
            alert.showAndWait();
        } else {
            zr.setCzyobowiazkowe(obowiazkoweBox.getValue().toString());
        }

        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci- dodaje mimo błednej godziny rozwozenia, pomija ja
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into ZEBRANIEZRODZICAMI(idzebrania, data, grupa, miejsca, prowadzacyzebranie, czyobowiazkowe, PRZEDSZKOLANKA_IDHOSPITACJI) values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setLong(1, zr.getIdzebrania());
            stmt.setTime(2, zr.getData());
            stmt.setLong(3, zr.getGrupa());
            stmt.setLong(4, zr.getMiejsca());
            stmt.setLong(5, zr.getProwadzacyzebranie());
            stmt.setString(6, zr.getCzyobowiazkowe());
            stmt.setInt(7,1);
            //TODO: nie mozna wartosci null do przedszkolanka_idhospitacji NAPRAWIC

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