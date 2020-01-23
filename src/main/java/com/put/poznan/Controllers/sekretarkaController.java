package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Przedszkolanka;
import com.put.poznan.SchemaObjects.Sekretarka;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

import static java.lang.Long.parseLong;

public class sekretarkaController {


    private DataBase dataBase;

    private int idx = 1;

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
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;


    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @FXML
    public void initialize() throws SQLException {
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT SEKRETARKA_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    private boolean dodawanie(Sekretarka s) {
        boolean czyDodac = true;
        try {
            s.setIdprac(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
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
            czyDodac = false;
        }

        try {
            if (godzRozField.getText().isEmpty()){
                s.setGodzrozpoczeciapracy(null);
            } else {
                s.setGodzrozpoczeciapracy(Time.valueOf(godzRozField.getText()));
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną godzine rozpoczecia pracy - sprawdź, czy jest w formacie HH:MM:SS");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            if (godzRozField.getText().isEmpty()){
                s.setGodzzakonczeniapracy(null);
            } else {
                s.setGodzzakonczeniapracy(Time.valueOf(godzRozField.getText()));
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną godzine zakonczenia pracy - sprawdź, czy jest w formacie HH:MM:SS");
            alert.showAndWait();
            czyDodac = false;
        }
    return czyDodac;
    }

    @FXML
    public void add() {
        Sekretarka s = new Sekretarka();
        boolean czyDodac = dodawanie(s);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into SEKRETARKA(idprac, godzrozpoczeciapracy, godzzakonczeniapracy, imie, nazwisko, kwalifikacje, placa) values (?, ?, ?, ?, ?, ?, ?)");
                stmt.setLong(1, s.getIdprac());
                stmt.setTime(2, s.getGodzrozpoczeciapracy());
                stmt.setTime(3, s.getGodzzakonczeniapracy());
                stmt.setString(4, s.getImie());
                stmt.setString(5, s.getNazwisko());
                stmt.setString(6, s.getKwalifikacje());
                stmt.setLong(7, s.getPlaca());
                stmt.executeQuery();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT SEKRETARKA_SEQ.nextval FROM dual");
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
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from SEKRETARKA where IDPRAC = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        imieField.setText(rs.getString("imie"));
        nazwiskoField.setText(rs.getString("nazwisko"));
        godzRozField.setText(String.valueOf(rs.getTime("godzrozpoczeciapracy")));
        godzZakField.setText(String.valueOf(rs.getTime("godzzakonczeniapracy")));
        kwalifikacjeField.setText(rs.getString("kwalifikacje"));
        placaField.setText(String.valueOf(rs.getLong("placa")));
        pstm.close();
    }

    @FXML
    private void update() {
        Sekretarka s = new Sekretarka();
        boolean czyDodac = dodawanie(s);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE SEKRETARKA SET GODZROZPOCZECIAPRACY = ?, GODZZAKONCZENIAPRACY = ?, IMIE = ?, NAZWISKO = ?, KWALIFIKACJE = ?, PLACA = ? WHERE IDPRAC = ?");
                stmt.setLong(7, s.getIdprac());
                stmt.setTime(1, s.getGodzrozpoczeciapracy());
                stmt.setTime(2, s.getGodzzakonczeniapracy());
                stmt.setString(3, s.getImie());
                stmt.setString(4, s.getNazwisko());
                stmt.setString(5, s.getKwalifikacje());
                stmt.setLong(6, s.getPlaca());

                stmt.executeUpdate();
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