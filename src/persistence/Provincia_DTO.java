package persistence;

import connection.connection;
import modules.Provincia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Provincia_DTO {

    public boolean insertProvincia(Provincia provincia) {
        String sql = "INSERT INTO provincia (id, codigo, descripcion) VALUES (?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, provincia.getId());
            stmt.setString(2, provincia.getCodigo());
            stmt.setString(3, provincia.getDescripcion());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error inserting province: " + ex.getMessage());
            return false;
        }
    }

    public Provincia getProvinciaById(int id) {
        String sql = "SELECT * FROM provincia WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Provincia(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving province: " + ex.getMessage());
        }
        return null;
    }

    public List<Provincia> getAllProvincias() {
        String sql = "SELECT * FROM provincia";
        List<Provincia> provincias = new ArrayList<>();

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Provincia provincia = new Provincia(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("descripcion")
                );
                provincias.add(provincia);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving provinces: " + ex.getMessage());
        }
        return provincias;
    }

    public boolean updateProvincia(Provincia provincia) {
        String sql = "UPDATE provincia SET codigo = ?, descripcion = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, provincia.getCodigo());
            stmt.setString(2, provincia.getDescripcion());
            stmt.setInt(3, provincia.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error updating province: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteProvincia(int id) {
        String sql = "DELETE FROM provincia WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error deleting province: " + ex.getMessage());
            return false;
        }
    }
}
