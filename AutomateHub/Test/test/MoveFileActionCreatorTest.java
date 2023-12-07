package test;

import automatehub.model_view.MoveFileAction;
import automatehub.model_view.MoveFileActionCreator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoveFileActionCreatorTest {

    private String startingPath;
    private String destinationPath;
    private MoveFileActionCreator creator;

    @Before
    public void setUp() {
        startingPath = "esempio/prova/prova.txt";
        destinationPath = "esempio2/prova2/prova.txt";
        creator = new MoveFileActionCreator(startingPath, destinationPath);
    }
    
    @Test
    public void testCreate() {
        MoveFileAction action = new MoveFileAction(startingPath, destinationPath);
        assertEquals(action, creator.create());
        
    }

}
