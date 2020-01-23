package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Oplata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private ComboBox id_zajeciaBox;

    private int idx = 7;

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @FXML
    public void initialize() throws SQLException {
        ObservableList<Long> listaZajec = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT z.idzajecia FROM Zajeciadodatkowe z");
        listaZajec.addAll(query.getResultList());
        id_zajeciaBox.setItems(listaZajec);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT OPLATA_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    private boolean dodawanie (Oplata o) {
        boolean czyDodac = true;

        try {
            o.setCzestosc(czestoscField.getText());
            o.setPrzedmiotoplaty(przedmiotField.getText());
        }catch (Exception e) {
            czyDodac = false;
        }

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

        if (id_zajeciaBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś numeru zajec");
            alert.showAndWait();
            czyDodac = false;
        } else {
            o.setIdzajecia((Long) id_zajeciaBox.getValue());
        }


        return czyDodac;
    }


    @FXML
    public void add() {
        Oplata o = new Oplata();
        boolean czyDodac = dodawanie(o);

        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into OPLATA(idoplaty, wielkosc, przedmiotoplaty, czestosc, ZAJECIADODATKOWE_IDZAJECIA) values (?, ?, ?, ?, ?)");
                stmt.setLong(1, o.getIdoplaty());
                stmt.setLong(2, o.getWielkosc());
                stmt.setString(3, o.getPrzedmiotoplaty());
                stmt.setString(4, o.getCzestosc());
                stmt.setLong(5, o.getIdzajecia());
                //TODO: cos nie do konca tu z iloscia dziwnych polaczen - idzajeciaDodatkowe nie zczytywane
                stmt.executeQuery();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT OPLATA_SEQ.nextval FROM dual");
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
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from OPLATA where IDOPLATY = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        wielkoscField.setText(String.valueOf(rs.getLong("wielkosc")));
        przedmiotField.setText(rs.getString("przedmiotoplaty"));
        czestoscField.setText(rs.getString("czestosc"));
        pstm.close();
    }

    @FXML
    private void update() {
        Oplata o = new Oplata();
        boolean czyDodac = dodawanie(o);
        try {
            if (czyDodac) {

                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE OPLATA SET WIELKOSC = ?, PRZEDMIOTOPLATY = ?, CZESTOSC = ?, ZAJECIADODATKOWE_IDZAJECIA = ? WHERE IDOPLATY = ?");
                stmt.setLong(5, o.getIdoplaty());
                stmt.setLong(1, o.getWielkosc());
                stmt.setString(2, o.getPrzedmiotoplaty());
                stmt.setString(3, o.getCzestosc());
                stmt.setLong(4, 1);
                //TODO: cos nie do konca tu z iloscia dziwnych polaczen - idzajeciaDodatkowe nie zczytywane
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
