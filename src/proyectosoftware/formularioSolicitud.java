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
        Solicitud sol = new Solicitud (this.Ci);
        
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
        Solicitud sol = new Solicitud (this.Ci);
        String solicitudtxt="Su solicitud esta :";
        if(sol.getestadoProceso().equals("Activo"))
        {
            solicitudtxt+="<html><font color='blue'>Activa</font></html>";           
        }
        else if (sol.getestadoProceso().equals("Completado"))
        {           
            solicitudtxt+="<html><font color='green'>Completada</font></html>";
        }
        else
        {
            solicitudtxt+="<html><font color='red'>Rechazada</font></html>";
        }
            
        JOptionPane.showMessageDialog(null,solicitudtxt);
    }
}
