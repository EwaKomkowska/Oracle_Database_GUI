package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginController {

    private String login;
    private String password;
    private DataBase dataBase;

    private Boolean onceLog = true; //TODO: INITIALIZE THIS DUDE
    private Boolean oncePass = true;

    public void LoginController(){
        this.dataBase = App.getDataBase();
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button passwordButton;

    @FXML
    private Button loginButton; //TODO: czy potrzebuje zmienne na buttony?

    @FXML
    private void setLogin(){
        this.login = loginTextField.getText();
    }

    @FXML
    private void setPassword(){
        this.password = passwordField.getText();
    }

    @FXML
    private void clearLogin(){  //usun tez this.pass this.log
        if(this.onceLog){
            this.login = "";
            this.loginTextField.setText("");
            this.onceLog = false;
        }
    }

    @FXML
    private void clearPassword(){
        if(this.oncePass){
            this.password = "";
            this.passwordField.setText("");
            this.passwordField.setPromptText("");
            this.oncePass = false;
        }
    }

    private void resetFormula(){
        this.onceLog = true;
        this.oncePass = true;
        this.login = "";
        this.loginTextField.setText("Login");
        this.password = "";
        this.passwordField.setText("******");
    }

  /*  @FXML
    private void emptyCheck(){
        if (this.passwordField.getText().equals("")){

        }
    }
  */

    @FXML
    private void showPassword(){
        password = passwordField.getText();
        passwordField.clear();
        passwordField.setPromptText(password);
    }

    @FXML
    private void hidePassword(){
        StringBuilder stringBuilder = new StringBuilder();
        passwordField.setText(password);
        for(int i = 0; i < password.length(); i++ ) {stringBuilder.append('*');}
        passwordField.setPromptText(stringBuilder.toString());
    }

    @FXML
    private void startConnection() throws IOException, SQLException {
        this.setLogin();
        this.setPassword();
        Boolean conState = false;
        conState = dataBase.startConnection(this.login, this.password);
        //System.out.println("it worked");
        if (!conState) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error connecting to the database.\nWrong login or password.\nTry again.", ButtonType.OK);
            alert.showAndWait();
            this.resetFormula();
            if (alert.getResult() == ButtonType.OK) {
                //do stuff
                //this
                //"**********";
            }
        } else {
           // App.getStage().setScene(new Scene(App.loadFXML("primary")) );
            FXMLLoader loader = App.getFXMLLoader("primary");
            Parent root = loader.load();
            MainViewController c = loader.getController();
            c.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene); //, 500, 500));

            //inicjalizacje ID:
            PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT ZEBRANIE_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT ZAJDOD_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT SEKRETARKA_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT PRZEDSZKOLANKA_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT POSILEK_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT POMOCDYD_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT OPLATA_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT HOSPITACJA_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT GRUPA_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT FESTYN_SEQ.nextval FROM dual");
            pstm.executeQuery();

            pstm = DataBase.getConnection().prepareStatement("SELECT DZIECKO_SEQ.nextval FROM dual");
            pstm.executeQuery();
            //TODO: przekaz referencje na baze danych
        }
        //try to connect if fail pop a window that says try again wrong shit and reset
    }


}
