/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazempleado;

import interfazadmin.FXMLModEmpleadosController;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static migraciones.Migraciones.bd;
import static migraciones.Migraciones.stEmpleado;
import static migraciones.Migraciones.ventanita;
import modelo.AgenciaMigratoria;
import modelo.Migrante;
import modelo.Puesto;
import modelo.Registro;
import migraciones.Migraciones;
/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLEmpleadoController implements Initializable {

    @FXML
    private AnchorPane view_migrantes;
    @FXML
    private Label lbl_titulo;
    @FXML
    private ImageView img_mig;
    @FXML
    private TableView<Registro> table_regi_mig;
    @FXML
    private TableColumn col_noreg;
    @FXML
    private TableColumn<Registro,String> col_ced;
    @FXML
    private TableColumn<Registro,String> col_nombreap;
    @FXML
    private TableColumn<Registro,String> col_sexo;
    @FXML
    private TableColumn<Registro,Integer> col_anionac;
    @FXML
    private TableColumn<Registro,Integer> col_edad;
    @FXML
    private TableColumn<Registro,String> col_nacionalidad;
    @FXML
    private TableColumn<Registro,String> col_paisresid;
    @FXML
    private TableColumn<Registro,String> col_clasemig;
    @FXML
    private TableColumn col_tipomov;
    @FXML
    private TableColumn col_viatrans;
    @FXML
    private TableColumn col_paisdest;
    @FXML
    private TableColumn col_tiempoesta;
    @FXML
    private TableColumn col_fsalida;
    @FXML
    private TableColumn col_fentrada;
    @FXML
    private ImageView bt_search;
    @FXML
    private ImageView bt_add;
    @FXML
    private ImageView bt_edit;
    @FXML
    private ImageView bt_delete;
    @FXML
    private TextField txt_cedula;
    @FXML
    private Label lbl_cedula;
   public static Integer i;
    protected static ObservableList<Registro> datos = observableArrayList();
    @FXML
    private ComboBox<Puesto> puestosDisponibles;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       actualizar();
    }
    
    public void llenarTabla(){
        try (Statement st = bd.createStatement()) {
            String query = "SELECT * FROM migrante m join registro r on (m.cedula=r.migrante) where estado =\"disponible\" ";
            try(ResultSet rs = st.executeQuery(query)){
                while (rs.next()){
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String sexo = rs.getString("sexo");
                int anio_nac = rs.getInt("anio_nac");
                int edad = rs.getInt("edad");
                String nacionalidad = rs.getString("nacionalidad");
                String pais_residencia = rs.getString("pais_res");
                String clase_migratoria = rs.getString("clase_migratoria");
                
                int id = rs.getInt("id");
                String tipo_movilizacion = rs.getString("tipo_movilizacion");
                String via_transporte = rs.getString("via_transporte");
                String pais_dest = rs.getString("pais_dest");
                String tiempo_estadia = rs.getString("tiempo_estadia");
                Date fecha_registro = rs.getDate("fecha_salida");
                Date fecha_salida = rs.getDate("fecha_salida");
                Date fecha_regreso = rs.getDate("fecha_regreso");
                String estado = rs.getString("estado");

                datos.add(new Registro(new Migrante(cedula,nombre,sexo,anio_nac,edad,nacionalidad,clase_migratoria),id,tipo_movilizacion,via_transporte,fecha_registro,tiempo_estadia,fecha_salida,fecha_regreso,pais_dest,estado,pais_residencia));
            }
            }catch(Exception e){
                
            }

            
            setDatos();
            
        } catch (Exception e) {
          System.out.println("Error al cargar los datos! "+e);
        }
        
    }
    
    private void setDatos(){
        col_noreg.setCellValueFactory(new PropertyValueFactory<>("id"));   
        col_ced.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMigrante().getCedula()));
        col_nombreap.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMigrante().getNombre()));
        col_sexo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMigrante().getSexo()));
        col_anionac.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMigrante().getAnio_nac()).asObject());
        col_edad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMigrante().getEdad()).asObject());
        col_nacionalidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMigrante().getNacionalidad()));
        col_paisresid.setCellValueFactory(new PropertyValueFactory<>("pais_residencia"));
        col_clasemig.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMigrante().getClase_migratoria()));
        col_tipomov.setCellValueFactory(new PropertyValueFactory<>("tipo_movilizacion"));
        col_viatrans.setCellValueFactory(new PropertyValueFactory<>("via_transporte"));
        col_paisdest.setCellValueFactory(new PropertyValueFactory<>("pais_dest"));
        col_tiempoesta.setCellValueFactory(new PropertyValueFactory<>("tiempo_estadia"));
        col_fsalida.setCellValueFactory(new PropertyValueFactory<>("fecha_salida"));
        col_fentrada.setCellValueFactory(new PropertyValueFactory<>("fecha_regreso"));
        
        table_regi_mig.setItems(datos);
        table_regi_mig.refresh();
        
    }

    @FXML
    private void buscarMig(MouseEvent event) throws IOException {
       datos.removeAll(datos);
       try (Statement st = bd.createStatement()) {
            String query = "SELECT * FROM migrante m join registro r on (m.cedula=r.migrante) where estado =\"disponible\" and m.cedula =\""+txt_cedula.getText()+"\"";
            try(ResultSet rs = st.executeQuery(query)){
                while (rs.next()){
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String sexo = rs.getString("sexo");
                int anio_nac = rs.getInt("anio_nac");
                int edad = rs.getInt("edad");
                String nacionalidad = rs.getString("nacionalidad");
                String pais_residencia = rs.getString("pais_res");
                String clase_migratoria = rs.getString("clase_migratoria");
                
                int id = rs.getInt("id");
                String tipo_movilizacion = rs.getString("tipo_movilizacion");
                String via_transporte = rs.getString("via_transporte");
                String pais_dest = rs.getString("pais_dest");
                String tiempo_estadia = rs.getString("tiempo_estadia");
                Date fecha_registro = rs.getDate("fecha_salida");
                Date fecha_salida = rs.getDate("fecha_salida");
                Date fecha_regreso = rs.getDate("fecha_regreso");
                String estado = rs.getString("estado");

                datos.add(new Registro(new Migrante(cedula,nombre,sexo,anio_nac,edad,nacionalidad,clase_migratoria),id,tipo_movilizacion,via_transporte,fecha_registro,tiempo_estadia,fecha_salida,fecha_regreso,pais_dest,estado,pais_residencia));
            }
            setDatos();
            
            }catch(Exception e){
                
            }

            
        } catch (Exception e) {
          System.out.println("Error al cargar los datos! "+e);
        } 
    }

    @FXML
    private void addReg(MouseEvent event) throws IOException {
        FXMLModRegistroController.regiMod=(Registro) table_regi_mig.getSelectionModel().getSelectedItem();
        if(table_regi_mig.getSelectionModel().getSelectedItem()!=null){
            FXMLModRegistroController.modo="nuevoR";
        } else {
            FXMLModRegistroController.modo="nuevoM";
        }
        Parent rootGEmpleados = FXMLLoader.load(getClass().getResource("/interfazempleado/FXMLModRegistro.fxml"));
        ventanita.setScene(new Scene(rootGEmpleados));
        ventanita.show();
        new Thread(() -> {
            while(ventanita.isShowing()){
            }
            actualizar();
        }).start();
        
        
    }

    @FXML
    private void modificarReg(MouseEvent event) throws IOException {
        FXMLModRegistroController.modo="modificarR";
        FXMLModRegistroController.regiMod=(Registro) table_regi_mig.getSelectionModel().getSelectedItem();
        Parent rootGEmpleados = FXMLLoader.load(getClass().getResource("/interfazempleado/FXMLModRegistro.fxml"));
        ventanita.setScene(new Scene(rootGEmpleados));
        ventanita.show();
        new Thread(() -> {
            while(ventanita.isShowing()){}
            actualizar();
        }).start();
    }

    @FXML
    private void borrarReg(MouseEvent event) throws SQLException {
        int id_registro = (int) table_regi_mig.getSelectionModel().getSelectedItem().getId();
        String query = "UPDATE registro set estado= "+"\"no disponible\" where id = \""+id_registro+"\";";
            System.out.println(query);
            PreparedStatement pst = bd.prepareStatement(query);
            pst.execute();
            datos.remove(table_regi_mig.getSelectionModel().getSelectedItem());
            actualizar();
    }
    private void actualizar(){
        datos.clear();
        llenarTabla();
         ObservableList<Puesto> items = FXCollections.observableArrayList();
        for(Puesto p: AgenciaMigratoria.puestosDisponibles){
            items.add(p);
        }
        puestosDisponibles.setItems(items);
        System.out.println("actualizar");
    }

    @FXML
    private void clicar(ActionEvent event) {
        i=puestosDisponibles.getValue().getId();
    }
}
