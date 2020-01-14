package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Oplata;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;

public class oplataController {
    private DataBase dataBase;


    @FXML
    private TextField wielkoscField;
    @FXML
    private TextField idField;
    @FXML
    private TextField czestoscField;
    @FXML
    private TextField przedmiotField;



    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @FXML
    public void add() {
        //TODO: czy na pewno nie jest potrzebne wiecej informacji (w okienku)
        Oplata o = new Oplata();

        o.setCzestosc(czestoscField.getText());
        o.setPrzedmiotoplaty(przedmiotField.getText());

        try {
            o.setIdoplaty(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        try {
            //TODO: czy oplata jest >= 0
            if (Integer.parseInt(wielkoscField.getText()) >= 0)
                o.setWielkosc((long) Integer.parseInt(wielkoscField.getText()));
        } catch (Exception e) {         //IllegalArgumentException
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną wielkość opłaty - sprawdź czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }


        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into OPLATA(idoplaty, wielkosc, przedmiotoplaty, czestosc, ZAJECIADODATKOWE_IDZAJECIA) values (?, ?, ?, ?, ?)");
            stmt.setLong(1, o.getIdoplaty());
            stmt.setLong(2, o.getWielkosc());
            stmt.setString(3, o.getPrzedmiotoplaty());
            stmt.setString(4, o.getCzestosc());
            stmt.setLong(5, 1);
            //TODO: cos nie do konca tu z iloscia dziwnych polaczen - idzajeciaDodatkowe nie zczytywane
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
