/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author nicoleagila
 */
public class Empleado {
    private String nombre;
    private String cedula;
    private String estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Empleado(String nombre, String cedula, String estado) {
        this.nombre = nombre;
        this.cedula=cedula;
        this.estado=estado;
    }

    public String getCedula() {
        return cedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
