package com.example.devoir.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionDB {


    private Connection Con;

    public Connection getCon() {
        return Con;
    }

    public void setCon(Connection con) {
        Con = con;
    }

    public ConnexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "");
            System.out.println("Connection OK");
        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }catch (SQLException sqex){
            sqex.printStackTrace();
        }

}}
