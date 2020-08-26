/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2_ipc;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.SesionTipo;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class ListaSesionesController implements Initializable {

    @FXML
    private Button anadir;
    @FXML
    private Button borrar;
    @FXML
    private Button modificar;
    @FXML
    private Button descripcion;
    @FXML
    private Button seleccionar;
    @FXML
    private Button cancelar;
    @FXML
    private TableView<SesionTipo> sesiones;
    @FXML
    private TableColumn<SesionTipo, String> codigo;
    @FXML
    private TableColumn<SesionTipo, String> nEjercicios;
    @FXML
    private TableColumn<SesionTipo, String> tEjercicios;
    @FXML
    private TableColumn<SesionTipo, String> nCircuitos;
    
    private ObservableList<SesionTipo> listaSesiones;
    private ArrayList<SesionTipo> datosSesiones;
    private boolean primero, cancel;
    
    private SesionTipo selected;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        
        selected = new SesionTipo();
        setSelected(null);
        descripcion.setDisable(true);
        modificar.setDisable(true);
        borrar.setDisable(true);
        seleccionar.setDisable(true);
        
        codigo.setCellValueFactory(cellData1 -> new SimpleStringProperty(cellData1.getValue().getCodigo()));
        nEjercicios.setCellValueFactory(cellData2 -> new SimpleStringProperty(Integer.toString(cellData2.getValue().getNum_ejercicios())));
        tEjercicios.setCellValueFactory(cellData3 -> new SimpleStringProperty(Integer.toString(cellData3.getValue().getT_ejercicio())));
        nCircuitos.setCellValueFactory(cellData4 -> new SimpleStringProperty(Integer.toString(cellData4.getValue().getNum_circuitos())));
    
        sesiones.getSelectionModel().selectedIndexProperty().addListener((o, oldVal, newVal) -> {
            if((int) (newVal) >= 0){
                
                descripcion.setDisable(false);
                modificar.setDisable(false);
                borrar.setDisable(false);
                
                if(primero){
                    seleccionar.setDisable(false);
                }
                else{
                    seleccionar.setDisable(true);
                }
            }else{
                setSelected(null);
                descripcion.setDisable(true);
                modificar.setDisable(true);
                borrar.setDisable(true);
                seleccionar.setDisable(true);
            }
        });
        
        
    }    

    

    @FXML
    private void Anadir(ActionEvent event) {
        SesionTipo nuevaS = new SesionTipo();
        try{    
            FXMLLoader ventanaNuevaSesion = new FXMLLoader(getClass().getResource("NuevaSesionTipo.fxml"));
            Parent x = (Parent) ventanaNuevaSesion.load();
            
            NuevaSesionTipoController controladorAnadir = ventanaNuevaSesion.<NuevaSesionTipoController>getController();            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Nuevo grupo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            
            if(controladorAnadir.isFin()){
                nuevaS.setCodigo(controladorAnadir.getCod());
                nuevaS.setNum_ejercicios(controladorAnadir.getnE());
                nuevaS.setNum_circuitos(controladorAnadir.getnC());
                nuevaS.setT_ejercicio(controladorAnadir.gettE());
                nuevaS.setD_ejercicio(controladorAnadir.gettDE());
                nuevaS.setD_circuito(controladorAnadir.gettDC());
                nuevaS.setT_calentamiento(controladorAnadir.gettCal());
                
                listaSesiones.add(nuevaS);
            }
        }
        catch(Exception e){}
    }

    @FXML
    private void Borrar(ActionEvent event) {
        setSelected(listaSesiones.get(sesiones.getSelectionModel().getSelectedIndex()));
        try{
            FXMLLoader ventanaConfirmacion = new FXMLLoader(getClass().getResource("Confirmacion.fxml"));
            Parent x = (Parent) ventanaConfirmacion.load();

            ConfirmacionController controladorConfirmacion = ventanaConfirmacion.<ConfirmacionController>getController();
                

            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Confirmacion");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);            
            
            stage.showAndWait();
            
            if(controladorConfirmacion.isEliminar()){
                listaSesiones.remove(sesiones.getSelectionModel().getSelectedIndex());
            }
        }
        catch(Exception e){}
        setSelected(null);
        
    }

    @FXML
    private void Medificar(ActionEvent event) {
        setSelected(listaSesiones.get(sesiones.getSelectionModel().getSelectedIndex()));
        int aux = sesiones.getSelectionModel().getSelectedIndex();
        SesionTipo nuevaS = listaSesiones.get(aux);
        try{    
            FXMLLoader ventanaNuevaSesion = new FXMLLoader(getClass().getResource("NuevaSesionTipo.fxml"));
            Parent x = (Parent) ventanaNuevaSesion.load();
            
            NuevaSesionTipoController controladorAnadir = ventanaNuevaSesion.<NuevaSesionTipoController>getController();  
            //String c, String nE, String nC, String tE, String tDesEj, String tDesCa, String tCale
            controladorAnadir.mod(nuevaS.getCodigo(), 
                    Integer.toString(nuevaS.getNum_ejercicios()),
                    Integer.toString(nuevaS.getNum_circuitos()), 
                    Integer.toString(nuevaS.getT_ejercicio()), 
                    Integer.toString(nuevaS.getD_ejercicio()), 
                    Integer.toString(nuevaS.getD_circuito()), 
                    Integer.toString(nuevaS.getT_calentamiento()));
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Nuevo grupo");
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            
            if(controladorAnadir.isFin()){
                listaSesiones.remove(aux);
                nuevaS.setCodigo(controladorAnadir.getCod());
                nuevaS.setNum_ejercicios(controladorAnadir.getnE());
                nuevaS.setNum_circuitos(controladorAnadir.getnC());
                nuevaS.setT_ejercicio(controladorAnadir.gettE());
                nuevaS.setD_ejercicio(controladorAnadir.gettDE());
                nuevaS.setD_circuito(controladorAnadir.gettDC());
                nuevaS.setT_calentamiento(controladorAnadir.gettCal());
                
                listaSesiones.add(aux, nuevaS);
            }
        }
        catch(Exception e){}
        
        setSelected(null);
    }

    @FXML
    private void Descripcion(ActionEvent event) {
        setSelected(listaSesiones.get(sesiones.getSelectionModel().getSelectedIndex()));
        SesionTipo aux = getSelected();
        try{
            FXMLLoader ventanaDesc = new FXMLLoader(getClass().getResource("Descripcion.fxml"));
            Parent x = (Parent) ventanaDesc.load();
            
            DescripcionController controladorDes = ventanaDesc.<DescripcionController>getController();    
            controladorDes.codigo(aux.getCodigo());
            controladorDes.tiempoCalentamiento(Integer.toString(aux.getT_calentamiento()));
            controladorDes.tiempoEjer(Integer.toString(aux.getT_ejercicio()));
            controladorDes.tiempoDescanso(Integer.toString(aux.getD_ejercicio()) , Integer.toString(aux.getD_circuito()));
            controladorDes.numeroRep(Integer.toString(aux.getNum_ejercicios()), Integer.toString(aux.getNum_circuitos()));
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Ficha SesionTipo");
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.showAndWait();
        }
        catch(Exception e){}
        setSelected(null);
    }

    @FXML
    private void Seleccionar(ActionEvent event) {
        setSelected(listaSesiones.get(sesiones.getSelectionModel().getSelectedIndex()));
        setCancel(false);
        setSelected(listaSesiones.get(sesiones.getSelectionModel().getSelectedIndex()));
        
        Stage stage = (Stage) seleccionar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        setSelected(null);
        setCancel(true);
        
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
    }
    
    public void getListaSesiones(ArrayList<SesionTipo> e){
        datosSesiones = new ArrayList<SesionTipo>();
        datosSesiones = e;
        
        listaSesiones = FXCollections.observableList(datosSesiones);
        sesiones.setItems(listaSesiones);
    }
    
    public void getModo(boolean b){
        primero = b;
        
    }
    
    public SesionTipo getSelected() {
        return selected;
    }

    public void setSelected(SesionTipo selected) {
        this.selected = selected;
    }
   
    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
    
}
