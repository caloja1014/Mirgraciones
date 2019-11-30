/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author nicoleagila
 */
public class Migrante {
    private String cedula;
    private String nombre;
    private String sexo;
    private int anio_nac;
    private int edad;
    private String nacionalidad;

    private String clase_migratoria;

    public Migrante(String cedula,String nombre, String sexo, int anio_nac, int edad, String nacionalidad, String clase_mig) {
        this.cedula = cedula;
        this.nombre=nombre;
        this.sexo = sexo;
        this.anio_nac = anio_nac;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.clase_migratoria = clase_mig;
    }

    public Migrante(String cedula){
        this.cedula=cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getSexo() {
        return sexo;
    }
    
    public String getClase_migratoria() {
        return clase_migratoria;
    }

    
    public int getAnio_nac() {
        return anio_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
 


    public int getEdad() {
        return edad;
    }

    public void setClase_migratoria(String clase_mig) {
        this.clase_migratoria = clase_mig;
    }

    
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAnio_nac(int anio_nac) {
        this.anio_nac = anio_nac;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.cedula);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Migrante)){
            return false;
        }
        Migrante other = (Migrante) obj;
        if (this.cedula != other.cedula) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Migrante{" + "cedula=" + cedula + ", nombre=" + nombre + ", sexo=" + sexo + ", anio_nac=" + anio_nac + ", edad=" + edad + ", nacionalidad=" + nacionalidad  + ", clase_migratoria=" + clase_migratoria + '}';
    }
    
    
    
    
}
