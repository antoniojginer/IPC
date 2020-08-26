/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega1;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ListCell;
import model.Appointment;
 



/**
 *
 * @author Antonio Jose
 */




public class VerCitas extends ListCell<Appointment> {
    @Override
    protected void updateItem(Appointment item, boolean empty){ 
        super.updateItem(item, empty); // Obligatoria esta llamada
        if (item==null || empty) setText(null);
        else{
            DateTimeFormatter date;
            date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fecha = item.getAppointmentDateTime().format(date);
            String doctor = item.getDoctor().getName() + " " + item.getDoctor().getSurname();
            String paciente = item.getPatient().getName() + " " + item.getPatient().getSurname();
            
            setText(fecha + "  ||  "  +  paciente + "  ||  " + doctor);                   
        }
}
    
}
