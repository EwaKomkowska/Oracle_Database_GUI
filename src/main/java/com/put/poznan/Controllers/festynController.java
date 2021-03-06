package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Festyn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class festynController {
    private DataBase dataBase;

    @FXML
    private TextField idField;
    @FXML
    private TextField hasloField;
    @FXML
    private DatePicker terminDatePicker;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_przedszkolankiBox;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;

    private int idx = 8;

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @FXML
    public void initialize() throws SQLException {
        ObservableList<Long> listaGrup = FXCollections.observableList(new ArrayList<>());
        Query query = App.getEm().createQuery("SELECT DISTINCT p.idgrupy FROM Grupaprzedszkolna p");
        listaGrup.addAll(query.getResultList());
        id_grupyBox.setItems(listaGrup);

        ObservableList<Long> listaPrzedszkolanek = FXCollections.observableList(new ArrayList<>());
        query = App.getEm().createQuery("SELECT DISTINCT p.idprac FROM Przedszkolanka p");
        listaPrzedszkolanek.addAll(query.getResultList());
        id_przedszkolankiBox.setItems(listaPrzedszkolanek);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT FESTYN_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    public boolean dodawanie(Festyn f) {
        boolean czyDodac = true;
        try {
            f.setHaslo(hasloField.getText());
        } catch (Exception e) {
            czyDodac = false;
        }

        try {
            f.setTerminwydarzena(Date.valueOf(terminDatePicker.getValue().toString()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę wydarzenia - sprawdź, czy jest w formacie DD.MM.RRRR");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            f.setIdfestynu(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        if (id_grupyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś numeru grupy");
            alert.showAndWait();
            czyDodac = false;
        } else {
            f.setGrupawystepujaca((Long) id_grupyBox.getValue());
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id przedszkolanki");
            alert.showAndWait();
            czyDodac = false;
        } else {
            f.setOsobaodpowiedzialna((Long) id_przedszkolankiBox.getValue());
        }
        return czyDodac;
    }


    @FXML
    public void add() {
        Festyn f = new Festyn();
        boolean czyDodac = dodawanie(f);

        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into FESTYN(idfestynu, grupawystepujaca, osobaodpowiedzialna, terminwydarzena, haslo) values (?, ?, ?, ?, ?)");
                stmt.setLong(1, f.getIdfestynu());
                stmt.setLong(2, f.getGrupawystepujaca());
                stmt.setLong(3, f.getOsobaodpowiedzialna());
                stmt.setDate(4, f.getTerminwydarzena());
                stmt.setString(5, f.getHaslo());

                stmt.executeQuery();
                stmt.close();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT FESTYN_SEQ.nextval FROM dual");
                pstm.executeQuery();
                pstm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void modify(long id) throws SQLException, IOException {
        idField.setText(String.valueOf(id));
        addButton.setVisible(false);
        modifyButton.setVisible(true);
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from FESTYN where IDFESTYNU = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        hasloField.setText(rs.getString("haslo"));
        terminDatePicker.setValue(LocalDate.parse(String.valueOf(rs.getDate("terminwydarzena"))));
        id_przedszkolankiBox.setValue(rs.getLong(3));
        id_grupyBox.setValue(rs.getLong(2));
        pstm.close();
    }


    @FXML
    private void update() {
        Festyn f = new Festyn();
        boolean czyDodac = dodawanie(f);

        try {
            if (czyDodac) {

                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE FESTYN SET grupawystepujaca = ?, osobaodpowiedzialna = ?, terminwydarzena = ?, haslo = ? WHERE IDFESTYNU = ?");
                stmt.setLong(1, f.getGrupawystepujaca());
                stmt.setLong(2, f.getOsobaodpowiedzialna());
                stmt.setDate(3, f.getTerminwydarzena());
                stmt.setString(4, f.getHaslo());
                stmt.setLong(5, f.getIdfestynu());

                stmt.executeQuery();
                stmt.close();

                MainViewController.add(this.dataBase, idx);
            }
        } catch (Exception e) {
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
