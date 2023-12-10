package test;

import automatehub.model_view.action.Action;
import automatehub.model_view.action.RemoveFileAction;
import automatehub.model_view.action.creator.RemoveFileActionCreator;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RemoveFileActionCreatorTest {
    private String filePath;
    private RemoveFileActionCreator creator;
    
    @Before
    public void setUp(){
        filePath="esempio/prova/prova.txt";
        creator = new RemoveFileActionCreator(filePath);
    }
    
    @Test
    public void testCreate(){
        RemoveFileAction r = new RemoveFileAction(filePath);
        Action action = creator.create();
        assertTrue(action.equals(r));
    }
    
}
