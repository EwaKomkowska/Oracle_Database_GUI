package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Przedszkolanka;
import com.put.poznan.SchemaObjects.Zebraniezrodzicami;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class PrzedszkolankaController {

    private DataBase dataBase;
    private Przedszkolanka przedszkolanka;

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
    public void initialize() {
        ObservableList<Long> listaGrup = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idgrupy FROM Grupaprzedszkolna p");
        listaGrup.addAll(query.getResultList());
        id_grupyBox.setItems(listaGrup);

        ObservableList<Long> listaHospitacji = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT h.idhospitacji FROM Hospitacja h");
        listaHospitacji.addAll(query.getResultList());
        id_hospitacjiBox.setItems(listaHospitacji);
    }


    public void add () {
       Przedszkolanka p = new Przedszkolanka();
        //TODO: tutaj zrobić zczytywanie z okienka, więc bez parametrów
        try {
            p.setIdprac(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

       if (id_grupyBox.getSelectionModel().getSelectedIndex() == -1) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Nie wybrałeś numeru grupy");
           alert.showAndWait();
       } else {
           p.setNazwagrupy((Long) id_grupyBox.getValue());
       }

        p.setImie(imieField.getText());
        p.setNazwisko(nazwiskoField.getText());
        p.setKwalifikacje(kwalifikacjeField.getText());

        try {
            p.setPlaca(parseLong(placaField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną płacę - sprawdz, czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
        }

        try {
            //TODO: jesli jakis blad zlapany to nie wykonywac tej czesci
            PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into Przedszkolanka (idprac, imie, nazwisko, kwalifikacje, placa, nazwagrupy, HOS_IDHOS) values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setLong(1, p.getIdprac());
            stmt.setString(2,p.getImie());
            stmt.setString(3, p.getNazwisko());
            stmt.setString(4, p.getKwalifikacje());
            stmt.setLong(5, p.getPlaca());
            stmt.setLong(6, p.getNazwagrupy());
            stmt.setLong(7, 1);     //p.gethospitacja???
            //TODO: jeszcze z tym id_hospitacji trzeba pomyslec jak to ma byc dokładnie
            //ResultSet rs = //trzeba by i tak petla sprawdzac ile jest
            stmt.executeQuery();

           MainViewController.add(this.dataBase);
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
    public void clearId() {
        idField.setText("");
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