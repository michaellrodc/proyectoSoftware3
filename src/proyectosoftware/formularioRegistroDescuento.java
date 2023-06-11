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
public class formularioRegistroDescuento {
    String cbxOpcion;
    String codDescuento;
    String codComision;
    
    formularioRegistroDescuento(String cbx){
        this.cbxOpcion=cbx;
        codDescuento=null;
        codComision=null;
    }

    public void registroDescuento( float SRI, float Iees)
    {     
        Descuento x= new Descuento(SRI,Iees);
        x.registrarDescuento(cbxOpcion);
        codDescuento=x.getcodDescuento();
             
             

    }
    public void registroComision( int horasAntes, int horasDespues, float comisiones)
    {
        Comision x = new Comision(horasAntes,horasDespues,comisiones);       
        x.IngresarComision(cbxOpcion);
        codComision=x.getcodcomision();
             

    }
    public void registroSalario ()
    {
        Salario a = new Salario ();
        a.ingresarCodigos(cbxOpcion);
    }
    
}
