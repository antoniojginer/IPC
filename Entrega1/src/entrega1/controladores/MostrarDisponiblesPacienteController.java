/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1.controladores;

import entrega1.VerPaciente;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class MostrarDisponiblesPacienteController implements Initializable {

    @FXML
    private ListView<Patient> listaPaciente;
    @FXML
    private Button seleccionarPaciente;
    @FXML
    private Button cancelarPaciente;
    @FXML
    private TextField buscarPaciente;

    
    ArrayList<Patient> pacientes;
    static ObservableList<Patient> datosPaciente;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pacientes = PrincipalController.paciente;
        datosPaciente = FXCollections.observableList(pacientes);        
        listaPaciente.setItems(datosPaciente);
        
        listaPaciente.setCellFactory(a -> new VerPaciente());
    }    

    @FXML
    private void SeleccionarPaciente(ActionEvent event) {
    }

    @FXML
    private void CancelarPaciente(ActionEvent event) {
    }
    
}
