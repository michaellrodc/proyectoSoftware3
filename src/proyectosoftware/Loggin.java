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
import javax.swing.JOptionPane;
import static proyectosoftware.Conexion.con;

/**
 *
 * @author danny
 */
public class Loggin {

    
    public int validarUsuario(String Usuario,String Contraseña)
    {
        int bandera=0;
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        String usuarioIngresado=Usuario;
        String contraseñaIngresada=Contraseña;
        if(Usuario.equals("admin")&&Contraseña.equals("admin"))
        {
            bandera=2;
        }
        else
        {
            try {
            // TODO add your handling code here:

            con = conexion.conector();
            String query = "SELECT u.usuario, u.contraseña FROM  Usuario u WHERE u.usuario = ? AND u.contraseña = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, usuarioIngresado);
            stmt.setString(2, contraseñaIngresada);
            result = stmt.executeQuery();
            
            if(result.next())
                bandera=1;
            else
                bandera=0;
            
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
            }
              
        }
        
        return bandera;
    }
}
