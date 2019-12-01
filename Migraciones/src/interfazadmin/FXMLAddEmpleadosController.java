/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazadmin;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static migraciones.Migraciones.bd;
import static migraciones.Migraciones.stLogin;
import static migraciones.Migraciones.ventanita;
import modelo.Empleado;

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
    private TableView<Empleado> table_empleados;
    @FXML
    private TableColumn col_ced;
    @FXML
    private TableColumn col_nom;
    @FXML
    private  TableColumn col_estado;
    @FXML
    private ImageView img_empleado;
    @FXML
    private ImageView bt_nuevo;
    @FXML
    private ImageView bt_editar;
    @FXML
    private ImageView bt_back;
    
    protected static ObservableList<Empleado> datos = observableArrayList();
    private static boolean ejecutado = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if(ejecutado == false) llenarTabla();
       else{
            actualizarDatos();
       }
       ejecutado = true;
        
    }    
    
    public void llenarTabla(){
        try (Statement st = bd.createStatement()) {
            String query = "SELECT * FROM empleado";
            try(ResultSet rs = st.executeQuery(query)){
             while (rs.next()){
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");

                datos.add(new Empleado(nombre,cedula,estado));
            }
            setDatos();   
            } catch(Exception e){
            }
        } catch (Exception e) {
          System.out.println("Error al cargar los datos! "+e);
        }
        
    }
    
    @FXML
    private void atras(MouseEvent event) throws IOException {
        Parent rootAdmin = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLAdmin.fxml"));
        stLogin.setScene(new Scene(rootAdmin));
        stLogin.show(); 
    }

    @FXML
    private void toAgregarEmpleado(MouseEvent event) throws IOException {
        Parent rootForm = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLFormEmpleados.fxml"));
        ventanita.setScene(new Scene(rootForm));
        ventanita.show();
       new Thread (() -> {
            try {
                while(ventanita.isShowing()){
                    Thread.sleep(500);
                }
                Platform.runLater(() -> {
                    actualizarDatos();
                });
                System.out.println("Refrescar");
                
            }catch (InterruptedException ex) {
                System.out.println(ex);
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    @FXML
    private void toEditarInfoEmpleado(MouseEvent event) throws IOException {
        FXMLModEmpleadosController.emp_mod = (String) table_empleados.getSelectionModel().getSelectedItem().getCedula();
        Parent rootMod = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLModEmpleados.fxml"));
        ventanita.setScene(new Scene(rootMod));
        ventanita.show();  
        new Thread (() -> {
            try {
                while(ventanita.isShowing()){
                    Thread.sleep(500);
                }
                Platform.runLater(() -> {
                    actualizarDatos();
                });
                System.out.println("Refrescar");
                
            }catch (InterruptedException ex) {
                System.out.println(ex);
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    protected void actualizarDatos(){
            datos.removeAll(datos);
            llenarTabla();
    }
    private void setDatos(){
        col_ced.setCellValueFactory(new PropertyValueFactory<>("cedula"));   
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        table_empleados.setItems(datos);
        table_empleados.refresh();
        
    }

}
