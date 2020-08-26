/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2_ipc;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class GoController implements Initializable {

    @FXML
    private Text sesion;
    @FXML
    private ImageView imagen;
    @FXML
    private Text actualText;
    @FXML
    private Text reloj;
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button siguiente;
    @FXML
    private Button stop;

    
    private int[] tiempos;
    private String[] fases;
    private boolean pl, pa, si, st, ini, fin, inicio;
    private int i, actual;
    
    Image calentamiento;    
    Image ejercicio;
    Image descanso;
    Image finI;
    LocalTime tiempo;
    Thread hiloBack;
    @FXML
    private Button home;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pl = false;
        pa = false;
        si = false;
        ini = true;
        fin = false;
        inicio = true;
        tiempo = null;
        i = 0;
        actual = -1;
        
        pause.setDisable(true);
        siguiente.setDisable(true);
        stop.setDisable(true);
        
        calentamiento = new Image("fotos/calentamiento.jpg"); 
        ejercicio = new Image("fotos/crossfit.jpg"); 
        descanso = new Image("fotos/descanso.jpg"); 
        finI = new Image("fotos/fin.jpg"); 
    }    

    @FXML
    private void Play(ActionEvent event) {
        inicio = false;
        play.setDisable(true);
        pause.setDisable(false);
        siguiente.setDisable(false);
        stop.setDisable(false);
        
        if(i >= tiempos.length) {
            imagen.setImage(finI);
            actualText.setText("FIN DEL ENTRENAMIENTO");
        }
        else{
            Task<Void> cronometro;
            cronometro = new Task<Void>(){
                @Override
                protected Void call() throws Exception{

                    while(i < tiempos.length){
                        if(fases[i] == "Calentamiento"){
                            imagen.setImage(calentamiento);
                            actualText.setText("CALENTAMIENTO");
                        }
                        if(fases[i] == "Ejercicio"){
                            imagen.setImage(ejercicio);
                            actualText.setText("EJERCICIO");
                        }
                        if(fases[i] == "Descanso"){
                            imagen.setImage(descanso);
                            actualText.setText("DESCANSO ENTRE EJERCICIO");
                        }
                        if(fases[i] == "DescansoC"){
                            imagen.setImage(descanso);
                            actualText.setText("DESCANSO ENTRE CIRCUITOS");
                        }
                        for(int j = tiempos[i]; j>-1; j--){
                            actual = j;
                            LocalTime ahora = calcula(j);
                            Platform.runLater(() -> reloj.setText(ahora.toString()));

                            Thread.sleep(1000);
                            if(i == 5){
                                Media sonido = new Media(getClass().getResource("/fotos/sonido.wav").toExternalForm());
                                MediaPlayer mediaPlayer = new MediaPlayer(sonido);
                                mediaPlayer.setAutoPlay(true);
                            }

                        }
                        if(i +1 == tiempos.length){
                            imagen.setImage(finI);
                            actualText.setText("FIN DEL ENTRENAMIENTO");
                            break;
                        }
                        i++;

                    }
                    return null;
                }
            };

            if(ini || pa || si){
                hiloBack = new Thread(cronometro);
                hiloBack.setDaemon(true);
                hiloBack.start();

                ini = false;
                pa = false;
                si = false;
            }
        }
        
    }

    @FXML
    private void Pause(ActionEvent event) {
        pa = true;
        tiempos[i] = actual;
        hiloBack.interrupt();
        play.setDisable(false);
        pause.setDisable(true);
        siguiente.setDisable(true);
        stop.setDisable(true);
    }

    @FXML
    private void Siguiente(ActionEvent event) {
        si = true;
        i++;
        hiloBack.interrupt();
        play.setDisable(false);
        pause.setDisable(true);
        siguiente.setDisable(true);
        stop.setDisable(true);
    }

    @FXML
    private void Stop(ActionEvent event) {
        i = 0;
        hiloBack.interrupt();
        
        imagen.setImage(finI);
        actualText.setText("FIN DEL ENTRENAMIENTO");
        
        play.setDisable(true);
        pause.setDisable(true);
        siguiente.setDisable(true);
        stop.setDisable(true);
    }
    
    public void datos(int[] x, String[] y){
        tiempos = x;
        fases = y;
    }
    
    private LocalTime calcula(int i){
        LocalTime res = null;
        if(i>59){
            res = LocalTime.of(0, i/60, i%60);
        }
        else{
            res = LocalTime.of(0,0,i);
        }     
        if(i == 5){
            Media sonido = new Media(getClass().getResource("/fotos/sonido.wav").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sonido);
            mediaPlayer.setAutoPlay(true);
        }
        return res;
    }

    @FXML
    private void Home(ActionEvent event) {
        if(!inicio) hiloBack.interrupt();
        Stage stage = (Stage) home.getScene().getWindow();
        stage.close();
    }
}
