package com.put.poznan.JDBC;

import java.sql.*;

public class DataBase {

    private Connection connection;

    public DataBase(){
        this.setUp();
    }

    public DataBase(String login, String password){
        this.setUp();
        this.startConnection(login,password);
    }

    public void setUp() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean startConnection(String login, String password){
        Boolean conState = false;
        try {
            this.connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1523:XE",login, password); //XE
            conState = true;
        } catch (SQLException e) {
            //System.out.println(e.getErrorCode());
            conState = false;
            e.printStackTrace();
        }
        return conState;
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
