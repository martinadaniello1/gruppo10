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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void importRule() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("SavedRule.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        System.out.println("File aperto");
        if(ois==null){
            return ;
        }

        try {
            while (true) {
                String name = ois.readUTF();
                String actionClassName = ois.readUTF();
                String actionMsg = ois.readUTF();
                String triggerClassName = ois.readUTF();
                String triggerMsg = ois.readUTF();
                boolean active = ois.readBoolean();
                System.out.println("Lettura effettuata");
                // Ottieni la classe Action e Trigger usando il nome della classe
                Class<?> actionClass = Class.forName(actionClassName);
                Class<?> triggerClass = Class.forName(triggerClassName);
                System.out.println("Lettura classe effettuata");
                // Ottieni il costruttore delle classi Action e Trigger
                Constructor<?> actionConstructor = actionClass.getConstructor(String.class);
                Constructor<?> triggerConstructor = triggerClass.getConstructor(String.class);
                System.out.println("Costruttore ricevuto");
                // Usa reflection per creare un'istanza delle classi Action e Trigger
                Action action = (Action) actionConstructor.newInstance(actionMsg);
                Trigger trigger = (Trigger) triggerConstructor.newInstance(triggerMsg);
                System.out.println("Conversione effettuata");
                // Crea un nuovo oggetto Rule con le istanze Action e Trigger create
                Rule importedRule = new Rule(name, action, trigger,active);
                // Aggiungi la regola importata alla ruleList
                addRule(importedRule);
                System.out.println("Importazione regola "+ importedRule.getNameRule()+" effettuata");

            }
        }catch (IOException e) {
            System.out.println("Raggiunta la fine del file");
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ois.close();
        }
    }
    
    public void exportRule() throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedRule.dat"));       
        try  {
             for (Rule regola : ruleList){
                out.writeUTF(regola.getNameRule());
                out.writeUTF(regola.getAction().getClass().getName()); // Salva il nome della classe Action
                out.writeUTF(regola.getAction().toString()); 
                out.writeUTF(regola.getTrigger().getClass().getName()); // Salva il nome della classe Trigger
                out.writeUTF(regola.getTrigger().toString()); 
                out.writeBoolean(regola.getActive());
             }
            
        } catch (IOException e) {
            System.out.println("Salvataggio completato");
        }finally{
            out.close();
        }
    }
}
