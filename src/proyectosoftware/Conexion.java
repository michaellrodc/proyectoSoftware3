/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author danny
 */
public class Conexion {
    String filePath = System.getProperty("user.dir") + "/src/BaseDeDatos/Financiero.accdb";
 
    public static Connection con;
    public Connection conector() throws FileNotFoundException, IOException, SQLException {
           con = DriverManager.getConnection("jdbc:ucanaccess://"+filePath);
            if (con!=null){
                //System.out.println("Conexion establecida");
                //JOptionPane.showMessageDialog(null,"Conexion con la Base de Datos Establecida Adecuadamente");
            }
            else{
                System.out.println("No hay una conexion establecida");
            }
            return con ;
    }
    
}
