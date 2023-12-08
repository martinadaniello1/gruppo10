package test;

import automatehub.model_view.Action;
import automatehub.model_view.AppendToFileAction;
import automatehub.model_view.AppendToFileActionCreator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppendToFileActionCreatorTest {

    private final String filePath = "C:\\Users\\marti\\OneDrive\\Desktop\\prova.txt";
    private final String stringToTest = "prova";
    private AppendToFileActionCreator appendStringCreator;

    @Before
    public void setUp() {
        appendStringCreator = new AppendToFileActionCreator(stringToTest, filePath);
    }
    
    @Test
    public void testCreate() {
        AppendToFileAction appendString = new AppendToFileAction(stringToTest, filePath);
        Action appendStringTest = appendStringCreator.create();
        assertEquals(appendStringTest, appendString);
    }
}