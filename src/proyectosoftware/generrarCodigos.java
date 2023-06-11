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
public class generrarCodigos {
    
    public String codigoComisiones()
    {
        String nuevoCodigo="";
        Conexion conexion = new Conexion();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            con = conexion.conector();
            String query =("SELECT Max(Right(com_codigo,3)) AS Expr1 FROM COMISION;");
           
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            
            int ultimoDigito = 0;
            
            if (rs.next()) {
                String maximo = rs.getString(1);
                if (maximo != null) {
                    ultimoDigito = Integer.parseInt(maximo);
                }
            }
            
            // Generar el nuevo código secuencial
            ultimoDigito++;
            
            nuevoCodigo = String.format("COM-%03d-001", ultimoDigito);
            
            return nuevoCodigo;
            
            
        } catch (IOException | SQLException ex) {
        }
            
        
        return nuevoCodigo;
    }
    public String codigoDescuento()
    {
        String nuevoCodigo="";
        Conexion conexion = new Conexion();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            con = conexion.conector();
            String query =("SELECT Max(Right(desc_codigo,3)) AS Expr1 FROM DESCUENTO;");
           
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            
            int ultimoDigito = 0;
            
            if (rs.next()) {
                String maximo = rs.getString(1);
                if (maximo != null) {
                    ultimoDigito = Integer.parseInt(maximo);
                }
            }
            
            // Generar el nuevo código secuencial
            ultimoDigito++;
            
            nuevoCodigo = String.format("DES-%03d-001", ultimoDigito);
            
            return nuevoCodigo;
            
            
        } catch (IOException | SQLException ex) {
        }
            
        
        return nuevoCodigo;
    }
}
