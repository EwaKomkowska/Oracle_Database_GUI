package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Przedszkolanka;
import com.put.poznan.SchemaObjects.Sekretarka;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;

import static java.lang.Long.parseLong;

public class sekretarkaController {
    private DataBase dataBase;
    //TODO: more fields in sekretarka.fxml!!!
    @FXML
    private TextField idField;
    @FXML
    private TextField godzRozField;
    @FXML
    private TextField godzZakField;
    @FXML
    private TextField imieField;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private TextField kwalifikacjeField;
    @FXML
    private TextField placaField;


    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @FXML
    public void add() {
        Sekretarka s = new Sekretarka();

        try {
            s.setIdprac(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        s.setImie(imieField.getText());
        s.setNazwisko(nazwiskoField.getText());
        s.setKwalifikacje(kwalifikacjeField.getText());

        try {
            s.setPlaca(parseLong(placaField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną płacę - sprawdz, czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into SEKRETARKA(idprac, godzrozpoczeciapracy, godzzakonczeniapracy, imie, nazwisko, kwalifikacje, placa) values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setLong(1, s.getIdprac());
            stmt.setTime(2,s.getGodzrozpoczeciapracy());
            stmt.setTime(3, s.getGodzzakonczeniapracy());
            stmt.setString(4, s.getImie());
            stmt.setString(5, s.getNazwisko());
            stmt.setString(6, s.getKwalifikacje());
            stmt.setLong(7, s.getPlaca());
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