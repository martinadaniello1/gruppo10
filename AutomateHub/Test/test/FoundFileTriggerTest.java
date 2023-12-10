package test;

import automatehub.model_view.trigger.FoundFileTrigger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FoundFileTriggerTest {

    private static String checkFile;
    private static String checkDirectory;
    private FoundFileTrigger fft;

    @BeforeClass
    public static void setUpClass() {

        checkFile = "prova.txt";
        checkDirectory = "./Test/test/testFiles/";
    }

    @Before
    public void setUp() {

        fft = new FoundFileTrigger(checkFile, checkDirectory);
    }

    @Test
    public void testFoundFileTrigger() {

        assertEquals(fft.getFileToSearch(), checkFile);
        assertEquals(fft.getReferentDirectory(), checkDirectory);
    }

    /**
     * Test of getFileToSearch method, of class FoundFileTrigger.
     */
    @Test
    public void testGetFileToSearch() {
        assertEquals(fft.getFileToSearch(), checkFile);
    }

    /**
     * Test of setFileToSearch method, of class FoundFileTrigger.
     */
    @Test
    public void testSetFileToSearch() {

        fft.setFileToSearch("testing/the/set/method");
        assertEquals("testing/the/set/method", fft.getFileToSearch());
    }

    /**
     * Test of getReferentDirectory method, of class FoundFileTrigger.
     */
    @Test
    public void testGetReferentDirectory() {
        assertEquals(fft.getReferentDirectory(), checkDirectory);
    }

    /**
     * Test of setReferentDirectory method, of class FoundFileTrigger.
     */
    @Test
    public void testSetReferentDirectory() {
        fft.setReferentDirectory("testing/the/set/method");
        assertEquals("testing/the/set/method", fft.getReferentDirectory());
    }

    /**
     * Test of check method, of class FoundFileTrigger.
     */
    @Test
    public void testCheck() {
        assertEquals(true, fft.check());
    }

}
