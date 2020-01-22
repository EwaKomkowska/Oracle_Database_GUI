package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Festyn;
import com.put.poznan.SchemaObjects.Grupaprzedszkolna;
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
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class grupaPrzedszkolnaController {
    private DataBase dataBase;

    @FXML
    private TextField idField;
    @FXML
    private TextField salaField;
    @FXML
    private TextField nazwaField;
    @FXML
    private TextField wiekField;
    @FXML
    private ComboBox id_przedszkolankiBox;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;

    private int idx = 3;

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

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT GRUPA_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }

    private boolean dodawanie (Grupaprzedszkolna g) {
        boolean czyDodac = true;
        try {
            g.setNazwa(nazwaField.getText());
        } catch (Exception e) {
            czyDodac = false;
        }

        try {
            g.setIdgrupy(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            g.setWiekdzieci((long) Integer.parseInt(wiekField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś nieodpowiedni wiek dzieci!");
            alert.showAndWait();
            czyDodac = false;
        }

        try {
            g.setSala((long) Integer.parseInt(salaField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Sala musi być liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id przedszkolanki");
            alert.showAndWait();
            czyDodac = false;
        } else {
            g.setIdprac((Long) id_przedszkolankiBox.getValue());
        }
        return czyDodac;
    }


    @FXML
    public void add() {
        Grupaprzedszkolna g = new Grupaprzedszkolna();
        boolean czyDodac = dodawanie(g);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into GRUPAPRZEDSZKOLNA(idgrupy, sala, nazwa, wiekdzieci, idprac) values (?, ?, ?, ?, ?)");
                stmt.setLong(1, g.getIdgrupy());
                stmt.setLong(2, g.getSala());
                stmt.setString(3, g.getNazwa());
                stmt.setLong(4, g.getWiekdzieci());
                stmt.setLong(5, g.getIdprac());

                stmt.executeQuery();
                stmt.close();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT GRUPA_SEQ.nextval FROM dual");
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
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from GRUPAPRZEDSZKOLNA where IDGRUPY = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        salaField.setText(rs.getString("sala"));
        nazwaField.setText(rs.getString("nazwa"));
        wiekField.setText(String.valueOf(rs.getLong("wiekdzieci")));
        id_przedszkolankiBox.setValue(rs.getLong("idprac"));
        pstm.close();
    }

    @FXML
    private void update() {
        Grupaprzedszkolna g = new Grupaprzedszkolna();
        boolean czyDodac = dodawanie(g);
        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE GRUPAPRZEDSZKOLNA SET SALA = ?, NAZWA = ?, WIEKDZIECI = ?, IDPRAC = ? WHERE IDGRUPY = ?");
                stmt.setLong(5, g.getIdgrupy());
                stmt.setLong(1, g.getSala());
                stmt.setString(2, g.getNazwa());
                stmt.setLong(3, g.getWiekdzieci());
                stmt.setLong(4, g.getIdprac());

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
