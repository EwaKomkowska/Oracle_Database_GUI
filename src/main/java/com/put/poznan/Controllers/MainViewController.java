package com.put.poznan.Controllers;

import java.io.IOException;

import com.put.poznan.JDBC.DataBase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

public class MainViewController {

    private DataBase dataBase;

    public MainViewController(){    }

    //TODO: CHECK IF THIS WORKS
    public MainViewController(DataBase dataBase){
        this.setDataBase(dataBase);
    }

    public void setDataBase(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @FXML
    private void logOut() throws IOException {
        this.dataBase.closeConnection();
        //Platform.exit(); //TODO: zamiast tego idz do login formularza od nowa
       // App.getStage().setScene(new Scene(App.loadFXML("login")) );
        FXMLLoader loader = App.getFXMLLoader("login");
        Parent root = loader.load();
        LoginController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
