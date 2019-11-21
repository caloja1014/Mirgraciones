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
    private Empleado empleado;
    private int id;
    private boolean disponible;
    private static int IDP=0;

    public Puesto() {
        this.id = IDP++;
        this.disponible=true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisponible(boolean disponible) {
        if(disponible) FXMLTurnosController.actualizarPuesto(id);
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public boolean isDisponible() {
        return disponible;
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

