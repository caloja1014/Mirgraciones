/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static migraciones.Migraciones.bd;
import modelo.Empleado;

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
    
    protected static Empleado emp_mod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> estados= new ArrayList<>();
        estados.add("Disponible");
        estados.add("Ausente");
        cb_estados = new ComboBox(FXCollections.observableArrayList(estados));
        
    }    

    @FXML
    private void modificarInfo(MouseEvent event) throws SQLException {
        if(cb_estados.getValue()!=null){
            String query = "UPDATE empleado set estado= "+"\""+cb_estados.getValue()+"\" where cedula = \""+emp_mod.getCedula()+"\");";
            System.out.println(query);
            PreparedStatement pst = bd.prepareStatement(query);
            pst.execute();
        } if(txt_puesto!=null) {
            
        }
        
        
    }
    
}
