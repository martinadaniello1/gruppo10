package automatehub.model_view;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.ArrayList;

/**
 * The RuleManagerService class manages a list of rules, performs periodic
 * checks on active rules, and provides functionalities for adding, removing,
 * editing, importing, and exporting rules. It also implements the Observer
 * pattern to notify registered observers about rule-related events. This class
 * is designed as a Singleton to ensure a single instance throughout the
 * application.
 *
 */
public class RuleManagerService implements Serializable {

    private ArrayList<RuleObserver> observers;
    private ArrayList<Rule> ruleList;
    private Thread repeteable;
    private boolean isCheckingRules = true;
    //Unica istanza della classe
    private static RuleManagerService instance = null;

    //Costruttore privato
    private RuleManagerService() {
        this.observers = new ArrayList<RuleObserver>();
        this.ruleList = new ArrayList<Rule>();
    }

    ;
    
    //Lazy initialization
     /**
     * Returns the singleton instance of RuleManagerService. If the instance doesn't exist, it creates a new one.
     * @return The singleton instance of RuleManagerService.
     */
    public static RuleManagerService getRuleManager() {
        //Crea l'oggetto solo se non esiste
        if (instance == null) {
            instance = new RuleManagerService();
        }

        return instance;
    }

    public void start() {
        new Thread(() -> {
            System.out.println("MAIN Thread");
            while (isCheckingRules) {
                synchronized (ruleList) {
                    for (Rule rule : ruleList) {
                        if (rule.getActive()) {
                            if (rule.getTrigger().check()) {
                                notifyRuleVerified(rule);
                            } else {
                                System.out.println("Rule verification failed: " + rule.toString());
                            }
                            if (!rule.getPeriod().isZero()) {
                                repeteable = new Thread(() -> {
                                    System.out.println("repeteable Thread 0");
                                    while (!repeteable.isInterrupted()) {
                                        try {
                                            System.out.println("repeteable Thread");
                                            Thread.sleep(rule.getPeriod().toMillis());
                                            rule.setActive(true);
                                        } catch (InterruptedException ex) {
                                            repeteable.interrupt();
                                        }
                                    }
                                });
                                repeteable.start();
                            }
                        }
                    }
                }
                try {
                    // Sleep for 3 seconds before the next rule check
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }).start();
    }

    public void stop() {
        System.out.println("Stop Thread ....");
        isCheckingRules = false;
        if (repeteable != null) {
            repeteable.interrupt();
            try {
                repeteable.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Stop Thread eseguito correttamente");
    }

    public void addRule(Rule r) {
        if (r == null) {
            throw new IllegalArgumentException("Regola non valida");
        }
        synchronized (ruleList) {
            this.ruleList.add(r);
        }
        notifyRuleAdded(r);

        //Logging
        System.out.println("addRule in RuleManagerService eseguita correttamente");
        System.out.println(r.toString());

    }

    public void removeRule(Rule r) {

        try {
            synchronized (ruleList) {
                ruleList.remove(r);
            }
            notifyRuleRemoved(r);

            System.out.println("Regola rimossa con successo: " + r.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Regola non presente");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void editRule(Rule oldRule, Rule newRule) {
        synchronized (ruleList) {
            if (!newRule.equals(oldRule)) {
                this.ruleList.remove(oldRule);
                this.ruleList.add(newRule);
                notifyRuleEdited(oldRule, newRule);
            }
        }
    }

    public ArrayList<Rule> getRuleList() {
        return this.ruleList;
    }

    public ArrayList<RuleObserver> getObservers() {
        return this.observers;
    }

    public void importRule() throws IOException, ClassNotFoundException {
        System.out.println("Recupero Rule salvate ***********");

        FileInputStream fis = new FileInputStream("SavedRule.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        System.out.println("        File aperto");
        if (ois == null) {
            return;
        }

        try {
            while (true) {
                String name = ois.readUTF();
                Action action = (Action) ois.readObject();
                Trigger trigger = (Trigger) ois.readObject();
                boolean active = ois.readBoolean();
                Duration duration = (Duration) ois.readObject();

                Rule rule = new Rule(name, action, trigger, active, duration);
                addRule(rule);
                System.out.println("        Importazione regola " + rule.getNameRule() + " effettuata");
            }
        } catch (IOException e) {
            System.out.println("Importazione completata");
        } finally {
            ois.close();
        }
    }

    public void exportRule() throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedRule.dat"));
        try {
            for (Rule regola : ruleList) {

                out.writeUTF(regola.getNameRule());
                out.writeObject(regola.getAction());
                out.writeObject(regola.getTrigger());
                out.writeBoolean(regola.getActive());
                out.writeObject(regola.getPeriod());
            }
            System.out.println("Salvataggio completato");
        } catch (IOException e) {
            System.out.println("Salvataggio completato");
        } finally {
            out.close();
        }
    }

    //**************************************************************************
    //*******************   METHODS OBSERVER PATTERN
    public void addObserver(RuleObserver observer) {
        observers.add(observer);
    }

    // Rimuovi un osservatore
    public void removeObserver(RuleObserver observer) {
        observers.remove(observer);
    }

    // Notifica gli osservatori quando una regola viene aggiunta
    private void notifyRuleAdded(Rule rule) {
        observers.forEach(observer -> {
            observer.onRuleAdded(rule);
        });
    }

    // Notifica gli osservatori quando una regola viene rimossa
    private void notifyRuleRemoved(Rule rule) {
        observers.forEach(observer -> {
            observer.onRuleRemoved(rule);
        });
    }

    // Notifica gli osservatori quando una regola viene modificata
    private void notifyRuleEdited(Rule oldRule, Rule newRule) {
        observers.forEach(observer -> {
            observer.onRuleEdited(oldRule, newRule);
        });
    }

    private void notifyRuleVerified(Rule rule) {
        observers.forEach(observer -> {
            observer.onRuleVerified(rule);
        });
    }

}
