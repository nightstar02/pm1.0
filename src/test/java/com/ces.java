package com;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ces


{
    @Test

    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String dbURL = "jdbc:oracle:thin:@//134.64.244.6:1521/edp555";
            conn = DriverManager.getConnection(dbURL, "bdw", "bdw#$_2017");
            System.out.println("链接成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

}


