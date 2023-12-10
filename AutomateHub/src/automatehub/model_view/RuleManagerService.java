package automatehub.model_view;

import automatehub.model_view.trigger.*;
import automatehub.model_view.action.*;
import java.time.Duration;
import java.util.logging.*;
import java.util.*;
import java.io.*;
import java.time.Instant;
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
    private List<Thread> repeteableThreads;
    private boolean isCheckingRules = true;
    private Instant programShutdownTime;
    //Single instance of the class
    private static RuleManagerService instance = null;

    //Private constructor
    private RuleManagerService() {
        this.observers = new ArrayList<RuleObserver>();
        this.ruleList = new ArrayList<Rule>();
        this.repeteableThreads = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of RuleManagerService. If the instance
     * doesn't exist, it creates a new one.
     *
     * @return The singleton instance of RuleManagerService.
     */
    //Lazy initialization
    public static RuleManagerService getRuleManager() {
        //Crea l'oggetto solo se non esiste
        if (instance == null) {
            instance = new RuleManagerService();
        }
        return instance;
    }

    public void start() {
        new Thread(() -> {
            while (isCheckingRules) {
                synchronized (ruleList) {
                    for (Rule rule : ruleList) {
                        if (rule.getActive()) {
                            if (rule.getTrigger().check()) {
                                rule.setActive(false);
                                notifyRuleVerified(rule);
                                if (!rule.getPeriod().isZero()) {
                                    startRepeteableThread(rule, rule.getPeriod());
                                }
                            } else {
                                System.out.println("Rule verification failed: " + rule.toString());
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
        programShutdownTime = Instant.now();
        for (Thread thread : repeteableThreads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        saveProgramShutdownTime();
        System.out.println("Stop Thread eseguito correttamente");
    }

    private void saveProgramShutdownTime() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ProgramShutdownTime.dat"))) {
            out.writeObject(programShutdownTime);
            System.out.println("Program shutdown time saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        synchronized (ruleList) {
            ruleList.remove(r);
        }
        notifyRuleRemoved(r);

        System.out.println("Regola rimossa con successo: " + r.toString());
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

    private void startRepeteableThread(Rule rule, Duration sleepDuration) {
        Thread repeteable = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("repeteable Thread:  " + rule.getNameRule());
                    Thread.sleep(sleepDuration.toMillis());
                    rule.setActive(true);
                    Thread.currentThread().interrupt();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        repeteableThreads.add(repeteable);
        repeteable.start();
    }

    private Rule getRule(ObjectInputStream ois, Duration downtime) throws IOException, ClassNotFoundException {
        String name = ois.readUTF();
        Action action = (Action) ois.readObject();
        Trigger trigger = (Trigger) ois.readObject();
        boolean active = ois.readBoolean();
        Duration duration = (Duration) ois.readObject();
        Rule rule = new Rule(name, action, trigger, active, duration);
        if (!active && !duration.isZero()) {
            Duration remainingTime = duration.minus(downtime);

            if (remainingTime.isNegative()) {
                // Cosa deve fare????
                System.out.println("Regola già attivata: " + rule.getNameRule());
                notifyRuleNotExecuted(rule);
            } else {
                startRepeteableThread(rule, remainingTime);
            }
        }
        return rule;
    }

    public void importRule() throws FileNotFoundException, IOException {
        System.out.println("Recupero Rule salvate ***********");
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream("ProgramShutdownTime.dat"));
        try {
            programShutdownTime = (Instant) ois.readObject();
            System.out.println("Program shutdown time loaded: " + programShutdownTime);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Calculate the time difference
        Instant programRestartTime = Instant.now();
        Duration downtime = Duration.between(programShutdownTime, programRestartTime);

        FileInputStream fis = new FileInputStream("SavedRule.dat");
        ois = new ObjectInputStream(fis);
        System.out.println("        File aperto");
        if (ois == null) {
            return;
        }
        try {
            while (true) {
                Rule rule = getRule(ois, downtime);
                addRule(rule);
                System.out.println("        Importazione regola " + rule.getNameRule() + " effettuata");
            }
        } catch (IOException e) {
            System.out.println("Importazione completata");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RuleManagerService.class.getName()).log(Level.SEVERE, null, ex);
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

    private void notifyRuleNotExecuted(Rule rule) {
        observers.forEach(observer -> {
            observer.onRuleNotExecuted(rule);
        });
    }

}
