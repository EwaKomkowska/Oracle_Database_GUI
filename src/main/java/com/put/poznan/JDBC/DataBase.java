package com.put.poznan.JDBC;

import com.put.poznan.Controllers.App;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private static Connection connection;

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
            this.connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:PAWEL",login, password); //XE 1523
            //this.connection.setAutoCommit(false); //TODO: do refresha moze
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("hibernate.connection.username", login); //system//TODO: przekaz stringi zapisz w apie?? dostac z bazy??
            properties.put("hibernate.connection.password", password); //Oracle2019
            //EntityManagerFactory
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit", properties);
            EntityManager em = emf.createEntityManager();
            App.setEMF(emf);
            App.setEM(em);
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

    public static Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
