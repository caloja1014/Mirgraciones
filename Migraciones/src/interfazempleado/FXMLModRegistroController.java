/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazempleado;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static migraciones.Migraciones.bd;
import static migraciones.Migraciones.ventanita;
import modelo.Registro;
import modelo.TipoMov;

/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLModRegistroController implements Initializable {

    @FXML
    public AnchorPane pane_nreg;
    @FXML
    private ImageView bt_add;
    @FXML
    private Label lbl_ced;
    @FXML
    private TextField txt_id;
    @FXML
    private Label lbl_titulo1;
    @FXML
    private Label lbl_nombre;
    @FXML
    private TextField txt_nombre;
    @FXML
    private Label lbl_sexo;
    @FXML
    private TextField txt_sexo;
    @FXML
    private Label lbl_annac;
    @FXML
    private TextField txt_anionac;
    @FXML
    private Label lbl_edad;
    @FXML
    private TextField txt_edad;
    @FXML
    private Label lbl_nacional;
    @FXML
    private TextField txt_nacionalid;
    @FXML
    private Label lbl_paisres;
    @FXML
    private TextField txt_paisres;
    @FXML
    private Label lbl_clasmig;
    @FXML
    private TextField txt_clasemig;
    @FXML
    private Label lbl_tipmov;
    @FXML
    private Label lbl_viatransp;
    @FXML
    private TextField txt_viatrans;
    @FXML
    private Label lbl_paisdest;
    @FXML
    private TextField txt_destino;
    @FXML
    private Label lbl_tiempoest;
    @FXML
    private TextField txt_tiempesta;
    @FXML
    private Label lbl_fsalida;
    @FXML
    private Label lbl_fregreso;
    @FXML
    private DatePicker fechasalida;
    @FXML
    private DatePicker fecharegreso;
    @FXML
    private ComboBox<TipoMov> cb_tipomov;
    
    protected static Registro regiMod;
    
    protected static String modo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTiposMov();
        switch (modo) {
            case "nuevoR":
                autollenarDatosMigrante();
                bloquearDatosPersonales();
                break;
            case "nuevoM":
                break;
            case "modificarR":
                autollenarDatosMigrante();
                autollenarDatosRegistro();
                bloquearDatosPersonales();
                break;
            default:
                break;
        }
    }    
    
    public void bloquearDatosPersonales(){
        txt_id.setDisable(true);
        txt_nombre.setDisable(true);
        txt_sexo.setDisable(true);
        txt_anionac.setDisable(true);
        txt_edad.setDisable(true);
        txt_nacionalid.setDisable(true);
        txt_clasemig.setDisable(true);
    }
    
    public void autollenarDatosMigrante(){
        txt_id.setText(regiMod.getMigrante().getCedula());
        txt_nombre.setText(regiMod.getMigrante().getNombre());
        txt_sexo.setText(regiMod.getMigrante().getSexo());
        txt_anionac.setText(String.valueOf(regiMod.getMigrante().getAnio_nac()));
        txt_edad.setText(String.valueOf(regiMod.getMigrante().getEdad()));
        txt_nacionalid.setText(regiMod.getMigrante().getNacionalidad());
        txt_clasemig.setText(regiMod.getMigrante().getClase_migratoria());
    }
    
    public void autollenarDatosRegistro(){
        txt_paisres.setText(regiMod.getPais_residencia());
        txt_viatrans.setText(regiMod.getPais_residencia());
        txt_destino.setText(regiMod.getPais_dest());
        txt_tiempesta.setText(regiMod.getTiempo_estadia());
    }

    @FXML
    private void aggRegi(MouseEvent event) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fecha = dateFormat.format(date);  
        if(modo.equalsIgnoreCase("nuevoM")){
            String queryMig = "INSERT INTO migrante values ( "+"\""+txt_id.getText()+"\",\""+txt_nombre.getText()+"\",\""+txt_sexo.getText()+"\","+txt_anionac.getText()+","+txt_edad.getText()+",\""+txt_nacionalid.getText()+"\",\""+txt_clasemig.getText()+"\""+");";
            System.out.println(queryMig);      
            String queryReg = "INSERT INTO REGISTRO (fecha_registro,migrante,tipo_movilizacion,via_transporte,pais_dest,tiempo_estadia,fecha_salida,fecha_regreso,pais_res) values ("+"\""+fecha+"\",\""+txt_id.getText()+"\",\""+cb_tipomov.getValue()+"\",\""+txt_viatrans.getText()+"\",\""+txt_destino.getText()+"\",\""+txt_tiempesta.getText()+"\",\""+fechasalida.getValue().format(DateTimeFormatter.ISO_DATE)+"\",\""+fecharegreso.getValue().format(DateTimeFormatter.ISO_DATE)+"\",\""+ txt_paisres.getText()+"\");";
            System.out.println(queryReg);
            PreparedStatement pst = bd.prepareStatement(queryReg);
            PreparedStatement pst1 = bd.prepareStatement(queryMig);
            pst1.execute();
            pst.execute();
        } else if(modo.equalsIgnoreCase("nuevoR")) {
            String queryReg = "INSERT INTO REGISTRO (fecha_registro,migrante,tipo_movilizacion,via_transporte,pais_dest,tiempo_estadia,fecha_salida,fecha_regreso,pais_res) values ("+"\""+fecha+"\",\""+txt_id.getText()+"\",\""+cb_tipomov.getValue()+"\",\""+txt_viatrans.getText()+"\",\""+txt_destino.getText()+"\",\""+txt_tiempesta.getText()+"\",\""+fechasalida.getValue().format(DateTimeFormatter.ISO_DATE)+"\",\""+fecharegreso.getValue().format(DateTimeFormatter.ISO_DATE)+"\",\""+ txt_paisres.getText()+"\");";
            System.out.println(queryReg);
            PreparedStatement pst = bd.prepareStatement(queryReg);
            pst.execute();
        }
        ventanita.close();
    }
    
    public void llenarTiposMov(){
        ObservableList<TipoMov> tipos= observableArrayList();
        tipos.add(TipoMov.Entrada);
        tipos.add(TipoMov.Salida);
        cb_tipomov.setItems(tipos);
    }   
}
