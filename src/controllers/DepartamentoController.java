package controllers;

import persistence.Departamento_DTO;
import modules.Departamento;
import java.util.List;

public class DepartamentoController {
    private Departamento_DTO departamento_dto;

    public DepartamentoController(){
        this.departamento_dto = new Departamento_DTO();
    }

    public void createDepartamento(int id, String codigo, String descripcion){
        Departamento departamento = new Departamento(id, codigo, descripcion);
        if(departamento_dto.insertDepto(departamento)) {
            System.out.println("Departamento created successfully.");
        } else {
            System.out.println("Error creating departamento.");
        }
    }

    public void viewDepto(int id){
        Departamento departamento = departamento_dto.getDeptoById(id);
        if(departamento != null) {
            System.out.println("ID: " + departamento.getId());
            System.out.println("Codigo: " + departamento.getCodigo());
            System.out.println("Descripcion: " + departamento.getDescripcion());
        } else {
            System.out.println("Departamento not found.");
        }
    }

    public void listDepto(){
        List<Departamento> departamentos = departamento_dto.getAllDepartamentos();
        if (departamentos != null) {
            for (Departamento departamento : departamentos) {
                System.out.println("ID: " + departamento.getId());
                System.out.println("Codigo: " + departamento.getCodigo());
                System.out.println("Descripcion: " + departamento.getDescripcion());
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("No departamentos found.");
        }
    }

    public void updateDepartamento(int id, String codigo, String descripcion){
        Departamento departamento = new Departamento(id, codigo, descripcion);
        if(departamento_dto.updateDepto(departamento)) {
            System.out.println("Departamento updated successfully.");
        } else {
            System.out.println("Error updating departamento.");
        }
    }

    public void deleteDepto(int id){
        if(departamento_dto.deleteDepto(id)) {
            System.out.println("Departamento deleted successfully.");
        } else {
            System.out.println("Error deleting departamento.");
        }
    }
}
