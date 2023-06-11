package proyectosoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Comision {

    private int horasExtraAntes;
    private int horasExtraDespues;
    private float valorComision;
    private String codigoComision;

    public Comision() {
    this.horasExtraAntes = 0;
    this.horasExtraDespues = 0;
    this.valorComision = 0;
    }
    Comision(int horasExtAnt,int horasExtDes,float comision ){
      this.horasExtraAntes = horasExtAnt;
    this.horasExtraDespues = horasExtDes;
    this.valorComision = comision; 
    this.codigoComision=null;
    }
    
    public float calcularComision(String codigo) throws SQLException {
    Conexion conexion = new Conexion();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet result = null;
    float comisionTotal = 0;
    try {
        con = conexion.conector();
        String query = "SELECT c.com_horasExtraAntes, c.com_horasExtraDespues, c.com_valorComision, c.com_codigo, s.com_codigo FROM comision c, salario s WHERE s.slr_codigo = ? AND s.com_codigo = c.com_codigo";
        stmt = con.prepareStatement(query);

        stmt.setString(1, codigo);

        result = stmt.executeQuery();

        if (result.next()) {
            int horasExtraAntes = result.getInt("com_horasExtraAntes");
            int horasExtraDespues = result.getInt("com_horasExtraDespues");
            float valorComision = result.getFloat("com_valorComision");
           
            String codigo2 = codigo.substring(4);
            comisionTotal = (float) ((((horasExtraAntes * 1.5) * Empleado.getEmpleado(codigo2).getSalarioNeto() / 160) + ((horasExtraDespues * 2) * Empleado.getEmpleado(codigo2).getSalarioNeto() / 160))+valorComision);
            actualizarComisionTotal(codigo,comisionTotal);
        }
        
        
        else {
                System.out.println("No se encontro ningun empleado ");
            }
        
    } catch (IOException | SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
    } finally {
        if (result != null) {
            result.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
    return comisionTotal;
    }
    public void actualizarComisionTotal(String codigo, float comisionTotal) throws SQLException {
    Conexion conexion = new Conexion();
    Connection con = null;
    PreparedStatement stmt = null;

    try {
        con = conexion.conector();
        String query = "UPDATE COMISION SET com_total = ? WHERE com_codigo = (SELECT com_codigo FROM salario WHERE slr_codigo = ?)";
        stmt = con.prepareStatement(query);

        stmt.setFloat(1, comisionTotal);
        stmt.setString(2, codigo);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            //System.out.println("Comisión total actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la comisión total.");
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

    
    public void IngresarComision( String ciCod )
    {
            Conexion conexion = new Conexion();
            Connection con = null;
            PreparedStatement stmt = null;
            ciCod="COM-"+ciCod;

            codigoComision=ciCod;
            validarCodigos ab = new validarCodigos();

            try {
                con = conexion.conector();
                if(!ab.validarComisiones(ciCod))
                {
                    String query =("INSERT INTO Comision (com_codigo, com_horasExtraAntes,com_horasExtraDespues,com_valorComision) VALUES (?, ?, ?, ?)");

                    stmt = con.prepareStatement(query);
                    stmt.setString(1, ciCod);
                    stmt.setInt(2, horasExtraAntes);
                    stmt.setInt(3, horasExtraDespues);
                    stmt.setFloat(4, valorComision); 
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Comision Ingresado Correctamente" );
                }
                else
                {
                    String query =("UPDATE Comision SET com_horasExtraAntes = ?, com_horasExtraDespues = ?, com_valorComision=?  WHERE com_codigo='"+ciCod+"';");
            
                    stmt = con.prepareStatement(query);
                    
                    stmt.setInt(1, horasExtraAntes);
                    stmt.setInt(2, horasExtraDespues);
                    stmt.setFloat(3, valorComision); 

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "comision Actualizado Correctamente" );
                }
                
                
            } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
            }
    }
    
    public String getcodcomision()
    {
        String resultado =this.codigoComision;
        
        return resultado;
    }
}