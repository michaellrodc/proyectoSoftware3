/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosoftware;

/**
 *
 * @author danny
 */
public class formularioRegistroEmpleados {
    String usuarioIngresado;
    String contraseñaIngresada;
    String cedulaIngresada;
    
    formularioRegistroEmpleados(String usuario,String contraseña,String cedula)
    {
        this.cedulaIngresada=cedula;
        this.contraseñaIngresada=contraseña;
        this.usuarioIngresado=usuario;
        
    }
    public void registrarUsuario()
    {
        Usuario a = new Usuario(this.usuarioIngresado,this.contraseñaIngresada,this.cedulaIngresada);
        a.ingresarUsuario();
    }
}
