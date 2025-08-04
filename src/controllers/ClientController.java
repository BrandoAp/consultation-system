package controllers;

import persistence.Cliente_DTO;
import modules.Cliente;

import java.util.List;

public class ClientController {
    private final Cliente_DTO clienteDTO;

    public ClientController() {
        this.clienteDTO = new Cliente_DTO();
    }

    // Validación de cédula
    private boolean validarCedula(String cedula) {
        return cedula != null && cedula.matches("\\d-\\d{4}-\\d{4}");
    }

    // Formateo de nombre/apellido
    private String formatearTexto(String texto) {
        if (texto == null) return null;
        String s = texto.trim().replaceAll("\\s+", "");
        return s.isEmpty() ? s : s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    // Validaciones comunes
    private boolean validarDatos(String cedula, String nombre, String apellido, String telefono, String provincia, String compraAnualStr) {
        if (!validarCedula(cedula)) {
            System.out.println("❌ Formato de cédula inválido. Debe ser d-dddd-dddd");
            return false;
        }
        nombre = formatearTexto(nombre);
        apellido = formatearTexto(apellido);
        if (nombre.isEmpty() || apellido.isEmpty()) {
            System.out.println("❌ Nombre o apellido no pueden estar vacíos");
            return false;
        }
        if (telefono == null || telefono.length() != 7) {
            System.out.println("❌ Teléfono debe tener exactamente 7 caracteres");
            return false;
        }
        if (provincia == null || provincia.length() != 2) {
            System.out.println("❌ Código de provincia debe tener 2 caracteres");
            return false;
        }
        try {
            Integer.parseInt(compraAnualStr);
        } catch (NumberFormatException ex) {
            System.out.println("❌ Compra anual debe ser un entero");
            return false;
        }
        return true;
    }

    public boolean createClient(String cedula, String nombre, String apellido,
                                String direccion, String telefono, String provincia, int compra_anual) {
        if (!validarDatos(cedula, nombre, apellido, telefono, provincia, String.valueOf(compra_anual))) {
            return false;
        }
        // Verificar duplicado
        if (clienteDTO.getClientByCedula(cedula) != null) {
            System.out.println("❌ Ya existe un cliente con esta cédula: " + cedula);
            return false;
        }
        Cliente cliente = new Cliente(cedula, nombre, apellido, direccion, telefono, provincia, compra_anual);
        boolean ok = clienteDTO.insertClient(cliente);
        System.out.println(ok ? "✅ Cliente creado exitosamente." : "❌ Error al crear el cliente.");
        return ok;
    }

    public List<Cliente> viewAllClients() {
        return clienteDTO.getClients();
    }

    public Cliente getClientByCedula(String cedula) {
        if (!validarCedula(cedula)) return null;
        return clienteDTO.getClientByCedula(cedula);
    }

    public boolean updateClient(String cedula, String nombre, String apellido,
                                String direccion, String telefono, String provincia, int compra_anual) {
        if (!validarDatos(cedula, nombre, apellido, telefono, provincia, String.valueOf(compra_anual))) {
            return false;
        }
        if (clienteDTO.getClientByCedula(cedula) == null) {
            System.out.println("❌ No existe cliente con cédula: " + cedula);
            return false;
        }
        Cliente cliente = new Cliente(cedula, nombre, apellido, direccion, telefono, provincia, compra_anual);
        boolean ok = clienteDTO.updateClient(cliente);
        System.out.println(ok ? "✅ Cliente actualizado exitosamente." : "❌ Error al actualizar el cliente.");
        return ok;
    }

    public boolean deleteClient(String cedula) {
        if (!validarCedula(cedula)) {
            return false;
        }
        if (clienteDTO.getClientByCedula(cedula) == null) {
            System.out.println("❌ No existe cliente con cédula: " + cedula);
            return false;
        }
        boolean ok = clienteDTO.deleteClient(cedula);
        System.out.println(ok ? "✅ Cliente eliminado exitosamente." : "❌ Error al eliminar el cliente.");
        return ok;
    }
}