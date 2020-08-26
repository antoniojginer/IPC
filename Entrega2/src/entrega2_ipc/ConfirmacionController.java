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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class ConfirmacionController implements Initializable {

    @FXML
    private Button si;
    @FXML
    private Button no;
    
    private boolean eliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setEliminar(false);
    }    

    @FXML
    private void Si(ActionEvent event) {
        setEliminar(true);
        
        Stage stage = (Stage) si.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void No(ActionEvent event) {
        setEliminar(false);
        
        Stage stage = (Stage) si.getScene().getWindow();
        stage.close();
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
    
}
