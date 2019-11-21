/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicoleagila
 */
public class AgenciaMigratoria {
    public static PriorityQueue<Ticket> turnos = new PriorityQueue<>((Ticket t1, Ticket t2) -> (t2.getPrioridad()-t1.getPrioridad()));
    static ArrayList<Registro> registros;
    public static ArrayList<Puesto> puestos=new ArrayList<>();
    
public static void serializar(){
    
        try (FileOutputStream fileOut = new FileOutputStream("registros.ser")) {
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(registros);
                out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AgenciaMigratoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AgenciaMigratoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void desSeralizar(){
        try{
        FileInputStream fileIn = new FileInputStream("registros.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        registros = (ArrayList) in.readObject();
        in.close();
        fileIn.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(AgenciaMigratoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(AgenciaMigratoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
