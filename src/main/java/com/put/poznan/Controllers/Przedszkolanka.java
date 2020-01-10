package com.put.poznan.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;

import java.io.IOException;

public class Przedszkolanka {
    private int id;
    private String imie;
    private String nazwisko;
    private String kwalifikacje;
    private int placa;
    private int idGrupy;
    private int idHospitacji;

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
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

    }

    @FXML
    public void cancel() throws IOException {
        App.getStage().setScene(new Scene(App.loadFXML("primary")) );
    }

    @FXML
    public void clear() {
        //czy da sie jakos wyczyscic wcisniety tylko
    }
}