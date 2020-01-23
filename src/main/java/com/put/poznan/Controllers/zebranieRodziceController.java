package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Zebraniezrodzicami;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import org.hibernate.boot.model.relational.Database;

import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class zebranieRodziceController {

    private DataBase dataBase;

    private int idx = 9;

    @FXML
    private TextField idField;
    @FXML
    private DatePicker terminDatePicker;
    @FXML
    private TextField salaField;
    @FXML
    private ComboBox obowiazkoweBox;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_przedszkolankiBox;
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
        ObservableList<Long> listaGrup = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idgrupy FROM Grupaprzedszkolna p");
        listaGrup.addAll(query.getResultList());
        id_grupyBox.setItems(listaGrup);

        ObservableList<Long> listaPrzedszkolanek = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT p.idprac FROM Przedszkolanka p");
        listaPrzedszkolanek.addAll(query.getResultList());
        id_przedszkolankiBox.setItems(listaPrzedszkolanek);

        ObservableList<Boolean> obowiazek = FXCollections.observableList(new ArrayList<>());
        obowiazek.addAll(true, false);
        obowiazkoweBox.setItems(obowiazek);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT ZEBRANIE_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    private boolean dodawanie(Zebraniezrodzicami zr) {
        boolean czyDodac = true;

        try {
            zr.setData(Date.valueOf(terminDatePicker.getValue().toString()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę zebrania - sprawdź, czy jest w formacie DD.MM.RRRR");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            zr.setIdzebrania(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            zr.setMiejsca((long) Integer.parseInt(salaField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną salę - sprawdź, czy jest liczbą całkowitą dodatnią!");
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
            zr.setGrupa((Long) id_grupyBox.getValue());
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id przedszkolanki");
            alert.showAndWait();
            czyDodac = false;
        } else {
            zr.setProwadzacyzebranie((Long) id_przedszkolankiBox.getValue());
        }

        if (obowiazkoweBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś wariantu obowiązkowości");
            alert.showAndWait();
            czyDodac = false;
        } else {
            zr.setCzyobowiazkowe(obowiazkoweBox.getValue().toString());
        }

        try {
            PreparedStatement stm = DataBase.getConnection().prepareStatement("SELECT HOS_IDHOS from PRZEDSZKOLANKA where IDPRAC = ?");
            stm.setLong(1, zr.getProwadzacyzebranie());
            ResultSet rs = stm.executeQuery();
            rs.next();
            zr.setPrzedszkolankaIdhospitacji(rs.getLong("hos_idhos"));
        } catch (Exception e) {
            e.printStackTrace();
            czyDodac = false;
        }
        return czyDodac;
    }

    @FXML
    public void add()  {
        Zebraniezrodzicami zr = new Zebraniezrodzicami();
        boolean czyDodac = dodawanie(zr);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into ZEBRANIEZRODZICAMI(idzebrania, data, grupa, miejsca, prowadzacyzebranie, czyobowiazkowe, PRZEDSZKOLANKA_IDHOSPITACJI) values (?, ?, ?, ?, ?, ?, ?)");
                stmt.setLong(1, zr.getIdzebrania());
                stmt.setDate(2, zr.getData());
                stmt.setLong(3, zr.getGrupa());
                stmt.setLong(4, zr.getMiejsca());
                stmt.setLong(5, zr.getProwadzacyzebranie());
                stmt.setString(6, zr.getCzyobowiazkowe());
                stmt.setLong(7, zr.getPrzedszkolankaIdhospitacji());

                stmt.executeQuery();
                stmt.close();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT ZEBRANIE_SEQ.nextval FROM dual");
                pstm.executeQuery();
                pstm.close();
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
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from ZEBRANIEZRODZICAMI where IDZEBRANIA = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        terminDatePicker.setValue(LocalDate.parse(String.valueOf(rs.getDate("data"))));
        id_grupyBox.setValue(rs.getLong("grupa"));
        salaField.setText(String.valueOf(rs.getLong("miejsca")));
        id_przedszkolankiBox.setValue(rs.getLong("prowadzacyzebranie"));
        obowiazkoweBox.setValue(rs.getString("czyobowiazkowe"));
        pstm.close();
    }

    @FXML
    private void update() {
        Zebraniezrodzicami zr = new Zebraniezrodzicami();
        boolean czyDodac = dodawanie(zr);
        try {
            if (czyDodac) {

                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE ZEBRANIEZRODZICAMI SET DATA = ?, GRUPA = ?, MIEJSCA = ?, PROWADZACYZEBRANIE = ?, CZYOBOWIAZKOWE = ?, PRZEDSZKOLANKA_IDHOSPITACJI =? WHERE IDZEBRANIA = ?");
                stmt.setLong(7, zr.getIdzebrania());
                stmt.setDate(1, zr.getData());
                stmt.setLong(2, zr.getGrupa());
                stmt.setLong(3, zr.getMiejsca());
                stmt.setLong(4, zr.getProwadzacyzebranie());
                stmt.setString(5, zr.getCzyobowiazkowe());
                stmt.setLong(6, zr.getPrzedszkolankaIdhospitacji());
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