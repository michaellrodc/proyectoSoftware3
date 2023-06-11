/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author danny
 */
public class Devolucion {
    double ivaDevolucion;
    Empleado emp = null;
    Solicitud Sol=null;

    
    public double getivaDevolucion()
    {
        return this.ivaDevolucion;
    }
    public void calcularIva(String Ci)
    {
        emp=Empleado.getEmpleado(Ci);
        this.ivaDevolucion=emp.getSalarioNeto()*0.12;
    }
    public void depositarDevolucion(String Ci)
    {
        String cod="CNT-"+Ci.trim();
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        emp=Empleado.getEmpleado(Ci);
        double sueldo =emp.getSalarioNeto();
        try {
            con = conexion.conector();
            String cadena = "UPDATE cuentaBancaria SET cnt_monto = ? WHERE cnt_codigo = ?;";
            stmt = con.prepareStatement(cadena);

            stmt.setDouble(1, this.ivaDevolucion+sueldo);
            stmt.setString(2, cod);

            stmt.executeUpdate();
            stmt.close();
            con.close();
            
            Sol=  Solicitud.getSolicitud(Ci);
            Sol.actualizarEstado("Completado");
            
            JOptionPane.showMessageDialog(null, "Solicitud Pagada con éxito");

        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
        }
        
    }
    public void rechazarDevolucion (String Ci)
    {
            Sol=  Solicitud.getSolicitud(Ci);
            Sol.actualizarEstado("Rechazado");
    }
    //sdfsdfs
}
