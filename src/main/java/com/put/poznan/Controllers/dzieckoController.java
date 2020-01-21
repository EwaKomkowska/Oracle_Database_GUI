package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
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
import java.util.ArrayList;

public class dzieckoController {
    private DataBase dataBase;

    @FXML
    private TextField imieField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private DatePicker dataDatePicker;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_posilkuBox;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;

    private int idx = 2;

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

        ObservableList<Long> listaPosilkow = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT p.idposilku FROM Posilek p");
        listaPosilkow.addAll(query.getResultList());
        id_posilkuBox.setItems(listaPosilkow);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT DZIECKO_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    public boolean dodawanie(Dziecko d) {
        boolean czyDodac = true;
        try {
            d.setImie(imieField.getText());
            d.setNazwisko(nazwiskoField.getText());
        } catch (Exception e) {
            czyDodac = false;
        }

        try {
            d.setDataurodzenia(Date.valueOf(dataDatePicker.getValue().toString()));
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędną datę urodzenia - sprawdź, czy jest w formacie YYYY-MM-DD");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            d.setIddziecka(Integer.parseInt(idField.getText()));
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
            d.setGrupaprzedszkolnaIdgrupy((Long) id_grupyBox.getValue());
        }

        if (id_posilkuBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś numeru posilku");
            alert.showAndWait();
            czyDodac = false;
        } else {
            d.setPosilekIdposilku((Long) id_posilkuBox.getValue());
        }
        return czyDodac;
    }

    @FXML
    public void add() {
        Dziecko d = new Dziecko();
        boolean czyDodac = dodawanie(d);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into DZIECKO(iddziecka, imie, nazwisko, dataurodzenia, grupaprzedszkolna_idgrupy, posilek_idposilku) values (?, ?, ?, ?, ?, ?)");
                stmt.setLong(1, d.getIddziecka());
                stmt.setString(2, d.getImie());
                stmt.setString(3, d.getNazwisko());
                stmt.setDate(4, d.getDataurodzenia());
                stmt.setLong(5, d.getGrupaprzedszkolnaIdgrupy());
                stmt.setLong(6, d.getPosilekIdposilku());

                stmt.executeQuery();
                stmt.close(); //TODO: DODAC DO RESZTY CLOSE!

                MainViewController.add(this.dataBase);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT DZIECKO_SEQ.nextval FROM dual");
                pstm.executeQuery();
                pstm.close();

            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nie udało się dodać dziecka");
            alert.setHeaderText(null);
            alert.showAndWait();
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
    public void modify(long id) throws SQLException, IOException {
        idField.setText(String.valueOf(id));
        addButton.setVisible(false);
        modifyButton.setVisible(true);
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from DZIECKO where IDDZIECKA = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        imieField.setText(rs.getString("imie"));
        nazwiskoField.setText(rs.getString("nazwisko"));
        dataDatePicker.setPromptText(String.valueOf(rs.getTime("data_urodzenia")));
        id_posilkuBox.setValue(rs.getLong("posilek_idposilku"));
        id_grupyBox.setValue(rs.getLong("grupaprzedszkolna_idgrupy"));
        pstm.close(); //TODO: CLOSE NA KONIEC!! tak jest ok
    }

    @FXML
    private void update() {
        Dziecko d = new Dziecko();
        boolean czyDodac = dodawanie(d);
        try {
            if (czyDodac) {

                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE DZIECKO SET imie = ?, nazwisko = ?, dataurodzenia = ?, grupaprzedszkolna_idgrupy = ?, posilek_idposilku = ? WHERE IDDZIECKA = ?");
                stmt.setString(1, d.getImie());
                stmt.setString(2, d.getNazwisko());
                stmt.setDate(3, d.getDataurodzenia());
                stmt.setLong(4, d.getGrupaprzedszkolnaIdgrupy());
                stmt.setLong(5, d.getPosilekIdposilku());
                stmt.setLong(6, d.getIddziecka());
                stmt.executeUpdate();

                stmt.close();

                MainViewController.add(this.dataBase);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clear() {
        //TODO: czy da sie jakos wyczyscic wcisniety tylko
    }
}

