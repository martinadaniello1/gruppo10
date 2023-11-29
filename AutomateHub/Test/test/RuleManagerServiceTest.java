package test;

import automatehub.model_view.DialogBoxAction;
import automatehub.model_view.Rule;
import automatehub.model_view.RuleManagerService;
import automatehub.model_view.TimeTrigger;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RuleManagerServiceTest {

    private RuleManagerService ruleManager;
    
    @BeforeClass
    public static void initJavaFX() {
        new JFXPanel(); // Inizializza il toolkit JavaFX
    }

    @Before
    public void setUp() {
        ruleManager = RuleManagerService.getRuleManager();
    }

    @AfterClass
    public static void shutdownJFX() {
        // Chiudi il toolkit di JavaFX
    }

    @Test
    public void testAddRule() {
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("00:00"), true);
        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullRule() {
        ruleManager.addRule(null);
    }
    
    @Test
    public void testImportRule() throws IOException {
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("20:00"), true);
        ruleManager.addRule(rule);
        ruleManager.exportRule();
        assertTrue(ruleManager.getRuleList().contains(rule));

        ruleManager.removeRule(rule);
        //assertFalse(ruleManager.getRuleList().contains(rule));
        ruleManager.importRule();
        System.out.println(ruleManager.getRuleList());
        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    @Test
    public void testRemoveRule() {
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("01:00"), true);
        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule));

        ruleManager.removeRule(rule);

        assertFalse(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingRule() {
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("10:00"), true);
        ruleManager.removeRule(rule);  // Questa regola non è stata aggiunta, quindi dovrebbe lanciare un'eccezione
    
    }

    @Test
    public void testRuleManagerSingleton() {
        RuleManagerService secondInstance = RuleManagerService.getRuleManager();
        assertSame(ruleManager, secondInstance);
    }

 @Test
    public void testRuleManagerService() throws InterruptedException {
            // crea il servizio all'interno di Platform.runLater() per eseguirlo nel thread di JavaFX
            Platform.runLater(() -> {
            RuleManagerService ruleManager = RuleManagerService.getRuleManager();

            ruleManager.start();  // Avvia il servizio

            // verifica che il servizio stia eseguendo
            assertTrue(ruleManager.isRunning());

            // dopo un po' verifica che il servizio non sia ancora stato cancellato
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(ruleManager.isRunning());

            // Cancella il servizio
            ruleManager.cancel();

            //  verifica che il servizio sia stato cancellato dopo un po'
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertFalse(ruleManager.isRunning());
        });
    }
}
