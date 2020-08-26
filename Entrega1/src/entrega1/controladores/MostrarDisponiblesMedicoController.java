/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1.controladores;

import entrega1.VerDoctor;
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
import model.Doctor;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class MostrarDisponiblesMedicoController implements Initializable {

    @FXML
    private ListView<Doctor> listaMedicos;
    @FXML
    private Button seleccionarMedico;
    @FXML
    private Button cancelarMedico;
    @FXML
    private TextField buscarMedico;
    
    ArrayList<Doctor> doctores;
    static ObservableList<Doctor> datosMedicos = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        doctores = PrincipalController.doctores;
        datosMedicos = FXCollections.observableList(doctores);        
        listaMedicos.setItems(datosMedicos);
        
        listaMedicos.setCellFactory(a -> new VerDoctor());
    }    

    @FXML
    private void SeleccionarPaciente(ActionEvent event) {
    }

    @FXML
    private void CancelarPaciente(ActionEvent event) {
    }
    /**
     * 
     * ArrayList<Patient> pacientes;
    static ObservableList<Patient> datosPacientes = null;
     pacientes = PrincipalController.pacientes;
        datosPacientes = FXCollections.observableList(pacientes);        
        listaPacientes.setItems(datosPacientes);
        
        listaPacientes.setCellFactory(a -> new VerPaciente());
     */
}

