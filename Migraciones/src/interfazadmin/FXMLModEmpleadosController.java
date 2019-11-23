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
import static modelo.AgenciaMigratoria.empleados;
import static modelo.AgenciaMigratoria.puestosDisponibles;
import modelo.Empleado;
import static modelo.AgenciaMigratoria.puestosAsignadosEmpl;

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
    private Label lbl_puesto;
    @FXML
    private TextField txt_puesto;
    @FXML
    private ImageView bt_editar;
    @FXML
    private ComboBox<String> cb_estados;
    
    public static HashMap<String,Empleado> nuevosEmpleados=empleados;
    
    protected static String emp_mod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarEstados();
    }    

    @FXML
    private void modificarInfo(MouseEvent event) throws SQLException {
        if(!cb_estados.getValue().isEmpty()){
            String query = "UPDATE empleado set estado= "+"\""+cb_estados.getValue()+"\" where cedula = \""+emp_mod+"\";";
            System.out.println(query);
            PreparedStatement pst = bd.prepareStatement(query);
            pst.execute();
            nuevosEmpleados.get(emp_mod).setEstado(cb_estados.getValue());
        } if(!txt_puesto.getText().isEmpty()) {
            puestosAsignadosEmpl.put(puestosDisponibles.get(0),nuevosEmpleados.get(emp_mod));
            puestosDisponibles.remove(0);
        }   
    }
    
    private void agregarEstados(){
        ObservableList<String> estados= observableArrayList();
        estados.add("Disponible");
        estados.add("Ausente");
        cb_estados.setItems(estados);
    }
    
}
