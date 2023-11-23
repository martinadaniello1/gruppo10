package test ;

import automatehub.model_view.Rule;
import automatehub.model_view.RuleManagerService;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RuleManagerServiceTest {

    private RuleManagerService ruleManager;

    @Before
    public void setUp() {
        ruleManager = RuleManagerService.getRuleManager();
    }

    @After
    public void tearDown() {
        ruleManager.cancel();  // Assicurati di fermare il servizio dopo ogni test
    }

    @Test
    public void testAddRule() {
        Rule rule = new Rule();
        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullRule() {
        ruleManager.addRule(null);
    }

    @Test
    public void testRemoveRule() {
        Rule rule = new Rule();
        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule));

        ruleManager.removeRule(rule);

        assertFalse(ruleManager.getRuleList().contains(rule));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingRule() {
        Rule rule = new Rule();
        ruleManager.removeRule(rule);  // Questa regola non è stata aggiunta, quindi dovrebbe lanciare un'eccezione
    }

    @Test
    public void testRuleManagerSingleton() {
        RuleManagerService secondInstance = RuleManagerService.getRuleManager();
        assertSame(ruleManager, secondInstance);
    }

    @Test
    public void testRuleManagerService() throws InterruptedException {
        Rule rule = new Rule();
        ruleManager.addRule(rule);

        ruleManager.start();  // Avvia il servizio

        // Verifica che il servizio stia eseguendo
        assertTrue(ruleManager.isRunning());

        // Attendi un po' e verifica che il servizio non sia ancora stato cancellato
        Thread.sleep(3000);
        assertTrue(ruleManager.isRunning());

        // Cancella il servizio
        ruleManager.cancel();

        // Attendi un po' e verifica che il servizio sia stato cancellato
        Thread.sleep(3000);
        assertFalse(ruleManager.isRunning());
    }
}
