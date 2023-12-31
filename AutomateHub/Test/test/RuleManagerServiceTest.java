package test;

import automatehub.model_view.trigger.TimeTrigger;
import automatehub.model_view.action.AudioAction;
import automatehub.model_view.action.DialogBoxAction;
import automatehub.controller.FXMLDocumentController;
import automatehub.model_view.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RuleManagerServiceTest {

    private RuleManagerService ruleManager;
    private Rule rule;

    @Before
    public void setUp() {
        ruleManager = RuleManagerService.getRuleManager();
        rule = new Rule("nameRule", new DialogBoxAction("message"), new TimeTrigger(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)), true, Duration.ZERO);

    }

    @Test
    public void testAddRule() {
        ruleManager.addRule(rule);
        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullRule() {
        ruleManager.addRule(null);
    }

    @Test
    public void testSavingRule() throws IOException, ClassNotFoundException {
        Rule r = new Rule("nameRule2", new AudioAction("message2"), new TimeTrigger(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)), true, Duration.ZERO);
        ruleManager.addRule(r);
        ruleManager.exportRule();
        ruleManager.removeRule(r);
        ruleManager.importRule();
        ruleManager.getRuleList().stream().filter(rule -> (rule.equals(new Rule("nameRule2", new AudioAction("message2"), new TimeTrigger(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)), true, Duration.ZERO)))).forEachOrdered(_item -> {
            assert (true);
        });
    }

    @Test
    public void testRemoveRule() {
        ruleManager.addRule(rule);
        assertTrue(ruleManager.getRuleList().contains(rule));
        ruleManager.removeRule(rule);
        assertFalse(ruleManager.getRuleList().contains(rule));
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
