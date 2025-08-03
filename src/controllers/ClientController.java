package controllers;

import persistence.Cliente_DTO;
import modules.Cliente;

import java.util.List;

public class ClientController {
    private Cliente_DTO cliente;

    public ClientController() {
        this.cliente = new Cliente_DTO();
    }

    public void createClient(String cedula, String nombre, String apellido, String direccion, String telefono, String provincia, int compra_anual) {
        Cliente clienteModel = new Cliente(cedula, nombre, apellido, direccion, telefono, provincia, compra_anual);
        if(cliente.insertClient(clienteModel)) {
            System.out.println("Cliente creado exitosamente.");
        } else {
            System.out.println("Error al crear el cliente.");
        }
    }

    public List<Cliente> viewAllClients() {
        return cliente.getClients();
    }

    public Cliente getClientByCedula(String cedula) {
        return cliente.getClientByCedula(cedula);
    }

    public void updateClient(String cedula, String nombre, String apellido, String direccion, String telefono, String provincia, int compra_anual) {
        Cliente clienteModel = new Cliente(cedula, nombre, apellido, direccion, telefono , provincia, compra_anual);
        if(cliente.updateClient(clienteModel)) {
            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el cliente.");
        }
    }

    public void deleteClient(String cedula) {
        if(cliente.deleteClient(cedula)) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el cliente.");
        }
    }
}
