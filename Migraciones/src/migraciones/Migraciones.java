/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migraciones;

import interfazturnos.FXMLTurnosController;
import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.AgenciaMigratoria;
import modelo.Conexion;


/**
 *
 * @author nicoleagila
 */
public class Migraciones extends Application {
    public static Stage stEmpleado = new Stage();
    public static Stage stPuesto = new Stage();
    public static Stage stTicket = new Stage();
    public static Stage stTurnosP = new Stage();
    public static Stage stLogin = new Stage();
    public static Stage ventanita = new Stage();
    public static Connection bd;

    @Override
    public void start(Stage stage) throws IOException  {
        Conexion cn = new Conexion();
        bd = cn.getConnection();    
        AgenciaMigratoria.cargarListas();
        Parent rootTicket = FXMLLoader.load(getClass().getResource("/interfazticket/FXMLTicket.fxml"));
        Parent rootTurnos = FXMLLoader.load(getClass().getResource("/interfazturnos/FXMLTurnos.fxml"));
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/interfazlogin/FXMLLogin.fxml"));
              
        Scene scTicket = new Scene(rootTicket);
        Scene scTurnos = new Scene(rootTurnos);
        Scene scLogin = new Scene(rootLogin);

        stTurnosP.setOnHiding(new EventHandler<WindowEvent>() {

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
        stTicket.setScene(scTicket);
        stLogin.setScene(scLogin);
        stTurnosP.setScene(scTurnos);
        stLogin.show();  
        stTurnosP.show();
        stTicket.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
