/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author nicoleagila
 */
public class AgenciaMigratoria {
    PriorityQueue<Ticket> turnos = new PriorityQueue<>((Ticket t1, Ticket t2) -> (t1.getPrioridad()-t2.getPrioridad()));
    ArrayList<Registro> registros;
}
