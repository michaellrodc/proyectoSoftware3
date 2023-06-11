/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

/**
 *
 * @author danny
 */
public class formularioDevolucionIVA {
    
    public void rechazarSolicitud(String Ci)
    {
        Devolucion dev = new Devolucion();
        dev.rechazarDevolucion(Ci);
    }
    public void aceptarPago(String Ci)
    {
        Devolucion dev = new Devolucion();
        dev.depositarDevolucion(Ci);
    }
    
}
