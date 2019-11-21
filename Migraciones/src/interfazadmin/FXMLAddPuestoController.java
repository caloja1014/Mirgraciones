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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLAddPuestoController implements Initializable {

    @FXML
    private AnchorPane view_puesto;
    @FXML
    private Label lbl_titulo;
    @FXML
    private ImageView img_puesto;
    @FXML
    private ImageView bt_add;
    @FXML
    private ImageView bt_back;
    @FXML
    private FlowPane pane_puestos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
