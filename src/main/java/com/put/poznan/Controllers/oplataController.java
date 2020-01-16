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
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void initialize() throws SQLException {
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT OPLATA_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
    }


    @FXML
    public void add() {
        //TODO: czy na pewno nie jest potrzebne wiecej informacji (w okienku)
        boolean czyDodac = true;
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
            czyDodac = false;
        }

        try {
            o.setWielkosc((long) Integer.parseInt(wielkoscField.getText()));
        } catch (IllegalArgumentException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną wielkość opłaty - sprawdź czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }


        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into OPLATA(idoplaty, wielkosc, przedmiotoplaty, czestosc, ZAJECIADODATKOWE_IDZAJECIA) values (?, ?, ?, ?, ?)");
                stmt.setLong(1, o.getIdoplaty());
                stmt.setLong(2, o.getWielkosc());
                stmt.setString(3, o.getPrzedmiotoplaty());
                stmt.setString(4, o.getCzestosc());
                stmt.setLong(5, 1);
                //TODO: cos nie do konca tu z iloscia dziwnych polaczen - idzajeciaDodatkowe nie zczytywane
                stmt.executeQuery();

                MainViewController.add(this.dataBase);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT OPLATA_SEQ.nextval FROM dual");
                pstm.executeQuery();
            }
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
