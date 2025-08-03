package controllers;

import persistence.Provincia_DTO;
import modules.Provincia;

import java.util.List;

public class ProvinciaController {
    private Provincia_DTO provinciaDTO;

    public ProvinciaController(){
        this.provinciaDTO = new Provincia_DTO();
    }

    public void createProvincia(int id, String codigo, String descripcion) {
        Provincia provincia = new Provincia(id, codigo, descripcion);
        if(provinciaDTO.insertProvincia(provincia)) {
            System.out.println("Provincia creada exitosamente.");
        } else {
            System.out.println("Error al crear la provincia.");
        }
    }

    public void viewProvincia(int id) {
        Provincia provincia = provinciaDTO.getProvinciaById(id);
        if(provincia != null) {
            System.out.println("ID: " + provincia.getId());
            System.out.println("Código: " + provincia.getCodigo());
            System.out.println("Descripción: " + provincia.getDescripcion());
        } else {
            System.out.println("Provincia no encontrada.");
        }
    }

    public void listProvincias() {
        List<Provincia> provincias = provinciaDTO.getAllProvincias();
        System.out.println("Lista de Provincias:");
        for (Provincia provincia : provincias) {
            System.out.println("ID: " + provincia.getId());
            System.out.println("Código: " + provincia.getCodigo());
            System.out.println("Descripción: " + provincia.getDescripcion());
            System.out.println("-----------------------------");
        }
    }

    public void updateProvincia(int id, String codigo, String descripcion) {
        Provincia provincia = new Provincia(id, codigo, descripcion);
        if(provinciaDTO.updateProvincia(provincia)) {
            System.out.println("Provincia actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la provincia.");
        }
    }

    public void deleteProvincia(int id) {
        if(provinciaDTO.deleteProvincia(id)) {
            System.out.println("Provincia eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la provincia.");
        }
    }
}
