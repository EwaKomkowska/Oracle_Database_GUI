package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Zajeciadodatkowe;
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

public class zajeciaDodatkoweController {

    private DataBase dataBase;

    private int idx = 4;

    @FXML
    private TextField idField;
    @FXML
    private TextField rodzajField;
    @FXML
    private DatePicker terminDatePicker;
    @FXML
    private TextField czasField;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_oplatyBox;
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

        ObservableList<Long> listaOplat = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT o.idoplaty FROM Oplata o");
        listaOplat.addAll(query.getResultList());
        id_oplatyBox.setItems(listaOplat);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT ZAJDOD_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    private boolean dodawanie (Zajeciadodatkowe zd) {
        boolean czyDodac = true;
        try {
            zd.setRodzaj(rodzajField.getText());
        } catch (Exception e) {
            czyDodac = false;
        }

        try {
            zd.setDataprowadzenia(Date.valueOf(terminDatePicker.getValue().toString()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę wydarzenia - sprawdź, czy jest w formacie DD.MM.RRRR");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            zd.setIdzajecia(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            zd.setCzastygodniowo((long) Integer.parseInt(czasField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędny czas tygodniowo - sprawdź, czy jest liczbą całkowitą dodatnią!");
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
            zd.setDlakogo((Long) id_grupyBox.getValue());
        }

        if (id_oplatyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id oplaty");
            alert.showAndWait();
            czyDodac = false;
        } else {
            zd.setOplaty((Long) id_oplatyBox.getValue());
        }
        return czyDodac;
    }

    @FXML
    public void add() {
        Zajeciadodatkowe zd = new Zajeciadodatkowe();
        boolean czyDodac = dodawanie(zd);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into ZAJECIADODATKOWE(idzajecia, rodzaj, dataprowadzenia, oplaty, czastygodniowo, dlakogo) values (?, ?, ?, ?, ?, ?)");
                stmt.setLong(1, zd.getIdzajecia());
                stmt.setString(2, zd.getRodzaj());
                stmt.setDate(3, zd.getDataprowadzenia());
                stmt.setLong(4, zd.getOplaty());
                stmt.setLong(5, zd.getCzastygodniowo());
                stmt.setLong(6, zd.getDlakogo());

                stmt.executeQuery();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT ZAJDOD_SEQ.nextval FROM dual");
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
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from ZAJECIADODATKOWE where IDZAJECIA = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        rodzajField.setText(rs.getString("rodzaj"));
        terminDatePicker.setValue(LocalDate.parse(String.valueOf(rs.getDate("DATAPROWADZENIA"))));
        id_oplatyBox.setValue(rs.getLong("oplaty"));
        czasField.setText(String.valueOf(rs.getLong("czastygodniowo")));
        id_grupyBox.setValue(rs.getLong("dlakogo"));
        pstm.close();
    }

    @FXML
    private void update() {
        Zajeciadodatkowe zd = new Zajeciadodatkowe();
        boolean czyDodac = dodawanie(zd);
        try {
            if (czyDodac) {

                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE ZAJECIADODATKOWE SET RODZAJ = ?, DATAPROWADZENIA = ?, OPLATY = ?, CZASTYGODNIOWO = ?, DLAKOGO = ? WHERE IDZAJECIA = ?");
                stmt.setLong(6, zd.getIdzajecia());
                stmt.setString(1, zd.getRodzaj());
                stmt.setDate(2, zd.getDataprowadzenia());
                stmt.setLong(3, zd.getOplaty());
                stmt.setLong(4, zd.getCzastygodniowo());
                stmt.setLong(5, zd.getDlakogo());
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