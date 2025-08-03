package controllers;

import persistence.Cliente_DTO;
import modules.Cliente;

import java.util.List;

public class ClientController {
    private Cliente_DTO cliente;

    public ClientController() {
        this.cliente = new Cliente_DTO();
    }

    public void createClient(int id, String cedula, String nombre, String apellido, String direccion, String telefono, String provincia, int compra_anual) {
        Cliente clienteModel = new Cliente(id, cedula, nombre, apellido, direccion, telefono, provincia, compra_anual);
        if(cliente.insertClient(clienteModel)) {
            System.out.println("Cliente creado exitosamente.");
        } else {
            System.out.println("Error al crear el cliente.");
        }
    }

    public void viewClient(int id){
        Cliente clientModel = cliente.getClientById(id);
        if(clientModel != null) {
            System.out.println("ID: " + clientModel.getId());
            System.out.println("Cédula: " + clientModel.getCedula());
            System.out.println("Nombre: " + clientModel.getNombre());
            System.out.println("Apellido: " + clientModel.getApellido());
            System.out.println("Dirección: " + clientModel.getDireccion());
            System.out.println("Teléfono: " + clientModel.getTelefono());
            System.out.println("Provincia: " + clientModel.getProvincia_codigo());
            System.out.println("Compra Anual: " + clientModel.getCompra_anual());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void ListClients(){
        List<Cliente> clientes = cliente.getClients();
        System.out.println("Lista de Clientes:");
        for (Cliente clienteModel : clientes) {
            System.out.println("ID: " + clienteModel.getId());
            System.out.println("Cédula: " + clienteModel.getCedula());
            System.out.println("Nombre: " + clienteModel.getNombre());
            System.out.println("Apellido: " + clienteModel.getApellido());
            System.out.println("Dirección: " + clienteModel.getDireccion());
            System.out.println("Teléfono: " + clienteModel.getTelefono());
            System.out.println("Provincia: " + clienteModel.getProvincia_codigo());
            System.out.println("Compra Anual: " + clienteModel.getCompra_anual());
            System.out.println("-----------------------------");
        }
    }

    public void updateClient(int id, String cedula, String nombre, String apellido, String direccion, String telefono, String provincia, int compra_anual) {
        Cliente clienteModel = new Cliente(id, cedula, nombre, apellido, direccion, telefono , provincia, compra_anual);
        if(cliente.updateClient(clienteModel)) {
            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el cliente.");
        }
    }

    public void deleteClient(int id) {
        if(cliente.deleteClient(id)) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el cliente.");
        }
    }
}
