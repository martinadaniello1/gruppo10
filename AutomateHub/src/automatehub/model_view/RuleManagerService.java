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
import java.util.ArrayList;

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
                
                        synchronized (ruleList) {
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
                        }
                    // Verifica le condizioni e esegui le azioni per ogni regola attiva
                    
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
        synchronized (ruleList) {
            try {
                ruleList.remove(r);
                System.out.println("Regola rimossa con successo: " + r.toString());
            } catch (IllegalArgumentException e) {
                System.out.println("Regola non presente");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
    
    public ObservableList<Rule> getRuleList(){
        return this.ruleList;
    }
    
    private CreatorAction createAction(String className, String[] message) {
        try {
            // Ottieni la classe utilizzando className
            Class<?> actionFactoryClass = Class.forName(className + "Creator");
            // Ottieni il costruttore della classe
            Constructor<?> actionConstructor = actionFactoryClass.getConstructor(String[].class);
            Object[] parameters = new Object[]{message}; // Creoo un array di oggetti (Object[]) e assegno un singolo elemento message

            CreatorAction actionFactory = (CreatorAction) actionConstructor.newInstance(parameters);
            return actionFactory;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private CreatorTrigger createTrigger(String className, String[] message) {
        try {
            Class<?> triggerFactoryClass = Class.forName(className+"Creator" );
            Constructor<?> triggerConstructor = triggerFactoryClass.getConstructor(String[].class);
            Object[] parameters = new Object[]{message}; // Creoo un array di oggetti (Object[]) e assegno un singolo elemento message

            CreatorTrigger triggerFactory = (CreatorTrigger) triggerConstructor.newInstance(parameters);
            return triggerFactory;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void importRule() throws IOException  {
        System.out.println("Recupero Rule salvate ***********");

        FileInputStream fis = new FileInputStream("SavedRule.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        System.out.println("        File aperto");
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
                String[] actionMsgArray =  actionMsg.split(";");
                String[] trigerrMsgArray = triggerMsg.split(";");
                
                CreatorAction ac = createAction(actionClassName,actionMsgArray);
                CreatorTrigger tr = createTrigger(triggerClassName,trigerrMsgArray);

                Rule importedRule = new Rule(name, ac.create(), tr.create(), active);
                
                addRule(importedRule);                
                System.out.println("        Importazione regola "+ importedRule.getNameRule()+" effettuata");
            }
        }catch (IOException e) {
            System.out.println("Importazione completata");
        } finally{
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
            System.out.println("Salvataggio completato");
        } catch (IOException e) {
            System.out.println("Salvataggio completato");
        }finally{
            out.close();
        }
    }
    
    
}
