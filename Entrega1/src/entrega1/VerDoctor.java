/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1;



import java.time.format.DateTimeFormatter;
import javafx.scene.control.ListCell;
import model.Doctor;

/**
 *
 * @author Antonio Jose
 */
public class VerDoctor extends ListCell<Doctor>{
    @Override
    protected void updateItem(Doctor item, boolean empty){ 
        super.updateItem(item, empty); // Obligatoria esta llamada
        if (item==null || empty) setText(null);
        else{
            String horaVisita;
            DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
            String horaVisitaI = item.getVisitStartTime().format(formatter);
            String horaVisitaF = item.getVisitEndTime().format(formatter);
            
            String doctor = item.getName() + " " + item.getSurname();
            
            
            setText(doctor + "  ||  "  +  horaVisitaI + "  ||  " + horaVisitaF);                   
        }
}
}
