package test;

import automatehub.model_view.trigger.FoundFileTrigger;
import automatehub.model_view.trigger.FoundFileTriggerCreator;
import automatehub.model_view.trigger.Trigger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FoundFileTriggerCreatorTest {

    private static String checkFile;
    private static String checkDirectory;
    private FoundFileTriggerCreator fftc;

    @BeforeClass
    public static void setUpClass() {

        checkFile = "prova.txt";
        checkDirectory = "./Test/test/testFiles/prova.txt";

    }

    @Before
    public void setUp() {
        fftc = new FoundFileTriggerCreator(checkFile, checkDirectory);
    }

    /**
     * Test of create method, of class FoundFileTriggerCreator.
     */
    @Test
    public void testCreate() {
        FoundFileTrigger fft = new FoundFileTrigger(checkFile, checkDirectory);
        Trigger triggerReturned = fftc.create();
        assertEquals(fft, triggerReturned);
    }

}
