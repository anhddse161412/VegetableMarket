/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Utech
 */
public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        
        String databaseName = "VegetableShopManagementSE";
        String user = "user";
        String password = "user";
        String IP ="localhost" ;
        String port = "1433";
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = ("jdbc:sqlserver://"+ IP + ":" + port +";databaseName="+databaseName);
        conn = DriverManager.getConnection(url, user, password);
        
        return conn;
    }
}
