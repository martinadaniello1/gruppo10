package automatehub.model;

import java.util.List;
import java.util.LinkedList;

/**
 *Tale classe ha lo scopo di andare a gestire l'insieme di regole presenti nell'
 * applicativo, controllandone le condizioni periodicamente. 
 * Si implementa il pattern singleton. Tale implementazione del pattern singleton
 * risulta valido fintanto stiamo lavorando in ambito single-thread.
 * Come collezione per gestire l'insieme di regole si è scelta una LinkedList,
 * essendo che saranno necessarie frequenti visite della collezione e si 
 * potranno effettuare anche operazioni di aggiunta e rimozione.
 * @author adc01
 */

public class RuleManager {
    
    private List<Rule> ruleList;
    
    //Unica istanza della classe
    private static RuleManager instance = null ;
    
    //Costruttore privato
    private RuleManager(){
        this.ruleList = new LinkedList<>();
    };
    
    //Lazy initialization
    public static RuleManager getRuleManager(){
        //Crea l'oggetto solo se non esiste
        if(instance == null)
            instance = new RuleManager();
        
        return instance;
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
    
    public void getRuleList(){
        this.ruleList.toString();
    }
   
}