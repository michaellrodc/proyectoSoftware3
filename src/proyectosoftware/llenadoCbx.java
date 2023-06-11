/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author danny
 */
public class llenadoCbx {
    
    public void llenado(JComboBox<String> cbx)
    {
        
        Conexion conexion = new Conexion();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            // TODO add your handling code here:

            con = conexion.conector();
            String query =("SELECT emp_cedula FROM Empleado");
           
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            while(rs.next())
            {
                String ci=rs.getString("emp_cedula");
                cbx.addItem(ci);

            }

           
        } catch (IOException | SQLException ex) {
        }
        
    }
}
