/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nicoleagila
 */
public class Conexion  {
    private Connection conexion;

    public Conexion() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/migraciones";
        String user= "root";
        String pass= "nsaproot";
        try {
            con = DriverManager.getConnection(url, user, pass);
            this.conexion= con;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Error en conexion "+ex.getMessage());
            this.conexion=null;
        }
        System.out.println("Conexion existosa");
    }

    public Connection getConnection() {
        return this.conexion;
    }
    
}
