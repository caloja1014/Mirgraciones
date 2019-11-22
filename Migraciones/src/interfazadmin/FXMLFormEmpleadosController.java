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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
