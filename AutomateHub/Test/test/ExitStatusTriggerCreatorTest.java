package test;

import automatehub.model_view.trigger.ExitStatusTrigger;
import automatehub.model_view.trigger.creator.ExitStatusTriggerCreator;
import automatehub.model_view.trigger.Trigger;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ExitStatusTriggerCreatorTest {

    private String filePath;
    private String[] commandLineArgs;
    private Integer exitCode;
    private ExitStatusTriggerCreator creator;

    @Before
    public void setUp() {
        filePath = "example/script.py";
        commandLineArgs = new String[]{"arg1", "arg2"};
        exitCode = 0;
        creator = new ExitStatusTriggerCreator(filePath, commandLineArgs, exitCode);
    }

    @Test
    public void testCreate() {
        ExitStatusTrigger expectedTrigger = new ExitStatusTrigger(filePath, commandLineArgs, exitCode);
        Trigger createdTrigger = creator.create();
        assertTrue(createdTrigger.equals(expectedTrigger));
    }
}
