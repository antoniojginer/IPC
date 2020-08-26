/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2_ipc;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Grupo;
import modelo.Sesion;
import modelo.SesionTipo;

/**
 * FXML Controller class
 *
 * @author Antonio Jose
 */
public class GraficasController implements Initializable {

    @FXML
    private Text grupo;
    @FXML
    private LineChart<String, Long> graph;
    @FXML
    private NumberAxis ejeY;
    @FXML
    private CategoryAxis ejeX;

    XYChart.Series<String, Long> series1;
    XYChart.Series<String, Long> series2;
    XYChart.Series<String, Long> series3;
    private Grupo g;
    @FXML
    private Button home;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ejeX.setLabel("Fecha de realización");
        ejeY.setLabel("Tiempo del entrenamiento");
        
        series1 = new XYChart.Series<>();
        series1.setName("Tiempo real");
        
        series2 = new XYChart.Series<>();
        series2.setName("Tiempo de entrenamiento");
        
        series3 = new XYChart.Series<>();
        series3.setName("Tiempo de descanso");
        
        /**if(!datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()).getSesiones().isEmpty()){
                    estadisticas.setTitle("Historial de " + datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()).getCodigo());
                    sesionesRealizadas = datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()).getSesiones();
                    
                    
                    
                } else{
                    estadisticas.setTitle("Este grupo no dispone de historial de sesiones");
                }
        for(Sesion s:sesionesRealizadas){
                        long x = s.getDuracion().getSeconds();
                        LocalDateTime y = s.getFecha();
                        
                        SesionTipo aux = s.getTipo(); 
                        
                        long tiempoTrabajo = TiempoTrabajo(aux);
                        long tiempoDescanso = TiempoDescanso(aux);
                        
                        series1.getData().add(new XYChart.Data(y, x));
                        series2.getData().add(new XYChart.Data(y, tiempoTrabajo));
                        series3.getData().add(new XYChart.Data(y, tiempoDescanso));
                    }
                    estadisticas.getData().addAll(series1, series2, series3);*/
    }    
    
    public void getGrupo(Grupo g, long[] resTReal, long[] resTTrabajo, long[] resTDescanso, LocalDateTime[] resF){
        grupo.setText(g.getCodigo() + ": ");
        
        for(int i = 0; i<resF.length; i++){
            series1.getData().add(new XYChart.Data(resF[i].toString(),  resTReal[i]));
            series2.getData().add(new XYChart.Data(resF[i].toString(),  resTTrabajo[i]));
            series3.getData().add(new XYChart.Data(resF[i].toString(),  resTDescanso[i]));
        }
        
        graph.getData().addAll(series1, series2, series3);
    }

    @FXML
    private void Home(ActionEvent event) {
        Stage stage = (Stage) home.getScene().getWindow();
        stage.close();
    }
}
