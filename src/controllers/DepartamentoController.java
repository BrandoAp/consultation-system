package controllers;

import persistence.Departamento_DTO;
import modules.Departamento;
import java.util.List;

public class DepartamentoController {
    private Departamento_DTO departamento_dto;

    public DepartamentoController(){
        this.departamento_dto = new Departamento_DTO();
    }

    public void createDepartamento(String codigo, String descripcion){
        Departamento departamento = new Departamento(codigo, descripcion);
        if(departamento_dto.insertDepto(departamento)) {
            System.out.println("Departamento created successfully.");
        } else {
            System.out.println("Error creating departamento.");
        }
    }

    public Departamento viewDepto(String codigo){
       return departamento_dto.getDeptoByCodigo(codigo);
    }

    public List<Departamento> listDepto(){
        return  departamento_dto.getAllDepartamentos();
    }

    public void updateDepartamento(String codigo, String descripcion){
        Departamento departamento = new Departamento(codigo, descripcion);
        if(departamento_dto.updateDepto(departamento)) {
            System.out.println("Departamento updated successfully.");
        } else {
            System.out.println("Error updating departamento.");
        }
    }

    public void deleteDepto(String codigo){
        if(departamento_dto.deleteDepto(codigo)) {
            System.out.println("Departamento deleted successfully.");
        } else {
            System.out.println("Error deleting departamento.");
        }
    }
}
