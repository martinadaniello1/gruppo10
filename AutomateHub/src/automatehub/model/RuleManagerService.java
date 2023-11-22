package automatehub.model;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *Tale classe ha lo scopo di andare a gestire l'insieme di regole presenti nell'
 * applicativo, controllandone le condizioni periodicamente. 
 * Si implementa il pattern singleton. Tale implementazione del pattern singleton
 * risulta valido fintanto stiamo lavorando in ambito single-thread.
 * Come collezione per gestire l'insieme di regole si è scelta una ObservableList,
 * @author adc01
 */

public class RuleManagerService extends Service{
    
    private Collection<Rule> ruleList;
    
    //Unica istanza della classe
    private static RuleManagerService instance = null ;
    
    //Costruttore privato
    private RuleManagerService(){
        this.ruleList =  new CopyOnWriteArrayList<>();
    };
    
    //Lazy initialization
    public static RuleManagerService getRuleManager(){
        //Crea l'oggetto solo se non esiste
        if(instance == null)
            instance = new RuleManagerService();
        
        return instance;
    }
    
       @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    // Verifica le condizioni e esegui le azioni per ogni regola
                    for (Regola regola : ruleList) {
                        if(regola.check())
                            regola.execute();
                    }
                    //Si attendono 2 secondi prima della prossima verifica
                    Thread.sleep(2000);
                }
                return null;
            }
        };
    }
    
    public void addRule(Rule r) {
        if(r == null)
            throw new IllegalArgumentException("Regola non valida");
       
        this.ruleList.add(r);
    }
    public void removeRule (Rule r){
        if(this.ruleList.remove(r)==false)
            throw new IllegalArgumentException("Regola non presente");
    }    
    
    public CopyOnWriteArrayList<Rule> getRuleList(){
        return this.ruleList;
    }
}