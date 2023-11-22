package automatehub.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Luca
 */
public class TimeTrigger implements Trigger{
    
    private LocalDateTime time;

    public TimeTrigger(String timeInserted) {        
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm");
        this.time = LocalDateTime.parse(timeInserted,dtf).truncatedTo(ChronoUnit.MINUTES);
    }

    public TimeTrigger(String nameTrigger, String timeInserted) {
        nameTrigger=nameTrigger;
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm");
        this.time = LocalDateTime.parse(timeInserted,dtf).truncatedTo(ChronoUnit.MINUTES);  
    }
   
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    
    @Override
    public String getNameTrigger(){
        return nameTrigger;
    }
    
    public void setNameTrigger(String nameTrigger){
        nameTrigger= nameTrigger;
    }
       
    @Override
    public boolean check() {
        
        LocalDateTime actualTime= LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                
        return actualTime.isEqual(time);
    }
    
    
}
