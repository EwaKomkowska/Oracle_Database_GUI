package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Hospitacja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class hospitacjaController {

    private DataBase dataBase;

    private int idx = 10;

    @FXML
    private TextField idField;
    @FXML
    private DatePicker terminDatePicker;
    @FXML
    private ComboBox id_przedszkolankiBox;
    @FXML
    private ComboBox id_przedszkolanki2Box;


    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @FXML
    public void initialize() throws SQLException {
        ObservableList<Long> listaPrzedszkolanek = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idprac FROM Przedszkolanka p");
        listaPrzedszkolanek.addAll(query.getResultList());
        id_przedszkolankiBox.setItems(listaPrzedszkolanek);
        id_przedszkolanki2Box.setItems(listaPrzedszkolanek);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT HOSPITACJA_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
    }

    @FXML
    public void add() {
        //TODO: żeby przedszkolanka nie mogla nadzorowac samej siebie
        Hospitacja h = new Hospitacja();
        boolean czyDodac = true;

        try {
            h.setIdhospitacji(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            h.setTermin(Date.valueOf(terminDatePicker.getValue().toString()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę hospitacji - sprawdź, czy jest w formacie YYYY-MM-DD");
            alert.showAndWait();
            czyDodac = false;
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś nadzorowanego");
            alert.showAndWait();
            czyDodac = false;
        } else {
            h.setKtonadzorowany((long)id_przedszkolankiBox.getValue());
        }

        if (id_przedszkolanki2Box.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś nadzorujacego");
            alert.showAndWait();
            czyDodac = false;
        } else {
            h.setKtonadzoruje((long)id_przedszkolanki2Box.getValue());
        }

        try {
            if(czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into HOSPITACJA(idhospitacji, termin, ktonadzorowany, ktonadzoruje) values (?, ?, ?, ?)");
                stmt.setLong(1, h.getIdhospitacji());
                stmt.setDate(2, h.getTermin());
                stmt.setLong(3, h.getKtonadzorowany());
                stmt.setLong(4, h.getKtonadzoruje());

                stmt.executeQuery();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT HOSPITACJA_SEQ.nextval FROM dual");
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
        c.setCurrentTab(this.idx);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    public void clear() {
        //TODO: czy da sie jakos wyczyscic wcisniety tylko
    }
}
