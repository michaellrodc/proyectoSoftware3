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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author danny
 */
public class validarCodigos {
    
    public boolean validarDescuento(String codigo)
    {
        boolean resultado=false;
        Conexion conexion = new Conexion();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            // TODO add your handling code here:

            con = conexion.conector();
            String query =("SELECT * from descuento WHERE desc_codigo='"+codigo+"';");
           
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                resultado=true;

            }
            
           stmt.close();
            con.close();

           
        } catch (IOException | SQLException ex) {
        }
        
        return resultado;
    }
    public boolean validarComisiones(String codigo)
    {
        boolean resultado=false;
        Conexion conexion = new Conexion();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            // TODO add your handling code here:

            con = conexion.conector();
            String query =("SELECT * from Comision WHERE com_codigo='"+codigo+"';");
           
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                resultado=true;

            }
            stmt.close();
            con.close();
           

           
        } catch (IOException | SQLException ex) {
        }
        
        return resultado;
    }
    public void validarCodigoSalarios(String codigo)
    {
        
        
    }
    
}
