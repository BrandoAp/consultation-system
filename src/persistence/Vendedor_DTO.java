package persistence;

import connection.connection;
import modules.Vendedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Vendedor_DTO {

    public boolean insertVendedor(Vendedor vendedor) {
        String sql = "INSERT INTO vendedor (id, codigo, nombre, apellido, departamento_codigo, cargo, ventas_mensuales, ventas_anuales) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vendedor.getId());
            stmt.setString(2, vendedor.getCodigo());
            stmt.setString(3, vendedor.getNombre());
            stmt.setString(4, vendedor.getApellido());
            stmt.setString(5, vendedor.getDepartamento_codigo());
            stmt.setString(6, vendedor.getCargo());
            stmt.setInt(7, vendedor.getVentas_mensuales());
            stmt.setInt(8, vendedor.getVentas_anuales());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error inserting vendor: " + ex.getMessage());
            return false;
        }
    }

    public Vendedor getVendedorById(int id) {
        String sql = "SELECT * FROM vendedor WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vendedor(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("departamento_codigo"),
                    rs.getString("cargo"),
                    rs.getInt("ventas_mensuales"),
                    rs.getInt("ventas_anuales")
                );
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving vendor: " + ex.getMessage());
        }
        return null;
    }

    public List<Vendedor> getAllVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vendedores.add(new Vendedor(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento_codigo"),
                        rs.getString("cargo"),
                        rs.getInt("ventas_mensuales"),
                        rs.getInt("ventas_anuales")
                ));
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving vendors: " + ex.getMessage());
        }
        return vendedores;
    }

    public boolean updateVendedor(Vendedor vendedor) {
        String sql = "UPDATE vendedor SET codigo = ?, nombre = ?, apellido = ?, departamento_codigo = ?, cargo = ?, " +
                     "ventas_mensuales = ?, ventas_anuales = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vendedor.getCodigo());
            stmt.setString(2, vendedor.getNombre());
            stmt.setString(3, vendedor.getApellido());
            stmt.setString(4, vendedor.getDepartamento_codigo());
            stmt.setString(5, vendedor.getCargo());
            stmt.setInt(6, vendedor.getVentas_mensuales());
            stmt.setInt(7, vendedor.getVentas_anuales());
            stmt.setInt(8, vendedor.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error updating vendor: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteVendedor(int id) {
        String sql = "DELETE FROM vendedor WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error deleting vendor: " + ex.getMessage());
            return false;
        }
    }
}