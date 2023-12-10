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
    private List<Thread> repeatableThreads;
    private boolean isCheckingRules = true;
    private Instant programShutdownTime;
    //Single instance of the class
    private static RuleManagerService instance = null;

    //Private constructor
    private RuleManagerService() {
        this.observers = new ArrayList<RuleObserver>();
        this.ruleList = new ArrayList<Rule>();
        this.repeatableThreads = new ArrayList<>();
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

    /**
     * Starts the Rule verification thread and the Repeatable thread.
     */
    public void start() {
        new Thread(() -> {
            while (isCheckingRules) {
                synchronized (ruleList) {
                    /*Scans the rule list and checks if there are any active rules, checking if their condition is verified. 
                    In that case, notifies to the Observers*/
                    for (Rule rule : ruleList) {
                        if (rule.getActive()) {
                            if (rule.getTrigger().check()) {
                                rule.setActive(false);
                                notifyRuleVerified(rule);
                                /*Checks if the rule is repeatable - in that case, starts the repeatable thread. */
                                if (!rule.getPeriod().isZero()) {
                                    startRepeatableThread(rule, rule.getPeriod());
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

    /**
     * Stops all the running threads.
     */
    public void stop() {
        System.out.println("Stop Thread ....");
        isCheckingRules = false;
        programShutdownTime = Instant.now();
        for (Thread thread : repeatableThreads) {
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

    /**
     * Saves the shutdown time on a file.
     */
    private void saveProgramShutdownTime() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ProgramShutdownTime.dat"))) {
            out.writeObject(programShutdownTime);
            System.out.println("Program shutdown time saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the rule to the list and notifies the observers.
     *
     * @param r the rule to be added.
     */
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

    /**
     * Removes the rule from the list and notifies the observers.
     *
     * @param r the rule to be removed
     */
    public void removeRule(Rule r) {
        synchronized (ruleList) {
            ruleList.remove(r);
        }
        notifyRuleRemoved(r);

        System.out.println("Regola rimossa con successo: " + r.toString());
    }

    /**
     * Edits the rule
     *
     * @param oldRule the old rule to be modified
     * @param newRule the new rule
     */
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

    /**
     * Starts the repeatable threads.
     *
     * @param rule the rule to be repeated
     * @param sleepDuration the period of sleep for the thread
     */
    private void startRepeatableThread(Rule rule, Duration sleepDuration) {
        Thread repeatable = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("repeatable Thread:  " + rule.getNameRule());
                    Thread.sleep(sleepDuration.toMillis());
                    rule.setActive(true);
                    Thread.currentThread().interrupt();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        repeatableThreads.add(repeatable);
        repeatable.start();
    }

    /**
     * Reads a Rule object from the specified ObjectInputStream, adjusts its
     * properties based on downtime, and returns the resulting Rule.
     *
     * @param ois The ObjectInputStream from which to read the Rule object.
     * @param downtime The Duration representing the downtime to adjust the
     * rule's duration.
     * @return A Rule object read from the ObjectInputStream with adjusted
     * properties.
     * @throws IOException If an I/O error occurs while reading from the
     * ObjectInputStream.
     * @throws ClassNotFoundException If the class of a serialized object cannot
     * be found.
     */
    private Rule getRule(ObjectInputStream ois, Duration downtime) throws IOException, ClassNotFoundException {
        // Read attributes from ObjectInputStream
        String name = ois.readUTF();
        Action action = (Action) ois.readObject();
        Trigger trigger = (Trigger) ois.readObject();
        boolean active = ois.readBoolean();
        Duration duration = (Duration) ois.readObject();

        // Create a new Rule object with the read attributes
        Rule rule = new Rule(name, action, trigger, active, duration);

        // Adjust the rule based on downtime if it is inactive with a non-zero duration
        if (!active && !duration.isZero()) {
            Duration remainingTime = duration.minus(downtime);

            // Check if the rule has already been activated
            if (remainingTime.isNegative()) {
                System.out.println("Regola già attivata: " + rule.getNameRule());
                notifyRuleNotExecuted(rule);
            } else {
                // Start a repeatable thread for the rule with the remaining time
                startRepeatableThread(rule, remainingTime);
            }
        }

        // Return the resulting Rule
        return rule;
    }

    /**
     * Imports saved Rule objects from file, considering program shutdown and
     * restart times. The method reads the saved program shutdown time,
     * calculates downtime, and imports rules from the "SavedRule.dat" file with
     * adjusted durations based on downtime.
     *
     * @throws FileNotFoundException If the file containing the program shutdown
     * time or saved rules is not found.
     * @throws IOException If an I/O error occurs while reading from the
     * ObjectInputStream.
     */
    public void importRule() throws FileNotFoundException, IOException {
        System.out.println("Recupero Rule salvate ***********");

        // Initialize ObjectInputStream for reading program shutdown time
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream("ProgramShutdownTime.dat"));
        try {
            // Read the saved program shutdown time
            programShutdownTime = (Instant) ois.readObject();
            System.out.println("Program shutdown time loaded: " + programShutdownTime);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Calculate the time difference between program shutdown and restart
        Instant programRestartTime = Instant.now();
        Duration downtime = Duration.between(programShutdownTime, programRestartTime);

        // Initialize ObjectInputStream for reading saved rules
        FileInputStream fis = new FileInputStream("SavedRule.dat");
        ois = new ObjectInputStream(fis);
        System.out.println("        File aperto");

        try {
            // Read rules from the ObjectInputStream and add them to the rule manager
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

    /**
     * Exports the list of Rule objects to a file named "SavedRule.dat". The
     * method writes each Rule's attributes, including name, action, trigger,
     * activation status, and period, to the ObjectOutputStream for persistent
     * storage.
     *
     * @throws FileNotFoundException If the file "SavedRule.dat" cannot be
     * created or opened for writing.
     * @throws IOException If an I/O error occurs while writing to the
     * ObjectOutputStream.
     */
    public void exportRule() throws FileNotFoundException, IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SavedRule.dat"));
        try {
            // Iterate through the list of rules and write their attributes to the ObjectOutputStream
            for (Rule rule : ruleList) {
                out.writeUTF(rule.getNameRule());
                out.writeObject(rule.getAction());
                out.writeObject(rule.getTrigger());
                out.writeBoolean(rule.getActive());
                out.writeObject(rule.getPeriod());
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

    //Notifica gli osservatori quando la condizione di una regola è verificata
    private void notifyRuleVerified(Rule rule) {
        observers.forEach(observer -> {
            observer.onRuleVerified(rule);
        });
    }

    //Notifica gli osservatori quando una regola non viene eseguita dato che il programma era chiuso
    private void notifyRuleNotExecuted(Rule rule) {
        observers.forEach(observer -> {
            observer.onRuleNotExecuted(rule);
        });
    }

}
