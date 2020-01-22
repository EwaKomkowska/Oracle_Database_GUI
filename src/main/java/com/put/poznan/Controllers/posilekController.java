package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Pomocdydaktyczna;
import com.put.poznan.SchemaObjects.Posilek;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class posilekController {

    private DataBase dataBase;

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    private int idx = 5;

    @FXML
    private TextField nazwaField;
    @FXML
    private TextField idField;
    @FXML
    private TextField dietaField;
    @FXML
    private TextField godzField;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;

    @FXML
    public void initialize()throws SQLException {
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT POSILEK_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    private boolean dodawanie (Posilek p) {
        boolean czyDodac = true;

        try {
            p.setGodzrozwozenia(Time.valueOf(godzField.getText()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną godzinę rozwożenia - sprawdź, czy jest w formacie HH:MM:SS");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            p.setNazwa(nazwaField.getText());
            p.setDieta(dietaField.getText());
        }catch (Exception e){
            czyDodac = false;
        }

        try {
            p.setIdposilku(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }
        return czyDodac;
    }

    @FXML
    public void add() {
        Posilek p = new Posilek();
        boolean czyDodac = dodawanie(p);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into POSILEK (IDPOSILKU, NAZWA, GODZROZWOZENIA, DIETA) values (?, ?, ?, ?)");
                stmt.setLong(1, p.getIdposilku());
                stmt.setString(2, p.getNazwa());
                stmt.setTime(3, p.getGodzrozwozenia());
                stmt.setString(4, p.getDieta());
                stmt.executeQuery();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT POSILEK_SEQ.nextval FROM dual");
                pstm.executeQuery();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modify(long id) throws SQLException, IOException {
        idField.setText(String.valueOf(id));
        addButton.setVisible(false);
        modifyButton.setVisible(true);
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from POSILEK where IDPOSILKU = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        nazwaField.setText(rs.getString("nazwa"));
        godzField.setText(String.valueOf(rs.getTime("godzrozwozenia")));
        dietaField.setText(rs.getString("dieta"));
        pstm.close();
    }

    @FXML
    private void update() {
        Posilek p = new Posilek();
        boolean czyDodac = dodawanie(p);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE POSILEK SET NAZWA = ?, GODZROZWOZENIA = ?, DIETA = ? WHERE IDPOSILKU = ?");
                stmt.setLong(4, p.getIdposilku());
                stmt.setString(1, p.getNazwa());
                stmt.setTime(2, p.getGodzrozwozenia());
                stmt.setString(3, p.getDieta());

                stmt.executeUpdate();
                stmt.close();

                MainViewController.add(this.dataBase, idx);
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
        c.setCurrentTab(this.idx);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }
}
