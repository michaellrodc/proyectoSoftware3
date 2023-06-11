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
    private double ivaDevolucion;
    private Empleado emp = null;
    private Solicitud Sol=null;

    
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
        emp=Empleado.getEmpleado(Ci);
        double sueldo =emp.getSalarioNeto();
        double totalDevolver = this.ivaDevolucion+sueldo;
        emp.getCuenta().actualizarMonto(Ci.trim(), totalDevolver);
        
    }
    public void rechazarDevolucion (String Ci)
    {
            Sol=  Solicitud.getSolicitud(Ci);
            Sol.actualizarEstado("Rechazado");
    }
    //sdfsdfs
}
