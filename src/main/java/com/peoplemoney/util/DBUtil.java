package com.peoplemoney.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties properties=new Properties();
    static {


        try {
            InputStream in =DBUtil.class.getClassLoader().getResourceAsStream("db3.properties");
            properties.load(in);

            Class.forName(properties.getProperty("jdbcName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
            Connection  connection = null;



        try {
            String dbUrl=properties.getProperty("dbUrl");
            String dbName=properties.getProperty("dbName");
            String dbPwd=properties.getProperty("dbPwd");
            connection= DriverManager.getConnection(dbUrl,dbName,dbPwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;

    }
    /**
     * 关闭资源
     */


    public static void close(ResultSet resultSet,
                             PreparedStatement preparedStatement,
                             Connection connection){

        if (resultSet !=null){
            try {
                resultSet.close();
                if (preparedStatement !=null){
                    resultSet.close();
                }
                if (connection !=null){
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
