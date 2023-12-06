package test;

import automatehub.model_view.CopyFileAction;
import automatehub.model_view.CopyFileActionCreator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CopyFileActionCreatorTest {

    private String startingPath;
    private String destinationPath;
    private CopyFileActionCreator creator;

    @Before
    public void setUp() {
        startingPath = "esempio/prova/prova.txt";
        destinationPath = "desktop/prova.txt";
        creator = new CopyFileActionCreator(startingPath, destinationPath);
    }
    
    @Test
    public void testCreate() {
        CopyFileAction action = new CopyFileAction(startingPath, destinationPath);
        assertEquals(action, creator.create());
        
    }

}
