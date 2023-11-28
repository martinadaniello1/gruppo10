package automatehub.model_view;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Luca
 */
public class TimeTrigger implements Trigger{
    
    private LocalTime time;
    

    public TimeTrigger(String timeInserted) {
        
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(timeInserted,dtf).truncatedTo(ChronoUnit.MINUTES);  
        
    }
   
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
       
    @Override
    public boolean check() {
        
        LocalTime actualTime= LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
                
        return actualTime.equals(this.time);
    }
    
    @Override
    public String toString() {
        return this.getTime().toString();
    }

}
