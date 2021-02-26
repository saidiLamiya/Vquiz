package com.gi.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnnectionUtil {
    Connection conn = null;

    public static Connection conDB() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
