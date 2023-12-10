package test;

import automatehub.model_view.action.Action;
import automatehub.model_view.action.ExecutorFileAction;
import automatehub.model_view.action.creator.ExecutorFileActionCreator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ExecutorFileActionCreatorTest {

    private final String filePath = "./Test/test/testFiles/example.py";
    private ExecutorFileActionCreator executorFileCreator;

    @Before
    public void setUp() {
        executorFileCreator = new ExecutorFileActionCreator(filePath, new String[]{"15","17"});
    }
    
    @Test
    public void testCreate() {        
        ExecutorFileAction execute = new ExecutorFileAction(filePath,new String[]{"15","17"});
        Action action = executorFileCreator.create();
        assertTrue(execute.equals(action));
    }
}
    

