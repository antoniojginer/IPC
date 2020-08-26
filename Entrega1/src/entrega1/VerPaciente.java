/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1;

import javafx.scene.control.ListCell;
import model.Patient;

/**
 *
 * @author Antonio Jose
 */
public class VerPaciente extends ListCell<Patient>{
    @Override
    protected void updateItem(Patient item, boolean empty){ 
        super.updateItem(item, empty); // Obligatoria esta llamada
        if (item==null || empty) setText(null);
        else{
            
            String nombre = item.getName();
            String apellidos = item.getSurname();
            String tlf = item.getTelephon();
            setText(nombre + " " + apellidos + "  ||  ");                   
        }
}
    
    
}
