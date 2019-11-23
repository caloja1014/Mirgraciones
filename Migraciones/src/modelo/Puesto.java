/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import interfazturnos.FXMLTurnosController;
/**
 *
 * @author nicoleagila
 */
public class Puesto {
    private int id;
    private static int IDT=0;
    private String estado;

    public Puesto(int id, String estado) {
        this.id = id;
        this.estado=estado;
    }
    
    public Puesto(String estado){
        ++IDT;
        this.estado=estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisponible(String disponible) {
        if(disponible.equals("activo")) FXMLTurnosController.actualizarPuesto(id);
        this.estado = disponible;
    }

    public int getId() {
        return id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    public String getEstado() {
        return estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Puesto)){
            return false;
        }
        
        Puesto other = (Puesto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

}

