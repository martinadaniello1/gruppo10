package automatehub.model_view;


import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


public class TimeTrigger implements Trigger, Serializable{
    
    private LocalTime time;
    

    public TimeTrigger(String timeInserted) {
        
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(timeInserted,dtf).truncatedTo(ChronoUnit.MINUTES);  
        
    }
    
    public String getType() {
        return "When the clock hits ...";
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeTrigger other = (TimeTrigger) obj;
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }
    
    

}
