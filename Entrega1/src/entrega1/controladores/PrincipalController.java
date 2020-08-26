/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1.controladores;

import DBAccess.ClinicDBAccess;
import entrega1.VerCitas;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import model.Appointment;
import model.Doctor;
import model.Patient;
import utils.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class PrincipalController implements Initializable {

    @FXML
    private ListView<Appointment> listaCitas;
    @FXML
    private Text fechaText;
    @FXML
    private MenuItem borrarCitas;
    @FXML
    private MenuItem mostrarCitas;
    
    ClinicDBAccess clinica;
    static ArrayList<Patient> paciente;
    static ArrayList<Doctor> doctores;    
    ArrayList<Appointment> citas;
    ObservableList<Appointment> datos = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciar();
    }    
    private void iniciar(){
        clinica = ClinicDBAccess.getSingletonClinicDBAccess();
        paciente =  clinica.getPatients();
        doctores = clinica.getDoctors();
        citas = clinica.getAppointments();
        
        //Lista de citas
        datos = FXCollections.observableList(citas);
        
        listaCitas.setItems(datos);
        listaCitas.setCellFactory(a -> new VerCitas());
        
        
        listaCitas.focusedProperty().addListener(new ChangeListener<Boolean>()
				{	@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						if (listaCitas.getSelectionModel().getSelectedIndex()>=0) {
							borrarCitas.setDisable(false);
							mostrarCitas.setDisable(false);
    }    
    
					}
    
				});

}
    

    @FXML
    private void AñadirCita(ActionEvent event) {
        try{
            FXMLLoader ventanaAñadirCita = new FXMLLoader(getClass().getResource("/interfaces/AñadirCita.fxml"));
            Parent x = (Parent) ventanaAñadirCita.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("NUEVA CITA");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e){}
        
    }

    @FXML
    private void BorrarCita(ActionEvent event) {
        
    }

    @FXML
    private void MostrarCita(ActionEvent event) {
        try{
            FXMLLoader ventanaMostrarCita = new FXMLLoader(getClass().getResource("/interfaces/MostrarCita.fxml"));
            Parent x = (Parent) ventanaMostrarCita.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("CITA");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e){}
    }

    @FXML
    private void AñadirMedico(ActionEvent event) {
        try{
            FXMLLoader ventanaAñadirMedico = new FXMLLoader(getClass().getResource("/interfaces/AñadirMedico.fxml"));
            Parent x = (Parent) ventanaAñadirMedico.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("NUEVO MEDICO");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            if(AñadirMedicoController.botonAceptarMedico == true){
                MostrarDisponiblesMedicoController.datosMedicos.add(AñadirMedicoController.nuevoD);
                //boolean guardado = clinica.saveDB();
            }
            
            
            
        }
        catch(Exception e){}
        finally{}
    }

    @FXML
    private void BorrarMedico(ActionEvent event) {
        try {
            FXMLLoader ventanaBorrarMedico = new FXMLLoader(getClass().getResource("/interfaces/MostrarDisponiblesMedico.fxml"));
            Parent x = (Parent) ventanaBorrarMedico.load();
        
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("ELIMINAR MEDICO");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e){}
    }

    @FXML
    private void MostrarMedico(ActionEvent event) {
        try {
            FXMLLoader ventanaMostrarMedico = new FXMLLoader(getClass().getResource("/interfaces/MostrarDisponiblesMedico.fxml"));
            Parent x = (Parent) ventanaMostrarMedico.load();
        
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("BUSCAR MEDICO");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e){}
    
    }

    @FXML
    private void AñadirPaciente(ActionEvent event) {
        try {
            FXMLLoader ventanaAñadirPaciente = new FXMLLoader(getClass().getResource("/interfaces/AñadirPaciente.fxml"));
            Parent x = (Parent) ventanaAñadirPaciente.load();
        
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("AÑADIR PACIENTE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            if(AñadirPacienteController.botonAceptarPaciente == true){
                MostrarDisponiblesPacienteController.datosPaciente.add(AñadirPacienteController.nuevoP);
                //boolean guardado = clinica.saveDB();
            }
            
            
        }
        catch(Exception e){}
        finally{}
    }

    @FXML
    private void BorrarPaciente(ActionEvent event) {
        try {
            FXMLLoader ventanaMostrarPaciente = new FXMLLoader(getClass().getResource("/interfaces/MostrarDisponiblesPaciente.fxml"));
            Parent x = (Parent) ventanaMostrarPaciente.load();
        
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("BUSCAR PACIENTE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e){}
    }

    @FXML
    private void MostrarPaciente(ActionEvent event) {
        try {
            FXMLLoader ventanaMostrarPaciente = new FXMLLoader(getClass().getResource("/interfaces/MostrarDisponiblesPaciente.fxml"));
            Parent x = (Parent) ventanaMostrarPaciente.load();
        
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("BUSCAR PACIENTE");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(Exception e){}
        
    }
    
}

/**class AppointmentCell extends ListCell<Appointment>{
    @Override
    protected void updateItem(LocalDateTime appointmentDateTime, Doctor doctor, Patient patient){
        
    }
}*/
