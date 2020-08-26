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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Grupo;
import modelo.SesionTipo;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class AnadirController implements Initializable {

    @FXML
    private ComboBox<SesionTipo> sesionDefecto;
    @FXML
    private TextField codigo;
    @FXML
    private TextField descripcion;
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;
    @FXML
    private Text error;
    
    private ObservableList<SesionTipo> sesiones;
    private ArrayList<SesionTipo> datosSesion;
    private String cod, des;
    private SesionTipo ses;
    private boolean fin;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fin = false;
        cod = "";
        des = "";
        ses = null;     
        
        guardar.setDisable(true);

        /**sesionDefecto.setButtonCell(buttonCell -> new SimpleStringProperty(butonCell.getValue().getCodigo()));*/
        
        sesionDefecto.setCellFactory((ListView<SesionTipo> l) -> new ListCell<SesionTipo>() {
            @Override
            protected void updateItem(SesionTipo item, boolean empty){
                super.updateItem(item, empty);
                if(item == null || empty){}
                else{setText(item.getCodigo());}
            }
        });
        
        codigo.textProperty().addListener((o, oldVal, newVal) -> {
            if(newVal.isEmpty()){
                error.setText("Introduzca el identificador del grupo.");
                guardar.setDisable(true);
            } else{
                if(descripcion.getText().isEmpty()){
                    error.setText("Introduzca una descripción del grupo.");
                    guardar.setDisable(true);
                } else{
                    error.setText("");
                    guardar.setDisable(false);
                }
            }
        });
        
        descripcion.textProperty().addListener((o, oldVal, newVal) -> {
            if(newVal.isEmpty()){
                
                error.setText("Introduzca una descripción del grupo.");
                guardar.setDisable(true);
            } else{
                if(codigo.getText().isEmpty()){
                    error.setText("Introduzca el identificador del grupo.");
                    guardar.setDisable(true);
                } else{
                    error.setText("");
                    guardar.setDisable(false);
                }
            }
        });
        
        
    }      

    @FXML
    private void Guardar(ActionEvent event) {
        setFin(true);
        setCod(codigo.getText());
        setDes(descripcion.getText());
        setSes(sesionDefecto.getSelectionModel().getSelectedItem());
        
        Stage stage = (Stage) guardar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        setFin(false);
        
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
    }
    
    public void getListaSesiones(ArrayList<SesionTipo> e){
        datosSesion = new ArrayList<SesionTipo>();
        datosSesion = e;
        
        sesiones = FXCollections.observableList(datosSesion);
        sesionDefecto.setItems(sesiones);     
    }
    
    public void nuevoNombre(String s){
        codigo.setText(s);
    }
    
    public void nuevaDes(String s){
        descripcion.setText(s);
        
    }
    
    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }
    
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public SesionTipo getSes() {
        return ses;
    }

    public void setSes(SesionTipo ses) {
        this.ses = ses;
    }
}
