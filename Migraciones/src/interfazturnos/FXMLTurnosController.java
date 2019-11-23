/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazturnos;

import cicularlinkedlist.CircularLinkedList;
import cicularlinkedlist.List;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import interfazticket.FXMLTicketController;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalTime;
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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
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
    private static ArrayList<Label> labelsTurnosEspera;
    private static final PriorityQueue<Ticket> tickets=AgenciaMigratoria.turnos;
    private static PriorityQueue<Ticket> turnosEspera=new PriorityQueue<>((Ticket t1, Ticket t2) -> (t2.getPrioridad()-t1.getPrioridad()));
    private static  final HashMap<Integer,Label>puestosTurnos=new HashMap<>();
    @FXML
    private Label label7;
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
            LocalTime currentTime = LocalTime.now();
            if(currentTime.getMinute() < 10){
                tiempo.setText(currentTime.getHour() + ":" + "0"+ currentTime.getMinute());
            }else{
                tiempo.setText(currentTime.getHour() + ":" + currentTime.getMinute());
            }
        }),
             new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();        
           
        publicidadIV.fitHeightProperty().bind(publicidad.heightProperty());
        publicidadIV.fitWidthProperty().bind(publicidad.widthProperty());
        publicidadIV.setPreserveRatio(false);
        labelsTurnosEspera=  new ArrayList<>();
        labelsTurnosEspera.add(label1);
        labelsTurnosEspera.add(label2);
        labelsTurnosEspera.add(label3);
        labelsTurnosEspera.add(label4);
        labelsTurnosEspera.add(label5);
        labelsTurnosEspera.add(label6);
        labelsTurnosEspera.add(label7);
        try {
            listaPublicidad=leer();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLTurnosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(listaPublicidad);
        publicidadLoop();
        ticketSimulacion();
    }
    
    private void publicidadLoop(){
            CircularLinkedList<String> p=(CircularLinkedList) listaPublicidad;
            Iterator<String> it= p.iterator();
            new Thread (() -> {
                try {
                    
                    while(it.hasNext()&&loop){
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
    
    private void ticketSimulacion(){
        new Thread(() ->{
            try {
                while(loop != false){
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    llenarPuestos();
                     actualizacionTickets();
                });
                }

 
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLTurnosController.class.getName()).log(Level.SEVERE, null, ex);
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
        Ticket t =turnosEspera.poll();
        Label puesto=puestosTurnos.get(pos);
        puesto.setText(t.getId());
        Ticket t2=tickets.poll();
        turnosEspera.offer(t2);
    }
        
    
    public void crearLabelsPuestos(int i){
        int h=1;
        for(int j=0;j<i;j++){
            Label l=new Label("");
            puestosTurnos.put(h, l);
            turnoPuesto.add(l, 0, h);
            h++;
        } 
    }
    public void llenarPuestos(){
        if(turnosEspera.size()<=6){
            while(!tickets.isEmpty()){
                Ticket t= tickets.poll();
                turnosEspera.offer(t);
                
            }
            
        }
        if(!turnosEspera.isEmpty()){
            for(Map.Entry<Integer,Label> e: puestosTurnos.entrySet()){
                Label l=e.getValue();
                if(turnosEspera.peek()!= null && l.getText().equals("")){
                    Ticket t= turnosEspera.poll();
                    l.setText(t.getId());
                } 
            }
        }
    }
    public void actualizacionTickets(){
        //PriorityQueue<Ticket> another=new PriorityQueue<>((Ticket t1, Ticket t2) -> ((t2.getPrioridad()-t1.getPrioridad()))*(Integer.valueOf(t1.getId().substring(1))-Integer.valueOf(t2.getId().substring(1))));
        
        PriorityQueue<Ticket> another;
        another = new PriorityQueue<>(
                (Ticket t1, Ticket t2) -> {
                    return t1.getId().compareTo(t2.getId());
        });
        for (Label l : labelsTurnosEspera) {
            if(turnosEspera.peek()!= null){
            Ticket t =turnosEspera.poll();
            l.setText(t.getId());
            another.offer(t);
            }
        }
        turnosEspera=another;
    }
}
