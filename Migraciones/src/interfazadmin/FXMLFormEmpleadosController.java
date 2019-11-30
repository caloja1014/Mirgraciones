/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import static interfazadmin.FXMLAddEmpleadosController.datos;
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
    @FXML
    private TextField txt_puesto;
    @FXML
    private ImageView bt_agregar;
    
    public static HashMap<String,Empleado> nuevosEmpleados=empleados;   
    

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
        pst.execute();
        Empleado nuevo= new Empleado (txt_nombre.getText(), txt_id.getText(), "activo");
        nuevosEmpleados.put(txt_id.getText(), nuevo);
        if(!txt_puesto.getText().isEmpty()) {            
            puestosAsignadosEmpl.put(puestosDisponibles.get(Integer.parseInt(txt_puesto.getText())), nuevo) ;
            String query1 = "INSERT INTO E_P_R (empleado, puesto) values ( "+"\""+txt_id.getText()+"\",\""+puestosDisponibles.get(Integer.parseInt(txt_puesto.getText())).getId()+"\");";
            System.out.println(query1);
            PreparedStatement pst1 = bd.prepareStatement(query1);
            pst1.execute();
        }
        ventanita.close();
    }
    
}
