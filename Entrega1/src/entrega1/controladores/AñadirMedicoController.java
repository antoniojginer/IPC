/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1.controladores;

import java.awt.Image;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Days;
import model.Doctor;
import model.ExaminationRoom;
import model.LocalTimeAdapter;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class AñadirMedicoController implements Initializable {

    @FXML
    private TextField nombreMedico;
    @FXML
    private TextField apellidoMedico;
    @FXML
    private TextField dniMedico;
    @FXML
    private TextField telefonoMedico;
    @FXML
    private CheckBox lunesBox;
    @FXML
    private CheckBox viernesBox;
    @FXML
    private CheckBox juevesBox;
    @FXML
    private CheckBox miercolesBox;
    @FXML
    private CheckBox martesBox;
    private TextField horaInicio;
    @FXML
    private TextField salaMedico;
    @FXML
    private Button aceptarMedico;
    @FXML
    private Button cancelarMedico;
    
    protected static Doctor nuevoD;
    protected static boolean botonAceptarMedico, botonCancelarMedico;
    
    String dni, nombre, apellidos, tlf;
    LocalTime horaI, horaF ;
    Image foto ;
    boolean lu, ma, mi, ju, vi;
    ExaminationRoom salaVisita;
    
    @FXML
    private TextField inicioHora;
    @FXML
    private TextField finHora;
    @FXML
    private TextField minutosInicio;
    @FXML
    private TextField minutosFin;
    

    /**
     * Initializes the controller class.
     * public Doctor(ExaminationRoom examinationRoom, Days visitDay, LocalTime startTime, LocalTime endTime, String identifier, String name, String surname, String telephon, Image photo) 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciar();        
    }    
    
    private void iniciar(){
        botonAceptarMedico = false;
        botonCancelarMedico = false;
        nuevoD = null;
        foto = null;
        Days diasV = null;
        horaI = null;
        horaF = null;
        dni = "";
        nombre = "";
        apellidos = "";
        tlf = "";
        //pruebas
        aceptarMedico.setDisable(false);
    }

    @FXML
    private void AceptarMedico(ActionEvent event) {
        nombre = nombreMedico.getText();
        apellidos = apellidoMedico.getText();
        dni = dniMedico.getText();
        tlf = telefonoMedico.getText();
        
        
        horaI = LocalTime.of(Integer.valueOf(inicioHora.getText()), Integer.valueOf(minutosInicio.getText()) );
        horaF = LocalTime.of(Integer.valueOf(finHora.getText()), Integer.valueOf(minutosFin.getText()) );
                
        salaVisita = new ExaminationRoom(Integer.valueOf(salaMedico.getText()), "");  
        
        nuevoD = new Doctor(salaVisita, null, horaI, horaF, dni, nombre, apellidos, tlf, null);
        
        if(lunesBox.isSelected()){nuevoD.addVisitDay(Days.Monday);} 
        if(martesBox.isSelected()){nuevoD.addVisitDay(Days.Tuesday);} 
        if(miercolesBox.isSelected()){nuevoD.addVisitDay(Days.Wednesday);} 
        if(juevesBox.isSelected()){nuevoD.addVisitDay(Days.Thursday);} 
        if(viernesBox.isSelected()){nuevoD.addVisitDay(Days.Friday);}  
        
        botonAceptarMedico = true;
        
        Stage stage = (Stage) aceptarMedico.getScene().getWindow();
        stage.close();
        
        
    }

    @FXML
    private void CancelarMedico(ActionEvent event) {
        iniciar();
        botonCancelarMedico = true;
        Stage stage = (Stage) aceptarMedico.getScene().getWindow();
        stage.close();
    }
    
    
    
}
