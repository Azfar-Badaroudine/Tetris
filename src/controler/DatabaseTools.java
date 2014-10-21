/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

/**
 *
 * @author DLU_usager
 */
public class DatabaseTools {
    private String url;         //lien pour l'accès à mysql
    private String username;    //
    private String password;
    private String driver;
    private Connection connection = null;

    public DatabaseTools(String url, String username, String password, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }

    public DatabaseTools() {
        this.url = "jdbc:mysql://localhost:3306/inscription";
        this.username = "root";
        this.password = "";
        this.driver = "com.mysql.jdbc.Driver";
    }
    
    public Connection createConnexion() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        
        connection = DriverManager.getConnection(url,username,password);
        return connection;
    }
    
    public void closeConnexion() throws SQLException{
        connection.close();
    }
}
