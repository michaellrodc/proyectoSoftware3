/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

/**
 *
 * @author danny
 */
public class Devolucion {
    double ivaDevolucion;
    Empleado emp = null;
    Devolucion()
    {
        
    }
    public double getivaDevolucion()
    {
        return this.ivaDevolucion;
    }
    public void calcularIva(String Ci)
    {
        emp=Empleado.getEmpleado(Ci);
        this.ivaDevolucion=emp.getSalarioNeto()*0.12;
    }
    public void depositarDevolucion()
    {
        
    }
    //sdfsdfs
}
