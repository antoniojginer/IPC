/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega2_ipc;

import accesoBD.AccesoBD;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Grupo;
import modelo.Gym;
import modelo.Sesion;
import modelo.SesionTipo;

/**
 *
 * @author Antonio Jose
 */
public class Entrega2Controller implements Initializable {
    
   
    @FXML
    private Button guardar;
    @FXML
    private Button salir;
    @FXML
    private TableView<Grupo> grupos;
    @FXML
    private TableColumn<Grupo, String> grupo;
    @FXML
    private TableColumn<Grupo, String> descripcion;
    @FXML
    private Button go;
    @FXML
    private Button anadirGrupo;
    @FXML
    private Button modificar;
    @FXML
    private Button borrar;
    @FXML
    private Text sesionTipoText;
    @FXML
    private Button cambiar;
    @FXML
    private Button anadirSesion;
    @FXML
    private Button descripcionSesion;
    @FXML
    private Button listaSesiones;
    @FXML
    private Button mostrarEstadisticas;
    @FXML
    private Text estadisticasText;
    
    private Gym gimnasioGo;    
    private ObservableList<Grupo> datosGrupos;
    
    private SesionTipo sesionSeleccionada;

    
    private Grupo grupoSeleccionado;
    
    private ArrayList<SesionTipo> sesionesTipo;
    private ArrayList<Sesion> sesionesRealizadas;
    
    private boolean modo, guardado;
    
    
    
    

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        gimnasioGo = AccesoBD.getInstance().getGym();
        sesionesTipo = gimnasioGo.getTiposSesion();
        
        sesionTipoText.setText("No hay ninguna sesión seleccionada");
        
        borrar.setDisable(true);
        modificar.setDisable(true);
        go.setDisable(true);
        cambiar.setDisable(true);
        mostrarEstadisticas.setDisable(true);
        
        descripcionSesion.setDisable(true);
        setGuardado(false);
        setSesionSeleccionada(null);
        
        
        
        datosGrupos = FXCollections.observableList(gimnasioGo.getGrupos());
        grupos.setItems(datosGrupos);
        grupo.setCellValueFactory(cellData1 -> new SimpleStringProperty(cellData1.getValue().getCodigo()));
        descripcion.setCellValueFactory(cellData2 -> new SimpleStringProperty(cellData2.getValue().getDescripcion()));
        
        estadisticasText.setText("");
        
        
        
        grupos.getSelectionModel().selectedIndexProperty().addListener((o, oldVal, newVal) -> {
            if((int) (newVal) >= 0){
                borrar.setDisable(false);
                modificar.setDisable(false);
                cambiar.setDisable(false);
                setGrupoSeleccionado(datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()));
                mostrarEstadisticas.setDisable(false);
                
                if(datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()).getSesiones().isEmpty()){
                    estadisticasText.setText("El grupo seleccionado no dispone de historial de sesiones.");
                    mostrarEstadisticas.setDisable(true);
                }else{
                    estadisticasText.setText("");
                    mostrarEstadisticas.setDisable(false);
                }
                
                if(datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()).getDefaultTipoSesion() != null){
                    setSesionSeleccionada(datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()).getDefaultTipoSesion());
                    descripcionSesion.setDisable(false);
                    
                    go.setDisable(false);
                    sesionTipoText.setText("Sesión seleccionada: " + datosGrupos.get(grupos.getSelectionModel().getSelectedIndex()).getDefaultTipoSesion().getCodigo() );
                }
                else{
                    setSesionSeleccionada(null);
                    descripcionSesion.setDisable(true);
                    
                    go.setDisable(true);
                    sesionTipoText.setText("No hay ninguna sesión seleccionada");
                }
            }
            else{
                setGrupoSeleccionado(null);
                mostrarEstadisticas.setDisable(true);
                borrar.setDisable(true);
                modificar.setDisable(true);
                cambiar.setDisable(true);
            }
        });
        
        
        
        
        
    }    

    

    @FXML
    private void Guardar(ActionEvent event) {
        setGuardado(true);
        AccesoBD.getInstance().salvar();
            
    }

    

    @FXML
    private void Salir(ActionEvent event) {
        if(!isGuardado()){
            try{
                FXMLLoader ventanaExit = new FXMLLoader(getClass().getResource("Exit.fxml"));
                Parent x = (Parent) ventanaExit.load();

                ExitController controladorGo = ventanaExit.<ExitController>getController();
                

                Stage stage = new Stage();
                stage.setScene(new Scene(x));
                stage.setTitle("Confirmacion");
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                long inicio = System.currentTimeMillis();

                stage.showAndWait();
                
                if(controladorGo.isSalvar()){
                    setGuardado(true);
                    AccesoBD.getInstance().salvar();
                    Stage stagex = (Stage) salir.getScene().getWindow();
                    stagex.close();
                }
                if(!controladorGo.isSeguir() && !controladorGo.isSalvar()){
                    Stage stagex = (Stage) salir.getScene().getWindow();
                    stagex.close();
                }
            } 
            catch(Exception e){}
        } else{
            Stage stagex = (Stage) salir.getScene().getWindow();
            stagex.close();
        }
        
        
    }

    @FXML
    private void Go(ActionEvent event) {
        Grupo g = getGrupoSeleccionado();
        SesionTipo aux = getSesionSeleccionada();
        
        try{
            FXMLLoader ventanaGo = new FXMLLoader(getClass().getResource("Go.fxml"));
            Parent x = (Parent) ventanaGo.load();
            
            GoController controladorGo = ventanaGo.<GoController>getController();
            controladorGo.datos(tiempos(aux), fases(aux));
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Entrenamiento");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            long inicio = System.currentTimeMillis();
            
            stage.showAndWait();
            
            long fin = System.currentTimeMillis();
            Sesion nuevaS = new Sesion();
            nuevaS.setTipo(aux);
            /**Interpreto que según el enunciado de la entrega esto se realiza automaticamente y por tanto
             * el registro del historial de sesiones se hace a tiempo real
             */
            nuevaS.setFecha(LocalDateTime.now());
            /**Interpreto que según el enunciado de la entrega esto se corresponde al tiempo total que 
             * que dura la sesión, es decir, el tiempo que transcurre desde que se inicia la ventana 
             * de entrenamiento hasta que se cierra
             */
            long duracion = fin - inicio;
            
            Duration duration = Duration.ofMillis(duracion);
            nuevaS.setDuracion(duration);
            
            g.getSesiones().add(nuevaS);
            
            setGuardado(false);
            
        }
        catch(Exception e){}
    }
    
    @FXML
    private void AnadirGrupo(ActionEvent event) {
        Grupo nuevoG = new Grupo();
        try{
            FXMLLoader ventanaNuevoGrupo = new FXMLLoader(getClass().getResource("Anadir.fxml"));
            Parent x = (Parent) ventanaNuevoGrupo.load();
            
            AnadirController controladorAnadir = ventanaNuevoGrupo.<AnadirController>getController();
            controladorAnadir.getListaSesiones(getSesionesTipo());
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Nuevo grupo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            
            if(controladorAnadir.isFin()){
                nuevoG.setCodigo(controladorAnadir.getCod());
                nuevoG.setDescripcion(controladorAnadir.getDes());
                nuevoG.setDefaultTipoSesion(controladorAnadir.getSes());
                ArrayList<Sesion> aux = new ArrayList<Sesion>();
                nuevoG.setSesiones(aux);
                
                datosGrupos.add(nuevoG);
                
                setGuardado(false);
            }
        }
        catch(Exception e){}
    }
    
    @FXML
    private void Modificar(ActionEvent event) {
        int aux = grupos.getSelectionModel().getSelectedIndex();
        Grupo nuevoG = datosGrupos.get(aux);
        try{
            FXMLLoader ventanaNuevoGrupo = new FXMLLoader(getClass().getResource("Anadir.fxml"));
            Parent x = (Parent) ventanaNuevoGrupo.load();
            
            AnadirController controladorAnadir = ventanaNuevoGrupo.<AnadirController>getController();
            controladorAnadir.getListaSesiones(getSesionesTipo());
            controladorAnadir.nuevoNombre(nuevoG.getCodigo());
            controladorAnadir.nuevaDes(nuevoG.getDescripcion());
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Editar grupo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            
            if(controladorAnadir.isFin()){
                datosGrupos.remove(aux);
                nuevoG.setCodigo(controladorAnadir.getCod());
                nuevoG.setDescripcion(controladorAnadir.getDes());
                nuevoG.setDefaultTipoSesion(controladorAnadir.getSes());
                ArrayList<Sesion> aux1 = new ArrayList<Sesion>();
                nuevoG.setSesiones(aux1);
                
                
                datosGrupos.add(aux, nuevoG);
                
                setGuardado(false);
            }
        }
        catch(Exception e){}
    }

    @FXML
    private void Borrar(ActionEvent event) {
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
                datosGrupos.remove(grupos.getSelectionModel().getSelectedIndex());
                setGuardado(false);
            }
        }
        catch(Exception e){}
        
    }

    @FXML
    private void CambiarSesion(ActionEvent event) {
        setModo(true);
        
        try{    
            FXMLLoader ventanaNuevaSesion = new FXMLLoader(getClass().getResource("ListaSesiones.fxml"));
            Parent x = (Parent) ventanaNuevaSesion.load();
            
            ListaSesionesController controladorSesiones = ventanaNuevaSesion.<ListaSesionesController>getController();      
            controladorSesiones.getListaSesiones(getSesionesTipo());
            controladorSesiones.getModo(isModo());
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Seleccionar SesionTipo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            setSesionSeleccionada(controladorSesiones.getSelected());
            if(getSesionSeleccionada() == null){
                sesionTipoText.setText("No hay ninguna sesión seleccionada");
                go.setDisable(true);
                descripcionSesion.setDisable(true);
            }else{
                if(!controladorSesiones.isCancel()){
                    
                    sesionTipoText.setText("Sesión seleccionada: " + controladorSesiones.getSelected().getCodigo());
                    go.setDisable(false);
                    descripcionSesion.setDisable(false);
            }   
            }
            
            
            
        }catch(Exception e){}
            
    }

    @FXML
    private void AnadirSesionTipo(ActionEvent event) {
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
                
                anadirNuevaSesionTipo(nuevaS);
                
                setGuardado(false);
            }
        }
        catch(Exception e){}
        
        
        
        System.out.println(nuevaS.getCodigo());
    }

    @FXML
    private void DescripcionSesion(ActionEvent event) {
        SesionTipo aux = getSesionSeleccionada();
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
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.showAndWait();
        }
        catch(Exception e){}
    }

    @FXML
    private void ListaSesiones(ActionEvent event) {
        setModo(false);
        
        try{    
            FXMLLoader ventanaNuevaSesion = new FXMLLoader(getClass().getResource("ListaSesiones.fxml"));
            Parent x = (Parent) ventanaNuevaSesion.load();
            
            ListaSesionesController controladorSesiones = ventanaNuevaSesion.<ListaSesionesController>getController();      
            controladorSesiones.getListaSesiones(getSesionesTipo());
            controladorSesiones.getModo(isModo());
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Lista Sesiones Tipo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            
            setGuardado(false);
            
        }catch(Exception e){}
    }
    
    @FXML
    private void MostrarEstadisticas(ActionEvent event) {
        Grupo aux = datosGrupos.get(grupos.getSelectionModel().getSelectedIndex());
        ArrayList<Sesion> x1 = aux.getSesiones();
        long[] resTReal = new long[x1.size()];
        long[] resTTrabajo = new long[x1.size()];
        long[] resTDescanso = new long[x1.size()];
        LocalDateTime[] resF = new LocalDateTime[x1.size()];
        int i = 0;
        for(Sesion s:x1){
            System.out.println(s.getTipo().getCodigo());
            resF[i] = s.getFecha();
            resTReal[i] = s.getDuracion().getSeconds();
            resTTrabajo[i] = TiempoTrabajo(s.getTipo());
            resTDescanso[i] = TiempoDescanso(s.getTipo());
            i++;
        }
        try{    
            FXMLLoader ventanaGraficas = new FXMLLoader(getClass().getResource("Graficas.fxml"));
            Parent x = (Parent) ventanaGraficas.load();
            
            GraficasController controladorGraficas = ventanaGraficas.<GraficasController>getController();      
            controladorGraficas.getGrupo(aux, resTReal, resTTrabajo, resTDescanso, resF);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(x));
            stage.setTitle("Lista Sesiones Tipo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            
        }catch(Exception e){}
        
    }
    
    public int[] tiempos(SesionTipo s){
        int aux = s.getNum_ejercicios() * s.getNum_circuitos() ;
        if(s.getT_calentamiento() > 0){aux++;}
        if(s.getD_circuito() > 0){aux += s.getNum_circuitos();} 
        if(s.getD_ejercicio() > 0){aux += s.getNum_ejercicios()*s.getNum_circuitos();}
        int[] res = new int[aux];
        
        int i = 0;
        int t = s.getT_calentamiento();
        if(t>0){
            res[i] = t;
            i++;
        }
        
        t = s.getT_ejercicio();
        int des = s.getD_ejercicio();
        if(s.getT_calentamiento() != 0){
            for(int x = s.getNum_circuitos(); x>0; x--){
                int rep = s.getNum_ejercicios();
                if(des>0){rep = rep*2;}
                while(rep > 0){            
                    if(i%2 != 0){
                        res [i] = t;
                        rep--;
                    }
                    else{
                        if(des>0){
                            res[i] = des;
                            rep--;
                        }                        
                    }
                    i++;
                }
                if(s.getD_circuito()>0){
                    res[i] = sesionSeleccionada.getD_circuito();
                    i++;
                }    
            }
        } else{
            for(int x = s.getNum_circuitos(); x>0; x--){
                int rep = s.getNum_ejercicios();
                if(des>0){rep = rep*2;}
                while(rep > 0){            
                    if(i%2 == 0){
                        res [i] = t;
                        rep--;
                    }
                    else{
                        res[i] = des;
                        rep--;
                    }
                    i++;
                }
                if(s.getD_circuito()>0){
                    res[i] = sesionSeleccionada.getD_circuito();
                    i++;
                }     
            }
        }
        
        
        return res;
    }
    
    public String[] fases(SesionTipo s){
        int aux = s.getNum_ejercicios() * s.getNum_circuitos() ;
        if(s.getT_calentamiento() > 0){aux++;}
        if(s.getD_circuito() > 0){aux += s.getNum_circuitos();} 
        if(s.getD_ejercicio() > 0){aux += s.getNum_ejercicios()*s.getNum_circuitos();}
        String[] res = new String[aux];
        
        int i = 0;
        int t = s.getT_calentamiento();
        if(t>0){
            res[i] = "Calentamiento";
            i++;
        }
        
        t = s.getT_ejercicio();
        int des = s.getD_ejercicio();
        if(s.getT_calentamiento() != 0){
            for(int x = s.getNum_circuitos(); x>0; x--){
                int rep = s.getNum_ejercicios();
                if(des>0){rep = rep*2;}
                while(rep > 0){            
                    if(i%2 != 0){
                        res [i] = "Ejercicio";
                        rep--;
                    }
                    else{
                        if(des>0){
                            res[i] = "Descanso";
                            rep--;
                        }                        
                    }
                    i++;
                }
                if(s.getD_circuito()>0){
                    res[i] = "DescansoC";
                    i++;
                }    
            }
        } else{
            for(int x = s.getNum_circuitos(); x>0; x--){
                int rep = s.getNum_ejercicios();
                if(des>0){rep = rep*2;}
                while(rep > 0){            
                    if(i%2 == 0){
                        res [i] = "Ejercicio";
                        rep--;
                    }
                    else{
                        res[i] = "Descanso";
                        rep--;
                    }
                    i++;
                }
                if(s.getD_circuito()>0){
                    res[i] = "DescansoC";
                    i++;
                }     
            }
        }
        
        
        return res;
    }
    
    public long TiempoReal(SesionTipo s){
        int[] tiempos = tiempos(s);
        long res = 0;
        for(int i = 0; i<tiempos.length; i++){
            res += tiempos[i];
        }
        
        return res;
    }
    public long TiempoTrabajo(SesionTipo s){
        String[] fases = fases(s);
        int[] tiempos = tiempos(s);
        long res = 0;
        for(int i = 0; i<tiempos.length; i++){
            if(fases[i] == "Ejercicio") res += tiempos[i];
        }
        
        return res;
    }
    
    public long TiempoDescanso(SesionTipo s){
        String[] fases = fases(s);
        int[] tiempos = tiempos(s);
        long res = 0;
        for(int i = 0; i<tiempos.length; i++){
            if(fases[i] == "Descanso" || fases[i] == "DescansoC") res += tiempos[i];
        }
        
        return res;
    }

    public void anadirNuevaSesionTipo(SesionTipo s){
        sesionesTipo.add(s);
    }
    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    public boolean isModo() {
        return modo;
    }

    public void setModo(boolean modo) {
        this.modo = modo;
    }
    
    public ArrayList<SesionTipo> getSesionesTipo() {
        return sesionesTipo;
    }
    
    public SesionTipo getSesionSeleccionada() {
        return sesionSeleccionada;
    }

    public void setSesionSeleccionada(SesionTipo sesionSeleccionada) {
        this.sesionSeleccionada = sesionSeleccionada;
    }

    public Grupo getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }
    
}


