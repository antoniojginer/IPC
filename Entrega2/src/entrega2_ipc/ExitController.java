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
public class ExitController implements Initializable {

    @FXML
    private Button guardar;
    @FXML
    private Button si;
    @FXML
    private Button no;
    private boolean salvar;
    private boolean seguir;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSalvar(false);
        setSeguir(false);
    }    

    @FXML
    private void Guardar(ActionEvent event) {
        setSalvar(true);
        setSeguir(false);
        Stage stage = (Stage) guardar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Si(ActionEvent event) {
        setSalvar(false);
        setSeguir(false);
        Stage stage = (Stage) si.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void No(ActionEvent event) {
        setSalvar(false);
        setSeguir(true);
        Stage stage = (Stage) no.getScene().getWindow();
        stage.close();
    }
    
    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

    public boolean isSeguir() {
        return seguir;
    }

    public void setSeguir(boolean seguir) {
        this.seguir = seguir;
    }

    
}
