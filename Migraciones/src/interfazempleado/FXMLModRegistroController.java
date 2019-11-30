/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazempleado;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static migraciones.Migraciones.bd;
import modelo.Puesto;
import modelo.Registro;

/**
 * FXML Controller class
 *
 * @author nicoleagila
 */
public class FXMLModRegistroController implements Initializable {

    @FXML
    private AnchorPane pane_nreg;
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
    private ComboBox<String> cb_tipomov;
    
    protected static Registro regiMod;
    
    protected static boolean nuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autollenarDatos();
    }    
    
    public void autollenarDatos(){
        txt_id.setText(regiMod.getMigrante().getCedula());
        txt_nombre.setText(regiMod.getMigrante().getNombre());
        txt_sexo.setText(regiMod.getMigrante().getSexo());
        txt_anionac.setText(String.valueOf(regiMod.getMigrante().getAnio_nac()));
        txt_edad.setText(String.valueOf(regiMod.getMigrante().getEdad()));
        txt_nacionalid.setText(regiMod.getMigrante().getNacionalidad());
        txt_paisres.setText(regiMod.getMigrante().getPais_residencia());
        txt_clasemig.setText(regiMod.getMigrante().getClase_migratoria());
    }


    @FXML
    private void aggRegi(MouseEvent event) throws SQLException {
        if(nuevo==false){
            String queryMig = "INSERT INTO migrante values ( "+"\""+txt_id.getText()+"\",\""+txt_nombre.getText()+"\",\""+txt_sexo.getText()+"\","+txt_anionac.getText()+","+txt_edad.getText()+"\""+txt_nacionalid.getText()+"\""+");";
            String queryReg = "INSERT INTO registro values ();";
            PreparedStatement pst = bd.prepareStatement(queryReg);
            pst.execute();
        }
    }
    
    
   
}
