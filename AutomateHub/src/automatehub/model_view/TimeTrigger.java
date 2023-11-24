package automatehub.model_view;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Luca
 */
public class TimeTrigger implements Trigger{
    private String nameTrigger;
    private LocalDateTime time;
    

    public TimeTrigger(String timeInserted, String nameTrigger) {
        this.nameTrigger=nameTrigger;
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
    
    @Override
    public void setNameTrigger(String nameTrigger){
        this.nameTrigger= nameTrigger;
    }
       
    @Override
    public boolean check() {
        
        LocalDateTime actualTime= LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                
        return actualTime.isEqual(time);
    }
    
    
}
