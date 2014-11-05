package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe DatabaseTools Outils de transfert de base de données
 * @author Donavan Martin
 */
public class DatabaseTools {
    private final String url;         //Lien acces MySQL
    private final String username;    //User MySQL
    private final String password;    //Password MySQL
    private final String driver;      //Driver MyQSL
    private Connection connection = null;
    
    /**
     * Constructeur par défaut du DatabaseTools
     */
    public DatabaseTools() {
        url      = "jdbc:mysql://localhost:3306/tetris";       
        username = "root";
        password = "";
        driver   = "com.mysql.jdbc.Driver";
    }

    /**
     * Constructeur DatabaseTools(
     * @param url lien URL MySQL
     * @param username nom d'utilisateur MySQL
     * @param password mot de passe MySQL
     * @param driver driver MySQL
     */
    public DatabaseTools(String url, String username, String password, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }
    
    /**
     * Cration de la connection à MySQL
     * @return connection
     * @throws SQLException database Access error or other errors
     * @throws ClassNotFoundException No definition for the class with the specified name could be found. 
     */
    public Connection createConnexion() throws SQLException, ClassNotFoundException{
        //Register the JDBC  driver for MySql
        Class.forName(driver);
        System.out.println("com.mysql.jdbc.Driver found");
        connection = DriverManager.getConnection(url,username,password);
        System.out.println("Connexion Ok");
        return connection;
    }
    
    /**
     * Fermeture de la connection
     * @throws SQLException Database Access error or other errors
     */
    public void closeConnexion() throws SQLException{
        connection.close();
    }
}
