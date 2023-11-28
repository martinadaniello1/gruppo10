package automatehub.model_view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *Tale classe ha lo scopo di andare a gestire l'insieme di regole presenti nell'
 * applicativo, controllandone le condizioni periodicamente. 
 * Si implementa il pattern singleton. Tale implementazione del pattern singleton
 * risulta valido fintanto stiamo lavorando in ambito single-thread.
 * Come collezione per gestire l'insieme di regole si è scelta una ArrayList.
 * @author adc01
 */

public class RuleManagerService extends Service implements Serializable{
    
    private ObservableList<Rule> ruleList;
    
    //Unica istanza della classe
    private static RuleManagerService instance = null ;
    
    //Costruttore privato
    private RuleManagerService(){
        this.ruleList = FXCollections.observableArrayList();
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
                    // Verifica le condizioni e esegui le azioni per ogni regola attiva
                    for (Rule regola : ruleList) {
                        if(regola.getActive()){
                            if(regola.getTrigger().check()){
                                Platform.runLater(() -> {
                                    regola.getAction().execute();
                                    System.out.println("regola verificata con esito positivo:" + regola.toString()); //Logging
                                    regola.setActive(false);
                                });
                            } else 
                                System.out.println("regola verficata con esito negativo:" + regola.toString()); //Logging
                        }
                    }
                    //Si attendono 3 secondi prima della prossima verifica
                    Thread.sleep(3000);
                }
                return null;
            }
        };
    }
    
    public void addRule(Rule r) {
        if(r == null)
            throw new IllegalArgumentException("Regola non valida");
        synchronized(ruleList){
        this.ruleList.add(r);
        //Logging
        System.out.println("addRule in RuleManagerService eseguita correttamente");
        System.out.println(r.toString());
        }           
    }
    
    public void removeRule (Rule r){
        synchronized(ruleList){
        if(this.ruleList.remove(r)==false)
            throw new IllegalArgumentException("Regola non presente");
        }
        //Logging
        System.out.println("removeRule in RuleManagerService eseguita correttamente");
        System.out.println(r.toString());
    }    
    
    public ObservableList<Rule> getRuleList(){
        return this.ruleList;
    }
    
    public void importRule() throws IOException{
        FileInputStream fis = new FileInputStream("SavedRule.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            
        }finally{
            ois.close();
        }
    }
    
    public void exportRule() throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedRule.txt"));       
        try  {
             for (Rule regola : ruleList){
                 out.writeChars(regola.getNameRule());
                 out.writeChars(regola.getAction().toString());
                 out.writeChars(regola.getTrigger().toString());
                 out.writeBoolean(regola.getActive());
             }
            
            System.out.println("Salvataggio completato");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
    ///throws FileNotFoundException, IOException 
}
