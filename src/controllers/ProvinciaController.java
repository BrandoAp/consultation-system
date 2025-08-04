package controllers;

import persistence.Provincia_DTO;
import modules.Provincia;

import java.util.List;

public class ProvinciaController {
    private Provincia_DTO provinciaDTO;

    public ProvinciaController(){
        this.provinciaDTO = new Provincia_DTO();
    }

    public void createProvincia(String codigo, String descripcion) {
        Provincia provincia = new Provincia(codigo, descripcion);
        if(provinciaDTO.insertProvincia(provincia)) {
            System.out.println("Provincia creada exitosamente.");
        } else {
            System.out.println("Error al crear la provincia.");
        }
    }

    public Provincia viewProvincia(String codigo) {
        return provinciaDTO.getProvinciaByCode(codigo);
    }

    public List<Provincia> listProvincias() {
        return provinciaDTO.getAllProvincias();
    }

    public void updateProvincia(int id, String codigo, String descripcion) {
        Provincia provincia = new Provincia(id, codigo, descripcion);
        if(provinciaDTO.updateProvincia(provincia)) {
            System.out.println("Provincia actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la provincia.");
        }
    }

    public void deleteProvincia(String codigo) {
        if(provinciaDTO.deleteProvincia(codigo)) {
            System.out.println("Provincia eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la provincia.");
        }
    }
}
