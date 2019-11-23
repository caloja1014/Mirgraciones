/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazticket;

import modelo.AgenciaMigratoria;
import modelo.Ticket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author CLOJA
 */
public class FXMLTicketController implements Initializable {

    @FXML
    private Label labelTurno;
    @FXML
    private Label labelTipoPersona;
    @FXML
    private Button btnTerceraEdad;
    @FXML
    private Button btnEspeciales;
    @FXML
    private Button btnNormal;
    @FXML
    private Label mostrarTurno;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void turnoD(ActionEvent event) {
        asignarTurno(2);
    }

    @FXML
    private void turnoT(ActionEvent event) {
        asignarTurno(3);
    }

    @FXML
    private void turnoU(ActionEvent event) {
        asignarTurno(1);
    }
    private void asignarTurno(int tur){       
        Ticket t = new Ticket(tur);
        AgenciaMigratoria.turnos.offer(t);
        mostrarTurno.setText("Su turno es: "+t.getId());
        new Thread (() -> {
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> {
                    FXMLTicketController.this.mostrarTurno.setText("");
                });
                
            }catch (InterruptedException ex) {
                Logger.getLogger(FXMLTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
}
