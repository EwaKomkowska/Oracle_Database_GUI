package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Posilek;
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
import java.sql.Time;

public class posilekController {

    private DataBase dataBase;

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @FXML
    private TextField nazwaField;
    @FXML
    private TextField idField;
    @FXML
    private TextField dietaField;
    @FXML
    private TextField godzField;

    @FXML
    public void initialize()throws SQLException {
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT POSILEK_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
    }

    @FXML
    public void add() {
        Posilek p = new Posilek();
        boolean czyDodac = true;
        p.setDieta(dietaField.getText());
        try {
            p.setGodzrozwozenia(Time.valueOf(godzField.getText()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną godzinę rozwożenia - sprawdź, czy jest w formacie...");
            alert.showAndWait();
            czyDodac = false;
        }

        p.setNazwa(nazwaField.getText());

        try {
            p.setIdposilku(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into POSILEK (IDPOSILKU, NAZWA, GODZROZWOZENIA, DIETA) values (?, ?, ?, ?)");
                stmt.setLong(1, p.getIdposilku());
                stmt.setString(2, p.getNazwa());
                stmt.setTime(3, p.getGodzrozwozenia());
                stmt.setString(4, p.getDieta());
                stmt.executeQuery();

                MainViewController.add(this.dataBase);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT POSILEK_SEQ.nextval FROM dual");
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
    public void clearNazwa() {
        nazwaField.setText("");
    }

    @FXML
    public void clearId() {
        idField.setText("");
    }

    @FXML
    public void clearDieta() {
        dietaField.setText("");
    }

    @FXML
    public void clearGodz() {
        godzField.setText("");
    }
}
