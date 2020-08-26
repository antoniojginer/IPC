/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class AñadirPacienteController implements Initializable {

    @FXML
    private TextField nombrePaciente;
    @FXML
    private TextField apellidosPaciente;
    @FXML
    private TextField dniPaciente;
    @FXML
    private TextField telefonoPaciente;
    @FXML
    private Button aceptarPaciente;
    @FXML
    private Button cancelarPaciente;
    
    String nombre, apellidos, dni, telefono;
    protected static Patient nuevoP;
    protected static boolean botonAceptarPaciente, botonCancelarPaciente;

    /**
     * Initializes the controller class.
     * Patient(String identifier, String name, String surname, String telephon, Image photo)
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciar();
    }    
    
    private void iniciar(){
        nombre = "";
        apellidos = "";
        dni = "";
        telefono= "";
        nuevoP = null;
        botonCancelarPaciente = false;
        botonAceptarPaciente = false;
        
        //pruebas
        aceptarPaciente.setDisable(false);
    }

    @FXML
    private void AceptarPaciente(ActionEvent event) {
        botonAceptarPaciente = true;
        
        dni = dniPaciente.getText();
        nombre = nombrePaciente.getText();
        apellidos = apellidosPaciente.getText();
        telefono = telefonoPaciente.getText();
        
        nuevoP = new Patient(dni, nombre, apellidos, telefono, null);        
        
        Stage stage = (Stage) aceptarPaciente.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void CancelarPaciente(ActionEvent event) {
        iniciar();
        botonCancelarPaciente = true;
        Stage stage = (Stage) aceptarPaciente.getScene().getWindow();
        stage.close();
    }
    
}
