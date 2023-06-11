package proyectosoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Descuento {
    private float aportacionesIESS;
    private float aportacionesSRI;
    private String codigoDescuento;

    public Descuento(double aporteIESS, double aporteSRI) {
        this.aportacionesIESS = (float)aporteIESS;
        this.aportacionesSRI =  (float) aporteSRI;
        
        this.codigoDescuento=null;
        //System.out.println(aportacionesIESS);
        //System.out.println(aportacionesSRI);
    }
    
    public float calcularDescuento(String codigo) throws SQLException {
    Conexion conexion = new Conexion();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet result = null;

    float descuentoTotal = 0;

    try {
        con = conexion.conector();
        String query = "SELECT d.desc_aporteIESS, d.desc_aporteSRI FROM descuento d, salario s WHERE s.slr_codigo = ? AND s.desc_codigo = d.desc_codigo";
        stmt = con.prepareStatement(query);

        stmt.setString(1, codigo);

        result = stmt.executeQuery();

        if (result.next()) {
            float aportacionesIESS = result.getFloat("desc_aporteIESS");
            float aportacionesSRI = result.getFloat("desc_aporteSRI");

            String codigo2 = codigo.substring(4);

            descuentoTotal = (float) ((Empleado.getEmpleado(codigo2).getSalarioNeto() * aportacionesIESS) + (Empleado.getEmpleado(codigo2).getSalarioNeto() * aportacionesSRI));

            // Llamar a la función para actualizar el descuento total en la tabla DESCUENTO
            actualizarDescuentoTotal(codigo, descuentoTotal);
        } else {
            // No se encontró ningún resultado en la consulta
            // Puedes agregar el código correspondiente o un mensaje de error si es necesario
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
    return descuentoTotal;
}

public void actualizarDescuentoTotal(String codigo, float descuentoTotal) throws SQLException {
    Conexion conexion = new Conexion();
    Connection con = null;
    PreparedStatement stmt = null;

    try {
        con = conexion.conector();
        String query = "UPDATE DESCUENTO SET desc_total = ? WHERE desc_codigo = (SELECT desc_codigo FROM salario WHERE slr_codigo = ?)";
        stmt = con.prepareStatement(query);

        stmt.setFloat(1, descuentoTotal);
        stmt.setString(2, codigo);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            //System.out.println("Descuento total actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el descuento total.");
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

    public void registrarDescuento(String ci)
    {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        String codigoSecuencial;
        ci="DESC-"+ci;
        validarCodigos ab =new validarCodigos();
        codigoDescuento=ci;

        try {
            con = conexion.conector();
            if (!ab.validarDescuento(ci))
            {
                String query =("INSERT INTO Descuento (desc_codigo, desc_aporteIESS,desc_aporteSRI) VALUES (?, ?, ?)");
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, ci);
            stmt.setFloat(2, aportacionesIESS);
            stmt.setFloat(3, aportacionesSRI);

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Descuento Ingresado Correctamente" );
            }
            else
            {
                String query =("UPDATE Descuento SET desc_aporteIESS = ?, desc_aporteSRI = ?  WHERE desc_codigo='"+ci+"';");
            
                stmt = con.prepareStatement(query);
                stmt.setFloat(1, aportacionesIESS);
                stmt.setFloat(2, aportacionesSRI);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Descuento Actualizado Correctamente" );
            }

            
            
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
        }
    }
    public String getcodDescuento()
    {
        String resultado =this.codigoDescuento;
        
        return resultado;
    }
}