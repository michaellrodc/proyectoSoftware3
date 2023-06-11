package proyectosoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Consulta {
    private JTable table;
    private DefaultTableModel model;

    public Consulta(JTable table) {
        this.table = table;
        this.model = (DefaultTableModel) table.getModel();
    }

    public void mostrarTabla(String cedula) throws SQLException {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try {
            con = conexion.conector();

            // Consulta para obtener los datos necesarios
            String query = "SELECT e.emp_cedula, e.emp_nombre, e.emp_apellido, e.emp_categoria, e.emp_contrato, e.emp_salarioNeto, c.com_total, d.desc_total, (e.emp_salarioNeto + c.com_total - d.desc_total) AS slr_total " +
                    "FROM Empleado e " +
                    "JOIN Salario s ON s.slr_codigo = e.slr_codigo " +
                    "JOIN Descuento d ON d.desc_codigo = s.desc_codigo " +
                    "JOIN Comision c ON c.com_codigo = s.com_codigo " +
                    "WHERE e.emp_cedula LIKE ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, cedula + "%");
            result = stmt.executeQuery();

            // Limpiar la tabla antes de agregar nuevos datos
            model.setRowCount(0);

            while (result.next()) {
                Object[] fila = new Object[9];

                fila[0] = result.getString("emp_cedula");
                fila[1] = result.getString("emp_nombre");
                fila[2] = result.getString("emp_apellido");
                fila[3] = result.getString("emp_categoria");
                fila[4] = result.getString("emp_contrato");
                fila[5] = result.getString("emp_salarioNeto");
                fila[6] = result.getString("com_total");
                fila[7] = result.getString("desc_total");

                Salario salario = new Salario();
                salario.calcularSalario("SLR-" + result.getString("emp_cedula"));
                fila[8] = result.getString("slr_total");

                model.addRow(fila);
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
    }
    
    public void mostrarTablaExtranjeros(String cedula) throws SQLException {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try {
            con = conexion.conector();

            // Consulta para obtener los datos necesarios
            String query = "SELECT e.emp_cedula, e.emp_nombre, e.emp_apellido, e.emp_categoria, e.emp_contrato, e.emp_salarioNeto, c.com_total, d.desc_total, (e.emp_salarioNeto + c.com_total - d.desc_total) AS slr_total " +
                    "FROM Empleado e " +
                    "JOIN Salario s ON s.slr_codigo = e.slr_codigo " +
                    "JOIN Descuento d ON d.desc_codigo = s.desc_codigo " +
                    "JOIN Comision c ON c.com_codigo = s.com_codigo " +
                    "WHERE e.emp_cedula LIKE ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, cedula + "%");
            result = stmt.executeQuery();

            // Limpiar la tabla antes de agregar nuevos datos
            model.setRowCount(0);

            while (result.next()) {
                Object[] fila = new Object[9];

                fila[0] = result.getString("emp_cedula");
                fila[1] = result.getString("emp_nombre");
                fila[2] = result.getString("emp_apellido");
                fila[3] = result.getString("emp_categoria");
                fila[4] = result.getString("emp_contrato");
                fila[5] = result.getString("emp_salarioNeto");
                fila[6] = result.getString("com_total");
                fila[7] = result.getString("desc_total");

                Salario salario = new Salario();
                salario.calcularSalario("SLR-" + result.getString("emp_cedula"));
                fila[8] = result.getString("slr_total");

                model.addRow(fila);
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
    }
}
