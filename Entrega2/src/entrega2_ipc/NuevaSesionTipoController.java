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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.SesionTipo;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class NuevaSesionTipoController implements Initializable {

    @FXML
    private TextField codigo;
    @FXML
    private TextField nEjercicios;
    @FXML
    private TextField tCalentamiento;
    @FXML
    private TextField tDesC;
    @FXML
    private TextField tDesE;
    @FXML
    private TextField tEjercicio;
    @FXML
    private TextField nCircuitos;
    @FXML
    private Text error;
    @FXML
    private Button cancelar;
    @FXML
    private Button aceptar;
    private boolean fin;
    private SesionTipo res;
    private String cod;

    
    private int nE, nC, tE, tDE, tDC, tCal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fin = false;
        res = null;
        
        cod = "";
        nE = -1;
        nC = -1;
        tE = -1;
        tDE = -1;
        tDC = -1;
        tCal = -1;
        
        aceptar.setDisable(true);
        
        codigo.textProperty().addListener((o, oldVal, newVal) -> {
            if((newVal).isEmpty()){
                error.setText("Por favor introduzca el identificador de la nueva sesión.");  
                aceptar.setDisable(true);
            } else{
                if(nEjercicios.getText().isEmpty() || !isInteger(nEjercicios.getText()) || Integer.valueOf(nEjercicios.getText()) == 0){
                        error.setText("Por favor introduzca el numero de ejercicios por circuito (solo números y distinto de 0)."); 
                        aceptar.setDisable(true);
                    } else{
                        if(nCircuitos.getText().isEmpty() || !isInteger(nCircuitos.getText()) || Integer.valueOf(nCircuitos.getText()) == 0){
                            error.setText("Por favor introduzca el numero de repeticiones del circuito (solo números y distinto de 0).");
                            aceptar.setDisable(true);
                        } else{
                            if(tEjercicio.getText().isEmpty() || !isInteger(tEjercicio.getText())|| Integer.valueOf(tEjercicio.getText()) == 0){
                                error.setText("Por favor introduzca el tiempo del ejercicio en segundos (solo números y distinto de 0).");
                                aceptar.setDisable(true);
                            } else{
                            if(tDesE.getText().isEmpty() || !isInteger(tDesE.getText())){
                                error.setText("Por favor introduzca el tiempo de descanso entre ejercicios en segundos (solo números).");
                                aceptar.setDisable(true);
                            } else{
                                if(tDesC.getText().isEmpty() || !isInteger(tDesC.getText())){
                                    error.setText("Por favor introduzca el tiempo de descanso entre circuitos en segundos (solo números).");
                                    aceptar.setDisable(true);
                                } else{
                                    if(tCalentamiento.getText().isEmpty() || !isInteger(tCalentamiento.getText())){
                                         error.setText("Por favor introduzca el tiempo de calentamiento en segundos (solo números).");
                                         aceptar.setDisable(true);
                                    } else{
                                        error.setText("");
                                        aceptar.setDisable(false);
                                    }
                                }
                            }
                        } 
                        
                    }
                    
                }
            }
        });
        
        nEjercicios.textProperty().addListener((o, oldVal, newVal) -> {
            if((newVal).isEmpty() || !isInteger((newVal)) || Integer.valueOf(newVal) == 0){
                error.setText("Por favor introduzca el numero de ejercicios por circuito (solo números y distinto de 0).");  
                aceptar.setDisable(true);
            } else{
                if(codigo.getText().isEmpty()){
                    error.setText("Por favor introduzca el identificador de la nueva sesión.");
                    
                    aceptar.setDisable(true);
                } else{
                    if(nCircuitos.getText().isEmpty() || !isInteger(nCircuitos.getText()) || Integer.valueOf(nCircuitos.getText()) == 0){
                        error.setText("Por favor introduzca el numero de repeticiones del circuito (solo números).");
                        aceptar.setDisable(true);
                    } else{
                        if(tEjercicio.getText().isEmpty() || !isInteger(tEjercicio.getText())|| Integer.valueOf(tEjercicio.getText()) == 0){
                            error.setText("Por favor introduzca el tiempo del ejercicio en segundos (solo números y distinto de 0).");
                            aceptar.setDisable(true);
                        } else{
                            if(tDesE.getText().isEmpty() || !isInteger(tDesE.getText())){
                                error.setText("Por favor introduzca el tiempo de descanso entre ejercicios en segundos (solo números).");
                                aceptar.setDisable(true);
                            } else{
                                if(tDesC.getText().isEmpty() || !isInteger(tDesC.getText())){
                                    error.setText("Por favor introduzca el tiempo de descanso entre circuitos en segundos (solo números).");
                                    aceptar.setDisable(true);
                                } else{
                                    if(tCalentamiento.getText().isEmpty() || !isInteger(tCalentamiento.getText())){
                                         error.setText("Por favor introduzca el tiempo de calentamiento en segundos (solo números).");
                                         aceptar.setDisable(true);
                                    } else{
                                        error.setText("");
                                        aceptar.setDisable(false);
                                    }
                                }
                            }
                        } 
                        
                    }
                    
                }
            }
        });
        
        nCircuitos.textProperty().addListener((o, oldVal, newVal) -> {
            if((newVal).isEmpty() || !isInteger((newVal))|| Integer.valueOf(newVal) == 0){
                error.setText("Por favor introduzca el numero de repeticiones del circuito (solo números y distinto de 0).");
                 
                aceptar.setDisable(true);
            } else{
                if(codigo.getText().isEmpty()){
                    error.setText("Por favor introduzca el identificador de la nueva sesión.");
                    
                    aceptar.setDisable(true);
                } else{
                    if(nEjercicios.getText().isEmpty() || !isInteger(nEjercicios.getText()) || Integer.valueOf(nEjercicios.getText()) == 0){
                        error.setText("Por favor introduzca el numero de ejercicios por circuito (solo números y distinto de 0)."); 
                        aceptar.setDisable(true);
                    } else{
                        if(tEjercicio.getText().isEmpty() || !isInteger(tEjercicio.getText())|| Integer.valueOf(tEjercicio.getText()) == 0){
                            error.setText("Por favor introduzca el tiempo del ejercicio en segundos (solo números y distinto de 0).");
                            aceptar.setDisable(true);
                        } else{
                            if(tDesE.getText().isEmpty() || !isInteger(tDesE.getText())){
                                error.setText("Por favor introduzca el tiempo de descanso entre ejercicios en segundos (solo números).");
                                aceptar.setDisable(true);
                            } else{
                                if(tDesC.getText().isEmpty() || !isInteger(tDesC.getText())){
                                    error.setText("Por favor introduzca el tiempo de descanso entre circuitos en segundos (solo números).");
                                    aceptar.setDisable(true);
                                } else{
                                    if(tCalentamiento.getText().isEmpty() || !isInteger(tCalentamiento.getText())){
                                         error.setText("Por favor introduzca el tiempo de calentamiento en segundos (solo números).");
                                         aceptar.setDisable(true);
                                    } else{
                                        error.setText("");
                                        aceptar.setDisable(false);
                                    }
                                }
                            }
                        } 
                        
                    }
                    
                }
            }
        });
        
        tEjercicio.textProperty().addListener((o, oldVal, newVal) -> {
            if((newVal).isEmpty() || !isInteger((newVal)) || Integer.valueOf(newVal) == 0){
                
                error.setText("Por favor introduzca el tiempo del ejercicio en segundos (solo números y distinto de 0).");
                aceptar.setDisable(true);
            } else{
                if(codigo.getText().isEmpty()){
                    error.setText("Por favor introduzca el identificador de la nueva sesión.");
                    
                    aceptar.setDisable(true);
                } else{
                    if(nEjercicios.getText().isEmpty() || !isInteger(nEjercicios.getText()) || Integer.valueOf(nEjercicios.getText()) == 0){
                        error.setText("Por favor introduzca el numero de ejercicios por circuito (solo números y distinto de 0)."); 
                        aceptar.setDisable(true);
                    } else{
                        if(nCircuitos.getText().isEmpty() || !isInteger(nCircuitos.getText()) || Integer.valueOf(nCircuitos.getText()) == 0){
                        error.setText("Por favor introduzca el numero de repeticiones del circuito (solo números).");
                        aceptar.setDisable(true);
                    } else{
                            if(tDesE.getText().isEmpty() || !isInteger(tDesE.getText())){
                                error.setText("Por favor introduzca el tiempo de descanso entre ejercicios en segundos (solo números).");
                                aceptar.setDisable(true);
                            } else{
                                if(tDesC.getText().isEmpty() || !isInteger(tDesC.getText())){
                                    error.setText("Por favor introduzca el tiempo de descanso entre circuitos en segundos (solo números).");
                                    aceptar.setDisable(true);
                                } else{
                                    if(tCalentamiento.getText().isEmpty() || !isInteger(tCalentamiento.getText())){
                                         error.setText("Por favor introduzca el tiempo de calentamiento en segundos (solo números).");
                                         aceptar.setDisable(true);
                                    } else{
                                        error.setText("");
                                        aceptar.setDisable(false);
                                    }
                                }
                            }
                        } 
                        
                    }
                    
                }
            }
        });
        
        tDesE.textProperty().addListener((o, oldVal, newVal) -> {
            if((newVal).isEmpty() || !isInteger((newVal))){
                error.setText("Por favor introduzca el tiempo de descanso entre ejercicios en segundos (solo números).");
                
                aceptar.setDisable(true);
            } else{
                if(codigo.getText().isEmpty()){
                    error.setText("Por favor introduzca el identificador de la nueva sesión.");
                    
                    aceptar.setDisable(true);
                } else{
                    if(nEjercicios.getText().isEmpty() || !isInteger(nEjercicios.getText()) || Integer.valueOf(nEjercicios.getText()) == 0){
                        error.setText("Por favor introduzca el numero de ejercicios por circuito (solo números y distinto de 0)."); 
                        aceptar.setDisable(true);
                    } else{
                        if(nCircuitos.getText().isEmpty() || !isInteger(nCircuitos.getText()) || Integer.valueOf(nCircuitos.getText()) == 0){
                            error.setText("Por favor introduzca el numero de repeticiones del circuito (solo números y distinto de 0).");
                            aceptar.setDisable(true);
                        } else{
                            if(tEjercicio.getText().isEmpty() || !isInteger(tEjercicio.getText())|| Integer.valueOf(tEjercicio.getText()) == 0){
                                error.setText("Por favor introduzca el tiempo del ejercicio en segundos (solo números y distinto de 0).");
                                aceptar.setDisable(true);
                            } else{
                                if(tDesC.getText().isEmpty() || !isInteger(tDesC.getText())){
                                    error.setText("Por favor introduzca el tiempo de descanso entre circuitos en segundos (solo números).");
                                    aceptar.setDisable(true);
                                } else{
                                    if(tCalentamiento.getText().isEmpty() || !isInteger(tCalentamiento.getText())){
                                         error.setText("Por favor introduzca el tiempo de calentamiento en segundos (solo números).");
                                         aceptar.setDisable(true);
                                    } else{
                                        error.setText("");
                                        aceptar.setDisable(false);
                                    }
                                }
                            }
                        } 
                        
                    }
                    
                }
            }
        });
        
        tDesC.textProperty().addListener((o, oldVal, newVal) -> {
            if((newVal).isEmpty() || !isInteger((newVal))){
                
                error.setText("Por favor introduzca el tiempo de descanso entre circuitos en segundos (solo números).");
                aceptar.setDisable(true);
            } else{
                if(codigo.getText().isEmpty()){
                    error.setText("Por favor introduzca el identificador de la nueva sesión.");
                    
                    aceptar.setDisable(true);
                } else{
                    if(nEjercicios.getText().isEmpty() || !isInteger(nEjercicios.getText()) || Integer.valueOf(nEjercicios.getText()) == 0){
                        error.setText("Por favor introduzca el numero de ejercicios por circuito (solo números y distinto de 0)."); 
                        aceptar.setDisable(true);
                    } else{
                        if(nCircuitos.getText().isEmpty() || !isInteger(nCircuitos.getText()) || Integer.valueOf(nCircuitos.getText()) == 0){
                            error.setText("Por favor introduzca el numero de repeticiones del circuito (solo números y distinto de 0).");
                            aceptar.setDisable(true);
                        } else{
                            if(tEjercicio.getText().isEmpty() || !isInteger(tEjercicio.getText())|| Integer.valueOf(tEjercicio.getText()) == 0){
                                error.setText("Por favor introduzca el tiempo del ejercicio en segundos (solo números y distinto de 0).");
                                aceptar.setDisable(true);
                            } else{
                                if(tDesE.getText().isEmpty() || !isInteger(tDesE.getText())){
                                    error.setText("Por favor introduzca el tiempo de descanso entre ejercicios en segundos (solo números).");
                                    aceptar.setDisable(true);
                                } else{
                                    if(tCalentamiento.getText().isEmpty() || !isInteger(tCalentamiento.getText())){
                                         error.setText("Por favor introduzca el tiempo de calentamiento en segundos (solo números).");
                                         aceptar.setDisable(true);
                                    } else{
                                        error.setText("");
                                        aceptar.setDisable(false);
                                    }
                                }
                            }
                        } 
                        
                    }
                    
                }
            }
        });
        
       tCalentamiento.textProperty().addListener((o, oldVal, newVal) -> {
            if((newVal).isEmpty() || !isInteger((newVal))){
                error.setText("Por favor introduzca el tiempo de calentamiento en segundos (solo números).");
                
                aceptar.setDisable(true);
            } else{
                if(codigo.getText().isEmpty()){
                    error.setText("Por favor introduzca el identificador de la nueva sesión.");
                    
                    aceptar.setDisable(true);
                } else{
                    if(nEjercicios.getText().isEmpty() || !isInteger(nEjercicios.getText()) || Integer.valueOf(nEjercicios.getText()) == 0){
                        error.setText("Por favor introduzca el numero de ejercicios por circuito (solo números y distinto de 0)."); 
                        aceptar.setDisable(true);
                    } else{
                        if(nCircuitos.getText().isEmpty() || !isInteger(nCircuitos.getText()) || Integer.valueOf(nCircuitos.getText()) == 0){
                            error.setText("Por favor introduzca el numero de repeticiones del circuito (solo números y distinto de 0).");
                            aceptar.setDisable(true);
                        } else{
                            if(tEjercicio.getText().isEmpty() || !isInteger(tEjercicio.getText())|| Integer.valueOf(tEjercicio.getText()) == 0){
                                error.setText("Por favor introduzca el tiempo del ejercicio en segundos (solo números y distinto de 0).");
                                aceptar.setDisable(true);
                            } else{
                                if(tDesE.getText().isEmpty() || !isInteger(tDesE.getText())){
                                    error.setText("Por favor introduzca el tiempo de descanso entre ejercicios en segundos (solo números).");
                                    aceptar.setDisable(true);
                                } else{
                                    if( tDesC.getText().isEmpty() || !isInteger( tDesC.getText())){
                                         error.setText("Por favor introduzca el tiempo de descanso entre circuitos en segundos (solo números).");
                                         aceptar.setDisable(true);
                                    } else{
                                        error.setText("");
                                        aceptar.setDisable(false);
                                    }
                                }
                            }
                        } 
                        
                    }
                    
                }
            }
        });
    }  
    
    

    @FXML
    private void Cancelar(ActionEvent event) {
        fin = false;
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
    }

    public boolean isFin() {
        return fin;
    }

    @FXML
    private void Aceptar(ActionEvent event) {
        fin = true;
        
        setCod(codigo.getText());
        setnE(Integer.valueOf(nEjercicios.getText()));
        setnC(Integer.valueOf(nCircuitos.getText()));
        settE(Integer.valueOf(tEjercicio.getText()));
        settDE(Integer.valueOf(tDesE.getText()));
        settDC(Integer.valueOf(tDesC.getText()));
        settCal(Integer.valueOf(tCalentamiento.getText()));
        
        Stage stage = (Stage) aceptar.getScene().getWindow();
        stage.close();
        
    }
    
    public void mod(String c, String nE, String nC, String tE, String tDesEj, String tDesCa, String tCale){
        codigo.setText(c);
        nEjercicios.setText(nE);
        nCircuitos.setText(nC);
        tCalentamiento.setText(tCale);
        tDesC.setText(tDesCa);
        tDesE.setText(tDesEj);
        tEjercicio.setText(tE);
    }
    
    public boolean isInteger(String numero){
        try{
            Integer.parseInt(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getnE() {
        return nE;
    }

    public void setnE(int nE) {
        this.nE = nE;
    }

    public int getnC() {
        return nC;
    }

    public void setnC(int nC) {
        this.nC = nC;
    }

    public int gettE() {
        return tE;
    }

    public void settE(int tE) {
        this.tE = tE;
    }

    public int gettDE() {
        return tDE;
    }

    public void settDE(int tDE) {
        this.tDE = tDE;
    }

    public int gettDC() {
        return tDC;
    }

    public void settDC(int tDC) {
        this.tDC = tDC;
    }

    public int gettCal() {
        return tCal;
    }

    public void settCal(int tCal) {
        this.tCal = tCal;
    }
    
    
}
