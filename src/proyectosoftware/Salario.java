
package proyectosoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author micha
 */
public class Salario {

    public Salario() {
    }
    
  
    public float calcularSalario(String codigo) throws SQLException
    {
       
    
        Comision a= new Comision();
        Descuento b = new Descuento(0,0);
        String cedula = codigo.substring(4);
        float salarioFinal= (float) Empleado.getEmpleado(cedula).getSalarioNeto() + a.calcularComision(codigo)- b.calcularDescuento(codigo);       
        actualizarSalarioTotal(codigo, salarioFinal);
        return salarioFinal;
        
        
    }
    public void actualizarSalarioTotal(String codigo, float salarioTotal) throws SQLException {
    Conexion conexion = new Conexion();
    Connection con = null;
    PreparedStatement stmt = null;

    try {
        con = conexion.conector();
        String query = "UPDATE Salario SET slr_total = ? WHERE slr_codigo = ?";
        stmt = con.prepareStatement(query);

        stmt.setFloat(1, salarioTotal);
        stmt.setString(2, codigo);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
           // System.out.println("Salario total actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el salario total.");
        }
    } catch (IOException | SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
    } finally {
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
}

    public void ingresarCodigos(String CI)
    {
        Conexion conexion = new Conexion();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        String codSalario="SLR-"+CI;
        String codDescuento="DESC-"+CI;
        String codComision="COM-"+CI;
        try {
            con = conexion.conector();
            String query =("SELECT * from Salario WHERE slr_codigo='"+codSalario+"' AND desc_codigo='"+codDescuento+"' AND com_codigo ='"+codComision+"';");
           
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            if(!rs.next())
            {
                PreparedStatement stmtP = null;
                 query =("INSERT INTO Salario (slr_codigo, desc_codigo,com_codigo) VALUES (?, ?, ?)");
            
                stmtP = con.prepareStatement(query);
                stmtP.setString(1, codSalario);
                stmtP.setString(2, codDescuento);
                stmtP.setString(3, codComision);       
               
                 stmtP.executeUpdate();
                 
            }
            stmt.close();
            con.close();
           

           
        } catch (IOException | SQLException ex) {
        }
    }
}
