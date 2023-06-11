/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

import javax.swing.JOptionPane;

/**
 *
 * @author danny
 */
public class formularioSolicitud {
    
    String Ci;
    
    formularioSolicitud(String cedulaIngresada)
    {
        this.Ci=cedulaIngresada;
    }
    
    public void validarSolicitud()
    {
        Solicitud sol = new Solicitud(this.Ci);
        
        if(sol.validarExtranjero(Ci)){
            if(sol.validarSolicitud()==0){           
            sol.registroSolicitud();
            }
            else{
                if(sol.validarSolicitud()==2)
                {
                    JOptionPane.showMessageDialog(null,"Su solicitud ya fue Acpetada o Rechazada ");
                    sol.eliminarRegistroAnterior();
                    sol.registroSolicitud();
                }
                else if (sol.validarSolicitud()==1)
                JOptionPane.showMessageDialog(null,"Usted ya tiene una solicitud en proceso ");
            
            }
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Usted no es extranjero, por ende no puede solicitad una devolución del IVA");
        }

    }
    public void verEstado()
    {
        Solicitud sol =  Solicitud.getSolicitud(this.Ci);
        String op=sol.getEstadoProceso();
        
        String solicitudtxt="Su solicitud esta : ";
        if(op.equals("Activo"))
        {
            String azul ="<html><font color='blue'>Activa</font></html>";
            solicitudtxt+="<html>" + solicitudtxt + azul ;       
        }
        else if (op.equals("Completado"))
        {   
            String verde ="<html><font color='green'>Completada</font></html>";
            solicitudtxt+="<html>" + solicitudtxt+verde;
        }
        else
        {
            String rojo ="<html><font color='red'>Rechazada</font></html>";
            solicitudtxt+="<html>" +solicitudtxt+rojo;
        }
            
        JOptionPane.showMessageDialog(null,solicitudtxt);
    }
    public boolean validarExtranjero ()
    {
        boolean resultado =false;
        Empleado emp = Empleado.getEmpleado(Ci);
        
        if(emp.getExtranjero()){
            resultado=true;
        }
        else
           JOptionPane.showMessageDialog(null,"Usted No Es Extranjero.");
 
        return resultado;
    }
}
