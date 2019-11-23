/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.layout.FlowPane;
import static migraciones.Migraciones.bd;
import static migraciones.Migraciones.stLogin;
import static migraciones.Migraciones.stPuesto;

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

    @FXML
    private void agregarPuestoNuevo(MouseEvent event) throws SQLException {
        String query = "INSERT INTO puesto values ();";
        PreparedStatement pst = bd.prepareStatement(query);
        pst.execute();
    }

    @FXML
    private void atras(MouseEvent event) throws IOException {
        Parent rootAdmin = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLAdmin.fxml"));
        stLogin.setScene(new Scene(rootAdmin));
        stLogin.show();
        stPuesto.close();
    }
    
}
