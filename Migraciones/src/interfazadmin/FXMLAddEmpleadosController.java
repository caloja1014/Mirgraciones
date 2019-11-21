/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLAddEmpleadosController implements Initializable {

    @FXML
    private AnchorPane view_empleados;
    @FXML
    private Label lbl_titulo;
    @FXML
    private TableView<?> table_empleados;
    @FXML
    private TableColumn<?, ?> col_ced;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_estado;
    @FXML
    private ImageView img_empleado;
    @FXML
    private ImageView bt_nuevo;
    @FXML
    private ImageView bt_editar;
    @FXML
    private ImageView bt_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
