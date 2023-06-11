
package proyectosoftware;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author micha
 */
public class Solicitud {
    
    private static Conexion conexion = new Conexion();
    private static java.sql.Connection con = null;
    private static PreparedStatement stmt = null;
    private static ResultSet result = null;
    
    
    private String codigoSolicitud;
    private boolean estadoSolicitud;
    private String estadoProceso; //Activo Completado Rechazado
    private Empleado emp;

    
    
    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public boolean getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }
    
    public Solicitud(String codigoSolicitud, boolean estadoSolicitud, String estadoProceso, String cedula) {
        this.codigoSolicitud = codigoSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.estadoProceso = estadoProceso;
        this.emp = Empleado.getEmpleado(cedula);
    }
    
    public Solicitud( String cedula) {
        this.codigoSolicitud = "SLC-"+cedula;  
        this.estadoSolicitud = true;
        this.estadoProceso = "Activo";
        this.emp = Empleado.getEmpleado(cedula);
    }
    
    public boolean validarExtranjero(String cedula){
        
        return emp.getExtranjero();
        
    }

    public void actualizarEstado(String estado){
        this.estadoSolicitud = false;
        this.estadoProceso = estado;
        
        try {
            con = conexion.conector();
            String cadena = "UPDATE Solicitud SET slc_estadoProceso = ?, slc_estadoSolicitud = ? WHERE slc_codigo = ?;";
            stmt = con.prepareStatement(cadena);

            stmt.setString(1, this.estadoProceso);
            stmt.setBoolean(2, this.estadoSolicitud);
            stmt.setString(3, this.codigoSolicitud);

            stmt.executeUpdate();
            stmt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Estado actualizado correctamente");

        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
        }
        
    }
    
    public void registroSolicitud(){
        
        try {
            con = conexion.conector();
            String cadena = "INSERT INTO Solicitud (slc_codigo,slc_estadoProceso,slc_estadoSolicitud) VALUES (?,?,?)";
            stmt = con.prepareStatement(cadena);

            stmt.setString(1, this.codigoSolicitud);
            stmt.setString(2, this.estadoProceso);
            stmt.setBoolean(3, this.estadoSolicitud);

            stmt.executeUpdate();
            stmt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Solicitud ingresada correctamente");

        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
        }
    }
    //patron estado
    public void eliminarRegistroAnterior(){
        try {
            con = conexion.conector();
            
            String cadena = "DELETE FROM Solicitud WHERE slc_codigo = ?";
            stmt = con.prepareStatement(cadena);
            
            stmt.setString(1, this.codigoSolicitud);
            
            stmt.executeUpdate();
            stmt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Se elimino la anterior solicitud con codigo " + this.codigoSolicitud);
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en " + ex.getMessage());
        }
    }
    public int validarSolicitud()
    {
        int resultado =0;
        try {
            con = conexion.conector();
            String cadena = "SELECT * FROM Solicitud WHERE slc_codigo = ?";
            stmt = con.prepareStatement(cadena);
            stmt.setString(1, codigoSolicitud);
            result = stmt.executeQuery();
            
            if (result.next()) {
                resultado=1;
                stmt = null;
                result=null;
              cadena = "SELECT * FROM Solicitud WHERE slc_codigo = ? AND slc_estadoProceso = 'Rechazado' OR slc_estadoProceso = 'Completado' ";
              stmt = con.prepareStatement(cadena);
               stmt.setString(1, codigoSolicitud);
                result = stmt.executeQuery();
                if(result.next()){
                 resultado=2;
                }
            } 
                        
            stmt.close();
            con.close();
            
        } catch (IOException | SQLException ex) {
            System.out.println("Error en " + ex.getMessage());
        }
        
        return resultado;
        
    }
    
    static public Solicitud getSolicitud(String cedula) {
        String codigoSolicitud = "SLC-"+cedula.trim(); 
        Solicitud solicitud = null;
        
        try {
            con = conexion.conector();
            
            String cadena = "SELECT * FROM Solicitud WHERE slc_codigo = ?";
            stmt = con.prepareStatement(cadena);
            stmt.setString(1, codigoSolicitud);
            result = stmt.executeQuery();
            
            if (result.next()) {
                solicitud = new Solicitud(
                    result.getString("slc_codigo"),
                    result.getBoolean("slc_estadoSolicitud"),
                    result.getString("slc_estadoProceso"),
                    cedula
                );
            } else {
                System.out.println("No se encontro ninguna solicitud con la cedula "+ cedula);
            }
            
            
            
            stmt.close();
            con.close();
            
        } catch (IOException | SQLException ex) {
            System.out.println("Error en " + ex.getMessage());
        }
        
        return solicitud;
    }
}
