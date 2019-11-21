/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migraciones;

import interfazturnos.FXMLTurnosController;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 *
 * @author nicoleagila
 */
public class Migraciones extends Application {

    @Override
    public void start(Stage stage) throws IOException  {
        
        Parent root = FXMLLoader.load(getClass().getResource("/interfazticket/FXMLTicket.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("/interfazturnos/FXMLTurnos.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("/interfazlogin/FXMLLogin.fxml"));
        
        Scene scene = new Scene(root);
        Scene scene2 = new Scene(root2);
        Scene scene3 = new Scene(root3);
        
        
        Stage stage2 = new Stage();
        Stage stage3 = new Stage();
        
        
        stage2.setOnHiding(new EventHandler<WindowEvent>() {

         @Override
         public void handle(WindowEvent event) {
             Platform.runLater(new Runnable() {

                 @Override
                 public void run() {
                     FXMLTurnosController.setLoop();
;
                 }
             });
         }
     });
        stage3.setScene(scene3);
        stage2.setScene(scene2);
        stage.setScene(scene);
        stage.show();  
        stage2.show();
        stage3.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
