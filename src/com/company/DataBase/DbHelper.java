
package com.company.DataBase;

import java.sql.*;

public class DbHelper {
    private String userName="root";
    private String password="12345";
    private String dbURL="jdbc:mysql://localhost:3306/kutuphane?useSSL=false&serverTimezone=UTC";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL,userName,password);
    }

    public void showErrorMessage(SQLException exception){
        System.out.println("error : "+exception.getMessage());
        System.out.println("error code : "+exception.getErrorCode());
    }



}


