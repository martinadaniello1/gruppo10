package test;

import automatehub.controller.FXMLDocumentController;
import automatehub.model_view.DialogBoxAction;
import automatehub.model_view.Rule;
import automatehub.model_view.RuleManagerService;
import automatehub.model_view.TimeTrigger;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import java.time.Duration;
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
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("00:00"), true, Duration.ZERO);
        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullRule() {
        ruleManager.addRule(null);
    }

    /*@Test
    public void testSavingRule() throws IOException, ClassNotFoundException {
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("20:00"), true);
        ruleManager.addRule(rule);
        ruleManager.exportRule(); 
        assertTrue(ruleManager.getRuleList().contains(rule));

        ruleManager.removeRule(rule);
        //assertFalse(ruleManager.getRuleList().contains(rule));
        ruleManager.importRule();
        System.out.println(ruleManager.getRuleList());
        assertTrue(ruleManager.getRuleList().contains(rule));
    }*/
    
    @Test
    public void testRemoveRule() {
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("00:00"), true, Duration.ZERO);
        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule));

        ruleManager.removeRule(rule);

        assertFalse(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingRule() {
        Rule rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger("00:00"), true, Duration.ZERO);
        ruleManager.removeRule(rule);  // Questa regola non è stata aggiunta, quindi dovrebbe lanciare un'eccezione

    }

    @Test
    public void testRuleManagerSingleton() {
        RuleManagerService secondInstance = RuleManagerService.getRuleManager();
        assertSame(ruleManager, secondInstance);
    }

    @Test
    public void testAddObserver() {
        FXMLDocumentController controller = new FXMLDocumentController();
        ruleManager.addObserver(controller);
        assertTrue(ruleManager.getObservers().contains(controller));
    }

    @Test
    public void testRemoveObserver() {
        FXMLDocumentController controller = new FXMLDocumentController();
        ruleManager.addObserver(controller);
        assertTrue(ruleManager.getObservers().contains(controller));
        ruleManager.removeObserver(controller);
        assertTrue(!ruleManager.getObservers().contains(controller));
    }
}
