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
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty kwalifikacje = new SimpleStringProperty();
    private IntegerProperty placa = new SimpleIntegerProperty();
    private IntegerProperty idGrupy = new SimpleIntegerProperty();
    private IntegerProperty idHospitacji = new SimpleIntegerProperty();

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
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getKwalifikacje() {
        return kwalifikacje.get();
    }

    public StringProperty kwalifikacjeProperty() {
        return kwalifikacje;
    }

    public void setKwalifikacje(String kwalifikacje) {
        this.kwalifikacje.set(kwalifikacje);
    }

    public int getPlaca() {
        return placa.get();
    }

    public IntegerProperty placaProperty() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa.set(placa);
    }

    public int getIdGrupy() {
        return idGrupy.get();
    }

    public IntegerProperty idGrupyProperty() {
        return idGrupy;
    }

    public void setIdGrupy(int idGrupy) {
        this.idGrupy.set(idGrupy);
    }

    public int getIdHospitacji() {
        return idHospitacji.get();
    }

    public IntegerProperty idHospitacjiProperty() {
        return idHospitacji;
    }

    public void setIdHospitacji(int idHospitacji) {
        this.idHospitacji.set(idHospitacji);
    }

    public void add () {
        //TODO: tutaj zrobić zczytywanie z okienka, więc bez parametrów
        try {
            this.id = new SimpleIntegerProperty(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }
        this.imie = new SimpleStringProperty(imieField.getText());
        this.nazwisko = new SimpleStringProperty(nazwiskoField.getText());
        this.kwalifikacje =  new SimpleStringProperty(kwalifikacjeField.getText());
        try {
            this.placa = new SimpleIntegerProperty(Integer.parseInt(placaField.getText()));
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