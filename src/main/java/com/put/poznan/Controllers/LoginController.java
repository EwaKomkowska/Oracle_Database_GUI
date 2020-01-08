package com.put.poznan.Controllers;

import com.put.poznan.JDBC.DataBase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController {

    private String login;
    private String password;
    private DataBase dataBase; //TODO: przekaż referencje

    private Boolean onceLog = true; //TODO: INITIALIZE THIS DUDE
    private Boolean oncePass = true;

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
            this.oncePass = false;
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
    private void startConnection() throws IOException {
        this.setLogin();
        this.setPassword();
        Boolean failState = true;
        dataBase.setUp(this.login, this.password);
        System.out.println("it worked");
        failState=false; //TODO: usun
        if (failState == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error connecting to the database.\nWrong login or password.\nTry again.", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                //do stuff
                //this
                //"**********";
            }
        } else {
            App.getStage().setScene(new Scene(App.loadFXML("primary")) );
            //App.setRoot("primary");
            //App.getScene().setRoot(new String("primary"));
        }
        //try to connect if fail pop a window that says try again wrong shit and reset
    }

    public void LoginController(){

    }

}
