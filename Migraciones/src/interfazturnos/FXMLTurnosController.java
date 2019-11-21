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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
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
import modelo.AgenciaMigratoria;
import modelo.Ticket;



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
    private Label turnoOcupado;
    @FXML
    private Label lblTurnos;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    
    private static final PriorityQueue<Ticket> tickets=AgenciaMigratoria.turnos;
    private static final PriorityQueue<Ticket> turnosEspera=new PriorityQueue<>((Ticket t1, Ticket t2) -> (t2.getPrioridad()-t1.getPrioridad()));
    private static  final HashMap<Integer,Label>puestosTurnos=new HashMap<>();
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            listaPublicidad=leer();
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
    
    public static void actualizarPuesto(Integer pos){
        Ticket t =tickets.poll();
        Label puesto=puestosTurnos.get(pos);
        puesto.setText(t.getId());
    }
        
    
    public void crearLabelsPuestos(int i){
        int h=1;
        for(int j=0;j<i;j++){
            Label l=new Label(String.valueOf(i));
            puestosTurnos.put(h, l);
            turnoPuesto.add(l, 0, h);
            h++;
        } 
    }
    public void llenarPuestos(){
        if(!tickets.isEmpty()){
            for(Map.Entry<Integer,Label> e: puestosTurnos.entrySet()){
                Label l=e.getValue();
                Ticket t= tickets.poll();
                l.setText(t.getId());
            }
        }
    }
}
