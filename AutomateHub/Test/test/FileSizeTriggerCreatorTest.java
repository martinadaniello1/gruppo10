package test;

import automatehub.model_view.trigger.FileSizeTrigger;
import automatehub.model_view.trigger.FileSizeTriggerCreator;
import automatehub.model_view.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FileSizeTriggerCreatorTest {

    private String filePath;
    private Long specifiedSize;
    private FileSizeTriggerCreator creator;
    
    @Before
    public void setUp() {
        filePath = "./Test/test/testFiles/prova.txt";
        specifiedSize = 1200L;
        creator = new FileSizeTriggerCreator(filePath, specifiedSize);
    }
    
    @Test
    public void testCreate() {
        FileSizeTrigger trigger = new FileSizeTrigger(filePath, specifiedSize);
        assertEquals(trigger, creator.create());
    }

}
