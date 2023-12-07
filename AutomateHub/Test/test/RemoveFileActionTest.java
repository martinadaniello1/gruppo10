package test;

import automatehub.model_view.RemoveFileAction;
import java.io.File;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class RemoveFileActionTest {
    private String filePath;
    private RemoveFileAction removeFileAction;
    @Before
    
    public void setUp(){
        filePath="C:/Users/adc01/OneDrive/Desktop/provaDirectory2/rimuovi.txt";
        removeFileAction = new RemoveFileAction(filePath);
    }
    
    @Test
    public void testExecuteShouldRemoveFile(){
        int result = removeFileAction.execute();
        
        assertEquals(0,result);
        //Verify the presente of the file into the directory
        File f = new File(filePath);
        assertEquals(false, f.exists());
    }
    
    @Test
    public void testEcecuteShouldReturnErrorNegativeValueIfFileDoesNotExist(){
        int result = removeFileAction.execute();
        assertEquals(-1,result);
        }

    @Test
    public void testGetFilePath(){
        String provaFile = filePath;
        assertEquals(removeFileAction.getFilePath(),provaFile);
    }
    
    @Test
    public void testSetFilePath(){
        String filePath2 = "filePath/prova/prova.txt";
        removeFileAction.setFilePath(filePath2);
        assertEquals(filePath2, removeFileAction.getFilePath());
    }
    
    @Test
    public void testGetParam1(){
        assertEquals(removeFileAction.getParam1(), removeFileAction.getFilePath());
    }
    
    @Test
    public void testGetParam2(){
        assertEquals(removeFileAction.getParam2(), "");
    }
    
}
