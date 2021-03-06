/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import static interfazadmin.FXMLAddEmpleadosController.datos;
import static interfazadmin.FXMLModEmpleadosController.emp_mod;
import static interfazadmin.FXMLModEmpleadosController.nuevosEmpleados;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLFormEmpleadosController implements Initializable {

    @FXML
    private AnchorPane view_nuevoemp;
    @FXML
    private Label lbl_titulo;
    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_id;
    @FXML
    private Label lbl_puesto;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_id;
    private TextField txt_puesto;
    @FXML
    private ImageView bt_agregar;
    
    public static HashMap<String,Empleado> nuevosEmpleados=empleados;   
    @FXML
    private ImageView bt_puestoAl;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nuevoEmpleado(MouseEvent event) throws SQLException {
        System.out.println(puestosDisponibles);
        String query = "INSERT INTO empleado (cedula,nombre) values ( "+"\""+txt_id.getText()+"\",\""+txt_nombre.getText()+"\");";
        System.out.println(query);
        PreparedStatement pst = bd.prepareStatement(query);
        Empleado nuevo= new Empleado (txt_nombre.getText(), txt_id.getText(), "activo");
        nuevosEmpleados.put(txt_id.getText(), nuevo);
        ventanita.close();
    }

    @FXML
    private void asignarPuesto(MouseEvent event) throws SQLException {
        puestosAsignadosEmpl.put(puestosDisponibles.get(puestosDisponibles.size()-1), nuevosEmpleados.get(emp_mod)) ;
        puestosDisponibles.remove(puestosDisponibles.size()-1);
        System.out.println(puestosAsignadosEmpl);
    }
    
}
