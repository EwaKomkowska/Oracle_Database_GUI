package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Przedszkolanka;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class PrzedszkolankaController {

    private DataBase dataBase;
    private Przedszkolanka przedszkolanka;

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        /*
        int id = 0;
        String imie;
        String nazwisko;
        String kwalifikacje;
        float placa = 0;
        //TODO: tutaj zrobić zczytywanie z okienka, więc bez parametrów
        try {
            //this.id = new SimpleIntegerProperty(Integer.parseInt(idField.getText()));
            id = Integer.parseInt(idField.getText());
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
        /*
        imie = imieField.getText();
        nazwisko = nazwiskoField.getText();
        kwalifikacje =  kwalifikacjeField.getText();
        try {

            //this.placa = new SimpleIntegerProperty(Integer.parseInt(placaField.getText()));
            placa = Float.parseFloat(placaField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną płacę - sprawdź, czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }
        Przedszkolanka przedszkolanka = new Przedszkolanka(id,imie,nazwisko,kwalifikacje,placa);*/
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