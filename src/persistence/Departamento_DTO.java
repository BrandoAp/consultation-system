package persistence;

import modules.Departamento;
import connection.connection;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Departamento_DTO {

    public boolean insertDepto(Departamento departamento){
        String sql = "INSERT INTO departamento (codigo, descripcion) VALUES (?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, departamento.getCodigo());
            stmt.setString(2, departamento.getDescripcion());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error inserting department: " + ex.getMessage());
            return false;
        }
    }

    public Departamento getDeptoByCodigo(String codigo){
        String sql = "SELECT * FROM departamento WHERE codigo = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Departamento(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving department: " + ex.getMessage());
        }
        return null;
    }

    public List<Departamento> getAllDepartamentos() {
        String sql = "SELECT * FROM departamento";
        List<Departamento> departamentos = new ArrayList<>();

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Departamento departamento = new Departamento(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("descripcion")
                );
                departamentos.add(departamento);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving departments: " + ex.getMessage());
        }
        return departamentos;
    }

    public boolean updateDepto(Departamento departamento) {
        String sql = "UPDATE departamento SET codigo = ?, descripcion = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, departamento.getCodigo());
            stmt.setString(2, departamento.getDescripcion());
            stmt.setInt(3, departamento.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error updating department: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteDepto(String codigo) {
        String sql = "DELETE FROM departamento WHERE codigo = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error deleting department: " + ex.getMessage());
            return false;
        }
    }
}
