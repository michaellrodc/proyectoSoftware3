package proyectosoftware;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author danny
 */
public class cuentaBancaria {
    private static Conexion conexion = new Conexion();
    private static java.sql.Connection con = null;
    private static PreparedStatement stmt = null;
    private static ResultSet result = null;
        
    
    private String cuentaCodigo;
    private String numeroCuenta;
    private double monto;

    public String getCuentaCodigo() {
        return cuentaCodigo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getMonto() {
        return monto;
    }
    
    
    public cuentaBancaria(String cedula, String numeroCuenta) {
        this.cuentaCodigo = "CNT-"+cedula;
        this.numeroCuenta = numeroCuenta;
        this.monto = 0;
    }
    
    private cuentaBancaria(String cuentaCodigo, String numeroCuenta,double monto) {
        this.cuentaCodigo = cuentaCodigo;
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
    }
    
    public void actualizarMonto(String cedula, double monto){
        try {
            con = conexion.conector();
            String cadena = "UPDATE cuentaBancaria SET cnt_monto = ? WHERE cnt_codigo = ?;";
            stmt = con.prepareStatement(cadena);

            stmt.setDouble(1, monto + this.monto);
            stmt.setString(2, this.cuentaCodigo);

            stmt.executeUpdate();
            stmt.close();
            con.close();
            
            Solicitud Sol=  Solicitud.getSolicitud(cedula);
            Sol.actualizarEstado("Completado");
            
            JOptionPane.showMessageDialog(null, "Solicitud Pagada con éxito");

        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
        }
    }
    
    static public cuentaBancaria getCuenta(String cedula) {
        String codigoCuenta = "CNT-"+cedula.trim(); 
        cuentaBancaria cuenta = null;
        
        try {
            con = conexion.conector();
            
            String cadena = "SELECT * FROM cuentaBancaria WHERE cnt_codigo = ?";
            stmt = con.prepareStatement(cadena);
            stmt.setString(1, codigoCuenta);
            result = stmt.executeQuery();
            
            if (result.next()) {
                cuenta = new cuentaBancaria(
                    codigoCuenta,
                    result.getString("cnt_numeroCuenta"),
                    result.getDouble("cnt_monto")
                );
            } else {
                System.out.println("No se encontro ninguna cuenta con la cedula "+ cedula);
            }
            
            
            
            stmt.close();
            con.close();
            
        } catch (IOException | SQLException ex) {
            System.out.println("Error en " + ex.getMessage());
        }
        
        return cuenta;
    }
}
