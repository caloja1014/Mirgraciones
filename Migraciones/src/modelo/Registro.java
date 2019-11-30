/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author nicoleagila
 */
public class Registro implements Serializable{
    private Migrante migrante;
    private int id;
    private String tipo_movilizacion;
    private String  pais_dest;
    private String via_transporte;
    private Date fecha_registro;
    private int tiempo_estadia;
    private Date fecha_salida;
    private Date fecha_regreso;
    private String estado;

    public Registro(Migrante migrante, int id, String tipo_movilizacion, String via_transporte, Date fecha_registro, int tiempo_estadia, Date fecha_salida, Date fecha_regreso,String pais_dest, String estado) {
        this.migrante = migrante;
        this.id = id;
        this.tipo_movilizacion = tipo_movilizacion;
        this.via_transporte = via_transporte;
        this.fecha_registro = fecha_registro;
        this.tiempo_estadia = tiempo_estadia;
        this.fecha_salida = fecha_salida;
        this.fecha_regreso = fecha_regreso;
        this.pais_dest=pais_dest;
        this.estado = estado;
    }

    public Migrante getMigrante() {
        return migrante;
    }

    public int getId() {
        return id;
    }

    public String getTipo_movilizacion() {
        return tipo_movilizacion;
    }

    public String getVia_transporte() {
        return via_transporte;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public int getTiempo_estadia() {
        return tiempo_estadia;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public Date getFecha_regreso() {
        return fecha_regreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setMigrante(Migrante migrante) {
        this.migrante = migrante;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo_movilizacion(String tipo_movilizacion) {
        this.tipo_movilizacion = tipo_movilizacion;
    }

    public void setVia_transporte(String via_transporte) {
        this.via_transporte = via_transporte;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public void setTiempo_estadia(int tiempo_estadia) {
        this.tiempo_estadia = tiempo_estadia;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public void setFecha_regreso(Date fecha_regreso) {
        this.fecha_regreso = fecha_regreso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
