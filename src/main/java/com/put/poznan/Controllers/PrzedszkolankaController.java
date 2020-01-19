package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Przedszkolanka;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Long.parseLong;

public class PrzedszkolankaController {

    private DataBase dataBase;

    @FXML
    private TextField imieField;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private TextField kwalifikacjeField;
    @FXML
    private TextField idField;
    @FXML
    private TextField placaField;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_hospitacjiBox;


    @FXML
    public void initialize() throws SQLException {
        ObservableList<Long> listaGrup = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idgrupy FROM Grupaprzedszkolna p");
        listaGrup.addAll(query.getResultList());
        id_grupyBox.setItems(listaGrup);

        ObservableList<Long> listaHospitacji = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT h.idhospitacji FROM Hospitacja h");
        listaHospitacji.addAll(query.getResultList());
        id_hospitacjiBox.setItems(listaHospitacji);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT PRZEDSZKOLANKA_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
    }


    public void add () {
        boolean czyDodawac = true;
        Przedszkolanka p = new Przedszkolanka();
        try {
            p.setIdprac(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodawac = false;
        }

       if (id_grupyBox.getSelectionModel().getSelectedIndex() == -1) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Nie wybrałeś numeru grupy");
           alert.showAndWait();
           czyDodawac = false;
       } else {
           p.setNazwagrupy((Long) id_grupyBox.getValue());
       }

       try {
           p.setImie(imieField.getText());
           p.setNazwisko(nazwiskoField.getText());
           p.setKwalifikacje(kwalifikacjeField.getText());
       } catch (Exception e) {
           czyDodawac = false;
       }

        try {
            p.setPlaca(parseLong(placaField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną płacę - sprawdz, czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodawac = false;
        }

        try {
            if (czyDodawac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into Przedszkolanka (idprac, imie, nazwisko, kwalifikacje, placa, nazwagrupy, HOS_IDHOS) values (?, ?, ?, ?, ?, ?, ?)");
                stmt.setLong(1, p.getIdprac());
                stmt.setString(2, p.getImie());
                stmt.setString(3, p.getNazwisko());
                stmt.setString(4, p.getKwalifikacje());
                stmt.setLong(5, p.getPlaca());
                stmt.setLong(6, p.getNazwagrupy());
                stmt.setLong(7, 1);     //p.gethospitacja???
                //TODO: jeszcze z tym id_hospitacji trzeba pomyslec jak to ma byc dokładnie
                //ResultSet rs = //trzeba by i tak petla sprawdzac ile jest
                stmt.executeQuery();

                MainViewController.add(this.dataBase);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT PRZEDSZKOLANKA_SEQ.nextval FROM dual");
                pstm.executeQuery();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
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
    public void clearImie () {
        imieField.setText("");
    }

    @FXML
    public void clearNazwisko () {
        nazwiskoField.setText("");
    }

    @FXML
    public void clearKwalifikacje () {
        kwalifikacjeField.setText("");
    }

    @FXML
    public void clearPlaca () {
        placaField.setText("");
    }
}