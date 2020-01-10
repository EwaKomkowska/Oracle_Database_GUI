package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Przedszkolanka {
    private int id;
    private String imie;
    private String nazwisko;
    private String kwalifikacje;
    private int placa;
    private int idGrupy;
    private int idHospitacji;

    private DataBase dataBase;

    @FXML
    private TextField imieField;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private TextField kwalifikacjeField;
    @FXML
    private TextField idField;
    @FXML
    private TextField placaField;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_hospitacjiBox;


    @FXML
    public void initialize() {
    }

    public String getImie() {
        return imie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void add () {
        //tutaj zrobić zczytywanie z okienka, więc bez parametrów
        try {
            this.id = Integer.parseInt(idField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }
        this.imie = imieField.getText();
        this. nazwisko = nazwiskoField.getText();
        this.kwalifikacje = kwalifikacjeField.getText();
        try {
            this.placa = Integer.parseInt(placaField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną płacę - sprawdź, czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
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
        //czy da sie jakos wyczyscic wcisniety tylko
    }
}