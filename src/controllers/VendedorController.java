package controllers;

import modules.Vendedor;
import persistence.Vendedor_DTO;
import java.util.List;

public class VendedorController {
    private Vendedor_DTO vendedor_dto;

    public VendedorController() {
        this.vendedor_dto = new Vendedor_DTO();
    }

    public void createVendedor(int id, String codigo, String nombre, String apellido, String departamento, String cargo, int ventas_mensuales, int ventas_anuales){
        Vendedor vendedor = new Vendedor(id, codigo, nombre, apellido, departamento, cargo, ventas_mensuales, ventas_anuales);
        if(vendedor_dto.insertVendedor(vendedor)) {
            System.out.println("Vendedor created successfully");
        } else {
            System.out.println("Error creating vendedor");
        }
    }

    public void viewVendedor(int id){
        Vendedor vendedor = vendedor_dto.getVendedorById(id);
        if(vendedor != null) {
            System.out.println("Vendedor Details:");
            System.out.println("ID: " + vendedor.getId());
            System.out.println("Codigo: " + vendedor.getCodigo());
            System.out.println("Nombre: " + vendedor.getNombre());
            System.out.println("Apellido: " + vendedor.getApellido());
            System.out.println("Departamento: " + vendedor.getDepartamento_codigo());
            System.out.println("Cargo: " + vendedor.getCargo());
            System.out.println("Ventas Mensuales: " + vendedor.getVentas_mensuales());
            System.out.println("Ventas Anuales: " + vendedor.getVentas_anuales());
        } else {
            System.out.println("Vendedor not found");
        }
    }
    public void listVendedores(){
        List<Vendedor> vendedores = vendedor_dto.getAllVendedores();
        if(vendedores.isEmpty()) {
            System.out.println("No vendedores found");
        } else {
            System.out.println("List of Vendedores:");
            for(Vendedor vendedor : vendedores) {
                System.out.println("ID: " + vendedor.getId() + ", Codigo: " + vendedor.getCodigo() + ", Nombre: " + vendedor.getNombre() + ", Apellido: " + vendedor.getApellido());
            }
        }
    }

    public void updateVendedor(int id, String codigo, String nombre, String apellido, String departamento, String cargo, int ventas_mensuales, int ventas_anuales){
        Vendedor vendedor = new Vendedor(id, codigo, nombre, apellido, departamento, cargo, ventas_mensuales, ventas_anuales);
        if(vendedor_dto.updateVendedor(vendedor)) {
            System.out.println("Vendedor updated successfully");
        } else {
            System.out.println("Error updating vendedor");
        }
    }

    public void deleteVendedor(int id){
        if(vendedor_dto.deleteVendedor(id)) {
            System.out.println("Vendedor deleted successfully");
        } else {
            System.out.println("Error deleting vendedor");
        }
    }
}
