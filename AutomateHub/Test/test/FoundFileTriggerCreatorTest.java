/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import automatehub.model_view.FoundFileTrigger;
import automatehub.model_view.FoundFileTriggerCreator;
import automatehub.model_view.Trigger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class FoundFileTriggerCreatorTest {

    private static String checkFile;
    private static String checkDirectory;
    private FoundFileTriggerCreator fftc;

    @BeforeClass
    public static void setUpClass() {

        checkFile = "fileToSearch.txt";
        checkDirectory = "C:\\Users\\Luca\\Documents\\Task308";

    }

    @Before
    public void setUp() {
        fftc = new FoundFileTriggerCreator("fileToSearch.txt", "C:\\Users\\Luca\\Documents\\Task308");
    }

    @Test
    public void testFoundFileTriggerCreator() {

        assertEquals(checkFile, fftc.getFileToSearch());
        assertEquals(checkDirectory, fftc.getReferentDirectory());
    }

    /**
     * Test of getFileToSearch method, of class FoundFileTriggerCreator.
     */
    @Test
    public void testGetFileToSearch() {

        assertEquals(checkFile, fftc.getFileToSearch());
    }

    /**
     * Test of setFileToSearch method, of class FoundFileTriggerCreator.
     */
    @Test
    public void testSetFileToSearch() {

        fftc.setFileToSearch("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata\\income2015.txt");
        assertEquals("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata\\income2015.txt", fftc.getFileToSearch());
    }

    /**
     * Test of getReferentDirectory method, of class FoundFileTriggerCreator.
     */
    @Test
    public void testGetReferentDirectory() {

        assertEquals(checkDirectory, fftc.getReferentDirectory());
    }

    /**
     * Test of setReferentDirectory method, of class FoundFileTriggerCreator.
     */
    @Test
    public void testSetReferentDirectory() {

        fftc.setReferentDirectory("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata");
        assertEquals("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata", fftc.getReferentDirectory());
    }

    /**
     * Test of create method, of class FoundFileTriggerCreator.
     */
    @Test
    public void testCreate() {
        FoundFileTrigger fft = new FoundFileTrigger("fileToSearch.txt", "C:\\Users\\Luca\\Documents\\Task308");
        Trigger triggerReturned = fftc.create();
        assertEquals(fft, triggerReturned);
    }

}
