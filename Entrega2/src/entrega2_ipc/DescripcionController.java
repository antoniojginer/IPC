/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2_ipc;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class DescripcionController implements Initializable {

    @FXML
    private Text codigo;
    @FXML
    private Text numeroEjercicios;
    @FXML
    private Text numeroCircuitos;
    @FXML
    private Text tEjercicio;
    @FXML
    private Text tDescansoE;
    @FXML
    private Text tDescansoC;
    @FXML
    private Text tCalentamiento;
    @FXML
    private Text titulo;
    @FXML
    private Button cancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Cancelar(ActionEvent event) {
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
    }
    
    public void codigo(String s){
        titulo.setText("Sesión seleccionada: " + s);
        codigo.setText("Código: " + s);
    }
    
    public void tiempoEjer(String t){
        tEjercicio.setText("Tiempo por ejercicio: " + t);
    }
    
    public void tiempoDescanso(String desE, String desC){
        tDescansoE.setText("Tiempo de descanso entre ejercicios: " + desE);
        tDescansoC.setText("Tiempo de descanso entre circuitos: " + desC);        
    }
    
    public void tiempoCalentamiento(String t){
        tCalentamiento.setText("Tiempo de calentamiento: " + t);
    }
    
    public void numeroRep(String nE, String nC){
        numeroCircuitos.setText("Número de repeticiones del circuito: " + nC);
        numeroEjercicios.setText("Número de ejercicios por circuito: " + nE);
    }
    
}
