/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazlogin;


import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import static migraciones.Migraciones.stLogin;

/**
 * FXML Controller class
 *
 * @author walte
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField txtFieldUser;
    @FXML
    private TextField txtFieldPass;
    @FXML
    private Button Enter;
    @FXML
    private Button Salir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inicializarLogin(ActionEvent event) throws IOException {
       String user = txtFieldUser.getText();
       String pass = txtFieldPass.getText();
       Stage st = (Stage)Enter.getScene().getWindow();    
       if(user.equals("admin") && pass.equals("admin")){
        Parent root = FXMLLoader.load(getClass().getResource("/interfazadmin/FXMLAdmin.fxml"));
        stLogin.setScene(new Scene(root));
        stLogin.show();
       }
       else{
           System.out.println("User/Password Incorrectos");    
       }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
       Stage st = (Stage)Enter.getScene().getWindow();
       st.close();
    }

    @FXML
    private void loginConEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            Enter.fire();
        }
        
        
    }
    
}
