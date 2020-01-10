package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Przedszkolanka {
    private int id; //IntegerProperty id = new SimpleIntegerProperty();
    private String imie; //StringProperty imie = new SimpleStringProperty();
    private String nazwisko; //StringProperty nazwisko = new SimpleStringProperty();
    private String kwalifikacje; //StringProperty kwalifikacje = new SimpleStringProperty();
    private int placa; //IntegerProperty placa = new SimpleIntegerProperty();
    private int idGrupy; //IntegerProperty idGrupy = new SimpleIntegerProperty();
    private int idHospitacji; //IntegerProperty idHospitacji = new SimpleIntegerProperty();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getKwalifikacje() {
        return kwalifikacje;
    }

    public void setKwalifikacje(String kwalifikacje) {
        this.kwalifikacje = kwalifikacje;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public int getIdGrupy() {
        return idGrupy;
    }

    public void setIdGrupy(int idGrupy) {
        this.idGrupy = idGrupy;
    }

    public int getIdHospitacji() {
        return idHospitacji;
    }

    public void setIdHospitacji(int idHospitacji) {
        this.idHospitacji = idHospitacji;
    }

    public void add () {
        //TODO: tutaj zrobić zczytywanie z okienka, więc bez parametrów
        try {
            //this.id = new SimpleIntegerProperty(Integer.parseInt(idField.getText()));
            this.id = Integer.parseInt(idField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }
        /*this.imie = new SimpleStringProperty(imieField.getText());
        this.nazwisko = new SimpleStringProperty(nazwiskoField.getText());
        this.kwalifikacje =  new SimpleStringProperty(kwalifikacjeField.getText());
        */
        this.imie = imieField.getText();
        this.nazwisko = nazwiskoField.getText();
        this.kwalifikacje =  kwalifikacjeField.getText();
        try {

            //this.placa = new SimpleIntegerProperty(Integer.parseInt(placaField.getText()));
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
        //TODO: czy da sie jakos wyczyscic wcisniety tylko
    }
}