package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static DataBase dataBase;

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene(){
        return scene;
    }

    public static DataBase getDataBase(){return dataBase;}

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.dataBase = new DataBase(); //("paweu","haslo");
        FXMLLoader loader = getFXMLLoader("login");
        Parent root = loader.load();
        LoginController c = loader.getController();
        c.setDataBase(dataBase);
        Scene scene = new Scene(root);
        this.scene = scene;
        stage.setScene(scene); //, 500, 500));
        //scene = new Scene(loadFXML("login"));
        //stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/put/poznan/" + fxml + ".fxml"));
        return fxmlLoader;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/put/poznan/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        //System.out.println("DISCONNECTION");
        //dataBase.closeConnection();
    }



}