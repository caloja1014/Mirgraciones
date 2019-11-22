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

/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLAdminController implements Initializable {

    @FXML
    private AnchorPane view_control;
    @FXML
    private Label lbl_titulo;
    @FXML
    private Label lbl_emp;
    @FXML
    private Label lbl_puesto;
    @FXML
    private ImageView img_emp;
    @FXML
    private ImageView img_puesto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
