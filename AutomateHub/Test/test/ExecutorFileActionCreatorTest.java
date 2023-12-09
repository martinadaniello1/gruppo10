package test;

import automatehub.model_view.Action;
import automatehub.model_view.ExecutorFileAction;
import automatehub.model_view.ExecutorFileActionCreator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    
