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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import modelo.AgenciaMigratoria;
import modelo.Puesto;
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
    private static  final HashMap<Integer,Text>puestosTurnos=new HashMap<>();
    @FXML
    private Label label7;
    @FXML
    private VBox principal;
    @FXML
    private Label horarioAtencion;
    @FXML
    private HBox contenedorHorario;
    @FXML
    private HBox contenedorTurnos;
    @FXML
    private HBox contenedorTiempo;
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        principal.setSpacing(5);
        principal.setStyle("-fx-background-color: #FFFFFF;");
        horarioAtencion.setStyle("-fx-background-color: #66B2FF;");
       // contenedorHorario.setPadding(new Insets(5));
        contenedorTurnos.setStyle("-fx-background-color: #66B2FF;");
      //  contenedorTurnos.setPadding(new Insets(5));
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
        contenedorTiempo.setStyle("-fx-background-color: #6666FF;");
        tiempo.setTextFill(Color.WHITE);
        tiempo.setFont(new Font(25));
       // publicidadIV.fitHeightProperty().bind(publicidad.heightProperty());
        //publicidadIV.fitWidthProperty().bind(publicidad.widthProperty());
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
        crearLabelsPuestos();
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
        Text puesto=puestosTurnos.get(pos);
        puesto.setText(t.getId());
        Ticket t2=tickets.poll();
        turnosEspera.offer(t2);
    }
        
    
    /*public void crearLabelsPuestos(int i){
        int h=1;
        for(int j=0;j<i;j++){
            Label l=new Label("");
            puestosTurnos.put(h, l);
            turnoPuesto.add(l, 0, h);
            h++;
        }
    }*/
    
    public void crearLabelsPuestos(){
        int h=1;
        Rectangle pues = new Rectangle(155,30);
        pues.setFill(Color.rgb(102, 178, 255));
        pues.setStroke(Color.rgb(102, 178, 255));
        StackPane stack3 = new StackPane();
        Text t3=new Text("PUESTO");
        t3.setFont(new Font(25));
        t3.setFill(Color.WHITE);
        stack3.getChildren().addAll(pues, t3);
        
        Rectangle tur = new Rectangle(155,30);
        tur.setFill(Color.rgb(102, 102, 255));
        tur.setStroke(Color.rgb(102, 102, 255));
        StackPane stack4 = new StackPane();
        Text t4=new Text("TURNO");
        t4.setFont(new Font(25));
        t4.setFill(Color.WHITE);
        stack4.getChildren().addAll(tur,t4);
        
        turnoPuesto.add(stack3, 0, 0);
        turnoPuesto.add(stack4, 1, 0);
        turnoPuesto.setHgap(0);
        turnoPuesto.setVgap(1);
        for (Puesto p : AgenciaMigratoria.puestosDisponibles){
            Rectangle r = new Rectangle(155,30);
            r.setFill(Color.rgb(102, 102, 255));
            r.setStroke(Color.rgb(102, 102, 255));
            StackPane stack = new StackPane();
            Text t=new Text("");
            t.setFont(new Font(25));
            t.setFill(Color.WHITE);
            stack.getChildren().addAll(r, t);
            //Label l=new Label();
            //l.setMinSize(4, 4);
            //l.setFont(new Font(20));
           // Label l2 = new Label(String.valueOf(p.getId()));
            //l2.setStyle("-fx-background-color:POWDERBLUE");
            Rectangle r2 = new Rectangle(155,30);
            r2.setFill(Color.rgb(102, 178, 255));
            r2.setStroke(Color.rgb(102, 178, 255));
            StackPane stack2 = new StackPane();
            Text t2=new Text(String.valueOf(p.getId()));
            t2.setFont(new Font(25));
            t2.setFill(Color.WHITE);
            stack2.getChildren().addAll(r2, t2);
            //l2.setFont(new Font(20));
            puestosTurnos.put(p.getId(), t);
            turnoPuesto.add(stack, 1, h);
            turnoPuesto.add(stack2, 0, h);
            h++;
        }
        
        
    }
    public void llenarPuestos(){
        if(turnosEspera.size()<6){
            while(!tickets.isEmpty()){
                Ticket t= tickets.poll();
                turnosEspera.offer(t);    
            }   
        }
        if(!turnosEspera.isEmpty()){
            for(Map.Entry<Integer,Text> e: puestosTurnos.entrySet()){
                Text l=e.getValue();
                if(turnosEspera.peek()!= null && l.getText().equals("")){
                    Ticket t= turnosEspera.poll();
                    l.setText(t.getId());
                } 
            }
        }
    }
    public void actualizacionTickets(){
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
