package com.put.poznan.JDBC;

import java.sql.*;

public class DataBase {

    private Connection connection;

    public DataBase(){

    }

    public DataBase(String login, String password){
        this.setUp(login,password);
    }

    public void setUp(String login, String password){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:PAWEL",login, password); //"jdbc:oracle:thin:@localhost:1523:XE", login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
