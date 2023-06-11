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
public class formularioInicioSesion {
 
    
    String usuarioIngresado ;
    String contraseñaIngresada;
    
     formularioInicioSesion (String usuario,String contraseña)
    {
        this.usuarioIngresado=usuario;
        this.contraseñaIngresada=contraseña;
        
    }
    public void validarUsuario(javax.swing.JFrame x)
    {
        Loggin lg = new Loggin();
        int validador =lg.validarUsuario(this.usuarioIngresado, this.contraseñaIngresada);
        
        if(validador==1)
        {
            //Pantalla de consultas
            pantallaConsultar b = new pantallaConsultar();
            b.setVisible(true);
            x.dispose();
            
        }else if(validador==2)
        {
            pantallaOpciones b = new pantallaOpciones();
            b.setVisible(true);
            x.dispose();
        }else{
        JOptionPane.showMessageDialog(null, "Error en el inicio de sesion, revise el usuario u contraseña ");    

        }
    }
}
