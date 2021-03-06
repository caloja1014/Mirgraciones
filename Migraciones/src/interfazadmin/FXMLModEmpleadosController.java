/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static migraciones.Migraciones.bd;
import static migraciones.Migraciones.ventanita;
import static modelo.AgenciaMigratoria.empleados;
import static modelo.AgenciaMigratoria.puestosAsignadosEmpl;
import static modelo.AgenciaMigratoria.puestosDisponibles;
import modelo.Empleado;
import modelo.EstadoDisponibilidad;
import modelo.Puesto;

/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLModEmpleadosController implements Initializable {

    @FXML
    private AnchorPane view_editemp;
    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_info;
    @FXML
    private Label lbl_estado;
    @FXML
    private ImageView bt_editar;
    @FXML
    private ComboBox<EstadoDisponibilidad> cb_estados;
    
    public static HashMap<String,Empleado> nuevosEmpleados=empleados;
    
    protected static String emp_mod;
    @FXML
    private ImageView bt_refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarEstados();
    }    

    @FXML
    private void modificarInfo(MouseEvent event) throws SQLException {
        if(cb_estados.getValue()!=null){
            String query = "UPDATE empleado set estado= "+"\""+cb_estados.getValue()+"\" where cedula = \""+emp_mod+"\";";
            System.out.println(query);
            PreparedStatement pst = bd.prepareStatement(query);
            pst.execute();
            nuevosEmpleados.get(emp_mod).setEstado(cb_estados.getValue().name());
        }
        ventanita.close();
    }
    
    private void agregarEstados(){
        ObservableList<EstadoDisponibilidad> estados= observableArrayList();
        estados.add(EstadoDisponibilidad.Ausente);
        estados.add(EstadoDisponibilidad.Disponible);
        cb_estados.setItems(estados);
    }

    @FXML
    private void reasignarPuesto(MouseEvent event) throws SQLException {
        for (Iterator<Map.Entry<Puesto, Empleado>> it = puestosAsignadosEmpl.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Puesto, Empleado> entry = it.next();
            System.out.println(entry);
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            if(entry.getValue().equals(nuevosEmpleados.get(emp_mod))){
                puestosAsignadosEmpl.remove(entry.getKey());
                puestosAsignadosEmpl.put(puestosDisponibles.get(puestosDisponibles.size()-1), nuevosEmpleados.get(emp_mod));
                puestosDisponibles.remove(puestosDisponibles.size()-1);
                puestosDisponibles.add(entry.getKey());
            }
        }
    }
    
}
