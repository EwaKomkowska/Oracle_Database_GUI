package com.put.poznan.Controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Optional;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class PrimaryController {

    @FXML
    Button przedszkolankaAddButton;
    @FXML
    Button przedszkolankaRemoveButton;

    @FXML
    private void removePrzedszkolanka() {
        int id = 5;
        String statement = "DELETE FROM PRZEDSZKOLANKA WHERE IDPRAC = ?";

        remove(statement, id);
    }

    @FXML
    private void removeSekretarka() {
        int id = 5;
        String statement = "DELETE FROM SEKRETARKA WHERE IDPRAC = ?";

        remove(statement, id);
    }

    @FXML
    private void removeDziecko() {
        int id = 5;
        String statement = "DELETE FROM DZIECKO WHERE IDDZIECKA = ?";

        remove(statement, id);
    }

    @FXML
    private void removeFestyn() {
        int id = 5;
        String statement = "DELETE FROM FESTYN WHERE IDFESTYNU = ?";

        remove(statement, id);
    }

    @FXML
    private void removeOplata() {
        int id = 5;
        String statement = "DELETE FROM OPLATA WHERE IDOPLATY = ?";

        remove(statement, id);
    }


    @FXML
    private void removeZajecia() {
        int id = 5;
        String statement = "DELETE FROM ZAJECIADODATKOWE WHERE IDZAJECIA = ?";

        remove(statement, id);
    }

    @FXML
    private void removeZebranie() {
        int id = 5;
        String statement = "DELETE FROM ZEBRANIEZRODZICAMI WHERE IDZEBRANIA = ?";

        remove(statement, id);
    }

    @FXML
    private void removePosilek() {
        int id = 5;
        String statement = "DELETE FROM POSILEK WHERE IDPOSILKU = ?";

        remove(statement, id);
    }

    @FXML
    private void removeHospitacja() {
        int id = 5;
        String statement = "DELETE FROM HOSPITACJA WHERE IDHOSPITACJI = ?";

        remove(statement, id);
    }

    @FXML
    private void removeGrupa() {
        int id = 5;
        String statement = "DELETE FROM GRUPAPRZEDSZKOLNA WHERE IDGRUPY = ?";

        remove(statement, id);
    }

    @FXML
    private void removePomoc() {
        int id = 5;
        String statement = "DELETE FROM POMOCDYDAKTYCZNA WHERE IDPOMOCY = ?";

        remove(statement, id);
    }


    @FXML
    private void addPrzedszkolanka() {
        //tu będzie wiecej zabawy, bo to chyba trzeba zrobić z pobieraniem danych w nowym oknie
        return;
    }


    private void remove(String statement, int parameter) {
        PreparedStatement stmt;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        try {
            stmt = App.getDataBase().getConnection().prepareStatement(statement);
            stmt.setInt(1, parameter);        //ustaw pierwszy znak zapytania ma wartosc paramtetru

            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Jesteś pewien, że chcesz usunąć obekt o id: " + parameter + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int deletedRows = stmt.executeUpdate();         //ilosc usunietych wierszy

                if (deletedRows == 0) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("You can't remove this object. \nMaybe it's not exist?\nPlease, try again!\n Choose one of object that you can see on the list.");
                    alert.showAndWait();
                } else
                    System.out.println("Usunieto " + deletedRows + " obiektów");
            } /*else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Zrezygnowałeś z usuwania!");
                alert.showAndWait();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
