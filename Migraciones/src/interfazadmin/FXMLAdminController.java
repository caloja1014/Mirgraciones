/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static migraciones.Migraciones.stLogin;
import static migraciones.Migraciones.stPuesto;

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
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toVentanaEmpleados(MouseEvent event) throws IOException {
        Parent rootGEmpleados = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLAddEmpleados.fxml"));
        stLogin.setScene(new Scene(rootGEmpleados));
        stLogin.show();
    }

    @FXML
    private void toVentanaPuesto(MouseEvent event) throws IOException {
        Parent rootPuesto = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLAddPuesto.fxml"));
        stPuesto.setScene(new Scene(rootPuesto));
        stPuesto.show();        
        stLogin.close();
    }
    
}
