/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazturnos;

import cicularlinkedlist.CircularLinkedList;
import cicularlinkedlist.List;
import interfazticket.FXMLTicketController;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author walte
 */
public class FXMLTurnosController implements Initializable {
    private static boolean loop = true;
    @FXML
    private Label tiempo;
    @FXML
    private Pane publicidad;
    @FXML
    private GridPane turnoPuesto;
    private static List<String> listaPublicidad;
    @FXML
    private ImageView publicidadIV;
    @FXML
    private Label turno1;
    @FXML
    private Label puesto1;
    @FXML
    private Label turno2;
    @FXML
    private Label turno3;
    @FXML
    private Label puesto2;
    @FXML
    private Label puesto3;
    

    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listaPublicidad=leer();
            //publicidadLoop();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLTurnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(listaPublicidad);

        publicidadLoop();
    }
    
    private void publicidadLoop(){
            CircularLinkedList<String> p=(CircularLinkedList) listaPublicidad;
            Iterator<String> it= p.iterator();
            new Thread (() -> {
            try {
                while(it.hasNext()&&loop){
                    if(loop == false){
                        break;
                    }
                    String i = it.next();
                 
                    
                    Platform.runLater(() -> {
                         publicidadIV.setImage(new Image(new File(i).toURI().toString()));

                    });
                    Thread.sleep(3000);

                }
            }catch (InterruptedException ex) {
                Logger.getLogger(FXMLTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
    
    public static List<String> leer() throws FileNotFoundException{
        
        List<String> imagenes = new CircularLinkedList<>();
        
        File file=new File("src/publicidad/imagenes.txt");
        Scanner sc=new Scanner(file);
        while(sc.hasNextLine()){
            
            String line = "src/publicidad/"+sc.nextLine();
            System.out.println(line);
            imagenes.addLast(line);
        }
        return imagenes;
    }
    
    public static void setLoop(){
        loop = false;
    }


}
