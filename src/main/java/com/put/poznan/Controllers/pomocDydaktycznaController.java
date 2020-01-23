package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.Dziecko;
import com.put.poznan.SchemaObjects.Oplata;
import com.put.poznan.SchemaObjects.Pomocdydaktyczna;
import com.put.poznan.SchemaObjects.PomocdydaktycznaPK;
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
import java.sql.Time;
import java.util.ArrayList;

public class pomocDydaktycznaController {
    private DataBase dataBase;

    @FXML
    private TextField idField;
    @FXML
    private TextField rodzajField;
    @FXML
    private ComboBox id_grupyBox;
    @FXML
    private ComboBox id_oplatyBox;
    @FXML
    private ComboBox id_przedszkolankiBox;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;

    private int idx = 6;

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    @FXML
    private void initialize() throws SQLException {
        ObservableList<Long> listaGrup = FXCollections.observableList(new ArrayList<>());
        Query query=App.getEm().createQuery("SELECT DISTINCT p.idgrupy FROM Grupaprzedszkolna p");
        listaGrup.addAll(query.getResultList());
        id_grupyBox.setItems(listaGrup);

        ObservableList<Long> listaPrzedszkolanek = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT p.idprac FROM Przedszkolanka p");
        listaPrzedszkolanek.addAll(query.getResultList());
        id_przedszkolankiBox.setItems(listaPrzedszkolanek);

        ObservableList<Long> listaOplat = FXCollections.observableList(new ArrayList<>());
        query=App.getEm().createQuery("SELECT DISTINCT o.idoplaty FROM Oplata o");
        listaOplat.addAll(query.getResultList());
        id_oplatyBox.setItems(listaOplat);

        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT POMOCDYD_SEQ.currval FROM dual");
        ResultSet rs = pstm.executeQuery();
        rs.next();
        idField.setText(String.valueOf(rs.getLong(1)));
        idField.setDisable(true);
        modifyButton.setVisible(false);
    }


    private boolean dodawanie(Pomocdydaktyczna p) {
        boolean czyDodac = true;
        p.setRodzaj(rodzajField.getText());

        try {
            p.setIdpomocy(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Podałeś błędne ID, sprawdź czy jest unikalne i czy jest liczbą całkowitą dodatnią!");
            alert.showAndWait();
            czyDodac = false;
        }

        if (id_oplatyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id oplaty");
            alert.showAndWait();
            czyDodac = false;
        } else {
            p.setDodatkoweoplaty((Long) id_oplatyBox.getValue());
        }

        if (id_przedszkolankiBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id przedszkolanki");
            alert.showAndWait();
            czyDodac = false;
        } else {
            p.setPrzedszkolankaIdprac((Long) id_przedszkolankiBox.getValue());
        }

        if (id_grupyBox.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś id grupy");
            alert.showAndWait();
            czyDodac = false;
        } else {
            p.setGrupadocelowa((Long) id_grupyBox.getValue());
        }
        return czyDodac;
    }


    @FXML
    public void add() {
        Pomocdydaktyczna p = new Pomocdydaktyczna();
        boolean czyDodac = dodawanie(p);

        try {
            if (czyDodac) {
                PreparedStatement stmt = DataBase.getConnection().prepareStatement("insert into POMOCDYDAKTYCZNA(idpomocy, rodzaj, dodatkoweoplaty, grupadocelowa, osobaodpowiedzialna, oplata_idoplaty, przedszkolanka_idprac, grupaprzedszkolna_idgrupy) values (?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setLong(1, p.getIdpomocy());
                stmt.setString(2, p.getRodzaj());
                stmt.setLong(3, p.getDodatkoweoplaty());
                stmt.setLong(4, p.getGrupadocelowa());
                stmt.setLong(5, p.getPrzedszkolankaIdprac());
                stmt.setLong(6, p.getDodatkoweoplaty());
                stmt.setLong(7, p.getPrzedszkolankaIdprac());
                stmt.setLong(8, p.getGrupadocelowa());

                stmt.executeQuery();
                stmt.close();

                MainViewController.add(this.dataBase, idx);
                PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT POMOCDYD_SEQ.nextval FROM dual");
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
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT * from POMOCDYDAKTYCZNA where IDPOMOCY = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        rodzajField.setText(rs.getString("rodzaj"));
        id_grupyBox.setValue(rs.getLong("grupadocelowa"));
        id_oplatyBox.setValue(rs.getLong("dodatkoweoplaty"));
        id_przedszkolankiBox.setValue(String.valueOf(rs.getLong("osobaodpowiedzialna")));
        pstm.close();
    }

    @FXML
    private void update() {
        Pomocdydaktyczna p = new Pomocdydaktyczna();
        boolean czyDodac = dodawanie(p);
        try {
            if (czyDodac) {

                PreparedStatement stmt = DataBase.getConnection().prepareStatement("UPDATE POMOCDYDAKTYCZNA SET RODZAJ = ?, DODATKOWEOPLATY = ?, GRUPADOCELOWA = ?, OSOBAODPOWIEDZIALNA = ?, OPLATA_IDOPLATY = ?, PRZEDSZKOLANKA_IDPRAC = ?, GRUPAPRZEDSZKOLNA_IDGRUPY = ? WHERE IDPOMOCY = ?");
                stmt.setLong(8, p.getIdpomocy());
                stmt.setString(1, p.getRodzaj());
                stmt.setLong(2, p.getDodatkoweoplaty());
                stmt.setLong(3, p.getGrupadocelowa());
                stmt.setLong(4, p.getOsobaodpowiedzialna());
                stmt.setLong(5, p.getDodatkoweoplaty());
                stmt.setLong(6, p.getPrzedszkolankaIdprac());
                stmt.setLong(7, p.getGrupadocelowa());

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
