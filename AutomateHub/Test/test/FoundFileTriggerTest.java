/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test;

import automatehub.model_view.FoundFileTrigger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca
 */
public class FoundFileTriggerTest {

    private static String checkFile;
    private static String checkDirectory;
    private FoundFileTrigger fft;

    @BeforeClass
    public static void setUpClass() {

        checkFile = "fileToSearch.txt";
        checkDirectory = "C:\\Users\\Luca\\Documents\\Task308";
    }

    @Before
    public void setUp() {

        fft = new FoundFileTrigger("fileToSearch.txt", "C:\\Users\\Luca\\Documents\\Task308");
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

        fft.setFileToSearch("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata\\income2015.txt");
        assertEquals("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata\\income2015.txt", fft.getFileToSearch());
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
        fft.setReferentDirectory("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata");
        assertEquals("C:\\Users\\Luca\\Documents\\hadoop-cluster-3.3.6-arm64\\hddata", fft.getReferentDirectory());
    }

    /**
     * Test of check method, of class FoundFileTrigger.
     */
    @Test
    public void testCheck() {

        assertEquals(true, fft.check());
    }

}
