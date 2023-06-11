/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danny
 */
public class Usuario {
    String ususario;
    String Ci_Usuario;
    String contraseña;
    
    public Usuario(String Usuario,String Contraseña,String CI)
    {
        this.ususario=Usuario;
        this.contraseña=Contraseña;
        this.Ci_Usuario=CI;

    }
    public void ingresarUsuario()
    {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            // TODO add your handling code here:

            con = conexion.conector();
            String query =("INSERT INTO usuario (usuario, contraseña,emp_cedula) VALUES (?, ?, ?)");
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, ususario);
            stmt.setString(2, contraseña);
            stmt.setString(3, Ci_Usuario);
            

               
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario Ingresado Correctamente" );
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
        }
    }
}
