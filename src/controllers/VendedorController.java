package controllers;

import modules.Vendedor;
import persistence.Vendedor_DTO;
import java.util.List;

public class VendedorController {
    private Vendedor_DTO vendedor_dto;

    public VendedorController() {
        this.vendedor_dto = new Vendedor_DTO();
    }

    public void createVendedor(String codigo, String nombre, String apellido, String departamento, String cargo, int ventas_mensuales, int ventas_anuales){
        Vendedor vendedor = new Vendedor(codigo, nombre, apellido, departamento, cargo, ventas_mensuales, ventas_anuales);
        if(vendedor_dto.insertVendedor(vendedor)) {
            System.out.println("Vendedor created successfully");
        } else {
            System.out.println("Error creating vendedor");
        }
    }

    public Vendedor viewVendedorByCode(String codigo){
        return vendedor_dto.getVendedorByCode(codigo);
    }
    public List<Vendedor> getVendedores(){
        return  vendedor_dto.getAllVendedores();
    }

    public void updateVendedor(String codigo, String nombre, String apellido, String departamento, String cargo, int ventas_mensuales, int ventas_anuales){
        Vendedor vendedor = new Vendedor(codigo, nombre, apellido, departamento, cargo, ventas_mensuales, ventas_anuales);
        if(vendedor_dto.updateVendedor(vendedor)) {
            System.out.println("Vendedor updated successfully");
        } else {
            System.out.println("Error updating vendedor");
        }
    }

    public void deleteVendedor(String codigo){
        if(vendedor_dto.deleteVendedor(codigo)) {
            System.out.println("Vendedor deleted successfully");
        } else {
            System.out.println("Error deleting vendedor");
        }
    }
}
