/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author nicoleagila
 */
public class Ticket {
    private String id;
    //private Migrante usuario;
    private  int prioridad;
    private static int IDT=0;
    private int contadorT=1;
    private int contadorD=1;
    private int contadorU=1;

    public Ticket(int prioridad) {
        ++IDT;
        generarTurnoPersona(prioridad);
        ++contadorT;
        ++contadorD;
        ++contadorU;
        //this.usuario = usuario;
        this.prioridad=prioridad;
    }

    public String getId() {
        return id;
    }

   /* public Migrante getUsuario() {
        return usuario;
    }*/

    public int getPrioridad() {
        return prioridad;
    }
/*
    public void setUsuario(Migrante usuario) {
        this.usuario = usuario;
    }

    public int setPrioridad(){
        if(usuario.getTipo().compareTo(TipoPersona.capEspeciales)==0){
            return 1;
        } else if (usuario.getTipo().compareTo(TipoPersona.tercEdad)==0){
            return 2;
        }else if (usuario.getTipo().compareTo(TipoPersona.normal)==0){
            return 3;
        }
        return 0;
    }
*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    public  void generarTurnoPersona(int prioridad){
        switch (prioridad) {
            case 3:
                this.id=String.valueOf(this.contadorT);
                
                break;
            case 2:
                this.id="B"+this.contadorD;
                
                break;
            case 1:
                this.id="C"+this.contadorU;
                
                break;
        }
    }
    
    
}
