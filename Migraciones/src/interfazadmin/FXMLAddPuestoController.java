/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import static migraciones.Migraciones.bd;
import static migraciones.Migraciones.stLogin;
import static migraciones.Migraciones.stPuesto;
import static modelo.AgenciaMigratoria.puestosDisponibles;
import modelo.Puesto;

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
    
    public static ArrayList<Puesto> puestoSinEmpleado=puestosDisponibles;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarPane();
    }    

    @FXML
    private void agregarPuestoNuevo(MouseEvent event) throws SQLException {
        String query = "INSERT INTO puesto values ();";
        PreparedStatement pst = bd.prepareStatement(query);
        pst.execute();
        actualizarPane();
        Puesto nuevo = new Puesto("libre");
        puestoSinEmpleado.add(nuevo);
        
    }

    @FXML
    private void atras(MouseEvent event) throws IOException {
        Parent rootAdmin = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLAdmin.fxml"));
        stLogin.setScene(new Scene(rootAdmin));
        stLogin.show();
        stPuesto.close();
    }
    
    private void llenarPane(){
        try (Statement st = bd.createStatement()) {
            String query = "SELECT * FROM puesto";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                VBox caja = new VBox();
                int id = rs.getInt("id");
                Label nombre = new Label("Puesto "+Integer.toString(id));
                caja.getChildren().addAll(nombre);
                caja.alignmentProperty();
                caja.setPadding(new Insets(5,5,5,5));
                pane_puestos.getChildren().add(caja);
            }
            
        } catch (Exception e) {
          System.err.println("Error al cargar los datos! "+e);
        }
    }
    
    private void actualizarPane(){
        pane_puestos.getChildren().clear();
        llenarPane();
    }
    
}
