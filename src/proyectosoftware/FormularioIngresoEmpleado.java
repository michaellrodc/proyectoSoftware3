package proyectosoftware;

/**
 *
 * @author mao_a
 */
public class FormularioIngresoEmpleado {
    static private Empleado emp;

    static public void IngresarEmpleado(String cedulaIngresada, String nombreIngresado, String apellidoIngresado, boolean extranjero, String categoriaIngresada, String contratoIngresado, int horasEquivalentesIngresadas, double salarioNetoIngresado) {
        
        emp = new Empleado(cedulaIngresada,nombreIngresado,apellidoIngresado,extranjero,
                    categoriaIngresada,contratoIngresado,horasEquivalentesIngresadas,
                   salarioNetoIngresado
        );
        
        emp.registroEmpleado();
    }

   
    
    
}
