/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author nicoleagila
 */
public class Ticket {
    private int id;
    //private Migrante usuario;
    private int prioridad;
    private static int IDT=0;

    public Ticket(int prioridad) {
        this.id=IDT++;
        //this.usuario = usuario;
        this.prioridad=prioridad;
    }

    public int getId() {
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
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Ticket)){
            return false;
        }
        Ticket other = (Ticket) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
