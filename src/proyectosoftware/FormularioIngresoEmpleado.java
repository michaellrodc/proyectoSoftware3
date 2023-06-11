package proyectosoftware;

/**
 *
 * @author mao_a
 */
public class FormularioIngresoEmpleado {
    static private Empleado emp;
    static private cuentaBancaria cuenta;

    static public void IngresarEmpleado(String cedulaIngresada, String nombreIngresado, String apellidoIngresado, boolean extranjero, String categoriaIngresada, String contratoIngresado, int horasEquivalentesIngresadas, double salarioNetoIngresado, String numeroCuentaIngresado) {
        
        cuenta = new cuentaBancaria(cedulaIngresada,numeroCuentaIngresado);
        
        emp = new Empleado(cedulaIngresada,nombreIngresado,apellidoIngresado,extranjero,
                    categoriaIngresada,contratoIngresado,horasEquivalentesIngresadas,
                   salarioNetoIngresado,cuenta
        );
        
        emp.registroEmpleado();
    }

   
    
    
}
