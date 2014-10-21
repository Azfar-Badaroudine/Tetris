/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package base.de.données;

import java.sql.*;

/**
 *
 * @author DLU_usager
 */
public class BaseDeDonnées {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    Statement stmt;
    ResultSet rs;
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/inscription"; 

    try{
        //Register the JDBC driver for MySQL.
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("com.mysql.jdbc.Driver found");

        con = DriverManager.getConnection(url,"root","");
        System.out.println("Connexion Ok");

        //Get a Statement object
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * " + "from inscrit ORDER BY nom, prenom"); //sorting and research

        System.out.println("Voici mes contacts:");
        while(rs.next()){

        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        String noTel = rs.getString("noTelephone");

        System.out.printf("%-20s %-20s %-10s\n", nom, prenom, noTel);
        }//end while loop
        con.close();

    }catch(SQLException e){			//acces fail with database
    System.out.println("Error SQL : " + e.getMessage());
    }catch(ClassNotFoundException e){		//connexion fail with database
    System.out.println("Error BD : " + e.getMessage());
    }catch(Exception e){			//error with the program
    System.out.println("Error :" + e.getMessage());
    }finally {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }
}

    
}
