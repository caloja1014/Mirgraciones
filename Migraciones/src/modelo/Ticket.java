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
public final class Ticket {
    private String id;
    //private Migrante usuario;
    private final  int prioridad;
    private static int IDT=0;
    private static int contadorT=1;
    private static int contadorD=1;
    private static int contadorU=1;

    public Ticket(int prioridad) {
        ++IDT;
        generarTurnoPersona(prioridad);
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
        return Objects.equals(this.id, other.id);
    }

    
    public  void generarTurnoPersona(int prioridad){
        switch (prioridad) {
            case 3:
                this.id="A"+Ticket.contadorT;
                ++contadorT; 
                break;
            case 2:
                this.id="B"+Ticket.contadorD;
                ++contadorD;
                break;
            case 1:
                this.id="C"+Ticket.contadorU;
                ++contadorU;
                break;
        }
    }
    
    
}
