package persistence;

import modules.Cliente;
import connection.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente_DTO {

    public boolean insertClient (Cliente cliente){
        String sql = "INSERT INTO cliente (id, cedula, nombre, apellido, direccion, telefono, provincia_codigo, compra_anual)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getCedula());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getApellido());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getTelefono());
            stmt.setString(7, cliente.getProvincia_codigo());
            stmt.setInt(8, cliente.getCompra_anual());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error inserting client: " + ex.getMessage());
            return false;
        }
    }

    public Cliente getClientById(int id){
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = connection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("provincia_codigo"),
                    rs.getInt("compra_anual")
                );
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving client: " + ex.getMessage());
        }
        return null;
    }

    public List<Cliente> getClients(){
        List<Cliente> list = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = connection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("provincia_codigo"),
                    rs.getInt("compra_anual")
                );
                list.add(cliente);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error retrieving clients: " + ex.getMessage());
        }
        return list;
    }

    public boolean updateClient(Cliente cliente){
        String sql = "UPDATE cliente SET cedula = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, provincia_codigo = ?, compra_anual = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getCedula());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getProvincia_codigo());
            stmt.setInt(7, cliente.getCompra_anual());
            stmt.setInt(8, cliente.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error updating client: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteClient(int id){
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = connection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error deleting client: " + ex.getMessage());
            return false;
        }
    }
}
