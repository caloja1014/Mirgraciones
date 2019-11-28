/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import static migraciones.Migraciones.bd;

/**
 *
 * @author nicoleagila
 */
public class AgenciaMigratoria {
    public static PriorityQueue<Ticket> turnos = new PriorityQueue<>((Ticket t1, Ticket t2) -> (t2.getPrioridad()-t1.getPrioridad()));
    
    public static HashMap<String,Empleado>empleados=new HashMap<>();
    
    public static HashMap<Puesto,Empleado> puestosAsignadosEmpl=new HashMap<>();
    
    public static ArrayList<Puesto> puestosDisponibles=new ArrayList<>();
    
    public static void cargarListas(){
        try (Statement st = bd.createStatement()) {
            String query = "SELECT * FROM empleado";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");

                empleados.put(cedula,new Empleado(nombre,cedula,estado));
            }
            
        } catch (Exception e) {
          System.err.println("Error al cargar los datos! "+e);
        }
        
        try (Statement st = bd.createStatement()) {
            String query = "SELECT * FROM puesto";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                int id = rs.getInt("id");
                String estado = rs.getString("estado");
                puestosDisponibles.add(new Puesto(id,estado));
            }
            
        } catch (Exception e) {
          System.err.println("Error al cargar los datos! "+e);
        }
        
        
    }
/**
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
*/
}
