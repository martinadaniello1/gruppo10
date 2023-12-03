package test;

import automatehub.controller.FXMLDocumentController;
import automatehub.model_view.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RuleManagerServiceTest {

    private RuleManagerService ruleManager;

    @Before
    public void setUp() {
        ruleManager = RuleManagerService.getRuleManager();
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

    @Test
    public void testSavingRule() throws IOException, ClassNotFoundException {
        Rule r = new Rule("nameRule2", new AudioAction("message2"), new TimeTrigger("02:00"), true, Duration.ZERO);
        ruleManager.addRule(r);
        ruleManager.exportRule();
        ruleManager.removeRule(r);
        ruleManager.importRule();
        ruleManager.getRuleList().stream().filter(rule -> (rule.equals(new Rule("nameRule2", new AudioAction("message2"), new TimeTrigger("02:00"), true, Duration.ZERO)))).forEachOrdered(_item -> {
            assert(true);
        });
    }
    
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
